package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.tour;

import com.vaadin.shared.Connector;
import com.vaadin.shared.annotations.NoLayout;
import com.vaadin.shared.communication.SharedState;

import java.util.LinkedList;
import java.util.List;

public class TourState extends SharedState {

  @NoLayout
  public List<Connector> steps = new LinkedList<Connector>();
  @NoLayout
  public Connector currentStep;
}
