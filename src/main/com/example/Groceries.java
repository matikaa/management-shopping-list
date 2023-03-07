package main.com.example;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Groceries {
    ArrayList<String> groceries;

    public Groceries() {
        groceries = new ArrayList<>();
    }

    public void manageFile() {
        try {
            File Object = new File("DataBase.txt");
            Scanner Reader = new Scanner(Object);
            String data = "";
            while (Reader.hasNextLine()) {

                if (data.equals("groceries")) {
                    while (Reader.hasNextLine()) {
                        data = Reader.nextLine();

                        if (data.equals("chemistry") || data.equals("groceries") || data.equals("automotive"))
                            break;
                        addGrocery(data);
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

    public void addGrocery(String product) {
        groceries.add(product);
    }

    public void showGroceries() {
        System.out.println("GROCERY PRODUCTS:");
        for (int i = 0; i < groceries.size(); ++i)
            System.out.println("- " + groceries.get(i) + " (" + i + ")");
    }
}
