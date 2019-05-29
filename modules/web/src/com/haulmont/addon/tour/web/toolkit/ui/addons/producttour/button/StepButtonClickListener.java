package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface StepButtonClickListener extends Serializable {

  Method CLICK_METHOD = ReflectTools.findMethod(StepButtonClickListener.class,
                                                "onClick",
                                                ClickEvent.class);

  /**
   * Fired if a {@link StepButton} is clicked.
   *
   * @param event
   *     An provider containing information about the click
   */
  void onClick(ClickEvent event);

  /**
   * Event class that contains information about a click.
   */
  class ClickEvent extends StepButtonEvent {

    private final MouseEventDetails details;

    public ClickEvent(StepButton source) {
      this(source, null);
    }

    public ClickEvent(StepButton source, MouseEventDetails details) {
      super(source);
      this.details = details;
    }

    public int getClientX() {
      if (null != details) {
        return details.getClientX();
      } else {
        return -1;
      }
    }

    public int getClientY() {
      if (null != details) {
        return details.getClientY();
      } else {
        return -1;
      }
    }

    public int getRelativeX() {
      if (null != details) {
        return details.getRelativeX();
      } else {
        return -1;
      }
    }

    public int getRelativeY() {
      if (null != details) {
        return details.getRelativeY();
      } else {
        return -1;
      }
    }

    public boolean isAltKey() {
      if (null != details) {
        return details.isAltKey();
      } else {
        return false;
      }
    }

    public boolean isCtrlKey() {
      if (null != details) {
        return details.isCtrlKey();
      } else {
        return false;
      }
    }

    public boolean isMetaKey() {
      if (null != details) {
        return details.isMetaKey();
      } else {
        return false;
      }
    }

    public boolean isShiftKey() {
      if (null != details) {
        return details.isShiftKey();
      } else {
        return false;
      }
    }
  }
}
