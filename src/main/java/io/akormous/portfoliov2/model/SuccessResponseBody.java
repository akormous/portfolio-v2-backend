package io.akormous.portfoliov2.model;

public class SuccessResponseBody implements ResponseBody {
    private String message;

    public SuccessResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
