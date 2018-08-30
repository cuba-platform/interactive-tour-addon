/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.cuba.gui.components.Component;

import javax.annotation.Nullable;
import java.util.EventObject;
import java.util.List;
import java.util.function.Consumer;

/**
 * A single step of a tour.
 *
 * @see Tour
 */
public interface Step {

    /**
     * Gets client specific component instance. Can be used in client module to simplify invocation of underlying API.
     *
     * @param internalClass class of underlying component implementation
     * @param <X>           type of internal class
     * @return internal client specific component
     */
    <X> X unwrap(Class<X> internalClass);

    /**
     * Adds the step to the given tour.
     * Use {@link Tour#addStep(Step)} instead.
     *
     * @param tour the tour the step should be added to
     */
    void setTour(Tour tour);

    /**
     * Gets the tour this step is added to.
     *
     * @return the tour
     */
    Tour getTour();

    /**
     * Sets the step size to undefined.
     */
    void setSizeUndefined();

    /**
     * Sets the step size to 100 percentage.
     */
    void setSizeFull();

    /**
     * Sets the title of the step.
     *
     * @param title the title to be set
     */
    void setTitle(String title);

    /**
     * Gets the title of the step.
     *
     * @return the title of the step
     */
    String getTitle();

    /**
     * Sets the text of the step.
     *
     * @param text the text to be set
     */
    void setText(String text);

    /**
     * Gets the text of the step.
     *
     * @return the text of the step
     */
    String getText();

    /**
     * Checks if the step is currently visible.
     *
     * @return <code>true</code> if the step is currently visible, <code>false</code> otherwise
     */
    boolean isVisible();

    /**
     * Sets the step width.
     *
     * @param width the width
     */
    void setWidth(String width);

    /**
     * Gets the step width.
     *
     * @return the width
     */
    float getWidth();

    /**
     * Sets the step height.
     *
     * @param height the height
     */
    void setHeight(String height);

    /**
     * Gets the step height.
     *
     * @return the height
     */
    float getHeight();

    /**
     * Gets the step height units.
     *
     * @return the height units
     */
    int getHeightUnits();

    /**
     * Gets the step width units.
     *
     * @return the width units
     */
    int getWidthUnits();

    /**
     * Gets the id of the step.
     *
     * @return the id of the step
     */
    String getId();

    /**
     * Gets the component the step is attached to.
     *
     * @return the component or <code>null</code> if the step is not attached to any component
     */
    Component getAttachedTo();

    /**
     * Sets the component the step will be attached to.
     * <p>
     * If set to <code>null</code>, the step will not be attached and shown in the middle of the
     * screen.
     *
     * @param component the component to attach the step to or <code>null</code>
     * @see #setDetached()
     */
    void setAttachedTo(Component component);

    /**
     * Sets the step to be not attached to any component. The step will then be shown in the middle of
     * the screen. This is equal to calling {@link #setAttachedTo(Component)} with
     * <code>null</code> as parameter.
     */
    void setDetached();

    /**
     * Sets the cancellable state of the step.
     *
     * @param cancellable <code>true</code> if the step should be cancellable, <code>false</code> otherwise
     */
    void setCancellable(boolean cancellable);

    /**
     * Gets the cancellable state of the step.
     *
     * @return <code>true</code> if the step is cancellable, <code>false</code> otherwise
     */
    boolean isCancellable();

    /**
     * Sets the modality of the step.
     *
     * @param modal <code>true</code> if the step should be modal, <code>false</code> otherwise
     */
    void setModal(boolean modal);

    /**
     * Gets the modal state of the step.
     *
     * @return <code>true</code> if the step is modal, <code>false</code> otherwise
     */
    boolean isModal();

    /**
     * Sets the scrollTo state of the step.
     *
     * @param scrollTo <code>true</code> if the step should be scrolled into view when shown, <code>false</code> otherwise
     */
    void setScrollTo(boolean scrollTo);

    /**
     * Gets the scrollTo state of the step.
     *
     * @return <code>true</code> if the step is scrolled to when shown, <code>false</code> otherwise
     */
    boolean isScrollTo();

    /**
     * Sets the content mode for the text of the step.
     *
     * @param contentMode the content mode to be set
     */
    void setTextContentMode(ContentMode contentMode);

    /**
     * Gets the content mode for the text of the step.
     *
     * @return the content mode for the text of the step
     */
    ContentMode getTextContentMode();

    /**
     * Sets the content mode for the title of the step.
     *
     * @param contentMode the content mode to be set
     */
    void setTitleContentMode(ContentMode contentMode);

    /**
     * Gets the content mode for the title of the step.
     *
     * @return the content mode for the title of the step
     */
    ContentMode getTitleContentMode();

    /**
     * Sets the anchor the step is shown relative to the component it is attached to.
     *
     * @param anchor the anchor to be set
     */
    void setAnchor(StepAnchor anchor);

