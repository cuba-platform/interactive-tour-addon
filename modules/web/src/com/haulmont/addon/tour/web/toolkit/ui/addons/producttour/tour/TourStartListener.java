package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface TourStartListener extends Serializable {

  Method TOUR_STARTED_METHOD = ReflectTools.findMethod(TourStartListener.class,
                                                       "onStart",
                                                       StartEvent.class);

  /**
   * Fired if a {@link Tour} is started.
   *
   * @param event
   *     An provider containing information about the starting
   */
  void onStart(StartEvent event);

  /**
   * Event class that contains information about starting.
   */
  class StartEvent extends TourEvent {

    public StartEvent(Tour source) {
      super(source);
    }
  }
}
