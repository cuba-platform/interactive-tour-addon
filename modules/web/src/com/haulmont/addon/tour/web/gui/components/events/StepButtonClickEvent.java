package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.Step;
import com.haulmont.addon.tour.web.gui.components.StepButton;
import com.haulmont.addon.tour.web.gui.components.Tour;
import com.haulmont.addon.tour.web.gui.components.events.StepButtonProvider;
import com.haulmont.addon.tour.web.gui.components.events.StepProvider;
import com.haulmont.addon.tour.web.gui.components.events.TourProvider;
import com.haulmont.cuba.gui.components.Component;

import java.util.EventObject;

/**
 * Event class that contains information about a click.
 */
public class StepButtonClickEvent extends EventObject implements TourProvider, StepProvider, StepButtonProvider {
    protected Component.MouseEventDetails details;

    /**
     * Constructs a new provider.
     *
     * @param source the source of the provider
     */
    public StepButtonClickEvent(StepButton source) {
        this(source, null);
    }

    public StepButtonClickEvent(StepButton source, Component.MouseEventDetails details) {
        super(source);
        this.details = details;
    }

    public Component.MouseEventDetails getDetails() {
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
