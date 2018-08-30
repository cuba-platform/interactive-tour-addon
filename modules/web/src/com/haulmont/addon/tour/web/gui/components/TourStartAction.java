/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

import com.haulmont.bali.util.Preconditions;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.ClientType;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.security.app.UserSettingService;
import org.springframework.context.annotation.Scope;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Standard action to start a tour.
 * Use {@code create()} static methods instead of constructors when creating the action programmatically.
 */
@org.springframework.stereotype.Component("cuba_TourStartAction")
@Scope("prototype")
public class TourStartAction extends BaseAction {

    public static final String ACTION_ID = "tourStart";

    protected Tour tour;

    protected boolean settingsEnabled = true;

    @Inject
    protected UserSettingService userSettingService;

    /**
     * Creates an action with the given id and tour.
     *
     * @param id   action's identifier
     * @param tour the tour to start
     * @return the action
     */
    public static TourStartAction create(String id, Tour tour) {
        return AppBeans.getPrototype("cuba_TourStartAction", id, tour);
    }

    /**
     * Creates an action with the given tour.
     *
     * @param tour the tour to start
     * @return the action
     */
    public static TourStartAction create(Tour tour) {
        return AppBeans.getPrototype("cuba_TourStartAction", ACTION_ID, tour);
    }

    /**
     * Creates an action with the given id, tour and shortcut.
     *
     * @param id       action's identifier
     * @param shortcut the shortcut to start the tour
     * @param tour     the tour to start
     * @return the action
     */
    public static TourStartAction create(String id, @Nullable String shortcut, Tour tour) {
        return AppBeans.getPrototype("cuba_TourStartAction", id, shortcut, tour);
    }

    /**
     * Constructs an action with given id and tour.
     *
     * @param id   action's identifier
     * @param tour the tour to start
     */
    public TourStartAction(String id, Tour tour) {
        super(id);
        this.tour = tour;
    }

    /**
     * Constructs an action with given id, tour and shortcut.
     *
     * @param id       action's identifier
     * @param shortcut the shortcut to start the tour
     * @param tour     the tour to start
     */
    protected TourStartAction(String id, @Nullable String shortcut, Tour tour) {
        super(id, shortcut);
        this.tour = tour;
    }

    /**
     * Enables/Disables settings.
     */
    public void setSettingsEnabled(boolean settingsEnabled) {
        this.settingsEnabled = settingsEnabled;
    }

    /**
     * Gets the settings state.
     *
     * @return the state
     */
    public boolean isSettingsEnabled() {
        return settingsEnabled;
    }

    /**
     * This method is invoked by the action owner component.
     *
     * @param component component invoking the action
     */
    @Override
    public void actionPerform(Component component) {
        Preconditions.checkNotNullArgument(component);

        if (settingsEnabled) {
            String componentId = component.getId();
            String settingId = createSettingId(componentId);
            String startAction = userSettingService.loadSetting(ClientType.WEB, settingId);

            if (startAction == null) {
                startTour();
                userSettingService.saveSetting(ClientType.WEB, settingId, "Complete");
            }
        } else {
            startTour();
        }
    }

    /**
     * Creates setting id.
     *
     * @param componentId prefix
     * @return the setting id
     */
    protected String createSettingId(String componentId) {
        return componentId + ":" + ACTION_ID;
    }

    /**
     * Starts the tour.
     */
    protected void startTour() {
        if (tour.getCurrentStep() != null) {
            tour.cancel();
        }
        tour.start();
    }
}