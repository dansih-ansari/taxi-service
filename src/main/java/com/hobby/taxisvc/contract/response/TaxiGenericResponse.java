package com.hobby.taxisvc.contract.response;

import com.hobby.taxisvc.error.TaxiError;

import java.util.List;

public class TaxiGenericResponse {
    private Object data;
    private List<TaxiError> errors;

    public TaxiGenericResponse( List<TaxiError> errors) {
        this.data = null;
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<TaxiError> getErrors() {
        return errors;
    }

    public void setErrors(List<TaxiError> errors) {
        this.errors = errors;
    }
}
