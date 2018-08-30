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
package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface StepShowListener extends Serializable {

  Method SHOW_METHOD = ReflectTools.findMethod(StepShowListener.class,
                                               "onShow",
                                               ShowEvent.class);

  /**
   * Fired if a {@link Step} is shown.
   *
   * @param event
   *     An provider containing information about the showing
   */
  void onShow(ShowEvent event);

  /**
   * Event class that contains information about showing.
   */
  class ShowEvent extends StepEvent {

    public ShowEvent(Step source) {
      super(source);
    }
  }
}
