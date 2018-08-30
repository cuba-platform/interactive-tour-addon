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
package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.actions;

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.provider.StepProvider;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step;

import java.util.Optional;

/**
 * Contains shortcut actions for cleaner code
 */
public class StepActions {

  /**
   * Please use directly the static methods.
   */
  private StepActions() {
    // Prevent instantiation
  }

  public static void cancel(StepProvider provider) {
    Optional.ofNullable(provider.getStep()).ifPresent(Step::cancel);
  }

  public static void complete(StepProvider provider) {
    Optional.ofNullable(provider.getStep()).ifPresent(Step::complete);
  }

  public static void hide(StepProvider provider) {
    Optional.ofNullable(provider.getStep()).ifPresent(Step::hide);
  }

  public static void scrollTo(StepProvider provider) {
    Optional.ofNullable(provider.getStep()).ifPresent(Step::scrollTo);
  }

  public static void show(StepProvider provider) {
    Optional.ofNullable(provider.getStep()).ifPresent(Step::show);
  }
}
