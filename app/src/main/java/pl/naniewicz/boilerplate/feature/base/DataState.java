package pl.naniewicz.boilerplate.feature.base;

import javax.inject.Inject;

public class DataState<T> {

    private T data;
    private String errorMessage;

    @Inject
    public DataState() {
        clear();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean hasData() {
        return data != null;
    }

    public boolean hasErrorMessage() {
        return errorMessage != null;
    }

    public boolean isAvailable() {
        return hasData() || hasErrorMessage();
    }

    public void clear() {
        data = null;
        errorMessage = null;
    }
}
