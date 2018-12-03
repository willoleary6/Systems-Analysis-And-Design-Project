package ui.model;

import control.SearchController;
import control.UIController;
import routeCalculation.Airport;
import routeCalculation.Route;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Implemented Observer pattern using property change listeners
public class FlightSearchModel {
    public final static String AIRPORT_PROPERTY = "airports";
    private PropertyChangeSupport support;
    private Airport[] airports;
    private Date departureDate;
    private Airport departureAirport;
    private Airport destinationAirport;
    private boolean costBased = false;

    public FlightSearchModel(PropertyChangeListener pcl) {
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
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) throws ParseException {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(departureDate);
        this.departureDate = date;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public boolean isCostBased() {
        return costBased;
    }

    public void setCostBased(boolean costBased) {
        this.costBased = costBased;
    }

    public void updateAirports() {
        Airport[] airports = UIController.getInstance().getAirports().toArray(new Airport[0]);
        setAirports(airports);
    }

    public void searchForFlight() {
        UIController.getInstance().searchForFlights(departureAirport, destinationAirport, departureDate, costBased);
    }
}
