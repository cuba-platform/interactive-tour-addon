package com.haulmont.addon.tour.web.gui.components;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Enumerates standard step action types. Can create a corresponding action instance with default parameters.
 */
public enum StepActionType {

    CANCEL("cancel") {
        @Override
        public void execute(StepProvider provider) {
            Optional.ofNullable(provider.getStep()).ifPresent(Step::cancel);
        }
    },

    COMPLETE("complete") {
        @Override
        public void execute(StepProvider provider) {
            Optional.ofNullable(provider.getStep()).ifPresent(Step::complete);
        }
    },

    HIDE("hide") {
        @Override
        public void execute(StepProvider provider) {
            Optional.ofNullable(provider.getStep()).ifPresent(Step::hide);
        }
    },

    SCROLLTO("scrollTo") {
        @Override
        public void execute(StepProvider provider) {
            Optional.ofNullable(provider.getStep()).ifPresent(Step::scrollTo);
        }
    },

    SHOW("show") {
        @Override
        public void execute(StepProvider provider) {
            Optional.ofNullable(provider.getStep()).ifPresent(Step::show);
        }
    };

    private String id;

    StepActionType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static StepActionType fromId(String id) {
        StepActionType[] values = StepActionType.values();
        for (StepActionType actionType : values) {
            if (actionType.getId().equals(id)) {
                return actionType;
            }
        }
        return null;

    }

    public abstract void execute(StepProvider provider);
}