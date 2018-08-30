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

import com.google.gwt.dom.client.Element;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.button.StepButtonConnector;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.Command;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.Connect;

@Connect(Step.class)
public class StepConnector extends AbstractExtensionConnector {

  private StepJso stepJso;
  private final StepClientRpc clientRpc = new StepClientRpc() {
    @Override
    public void cancel() {
      stepJso.cancel();
    }

    @Override
    public void complete() {
      stepJso.complete();
    }

    @Override
    public void hide() {
      stepJso.hide();
    }

    @Override
    public void show() {
      stepJso.show();
    }

    @Override
    public void scrollTo() {
      stepJso.scrollTo();
    }
  };

  @Override
  protected void extend(ServerConnector target) {
    registerRpc(StepClientRpc.class, clientRpc);
  }

  public void setStepJso(StepJso stepJso) {
    this.stepJso = stepJso;
  }

  public String getStepId() {
    return getState().id != null ? getState().id : (stepJso != null ? stepJso.getId() : null);
  }

  @Override
  public void onStateChanged(StateChangeEvent stateChangeEvent) {
    super.onStateChanged(stateChangeEvent);
    redrawStep();
  }

  private void redrawStep() {
    if (stepJso != null) {
      stepJso.setOptions(getOptions());
      stepJso.reRender();
    }
  }

  public StepOptions getOptions() {
    StepOptions options = StepOptions.create();
    options.setTitle(getState().title);
    options.setText(getState().text);
    options.setModal(getState().modal);
    options.setShowCancelLink(getState().cancellable);
    options.setScrollTo(getState().scrollTo);
    options.setAttachTo(getAttachToElement(), getState().anchor);
    options.setWidth(getState().width);
    options.setHeight(getState().height);
    options.setCancelListener(new Command() {
      @Override
      public void execute() {
        getRpcProxy(StepServerRpc.class).onCancel();
      }
    });
    options.setCompleteListener(new Command() {
      @Override
      public void execute() {
        getRpcProxy(StepServerRpc.class).onComplete();
      }
    });
    options.setHideListener(new Command() {
      @Override
      public void execute() {
        getRpcProxy(StepServerRpc.class).onHide();
      }
    });
    options.setShowListener(new Command() {
      @Override
      public void execute() {
        getRpcProxy(StepServerRpc.class).onShow();
      }
    });

    for (Connector button : getState().buttons) {
      StepButtonConnector stepButtonConnector = ((StepButtonConnector) button);
      stepButtonConnector.removeStateChangeHandler(this);
      stepButtonConnector.addStateChangeHandler(this);
      options.addButtonOptions(stepButtonConnector.getOptions());
    }

    return options;
  }

  private Element getAttachToElement() {
    ComponentConnector attachTo = (ComponentConnector) getState().attachTo;
    return attachTo != null ? attachTo.getWidget().getElement() : null;
  }

  @Override
  public void onUnregister() {
    super.onUnregister();
    stepJso.destroy();
  }

  @Override
  public StepState getState() {
    return (StepState) super.getState();
  }
}
