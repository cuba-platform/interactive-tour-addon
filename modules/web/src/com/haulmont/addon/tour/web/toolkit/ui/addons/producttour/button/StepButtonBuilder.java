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
package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button;

/**
 * Simplify the creation of a {@link com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step}
 */
public class StepButtonBuilder {

  private final StepButton stepButton;

  /**
   * Create new step button builder
   */
  public StepButtonBuilder(String caption) {
    stepButton = new StepButton(caption);
  }

  /**
   * Convenience method calling {@link StepButton#setStyleName(String)}
   */
  public StepButtonBuilder withStyle(String style) {
    stepButton.setStyleName(style);
    return this;
  }

  /**
   * Convenience method calling {@link StepButton#setEnabled(boolean)}
   */
  public StepButtonBuilder withEnabled(boolean enabled) {
    stepButton.setEnabled(enabled);
    return this;
  }

  /**
   * Convenience method calling {@link StepButton#addClickListener(StepButtonClickListener)}
   */
  public StepButtonBuilder addClickListener(StepButtonClickListener listener) {
    stepButton.addClickListener(listener);
    return this;
  }

  /**
   * Build the button.
   *
   * @return The button
   */
  public StepButton build() {
    return stepButton;
  }
}
