package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Step;
import com.haulmont.addon.tour.web.gui.components.Tour;

import java.util.EventObject;

/**
 * Base class for all events that were caused by a {@link Step}.
 */
public class StepEvent extends EventObject implements TourProvider, StepProvider {

    /**
     * Constructs a new provider.
     *
     * @param source the source of the provider
     */
    public StepEvent(Step source) {
        super(source);
    }

    @Override
    public Step getStep() {
        return (Step) getSource();
    }

    @Override
    public Tour getTour() {
        Step step = getStep();
        return step != null ? step.getTour() : null;
    }
}
