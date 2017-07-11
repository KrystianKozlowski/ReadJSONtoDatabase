package com.myschoolbook.controller;

/**
 * Created by Krystian on 2017-06-12.
 */
import com.google.gson.Gson;
import com.myschoolbook.chart.ChartService;
import com.myschoolbook.model.FreequentCrime;
import com.myschoolbook.pojo.CrimeAtLocation;
import com.myschoolbook.pojo.MostFreequentCrimes;
import com.myschoolbook.service.CrimeService;
import com.myschoolbook.service.HibernateUtility;
import javafx.scene.chart.PieChart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class CrimesController {

    protected final Log logger = LogFactory.getLog(getClass());


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView printWelcome(ModelMap model) {

        String text = "Spring 3 MVC Hello World";

        logger.info("Returning main site");
        model.addAttribute("freequentCrime", new FreequentCrime());

        return new ModelAndView("main", "text", text);
    }

    @RequestMapping(value="chart", params = "chart", method = RequestMethod.POST)
    public void showChart(@ModelAttribute("freequentCrime") FreequentCrime freequentCrime, ModelMap model, HttpServletResponse servletResponse) {
        model.addAttribute("year", freequentCrime.getYear());
        model.addAttribute("localizationId", freequentCrime.getLocalizationId());

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
                try {
                    jsonArray = new JSONArray(json_string);
                    System.out.println(jsonArray.length());
                    for (Object json : jsonArray) {
                        System.out.println(json.toString());
                        crime = gson.fromJson(json.toString(), CrimeAtLocation.class);
                        crimeService.addCrime(crime);
                    }
                }catch(Exception e){
                        crime = gson.fromJson(json_string, CrimeAtLocation.class);
                        crimeService.addCrime(crime);
                }
            } catch (IOException e) {

                e.printStackTrace();


            }

        }

        ChartService chartService = new ChartService();
        List<FreequentCrime> fcList = crimeService.listOfCrime(freequentCrime.getYear(), freequentCrime.getLocalizationId());

        try {
            JFreeChart chart = chartService.createCrimesChart(fcList, freequentCrime.getYear(), freequentCrime.getLocalizationId());
            servletResponse.setContentType("image/png");
            int width = 640;   /* Width of the image */
            int height = 480;  /* Height of the image */
            ChartUtilities.writeChartAsPNG(servletResponse.getOutputStream(), chart, width, height);
            servletResponse.getOutputStream().close();
        } catch (Exception ex){
            ex.printStackTrace();
        }





    }


    @RequestMapping(value = "/crimes", method = RequestMethod.POST)
    public String crimesPage(@ModelAttribute("freequentCrime") FreequentCrime freequentCrime, ModelMap model) {


        model.addAttribute("year", freequentCrime.getYear() );
        model.addAttribute("localizationId", freequentCrime.getLocalizationId() );

        logger.info("Returning main site");

        return "crimes";
    }


/*    @RequestMapping(value = "/getjson", params = "getjson", method = RequestMethod.POST)
    public String returnJSON(HttpServletRequest request) {

        return "json";
    }

    @RequestMapping(value = "/getchart", params = "getchart", method = RequestMethod.POST)
    public String returnChart(HttpServletRequest request) {

        return "chart";
    }
 */


}
