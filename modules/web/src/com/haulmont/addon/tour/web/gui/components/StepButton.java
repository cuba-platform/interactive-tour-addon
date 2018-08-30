package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.cuba.gui.components.Component;

import java.util.EventObject;
import java.util.function.Consumer;

/**
 * A button of a step that can be used to provide different actions if clicked.
 *
 * @see Step
 */
public interface StepButton {

    /**
     * Gets client specific component instance. Can be used in client module to simplify invocation of underlying API.
     *
     * @param internalClass class of underlying component implementation
     * @param <X>           type of internal class
     * @return internal client specific component
     */
    <X> X unwrap(Class<X> internalClass);

    /**
     * Gets the step the button is attached to.
     *
     * @return the step
     */
    Step getStep();

    /**
     * Adds the button to the given step.
     * Use {@link Step#addButton(StepButton)} instead.
     *
     * @param step the step the button should be added to
     */
    void setStep(Step step);

    /**
     * Gets the caption of the button.
     *
     * @return the caption of the button
     */
    String getCaption();

    /**
     * Sets the caption of the button.
     *
     * @param caption the caption to be set
     */
    void setCaption(String caption);

    /**
     * Gets the enabled state of the button.
     *
     * @return <code>true</code> if the button is enabled, <code>false</code> otherwise
     */
    boolean isEnabled();

    /**
     * Sets the enabled state of the button.
     *
     * @param enabled the enabled state to be set
     */
    void setEnabled(boolean enabled);

    /**
     * Adds one or more style names to this component. Multiple styles can be
     * specified as a space-separated list of style names.
     *
     * @param style one or more style names separated by space
     */
    void addStyleName(String style);

    /**
     * Removes one or more style names from component. Multiple styles can be
     * specified as a space-separated list of style names.
     *
     * @param style one or more style names separated by space
     */
    void removeStyleName(String style);

    /**
     * Styles implementation is client-type-specific.
     *
     * @return current style name
     */
    String getStyleName();

    /**
     * Sets one or more style names of the component, replacing any
     * previous styles. Multiple styles can be specified as a
     * space-separated list of style names.
     * <p>
     * Styles implementation is client-type-specific.
     *
     * @param style one or more style names separated by space
     */
    void setStyleName(String style);

    /**
     * Adds a click listener to the button.
     *
     * @param listener the listener to be added
     */
    void addStepButtonClickListener(Consumer<ClickEvent> listener);

    /**
     * Removes the given click listener from the button.
     *
     * @param listener the listener to be removed
     */
    void removeStepButtonClickListener(Consumer<ClickEvent> listener);

    /**
     * Event class that contains information about a click.
     */
    class ClickEvent extends EventObject implements TourProvider, StepProvider, StepButtonProvider {
        protected Component.MouseEventDetails details;

        /**
         * Constructs a new provider.
         *
         * @param source the source of the provider
         */
        public ClickEvent(StepButton source) {
            this(source, null);
        }

        public ClickEvent(StepButton source, Component.MouseEventDetails details) {
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
}
