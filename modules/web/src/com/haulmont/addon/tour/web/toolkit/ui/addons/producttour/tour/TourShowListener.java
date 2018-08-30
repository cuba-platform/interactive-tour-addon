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

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface TourShowListener extends Serializable {

  Method SHOW_METHOD = ReflectTools.findMethod(TourShowListener.class,
                                               "onShow",
                                               ShowEvent.class);

  /**
   * Fired if a step of a {@link Tour} is shown.
   *
   * @param event
   *     An provider containing information about the showing
   */
  void onShow(ShowEvent event);

  /**
   * Event class that contains information about showing.
   */
  class ShowEvent extends TourEvent {

    private final Step previousStep;
    private final Step currentStep;

    public ShowEvent(Tour source, Step previousStep, Step currentStep) {
      super(source);
      this.previousStep = previousStep;
      this.currentStep = currentStep;
    }

    public Step getPreviousStep() {
      return previousStep;
    }

    public Step getCurrentStep() {
      return currentStep;
    }
  }
}
