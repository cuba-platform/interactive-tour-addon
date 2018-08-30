/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Enumerates standard tour action types. Can create a corresponding action instance with default parameters.
 */
public enum TourActionType {

    BACK("back") {
        @Override
        public void execute(TourProvider provider) {
            Optional.ofNullable(provider.getTour()).ifPresent(Tour::back);
        }
    },

    CANCEL("cancel") {
        @Override
        public void execute(TourProvider provider) {
            Optional.ofNullable(provider.getTour()).ifPresent(Tour::cancel);
        }
    },

    HIDE("hide") {
        @Override
        public void execute(TourProvider provider) {
            Optional.ofNullable(provider.getTour()).ifPresent(Tour::hide);
        }
    },

    NEXT("next") {
        @Override
        public void execute(TourProvider provider) {
            Optional.ofNullable(provider.getTour()).ifPresent(Tour::next);
        }
    },

    START("start") {
        @Override
        public void execute(TourProvider provider) {
            Optional.ofNullable(provider.getTour()).ifPresent(Tour::start);
        }
    };

    private String id;

    TourActionType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TourActionType fromId(String id) {
        TourActionType[] values = TourActionType.values();
        for (TourActionType actionType : values) {
            if (actionType.getId().equals(id)) {
                return actionType;
            }
        }
        return null;

    }

    public abstract void execute(TourProvider provider);
}