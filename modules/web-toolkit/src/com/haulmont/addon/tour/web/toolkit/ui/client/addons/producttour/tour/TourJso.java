package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour;

import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepJso;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepOptions;
import com.google.gwt.core.client.JavaScriptObject;

import java.io.Serializable;

public class TourJso extends JavaScriptObject implements Serializable {

  protected TourJso() {}

  public native final StepJso addStep(String id, StepOptions options) /*-{
    this.addStep(id, options);
    return this.getById(id);
  }-*/;

  public native final void removeStep(String id) /*-{
    this.removeStep(id);
  }-*/;

  public native final StepJso getStepById(String id) /*-{
    return this.getById(id);
  }-*/;

  public native final StepJso getCurrentStep() /*-{
    return this.currentStep;
  }-*/;

  public native final StepJso[] getSteps() /*-{
    return this.steps;
  }-*/;

  public native final void start() /*-{
    this.start();
  }-*/;

  public native final void next() /*-{
    this.next();
  }-*/;

  public native final void back() /*-{
    this.back();
  }-*/;

  public native final void cancel() /*-{
    this.cancel();
  }-*/;

  public native final void hide() /*-{
    this.hide();
  }-*/;

  public final void show(String stepId) {
    this.show(stepId, true);
  }

  public native final void show(String stepId, boolean fireEvents) /*-{
    this.show(stepId, true, fireEvents);
  }-*/;

  public native final void done() /*-{
    this.done();
  }-*/;
}
