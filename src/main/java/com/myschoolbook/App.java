package com.myschoolbook;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.myschoolbook.pojo.CrimeAtLocation;
import com.myschoolbook.service.CrimeService;
import com.myschoolbook.service.HibernateUtility;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(2014,1,0);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        String formatted;

        CrimeService crimeService = new CrimeService();
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = null;
        HttpResponse response= null;

        String json_string;
        JSONArray jsonArray;
        Gson gson = new Gson();
        CrimeAtLocation crime;

            for (int i = 0; i < 36; i++) {
                cal.add(Calendar.MONTH, 1);
                formatted = formatDate.format(cal.getTime());

                try {
                    httpget = new HttpGet("https://data.police.uk/api/crimes-at-location?date=" + formatted + "&location_id=884227");
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }

                try {
                    response = httpclient.execute(httpget);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    json_string = EntityUtils.toString(response.getEntity());
                    System.out.println(json_string);
                    jsonArray = new JSONArray(json_string);
                    System.out.println(jsonArray.length());
                    for (Object json : jsonArray) {
                        System.out.println(json.toString());
                        crime = gson.fromJson(json.toString(), CrimeAtLocation.class);
                        crimeService.addCrime(crime);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        HibernateUtility.getSessionFactory().close();
    }
}



