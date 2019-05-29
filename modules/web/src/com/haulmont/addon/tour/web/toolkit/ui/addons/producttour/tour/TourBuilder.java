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
