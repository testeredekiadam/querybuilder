package service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Csv {
    private final String inputFile;
    private final String outputFile;
    private ArrayList<ArrayList<String>> queryArray = new ArrayList<>();

    public Csv(String input, String output){
        this.inputFile = input;
        this.outputFile = output;
    }

    public void CsvToString () throws IOException {
        BufferedWriter writ = new BufferedWriter(new FileWriter("scout"));

        try (CSVReader reader = new CSVReader(new FileReader(this.inputFile));
             CSVWriter writer = new CSVWriter(new FileWriter(this.outputFile))) {


            ArrayList<String> partQueryArray = new ArrayList<>();

            List<String[]> filteredLines = new ArrayList<>();
            String[] li = new String[1000];
            StringBuilder scoutput = new StringBuilder();
            String[] nextLine;
            int i = 1;
            while ((nextLine = reader.readNext()) != null) {

                if(i%10==0){
                    this.queryArray.add(partQueryArray);
                    partQueryArray = new ArrayList<>();
                }

                partQueryArray.add(nextLine[0].toLowerCase(Locale.ROOT));

                scoutput.append("'");
                scoutput.append(nextLine[0].toLowerCase(Locale.ROOT));
                scoutput.append("', ");


                List<String> filteredValues = new ArrayList<>();

                for (String value : nextLine) {
                    if (!value.trim().isEmpty()) {
                        filteredValues.add(value);
                    }
                }

                filteredLines.add(filteredValues.toArray(new String[0]));
                i++;
            }


            this.queryArray.add(partQueryArray);
            System.out.println(i);
            System.out.println(this.queryArray);
            writ.write(scoutput.toString());
            writer.writeAll(filteredLines);
            writer.flush();
            writ.close();

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }


    public String getInputFile() {
        return inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public ArrayList<ArrayList<String>> getQueryArray() {
        return queryArray;
    }

    public void setQueryArray(ArrayList<ArrayList<String>> queryArray) {
        this.queryArray = queryArray;
    }
}
