package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step;

import com.vaadin.shared.communication.ServerRpc;

public interface StepServerRpc extends ServerRpc {

  void onCancel();

  void onComplete();

  void onHide();

  void onShow();
}
