package com.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public interface RestServiceUtil {

    HttpURLConnection urlConnectionValidator(URL url) throws InterruptedException, IOException;

    String postUsingXML(String serviceUrl, String request) throws IOException;
    String postUsingJson(String serviceUrl, String request) throws IOException;
}
