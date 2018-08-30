/*
 * Copyright 2017 Julien Charpenel
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour;

import com.google.gwt.core.client.JavaScriptObject;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepOptions;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.StringBiConsumer;

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
