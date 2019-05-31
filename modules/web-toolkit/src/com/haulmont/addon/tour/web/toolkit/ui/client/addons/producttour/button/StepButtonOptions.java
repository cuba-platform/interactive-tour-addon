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
