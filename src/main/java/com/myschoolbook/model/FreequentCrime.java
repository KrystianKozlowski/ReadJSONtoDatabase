package com.myschoolbook.model;

/**
 * Created by Krystian on 2017-06-19.
 */
public class FreequentCrime {
    private int year;
    private int localizationId;
    private int numberOfCrimes;
    private String category;


    public FreequentCrime() {
    }

    public FreequentCrime(int year, int localizationId, int numberOfCrimes, String category) {
        this.year = year;
        this.localizationId = localizationId;
        this.numberOfCrimes = numberOfCrimes;
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(int localizationId) {
        this.localizationId = localizationId;
    }

    public int getNumberOfCrimes() {
        return numberOfCrimes;
    }

    public void setNumberOfCrimes(int numberOfCrimes) {
        this.numberOfCrimes = numberOfCrimes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
