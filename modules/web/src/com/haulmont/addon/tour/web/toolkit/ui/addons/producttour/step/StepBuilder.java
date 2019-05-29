package com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.step;

import com.haulmont.addon.tour.web.toolkit.ui.addons.producttour.button.StepButton;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.ContentMode;
import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.StepAnchor;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractComponent;

/**
 * Simplify the creation of a {@link Step}
 */
public class StepBuilder {

  private final Step step;

  /**
   * Create new step builder
   */
  public StepBuilder() {
    step = new Step();
  }

  /**
   * Create new step builder
   *
   * @param id
   *     The id of the step to create
   */
  public StepBuilder(String id) {
    step = new Step(id);
  }

  /**
   * Convenience method calling {@link Step#setAttachedTo(AbstractComponent)}
   */
  public StepBuilder withAttachTo(AbstractComponent component) {
    step.setAttachedTo(component);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setDetached()}
   */
  public StepBuilder withDetached() {
    step.setDetached();
    return this;
  }

  /**
   * Convenience method calling {@link Step#setAnchor(StepAnchor)}
   */
  public StepBuilder withAnchor(StepAnchor anchor) {
    step.setAnchor(anchor);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setWidth(float, Sizeable.Unit)}
   */
  public StepBuilder withWidth(float width, Sizeable.Unit unit) {
    step.setWidth(width, unit);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setWidth(String)}
   */
  public StepBuilder withWidth(String width) {
    step.setWidth(width);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setWidthUndefined()}
   */
  public StepBuilder withWidthUndefined() {
    step.setWidthUndefined();
    return this;
  }

  /**
   * Convenience method calling {@link Step#setHeight(float, Sizeable.Unit)}
   */
  public StepBuilder withHeight(float height, Sizeable.Unit unit) {
    step.setHeight(height, unit);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setHeight(String)}
   */
  public StepBuilder withHeight(String height) {
    step.setHeight(height);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setHeightUndefined()}
   */
  public StepBuilder withHeightUndefined() {
    step.setHeightUndefined();
    return this;
  }

  /**
   * Convenience method calling {@link Step#setSizeFull()}
   */
  public StepBuilder withSizeFull() {
    step.setSizeFull();
    return this;
  }

  /**
   * Convenience method calling {@link Step#setSizeUndefined()}
   */
  public StepBuilder withSizeUndefined() {
    step.setSizeUndefined();
    return this;
  }

  /**
   * Convenience method calling {@link Step#setTitle(String)}
   */
  public StepBuilder withTitle(String title) {
    step.setTitle(title);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setText(String)}
   */
  public StepBuilder withText(String text) {
    step.setText(text);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setTitleContentMode(ContentMode)}
   */
  public StepBuilder withTitleContentMode(ContentMode contentMode) {
    step.setTitleContentMode(contentMode);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setTextContentMode(ContentMode)}
   */
  public StepBuilder withTextContentMode(ContentMode contentMode) {
    step.setTextContentMode(contentMode);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setCancellable(boolean)}
   */
  public StepBuilder withCancellable(boolean cancellable) {
    step.setCancellable(cancellable);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setScrollTo(boolean)}
   */
  public StepBuilder withScrollTo(boolean scrollTo) {
    step.setScrollTo(scrollTo);
    return this;
  }

  /**
   * Convenience method calling {@link Step#setModal(boolean)}
   */
  public StepBuilder withModal(boolean modal) {
    step.setModal(modal);
    return this;
  }

  /**
   * Convenience method calling {@link Step#addButton(StepButton)}
   */
  public StepBuilder addButton(StepButton button) {
    step.addButton(button);
    return this;
  }

  /**
   * Convenience method calling {@link Step#addCancelListener(StepCancelListener)}
   */
  public StepBuilder addCancelListener(StepCancelListener listener) {
    step.addCancelListener(listener);
    return this;
  }

  /**
   * Convenience method calling {@link Step#addCompleteListener(StepCompleteListener)}
   */
  public StepBuilder addCompleteListener(StepCompleteListener listener) {
    step.addCompleteListener(listener);
    return this;
  }

  /**
   * Convenience method calling {@link Step#addHideListener(StepHideListener)}
   */
  public StepBuilder addHideListener(StepHideListener listener) {
    step.addHideListener(listener);
    return this;
  }

  /**
   * Convenience method calling {@link Step#addShowListener(StepShowListener)}
   */
  public StepBuilder addShowListener(StepShowListener listener) {
    step.addShowListener(listener);
    return this;
  }

  /**
   * Build the step.
   *
   * @return The step
   */
  public Step build() {
    return step;
  }
}
