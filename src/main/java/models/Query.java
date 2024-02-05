package models;

import java.util.ArrayList;

public class Query {

    private String Id;
    private StringBuilder select;
    private StringBuilder from;
    private StringBuilder joinListString;

    private StringBuilder filter;
    private ArrayList<ArrayList<String>> csvArray;
    private StringBuilder csvArrayString;
    private StringBuilder footer;
    private boolean where;

    public Query(){
        select = new StringBuilder();
        from = new StringBuilder();
        joinListString = new StringBuilder(); // joins
        filter = new StringBuilder();
        csvArray = new ArrayList<>(); // that comes from csv
        csvArrayString = new StringBuilder();
        footer = new StringBuilder();
        where = false;
    }

    public StringBuilder getSelect() {
        return select;
    }

    public void setSelect(StringBuilder select) {
        this.select = select;
    }

    public StringBuilder getFrom() {
        return from;
    }

    public void setFrom(StringBuilder from) {
        this.from = from;
    }


    public StringBuilder getFilter() {
        return filter;
    }

    public void setFilter(StringBuilder filter) {
        this.filter = filter;
    }

    public StringBuilder getFooter() {
        return footer;
    }

    public void setFooter(StringBuilder footer) {
        this.footer = footer;
    }

    //csv array, csv string
    public ArrayList<ArrayList<String>> getCsvArray() {
        return csvArray;
    }

    public void setCsvArray(ArrayList<ArrayList<String>> csvArray) {
        this.csvArray = csvArray;
    }

    public StringBuilder getCsvArrayString() {
        return csvArrayString;
    }

    public void setCsvArrayString(StringBuilder csvArrayString) {
        this.csvArrayString = csvArrayString;
    }

    //join array, join string
    public StringBuilder getJoinListString() {
        return joinListString;
    }

    public void setJoinListString(StringBuilder joinListString) {
        this.joinListString = joinListString;
    }

    public boolean isWhere() {
        return where;
    }

    public void setWhere(boolean where) {
        this.where = where;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public StringBuilder display(){
        StringBuilder sb = new StringBuilder();
        sb.append(getSelect()).append(getFrom());
        if(!getJoinListString().isEmpty()){
            sb.append(getJoinListString());
        }
        if(!getFilter().isEmpty()){
            sb.append(getFilter());
        }
        if(!getCsvArray().isEmpty()){
            sb.append(getCsvArrayString());
        }
        if(!getFooter().isEmpty()){
            sb.append(getFooter());
        }

        return sb;
    }
}
