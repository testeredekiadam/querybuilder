package models;

import java.util.ArrayList;

public class Query {
    private StringBuilder select;
    private StringBuilder from;
    private StringBuilder subBase; // joins
    private StringBuilder filter;
    private ArrayList<ArrayList<String>> queryArray; // that comes from csv
    private StringBuilder queryArrayString;
    private StringBuilder footer;
    private boolean where;

    public Query(){
        select = new StringBuilder();
        from = new StringBuilder();
        subBase = new StringBuilder(); // joins
        filter = new StringBuilder();
        queryArray = new ArrayList<>(); // that comes from csv
        queryArrayString = new StringBuilder();
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

    public StringBuilder getSubBase() {
        return subBase;
    }

    public void setSubBase(StringBuilder subBase) {
        this.subBase = subBase;
    }

    public StringBuilder getFilter() {
        return filter;
    }

    public void setFilter(StringBuilder filter) {
        this.filter = filter;
    }

    public ArrayList<ArrayList<String>> getQueryArray() {
        return queryArray;
    }

    public void setQueryArray(ArrayList<ArrayList<String>> queryArray) {
        this.queryArray = queryArray;
    }

    public StringBuilder getFooter() {
        return footer;
    }

    public void setFooter(StringBuilder footer) {
        this.footer = footer;
    }

    public StringBuilder getQueryArrayString() {
        return queryArrayString;
    }

    public void setQueryArrayString(StringBuilder queryArrayString) {
        this.queryArrayString = queryArrayString;
    }

    public boolean isWhere() {
        return where;
    }

    public void setWhere(boolean where) {
        this.where = where;
    }

    public StringBuilder display(){
        StringBuilder sb = new StringBuilder();
        sb.append(getSelect()).append(getFrom());
        if(!getSubBase().isEmpty()){
            sb.append(getSubBase());
        }
        if(!getFilter().isEmpty()){
            sb.append(getFilter());
        }
        if(!getQueryArray().isEmpty()){
            sb.append(getQueryArrayString());
        }
        if(!getFooter().isEmpty()){
            sb.append(getFooter());
        }

        return sb;
    }
}
