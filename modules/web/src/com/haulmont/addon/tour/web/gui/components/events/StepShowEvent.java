package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Step;

/**
 * Event class that contains information about showing.
 */
public class StepShowEvent extends StepEvent {

    public StepShowEvent(Step source) {
        super(source);
    }
}