    /**
     * Gets the anchor the step is shown relative to the component it is attached to.
     *
     * @return the anchor of the step
     */
    StepAnchor getAnchor();

    /**
     * Gets the buttons of the step.
     *
     * @return the unmodifiable list of step buttons
     */
    List<StepButton> getButtons();

    /**
     * Gets a button by its index.
     *
     * @param index the index of the button to get
     * @return the button at the given index
     */
    StepButton getButtonByIndex(int index);

    /**
     * Gets the count of buttons of this step.
     *
     * @return the count of buttons of this step
     */
    int getButtonCount();

    /**
     * Adds a button the step. The button will be shown in the order they are added.
     *
     * @param button the button to be added
     */
    void addButton(StepButton button);

    /**
     * Removes a button from the step.
     *
     * @param button the button to be removed
     */
    void removeButton(StepButton button);

    /**
     * Hides this step and trigger the cancel provider.
     */
    void cancel();

    /**
     * Hides this step and trigger the complete provider.
     */
    void complete();

    /**
     * Hides this step.
     */
    void hide();

    /**
     * Shows this step.
     */
    void show();

    /**
     * Scrolls to this steps element.
     */
    void scrollTo();

    /**
     * Contains possible anchors of a step.
     */
    enum StepAnchor {
        TOP("top"),
        RIGHT("right"),
        BOTTOM("bottom"),
        LEFT("left");

        private String id;

        StepAnchor(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Nullable
        public static StepAnchor fromId(String id) {
            StepAnchor[] values = StepAnchor.values();
            for (StepAnchor anchor : values) {
                if (anchor.getId().equals(id)) {
                    return anchor;
                }
            }
            return null;

        }
    }

    /**
     * Contains possible content mode of step text and title.
     */
    enum ContentMode {

        /**
         * Content mode, where the step contains only plain text.
         */
        TEXT("text"),

        /**
         * Content mode, where the step contains preformatted text. In this mode
         * newlines are preserved when rendered on the screen.
         */
        PREFORMATTED("preformatted"),

        /**
         * Content mode, where the step contains HTML.
         */
        HTML("html");

        private String id;

        ContentMode(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Nullable
        public static ContentMode fromId(String id) {
            ContentMode[] values = ContentMode.values();
            for (ContentMode mode : values) {
                if (mode.getId().equals(id)) {
                    return mode;
                }
            }
            return null;

        }
    }

    /**
     * Adds the given listener to the step that will be triggered if the step is cancelled.
     *
     * @param listener the listener to be added
     */
    void addCancelListener(Consumer<CancelEvent> listener);

    /**
     * Removes the given listener from the step.
     *
     * @param listener the listener to be removed
     */
    void removeCancelListener(Consumer<CancelEvent> listener);

    /**
     * Event class that contains information about cancellation.
     */
    class CancelEvent extends StepEvent {

        public CancelEvent(Step source) {
            super(source);
        }
    }

    /**
     * Adds the given listener to the step that will be triggered if the step is completed.
     *
     * @param listener the listener to be added
     */
    void addCompleteListener(Consumer<CompleteEvent> listener);

    /**
     * Removes the given listener from the step.
     *
     * @param listener the listener to be removed
     */
    void removeCompleteListener(Consumer<CompleteEvent> listener);

    /**
     * Event class that contains information about completion.
     */
    class CompleteEvent extends StepEvent {

        public CompleteEvent(Step source) {
            super(source);
        }
    }

    /**
     * Adds the given listener to the step that will be triggered if the step is hidden.
     *
     * @param listener the listener to be added
     */
    void addHideListener(Consumer<HideEvent> listener);

    /**
     * Removes the given listener from the step.
     *
     * @param listener the listener to be removed.
     */
    void removeHideListener(Consumer<HideEvent> listener);

    /**
     * Event class that contains information about hiding.
     */
    class HideEvent extends StepEvent {

        public HideEvent(Step source) {
            super(source);
        }
    }

    /**
     * Adds the given listener to the step that will be triggered if the step is shown.
     *
     * @param listener the listener to be added
     */
    void addShowListener(Consumer<ShowEvent> listener);

    /**
     * Removes the given listener from the step.
     *
     * @param listener the listener to be removed
     */
    void removeShowListener(Consumer<ShowEvent> listener);

    /**
     * Event class that contains information about showing.
     */
    class ShowEvent extends StepEvent {

        public ShowEvent(Step source) {
            super(source);
        }
    }

    /**
     * Base class for all events that were caused by a {@link Step}.
     */
    class StepEvent extends EventObject implements TourProvider, StepProvider {

        /**
         * Constructs a new provider.
         *
         * @param source the source of the provider
         */
        public StepEvent(Step source) {
            super(source);
        }

        @Override
        public Step getStep() {
            return (Step) getSource();
        }

        @Override
        public Tour getTour() {
            Step step = getStep();
            return step != null ? step.getTour() : null;
        }
    }

}
