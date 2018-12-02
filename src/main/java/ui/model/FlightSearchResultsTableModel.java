package ui.model;

import routeCalculation.Route;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class FlightSearchResultsTableModel extends AbstractTableModel {

    private String[] columnNames = { "Origin", "Destination", "Cost" };
    ArrayList<Route> routes;

    public FlightSearchResultsTableModel(ArrayList<Route> routes) {
        this.routes = routes;
    }

    @Override
    public int getRowCount() {
        return routes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Route route = routes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return route.getOrigin();
            case 1:
                return route.getDestination();
            case 2:
                return route.getCost();
            default:
                return "";
        }
    }
}
