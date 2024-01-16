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

    public Csv(String input, String output){
        this.inputFile = input;
        this.outputFile = output;
    }

    public void CsvToString () throws IOException {
        BufferedWriter writ = new BufferedWriter(new FileWriter("scout"));

        try (CSVReader reader = new CSVReader(new FileReader(this.inputFile));
             CSVWriter writer = new CSVWriter(new FileWriter(this.outputFile))) {

            List<String[]> filteredLines = new ArrayList<>();
            String[] li = new String[1000];
            StringBuilder scoutput = new StringBuilder();
            String[] nextLine;
            int i = 1;
            while ((nextLine = reader.readNext()) != null) {

                if(i%900==0){
                    scoutput.append("\n\n\n'");
                    scoutput.append(i);
                    scoutput.append("'\n\n\n");
                }

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

            System.out.println(scoutput);
            System.out.println(i);
            writ.write(scoutput.toString());
            writer.writeAll(filteredLines);
            writer.flush();
            writ.close();

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

}
