/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.bali.util.Preconditions;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.web.gui.components.WebAbstractComponent;
import com.vaadin.ui.AbstractComponent;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class WebStep extends WebAbstractExtension<com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step> implements Step {

    protected Tour tour;

    protected List<StepButton> buttonList = new ArrayList<>();

    protected List<Consumer<CancelEvent>> stepCancelListeners = null;
    protected List<Consumer<CompleteEvent>> stepCompleteListeners = null;
    protected List<Consumer<HideEvent>> stepHideListeners = null;
    protected List<Consumer<ShowEvent>> stepShowListeners = null;

    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepCancelListener stepCancelListener;
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepCompleteListener stepCompleteListener;
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepHideListener stepHideListener;
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepShowListener stepShowListener;

    protected Component attachedTo;

    /**
     * Constructs a new step with an id.
     *
     * @param id the id of the step
     */
    public WebStep(@Nullable String id) {
        extension = createExtension(id);
        initExtension(extension);
    }

    /**
     * Creates an extension for a vaadin step.
     *
     * @param id the step id
     * @return the vaadin step extension
     */
    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step createExtension(String id) {
        if (StringUtils.isBlank(id)) {
            return new com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step();
        }
        return new com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step(id);
    }

    @Override
    protected void initExtension(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step extension) {
        extension.setSizeFull();
    }

    @Override
    public Tour getTour() {
        return tour;
    }

    @Override
    public void setTour(Tour tour) {
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour vaadinTour =
                tour.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour.class);
        extension.setTour(vaadinTour);
        this.tour = tour;
    }

    @Override
    public void setTitle(String title) {
        extension.setTitle(title);
    }

    @Override
    public String getTitle() {
        return extension.getTitle();
    }

    @Override
    public void setText(String text) {
        extension.setText(text);
    }

    @Override
    public String getText() {
        return extension.getText();
    }

    @Override
    public boolean isVisible() {
        return extension.isVisible();
    }

    @Override
    public void setSizeFull() {
        extension.setSizeFull();
    }

    @Override
    public void setSizeUndefined() {
        extension.setSizeUndefined();
    }

    @Override
    public void setWidth(String width) {
        extension.setWidth(width);
    }

    @Override
    public float getWidth() {
        return extension.getWidth();
    }

    @Override
    public void setHeight(String height) {
        extension.setHeight(height);
    }

    @Override
    public float getHeight() {
        return extension.getHeight();
    }

    @Override
    public int getHeightUnits() {
        return WebAbstractComponent.UNIT_SYMBOLS.indexOf(extension.getHeightUnits());
    }

    @Override
    public int getWidthUnits() {
        return WebAbstractComponent.UNIT_SYMBOLS.indexOf(extension.getWidthUnits());
    }

    @Override
    public List<StepButton> getButtons() {
        return Collections.unmodifiableList(buttonList);
    }

    @Override
    public StepButton getButtonByIndex(int index) {
        return buttonList.get(index);
    }

    @Override
    public int getButtonCount() {
        return buttonList.size();
    }

    @Override
    public String getId() {
        return extension.getId();
    }

    @Override
    public Component getAttachedTo() {
        return attachedTo;
    }

    @Override
    public void setAttachedTo(Component component) {
        attachedTo = component;
        AbstractComponent abstractComponent = component.unwrap(AbstractComponent.class);
        extension.setAttachedTo(abstractComponent);
    }

    @Override
    public void setDetached() {
        attachedTo = null;
        extension.setDetached();
    }

    @Override
    public void setCancellable(boolean cancellable) {
        extension.setCancellable(cancellable);
    }

    @Override
    public boolean isCancellable() {
        return extension.isCancellable();
    }

    @Override
    public void setModal(boolean modal) {
        extension.setModal(modal);
    }

    @Override
    public boolean isModal() {
        return extension.isModal();
    }

    @Override
    public void setScrollTo(boolean scrollTo) {
        extension.setScrollTo(scrollTo);
    }

    @Override
    public boolean isScrollTo() {
        return extension.isScrollTo();
    }

    @Override
    public void setTextContentMode(ContentMode contentMode) {
        extension.setTextContentMode(toVaadinContentMode(contentMode));
    }

    @Override
    public ContentMode getTextContentMode() {
        return fromVaadinContentMode(extension.getTextContentMode());
    }

    @Override
    public void setTitleContentMode(ContentMode contentMode) {
        extension.setTitleContentMode(toVaadinContentMode(contentMode));
    }

    @Override
    public ContentMode getTitleContentMode() {
        return fromVaadinContentMode(extension.getTitleContentMode());
    }

    @Override
    public void setAnchor(StepAnchor anchor) {
        extension.setAnchor(toVaadinStepAnchor(anchor));
    }

    @Override
    public StepAnchor getAnchor() {
        return fromVaadinStepAnchor(extension.getAnchor());
    }

    @Override
    public void cancel() {
        extension.cancel();
    }

    @Override
    public void complete() {
        extension.complete();
    }

    @Override
    public void hide() {
        extension.hide();
    }

    @Override
    public void show() {
        extension.show();
    }

    @Override
    public void scrollTo() {
        extension.scrollTo();
    }

    @Override
    public void addButton(StepButton button) {
        button.setStep(this);
        buttonList.add(button);
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton vaadinStepButton =
                button.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton.class);
        extension.addButton(vaadinStepButton);
    }

    @Override
    public void removeButton(StepButton button) {
        button.setStep(null);
        buttonList.remove(button);
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton vaadinStepButton =
                button.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton.class);
        extension.removeButton(vaadinStepButton);
    }

    @Override
    public void addCancelListener(Consumer<CancelEvent> cancelListener) {
        if (stepCancelListeners == null) {
            stepCancelListeners = new ArrayList<>();

            this.stepCancelListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepCancelListener) event -> {
                CancelEvent e = new CancelEvent(WebStep.this);
                for (Consumer<CancelEvent> stepCancelListener : stepCancelListeners) {
                    stepCancelListener.accept(e);
                }
            };

            extension.addCancelListener(this.stepCancelListener);

        }
        if (!stepCancelListeners.contains(cancelListener)) {
            stepCancelListeners.add(cancelListener);
        }
    }

    @Override
    public void removeCancelListener(Consumer<CancelEvent> cancelListener) {
        if (stepCancelListeners != null) {
            stepCancelListeners.remove(cancelListener);

            if (stepCancelListeners.isEmpty()) {
                stepCancelListeners = null;
                extension.removeCancelListener(this.stepCancelListener);
                this.stepCancelListener = null;
            }
        }
    }

    @Override
    public void addCompleteListener(Consumer<CompleteEvent> completeListener) {
        if (stepCompleteListeners == null) {
            stepCompleteListeners = new ArrayList<>();

            this.stepCompleteListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepCompleteListener) event -> {
                CompleteEvent e = new CompleteEvent(WebStep.this);
                for (Consumer<CompleteEvent> stepCompleteListener : stepCompleteListeners) {
                    stepCompleteListener.accept(e);
                }
            };

            extension.addCompleteListener(this.stepCompleteListener);

        }
        if (!stepCompleteListeners.contains(completeListener)) {
            stepCompleteListeners.add(completeListener);
        }
    }

    @Override
    public void removeCompleteListener(Consumer<CompleteEvent> completeListener) {
        if (stepCompleteListeners != null) {
            stepCompleteListeners.remove(completeListener);

            if (stepCompleteListeners.isEmpty()) {
                stepCompleteListeners = null;
                extension.removeCompleteListener(this.stepCompleteListener);
                this.stepCompleteListener = null;
            }
        }
    }

    @Override
    public void addHideListener(Consumer<HideEvent> hideListener) {
        if (stepHideListeners == null) {
            stepHideListeners = new ArrayList<>();

            this.stepHideListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepHideListener) event -> {
                HideEvent e = new HideEvent(WebStep.this);
                for (Consumer<HideEvent> stepHideListener : stepHideListeners) {
                    stepHideListener.accept(e);
                }
            };

            extension.addHideListener(this.stepHideListener);

        }
        if (!stepHideListeners.contains(hideListener)) {
            stepHideListeners.add(hideListener);
        }
    }

    @Override
    public void removeHideListener(Consumer<HideEvent> hideListener) {
        if (stepHideListeners != null) {
            stepHideListeners.remove(hideListener);

            if (stepHideListeners.isEmpty()) {
                stepHideListeners = null;
                extension.removeHideListener(this.stepHideListener);
                this.stepHideListener = null;
            }
        }
    }

    @Override
    public void addShowListener(Consumer<ShowEvent> showListener) {
        if (stepShowListeners == null) {
            stepShowListeners = new ArrayList<>();

            this.stepShowListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepShowListener) event -> {
                ShowEvent e = new ShowEvent(WebStep.this);
                for (Consumer<ShowEvent> stepShowListener : stepShowListeners) {
                    stepShowListener.accept(e);
                }
            };

            extension.addShowListener(this.stepShowListener);

        }
        if (!stepShowListeners.contains(showListener)) {
            stepShowListeners.add(showListener);
        }
    }

    @Override
    public void removeShowListener(Consumer<ShowEvent> showListener) {
        if (stepShowListeners != null) {
            stepShowListeners.remove(showListener);

            if (stepShowListeners.isEmpty()) {
                stepShowListeners = null;
                extension.removeShowListener(this.stepShowListener);
                this.stepShowListener = null;
            }
        }
    }

    /**
     * Converts a cuba step anchor to a vaadin step anchor.
     *
     * @param stepAnchor the cuba step anchor
     * @return the vaadin step anchor
     */
    public com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepAnchor toVaadinStepAnchor(Step.StepAnchor stepAnchor) {
        Preconditions.checkNotNullArgument(stepAnchor);

        switch (stepAnchor) {
            case TOP:
                return com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepAnchor.TOP;
            case RIGHT:
                return com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepAnchor.RIGHT;
            case BOTTOM:
                return com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepAnchor.BOTTOM;
            case LEFT:
                return com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepAnchor.LEFT;
            default:
                throw new IllegalArgumentException("Unknown extension anchor: " + stepAnchor);
        }
    }

    /**
     * Converts a vaadin step anchor to a cuba step anchor.
     *
     * @param stepAnchor the vaadin step anchor
     * @return the cuba step anchor
     */
    public Step.StepAnchor fromVaadinStepAnchor(com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepAnchor stepAnchor) {
        Preconditions.checkNotNullArgument(stepAnchor);

        switch (stepAnchor) {
            case TOP:
                return Step.StepAnchor.TOP;
            case RIGHT:
                return Step.StepAnchor.RIGHT;
            case BOTTOM:
                return Step.StepAnchor.BOTTOM;
            case LEFT:
                return Step.StepAnchor.LEFT;
            default:
                throw new IllegalArgumentException("Unknown extension anchor: " + stepAnchor);
        }
    }

    /**
     * Converts a cuba content mode to a vaadin content mode.
     *
     * @param contentMode the cuba content mode
     * @return the vaadin content mode
     */
    public com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.ContentMode toVaadinContentMode(Step.ContentMode contentMode) {
        Preconditions.checkNotNullArgument(contentMode);

        switch (contentMode) {
            case TEXT:
                return com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.ContentMode.TEXT;
            case PREFORMATTED:
                return com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.ContentMode.PREFORMATTED;
            case HTML:
                return com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.ContentMode.HTML;
            default:
                throw new IllegalArgumentException("Unknown content mode: " + contentMode);
        }
    }

    /**
     * Converts a vaadin content mode to a cuba content mode.
     *
     * @param contentMode the vaadin content mode
     * @return the cuba content mode
     */
    public Step.ContentMode fromVaadinContentMode(
            com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.ContentMode contentMode) {
        Preconditions.checkNotNullArgument(contentMode);

        switch (contentMode) {
            case TEXT:
                return Step.ContentMode.TEXT;
            case PREFORMATTED:
                return Step.ContentMode.PREFORMATTED;
            case HTML:
                return Step.ContentMode.HTML;
            default:
                throw new IllegalArgumentException("Unknown content mode: " + contentMode);
        }
    }
}
