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
package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour;

import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour.TourClientRpc;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour.TourServerRpc;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour.TourState;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step;
import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.UI;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A tour consisting of one or multiple steps.
 *
 * @see Step
 */
@JavaScript({"vaadin://addons/producttour/js/producttour.js"})
public class Tour extends AbstractExtension {

  private final List<Step> steps;
  private final TourServerRpc serverRpc = new TourServerRpc() {
    @Override
    public void onCancel() {
      getState().currentStep = null;
      fireEvent(new TourCancelListener.CancelEvent(Tour.this));
    }

    @Override
    public void onComplete() {
      getState().currentStep = null;
      fireEvent(new TourCompleteListener.CompleteEvent(Tour.this));
    }

    @Override
    public void onHide() {
      getState().currentStep = null;
      fireEvent(new TourHideListener.HideEvent(Tour.this));
    }

    @Override
    public void onShow(String previousStepId, String currentStepId) {
      Step previousStep = getStepById(previousStepId);
      Step currentStep = getStepById(currentStepId);
      getState().currentStep = currentStep; // Remember for serialization
      fireEvent(new TourShowListener.ShowEvent(Tour.this, previousStep, currentStep));
    }

    @Override
    public void onStart() {
      fireEvent(new TourStartListener.StartEvent(Tour.this));
    }
  };

  private Step currentStep;

  /**
   * Construct a new tour.
   */
  public Tour() {
    this.steps = new LinkedList<>();
    registerRpc(serverRpc);

    extend(UI.getCurrent());

    addShowListener((TourShowListener) event -> currentStep = event.getCurrentStep());
  }

  /**
   * Add the given listener to the tour that will be triggered if the tour is shown.
   *
   * @param listener
   *     The listener to be added
   */
  public void addShowListener(TourShowListener listener) {
    addListener(TourShowListener.ShowEvent.class, listener, TourShowListener.SHOW_METHOD);
  }

  /**
   * Add the given step to the tour. Steps will be shown in the order they were added.
   *
   * @param step
   *     The step to be added
   */
  public void addStep(Step step) {
    step.setTour(this);
    getState().steps.add(step);
    steps.add(step);
  }

  @Override
  public TourState getState() {
    return (TourState) super.getState();
  }

  @Override
  protected TourState getState(boolean markAsDirty) {
    return (TourState) super.getState(markAsDirty);
  }

  /**
   * Remove the given step from the tour.
   *
   * @param step
   *     The step to be removed
   */
  public void removeStep(Step step) {
    steps.remove(step);
    getState().steps.remove(step);
    step.remove();
  }

  /**
   * Get the last shown step.
   *
   * @return The last shown step or <code>null</code> if no step is shown
   */
  public Step getCurrentStep() {
    return currentStep;
  }

  /**
   * Get a step by its index.
   *
   * @param index
   *     The index of the step to get
   *
   * @return The step at the given index
   */
  public Step getStepByIndex(int index) {
    return steps.get(index);
  }

  /**
   * Get the count of steps for this tour.
   *
   * @return The count of steps
   */
  public int getStepCount() {
    return steps.size();
  }

  /**
   * Get the steps of the tour.
   *
   * @return The steps of the tour inside an unmodifiable container
   */
  public List<Step> getSteps() {
    return Collections.unmodifiableList(steps);
  }

  /**
   * Trigger cancel on the current step, hiding it without advancing. The cancel provider for the
   * tour will be triggered.
   */
  public void cancel() {
    getRpcProxy(TourClientRpc.class).cancel();
  }

  /**
   * Hide the current step. The hide provider for the tour will be triggered.
   */
  public void hide() {
    getRpcProxy(TourClientRpc.class).hide();
  }

  /**
   * Show the step with the given id. The show provider of the tour will be triggered.
   *
   * @param stepId
   *     The id of the step to show
   */
  public void show(String stepId) {
    getRpcProxy(TourClientRpc.class).show(stepId);
  }

  /**
   * Get a step by its id.
   *
   * @param stepId
   *     The id of the step to get
   *
   * @return The step with the given id or <code>null</code> if no step with the given id exists for
   * this tour
   */
  public Step getStepById(String stepId) {
    return steps.stream()
                .filter(s -> Objects.equals(s.getId(), stepId))
                .findFirst()
                .orElse(null);
  }

  /**
   * Show the first step and begin the tour. The start provider of the tour will be triggered.
   */
  public void start() {
    getRpcProxy(TourClientRpc.class).start();
  }

  /**
   * Show the previous step in the order they were added.
   */
  public void back() {
    getRpcProxy(TourClientRpc.class).back();
  }

  /**
   * Show the next step in the order they were added.
   */
  public void next() {
    getRpcProxy(TourClientRpc.class).next();
  }

  /**
   * Add the given listener to the tour that will be triggered if the tour is cancelled.
   *
   * @param listener
   *     The listener to be added
   */
  public void addCancelListener(TourCancelListener listener) {
    addListener(TourCancelListener.CancelEvent.class, listener, TourCancelListener.CANCEL_METHOD);
  }

  public void removeCancelListener(TourCancelListener listener) {
    removeListener(TourCancelListener.CancelEvent.class, listener,
                   TourCancelListener.CANCEL_METHOD);
  }

  /**
   * Add the given listener to the tour that will be triggered if the tour is completed.
   *
   * @param listener
   *     The listener to be added
   */
  public void addCompleteListener(TourCompleteListener listener) {
    addListener(TourCompleteListener.CompleteEvent.class, listener,
                TourCompleteListener.COMPLETE_METHOD);
  }

  /**
   * Remove the given listener from the tour.
   *
   * @param listener
   *     The listener to be removed.
   */
  public void removeCompleteListener(TourCompleteListener listener) {
    removeListener(TourCompleteListener.CompleteEvent.class, listener,
                   TourCompleteListener.COMPLETE_METHOD);
  }

  /**
   * Add the given listener to the tour that will be triggered if the tour is hidden.
   *
   * @param listener
   *     The listener to be added
   */
  public void addHideListener(TourHideListener listener) {
    addListener(TourHideListener.HideEvent.class, listener, TourHideListener.HIDE_METHOD);
  }

  /**
   * Remove the given listener from the tour.
   *
   * @param listener
   *     The listener to be removed.
   */
  public void removeHideListener(TourHideListener listener) {
    removeListener(TourHideListener.HideEvent.class, listener, TourHideListener.HIDE_METHOD);
  }

  /**
   * Remove the given listener from the tour.
   *
   * @param listener
   *     The listener to be removed.
   */
  public void removeShowListener(TourShowListener listener) {
    removeListener(TourShowListener.ShowEvent.class, listener, TourShowListener.SHOW_METHOD);
  }

  /**
   * Add the given listener to the tour that will be triggered if the tour is started.
   *
   * @param listener
   *     The listener to be added
   */
  public void addStartListener(TourStartListener listener) {
    addListener(TourStartListener.StartEvent.class, listener,
                TourStartListener.TOUR_STARTED_METHOD);
  }

  /**
   * Remove the given listener from the tour.
   *
   * @param listener
   *     The listener to be removed.
   */
  public void removeStartListener(TourStartListener listener) {
    removeListener(TourStartListener.StartEvent.class, listener,
                   TourStartListener.TOUR_STARTED_METHOD);
  }
}
