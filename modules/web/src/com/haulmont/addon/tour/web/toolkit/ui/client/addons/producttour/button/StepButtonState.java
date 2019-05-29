package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.button;

import com.vaadin.shared.annotations.NoLayout;
import com.vaadin.shared.communication.SharedState;

import java.util.ArrayList;
import java.util.List;

public class StepButtonState extends SharedState {

  @NoLayout
  public String caption;
  @NoLayout
  public boolean buttonEnabled = true;
  @NoLayout
  public List<String> styles = new ArrayList<String>();
}
