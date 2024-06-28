package com.vaadin.training.theming.solutions.ex3;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.training.theming.solutions.MainLayout;

import java.io.Serial;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Route(value = Exercise3.ROUTE, layout = MainLayout.class)
public class Exercise3 extends VerticalLayout {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String ROUTE = "ex3";
    public static final String TITLE = "Exercise 3";

    private final Grid<MonthlyExpense> expensesGrid;
    private final TextField limit;

    public Exercise3() {
        setSizeFull();

        limit = createLimitTextField();
        add(limit);

        expensesGrid = new Grid<>();
        add(expensesGrid);
        expand(expensesGrid);

        initializeAndPopulateGrid(expensesGrid);
    }

    private TextField createLimitTextField() {
        TextField limit = new TextField("Limit for monthly expenses");
        limit.addValueChangeListener(event -> expensesGrid.getDataProvider().refreshAll());
        return limit;
    }

    private void initializeAndPopulateGrid(Grid<MonthlyExpense> grid) {
        createGridColumns(grid);
        populateGridData(grid);
    }

    private void populateGridData(Grid<MonthlyExpense> grid) {
        List<MonthlyExpense> data = new ArrayList<>();
        for (int month = 0; month < 12; month++) {
            data.add(new MonthlyExpense(Month.values()[month].toString(), getExpenses(month)));
        }
        grid.setItems(data);
        grid.setAllRowsVisible(true);
    }

    private void createGridColumns(Grid<MonthlyExpense> grid) {
        grid.addColumn(MonthlyExpense::getMonth)
                .setHeader("Month");

        grid.addColumn(MonthlyExpense::getExpenses)
                .setKey("expenses")
                .setHeader("Expenses")
                .setPartNameGenerator(expense ->
                        expense.getExpenses() > getMonthlyExpenseLimit() ? "warn" : null
                );

        /* Note: you can also style the whole row by assigning a part name at the "grid level":
        grid.setPartNameGenerator(expense ->
                expense.getExpenses() > getMonthlyExpenseLimit() ? "warn" : null);
         */
    }

    private long getExpenses(int month) {
        return 300 + month * 50L;
    }

    private int getMonthlyExpenseLimit() {
        if (limit.getValue() == null || limit.getValue().isEmpty()) {
            return 100000;
        }
        return Integer.parseInt(limit.getValue());
    }
}