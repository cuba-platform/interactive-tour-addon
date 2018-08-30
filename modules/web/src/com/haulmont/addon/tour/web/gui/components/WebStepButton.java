/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.bali.util.Preconditions;
import com.haulmont.cuba.gui.components.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WebStepButton extends
        WebAbstractExtension<com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton> implements
        StepButton {

    protected com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButtonClickListener stepButtonClickListener;

    protected Step step;

    protected List<Consumer<ClickEvent>> listenerList = null;

    /**
     * Constructs a new button with the given caption.
     *
     * @param caption The caption of the button
     */
    public WebStepButton(String caption) {
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

    @Override
    public void addStepButtonClickListener(Consumer<ClickEvent> clickListener) {
        if (listenerList == null) {
            listenerList = new ArrayList<>();

            this.stepButtonClickListener =
                    (com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButtonClickListener) event -> {
                Component.MouseEventDetails details = new Component.MouseEventDetails();
                details.setClientX(event.getClientX());
                details.setClientY(event.getClientY());
                details.setRelativeY(event.getRelativeY());
                details.setRelativeX(event.getRelativeX());
                ClickEvent e = new ClickEvent(WebStepButton.this, details);
                for (Consumer<ClickEvent> clickEventConsumer : listenerList) {
                    clickEventConsumer.accept(e);
                }
            };
            extension.addClickListener(this.stepButtonClickListener);
        }

        if (!listenerList.contains(clickListener)) {
            listenerList.add(clickListener);
        }
    }

    @Override
    public void removeStepButtonClickListener(Consumer<ClickEvent> clickListener) {
        if (listenerList != null) {
            listenerList.remove(clickListener);

            if (listenerList.isEmpty()) {
                listenerList = null;
                extension.removeClickListener(this.stepButtonClickListener);
                this.stepButtonClickListener = null;
            }
        }
    }

    @Override
    public Step getStep() {
        return step;
    }

    @Override
    public void setStep(Step step) {
        com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step vaadinStep =
                step.unwrap(com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step.class);
        extension.setStep(vaadinStep);
        this.step = step;
    }

    @Override
    public String getCaption() {
        return extension.getCaption();
    }

    @Override
    public void setCaption(String caption) {
        extension.setCaption(caption);
    }

    @Override
    public boolean isEnabled() {
        return extension.isEnabled();
    }

    @Override
    public void setEnabled(boolean enabled) {
        extension.setEnabled(enabled);
    }

    @Override
    public void addStyleName(String style) {
        extension.addStyleName(style);
    }

    @Override
    public void removeStyleName(String style) {
        extension.removeStyleName(style);
    }

    @Override
    public String getStyleName() {
        return extension.getStyleName();
    }

    @Override
    public void setStyleName(String style) {
        extension.setStyleName(style);
    }
}