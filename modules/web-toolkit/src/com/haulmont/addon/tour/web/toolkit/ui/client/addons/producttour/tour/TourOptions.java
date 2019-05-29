package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour;

import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepOptions;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.StringBiConsumer;
import com.google.gwt.core.client.JavaScriptObject;

public class TourOptions extends JavaScriptObject {

  protected TourOptions() {}

  public static native TourOptions create() /*-{
    return {when: {}};
  }-*/;

  public final native void setDefaultOptions(StepOptions defaultOptions) /*-{
    this.defaults = defaultOptions;
  }-*/;

  public final native void setCancelListener(Command command) /*-{
    this.when.cancel = function () {
      $entry(function () {
        command.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command::execute()()
      })();
    }
  }-*/;

  public final native void setCompleteListener(Command command) /*-{
    this.when.complete = function () {
      $entry(function () {
        command.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command::execute()()
      })();
    }
  }-*/;

  public final native void setHideListener(Command command) /*-{
    this.when.hide = function () {
      $entry(function () {
        command.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command::execute()()
      })();
    }
  }-*/;

  public final native void setShowListener(StringBiConsumer consumer) /*-{
    this.when.show = function (e) {
      $entry(function (e) {
        var pId = e.previous != null ? e.previous.id : '';
        var cId = e.step != null ? e.step.id : '';
        consumer.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.StringBiConsumer::accept(*)(pId, cId);
      })(e);
    }
  }-*/;

  public final native void setStartListener(Command command) /*-{
    this.when.start = function () {
      $entry(function () {
        command.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command::execute()()
      })();
    }
  }-*/;
}
