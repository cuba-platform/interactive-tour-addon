package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.addon.tour.web.gui.components.events.*;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.*;
import com.haulmont.bali.events.Subscription;
import com.haulmont.cuba.gui.components.Component;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.shared.Registration;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * A tour consisting of one or multiple steps.
 *
 * @see Step
 */
public class Tour extends AbstractExtension<com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour> {

    protected List<Step> stepList = new ArrayList<>();

    protected Registration tourShowListener;
    protected Registration tourCancelListener;
    protected Registration tourCompleteListener;
    protected Registration tourStartListener;
    protected Registration tourHideListener;

    /**
     * An extension of vaadin tour allowing to extend the component of our choice and not just the main UI component.
     * It allows to display the tour only in the required component.
     */
    class CubaTour extends com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour {

        /**
         * Constructs a new tour extending a target component.
         *
         * @param target the target component
         */
        public CubaTour(AbstractClientConnector target) {
            extendInternal(target);
        }

        @Override
        protected void extend(AbstractClientConnector target) {
        }

        /**
         * Extends a component of our choice.
         *
         * @param target the target to extend
         */
        protected void extendInternal(AbstractClientConnector target) {
            super.extend(target);

            target.addDetachListener(event -> cancel());
        }
    }

    /**
     * Constructs a new tour extending a component.
     *
     * @param component the component to extend
     */
    public Tour(Component component) {
        extension = createExtension(component);
    }

    /**
     * Creates an extension for a vaadin tour.
     *
     * @param component the component to extend
     * @return the vaadin tour extension
     */
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour createExtension(Component component) {
        return new CubaTour(component.unwrap(AbstractClientConnector.class));
    }

    @Override
    protected void initExtension(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour extension) {
    }

