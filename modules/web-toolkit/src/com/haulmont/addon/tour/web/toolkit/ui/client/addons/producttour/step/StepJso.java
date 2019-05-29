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
