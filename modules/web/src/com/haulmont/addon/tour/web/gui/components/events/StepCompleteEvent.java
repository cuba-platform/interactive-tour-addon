package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Step;

/**
 * Event class that contains information about completion.
 */
public class StepCompleteEvent extends StepEvent {

    public StepCompleteEvent(Step source) {
        super(source);
    }
}