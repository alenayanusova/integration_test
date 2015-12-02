package com.silverrailtech.ninja.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Response for api requests.
 *
 * @author Alexander Latysh
 * @version 1.0
 * @since 2015-08-10
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response<T> {

    private String type;
    private Integer errorCode;
    private String message;
    private T data;

    public Response(String type, Integer errorCode, String message, T data) {
        this.type = type;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public Response() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

}
