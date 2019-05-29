package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface StepCompleteListener extends Serializable {

  Method COMPLETE_METHOD = ReflectTools.findMethod(StepCompleteListener.class,
                                                   "onComplete",
                                                   CompleteEvent.class);

  /**
   * Fired if a {@link Step} is completed.
   *
   * @param event
   *     An provider containing information about the completion
   */
  void onComplete(CompleteEvent event);

  /**
   * Event class that contains information about completion.
   */
  class CompleteEvent extends StepEvent {

    public CompleteEvent(Step source) {
      super(source);
    }
  }
}
