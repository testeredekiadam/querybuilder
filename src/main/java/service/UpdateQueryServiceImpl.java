package service;

import controller.SearchQueryController;
import controller.UpdateCompanyUserController;
import javafx.scene.control.TextArea;
import models.Join;
import models.Query;

import java.util.ArrayList;

public class UpdateQueryServiceImpl implements QueryServiceInterface{
    @Override
    public void displayComponent(TextArea query, ArrayList<Query> queryList) {}

    @Override
    public void displayComponent(TextArea query, Query query2) {

    }

    @Override
    public void selectComponent(Query query, String columns) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ");
        if(!columns.isEmpty()) {
            stringBuilder.append(columns);
        }
        else {
            stringBuilder.append("--ENTER TABLE NAME--");
        }
        stringBuilder.append("\n");
        UpdateCompanyUserController.query.setSelect(stringBuilder);
    }

    @Override
    public void fromComponent(Query query, String table) {

    }

    @Override
    public void whereComponent(Query query, String choice, String column, String filter) {

    }

    @Override
    public void searchInCsv(Query query, String columnFilter, ArrayList<ArrayList<String>> queryArray) {

    }

    @Override
    public void joinComponent(Query query, ArrayList<Join> joinList) {

    }
}
