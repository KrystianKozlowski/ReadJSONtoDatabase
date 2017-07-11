package com.myschoolbook.pojo;

import java.util.Map;

/**
 * Created by Krystian on 2017-06-12.
 */
public class MostFreequentCrimes {
    private int year;
    private int localization;
    private Map<String, Integer> freequent_crimes;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLocalization() {
        return localization;
    }

    public void setLocalization(int localization) {
        this.localization = localization;
    }

    public Map<String, Integer> getFreequent_crimes() {
        return freequent_crimes;
    }

    public void setFreequent_crimes(Map<String, Integer> freequent_crimes) {
        this.freequent_crimes = freequent_crimes;
    }
}
