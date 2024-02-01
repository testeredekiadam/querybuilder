package service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;

import java.util.ArrayList;
import java.util.Locale;

public class Csv {

    public static ArrayList<ArrayList<String>> CsvToString (String inputFile){

        ArrayList<ArrayList<String>> queryArray = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(inputFile))) {

            ArrayList<String> partQueryArray = new ArrayList<>();

            StringBuilder scoutput = new StringBuilder();
            String[] nextLine;
            int i = 1;
            while ((nextLine = reader.readNext()) != null) {

                if(i%900==0){
                    queryArray.add(partQueryArray);
                    partQueryArray = new ArrayList<>();
                }

                scoutput.append("'");
                scoutput.append(nextLine[0].toLowerCase(Locale.ROOT));
                scoutput.append("' ");
                partQueryArray.add(scoutput.toString());
                scoutput = new StringBuilder();

                i++;
            }

            queryArray.add(partQueryArray);

        } catch (IOException | CsvValidationException e) {
            System.out.println(e.getMessage());
        }

        return queryArray;
    }


}
