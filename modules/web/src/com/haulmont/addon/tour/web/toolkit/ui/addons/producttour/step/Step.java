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
package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step;

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.*;
import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.tour.Tour;
import com.vaadin.server.AbstractExtension;
import com.vaadin.server.SizeWithUnit;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractComponent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * A single step of a tour.
 *
 * @see Tour
 */
public class Step extends AbstractExtension implements Sizeable {

  private static final StepAnchor DEFAULT_ANCHOR = StepAnchor.RIGHT;

  private final List<StepButton> buttons;
  private final StepServerRpc serverRpc = new StepServerRpc() {
    @Override
    public void onCancel() {
      fireEvent(new StepCancelListener.CancelEvent(Step.this));
    }

    @Override
    public void onComplete() {
      fireEvent(new StepCompleteListener.CompleteEvent(Step.this));
    }

    @Override
    public void onHide() {
      fireEvent(new StepHideListener.HideEvent(Step.this));
    }

    @Override
    public void onShow() {
      fireEvent(new StepShowListener.ShowEvent(Step.this));
    }
  };

  /* Sizeable fields */
  private float width = -1;
  private float height = -1;
  private Unit widthUnit = Unit.PIXELS;
  private Unit heightUnit = Unit.PIXELS;

  private Tour tour;

  /**
   * Construct a new step with a random generated id that is not attached to any component and shown
   * in the center of the screen.
   */
  public Step() {
    this((AbstractComponent) null);
  }

  /**
   * Construct a new step with a random generated id that is attached to the given component at the
   * default anchor ({@link Step#DEFAULT_ANCHOR}).
   *
   * @param attachTo
   *     The component to attach the step to
   */
  public Step(AbstractComponent attachTo) {
    this(attachTo, DEFAULT_ANCHOR);
  }

  /**
   * Construct a new step with a random generated id that is attached to the given component at the
   * given anchor.
   *
   * @param attachTo
   *     The component to attach the step to
   * @param anchor
   *     The anchor of the step relative to the given component
   */
  public Step(AbstractComponent attachTo, StepAnchor anchor) {
    this(UUID.randomUUID().toString(), attachTo, anchor);
  }

  /**
   * Construct a new step with the given id that is attached to the given component at the given
   * anchor.
   *
   * @param id
   *     The id of the step
   * @param attachTo
   *     The component to attach the step to
   * @param anchor
   *     The anchor of the step relative to the given component
   */
  public Step(String id, AbstractComponent attachTo, StepAnchor anchor) {
    this.buttons = new LinkedList<>();
    registerRpc(serverRpc);

    setId(id);
    setAttachedTo(attachTo);
    setAnchor(anchor);
  }

  @Override
  public StepState getState() {
    return (StepState) super.getState();
  }

  @Override
  protected StepState getState(boolean markAsDirty) {
    return (StepState) super.getState(markAsDirty);
  }

  @Override
  public void beforeClientResponse(boolean initial) {
    super.beforeClientResponse(initial);
    if (getHeight() >= 0) {
      getState().height = getCSSHeight();
    } else {
      getState().height = null;
    }

    if (getWidth() >= 0) {
      getState().width = getCSSWidth();
    } else {
      getState().width = null;
    }
  }

  private String getCSSHeight() {
    return getHeight() + getHeightUnits().getSymbol();
  }

  @Override
  public float getWidth() {
    return width;
  }

  @Override
  public float getHeight() {
    return height;
  }

  @Override
  public Unit getWidthUnits() {
    return widthUnit;
  }

  @Override
  public Unit getHeightUnits() {
    return heightUnit;
  }

  @Override
  public void setHeight(String height) {
    SizeWithUnit size = SizeWithUnit.parseStringSize(height);
    if (size != null) {
      setHeight(size.getSize(), size.getUnit());
    } else {
      setHeight(-1, Unit.PIXELS);
    }
  }

  @Override
  public void setWidth(float width, Unit unit) {
    if (unit == null) {
      throw new IllegalArgumentException("Unit can not be null");
    }
    this.width = width;
    this.widthUnit = unit;
    markAsDirty();
  }

  @Override
  public void setHeight(float height, Unit unit) {
    if (unit == null) {
      throw new IllegalArgumentException("Unit can not be null");
    }
    this.height = height;
    this.heightUnit = unit;
    markAsDirty();
  }

  @Override
  public void setWidth(String width) {
    SizeWithUnit size = SizeWithUnit.parseStringSize(width);
    if (size != null) {
      setWidth(size.getSize(), size.getUnit());
    } else {
      setWidth(-1, Unit.PIXELS);
    }
  }

  @Override
  public void setSizeFull() {
    setWidth(100, Unit.PERCENTAGE);
    setHeight(100, Unit.PERCENTAGE);
  }

  @Override
  public void setSizeUndefined() {
    setWidthUndefined();
    setHeightUndefined();
  }

  @Override
  public void setWidthUndefined() {
    setWidth(-1, Unit.PIXELS);
  }

