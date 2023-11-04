package io.akormous.portfoliov2.model;

public class ErrorResponseBody implements ResponseBody {
    private String error;

    public ErrorResponseBody(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
