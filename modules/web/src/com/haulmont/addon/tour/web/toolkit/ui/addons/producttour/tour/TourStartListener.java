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

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface TourStartListener extends Serializable {

  Method TOUR_STARTED_METHOD = ReflectTools.findMethod(TourStartListener.class,
                                                       "onStart",
                                                       StartEvent.class);

  /**
   * Fired if a {@link Tour} is started.
   *
   * @param event
   *     An provider containing information about the starting
   */
  void onStart(StartEvent event);

  /**
   * Event class that contains information about starting.
   */
  class StartEvent extends TourEvent {

    public StartEvent(Tour source) {
      super(source);
    }
  }
}
