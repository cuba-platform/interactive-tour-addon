package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour;


import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepConnector;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepJso;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepOptions;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.StringBiConsumer;
import com.google.gwt.core.client.Scheduler;

import com.vaadin.client.ServerConnector;
import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.Connect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Connect(Tour.class)
public class TourConnector extends AbstractExtensionConnector {

  private TourJso tourJso;
  private final TourClientRpc clientRpc = new TourClientRpc() {
    @Override
    public void cancel() {
      tourJso.cancel();
    }

    @Override
    public void hide() {
      tourJso.hide();
    }

    @Override
    public void show(String stepId) {
      tourJso.show(stepId);
    }

    @Override
    public void start() {
      tourJso.start();
    }

    @Override
    public void back() {
      tourJso.back();
    }

    @Override
    public void next() {
      tourJso.next();
    }
  };

  @Override
  protected void extend(ServerConnector target) {
    registerRpc(TourClientRpc.class, clientRpc);
    tourJso = createTour(getOptions());
  }

  private native TourJso createTour(TourOptions tourOptions) /*-{
    var tour = new $wnd.Shepherd.Tour(tourOptions);
    var when = tourOptions.when;
    if (when) {
      for (var event in when) {
        if ({}.hasOwnProperty.call(when, event)) {
          var handler = when[event];
          tour.on(event, handler, this);
        }
      }
    }

    tour.on('inactive', function () {
      for (var i = 0; i < tour.steps.length; ++i) {
        tour.steps[i].destroy();
      }
    });

    tour.on('complete', function () {
      for (var i = 0; i < tour.steps.length; ++i) {
        tour.steps[i].destroy();
      }
    });

    tour.on('cancel', function () {
      for (var i = 0; i < tour.steps.length; ++i) {
        tour.steps[i].destroy();
      }
    });

    return tour;
  }-*/;

  public TourOptions getOptions() {
    StepOptions defaultStepOptions = StepOptions.create();
    defaultStepOptions.setButtons(false);
    defaultStepOptions.setClasses("shepherd-theme-valo");

    TourOptions options = TourOptions.create();
    options.setDefaultOptions(defaultStepOptions);
    options.setCancelListener(new Command() {
      @Override
      public void execute() {
        getRpcProxy(TourServerRpc.class).onCancel();
      }
    });
    options.setCompleteListener(new Command() {
      @Override
      public void execute() {
        getRpcProxy(TourServerRpc.class).onComplete();
      }
    });
    options.setHideListener(new Command() {
      @Override
      public void execute() {
        getRpcProxy(TourServerRpc.class).onHide();
      }
    });
    options.setShowListener(new StringBiConsumer() {
      @Override
      public void accept(String s, String s2) {
        getRpcProxy(TourServerRpc.class).onShow(s, s2);
      }
    });
    options.setStartListener(new Command() {
      @Override
      public void execute() {
        getRpcProxy(TourServerRpc.class).onStart();
      }
    });
    return options;
  }

  @OnStateChange("steps")
  private void updateSteps() {
    List<StepConnector> addedSteps = getAddedSteps();
    for (StepConnector step : addedSteps) {
      addStep(step);
    }

    List<String> stepIdsToRemove = getStepIdsToRemove();
    for (String stepId : stepIdsToRemove) {
      removeStep(stepId);
    }
  }

  private List<StepConnector> getAddedSteps() {
    List<StepConnector> addedSteps = new LinkedList<StepConnector>();

    List<Connector> stateSteps = getState().steps;
    List<StepJso> currentSteps = new LinkedList<StepJso>(Arrays.asList(tourJso.getSteps()));

    for (Connector stateStep : stateSteps) {
      boolean found = false;
      for (StepJso existingStep : currentSteps) {
        if (existingStep.getId().equals(((StepConnector) stateStep).getStepId())) {
          found = true;
          break;
        }
      }
      if (!found) {
        addedSteps.add(((StepConnector) stateStep));
      }
    }

    return addedSteps;
  }

  private void addStep(StepConnector stepConnector) {
    StepJso stepJso = tourJso.addStep(stepConnector.getStepId(), stepConnector.getOptions());
    stepConnector.setStepJso(stepJso);
  }

  private List<String> getStepIdsToRemove() {
    List<String> removedSteps = new ArrayList<String>();

    List<Connector> stateSteps = getState().steps;
    List<StepJso> currentSteps = new LinkedList<StepJso>(Arrays.asList(tourJso.getSteps()));

    for (StepJso existingStep : currentSteps) {
      boolean found = false;
      for (Connector stateStep : stateSteps) {
        if (existingStep.getId().equals(((StepConnector) stateStep).getStepId())) {
          found = true;
          break;
        }
      }
      if (!found) {
        removedSteps.add(existingStep.getId());
      }
    }

    return removedSteps;
  }

  private void removeStep(String stepId) {
    tourJso.removeStep(stepId);
  }

  @OnStateChange("currentStep")
  private void updateCurrentStep() {
    StepConnector currentStepFromState = (StepConnector) getState().currentStep;
    StepJso currentStepFromTour = tourJso.getCurrentStep();

    final String stepIdFromState = currentStepFromState != null
                                       ? currentStepFromState.getStepId()
                                       : "";
    final String stepIdFromTour = currentStepFromTour != null ? currentStepFromTour.getId() : "";

    if (!Objects.equals(stepIdFromState, stepIdFromTour)) {
      Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
        @Override
        public void execute() {
          tourJso.show(stepIdFromState, false);
        }
      });
    }
  }

  @Override
  public void onUnregister() {
    super.onUnregister();
    tourJso.done();
  }

  @Override
  public TourState getState() {
    return (TourState) super.getState();
  }
}
