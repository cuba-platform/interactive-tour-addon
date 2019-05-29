package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step;

import com.vaadin.event.ConnectorEvent;

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.provider.StepProvider;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.provider.TourProvider;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour;

/**
 * Base class for all events that were caused by a {@link Step}.
 */
public class StepEvent extends ConnectorEvent implements StepProvider, TourProvider {

  /**
   * Construct a new provider.
   *
   * @param source
   *     The source of the provider
   */
  public StepEvent(Step source) {
    super(source);
  }

  /**
   * Shortcut method to get the tour of the provider.
   *
   * @return The tour or <code>null</code> if the step that caused the provider is not attached to
   * any tour.
   */
  @Override
  public Tour getTour() {
    com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step step = getStep();
    return step != null ? step.getTour() : null;
  }

  /**
   * Get the step that is the source of the provider.
   *
   * @return The step that caused the provider
   */
  @Override
  public Step getStep() {
    return (Step) getSource();
  }
}
