package main.com.example;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import main.com.example.Category;

public class ListOfCategories {

    private Groceries groceries;
    private Chemistry chemistry;
    private Automotive automotive;
    private HashMap<Category, ArrayList<String>> ListOfShopping;
    private Scanner scanner;
    private int user_choice;
    private String data;

    public ListOfCategories() {
        groceries = new Groceries();
        chemistry = new Chemistry();
        automotive = new Automotive();
        ListOfShopping = new HashMap<>();
        scanner = new Scanner(System.in);
        data = "";

        loadShoppingList();
        loadFiles();
    }

    public void loadFiles() {
        groceries.manageFile();
        chemistry.manageFile();
        automotive.manageFile();
    }

    public void menu() {
        showMenu();
        int choice = validate(7);

        if (choice == 7) {
            System.out.println("You finished the program.");
            return;
        }

        manageChoices(choice);
    }

    public void manageChoices(int choice) {
        if (choice == 0)
            addProduct();
        else if (choice == 1)
            showAllProducts();
        else if (choice == 2)
            showProductsOfCategory();
        else if (choice == 3)
            removeAllProducts();
        else if (choice == 4)
            removeProductsOfCategory();
        else if (choice == 5)
            removeOneProductOfCategory();
        else
            saveListToFile();

        menu();
    }

    public void addProduct() {
        showCategories();
        user_choice = validate(2);

        if (user_choice == 0)
            addGroceryToHashMap();
        else if (user_choice == 1)
            addChemistryToHashMap();
        else
            addAutomotiveToHashMap();
    }

    public void addGroceryToHashMap() {
        int product = 0, check = 0;

        groceries.showGroceries();
        while (true) {
            product = validate(groceries.groceries.size());

            for (int i = 0; i < groceries.groceries.size(); ++i) {
                if (i == product) {
                    check = 1;
                    break;
                }
            }
            if (check == 1) {
                addToHashMap(Category.GROCERIES, groceries.groceries.get(product));
                break;
            }
        }
    }

    public void addChemistryToHashMap() {
        int product = 0, check = 0;

        chemistry.showChemistry();
        while (true) {
            product = validate(chemistry.chemistry.size());
            for (int i = 0; i < chemistry.chemistry.size(); ++i) {
                if (i == product) {
                    check = 1;
                    break;
                }
            }
            if (check == 1) {
                addToHashMap(Category.CHEMISTRY, chemistry.chemistry.get(product));
                break;
            }
        }
    }

    public void addAutomotiveToHashMap() {
        int product = 0, check = 0;

        automotive.showAutomotive();
        while (true) {
            product = validate(automotive.automotive.size());

            for (int i = 0; i < automotive.automotive.size(); ++i) {
                if (i == product) {
                    check = 1;
                    break;
                }
            }
            if (check == 1) {
                addToHashMap(Category.AUTOMOTIVE, automotive.automotive.get(product));
                break;
            }
        }
    }

    public void showAllProducts() {
        if (ListOfShopping.size() != 0) {

            System.out.println("List of shopping:");

            if (ListOfShopping.containsKey(Category.GROCERIES)) {
                System.out.println("    GROCERIES");

                for (int i = 0; i < ListOfShopping.get(Category.GROCERIES).size(); ++i)
                    System.out.println("        - " + ListOfShopping.get(Category.GROCERIES).get(i));
            }
            if (ListOfShopping.containsKey(Category.CHEMISTRY)) {
                System.out.println("    CHEMISTRY");

                for (int i = 0; i < ListOfShopping.get(Category.CHEMISTRY).size(); ++i)
                    System.out.println("        - " + ListOfShopping.get(Category.CHEMISTRY).get(i));
            }
            if (ListOfShopping.containsKey(Category.AUTOMOTIVE)) {
                System.out.println("    AUTOMOTIVE");

                for (int i = 0; i < ListOfShopping.get(Category.AUTOMOTIVE).size(); ++i)
                    System.out.println("        - " + ListOfShopping.get(Category.AUTOMOTIVE).get(i));
            }
        } else
            System.out.println("List of shopping is empty.");
    }

    public void showProductsOfCategory() {
        showCategories();
        user_choice = validate(2);

        if (user_choice == 0 && ListOfShopping.containsKey(Category.GROCERIES)) {
            System.out.println("List of shopping GROCERIES:");

            for (int i = 0; i < ListOfShopping.get(Category.GROCERIES).size(); ++i)
                System.out.println("    - " + ListOfShopping.get(Category.GROCERIES).get(i));
        } else if (user_choice == 1 && ListOfShopping.containsKey(Category.CHEMISTRY)) {
            System.out.println("List of shopping CHEMISTRY:");

            for (int i = 0; i < ListOfShopping.get(Category.CHEMISTRY).size(); ++i)
                System.out.println("    - " + ListOfShopping.get(Category.CHEMISTRY).get(i));
        } else if (ListOfShopping.containsKey(Category.AUTOMOTIVE)) {
            System.out.println("List of shopping AUTOMOTIVE:");

            for (int i = 0; i < ListOfShopping.get(Category.AUTOMOTIVE).size(); ++i)
                System.out.println("    - " + ListOfShopping.get(Category.AUTOMOTIVE).get(i));
        } else
            System.out.println("Lack of products.");
    }

    public void removeAllProducts() {
        ListOfShopping.clear();
        System.out.println("List has been deleted.");
    }

    public void removeProductsOfCategory() {
        showCategories();
        user_choice = validate(2);

        if (user_choice == 0)
            ListOfShopping.remove(Category.GROCERIES);
        else if (user_choice == 1)
            ListOfShopping.remove(Category.CHEMISTRY);
        else
            ListOfShopping.remove(Category.AUTOMOTIVE);
    }