    /**
     * Adds the given step to the tour. Steps will be shown in the order they were added.
     *
     * @param step the step to be added
     */
    public void addStep(Step step) {
        step.setTour(this);
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step vaadinStep = step.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step.class);
        extension.addStep(vaadinStep);
        stepList.add(step);
    }

    /**
     * Removes the given step from the tour.
     *
     * @param step the step to be removed
     */
    public void removeStep(Step step) {
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step vaadinStep = step.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step.class);
        extension.removeStep(vaadinStep);
        stepList.remove(step);
    }

    /**
     * Gets the last shown step.
     *
     * @return the last shown step or <code>null</code> if no step is shown
     */
    public Step getCurrentStep() {
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step currentStep = extension.getCurrentStep();
        return getStepByVaadinStep(currentStep);
    }

    /**
     * Triggers cancel on the current step, hiding it without advancing. The cancel provider for the
     * tour will be triggered.
     */
    public void cancel() {
        extension.cancel();
    }

    /**
     * Shows the first step and begin the tour. The start provider of the tour will be triggered.
     */
    public void start() {
        extension.start();
    }

    /**
     * Hides the current step. The hide provider for the tour will be triggered.
     */
    public void hide() {
        extension.hide();
    }

    /**
     * Shows the next step in the order they were added.
     */
    public void next() {
        extension.next();
    }

    /**
     * Shows the previous step in the order they were added.
     */
    public void back() {
        extension.back();
    }

    /**
     * Gets the count of steps for this tour.
     *
     * @return the count of steps
     */
    public int getStepCount() {
        return stepList.size();
    }

    /**
     * Gets a step by its id.
     *
     * @param stepId the id of the step to get
     * @return the step with the given id or <code>null</code> if no step with the given id exists for this tour
     */
    @Nullable
    public Step getStepById(String stepId) {
        return stepList.stream()
                .filter(s -> Objects.equals(s.getId(), stepId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets a step by its index.
     *
     * @param index the index of the step to get
     * @return the step at the given index
     */
    public Step getStepByIndex(int index) {
        return stepList.get(index);
    }

    /**
     * Gets the steps of the tour.
     *
     * @return the steps of the tour inside an unmodifiable container
     */
    public List<Step> getSteps() {
        return Collections.unmodifiableList(stepList);
    }

    /**
     * Gets a cuba step by its vaadin step.
     *
     * @param vaadinStep the vaadin step
     * @return the cuba step by the vaadin step
     */
    @Nullable
    protected Step getStepByVaadinStep(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step vaadinStep) {
        for (Step step : getSteps()) {
            com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step internalVaadinStep = step.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step.class);
            if (internalVaadinStep == vaadinStep) {
                return step;
            }
        }
        return null;
    }

    /**
     * Adds the given listener to the tour that will be triggered if the tour is shown.
     *
     * @param listener the listener to be added
     */
    public Subscription addShowListener(Consumer<TourShowEvent> listener) {
        if (tourShowListener == null) {
            tourShowListener = extension.addShowListener(this::onTourShow);
        }
        return getEventHub().subscribe(TourShowEvent.class, listener);
    }

    protected void onTourShow(TourShowListener.ShowEvent event) {
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step currentStep = event.getCurrentStep();
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step previousStep = event.getPreviousStep();
        TourShowEvent e = new TourShowEvent(Tour.this, getStepByVaadinStep(previousStep), getStepByVaadinStep(currentStep));
        publish(TourShowEvent.class, e);
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    @Deprecated
    public void removeShowListener(Consumer<TourShowEvent> listener) {
    }

    /**
     * Adds the given listener to the tour that will be triggered if the tour is cancelled.
     *
     * @param listener the listener to be added
     */
    public Subscription addCancelListener(Consumer<TourCancelEvent> listener) {
        if (tourCancelListener == null) {
            tourCancelListener = extension.addCancelListener(this::onTourCancel);
        }
        return getEventHub().subscribe(TourCancelEvent.class, listener);
    }

    protected void onTourCancel(TourCancelListener.CancelEvent event) {
        TourCancelEvent e = new TourCancelEvent(Tour.this);
        publish(TourCancelEvent.class, e);
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    @Deprecated
    public void removeCancelListener(Consumer<TourCancelEvent> listener) {
    }

    /**
     * Adds the given listener to the tour that will be triggered if the tour is completed.
     *
     * @param listener the listener to be added
     */
    public Subscription addCompleteListener(Consumer<TourCompleteEvent> listener) {
        if (tourCompleteListener == null) {
            tourCompleteListener = extension.addCompleteListener(this::onTourComplete);
        }
        return getEventHub().subscribe(TourCompleteEvent.class, listener);
    }

    protected void onTourComplete(TourCompleteListener.CompleteEvent event) {
        TourCompleteEvent e = new TourCompleteEvent(Tour.this);
        publish(TourCompleteEvent.class, e);
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    @Deprecated
    public void removeCompleteListener(Consumer<TourCompleteEvent> listener) {
    }

    /**
     * Adds the given listener to the tour that will be triggered if the tour is hidden.
     *
     * @param listener the listener to be added
     */
    public Subscription addHideListener(Consumer<TourHideEvent> listener) {
        if (tourHideListener == null) {
            tourHideListener = extension.addHideListener(this::onTourHide);
        }
        return getEventHub().subscribe(TourHideEvent.class, listener);
    }

    protected void onTourHide(TourHideListener.HideEvent event) {
        TourHideEvent e = new TourHideEvent(Tour.this);
        publish(TourHideEvent.class, e);
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    @Deprecated
    public void removeHideListener(Consumer<TourHideEvent> listener) {
    }

    /**
     * Adds the given listener to the tour that will be triggered if the tour is started.
     *
     * @param listener the listener to be added
     */
    public Subscription addStartListener(Consumer<TourStartEvent> listener) {
        if (tourStartListener == null) {
            tourStartListener = extension.addStartListener(this::onTourStart);
        }
        return getEventHub().subscribe(TourStartEvent.class, listener);
    }

    protected void onTourStart(TourStartListener.StartEvent event) {
        TourStartEvent e = new TourStartEvent(Tour.this);
        publish(TourStartEvent.class, e);
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    @Deprecated
    public void removeStartListener(Consumer<TourStartEvent> listener) {
    }
}