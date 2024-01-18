package service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;

import java.util.ArrayList;
import java.util.Locale;

public class Csv {
    private final String inputFile;

    private final ArrayList<ArrayList<String>> queryArray = new ArrayList<>();

    public Csv(String input){
        this.inputFile = input;
    }

    public void CsvToString (){

        try (CSVReader reader = new CSVReader(new FileReader(this.inputFile))) {


            ArrayList<String> partQueryArray = new ArrayList<>();

            StringBuilder scoutput = new StringBuilder();
            String[] nextLine;
            int i = 1;
            while ((nextLine = reader.readNext()) != null) {

                if(i%900==0){
                    this.queryArray.add(partQueryArray);
                    partQueryArray = new ArrayList<>();
                }


                scoutput.append("'");
                scoutput.append(nextLine[0].toLowerCase(Locale.ROOT));
                scoutput.append("' ");
                partQueryArray.add(scoutput.toString());
                scoutput = new StringBuilder();


                i++;
            }


            this.queryArray.add(partQueryArray);
            System.out.println(i);
            System.out.println(this.queryArray);

        } catch (IOException | CsvValidationException e) {
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<ArrayList<String>> getQueryArray() {
        return queryArray;
    }

}
