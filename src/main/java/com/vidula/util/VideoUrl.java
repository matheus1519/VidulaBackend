package com.vidula.util;

public class VideoUrl {

    private Long id;
    private String url;

    public VideoUrl(String url, Long id) {
        this.url = url;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
