/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.utils;


import com.google.gson.Gson;
import com.haulmont.addon.tour.web.gui.components.*;
import com.haulmont.bali.util.Preconditions;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.ComponentsHelper;
import com.haulmont.cuba.gui.GuiDevelopmentException;
import com.haulmont.cuba.gui.components.Window;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Parser of {@link Tour} objects
 */
@Component("scenarioaddon_TourParser")
@Scope("prototype")
public class TourParser {

    protected static Gson gson = new Gson();

    @Inject
    protected Messages messages;

    protected String messagesPack;

    protected Window windowToExtend;

    /**
     * Creates a tour from a given json extending a given window using a given messages pack.
     *
     * @param json           the json file
     * @param messagesPack   the messages pack
     * @param windowToExtend the window to extend
     * @return the tour
     */
    public Tour parseTour(String json, String messagesPack, Window windowToExtend) {

        Preconditions.checkNotNullArgument(windowToExtend, "windowToExtend is required!");

        initTourParser(messagesPack, windowToExtend);

        Tour tour = createTour(windowToExtend);
        loadTour(json, tour);
        return tour;
    }

    /**
     * Initializes a tour extending the given window.
     *
     * @param windowToExtend the window to extend
     * @return the tour
     */
    protected Tour createTour(Window windowToExtend) {
        return new WebTour(windowToExtend);
    }

    /**
     * Initialize the tour with given messages pack and window.
     *
     * @param messagesPack   the messages pack
     * @param windowToExtend the window
     */
    protected void initTourParser(String messagesPack, Window windowToExtend) {
        this.messagesPack = messagesPack;
        this.windowToExtend = windowToExtend;
    }

    /**
     * Load tour from the json file.
     *
     * @param json the json file
     * @param tour the loading tour
     */
    @SuppressWarnings("unchecked")
    protected void loadTour(String json, Tour tour) {
        ArrayList steps = gson.fromJson(json, ArrayList.class);
        for (Object step : steps) {
            Map<String, Object> stepMap = (Map<String, Object>) step;
            loadStep(stepMap, tour);
        }
    }

    /**
     * Load step from a step map for a tour.
     *
     * @param stepMap the step map
     * @param tour    the tour
     */
    protected void loadStep(Map<String, Object> stepMap, Tour tour) {
        Step step = createStepWithId(stepMap);

        loadText(stepMap, step);
        loadTitle(stepMap, step);
        loadTextContentMode(stepMap, step);
        loadTitleContentMode(stepMap, step);

        loadWidth(stepMap, step);
        loadHeight(stepMap, step);

        loadModal(stepMap, step);
        loadCancellable(stepMap, step);
        loadScrollTo(stepMap, step);

        loadAttachTo(stepMap, step);
        loadStepAnchor(stepMap, step);

        loadButtons(stepMap, step);

        tour.addStep(step);
    }

    /**
     * Load step buttons from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    @SuppressWarnings("unchecked")
    protected void loadButtons(Map<String, Object> stepMap, Step step) {
        if (stepMap.get("buttons") != null) {
            ArrayList buttons = (ArrayList) stepMap.get("buttons");

            for (Object button : buttons) {
                Map<String, String> buttonMap = (Map<String, String>) button;

                loadButton(buttonMap, step);
            }
        }
    }

    /**
     * Load step button from a button map for a step.
     *
     * @param buttonMap the button map
     * @param step      the step
     */
    protected void loadButton(Map<String, String> buttonMap, Step step) {
        StepButton stepButton = createStepButtonWithCaption(buttonMap);

        loadStyle(buttonMap, stepButton);
        loadEnabled(buttonMap, stepButton);
        loadDefaultButtonAction(buttonMap, stepButton);

        step.addButton(stepButton);
    }

    /**
     * Creates a step button with a caption loaded from a button map or with default value.
     *
     * @param buttonMap the button map
     * @return the step button
     */
    protected StepButton createStepButtonWithCaption(Map<String, String> buttonMap) {
        String caption = null;
        if (StringUtils.isNotEmpty(buttonMap.get("caption"))) {
            caption = buttonMap.get("caption");
            caption = loadResourceString(caption);
        }
        return new WebStepButton(caption);
    }

    /**
     * Load a click listener from a button map for a step button.
     *
     * @param buttonMap  the button map
     * @param stepButton the step button
     */
    protected void loadDefaultButtonAction(Map<String, String> buttonMap, StepButton stepButton) {
        if (StringUtils.isNotEmpty(buttonMap.get("action"))) {
            String action = buttonMap.get("action");

            Consumer<StepButton.ClickEvent> clickListener = getClickListener(action);
            if (clickListener == null) {
                throw new GuiDevelopmentException("Couldn't parse the action value!", windowToExtend.getFrame().getId());
            }
            stepButton.addStepButtonClickListener(clickListener);
        }
    }

    /**
     * Load an enabled value from a button map for a step button.
     *
     * @param buttonMap  the button map
     * @param stepButton the step button
     */
    protected void loadEnabled(Map<String, String> buttonMap, StepButton stepButton) {
        if (StringUtils.isNotEmpty(buttonMap.get("enabled"))) {
            String enabled = buttonMap.get("enabled");

            stepButton.setEnabled(Boolean.valueOf(enabled));
        }
    }

