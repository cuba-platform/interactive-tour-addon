package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Tour;

import java.util.EventObject;

/**
 * Base class for all events that were caused by a {@link Tour}.
 */
public class TourEvent extends EventObject implements TourProvider {

    /**
     * Constructs a new provider.
     *
     * @param source the source of the provider
     */
    public TourEvent(Object source) {
        super(source);
    }

    @Override
    public Tour getTour() {
        return (Tour) getSource();
    }
}
