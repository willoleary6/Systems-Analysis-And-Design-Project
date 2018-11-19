package ui.model;

import routeCalculation.Airport;

import javax.swing.*;
import javax.swing.event.ListDataListener;

public class AirportComboBoxModel implements ComboBoxModel {

    private Airport[] airports;
    private Object selected;

    public AirportComboBoxModel(Airport[] airports) {
        this.airports = airports;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return airports.length;
    }

    @Override
    public Object getElementAt(int index) {
        return airports[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }
}
