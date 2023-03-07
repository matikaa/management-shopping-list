package main.com.example;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Automotive {
    ArrayList<String> automotive;

    public Automotive() {
        automotive = new ArrayList<>();
    }

    public void manageFile() {
        try {
            File Object = new File("DataBase.txt");
            Scanner Reader = new Scanner(Object);
            String data = "";
            while (Reader.hasNextLine()) {

                if (data.equals("automotive")) {
                    while (Reader.hasNextLine()) {
                        data = Reader.nextLine();

                        if (data.equals("chemistry") || data.equals("groceries") || data.equals("automotive"))
                            break;

                        addAutomotive(data);
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

    public void addAutomotive(String product) {
        automotive.add(product);
    }

    public void showAutomotive() {
        System.out.println("AUTOMOTIVE PRODUCTS:");
        for (int i = 0; i < automotive.size(); ++i)
            System.out.println("- " + automotive.get(i) + " (" + i + ")");
    }
}
