/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.addon.tour.web.gui.components.events.StepButtonClickEvent;
import com.haulmont.bali.util.Preconditions;
import com.haulmont.cuba.gui.components.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * A button of a step that can be used to provide different actions if clicked.
 *
 * @see Step
 */
public class StepButton extends
        AbstractExtension<com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton> {

    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButtonClickListener stepButtonClickListener;

    protected Step step;

    protected List<Consumer<StepButtonClickEvent>> listenerList = null;

    /**
     * Constructs a new button with the given caption.
     *
     * @param caption The caption of the button
     */
    public StepButton(String caption) {
        Preconditions.checkNotEmptyString(caption);
        extension = createExtension(caption);
    }

    /**
     * Creates an extension for a vaadin step button.
     *
     * @param caption The step button caption
     * @return The vaadin step button extension
     */
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton createExtension(
            String caption) {
        return new com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton(caption);
    }

    @Override
    protected void initExtension(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton extension) {
    }

    /**
     * Adds a click listener to the button.
     *
     * @param listener the listener to be added
     */
    public void addStepButtonClickListener(Consumer<StepButtonClickEvent> listener) {
        if (listenerList == null) {
            listenerList = new ArrayList<>();

            this.stepButtonClickListener =
                    (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButtonClickListener) event -> {
                Component.MouseEventDetails details = new Component.MouseEventDetails();
                details.setClientX(event.getClientX());
                details.setClientY(event.getClientY());
                details.setRelativeY(event.getRelativeY());
                details.setRelativeX(event.getRelativeX());
                StepButtonClickEvent e = new StepButtonClickEvent(StepButton.this, details);
                for (Consumer<StepButtonClickEvent> clickEventConsumer : listenerList) {
                    clickEventConsumer.accept(e);
                }
            };
            extension.addClickListener(this.stepButtonClickListener);
        }

        if (!listenerList.contains(listener)) {
            listenerList.add(listener);
        }
    }

    /**
     * Removes the given click listener from the button.
     *
     * @param listener the listener to be removed
     */
    public void removeStepButtonClickListener(Consumer<StepButtonClickEvent> listener) {
        if (listenerList != null) {
            listenerList.remove(listener);

            if (listenerList.isEmpty()) {
                listenerList = null;
                extension.removeClickListener(this.stepButtonClickListener);
                this.stepButtonClickListener = null;
            }
        }
    }

    /**
     * Gets the step the button is attached to.
     *
     * @return the step
     */
    public Step getStep() {
        return step;
    }

    /**
     * Adds the button to the given step.
     * Use {@link Step#addButton(StepButton)} instead.
     *
     * @param step the step the button should be added to
     */
    public void setStep(Step step) {
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step vaadinStep =
                step.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step.class);
        extension.setStep(vaadinStep);
        this.step = step;
    }

    /**
     * Gets the caption of the button.
     *
     * @return the caption of the button
     */
    public String getCaption() {
        return extension.getCaption();
    }

    /**
     * Sets the caption of the button.
     *
     * @param caption the caption to be set
     */
    public void setCaption(String caption) {
        extension.setCaption(caption);
    }

    /**
     * Gets the enabled state of the button.
     *
     * @return <code>true</code> if the button is enabled, <code>false</code> otherwise
     */
    public boolean isEnabled() {
        return extension.isEnabled();
    }

    /**
     * Sets the enabled state of the button.
     *
     * @param enabled the enabled state to be set
     */
    public void setEnabled(boolean enabled) {
        extension.setEnabled(enabled);
    }

    /**
     * Adds one or more style names to this component. Multiple styles can be
     * specified as a space-separated list of style names.
     *
     * @param style one or more style names separated by space
     */
    public void addStyleName(String style) {
        extension.addStyleName(style);
    }

    /**
     * Removes one or more style names from component. Multiple styles can be
     * specified as a space-separated list of style names.
     *
     * @param style one or more style names separated by space
     */
    public void removeStyleName(String style) {
        extension.removeStyleName(style);
    }

    /**
     * Styles implementation is client-type-specific.
     *
     * @return current style name
     */
    public String getStyleName() {
        return extension.getStyleName();
    }

    /**
     * Sets one or more style names of the component, replacing any
     * previous styles. Multiple styles can be specified as a
     * space-separated list of style names.
     * <p>
     * Styles implementation is client-type-specific.
     *
     * @param style one or more style names separated by space
     */
    public void setStyleName(String style) {
        extension.setStyleName(style);
    }
}