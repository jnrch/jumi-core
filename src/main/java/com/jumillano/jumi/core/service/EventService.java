package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.exceptions.ResourceNotFoundException;
import com.jumillano.jumi.core.model.dao.IEventDao;
import com.jumillano.jumi.core.model.dao.IProviderDao;
import com.jumillano.jumi.core.model.entity.*;
import com.jumillano.jumi.core.model.enums.PaymentMethod;
import com.jumillano.jumi.core.model.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final static Logger logger = LoggerFactory.getLogger(EventService.class);

    private IEventDao eventDao;
    private IUploadFileService uploadFileService;
    private IProviderDao providerDao;

    @Autowired
    public EventService(IEventDao eventDao, IUploadFileService uploadFileService,
                        IProviderDao providerDao) {
        this.eventDao = eventDao;
        this.uploadFileService = uploadFileService;
        this.providerDao = providerDao;
    }

    public Collection<PaymentMethodResponse> groupPaymentByDate() throws ParseException {
        List<Event> events = eventDao.findAll();
        Collection<PaymentMethodResponse> paymentMethodResponseList = new ArrayList<>();

        Map<Object, Map<PaymentMethod, Double>> totalAmountByDateAndPaymentType = events.stream()
                .collect(Collectors.groupingBy(e -> e.getDateStart(),
                                Collectors.groupingBy(Event::getPaymentMethod,
                                        Collectors.summingDouble(Event::getAmount))));

       for (Map.Entry<Object, Map<PaymentMethod, Double>> totalAmountByDateAndPaymentTypeMap: totalAmountByDateAndPaymentType.entrySet()) {
           PaymentMethodResponse paymentMethodResponse = new PaymentMethodResponse();
           Collection<PaymentResponse> paymentList = new ArrayList<>();
           BigDecimal bigTotalPaymentType = BigDecimal.ZERO;

           Object dt = totalAmountByDateAndPaymentTypeMap.getKey();
           Date dateStartConvert = new SimpleDateFormat("yyyy-MM-dd").parse(dt.toString());

           paymentMethodResponse.setEnd(dateStartConvert);
           paymentMethodResponse.setStart(dateStartConvert);

            for(Map.Entry<PaymentMethod, Double> paymentAmountMap : totalAmountByDateAndPaymentTypeMap.getValue().entrySet()) {

                BigDecimal baseAmount = BigDecimal.valueOf(paymentAmountMap.getValue());

                PaymentMethod paymentMethod = paymentAmountMap.getKey();
                String paymentMethodValue = "";
                switch (paymentMethod) {
                    case CASH:
                        paymentMethodValue =  "Efectivo";
                        break;
                    case TRANSFER:
                        paymentMethodValue = "Transferencia";
                        break;
                    case CHECK:
                        paymentMethodValue = "Cheque";
                        break;
                    case CREDIT_CARD:
                        paymentMethodValue = "Tdc";
                        break;
                    default:
                    logger.error("Invalid paymentMethod type {}", paymentMethod);
                    throw new IllegalArgumentException("Invalid paymentMethod type: " + paymentMethod);
                }

                PaymentResponse payment = new PaymentResponse(paymentMethodValue, paymentAmountMap.getValue());
                paymentList.add(payment);
                bigTotalPaymentType = bigTotalPaymentType.add(baseAmount);

            }
           paymentMethodResponse.setBigTotal(bigTotalPaymentType.doubleValue());
           paymentMethodResponse.setPayments(paymentList);
           paymentMethodResponseList.add(paymentMethodResponse);
        }

        return paymentMethodResponseList;
    }

    public List<Event> findAll() {
        return eventDao.findAll();
    }

    public List<Event> findEventByUserAndRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<Event> events;
        String userId = userDetails.getId();
        ArrayList<String> roles = new ArrayList<>();

        for (GrantedAuthority grantedAuthority: userDetails.getAuthorities()) {
            roles.add(grantedAuthority.getAuthority());
        }

        if (roles.contains("ROLE_USER")) {
            events = eventDao.findByStatus("PAID");
        } else if (roles.contains("ROLE_LEADER")) {
            events = eventDao.findByUser(userId);
        } else {
            events = eventDao.findAll();
        }

        return events;
    }

    public Optional<Event> findById(String id) {
        Optional<Event> currentEvent = eventDao.findById(id);
        if(!currentEvent.isPresent()) throw new ResourceNotFoundException();
       return currentEvent;
    }

    public ResponseEntity<EventResponse> saveEvent(MultipartFile[] files, String provider, Double amount, Status status, String observation, Date start, Date end, PaymentMethod paymentMethod, Boolean processed) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String userId = userDetails.getId();
        String fileName;
        ArrayList<String> filesName = new ArrayList<>();

        User user = new User(userId);
        Event event = new Event();
        Optional<Provider> currentProvider = providerDao.findById(provider);

        if(!currentProvider.isPresent()) throw new ResourceNotFoundException();

        status = (status == null) ? Status.TOPAY : status;
        processed = processed != null && processed;

        event.setUser(user);
        event.setProvider(currentProvider.get());
        event.setAmount(amount);
        event.setStatus(status);
        event.setObservation(observation);
        event.setStart(start);
        event.setEnd(end);
        event.setPaymentMethod(paymentMethod);
        event.setIsProcessed(processed);

        if (files != null) {
            try {
                for (MultipartFile file: files) {
                    fileName = uploadFileService.copy(file);
                    filesName.add(fileName);
                }
            } catch (IOException e) {
                logger.error("Could not upload file: " + files, e);
                throw new RuntimeException(e);
            }
        }
        event.setFile(filesName);

        EventResponse eventResponse = new EventResponse();
        eventResponse.setEvent(event);
        eventResponse.setOk(true);

        eventDao.save(event);

        return new ResponseEntity<>(eventResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<EventResponse> updateEvent(String id, MultipartFile[] files, String provider, Double amount, Status status, String observation, Date start, Date end, PaymentMethod paymentMethod) {
        Optional<Event> currentEvent = eventDao.findById(id);
        String fileName;
        //ArrayList<String> filesName = new ArrayList<>();
        Event event = new Event();
        List<String> filesName = currentEvent.get().getFile();
        Optional<Provider> currentProvider = providerDao.findById(provider);

        if(!currentProvider.isPresent()) throw new ResourceNotFoundException();

        event.setId(currentEvent.get().getId());
        event.setProvider(currentProvider.get());
        event.setAmount(amount);
        event.setStatus(status);
        event.setObservation(observation);
        event.setStart(start);
        event.setEnd(end);
        event.setPaymentMethod(paymentMethod);
        event.setUser(currentEvent.get().getUser());
        if (files != null) {
            try {
                for (MultipartFile file: files) {
                    fileName = uploadFileService.copy(file);
                    filesName.add(fileName);
                }
            } catch (IOException e) {
                logger.error("Could not upload file: " + files, e);
                throw new RuntimeException(e);
            }
        }
        event.setFile(filesName);

        EventResponse eventResponse = new EventResponse();
        eventResponse.setEvent(event);
        eventResponse.setOk(true);

        eventDao.save(event);

        return new ResponseEntity<>(eventResponse, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Resource> getFile(String fileName) {
        Resource resource = null;

        try {
            resource = uploadFileService.load(fileName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<>(resource, header, HttpStatus.OK);

    }

    public void deleteEvent(String id) {
        eventDao.deleteById(id);
    }

    public Optional<Event> deleteFile(String fileName, String id) {

        Optional<Event> currentEvent = eventDao.findById(id);

        if(!currentEvent.isPresent()) throw new ResourceNotFoundException();
        uploadFileService.delete(fileName);
        List<String> files = currentEvent.get().getFile();
        files.remove(fileName);

        currentEvent.get().setFile(files);
        eventDao.save(currentEvent.get());

        return currentEvent;
    }
}
