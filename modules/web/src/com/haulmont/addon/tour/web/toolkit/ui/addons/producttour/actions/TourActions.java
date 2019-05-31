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

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.provider.TourProvider;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour;

import java.util.Optional;

/**
 * Contains shortcut actions for cleaner code
 */
public final class TourActions {

  /**
   * Please use directly the static methods.
   */
  private TourActions() {
    // Prevent instantiation
  }

  public static void back(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::back);
  }

  public static void cancel(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::cancel);
  }

  public static void hide(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::hide);
  }

  public static void next(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::next);
  }

  public static void start(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::start);
  }
}
