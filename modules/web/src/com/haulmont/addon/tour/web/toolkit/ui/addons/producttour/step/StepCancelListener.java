package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface StepCancelListener extends Serializable {

  Method CANCEL_METHOD = ReflectTools.findMethod(StepCancelListener.class,
                                                 "onCancel",
                                                 CancelEvent.class);

  /**
   * Fired if a {@link Step} is cancelled.
   *
   * @param event
   *     An provider containing information about the cancellation
   */
  void onCancel(CancelEvent event);

  /**
   * Event class that contains information about cancellation.
   */
  class CancelEvent extends StepEvent {

    public CancelEvent(Step source) {
      super(source);
    }
  }
}
