package com.github.swapbook.model;

public enum Specimen_status {
    AVAILABLE,WAIT_FOR_ACCEPT, ON_LOAN, READY_FOR_RETURN, NOT_AVAILABLE;

    @Override
    public String toString() {
        String defaultConversion = super.toString();
        String nowyString = defaultConversion.toLowerCase();
        return nowyString;
    }
}
