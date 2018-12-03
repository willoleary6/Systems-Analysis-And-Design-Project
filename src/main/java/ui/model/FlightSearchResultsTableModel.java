package ui.model;

import routeCalculation.FlightDayAndTimeToDateLambdaFunctions;
import routeCalculation.GrapherLambdaFunctions;
import routeCalculation.Route;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class FlightSearchResultsTableModel extends AbstractTableModel {

    private String[] columnNames = { "Origin", "Destination", "Cost", "Departure Date", "Arrival Date"};
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
        FlightDayAndTimeToDateLambdaFunctions flightDayAndTimeToDateLambdaFunctions = () -> 0;
        flightDayAndTimeToDateLambdaFunctions.initialise();
        switch (columnIndex) {
            case 0:
                return route.getOrigin();
            case 1:
                return route.getDestination();
            case 2:
                return route.getCost();
            case 3:
                return flightDayAndTimeToDateLambdaFunctions.convertFlightTimeToDate(
                        route.getFlightDecorator().getFlight().getDepartDay(),
                        route.getFlightDecorator().getFlight().getDepartTime(),
                        new Date()
                );
            case 4:
                return flightDayAndTimeToDateLambdaFunctions.convertFlightTimeToDate(
                        route.getFlightDecorator().getFlight().getArriveDay(),
                        route.getFlightDecorator().getFlight().getArriveTime(),
                        new Date()
                );

            default:
                return "";
        }
    }
    //convertFlightTimeToDate(flightOnThisRoute.getDepartDay(), flightOnThisRoute.getDepartTime(),input)
}
