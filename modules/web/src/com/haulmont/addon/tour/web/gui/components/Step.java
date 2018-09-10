/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.addon.tour.web.gui.components.events.StepCancelEvent;
import com.haulmont.addon.tour.web.gui.components.events.StepCompleteEvent;
import com.haulmont.addon.tour.web.gui.components.events.StepHideEvent;
import com.haulmont.addon.tour.web.gui.components.events.StepShowEvent;
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

/**
 * A single step of a tour.
 *
 * @see Tour
 */
public class Step extends AbstractExtension<com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step> {

    protected Tour tour;

    protected List<StepButton> buttonList = new ArrayList<>();

    protected List<Consumer<StepCancelEvent>> stepCancelListeners = null;
    protected List<Consumer<StepCompleteEvent>> stepCompleteListeners = null;
    protected List<Consumer<StepHideEvent>> stepHideListeners = null;
    protected List<Consumer<StepShowEvent>> stepShowListeners = null;

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
    public Step(@Nullable String id) {
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

    /**
     * Gets the tour this step is added to.
     *
     * @return the tour
     */
    public Tour getTour() {
        return tour;
    }

    /**
     * Adds the step to the given tour.
     * Use {@link Tour#addStep(Step)} instead.
     *
     * @param tour the tour the step should be added to
     */
    public void setTour(Tour tour) {
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour vaadinTour =
                tour.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour.class);
        extension.setTour(vaadinTour);
        this.tour = tour;
    }

    /**
     * Sets the title of the step.
     *
     * @param title the title to be set
     */
    public void setTitle(String title) {
        extension.setTitle(title);
    }

    /**
     * Gets the title of the step.
     *
     * @return the title of the step
     */
    public String getTitle() {
        return extension.getTitle();
    }

    /**
     * Sets the text of the step.
     *
     * @param text the text to be set
     */
    public void setText(String text) {
        extension.setText(text);
    }

    /**
     * Gets the text of the step.
     *
     * @return the text of the step
     */
    public String getText() {
        return extension.getText();
    }

    /**
     * Checks if the step is currently visible.
     *
     * @return <code>true</code> if the step is currently visible, <code>false</code> otherwise
     */
    public boolean isVisible() {
        return extension.isVisible();
    }

    /**
     * Sets the step size to 100 percentage.
     */
    public void setSizeFull() {
        extension.setSizeFull();
    }

    /**
     * Sets the step size to undefined.
     */
    public void setSizeUndefined() {
        extension.setSizeUndefined();
    }

    /**
     * Sets the step width.
     *
     * @param width the width
     */
    public void setWidth(String width) {
        extension.setWidth(width);
    }

    /**
     * Gets the step width.
     *
     * @return the width
     */
    public float getWidth() {
        return extension.getWidth();
    }

    /**
     * Sets the step height.
     *
     * @param height the height
     */
    public void setHeight(String height) {
        extension.setHeight(height);
    }

    /**
     * Gets the step height.
     *
     * @return the height
     */
    public float getHeight() {
        return extension.getHeight();
    }

    /**
     * Gets the step height units.
     *
     * @return the height units
     */
    public int getHeightUnits() {
        return WebAbstractComponent.UNIT_SYMBOLS.indexOf(extension.getHeightUnits());
    }

    /**
     * Gets the step width units.
     *
     * @return the width units
     */
    public int getWidthUnits() {
        return WebAbstractComponent.UNIT_SYMBOLS.indexOf(extension.getWidthUnits());
    }

    /**
     * Gets the buttons of the step.
     *
     * @return the unmodifiable list of step buttons
     */
    public List<StepButton> getButtons() {
        return Collections.unmodifiableList(buttonList);
    }

    /**
     * Gets a button by its index.
     *
     * @param index the index of the button to get
     * @return the button at the given index
     */
    public StepButton getButtonByIndex(int index) {
        return buttonList.get(index);
    }

    /**
     * Gets the count of buttons of this step.
     *
     * @return the count of buttons of this step
     */
    public int getButtonCount() {
        return buttonList.size();
    }

    /**
     * Gets the id of the step.
     *
     * @return the id of the step
     */
    public String getId() {
        return extension.getId();
    }

    /**
     * Gets the component the step is attached to.
     *
     * @return the component or <code>null</code> if the step is not attached to any component
     */
    public Component getAttachedTo() {
        return attachedTo;
    }

    /**
     * Sets the component the step will be attached to.
     * <p>
     * If set to <code>null</code>, the step will not be attached and shown in the middle of the
     * screen.
     *
     * @param component the component to attach the step to or <code>null</code>
     * @see #setDetached()
     */
    public void setAttachedTo(Component component) {
        attachedTo = component;
        AbstractComponent abstractComponent = component.unwrap(AbstractComponent.class);
        extension.setAttachedTo(abstractComponent);
    }

