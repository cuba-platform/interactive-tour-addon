package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Tour;

/**
 * Event class that contains information about starting.
 */
public class TourStartEvent extends TourEvent {

    public TourStartEvent(Tour source) {
        super(source);
    }
}
