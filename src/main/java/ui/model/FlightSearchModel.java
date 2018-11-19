package ui.model;

import control.SearchController;
import routeCalculation.Airport;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


//Implemented Observer pattern using property change listeners
public class FlightSearchModel {
    public final static String AIRPORT_PROPERTY = "airports";
    private PropertyChangeSupport support;
    private Airport[] airports;
    private String departureDate;
    private SearchController searchController;

    public FlightSearchModel(PropertyChangeListener pcl) {
        searchController = new SearchController();
        support = new PropertyChangeSupport(this);
        addPropertyChangeListener(pcl);
        updateAirports();
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setAirports(Airport[] airports) {
        support.firePropertyChange(AIRPORT_PROPERTY, this.airports, airports);
        this.airports = airports;
    }
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void updateAirports() {
        searchController.retrieveAirports();
        Airport[] airports = searchController.getAirports().toArray(new Airport[0]);
        setAirports(airports);
    }
}
