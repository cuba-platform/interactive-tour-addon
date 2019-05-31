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
package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step;

import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.button.StepButtonOptions;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class StepOptions extends JavaScriptObject {

  protected StepOptions() {}

  public static native StepOptions create() /*-{
    return {buttons: false, when: {}};
  }-*/;

  public final native void setClasses(String classes) /*-{
    this.classes = classes;
  }-*/;

  public final native void setTitle(String title) /*-{
    this.title = title;
  }-*/;

  public final native void setText(String text) /*-{
    this.text = text;
  }-*/;

  public final native void setShowCancelLink(boolean showCancelLink) /*-{
    this.showCancelLink = showCancelLink;
  }-*/;

  public final native void setScrollTo(boolean scrollTo) /*-{
    this.scrollTo = scrollTo;
  }-*/;

  public final native void setModal(boolean modal) /*-{
    this.modal = modal;
  }-*/;

  public final native void setWidth(String width) /*-{
    this.width = width;
  }-*/;

  public final native void setHeight(String height) /*-{
    this.height = height;
  }-*/;

  public final native void setAttachTo(Element element, StepAnchor anchor) /*-{
    if (element) {
      this.attachTo = {
        element: element,
        on: anchor.@java.lang.Enum::name.toLowerCase()
      }
    } else {
      this.attachTo = {};
    }
  }-*/;

  public final native void setButtons(boolean buttons) /*-{
    this.buttons = buttons;
  }-*/;

  public final native void addButtonOptions(StepButtonOptions buttonOptions) /*-{
    this.buttons = this.buttons || [];
    this.buttons.push(buttonOptions);
  }-*/;

  public final native void setShowListener(Command command) /*-{
    this.when.show = function () {
      $entry(function () {
        command.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command::execute()()
      })();
    }
  }-*/;

  public native final void setCancelListener(Command command) /*-{
    this.when.cancel = function () {
      $entry(function () {
        command.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command::execute()()
      })();
    }
  }-*/;

  public native final void setCompleteListener(Command command) /*-{
    this.when.complete = function () {
      $entry(function () {
        command.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command::execute()()
      })();
    }
  }-*/;

  public native final void setHideListener(Command command) /*-{
    this.when.hide = function () {
      $entry(function () {
        command.@com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command::execute()()
      })();
    }
  }-*/;
}
