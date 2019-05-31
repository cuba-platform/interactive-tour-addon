package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Step;
import com.haulmont.addon.tour.web.gui.components.StepButton;
import com.haulmont.addon.tour.web.gui.components.Tour;
import com.haulmont.cuba.gui.components.MouseEventDetails;

import java.util.EventObject;

/**
 * Event class that contains information about a click.
 */
public class StepButtonClickEvent extends EventObject implements TourProvider, StepProvider, StepButtonProvider {
    protected MouseEventDetails details;

    /**
     * Constructs a new provider.
     *
     * @param source the source of the provider
     */
    public StepButtonClickEvent(StepButton source) {
        this(source, null);
    }

    public StepButtonClickEvent(StepButton source, MouseEventDetails details) {
        super(source);
        this.details = details;
    }

    public MouseEventDetails getDetails() {
        return details;
    }

    @Override
    public Tour getTour() {
        Step step = getStep();
        return step != null ? step.getTour() : null;
    }

    @Override
    public Step getStep() {
        StepButton button = getStepButton();
        return button != null ? button.getStep() : null;
    }

    @Override
    public StepButton getStepButton() {
        return (StepButton) getSource();
    }
}
