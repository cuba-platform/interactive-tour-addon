package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step;

import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface StepShowListener extends Serializable {

  Method SHOW_METHOD = ReflectTools.findMethod(StepShowListener.class,
                                               "onShow",
                                               ShowEvent.class);

  /**
   * Fired if a {@link Step} is shown.
   *
   * @param event
   *     An provider containing information about the showing
   */
  void onShow(ShowEvent event);

  /**
   * Event class that contains information about showing.
   */
  class ShowEvent extends StepEvent {

    public ShowEvent(Step source) {
      super(source);
    }
  }
}
