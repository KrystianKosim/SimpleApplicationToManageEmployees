package com.company;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Data {
    private static final String file = "data.txt";
    private static boolean isEmptyTab = true;

    public static void saveDataInFile(Object[][] data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < data.length; i++) {
                bufferedWriter.write(data[i][0] + ",");
                bufferedWriter.write(data[i][1] + ",");
                bufferedWriter.write(data[i][2].toString() + ",");
                bufferedWriter.write(data[i][3] + ",");
                bufferedWriter.write(data[i][4].toString() + ",");

                bufferedWriter.write(data[i][5].toString() + ",");
                bufferedWriter.write(data[i][6].toString() + ",");
                bufferedWriter.write(data[i][7].toString() + ",");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object[][] readDataFromFile(int secondLenghtOfTab) {
        Object[][] tab;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            tab = new Object[0][0];
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> values = new LinkedList<>();
                String valueToAdd = "";
                for (int i = 0; i < line.length(); i++) {
                    char checkingChar = line.charAt(i);
                    if (checkingChar != ',') {
                        valueToAdd += checkingChar;
                    } else if (i == line.length() - 1) {
                        values.add(valueToAdd);
                    } else {
                        values.add(valueToAdd);
                        valueToAdd = "";
                    }
                }
                if (values.size() == secondLenghtOfTab) {
                    tab = addDataToTab(tab, values, secondLenghtOfTab);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("EX");
            return null;
        }
        return tab;
    }

    private static Object[][] addDataToTab(Object[][] tab, List<String> values, int secondLengthOfTab) {
        int lastIndex;
        if (isEmptyTab) {
            tab = new Object[1][secondLengthOfTab];
            lastIndex = 0;
            isEmptyTab = false;
        } else {
            int firstLength = tab.length + 1;
            Object[][] replacementTab = new Object[firstLength][secondLengthOfTab];
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab[i].length; j++) {
                    replacementTab[i][j] = tab[i][j];
                }
            }
            lastIndex = firstLength - 1;
            tab = replacementTab;
        }
        tab[lastIndex][0] = values.get(0);
        tab[lastIndex][1] = values.get(1);
        tab[lastIndex][2] = Integer.valueOf(values.get(2));
        tab[lastIndex][3] = values.get(3);
        tab[lastIndex][4] = Integer.valueOf(values.get(4));
        tab[lastIndex][5] = Integer.valueOf(values.get(5));
        tab[lastIndex][6] = Integer.valueOf(values.get(6));
        tab[lastIndex][7] = Integer.valueOf(values.get(7));
        return tab;
    }
}
