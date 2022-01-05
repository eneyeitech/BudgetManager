package com.eneyeitech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


enum PurchaseCategory {
    FOOD, CLOTHES, ENTERTAINMENT, OTHER
}

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static boolean exit;
    static BudgetManager budgetManager = new BudgetManager();

    public static void main(String[] args) {
        while (!exit) {
            menu();
            chooseAction();
        }
    }

    private static void menu() {
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "0) Exit");
    }

    private static void chooseAction() {
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                addIncome();
                break;
            case 2:
                addPurchase();
                break;
            case 3:
                getPurchases();
                break;
            case 4:
                budgetManager.showBalance();
                break;
            case 0:
                exit();
        }
    }

    private static void addIncome() {
        double income = 0.00;
        System.out.println("\nEnter income:");
        if (scanner.hasNextDouble() || scanner.hasNextInt()) {
            income = Double.parseDouble(scanner.nextLine());
            budgetManager.addToBalance(income);
        }
    }

    private static void addPurchase() {
        while (true) {
            String type = "";
            String item = "";
            String price = "";
            System.out.println("\nChoose the type of purchase\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) Back");

            type = scanner.nextLine();
            System.out.println();
            if (Integer.parseInt(type) >= 5 ){
                System.out.println();
                break;
            }
            System.out.println("Enter purchase name:");
            item = scanner.nextLine();
            System.out.println("Enter its price:");
            price = scanner.nextLine();

            try {
                double dprice = Double.parseDouble(price);
                int itype = Integer.parseInt(type);

                budgetManager.addItem(item, dprice, itype);
            } catch (Exception e) {
                System.out.println();
                break;
            }
        }
    }

    private static void getPurchases() {
        while (true) {
            System.out.println("\nChoose the type of purchases\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) All\n" +
                    "6) Back");
            int type = Integer.parseInt(scanner.nextLine());
            if (type == 6 || type < 1 || type > 6) {
                System.out.println();
                return;
            } else {
                System.out.println();
                switch (type) {
                    case 1:
                        budgetManager.showPurchaseList("Food");
                        break;
                    case 2:
                        budgetManager.showPurchaseList("Clothes");
                        break;
                    case 3:
                        budgetManager.showPurchaseList("Entertainment");
                        break;
                    case 4:
                        budgetManager.showPurchaseList("Other");
                        break;
                    case 5:
                        budgetManager.showPurchaseList();
                }
            }
        }
    }

    private static void exit() {
        System.out.print("\nBye!");
        exit = true;
    }
}

class Item {
    private String name;
    private double price;
    private PurchaseCategory category;

    public Item(String name, double price, int type) {
        this.name = name;
        this.price = price;

        switch (type) {
            case 1:
                this.category = PurchaseCategory.FOOD;
                break;
            case 2:
                this.category = PurchaseCategory.CLOTHES;
                break;
            case 3:
                this.category = PurchaseCategory.ENTERTAINMENT;
                break;
            case 4:
                this.category = PurchaseCategory.OTHER;
                break;
            default:
                break;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public PurchaseCategory getCategory() {
        return category;
    }
}

class BudgetManager {
    private ArrayList<Item> itemsList;
    private double balance;
    private double total;

    public BudgetManager() {
        this.itemsList = new ArrayList<>();
        this.balance = 0.00;
    }

    public void addToBalance(double income) {
        this.balance += income;
        System.out.println("Income was added!\n");
    }

    public void addItem(String item, double price, int type) {
        this.itemsList.add(new Item(item, price, type));
        this.balance = Math.max(this.balance - price, 0.0f);
        this.total += price;
        System.out.println("Purchase was added!");
    }

    public void showPurchaseList(String category) {
        double catTotal = 0.00;
        System.out.println();
        System.out.println(category + ":");
        if (this.itemsList.size() > 0) {
            ArrayList<Item> categoryList = new ArrayList<Item>();
            for (Item item : itemsList) {
                if (item.getCategory() == PurchaseCategory.valueOf(category.toUpperCase()))
                    categoryList.add(item);
            }
            if (categoryList.size() == 0) {
                System.out.println("Purchase list is empty\n");
            } else {
                for (Item item : categoryList) {
                    System.out.printf("%s $%.2f\n", item.getName(), item.getPrice());
                    catTotal += item.getPrice();
                }
                System.out.printf("Total sum: $%.2f\n", catTotal);
            }
        } else {
            System.out.println("Purchase list is empty");
        }
    }

    public void showPurchaseList() {
        System.out.println();
        System.out.println("All:");
        if (this.itemsList.size() > 0) {
            for (Item item : itemsList) {
                System.out.printf("%s (%s) $%.2f\n", item.getName(), item.getCategory().toString(), item.getPrice());
            }
            System.out.printf("Total sum: $%.2f\n", this.total);
        } else {
            System.out.println("Purchase list is empty\n");
        }
    }

    public void showBalance() {
        System.out.printf("\nBalance: $%.2f\n\n", this.balance);
    }
}