    /**
     * Load a style value from a button map for a step button.
     *
     * @param buttonMap  the button map
     * @param stepButton the step button
     */
    protected void loadStyle(Map<String, String> buttonMap, StepButton stepButton) {
        if (StringUtils.isNotEmpty(buttonMap.get("style"))) {
            String style = buttonMap.get("style");

            stepButton.setStyleName(style);
        }
    }

    /**
     * Creates a step with an id loaded from a step map or with random value.
     *
     * @param stepMap the step map
     * @return the step
     */
    protected Step createStepWithId(Map<String, Object> stepMap) {
        String id = null;
        if (StringUtils.isNotEmpty((String) stepMap.get("id"))) {
            id = (String) stepMap.get("id");
        }
        return new WebStep(id);
    }

    /**
     * Load an attachTo value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadAttachTo(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("attachTo"))) {
            String attachTo = (String) stepMap.get("attachTo");
            com.haulmont.cuba.gui.components.Component cubaComponent = getCubaComponent(attachTo);

            if (cubaComponent != null) {
                step.setAttachedTo(cubaComponent);
            } else {
                throw new GuiDevelopmentException("Couldn't parse the attachTo value!",
                        windowToExtend.getFrame().getId());
            }
        }
    }

    /**
     * Load a step anchor value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadStepAnchor(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("anchor"))) {
            String anchor = (String) stepMap.get("anchor");
            Step.StepAnchor stepAnchor = Step.StepAnchor.fromId(anchor);

            if (stepAnchor == null) {
                throw new GuiDevelopmentException("Couldn't parse the anchor value!",
                        windowToExtend.getFrame().getId());
            }

            step.setAnchor(stepAnchor);
        }
    }

    /**
     * Load a title content mode value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadTitleContentMode(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("titleContentMode"))) {
            String titleContentMode = (String) stepMap.get("titleContentMode");
            Step.ContentMode contentMode = Step.ContentMode.fromId(titleContentMode);

            if (contentMode == null) {
                throw new GuiDevelopmentException("Couldn't parse the titleContentMode value!",
                        windowToExtend.getFrame().getId());
            }

            step.setTitleContentMode(contentMode);
        }
    }

    /**
     * Load a text content mode value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadTextContentMode(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("textContentMode"))) {
            String textContentMode = (String) stepMap.get("textContentMode");
            Step.ContentMode contentMode = Step.ContentMode.fromId(textContentMode);

            if (contentMode == null) {
                throw new GuiDevelopmentException("Couldn't parse the textContentMode value!",
                        windowToExtend.getFrame().getId());
            }

            step.setTextContentMode(contentMode);
        }
    }

    /**
     * Load a scrollTo value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadScrollTo(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("scrollTo"))) {
            String scrollTo = (String) stepMap.get("scrollTo");

            step.setScrollTo(Boolean.valueOf(scrollTo));
        }
    }

    /**
     * Load a cancellable value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadCancellable(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("cancellable"))) {
            String cancellable = (String) stepMap.get("cancellable");

            step.setCancellable(Boolean.valueOf(cancellable));
        }
    }

    /**
     * Load a modal value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadModal(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("modal"))) {
            String modal = (String) stepMap.get("modal");

            step.setModal(Boolean.valueOf(modal));
        }
    }

    /**
     * Load a height value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadHeight(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("height"))) {
            String height = (String) stepMap.get("height");

            step.setHeight(height);
        }
    }

    /**
     * Load a width value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadWidth(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("width"))) {
            String width = (String) stepMap.get("width");

            step.setWidth(width);
        }
    }

    /**
     * Load a title value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadTitle(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("title"))) {
            String title = (String) stepMap.get("title");

            title = loadResourceString(title);
            step.setTitle(title);
        }
    }

    /**
     * Load a text value from a step map for a step.
     *
     * @param stepMap the step map
     * @param step    the step
     */
    protected void loadText(Map<String, Object> stepMap, Step step) {
        if (StringUtils.isNotEmpty((String) stepMap.get("text"))) {
            String text = (String) stepMap.get("text");

            text = loadResourceString(text);
            step.setText(text);
        }
    }

    /**
     * Load a string from a messages pack by a string key.
     *
     * @param stringKey the string key
     * @return the string
     */
    @Nullable
    protected String loadResourceString(String stringKey) {
        if (messagesPack != null) {
            return messages.getMessage(messagesPack, stringKey);
        }

        return stringKey;
    }

    /**
     * Get CUBA component from an windowToExtend by a component id.
     *
     * @param attachId the component id
     * @return the CUBA component
     */
    @Nullable
    protected com.haulmont.cuba.gui.components.Component getCubaComponent(String attachId) {
        return ComponentsHelper.findComponent(windowToExtend.getFrame(), attachId);
    }

    /**
     * Get a click listener by an action string.
     *
     * @param action the full action string
     * @return the click listener
     */
    @Nullable
    protected Consumer<StepButton.ClickEvent> getClickListener(String action) {
        String[] split = action.split(":");

        if (split.length == 2) {

            String actionId = split[1];
            if (split[0].equals("tour")) {
                TourActionType actionType = TourActionType.fromId(actionId);
                if (actionType == null) {
                    return null;
                }
                return actionType::execute;

            }

            if (split[0].equals("step")) {
                StepActionType stepActionType = StepActionType.fromId(actionId);
                if (stepActionType == null) {
                    return null;
                }
                return stepActionType::execute;
            }

        }
        return null;
    }
}
