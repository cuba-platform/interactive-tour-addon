package com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.util;

import com.haulmont.addon.tour.web.toolkit.ui.client.addons.producttour.step.ContentMode;
import com.google.gwt.dom.client.PreElement;

import com.vaadin.client.WidgetUtil;

public final class ContentFormatter {

  private ContentFormatter() {}

  public static String format(String text, ContentMode contentMode) {
    switch (contentMode) {
      case PREFORMATTED:
        return "<" + PreElement.TAG + ">" + text + "</" + PreElement.TAG + ">";
      case TEXT:
        return WidgetUtil.escapeHTML(text);
      case HTML:
        return text;
      default:
        return "";
    }
  }
}
