package com.company;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class View {
    private JFrame jFrame;
    private JPanel jPanel;
    private JPanel northPanel;
    private TableModel tableModel;
    private JTable jTable;
    private JScrollPane scrollPane;
    private JButton addEmployee;
    private JButton removeEmployee;

    public View() {
        jFrame = new JFrame();

        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jFrame.add(jPanel);

        tableModel = new TableModel();
        jTable = new JTable(tableModel);
        jTable.setFillsViewportHeight(true);

        TableColumn tableColumn = jTable.getColumnModel().getColumn(3);

        JComboBox jComboBox = new JComboBox();
        jComboBox.addItem("Kierownik");
        jComboBox.addItem("Sprzedawca");
        jComboBox.addItem("Doradca");
        jComboBox.addItem("Asystent");

        tableColumn.setCellEditor(new DefaultCellEditor(jComboBox));

        scrollPane = new JScrollPane(jTable);
        jPanel.add(scrollPane, BorderLayout.CENTER);
        northPanel = new JPanel();

        jPanel.add(northPanel, BorderLayout.NORTH);
        addEmployee = new JButton();
        addEmployee.setText("Dodaj pracownika");

        northPanel.add(addEmployee, BorderLayout.EAST);

        removeEmployee = new JButton();
        removeEmployee.setText("Usun pracownika");

        northPanel.add(addEmployee, BorderLayout.CENTER);
        northPanel.add(removeEmployee, BorderLayout.EAST);

        jFrame.setVisible(true);
        jFrame.setResizable(true);
        jFrame.setSize(640, 320);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public JTable getjTable() {
        return jTable;
    }

    public JButton getAddEmployee() {
        return addEmployee;
    }

    public JButton getRemoveEmployee() {
        return removeEmployee;
    }
}
