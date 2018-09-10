/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.addon.tour.web.gui.components;

/**
 * Base class for web extensions.
 */
public abstract class AbstractExtension<T extends com.vaadin.server.AbstractExtension> {

    public AbstractExtension() {
    }

    protected T extension;

    /**
     * Gets client specific component instance. Can be used in client module to simplify invocation of underlying API.
     *
     * @param internalClass class of underlying component implementation
     * @param <X>           type of internal class
     * @return internal client specific component
     */
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