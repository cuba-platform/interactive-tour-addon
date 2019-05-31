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

/**
 * Simplify the creation of a {@link Tour}
 */
public class TourBuilder {

  private final Tour tour;

  /**
   * Create new tour builder
   */
  public TourBuilder() {
    tour = new Tour();
  }

  /**
   * Convenience method calling {@link Tour#addStep(Step)}
   */
  public TourBuilder withStep(Step step) {
    tour.addStep(step);
    return this;
  }

  /**
   * Convenience method calling {@link Tour#addCancelListener(TourCancelListener)}
   */
  public TourBuilder addCancelListener(TourCancelListener listener) {
    tour.addCancelListener(listener);
    return this;
  }

  /**
   * Convenience method calling {@link Tour#addCompleteListener(TourCompleteListener)}
   */
  public TourBuilder addCompleteListener(TourCompleteListener listener) {
    tour.addCompleteListener(listener);
    return this;
  }

  /**
   * Convenience method calling {@link Tour#addHideListener(TourHideListener)}
   */
  public TourBuilder addHideListener(TourHideListener listener) {
    tour.addHideListener(listener);
    return this;
  }

  /**
   * Convenience method calling {@link Tour#addShowListener(TourShowListener)}
   */
  public TourBuilder addShowListener(TourShowListener listener) {
    tour.addShowListener(listener);
    return this;
  }

  /**
   * Convenience method calling {@link Tour#addStartListener(TourStartListener)}
   */
  public TourBuilder addStartListener(TourStartListener listener) {
    tour.addStartListener(listener);
    return this;
  }

  public Tour build() {
    return tour;
  }
}
