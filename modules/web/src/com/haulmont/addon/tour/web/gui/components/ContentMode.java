package com.haulmont.addon.tour.web.gui.components;

import javax.annotation.Nullable;

/**
 * Contains possible content mode of step text and title.
 */
public enum ContentMode {
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
