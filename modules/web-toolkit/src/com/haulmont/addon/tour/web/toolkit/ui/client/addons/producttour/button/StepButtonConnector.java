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

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util.MouseEventDetailsConsumer;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;

@Connect(StepButton.class)
public class StepButtonConnector extends AbstractExtensionConnector {

  @Override
  protected void extend(ServerConnector target) {
    // Nothing to do here
  }

  public StepButtonOptions getOptions() {
    StepButtonOptions options = StepButtonOptions.create();
    options.setText(getState().caption);
    options.setEnabled(getState().buttonEnabled);
    options.setClasses(getState().styles.toArray(new String[getState().styles.size()]));
    options.setClickListener(new MouseEventDetailsConsumer() {
      @Override
      public void accept(MouseEventDetails mouseEventDetails) {
        getRpcProxy(StepButtonServerRpc.class).onClick(mouseEventDetails);
      }
    });
    return options;
  }

  @Override
  public StepButtonState getState() {
    return (StepButtonState) super.getState();
  }
}
