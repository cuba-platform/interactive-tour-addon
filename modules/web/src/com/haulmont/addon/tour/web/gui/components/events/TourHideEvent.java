package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Tour;

/**
 * Event class that contains information about hiding.
 */
public class TourHideEvent extends TourEvent {

    public TourHideEvent(Tour source) {
        super(source);
    }
}
