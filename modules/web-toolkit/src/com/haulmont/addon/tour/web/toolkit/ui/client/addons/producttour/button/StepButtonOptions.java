package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.button;

import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.MouseEventDetailsConsumer;
import com.google.gwt.core.client.JavaScriptObject;

public class StepButtonOptions extends JavaScriptObject {

  protected StepButtonOptions() {}

  public static native StepButtonOptions create() /*-{
    return {};
  }-*/;

  public final native void setText(String text) /*-{
    this.text = text;
  }-*/;

  public final native void setClasses(String[] classes) /*-{
    this.classes = classes.join(' ');
  }-*/;

  public final native void setEnabled(boolean enabled) /*-{
    this.buttonEnabled = enabled;
  }-*/;

  // @formatter:off
  public final native void setClickListener(MouseEventDetailsConsumer consumer)/*-{
    this.events = {
      'click': function (e) {
        $entry(function (e) {
          var mouseEventDetails = @com.vaadin.client.MouseEventDetailsBuilder::buildMouseEventDetails(Lcom/google/gwt/dom/client/NativeEvent;Lcom/google/gwt/dom/client/Element;)(e, e.target);
          consumer.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.MouseEventDetailsConsumer::accept(*)(mouseEventDetails);
        })(e);
      }
    };
  }-*/;
  // @formatter:on
}
