package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface TourCompleteListener extends Serializable {

  Method COMPLETE_METHOD = ReflectTools.findMethod(TourCompleteListener.class,
                                                   "onComplete",
                                                   CompleteEvent.class);

  /**
   * Fired if a {@link Tour} is completed.
   *
   * @param event
   *     An provider containing information about the completion
   */
  void onComplete(CompleteEvent event);

  /**
   * Event class that contains information about completion.
   */
  class CompleteEvent extends TourEvent {

    public CompleteEvent(Tour source) {
      super(source);
    }
  }
}
