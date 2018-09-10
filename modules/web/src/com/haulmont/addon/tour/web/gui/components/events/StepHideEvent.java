package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Step;

/**
 * Event class that contains information about hiding.
 */
public class StepHideEvent extends StepEvent {

    public StepHideEvent(Step source) {
        super(source);
    }
}