  @Override
  public void setHeightUndefined() {
    setHeight(-1, Unit.PIXELS);
  }

  private String getCSSWidth() {
    return getWidth() + getWidthUnits().getSymbol();
  }

  /**
   * Construct a new step with the given id that is not attached to any component and shown in the
   * center of the screen.
   *
   * @param id
   *     The id of the step
   */
  public Step(String id) {
    this(id, null);
  }

  /**
   * Construct a new step with the given id that is attached to the given component at the default
   * anchor ({@link Step#DEFAULT_ANCHOR}).
   *
   * @param id
   *     The id of the step
   * @param attachTo
   *     The component to attach the step to
   */
  public Step(String id, AbstractComponent attachTo) {
    this(id, attachTo, StepAnchor.RIGHT);
  }

  /**
   * Check if the step is currently visible.
   *
   * @return <code>true</code> if the step is currently visible, <code>false</code> else
   */
  public boolean isVisible() {
    return tour != null && equals(tour.getCurrentStep());
  }

  /**
   * Get the tour this step is added to.
   *
   * @return The tour
   */
  public Tour getTour() {
    return tour;
  }

  /**
   * DO NOT USE!
   * <p>
   * Used internally to add the step to the given tour.
   * Please use {@link Tour#addStep(Step)} instead.
   *
   * @param tour
   *     The tour the step should be added to
   */
  public void setTour(Tour tour) {
    this.tour = tour;
    extend(tour);
  }

  /**
   * Get the id of the step.
   *
   * @return The id of the step
   */
  public String getId() {
    return getState().id;
  }

  /**
   * Set the id of the step.
   *
   * @param id
   *     The id to set
   */
  private void setId(String id) {
    getState().id = id;
  }

  /**
   * Get the component the step is attached to.
   *
   * @return The component or <code>null</code> if the step is not attached to any component
   */
  public AbstractComponent getAttachedTo() {
    return (AbstractComponent) getState().attachTo;
  }

  /**
   * Set the component the step should be attached to.
   * <p>
   * If set to <code>null</code>, the step will not be attached and shown in the middle of the
   * screen.
   *
   * @param component
   *     The component to attach the step to or <code>null</code>
   *
   * @see #setDetached()
   */
  public void setAttachedTo(AbstractComponent component) {
    getState().attachTo = component;
  }

  /**
   * Set the step to be not attached to any component. The step will then be shown in the middle of
   * the screen. This is equal to calling {@link #setAttachedTo(AbstractComponent)} with
   * <code>null</code> as parameter.
   */
  public void setDetached() {
    getState().attachTo = null;
  }

  /**
   * Get the title of the step.
   *
   * @return The title of the step
   */
  public String getTitle() {
    return getState().title;
  }

  /**
   * Set the title of the step.
   *
   * @param title
   *     The title to be set
   */
  public void setTitle(String title) {
    getState().title = title;
  }

  /**
   * Get the text of the step.
   *
   * @return The text of the step
   */
  public String getText() {
    return getState().text;
  }

  /**
   * Set the text of the step.
   *
   * @param text
   *     The text to be set
   */
  public void setText(String text) {
    getState().text = text;
  }

  /**
   * Get the cancellable state of the step.
   *
   * @return <code>true</code> if the step is cancellable, <code>false</code> else
   */
  public boolean isCancellable() {
    return getState().cancellable;
  }

  /**
   * Set the cancellable state of the step.
   *
   * @param cancellable
   *     <code>true</code> if the step should be cancellable, <code>false</code> else
   */
  public void setCancellable(boolean cancellable) {
    getState().cancellable = cancellable;
  }

  /**
   * Get the scrollTo state of the step.
   *
   * @return <code>true</code> if the step is scrolled to when shown, <code>false</code> else
   */
  public boolean isScrollTo() {
    return getState().scrollTo;
  }

  /**
   * Set the scrollTo state of the step.
   *
   * @param scrollTo
   *     <code>true</code> if the step should be scrolled into view when shown, <code>false</code>
   *     else
   */
  public void setScrollTo(boolean scrollTo) {
    getState().scrollTo = scrollTo;
  }

  /**
   * Get the modal state of the step.
   *
   * @return <code>true</code> if the step is modal, <code>false</code> else
   */
  public boolean isModal() {
    return getState().modal;
  }

  /**
   * Set the modality of the step.
   *
   * @param modal
   *     <code>true</code> if the step should be modal, <code>false</code> else
   */
  public void setModal(boolean modal) {
    getState().modal = modal;
  }

  /**
   * Get the content mode for the text of the step.
   *
   * @return The content mode for the text of the step
   */
  public ContentMode getTextContentMode() {
    return getState().textContentMode;
  }

  /**
   * Sets the content mode for the text of the step.
   *
   * @param contentMode
   *     The content mode to be set
   */
  public void setTextContentMode(ContentMode contentMode) {
    getState().textContentMode = contentMode;
  }

