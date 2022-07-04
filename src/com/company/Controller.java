package com.company;

public class Controller {
    private View view;

    public Controller(View view) {
        this.view = view;
    }

    void initController(){
        view.getAddEmployee().addActionListener(e -> {
            view.getTableModel().addToTable();
        });

        view.getRemoveEmployee().addActionListener(e -> {
            view.getTableModel().removeEmployee(view.getjTable().getSelectedRow());
        });
    }
}
