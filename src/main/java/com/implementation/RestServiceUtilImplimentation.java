package com.implementation;

import com.services.RestServiceUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class RestServiceUtilImplimentation implements RestServiceUtil {
    @Override
    public HttpURLConnection urlConnectionValidator(URL url) throws InterruptedException, IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /**
         * Set the method for the URL request, one of:
         * <UL>
         *  <LI>GET
         *  <LI>POST
         *  <LI>HEAD
         *  <LI>OPTIONS
         *  <LI>PUT
         *  <LI>DELETE
         *  <LI>TRACE
         * </UL> are legal, subject to protocol restrictions.  The default
         * method is GET.
         *
         * @param method the HTTP method
         * @exception ProtocolException if the method cannot be reset or if
         *              the requested method isn't valid for HTTP.
         * @exception SecurityException if a security manager is set and the
         *              method is "TRACE", but the "allowHttpTrace"
         *              NetPermission is not granted.
         * @see #getRequestMethod()
         */
//        connection.setRequestMethod("HEAD");
        switch (connection.getResponseCode()) {
            case HttpURLConnection.HTTP_ACCEPTED:
//                System.out.println(url + "**Status-Code 202: Accepted**");
                break;
            case HttpURLConnection.HTTP_BAD_GATEWAY:
//                System.out.println(url + "**HTTP Status-Code 502: Bad Gateway.**");
                break;
            case HttpURLConnection.HTTP_BAD_METHOD:
//                System.out.println(url + "**HTTP Status-Code 405: Method Not Allowed.**");
                break;

            case HttpURLConnection.HTTP_BAD_REQUEST:      //200
//                System.out.println(url + " **HTTP Status-Code 400: Bad Request.**");
                return connection; // **EXIT POINT** fine, go on
            case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
//                System.out.println(url + " **HTTP Status-Code 408: Request Time-Out.\n**");
                break;// retry
            case HttpURLConnection.HTTP_CONFLICT:
//                System.out.println(url + "**HTTP Status-Code 409: Conflict.\n**");
                break;
            case HttpURLConnection.HTTP_CREATED:
//                System.out.println(url + "***HTTP Status-Code 201: Created.\n**");
                break;
            case HttpURLConnection.HTTP_ENTITY_TOO_LARGE:
//                System.out.println(url + "***HTTP Status-Code 413: Request Entity Too Large.\n**");
                break;
            case HttpURLConnection.HTTP_FORBIDDEN:
//                System.out.println(url + "***HTTP Status-Code 403: Forbidden.\n**");
            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
//                System.out.println(url + "***HTTP Status-Code 504: Gateway Timeout.\n**");
                break;
            case HttpURLConnection.HTTP_GONE:
//                System.out.println(url + "***HTTP Status-Code 410: Gone.\n**");
                break;
            case HttpURLConnection.HTTP_LENGTH_REQUIRED:
//                System.out.println(url + "***HTTP Status-Code 411: Length Required.\n**");
                break;
            case HttpURLConnection.HTTP_MOVED_PERM:
//                System.out.println(url + "***HTTP Status-Code 301: Moved Permanently.\n**");
                break;
            case HttpURLConnection.HTTP_MOVED_TEMP:
//                System.out.println(url + "***HTTP Status-Code 302: Temporary Redirect.\n**");
                break;
            case HttpURLConnection.HTTP_MULT_CHOICE:
//                System.out.println(url + "***HTTP Status-Code 300: Multiple Choices.\n**");
                break;
            case HttpURLConnection.HTTP_NO_CONTENT:
//                System.out.println(url + "***HTTP Status-Code 204: No Content.\n**");
                break;
            case HttpURLConnection.HTTP_NOT_ACCEPTABLE:
//                System.out.println(url + "***HTTP Status-Code 406: Not Acceptable.\n**");
                break;
            case HttpURLConnection.HTTP_NOT_AUTHORITATIVE:
//                System.out.println(url + "***HTTP Status-Code 203: Non-Authoritative Information.\n**");
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
//                System.out.println(url + "***HTTP Status-Code 404: Not Found.\n**");
                break;
            case HttpURLConnection.HTTP_NOT_IMPLEMENTED:
//                System.out.println(url + "***HTTP Status-Code 501: Not Implemented.\n**");
                break;
            case HttpURLConnection.HTTP_NOT_MODIFIED:
//                System.out.println(url + "***HTTP Status-Code 304: Not Modified.\n**");
                break;
            case HttpURLConnection.HTTP_OK:
//                System.out.println(url + "***HTTP Status-Code 200: OK.\n**");
                break;
            case HttpURLConnection.HTTP_PARTIAL:
//                System.out.println(url + "***HTTP Status-Code 206: Partial Content.\n**");
                break;
            case HttpURLConnection.HTTP_PRECON_FAILED:
//                System.out.println(url + "***HTTP Status-Code 402: Payment Required.\n**");
                break;
            case HttpURLConnection.HTTP_PROXY_AUTH:
//                System.out.println(url + "***HTTP Status-Code 407: Proxy Authentication Required.\n**");
                break;
            case HttpURLConnection.HTTP_REQ_TOO_LONG:
//                System.out.println(url + "***HTTP Status-Code 414: Request-URI Too Large.\n**");
                break;
            case HttpURLConnection.HTTP_RESET:
//                System.out.println(url + "***HTTP Status-Code 205: Reset Content.\n**");
                break;
            case HttpURLConnection.HTTP_SEE_OTHER:
//                System.out.println(url + "***HTTP Status-Code 303: See Other.\n**");
                break;
            case HttpURLConnection.HTTP_SERVER_ERROR:
//                System.out.println(url + "***Deprecated. \n**");
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
//                System.out.println(url + "***HTTP Status-Code 401: Unauthorized.\n**");
                break;
            case HttpURLConnection.HTTP_UNAVAILABLE:
//                System.out.println(url + "***HTTP Status-Code 503: Service Unavailable.\n**");
                break;
            case HttpURLConnection.HTTP_UNSUPPORTED_TYPE:
//                System.out.println(url + "***HTTP Status-Code 415: Unsupported Media Type.\n**");
                break;
            case HttpURLConnection.HTTP_USE_PROXY:
//                System.out.println(url + "***HTTP Status-Code 305: Use Proxy.\n**");
                break;
            case HttpURLConnection.HTTP_VERSION:
//                System.out.println(url + "***HTTP Status-Code 505: HTTP Version Not Supported.\n**");
                break;

            default:
                System.out.println(url + " **unknown response code**.");
                break; // abort
        }
        connection.disconnect();

        return connection;
    }

    public String postUsingXML(String serviceUrl, String request) throws IOException {
        URL url = new URL(serviceUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        // Set timeout as per needs
        httpURLConnection.setConnectTimeout(20000);
        httpURLConnection.setReadTimeout(20000);

        // Default is false
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.connect();

        // Set Headers
        httpURLConnection.setRequestProperty("Content-Type", "application/xml; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/xml");

        // Write XML
        OutputStream outputStream = httpURLConnection.getOutputStream();
        byte[] b = request.getBytes("UTF-8");
        outputStream.write(b);
        outputStream.flush();
        outputStream.close();

        // For POST only - END

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        StringBuffer response = null;
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
        return response.toString();

    }

    @Override
    public String postUsingJson(String serviceUrl, String request) throws IOException {
        URL url = new URL(serviceUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        // Set timeout as per needs
        httpURLConnection.setConnectTimeout(20000);
        httpURLConnection.setReadTimeout(20000);
        // Default is false
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.connect();
        // Set Headers
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");

        try(OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = request.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        byte[] outputBytes = "{'value': 7.5}".getBytes("UTF-8");
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(outputBytes);

        outputStream.close();
        outputStream.flush();

        // For POST only - END

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        StringBuffer response = null;
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
        return response.toString();

    }
}
