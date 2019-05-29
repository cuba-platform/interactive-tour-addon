package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface TourCancelListener extends Serializable {

  Method CANCEL_METHOD = ReflectTools.findMethod(TourCancelListener.class,
                                                 "onCancel",
                                                 CancelEvent.class);

  /**
   * Fired if a {@link Tour} is cancelled.
   *
   * @param event
   *     An provider containing information about the cancellation
   */
  void onCancel(CancelEvent event);

  /**
   * Event class that contains information about cancellation.
   */
  class CancelEvent extends TourEvent {

    public CancelEvent(Tour source) {
      super(source);
    }
  }
}
