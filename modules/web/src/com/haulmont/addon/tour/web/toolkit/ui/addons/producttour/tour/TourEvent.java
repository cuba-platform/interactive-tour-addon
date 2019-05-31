/*
 * Copyright 2017 Julien Charpenel
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour;

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.provider.TourProvider;
import com.vaadin.event.ConnectorEvent;

/**
 * Base class for all events that were caused by a {@link Tour}.
 */
public class TourEvent extends ConnectorEvent implements TourProvider {

  /**
   * Construct a new provider.
   *
   * @param source
   *     The source of the provider
   */
  public TourEvent(Tour source) {
    super(source);
  }

  /**
   * Get the tour that is the source of the provider.
   *
   * @return The tour that caused the provider
   */
  @Override
  public Tour getTour() {
    return (Tour) getSource();
  }
}
