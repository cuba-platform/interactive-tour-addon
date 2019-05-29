package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.actions;

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.provider.TourProvider;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour;

import java.util.Optional;

/**
 * Contains shortcut actions for cleaner code
 */
public class TourActions {

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
