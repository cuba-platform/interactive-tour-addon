package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour;

import com.vaadin.shared.communication.ServerRpc;

public interface TourServerRpc extends ServerRpc {

  void onCancel();

  void onComplete();

  void onHide();

  void onShow(String previousStepId, String currentStepId);

  void onStart();
}
