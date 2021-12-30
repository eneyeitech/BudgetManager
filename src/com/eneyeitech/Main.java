package com.eneyeitech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<String> items = inputItemList();
        printItemList(items);
        System.out.printf("\nTotal: $%.2f", calculateTotal(items));
    }

    public static ArrayList<String> inputItemList(){
        ArrayList<String> items = new ArrayList<>();
        while(scanner.hasNextLine()){
            items.add(scanner.nextLine());
        }
        return items;
    }

    public static void printItemList(ArrayList<String> items){
        for (String item : items){
            System.out.println(item);
        }
    }

    public static float calculateTotal(ArrayList<String> items){
        float total = 0.00f;
        for (String item : items){
            if (item.contains("$")){
                total += Float.parseFloat(item.substring(item.lastIndexOf("$") + 1));
            }
        }
        return total;
    }
}

class Item {
    private String name;
    private float price;
    private final String symbol = "$";

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getName());
        builder.append(" ");
        builder.append(getSymbol());
        builder.append(getPrice());

        return builder.toString();
    }
}
