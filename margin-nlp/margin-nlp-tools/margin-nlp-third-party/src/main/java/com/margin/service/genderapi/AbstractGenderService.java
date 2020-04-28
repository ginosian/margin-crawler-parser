package com.margin.service.genderapi;

import com.margin.error.ApiException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class AbstractGenderService {
    protected String requestTheUrl(String url) {
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connect = (HttpURLConnection) apiUrl.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connect.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            Gson gson = new Gson();
            JsonObject jsonOb = gson.fromJson(reader, JsonObject.class);
            connect.disconnect();
            String gender = jsonOb.get("gender").getAsString();

            return gender;
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }
    }

    protected String requestTheUrl(String url, String apiKey, String apiUser, String acceptType) {
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connect = (HttpURLConnection) apiUrl.openConnection();
            connect.setRequestProperty("X-Channel-Secret", apiKey);
            connect.setRequestProperty("X-Channel-User", apiUser);
            connect.setRequestProperty("Accept", acceptType);
            InputStreamReader inputStreamReader = new InputStreamReader(connect.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            Gson gson = new Gson();
            JsonObject jsonOb = gson.fromJson(reader, JsonObject.class);
            connect.disconnect();
            String gender = jsonOb.get("gender").getAsString();

            return gender;
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }
    }
}
