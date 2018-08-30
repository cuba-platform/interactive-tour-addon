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

import com.google.gwt.core.client.JavaScriptObject;

import java.io.Serializable;

public class StepJso extends JavaScriptObject implements Serializable {

  protected StepJso() {}

  public native final String getId() /*-{
    return this.id;
  }-*/;

  public native final void setOptions(StepOptions options) /*-{
    this.options = $wnd.Tether.Utils.extend(this.options, options);
  }-*/;

  public native final void reRender() /*-{
    var open = this.isOpen();
    this.destroy()
    if (open) {
      this.show(false);
    }
  }-*/;

  public native final void hide() /*-{
    this.hide();
  }-*/;

  public native final void show() /*-{
    this.show();
  }-*/;

  public native final void cancel() /*-{
    this.cancel();
  }-*/;

  public native final void complete() /*-{
    this.complete();
  }-*/;

  public native final void scrollTo() /*-{
    this.scrollTo();
  }-*/;

  public native final void destroy() /*-{
    this.destroy();
  }-*/;
}
