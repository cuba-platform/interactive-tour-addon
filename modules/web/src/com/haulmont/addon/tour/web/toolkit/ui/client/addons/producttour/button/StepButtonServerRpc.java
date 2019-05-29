package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.button;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

public interface StepButtonServerRpc extends ServerRpc {

  void onClick(MouseEventDetails mouseEventDetails);
}
