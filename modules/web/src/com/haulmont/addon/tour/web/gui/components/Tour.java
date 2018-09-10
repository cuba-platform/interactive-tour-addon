package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.addon.tour.web.gui.components.events.*;
import com.haulmont.cuba.gui.components.Component;
import com.vaadin.server.AbstractClientConnector;

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

    protected List<Consumer<TourShowEvent>> tourShowListeners = null;
    protected List<Consumer<TourCancelEvent>> tourCancelListeners = null;
    protected List<Consumer<TourCompleteEvent>> tourCompleteListeners = null;
    protected List<Consumer<TourStartEvent>> tourStartListeners = null;
    protected List<Consumer<TourHideEvent>> tourHideListeners = null;

    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourShowListener tourShowListener;
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourCancelListener tourCancelListener;
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourCompleteListener tourCompleteListener;
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourStartListener tourStartListener;
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourHideListener tourHideListener;

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
    public void addShowListener(Consumer<TourShowEvent> listener) {
        if (tourShowListeners == null) {
            tourShowListeners = new ArrayList<>();

            this.tourShowListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourShowListener) event -> {
                com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step currentStep = event.getCurrentStep();
                com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step previousStep = event.getPreviousStep();
                TourShowEvent e = new TourShowEvent(Tour.this, getStepByVaadinStep(previousStep), getStepByVaadinStep(currentStep));
                for (Consumer<TourShowEvent> tourShowListener : tourShowListeners) {
                    tourShowListener.accept(e);
                }
            };

            extension.addShowListener(this.tourShowListener);

        }
        if (!tourShowListeners.contains(listener)) {
            tourShowListeners.add(listener);
        }
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    public void removeShowListener(Consumer<TourShowEvent> listener) {
        if (tourShowListeners != null) {
            tourShowListeners.remove(listener);

            if (tourShowListeners.isEmpty()) {
                tourShowListeners = null;
                extension.removeShowListener(this.tourShowListener);
                this.tourShowListener = null;
            }
        }
    }

    /**
     * Adds the given listener to the tour that will be triggered if the tour is cancelled.
     *
     * @param listener the listener to be added
     */
    public void addCancelListener(Consumer<TourCancelEvent> listener) {
        if (tourCancelListeners == null) {
            tourCancelListeners = new ArrayList<>();

            this.tourCancelListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourCancelListener) event -> {
                TourCancelEvent e = new TourCancelEvent(Tour.this);
                for (Consumer<TourCancelEvent> tourCancelListener : tourCancelListeners) {
                    tourCancelListener.accept(e);
                }
            };

            extension.addCancelListener(this.tourCancelListener);

        }
        if (!tourCancelListeners.contains(listener)) {
            tourCancelListeners.add(listener);
        }
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    public void removeCancelListener(Consumer<TourCancelEvent> listener) {
        if (tourCancelListeners != null) {
            tourCancelListeners.remove(listener);

            if (tourCancelListeners.isEmpty()) {
                tourCancelListeners = null;
                extension.removeCancelListener(this.tourCancelListener);
                this.tourCancelListener = null;
            }
        }
    }

    /**
     * Adds the given listener to the tour that will be triggered if the tour is completed.
     *
     * @param listener the listener to be added
     */
    public void addCompleteListener(Consumer<TourCompleteEvent> listener) {
        if (tourCompleteListeners == null) {
            tourCompleteListeners = new ArrayList<>();

            this.tourCompleteListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourCompleteListener) event -> {
                TourCompleteEvent e = new TourCompleteEvent(Tour.this);
                for (Consumer<TourCompleteEvent> tourCompleteListener : tourCompleteListeners) {
                    tourCompleteListener.accept(e);
                }
            };

            extension.addCompleteListener(this.tourCompleteListener);

        }
        if (!tourCompleteListeners.contains(listener)) {
            tourCompleteListeners.add(listener);
        }
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    public void removeCompleteListener(Consumer<TourCompleteEvent> listener) {
        if (tourCompleteListeners != null) {
            tourCompleteListeners.remove(listener);

            if (tourCompleteListeners.isEmpty()) {
                tourCompleteListeners = null;
                extension.removeCompleteListener(this.tourCompleteListener);
                this.tourCompleteListener = null;
            }
        }
    }

    /**
     * Adds the given listener to the tour that will be triggered if the tour is hidden.
     *
     * @param listener the listener to be added
     */
    public void addHideListener(Consumer<TourHideEvent> listener) {
        if (tourHideListeners == null) {
            tourHideListeners = new ArrayList<>();

            this.tourHideListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourHideListener) event -> {
                TourHideEvent e = new TourHideEvent(Tour.this);
                for (Consumer<TourHideEvent> tourHideListener : tourHideListeners) {
                    tourHideListener.accept(e);
                }
            };

            extension.addHideListener(this.tourHideListener);

        }
        if (!tourHideListeners.contains(listener)) {
            tourHideListeners.add(listener);
        }
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    public void removeHideListener(Consumer<TourHideEvent> listener) {
        if (tourHideListeners != null) {
            tourHideListeners.remove(listener);

            if (tourHideListeners.isEmpty()) {
                tourHideListeners = null;
                extension.removeHideListener(this.tourHideListener);
                this.tourHideListener = null;
            }
        }
    }

    /**
     * Adds the given listener to the tour that will be triggered if the tour is started.
     *
     * @param listener the listener to be added
     */
    public void addStartListener(Consumer<TourStartEvent> listener) {
        if (tourStartListeners == null) {
            tourStartListeners = new ArrayList<>();

            this.tourStartListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourStartListener) event -> {
                TourStartEvent e = new TourStartEvent(Tour.this);
                for (Consumer<TourStartEvent> tourStartListener : tourStartListeners) {
                    tourStartListener.accept(e);
                }
            };

            extension.addStartListener(this.tourStartListener);

        }
        if (!tourStartListeners.contains(listener)) {
            tourStartListeners.add(listener);
        }
    }

    /**
     * Removes the given listener from the tour.
     *
     * @param listener the listener to be removed
     */
    public void removeStartListener(Consumer<TourStartEvent> listener) {
        if (tourStartListeners != null) {
            tourStartListeners.remove(listener);

            if (tourStartListeners.isEmpty()) {
                tourStartListeners = null;
                extension.removeStartListener(this.tourStartListener);
                this.tourStartListener = null;
            }
        }
    }
}