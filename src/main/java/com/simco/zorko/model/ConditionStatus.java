package com.simco.zorko.model;

public class ConditionStatus extends Condition {

    private String status;

    public ConditionStatus() {
        super();
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean evaluate() {

        // lookup the status of the object in question
        String objectStatus = getGame().getItem( getObjectName() ).getStatus();

        // if object status equals condition status, this condition has been met
        return objectStatus.equalsIgnoreCase( this.status );
    }

}
