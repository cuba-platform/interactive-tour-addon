package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step;

import com.vaadin.shared.Connector;
import com.vaadin.shared.annotations.NoLayout;
import com.vaadin.shared.communication.SharedState;

import java.util.LinkedList;
import java.util.List;

public class StepState extends SharedState {

  @NoLayout
  public String id;
  @NoLayout
  public Connector attachTo;
  @NoLayout
  public String title;
  @NoLayout
  public String text;
  @NoLayout
  public Boolean cancellable = false;
  @NoLayout
  public Boolean scrollTo = false;
  @NoLayout
  public Boolean modal = false;
  @NoLayout
  public ContentMode textContentMode = ContentMode.TEXT;
  @NoLayout
  public ContentMode titleContentMode = ContentMode.TEXT;
  @NoLayout
  public StepAnchor anchor = StepAnchor.RIGHT;
  @NoLayout
  public String width;
  @NoLayout
  public String height;
  @NoLayout
  public List<Connector> buttons = new LinkedList<Connector>();
}
