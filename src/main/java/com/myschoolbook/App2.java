package com.myschoolbook;

import com.myschoolbook.chart.ChartService;
import com.myschoolbook.model.FreequentCrime;
import com.myschoolbook.service.CrimeService;
import com.myschoolbook.service.HibernateUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystian on 2017-06-20.
 */
public class App2 {
    public static void main(String[] args) {
        CrimeService cs = new CrimeService();
        ChartService chartService = new ChartService();
        List<FreequentCrime> fcList = cs.listOfCrime(2017, 901948294);

        //cs.clearTable();
        for(FreequentCrime fc : fcList){
            System.out.println(fc.getCategory() + " : " + String.valueOf(fc.getNumberOfCrimes()));
        }
        HibernateUtility.getSessionFactory().close();
        try {
            chartService.createCrimesChart(fcList, 2017, 901948294);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
