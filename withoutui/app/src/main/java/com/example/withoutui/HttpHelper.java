package com.example.withoutui;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URISyntaxException;
import java.net.URL;


public class HttpHelper extends AsyncTask<String, String, String> {

    public static HttpURLConnection urlConnection;
    private static String responseCode;

    public static String getResponseCode() {
        return responseCode;
    }

    private void setResponseCode(String responseCode) {
       HttpHelper.responseCode = responseCode;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        responseCode = "";
        if(strings[0] != null) {
            try {
                URL url = new URL(strings[0]);
                Proxy proxy = ProxySelector.getDefault().select(url.toURI()).get(0);
                urlConnection = (HttpURLConnection) url.openConnection(proxy);
                urlConnection.setRequestMethod("POST");
//                urlConnection.setRequestProperty("Content-Type", "application/json");
//                urlConnection.setRequestProperty("Accept", "application/json");
                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(strings[1]);
                wr.flush();
                wr.close();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                setResponseCode(String.valueOf(urlConnection.getResponseCode()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            } finally {
                try {
                    urlConnection.disconnect();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return result == "" ? "Oops. Something went wrong!" : result ;
    }
}
