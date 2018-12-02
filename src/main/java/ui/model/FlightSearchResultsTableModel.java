package ui.model;

import routeCalculation.Route;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class FlightSearchResultsTableModel extends AbstractTableModel {

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
        return Route.class.getFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return routes.get(rowIndex);
    }
}
