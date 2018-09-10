package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Tour;

/**
 * Event class that contains information about completion.
 */
public class TourCompleteEvent extends TourEvent {

    public TourCompleteEvent(Tour source) {
        super(source);
    }
}
