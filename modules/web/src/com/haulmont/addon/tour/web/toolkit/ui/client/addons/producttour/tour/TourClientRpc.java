package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour;

import com.vaadin.shared.communication.ClientRpc;

public interface TourClientRpc extends ClientRpc {

  void cancel();

  void hide();

  void show(String stepId);

  void start();

  void back();

  void next();
}
