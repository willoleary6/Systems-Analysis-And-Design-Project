package ui.model;

import routeCalculation.Airport;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


//Implemented Observer pattern using property change listeners
public class FlightSearchModel {
    public final static String AIRPORT_PROPERTY = "airport";
    private PropertyChangeSupport support;
    private Airport[] airports;
    private String departureDate;

    public FlightSearchModel() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    private void setAirports(Airport[] airports) {
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
        Airport[] ports = { new Airport("Test 1"), new Airport("Test 2"), new Airport("Test 3") };
        //Airport[] ports = UIController.shared.getAirports();
        setAirports(ports);
    }
}
