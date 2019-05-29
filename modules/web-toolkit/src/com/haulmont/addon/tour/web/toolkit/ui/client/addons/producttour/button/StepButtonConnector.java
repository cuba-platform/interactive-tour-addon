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
