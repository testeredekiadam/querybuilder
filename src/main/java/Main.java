import gui.GuiStarter;
import service.Csv;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {

       Csv a = new Csv("C:\\Users\\ERKO005\\db-excel\\printinggroup.csv", "printinggroup.csv");
       a.CsvToString();


        GuiStarter.main(args);

    }

}
