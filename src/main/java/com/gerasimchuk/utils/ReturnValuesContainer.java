package com.gerasimchuk.utils;

import com.gerasimchuk.enums.UpdateMessageType;

public class ReturnValuesContainer<T> {
    private UpdateMessageType updateMessageType;
    private T returnedValue;

    public ReturnValuesContainer() {
    }

    public ReturnValuesContainer(UpdateMessageType updateMessageType, T returnedValue) {
        this.updateMessageType = updateMessageType;
        this.returnedValue = returnedValue;
    }

    public UpdateMessageType getUpdateMessageType() {
        return updateMessageType;
    }

    public void setUpdateMessageType(UpdateMessageType updateMessageType) {
        this.updateMessageType = updateMessageType;
    }

    public T getReturnedValue() {
        return returnedValue;
    }

    public void setReturnedValue(T returnedValue) {
        this.returnedValue = returnedValue;
    }
}