    /**
     * Sets the step to be not attached to any component. The step will then be shown in the middle of
     * the screen. This is equal to calling {@link #setAttachedTo(Component)} with
     * <code>null</code> as parameter.
     */
    public void setDetached() {
        attachedTo = null;
        extension.setDetached();
    }

    /**
     * Sets the cancellable state of the step.
     *
     * @param cancellable <code>true</code> if the step should be cancellable, <code>false</code> otherwise
     */
    public void setCancellable(boolean cancellable) {
        extension.setCancellable(cancellable);
    }

    /**
     * Gets the cancellable state of the step.
     *
     * @return <code>true</code> if the step is cancellable, <code>false</code> otherwise
     */
    public boolean isCancellable() {
        return extension.isCancellable();
    }

    /**
     * Sets the modality of the step.
     *
     * @param modal <code>true</code> if the step should be modal, <code>false</code> otherwise
     */
    public void setModal(boolean modal) {
        extension.setModal(modal);
    }

    /**
     * Gets the modal state of the step.
     *
     * @return <code>true</code> if the step is modal, <code>false</code> otherwise
     */
    public boolean isModal() {
        return extension.isModal();
    }

    /**
     * Sets the scrollTo state of the step.
     *
     * @param scrollTo <code>true</code> if the step should be scrolled into view when shown, <code>false</code> otherwise
     */
    public void setScrollTo(boolean scrollTo) {
        extension.setScrollTo(scrollTo);
    }

    /**
     * Gets the scrollTo state of the step.
     *
     * @return <code>true</code> if the step is scrolled to when shown, <code>false</code> otherwise
     */
    public boolean isScrollTo() {
        return extension.isScrollTo();
    }

    /**
     * Sets the content mode for the text of the step.
     *
     * @param contentMode the content mode to be set
     */
    public void setTextContentMode(ContentMode contentMode) {
        extension.setTextContentMode(toVaadinContentMode(contentMode));
    }

    /**
     * Gets the content mode for the text of the step.
     *
     * @return the content mode for the text of the step
     */
    public ContentMode getTextContentMode() {
        return fromVaadinContentMode(extension.getTextContentMode());
    }

    /**
     * Sets the content mode for the title of the step.
     *
     * @param contentMode the content mode to be set
     */
    public void setTitleContentMode(ContentMode contentMode) {
        extension.setTitleContentMode(toVaadinContentMode(contentMode));
    }

    /**
     * Gets the content mode for the title of the step.
     *
     * @return the content mode for the title of the step
     */
    public ContentMode getTitleContentMode() {
        return fromVaadinContentMode(extension.getTitleContentMode());
    }

    /**
     * Sets the anchor the step is shown relative to the component it is attached to.
     *
     * @param anchor the anchor to be set
     */
    public void setAnchor(StepAnchor anchor) {
        extension.setAnchor(toVaadinStepAnchor(anchor));
    }

    /**
     * Gets the anchor the step is shown relative to the component it is attached to.
     *
     * @return the anchor of the step
     */
    public StepAnchor getAnchor() {
        return fromVaadinStepAnchor(extension.getAnchor());
    }

    /**
     * Hides this step and trigger the cancel provider.
     */
    public void cancel() {
        extension.cancel();
    }

    /**
     * Hides this step and trigger the complete provider.
     */
    public void complete() {
        extension.complete();
    }

    /**
     * Hides this step.
     */
    public void hide() {
        extension.hide();
    }

    /**
     * Shows this step.
     */
    public void show() {
        extension.show();
    }

    /**
     * Scrolls to this steps element.
     */
    public void scrollTo() {
        extension.scrollTo();
    }

