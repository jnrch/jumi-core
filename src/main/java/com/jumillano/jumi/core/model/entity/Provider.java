package com.jumillano.jumi.core.model.entity;

import com.jumillano.jumi.core.model.enums.AccountingArea;
import com.jumillano.jumi.core.model.enums.UsualPayment;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "providers")
public class Provider {

    @Id
    private String id;
    private String name;
    private String businessName;
    private AccountingArea accountingArea;
    private String documentNumber;
    private String address;
    private String email;
    private Integer phone;
    private Integer cellPhone;
    private UsualPayment usualPayment;
    private Integer usualPaymentTerm;
    private Boolean acceptThirdPartyCheck;
    private Boolean isWithholdingAndPerceptionExempt;
    private List<String> files;
    private SalesContact salesContact;
    private AdministrativeContact administrativeContact;

    public Provider() {
    }

    public Provider(String id) {
        this.id = id;
    }

    public Provider(String id, String name, String businessName, AccountingArea accountingArea,
                    String documentNumber, String address, String email, Integer phone, Integer cellPhone,
                    UsualPayment usualPayment, Integer usualPaymentTerm, Boolean acceptThirdPartyCheck,
                    Boolean isWithholdingAndPerceptionExempt, List<String> files, SalesContact salesContact,
                    AdministrativeContact administrativeContact) {
        this.id = id;
        this.name = name;
        this.businessName = businessName;
        this.accountingArea = accountingArea;
        this.documentNumber = documentNumber;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.usualPayment = usualPayment;
        this.usualPaymentTerm = usualPaymentTerm;
        this.acceptThirdPartyCheck = acceptThirdPartyCheck;
        this.isWithholdingAndPerceptionExempt = isWithholdingAndPerceptionExempt;
        this.files = files;
        this.salesContact = salesContact;
        this.administrativeContact = administrativeContact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public AccountingArea getAccountingArea() {
        return accountingArea;
    }

    public void setAccountingArea(AccountingArea accountingArea) {
        this.accountingArea = accountingArea;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Integer cellPhone) {
        this.cellPhone = cellPhone;
    }

    public UsualPayment getUsualPayment() {
        return usualPayment;
    }

    public void setUsualPayment(UsualPayment usualPayment) {
        this.usualPayment = usualPayment;
    }

    public Integer getUsualPaymentTerm() {
        return usualPaymentTerm;
    }

    public void setUsualPaymentTerm(Integer usualPaymentTerm) {
        this.usualPaymentTerm = usualPaymentTerm;
    }

    public Boolean getAcceptThirdPartyCheck() {
        return acceptThirdPartyCheck;
    }

    public void setAcceptThirdPartyCheck(Boolean acceptThirdPartyCheck) {
        this.acceptThirdPartyCheck = acceptThirdPartyCheck;
    }

    public Boolean getWithholdingAndPerceptionExempt() {
        return isWithholdingAndPerceptionExempt;
    }

    public void setWithholdingAndPerceptionExempt(Boolean withholdingAndPerceptionExempt) {
        isWithholdingAndPerceptionExempt = withholdingAndPerceptionExempt;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public SalesContact getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(SalesContact salesContact) {
        this.salesContact = salesContact;
    }

    public AdministrativeContact getAdministrativeContact() {
        return administrativeContact;
    }

    public void setAdministrativeContact(AdministrativeContact administrativeContact) {
        this.administrativeContact = administrativeContact;
    }
}
