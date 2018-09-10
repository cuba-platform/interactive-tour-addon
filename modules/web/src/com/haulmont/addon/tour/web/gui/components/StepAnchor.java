package com.haulmont.addon.tour.web.gui.components;

import javax.annotation.Nullable;

/**
 * Contains possible anchors of a step.
 */
public enum StepAnchor {
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
