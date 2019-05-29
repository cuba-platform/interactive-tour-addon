package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface TourHideListener extends Serializable {

  Method HIDE_METHOD = ReflectTools.findMethod(TourHideListener.class,
                                               "onHide",
                                               HideEvent.class);

  /**
   * Fired if a {@link Tour} is hidden.
   *
   * @param event
   *     An provider containing information about the hiding
   */
  void onHide(HideEvent event);

  /**
   * Event class that contains information about hiding.
   */
  class HideEvent extends TourEvent {

    public HideEvent(Tour source) {
      super(source);
    }
  }
}
