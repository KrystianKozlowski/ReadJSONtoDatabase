package com.myschoolbook.chart;

import com.myschoolbook.model.FreequentCrime;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.util.List;

/**
 * Created by Krystian on 2017-06-21.
 */
public class ChartService  {
    public JFreeChart createCrimesChart(List<FreequentCrime> fcList, int year, int localizationId) throws Exception {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        String title = "Most Freequent Crimes in year " + String.valueOf(year) + " at localization nr " + String.valueOf(localizationId);

        for (FreequentCrime freequentCrime : fcList){
            dataset.setValue(freequentCrime.getCategory(), freequentCrime.getNumberOfCrimes());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                title,            // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;


        //int width = 640;   /* Width of the image */
        //int height = 480;  /* Height of the image */
        //File pieChart = new File( "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\jsonreader\\resources\\charts\\PieChart.jpeg");
        //ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
    }
}
