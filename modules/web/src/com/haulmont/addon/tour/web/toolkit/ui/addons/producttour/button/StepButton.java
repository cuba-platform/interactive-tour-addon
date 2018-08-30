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
package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button;

import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.button.StepButtonServerRpc;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.button.StepButtonState;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step.Step;
import com.vaadin.server.AbstractExtension;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.AbstractComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A button of a step that can be used to provide different actions if clicked.
 *
 * @see Step
 */
public class StepButton extends AbstractExtension {

  private final StepButtonServerRpc serverRpc = new StepButtonServerRpc() {
    @Override
    public void onClick(MouseEventDetails mouseEventDetails) {
      fireEvent(new StepButtonClickListener.ClickEvent(StepButton.this, mouseEventDetails));
    }
  };

  private Step step;

  /**
   * Creates a new button with the given caption.
   *
   * @param caption
   *     The caption of the button
   */
  public StepButton(String caption) {
    this(caption, (String) null);
  }

  /**
   * Creates a new button with the given caption and style.
   *
   * @param caption
   *     The caption of the button
   * @param style
   *     The style of the button. The button styles of the valo theme can be used ({@link
   *     com.vaadin.ui.themes.ValoTheme}) to style the button in different ways
   */
  public StepButton(String caption, String style) {
    this(caption, style, null);
  }

  /**
   * Creates a new button with the given caption, click listener and style.
   *
   * @param caption
   *     The caption of the button
   * @param style
   *     The style of the button. The button styles of the valo theme can be used ({@link
   *     com.vaadin.ui.themes.ValoTheme}) to style the button in different ways
   * @param clickListener
   *     The click listener of the button
   */
  public StepButton(String caption, String style, StepButtonClickListener clickListener) {
    registerRpc(serverRpc);

    setCaption(caption);
    setStyleName(style);
    if (clickListener != null) {
      addClickListener(clickListener);
    }
  }

  /**
   * Adds a click listener to the button.
   *
   * @param listener
   *     The listener to be added
   */
  public void addClickListener(StepButtonClickListener listener) {
    addListener(StepButtonClickListener.ClickEvent.class, listener,
                StepButtonClickListener.CLICK_METHOD);
  }

  @Override
  protected StepButtonState getState() {
    return (StepButtonState) super.getState();
  }

  @Override
  protected StepButtonState getState(boolean markAsDirty) {
    return (StepButtonState) super.getState(markAsDirty);
  }

  /**
   * Creates a new button with the given caption and click listener.
   *
   * @param caption
   *     The caption of the button
   * @param clickListener
   *     The click listener of the button
   */
  public StepButton(String caption, StepButtonClickListener clickListener) {
    this(caption, "", clickListener);
  }

  /**
   * Remove the given click listener from the button.
   *
   * @param listener
   *     The listener to be removed
   */
  public void removeClickListener(StepButtonClickListener listener) {
    removeListener(StepButtonClickListener.ClickEvent.class, listener,
                   StepButtonClickListener.CLICK_METHOD);
  }

  /**
   * Get the caption of the button
   *
   * @return The caption of the button
   */
  public String getCaption() {
    return getState().caption;
  }

  /**
   * Set the caption of the button.
   *
   * @param caption
   *     The caption to be set
   */
  public void setCaption(String caption) {
    getState().caption = caption;
  }

  /**
   * Get the enabled state of the button.
   *
   * @return <code>true</code> if the button is enabled, <code>false</code> else
   */
  public boolean isEnabled() {
    return getState().buttonEnabled;
  }

  /**
   * Set the enabled state of the button.
   *
   * @param enabled
   *     The enabled state to be set
   */
  public void setEnabled(boolean enabled) {
    getState().buttonEnabled = enabled;
  }

  /**
   * @see AbstractComponent#addStyleName(String)
   */
  public void addStyleName(String style) {
    if (style == null || "".equals(style)) {
      return;
    }
    if (style.contains(" ")) {
      // Split space separated style names and add them one by one.
      StringTokenizer tokenizer = new StringTokenizer(style, " ");
      while (tokenizer.hasMoreTokens()) {
        addStyleName(tokenizer.nextToken());
      }
      return;
    }

    if (getState().styles == null) {
      getState().styles = new ArrayList<>();
    }
    List<String> styles = getState().styles;
    if (!styles.contains(style)) {
      styles.add(style);
    }
  }

  /**
   * @see AbstractComponent#removeStyleName(String)
   */
  public void removeStyleName(String style) {
    StepButtonState state = getState();
    if (state.styles != null && !state.styles.isEmpty()) {
      StringTokenizer tokenizer = new StringTokenizer(style, " ");
      while (tokenizer.hasMoreTokens()) {
        getState().styles.remove(tokenizer.nextToken());
      }
    }
  }

  /**
   * @see AbstractComponent#getStyleName()
   */
  public String getStyleName() {
    String s = "";
    StepButtonState state = getState(false);
    if (state.styles != null && !state.styles.isEmpty()) {
      for (final Iterator<String> it = state.styles.iterator(); it.hasNext(); ) {
        s += it.next();
        if (it.hasNext()) {
          s += " ";
        }
      }
    }
    return s;
  }

  /**
   * @see AbstractComponent#setStyleName(String)
   */
  public void setStyleName(String style) {
    if (style == null || "".equals(style)) {
      getState().styles = new ArrayList<>();
      return;
    }
    if (getState().styles == null) {
      getState().styles = new ArrayList<>();
    }
    List<String> styles = getState().styles;
    styles.clear();
    StringTokenizer tokenizer = new StringTokenizer(style, " ");
    while (tokenizer.hasMoreTokens()) {
      styles.add(tokenizer.nextToken());
    }
  }

  /**
   * Get the step the button is attached to.
   *
   * @return The step
   */
  public Step getStep() {
    return step;
  }

  /**
   * DO NOT USE!
   * <p>
   * Used internally to add the button to the given step.
   * Please use {@link Step#addButton(StepButton)} instead.
   *
   * @param step
   *     The step the button should be added to
   */
  public void setStep(Step step) {
    this.step = step;
    extend(step);
  }
}
