package com.individualcollection.entity;

public class UrlsAndResponses {
    private String url;
    private Boolean flg;
    private Integer responseCode;

    @Override
    public String toString() {
        return "UrlsAndResponses{" +
                "url='" + url + '\'' +
                ", flg=" + flg +
                ", responseCode=" + responseCode +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getFlg() {
        return flg;
    }

    public void setFlg(Boolean flg) {
        this.flg = flg;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
}
