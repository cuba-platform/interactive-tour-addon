package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour;

import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour.TourClientRpc;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour.TourServerRpc;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour.TourState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractExtension;
import com.vaadin.shared.Registration;
import com.vaadin.ui.UI;

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A tour consisting of one or multiple steps.
 *
 * @see Step
 */
@JavaScript({"vaadin://addons/producttour/js/producttour.js"})
public class Tour extends AbstractExtension {

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
    registerRpc(serverRpc);

    extend(UI.getCurrent());

    addShowListener((TourShowListener) event -> currentStep = event.getCurrentStep());
  }

  /**
   * Add the given listener to the tour that will be triggered if the tour is shown.
   *
   * @param listener
   *     The listener to be added
   *
   * @return A {@link Registration} object to be able to remove the listener
   */
  public Registration addShowListener(TourShowListener listener) {
    return addListener(TourShowListener.ShowEvent.class, listener, TourShowListener.SHOW_METHOD);
  }

  /**
   * Add the given step to the tour. Steps will be shown in the order they were added.
   *
   * @param step
   *     The step to be added
   */
  public void addStep(Step step) {
    step.setTour(this);
  }

  /**
   * Remove the given step from the tour.
   *
   * @param step
   *     The step to be removed
   */
  public void removeStep(Step step) {
    step.setTour(null);
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
    return getSteps().get(index);
  }

  /**
   * Get the steps of the tour.
   *
   * @return Copy of the list containing the steps of the tour
   */
  public List<Step> getSteps() {
    return getState().steps.stream()
                           .map(c -> (Step) c)
                           .collect(Collectors.toCollection(LinkedList::new));
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
   * Get the count of steps for this tour.
   *
   * @return The count of steps
   */
  public int getStepCount() {
    return getSteps().size();
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
    return getSteps().stream()
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
   *
   * @return A {@link Registration} object to be able to remove the listener
   */
  public Registration addCancelListener(TourCancelListener listener) {
    return addListener(TourCancelListener.CancelEvent.class, listener,
                       TourCancelListener.CANCEL_METHOD);
  }

  /**
   * Add the given listener to the tour that will be triggered if the tour is completed.
   *
   * @param listener
   *     The listener to be added
   *
   * @return A {@link Registration} object to be able to remove the listener
   */
  public Registration addCompleteListener(TourCompleteListener listener) {
    return addListener(TourCompleteListener.CompleteEvent.class, listener,
                       TourCompleteListener.COMPLETE_METHOD);
  }

  /**
   * Add the given listener to the tour that will be triggered if the tour is hidden.
   *
   * @param listener
   *     The listener to be added
   *
   * @return A {@link Registration} object to be able to remove the listener
   */
  public Registration addHideListener(TourHideListener listener) {
    return addListener(TourHideListener.HideEvent.class, listener, TourHideListener.HIDE_METHOD);
  }

  /**
   * Add the given listener to the tour that will be triggered if the tour is started.
   *
   * @param listener
   *     The listener to be added
   *
   * @return A {@link Registration} object to be able to remove the listener
   */
  public Registration addStartListener(TourStartListener listener) {
    return addListener(TourStartListener.StartEvent.class, listener,
                       TourStartListener.TOUR_STARTED_METHOD);
  }
}
