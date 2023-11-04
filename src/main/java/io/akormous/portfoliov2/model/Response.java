package io.akormous.portfoliov2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Response {
    private ResponseBody body;
    private Integer statusCode;

    private HashMap<String,String> headers;
    private Boolean isBase64Encoded;
    private List<String> cookies;

    public Response(ResponseBody body, Integer statusCode) {
        this.body = body;
        this.statusCode = statusCode;
        this.headers = new HashMap<>();
        this.headers.put("content-type","application/json");
        this.isBase64Encoded = false;
        this.cookies = new ArrayList<>();
    }

    public ResponseBody getBody() {
        return body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public Boolean getBase64Encoded() {
        return isBase64Encoded;
    }

    public List<String> getCookies() {
        return cookies;
    }
}
