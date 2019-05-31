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
