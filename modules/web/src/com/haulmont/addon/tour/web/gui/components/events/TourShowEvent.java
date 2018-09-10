package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Step;
import com.haulmont.addon.tour.web.gui.components.Tour;

/**
 * Event class that contains information about showing.
 */
public class TourShowEvent extends TourEvent {

    private final Step previousStep;
    private final Step currentStep;

    public TourShowEvent(Tour source, Step previousStep, Step currentStep) {
        super(source);
        this.previousStep = previousStep;
        this.currentStep = currentStep;
    }
}