    /**
     * Adds a button the step. The button will be shown in the order they are added.
     *
     * @param button the button to be added
     */
    public void addButton(StepButton button) {
        button.setStep(this);
        buttonList.add(button);
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton vaadinStepButton =
                button.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton.class);
        extension.addButton(vaadinStepButton);
    }

    /**
     * Removes a button from the step.
     *
     * @param button the button to be removed
     */
    public void removeButton(StepButton button) {
        button.setStep(null);
        buttonList.remove(button);
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton vaadinStepButton =
                button.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton.class);
        extension.removeButton(vaadinStepButton);
    }

    /**
     * Adds the given listener to the step that will be triggered if the step is cancelled.
     *
     * @param listener the listener to be added
     */
    public void addCancelListener(Consumer<StepCancelEvent> listener) {
        if (stepCancelListeners == null) {
            stepCancelListeners = new ArrayList<>();

            this.stepCancelListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepCancelListener) event -> {
                StepCancelEvent e = new StepCancelEvent(Step.this);
                for (Consumer<StepCancelEvent> stepCancelListener : stepCancelListeners) {
                    stepCancelListener.accept(e);
                }
            };

            extension.addCancelListener(this.stepCancelListener);

        }
        if (!stepCancelListeners.contains(listener)) {
            stepCancelListeners.add(listener);
        }
    }

    /**
     * Removes the given listener from the step.
     *
     * @param listener the listener to be removed
     */
    public void removeCancelListener(Consumer<StepCancelEvent> listener) {
        if (stepCancelListeners != null) {
            stepCancelListeners.remove(listener);

            if (stepCancelListeners.isEmpty()) {
                stepCancelListeners = null;
                extension.removeCancelListener(this.stepCancelListener);
                this.stepCancelListener = null;
            }
        }
    }

    /**
     * Adds the given listener to the step that will be triggered if the step is completed.
     *
     * @param listener the listener to be added
     */
    public void addCompleteListener(Consumer<StepCompleteEvent> listener) {
        if (stepCompleteListeners == null) {
            stepCompleteListeners = new ArrayList<>();

            this.stepCompleteListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepCompleteListener) event -> {
                StepCompleteEvent e = new StepCompleteEvent(Step.this);
                for (Consumer<StepCompleteEvent> stepCompleteListener : stepCompleteListeners) {
                    stepCompleteListener.accept(e);
                }
            };

            extension.addCompleteListener(this.stepCompleteListener);

        }
        if (!stepCompleteListeners.contains(listener)) {
            stepCompleteListeners.add(listener);
        }
    }

    /**
     * Removes the given listener from the step.
     *
     * @param listener the listener to be removed
     */
    public void removeCompleteListener(Consumer<StepCompleteEvent> listener) {
        if (stepCompleteListeners != null) {
            stepCompleteListeners.remove(listener);

            if (stepCompleteListeners.isEmpty()) {
                stepCompleteListeners = null;
                extension.removeCompleteListener(this.stepCompleteListener);
                this.stepCompleteListener = null;
            }
        }
    }

    /**
     * Adds the given listener to the step that will be triggered if the step is hidden.
     *
     * @param listener the listener to be added
     */
    public void addHideListener(Consumer<StepHideEvent> listener) {
        if (stepHideListeners == null) {
            stepHideListeners = new ArrayList<>();

            this.stepHideListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepHideListener) event -> {
                StepHideEvent e = new StepHideEvent(Step.this);
                for (Consumer<StepHideEvent> stepHideListener : stepHideListeners) {
                    stepHideListener.accept(e);
                }
            };

            extension.addHideListener(this.stepHideListener);

        }
        if (!stepHideListeners.contains(listener)) {
            stepHideListeners.add(listener);
        }
    }


    /**
     * Removes the given listener from the step.
     *
     * @param listener the listener to be removed.
     */
    public void removeHideListener(Consumer<StepHideEvent> listener) {
        if (stepHideListeners != null) {
            stepHideListeners.remove(listener);

            if (stepHideListeners.isEmpty()) {
                stepHideListeners = null;
                extension.removeHideListener(this.stepHideListener);
                this.stepHideListener = null;
            }
        }
    }

    /**
     * Adds the given listener to the step that will be triggered if the step is shown.
     *
     * @param listener the listener to be added
     */
    public void addShowListener(Consumer<StepShowEvent> listener) {
        if (stepShowListeners == null) {
            stepShowListeners = new ArrayList<>();

            this.stepShowListener = (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.StepShowListener) event -> {
                StepShowEvent e = new StepShowEvent(Step.this);
                for (Consumer<StepShowEvent> stepShowListener : stepShowListeners) {
                    stepShowListener.accept(e);
                }
            };

            extension.addShowListener(this.stepShowListener);

        }
        if (!stepShowListeners.contains(listener)) {
            stepShowListeners.add(listener);
        }
    }


    /**
     * Removes the given listener from the step.
     *
     * @param listener the listener to be removed
     */
    public void removeShowListener(Consumer<StepShowEvent> listener) {
        if (stepShowListeners != null) {
            stepShowListeners.remove(listener);

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
    public com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepAnchor toVaadinStepAnchor(StepAnchor stepAnchor) {
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
    public StepAnchor fromVaadinStepAnchor(com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepAnchor stepAnchor) {
        Preconditions.checkNotNullArgument(stepAnchor);

        switch (stepAnchor) {
            case TOP:
                return StepAnchor.TOP;
            case RIGHT:
                return StepAnchor.RIGHT;
            case BOTTOM:
                return StepAnchor.BOTTOM;
            case LEFT:
                return StepAnchor.LEFT;
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
    public com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.ContentMode toVaadinContentMode(ContentMode contentMode) {
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
    public ContentMode fromVaadinContentMode(
            com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.ContentMode contentMode) {
        Preconditions.checkNotNullArgument(contentMode);

        switch (contentMode) {
            case TEXT:
                return ContentMode.TEXT;
            case PREFORMATTED:
                return ContentMode.PREFORMATTED;
            case HTML:
                return ContentMode.HTML;
            default:
                throw new IllegalArgumentException("Unknown content mode: " + contentMode);
        }
    }
}
