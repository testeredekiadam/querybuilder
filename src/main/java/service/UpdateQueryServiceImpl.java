package service;

import javafx.scene.control.TextArea;
import models.Join;
import models.Query;

import java.util.ArrayList;

public class UpdateQueryServiceImpl implements QueryServiceInterface{
    @Override
    public void displayComponent(TextArea query, ArrayList<Query> queryList) {

    }

    @Override
    public void selectComponent(Query query, String columns) {

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
