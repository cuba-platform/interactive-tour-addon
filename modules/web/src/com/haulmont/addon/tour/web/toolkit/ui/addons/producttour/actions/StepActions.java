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
