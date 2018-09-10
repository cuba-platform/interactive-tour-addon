package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Step;

/**
 * Event class that contains information about cancellation.
 */
public class StepCancelEvent extends StepEvent {

    public StepCancelEvent(Step source) {
        super(source);
    }
}
