/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

public interface TourProvider {

    /**
     * Shortcut method to get the tour of the provider.
     *
     * @return the tour or <code>null</code> if the button that caused the provider is not attached to
     * any step that is attached to a tour
     */
    Tour getTour();
}