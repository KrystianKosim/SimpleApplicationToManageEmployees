package com.company;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;

public class TableModel extends AbstractTableModel {

    private boolean isEmpty = true;

    private final String[] columnNames = new String[]{
            "Imie", "Nazwisko", "Wiek", "Stanowisko", "Rocznik", "Liczba godzin", "Stawka za godzine", "Wyplata"
    };

    Object[][] employe = Data.readDataFromFile(columnNames.length);

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    private int getAge(int yearOfBirth) {
        return LocalDateTime.now().getYear() - yearOfBirth;
    }

    @Override
    public int getRowCount() {
        return employe.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return employe[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0:
                return true;
            case 1:
                return true;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                return true;
            case 6:
                return true;
            case 7:
                return false;
            default:
                return false;
        }
    }

    public void removeEmployee(int row) {
        if (row >= 0) {
            int firstLength = employe.length - 1;
            int secondLength = employe[0].length;
            Object[][] tab = new Object[firstLength][secondLength];
            int index = 0;
            for (int i = 0; i < employe.length; i++) {
                if (i != row) {
                    for (int j = 0; j < employe[i].length; j++) {
                        tab[index][j] = employe[i][j];
                    }
                    index++;
                }
            }
            employe = tab;
            if (firstLength == 0) {
                isEmpty = true;
            }
            Data.saveDataInFile(employe);

            fireTableRowsDeleted(0, getRowCount());
        }
    }

    public void addToTable() {
        int firstLenght;
        int secondLength;
        Object[][] tab;
        if (isEmpty) {
            firstLenght = employe.length + 1;
            secondLength = columnNames.length;
            tab = new Object[firstLenght][secondLength];
            for (int i = 0; i < employe.length; i++) {
                for (int j = 0; j < employe[i].length; j++) {
                    tab[i][j] = employe[i][j];
                }
            }
        } else {
            firstLenght = 1;
            secondLength = columnNames.length;
            tab = new Object[firstLenght][secondLength];
            isEmpty = false;
        }
        tab[firstLenght - 1][0] = "Imie";
        tab[firstLenght - 1][1] = "Nazwisko";
        tab[firstLenght - 1][2] = 0;
        tab[firstLenght - 1][3] = "Stanowisko";
        tab[firstLenght - 1][4] = 0;
        tab[firstLenght - 1][5] = 0;
        tab[firstLenght - 1][6] = 0;
        tab[firstLenght - 1][7] = 0;
        employe = tab;
        Data.saveDataInFile(employe);
        fireTableRowsInserted(0, getRowCount());
    }


    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void setValueAt(Object value, int i, int j) {
        switch (j) {
            case 0:
                employe[i][j] = value;
                break;
            case 1:
                employe[i][j] = value;
                break;
            case 2:
                employe[i][j] = getAge((int) value);
                break;
            case 3:
                employe[i][j] = value;
                break;
            case 4:
                employe[i][j] = value;
                setValueAt(value, i, 2);
                break;

            case 5:
                employe[i][j] = (int) value;
                setValueAt(getSallary(i), i, 7);
                break;
            case 6:
                employe[i][j] = (int) value;
                setValueAt(getSallary(i), i, 7);
                break;
            case 7:
                employe[i][j] = value;
                break;
        }
        Data.saveDataInFile(employe);
        fireTableCellUpdated(i, j);
    }

    private int getSallary(int i) {
        int hours = (int) employe[i][5];
        int sallaryPerHour = (int) employe[i][6];
        return hours * sallaryPerHour;
    }
}
