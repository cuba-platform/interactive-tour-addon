package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.cuba.gui.components.Component;
import com.vaadin.server.AbstractClientConnector;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class WebTour extends WebAbstractExtension<com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour> implements Tour {

    protected List<Step> stepList = new ArrayList<>();

    protected List<Consumer<ShowEvent>> tourShowListeners = null;
    protected List<Consumer<CancelEvent>> tourCancelListeners = null;
    protected List<Consumer<CompleteEvent>> tourCompleteListeners = null;
    protected List<Consumer<StartEvent>> tourStartListeners = null;
    protected List<Consumer<HideEvent>> tourHideListeners = null;

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
    public WebTour(Component component) {
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

    @Override
    public void addStep(Step step) {
        step.setTour(this);
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step vaadinStep = step.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step.class);
        extension.addStep(vaadinStep);
        stepList.add(step);
    }

    @Override
    public void removeStep(Step step) {
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step vaadinStep = step.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step.class);
        extension.removeStep(vaadinStep);
        stepList.remove(step);
    }

    @Override
    public Step getCurrentStep() {
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step currentStep = extension.getCurrentStep();
        return getStepByVaadinStep(currentStep);
    }

    @Override
    public void cancel() {
        extension.cancel();
    }

    @Override
    public void start() {
        extension.start();
    }

    @Override
    public void hide() {
        extension.hide();
    }

    @Override
    public void next() {
        extension.next();
    }

    @Override
    public void back() {
        extension.back();
    }

    @Override
    public int getStepCount() {
        return stepList.size();
    }

    @Override
    @Nullable
    public Step getStepById(String stepId) {
        return stepList.stream()
                .filter(s -> Objects.equals(s.getId(), stepId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Step getStepByIndex(int index) {
        return stepList.get(index);
    }

    @Override
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

    @Override
    public void addShowListener(Consumer<ShowEvent> showListener) {
        if (tourShowListeners == null) {
            tourShowListeners = new ArrayList<>();

            this.tourShowListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourShowListener) event -> {
                com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step currentStep = event.getCurrentStep();
                com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step previousStep = event.getPreviousStep();
                ShowEvent e = new ShowEvent(WebTour.this, getStepByVaadinStep(previousStep), getStepByVaadinStep(currentStep));
                for (Consumer<ShowEvent> tourShowListener : tourShowListeners) {
                    tourShowListener.accept(e);
                }
            };

            extension.addShowListener(this.tourShowListener);

        }
        if (!tourShowListeners.contains(showListener)) {
            tourShowListeners.add(showListener);
        }
    }

    @Override
    public void removeShowListener(Consumer<ShowEvent> showListener) {
        if (tourShowListeners != null) {
            tourShowListeners.remove(showListener);

            if (tourShowListeners.isEmpty()) {
                tourShowListeners = null;
                extension.removeShowListener(this.tourShowListener);
                this.tourShowListener = null;
            }
        }
    }

    @Override
    public void addCancelListener(Consumer<CancelEvent> cancelListener) {
        if (tourCancelListeners == null) {
            tourCancelListeners = new ArrayList<>();

            this.tourCancelListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourCancelListener) event -> {
                CancelEvent e = new CancelEvent(WebTour.this);
                for (Consumer<CancelEvent> tourCancelListener : tourCancelListeners) {
                    tourCancelListener.accept(e);
                }
            };

            extension.addCancelListener(this.tourCancelListener);

        }
        if (!tourCancelListeners.contains(cancelListener)) {
            tourCancelListeners.add(cancelListener);
        }
    }

    @Override
    public void removeCancelListener(Consumer<CancelEvent> cancelListener) {
        if (tourCancelListeners != null) {
            tourCancelListeners.remove(cancelListener);

            if (tourCancelListeners.isEmpty()) {
                tourCancelListeners = null;
                extension.removeCancelListener(this.tourCancelListener);
                this.tourCancelListener = null;
            }
        }
    }

    @Override
    public void addCompleteListener(Consumer<CompleteEvent> completeListener) {
        if (tourCompleteListeners == null) {
            tourCompleteListeners = new ArrayList<>();

            this.tourCompleteListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourCompleteListener) event -> {
                CompleteEvent e = new CompleteEvent(WebTour.this);
                for (Consumer<CompleteEvent> tourCompleteListener : tourCompleteListeners) {
                    tourCompleteListener.accept(e);
                }
            };

            extension.addCompleteListener(this.tourCompleteListener);

        }
        if (!tourCompleteListeners.contains(completeListener)) {
            tourCompleteListeners.add(completeListener);
        }
    }

    @Override
    public void removeCompleteListener(Consumer<CompleteEvent> completeListener) {
        if (tourCompleteListeners != null) {
            tourCompleteListeners.remove(completeListener);

            if (tourCompleteListeners.isEmpty()) {
                tourCompleteListeners = null;
                extension.removeCompleteListener(this.tourCompleteListener);
                this.tourCompleteListener = null;
            }
        }
    }

    @Override
    public void addHideListener(Consumer<HideEvent> hideListener) {
        if (tourHideListeners == null) {
            tourHideListeners = new ArrayList<>();

            this.tourHideListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourHideListener) event -> {
                HideEvent e = new HideEvent(WebTour.this);
                for (Consumer<HideEvent> tourHideListener : tourHideListeners) {
                    tourHideListener.accept(e);
                }
            };

            extension.addHideListener(this.tourHideListener);

        }
        if (!tourHideListeners.contains(hideListener)) {
            tourHideListeners.add(hideListener);
        }
    }

    @Override
    public void removeHideListener(Consumer<HideEvent> hideListener) {
        if (tourHideListeners != null) {
            tourHideListeners.remove(hideListener);

            if (tourHideListeners.isEmpty()) {
                tourHideListeners = null;
                extension.removeHideListener(this.tourHideListener);
                this.tourHideListener = null;
            }
        }
    }

    @Override
    public void addStartListener(Consumer<StartEvent> startListener) {
        if (tourStartListeners == null) {
            tourStartListeners = new ArrayList<>();

            this.tourStartListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.TourStartListener) event -> {
                StartEvent e = new StartEvent(WebTour.this);
                for (Consumer<StartEvent> tourStartListener : tourStartListeners) {
                    tourStartListener.accept(e);
                }
            };

            extension.addStartListener(this.tourStartListener);

        }
        if (!tourStartListeners.contains(startListener)) {
            tourStartListeners.add(startListener);
        }
    }

    @Override
    public void removeStartListener(Consumer<StartEvent> startListener) {
        if (tourStartListeners != null) {
            tourStartListeners.remove(startListener);

            if (tourStartListeners.isEmpty()) {
                tourStartListeners = null;
                extension.removeStartListener(this.tourStartListener);
                this.tourStartListener = null;
            }
        }
    }
}