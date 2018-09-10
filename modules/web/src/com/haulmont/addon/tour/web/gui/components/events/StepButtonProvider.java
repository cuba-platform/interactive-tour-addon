/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components.events;

import com.haulmont.addon.tour.web.gui.components.StepButton;

public interface StepButtonProvider {

    /**
     * Gets the button that is the source of the provider.
     *
     * @return the button that caused the provider
     */
    StepButton getStepButton();
}