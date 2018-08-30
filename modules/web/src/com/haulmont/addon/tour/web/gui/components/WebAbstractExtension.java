/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

import com.vaadin.server.AbstractExtension;

/**
 * Base class for web extensions.
 */
public abstract class WebAbstractExtension<T extends AbstractExtension> {

    public WebAbstractExtension() {
    }

    protected T extension;

    public <X> X unwrap(Class<X> internalClass) {
        return internalClass.cast(getExtension());
    }

    /**
     * Gets the component extension.
     *
     * @return the component extension
     */
    public T getExtension() {
        return extension;
    }

    /**
     * Initializes a component extension.
     *
     * @param extension The extension
     */
    protected abstract void initExtension(T extension);
}