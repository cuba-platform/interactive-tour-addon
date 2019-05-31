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

public interface TourCancelListener extends Serializable {

  Method CANCEL_METHOD = ReflectTools.findMethod(TourCancelListener.class,
                                                 "onCancel",
                                                 CancelEvent.class);

  /**
   * Fired if a {@link Tour} is cancelled.
   *
   * @param event
   *     An provider containing information about the cancellation
   */
  void onCancel(CancelEvent event);

  /**
   * Event class that contains information about cancellation.
   */
  class CancelEvent extends TourEvent {

    public CancelEvent(Tour source) {
      super(source);
    }
  }
}
