package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Tour;

/**
 * Event class that contains information about cancellation.
 */
public class TourCancelEvent extends TourEvent {

    public TourCancelEvent(Tour source) {
        super(source);
    }
}
