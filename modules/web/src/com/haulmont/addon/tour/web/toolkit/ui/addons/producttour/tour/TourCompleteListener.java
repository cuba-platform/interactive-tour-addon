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

public interface TourCompleteListener extends Serializable {

  Method COMPLETE_METHOD = ReflectTools.findMethod(TourCompleteListener.class,
                                                   "onComplete",
                                                   CompleteEvent.class);

  /**
   * Fired if a {@link Tour} is completed.
   *
   * @param event
   *     An provider containing information about the completion
   */
  void onComplete(CompleteEvent event);

  /**
   * Event class that contains information about completion.
   */
  class CompleteEvent extends TourEvent {

    public CompleteEvent(Tour source) {
      super(source);
    }
  }
}
