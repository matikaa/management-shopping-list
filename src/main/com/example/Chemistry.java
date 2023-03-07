package main.com.example;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Chemistry {
    ArrayList<String> chemistry;

    public Chemistry() {
        chemistry = new ArrayList<>();
    }

    public void manageFile() {
        try {
            File Object = new File("DataBase.txt");
            Scanner Reader = new Scanner(Object);
            String data = "";
            while (Reader.hasNextLine()) {

                if (data.equals("chemistry")) {
                    while (Reader.hasNextLine()) {
                        data = Reader.nextLine();

                        if (data.equals("chemistry") || data.equals("groceries") || data.equals("automotive"))
                            break;

                        addChemistry(data);
                    }
                    continue;
                }
                data = Reader.nextLine();

            }
            Reader.close();
        } catch (FileNotFoundException f) {
            System.out.println("Error");
        }
    }

    public void addChemistry(String product) {
        chemistry.add(product);
    }

    public void showChemistry() {
        System.out.println("CHEMISTRY PRODUCTS:");
        for (int i = 0; i < chemistry.size(); ++i)
            System.out.println("- " + chemistry.get(i) + " (" + i + ")");
    }
}