    public void removeOneProductOfCategory() {
        showCategories();
        user_choice = validate(2);

        Category category;
        if (user_choice == 0)
            category = Category.GROCERIES;
        else if (user_choice == 1)
            category = Category.CHEMISTRY;
        else
            category = Category.AUTOMOTIVE;

        if (ListOfShopping.get(category).size() == 0)
            System.out.println("Lack of products");
        else {
            System.out.println("Choose product:");
            for (int i = 0; i < ListOfShopping.get(category).size(); ++i)
                System.out.println("- " + ListOfShopping.get(category).get(i) + " (" + i + ")");

            user_choice = validate(ListOfShopping.get(category).size() + 1);

            ListOfShopping.get(category).remove(user_choice);
        }
    }

    public void saveListToFile() {
        try {
            FileWriter file = new FileWriter("ShoppingList.txt");
            BufferedWriter bufferedFile = new BufferedWriter(file);
            PrintWriter save = new PrintWriter(bufferedFile);

            if (ListOfShopping.containsKey(Category.GROCERIES)) {
                save.println("groceries");
                for (int i = 0; i < ListOfShopping.get(Category.GROCERIES).size(); ++i)
                    save.println(ListOfShopping.get(Category.GROCERIES).get(i));
            }
            if (ListOfShopping.containsKey(Category.CHEMISTRY)) {
                save.println("chemistry");
                for (int i = 0; i < ListOfShopping.get(Category.CHEMISTRY).size(); ++i)
                    save.println(ListOfShopping.get(Category.CHEMISTRY).get(i));
            }
            if (ListOfShopping.containsKey(Category.AUTOMOTIVE)) {
                save.println("automotive");
                for (int i = 0; i < ListOfShopping.get(Category.AUTOMOTIVE).size(); ++i)
                    save.println(ListOfShopping.get(Category.AUTOMOTIVE).get(i));
            }

            save.close();
            bufferedFile.close();
            file.close();
            System.out.println("Data has been saved in file.");

        } catch (IOException e) {
            System.out.println("Failed to open file." + e.getMessage());
        }
    }

    public void addToHashMap(Category Key, String product) {
        if (Key == Category.GROCERIES)
            addToHashMapGroceries(product);
        else if (Key == Category.CHEMISTRY)
            addToHashMapChemistry(product);
        else
            addToHashMapAutomotive(product);
    }

    public void addToHashMapGroceries(String product) {
        ArrayList<String> array = new ArrayList<>();
        array.add(product);

        if (!ListOfShopping.containsKey(Category.GROCERIES))
            ListOfShopping.put(Category.GROCERIES, array);
        else {
            array.addAll(ListOfShopping.get(Category.GROCERIES));
            ListOfShopping.put(Category.GROCERIES, array);
        }
    }

    public void addToHashMapChemistry(String product) {
        ArrayList<String> array = new ArrayList<>();
        array.add(product);

        if (!ListOfShopping.containsKey(Category.CHEMISTRY))
            ListOfShopping.put(Category.CHEMISTRY, array);
        else {
            array.addAll(ListOfShopping.get(Category.CHEMISTRY));
            ListOfShopping.put(Category.CHEMISTRY, array);
        }
    }

    public void addToHashMapAutomotive(String product) {
        ArrayList<String> array = new ArrayList<>();
        array.add(product);

        if (!ListOfShopping.containsKey(Category.AUTOMOTIVE))
            ListOfShopping.put(Category.AUTOMOTIVE, array);
        else {
            array.addAll(ListOfShopping.get(Category.AUTOMOTIVE));
            ListOfShopping.put(Category.AUTOMOTIVE, array);
        }
    }

    public void loadShoppingList() {
        try {
            File Object = new File("ShoppingList.txt");
            Scanner Reader = new Scanner(Object);
            while (Reader.hasNextLine()) {
                if (data.equals("groceries")) {
                    while (Reader.hasNextLine()) {
                        data = Reader.nextLine();
                        if (validateListFromFile(Category.GROCERIES) == 1)
                            break;
                    }
                    continue;
                } else if (data.equals("chemistry")) {
                    while (Reader.hasNextLine()) {
                        data = Reader.nextLine();
                        if (validateListFromFile(Category.CHEMISTRY) == 1)
                            break;
                    }
                    continue;
                } else if (data.equals("automotive")) {
                    while (Reader.hasNextLine()) {
                        data = Reader.nextLine();
                        if (validateListFromFile(Category.AUTOMOTIVE) == 1)
                            break;
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

    public int validateListFromFile(Category key) {
        if (data.equals("chemistry") || data.equals("groceries") || data.equals("automotive"))
            return 1;

        addToHashMap(key, data);
        return 0;
    }

    public void showMenu() {
        System.out.println("What do you want to do?");
        System.out.println("- add product (0)");
        System.out.println("- show products of all categories (1)");
        System.out.println("- show products of one category (2)");
        System.out.println("- delete all products of all categories (3)");
        System.out.println("- delete all products of one category (4)");
        System.out.println("- delete one product of one category (5)");
        System.out.println("- save list of products on disk (6)");
        System.out.println("- finish program (7)");
    }

    public int validate(int limit) {
        int choice = 0;
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Incorrect input. Try again:");
                scanner.next();
            } else {
                choice = scanner.nextInt();
                if (choice < 0 || choice > limit) {
                    System.out.println("Wrong number. Try again:");
                    continue;
                }
                break;
            }
        }

        return choice;
    }

    public void showCategories() {
        System.out.println("Choose category:");
        System.out.println("- groceries (0)");
        System.out.println("- chemistry (1)");
        System.out.println("- automotive (2)");
    }
}
