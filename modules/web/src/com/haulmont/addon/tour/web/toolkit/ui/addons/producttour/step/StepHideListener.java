package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface StepHideListener extends Serializable {

  Method HIDE_METHOD = ReflectTools.findMethod(StepHideListener.class,
                                               "onHide",
                                               HideEvent.class);

  /**
   * Fired if a {@link Step} is hidden.
   *
   * @param event
   *     An provider containing information about the hiding
   */
  void onHide(HideEvent event);

  /**
   * Event class that contains information about hiding.
   */
  class HideEvent extends StepEvent {

    public HideEvent(Step source) {
      super(source);
    }
  }
}
