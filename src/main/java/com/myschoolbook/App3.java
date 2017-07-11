package com.myschoolbook;

import com.google.gson.Gson;
import com.myschoolbook.chart.ChartService;
import com.myschoolbook.model.FreequentCrime;
import com.myschoolbook.pojo.CrimeAtLocation;
import com.myschoolbook.service.CrimeService;
import com.myschoolbook.service.HibernateUtility;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Krystian on 2017-06-21.
 */
public class App3 {
    public static void main(String[] args) {

        FreequentCrime freequentCrime = new FreequentCrime();
        freequentCrime.setYear(2015);
        freequentCrime.setLocalizationId(884226);


        Calendar cal = Calendar.getInstance();
        cal.set(freequentCrime.getYear(),1,0);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
        String formatted;

        CrimeService crimeService = new CrimeService();
        crimeService.clearTable();

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = null;
        HttpResponse response= null;

        String json_string;
        JSONArray jsonArray;
        Gson gson = new Gson();
        CrimeAtLocation crime;

        for (int i = 0; i < 12; i++) {
            cal.add(Calendar.MONTH, 1);
            formatted = formatDate.format(cal.getTime());

            try {
                httpget = new HttpGet("https://data.police.uk/api/crimes-at-location?date=" + formatted + "&location_id=" + String.valueOf(freequentCrime.getLocalizationId()));
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
                    crime = gson.fromJson(json.toString(), CrimeAtLocation.class);
                    crimeService.addCrime(crime);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        ChartService chartService = new ChartService();
        List<FreequentCrime> fcList = crimeService.listOfCrime(freequentCrime.getYear(), freequentCrime.getLocalizationId());

        //cs.clearTable();
        try {
            chartService.createCrimesChart(fcList, freequentCrime.getYear(), freequentCrime.getLocalizationId());
        } catch (Exception ex){
            ex.printStackTrace();
        }
        HibernateUtility.getSessionFactory().close();

    }

}
