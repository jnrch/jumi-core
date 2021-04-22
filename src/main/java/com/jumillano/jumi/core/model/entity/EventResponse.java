package com.jumillano.jumi.core.model.entity;

public class EventResponse {

    private boolean ok = true;
    private Event event;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
