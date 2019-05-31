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
