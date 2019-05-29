package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step;

import com.vaadin.shared.communication.ClientRpc;

public interface StepClientRpc extends ClientRpc {

  void cancel();

  void complete();

  void hide();

  void show();

  void scrollTo();
}