  /**
   * Get the content mode for the title of the step.
   *
   * @return The content mode for the title of the step
   */
  public ContentMode getTitleContentMode() {
    return getState().titleContentMode;
  }

  /**
   * Sets the content mode for the title of the step.
   *
   * @param contentMode
   *     The content mode to be set
   */
  public void setTitleContentMode(ContentMode contentMode) {
    getState().titleContentMode = contentMode;
  }

  /**
   * Get the anchor the step is shown relative to the component it is attached to.
   *
   * @return The anchor of the step
   */
  public StepAnchor getAnchor() {
    return getState().anchor;
  }

  /**
   * Set the anchor the step is shown relative to the component it is attached to.
   *
   * @param anchor
   *     The anchor to be set
   */
  public void setAnchor(StepAnchor anchor) {
    getState().anchor = anchor;
  }

  /**
   * Add a button the step. The button will be shown in the order they are added.
   *
   * @param button
   *     The button to be added
   */
  public void addButton(StepButton button) {
    button.setStep(this);
    getState().buttons.add(button);
    buttons.add(button);
  }

  /**
   * Remove a button from the step.
   *
   * @param button
   *     The button to be removed
   */
  public void removeButton(StepButton button) {
    buttons.remove(button);
    getState().buttons.remove(button);
    button.remove();
  }

  /**
   * Get a button by its index.
   *
   * @param index
   *     The index of the button to get
   *
   * @return The button at the given index
   */
  public StepButton getButtonByIndex(int index) {
    return buttons.get(index);
  }

  /**
   * Get the count of buttons of this step.
   *
   * @return The count of buttons of this step
   */
  public int getButtonCount() {
    return buttons.size();
  }

  /**
   * Get the buttons of the step.
   *
   * @return The buttons of the step inside an unmodifiable container
   */
  public List<StepButton> getButtons() {
    return Collections.unmodifiableList(buttons);
  }

  /**
   * Hide this step and trigger the cancel provider.
   */
  public void cancel() {
    getRpcProxy(StepClientRpc.class).cancel();
  }

  /**
   * Hide this ste and trigger the complete provider.
   */
  public void complete() {
    getRpcProxy(StepClientRpc.class).complete();
  }

  /**
   * Hide this step.
   */
  public void hide() {
    getRpcProxy(StepClientRpc.class).hide();
  }

  /**
   * Show this step.
   */
  public void show() {
    getRpcProxy(StepClientRpc.class).show();
  }

  /**
   * Scroll to this steps element.
   */
  public void scrollTo() {
    getRpcProxy(StepClientRpc.class).scrollTo();
  }

  /**
   * Add the given listener to the step that will be triggered if the step is cancelled.
   *
   * @param listener
   *     The listener to be added
   */
  public void addCancelListener(StepCancelListener listener) {
    addListener(StepCancelListener.CancelEvent.class, listener, StepCancelListener.CANCEL_METHOD);
  }

  /**
   * Remove the given listener from the step.
   *
   * @param listener
   *     The listener to be removed.
   */
  public void removeCancelListener(StepCancelListener listener) {
    removeListener(StepCancelListener.CancelEvent.class, listener,
                   StepCancelListener.CANCEL_METHOD);
  }

  /**
   * Add the given listener to the step that will be triggered if the step is completed.
   *
   * @param listener
   *     The listener to be added
   */
  public void addCompleteListener(StepCompleteListener listener) {
    addListener(StepCompleteListener.CompleteEvent.class, listener,
                StepCompleteListener.COMPLETE_METHOD);
  }

  /**
   * Remove the given listener from the step.
   *
   * @param listener
   *     The listener to be removed.
   */
  public void removeCompleteListener(StepCompleteListener listener) {
    removeListener(StepCompleteListener.CompleteEvent.class, listener,
                   StepCompleteListener.COMPLETE_METHOD);
  }

  /**
   * Add the given listener to the step that will be triggered if the step is hidden.
   *
   * @param listener
   *     The listener to be added
   */
  public void addHideListener(StepHideListener listener) {
    addListener(StepHideListener.HideEvent.class, listener, StepHideListener.HIDE_METHOD);
  }

  /**
   * Remove the given listener from the step.
   *
   * @param listener
   *     The listener to be removed.
   */
  public void removeHideListener(StepHideListener listener) {
    removeListener(StepHideListener.HideEvent.class, listener, StepHideListener.HIDE_METHOD);
  }

  /**
   * Add the given listener to the step that will be triggered if the step is shown.
   *
   * @param listener
   *     The listener to be added
   */
  public void addShowListener(StepShowListener listener) {
    addListener(StepShowListener.ShowEvent.class, listener, StepShowListener.SHOW_METHOD);
  }

  /**
   * Remove the given listener from the step.
   *
   * @param listener
   *     The listener to be removed.
   */
  public void removeShowListener(StepShowListener listener) {
    removeListener(StepShowListener.ShowEvent.class, listener, StepShowListener.SHOW_METHOD);
  }
}
