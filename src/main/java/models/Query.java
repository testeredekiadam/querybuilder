package models;

import java.util.ArrayList;

public class Query {
    private StringBuilder base; // Select, From
    private StringBuilder subBase; // joins
    private StringBuilder filter;
    private ArrayList<ArrayList<String>> queryArray = new ArrayList<>(); // that comes from csv
    private StringBuilder footer;



}
