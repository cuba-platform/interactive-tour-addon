package com.haulmont.addon.tour.parser;

import com.haulmont.addon.tour.web.gui.components.*;
import com.haulmont.addon.tour.web.gui.utils.TourParser;
import com.haulmont.cuba.client.testsupport.CubaClientTestCase;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Resources;
import com.haulmont.cuba.core.sys.ResourcesImpl;
import com.haulmont.cuba.gui.GuiDevelopmentException;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.sys.FrameImplementation;
import com.haulmont.cuba.web.gui.WebUiComponents;
import com.haulmont.cuba.web.gui.components.WebTabWindow;
import com.haulmont.cuba.web.gui.components.WebTextField;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

@RunWith(JMockit.class)
public class TourParserTest extends CubaClientTestCase {

    protected Resources resources;
    protected TestTourParser tourParser;
    protected WebUiComponents testWebUiComponents;

    protected TestTextField textField1;
    protected TestTextField textField2;

    @Before
    public void init() {
        setupInfrastructure();
        resources = new ResourcesImpl(getClass().getClassLoader(), null);
        tourParser = new TestTourParser();
        tourParser.setMessages(messages);
        testWebUiComponents = new TestWebUiComponents();
    }

    protected class TestTourParser extends TourParser {
        @Override
        public Tour parseTour(String json, String messagesPack, Window extension) {
            return super.parseTour(json, messagesPack, extension);
        }

        protected void setMessages(Messages messages) {
            this.messages = messages;
        }
    }

    protected class TestTextField extends WebTextField {
        @Override
        public void setId(String id) {
            if (!Objects.equals(this.id, id)) {
                if (frame != null) {
                    ((FrameImplementation) frame).unregisterComponent(this);
                }

                this.id = id;
                if (this.component != null) {
                    this.component.setCubaId(id);
                }

                if (frame != null) {
                    ((FrameImplementation) frame).registerComponent(this);
                }
            }
        }
    }

    protected class TestWebUiComponents extends WebUiComponents {
        @Override
        public <T extends Component> T create(String name) {
            Class<? extends Component> componentClass = classes.get(name);
            if (componentClass == null) {
                throw new IllegalStateException(String.format("Can't find component class for '%s'", name));
            }

            Constructor<? extends Component> constructor;
            try {
                constructor = componentClass.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(String.format("Unable to get constructor for '%s' component", name), e);
            }

            try {
                Component instance = constructor.newInstance();
                return (T) instance;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(String.format("Error creating the '%s' component instance", name), e);
            }
        }
    }

    @Test
    public void parseCorrectTourFromJson() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/correctTour.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        textField1 = new TestTextField();
        textField1.setId("textField1");
        window.add(textField1);

        textField2 = new TestTextField();
        textField2.setId("textField2");
        window.add(textField2);

        Tour tour = tourParser.parseTour(file,
                "com/haulmont/scenario/messages.properties", window);

        List<Step> steps = tour.getSteps();

        validateFirstStep(steps.get(0));

        validateSecondStep(steps.get(1));
    }

    @Test(expected = GuiDevelopmentException.class)
    public void validateIncorrectAttachTo() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/incorrectAttachTo.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        tourParser.parseTour(file, null, window);
    }

    @Test(expected = GuiDevelopmentException.class)
    public void validateIncorrectAnchor() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/incorrectAnchor.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        tourParser.parseTour(file, null, window);
    }

    @Test(expected = GuiDevelopmentException.class)
    public void validateIncorrectTextContentMode() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/incorrectTextContentMode.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        tourParser.parseTour(file, null, window);
    }

    @Test(expected = GuiDevelopmentException.class)
    public void validateIncorrectTitleContentMode() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/incorrectTitleContentMode.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        tourParser.parseTour(file, null, window);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateIncorrectWidth() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/incorrectWidth.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        tourParser.parseTour(file, null, window);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateIncorrectHeight() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/incorrectHeight.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        tourParser.parseTour(file, null, window);
    }

    @Test
    public void validateEmptyId() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/emptyId.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        Tour tour = tourParser.parseTour(file, null, window);
        Step step = tour.getSteps().get(0);
        Assert.assertNotNull(step.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateEmptyCaption() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/emptyCaption.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        tourParser.parseTour(file, null, window);
    }

    @Test(expected = GuiDevelopmentException.class)
    public void validateIncorrectAction() {
        String file = resources.getResourceAsString("com/haulmont/addon/tour/parser/data/incorrectAction.json");

        Window window = testWebUiComponents.create(WebTabWindow.class);

        tourParser.parseTour(file, null, window);
    }

    protected void validateFirstStep(Step firstStep) {
        Assert.assertEquals(firstStep.getId(), "step1");
        Assert.assertEquals(firstStep.getAnchor(), StepAnchor.LEFT);
        Assert.assertEquals(firstStep.getAttachedTo(), textField1);

        Assert.assertEquals(firstStep.getHeight(), 600, 0);
        Assert.assertEquals(firstStep.getWidth(), 400, 0);

        Assert.assertEquals(firstStep.getText(), messages.getMessage("com/haulmont/scenario/messages.properties",
                "tour.createButtonText"));
        Assert.assertEquals(firstStep.getTitle(), messages.getMessage("com/haulmont/scenario/messages.properties",
                "tour.title"));
        Assert.assertEquals(firstStep.getTextContentMode(), ContentMode.HTML);
        Assert.assertEquals(firstStep.getTitleContentMode(), ContentMode.HTML);

        Assert.assertTrue(firstStep.isCancellable());
        Assert.assertTrue(firstStep.isScrollTo());
        Assert.assertFalse(firstStep.isModal());
        Assert.assertFalse(firstStep.isVisible());

        List<StepButton> firstStepButtons = firstStep.getButtons();
        Assert.assertEquals(firstStepButtons.size(), 2);

        validateFirstStepFirstButton(firstStepButtons.get(0));

        validateFirstStepSecondButton(firstStepButtons.get(1));
    }

    protected void validateFirstStepSecondButton(StepButton firstStepSecondButton) {
        Assert.assertEquals(firstStepSecondButton.getCaption(), "Next");
        Assert.assertEquals(firstStepSecondButton.getStyleName(), "primary");
        Assert.assertTrue(firstStepSecondButton.isEnabled());
    }

    protected void validateFirstStepFirstButton(StepButton firstStepFirstButton) {
        Assert.assertEquals(firstStepFirstButton.getCaption(), "Cancel");
        Assert.assertEquals(firstStepFirstButton.getStyleName(), "danger");
        Assert.assertFalse(firstStepFirstButton.isEnabled());
    }

    protected void validateSecondStep(Step secondStep) {
        Assert.assertEquals(secondStep.getId(), "step2");
        Assert.assertEquals(secondStep.getAnchor(), StepAnchor.TOP);
        Assert.assertEquals(secondStep.getAttachedTo(), textField2);

        Assert.assertEquals(secondStep.getText(), "text");
        Assert.assertEquals(secondStep.getTitle(), "Title");
        Assert.assertEquals(secondStep.getTextContentMode(), ContentMode.TEXT);
        Assert.assertEquals(secondStep.getTitleContentMode(), ContentMode.TEXT);

        Assert.assertFalse(secondStep.isCancellable());
        Assert.assertFalse(secondStep.isScrollTo());
        Assert.assertFalse(secondStep.isModal());
        Assert.assertFalse(secondStep.isVisible());

        List<StepButton> secondStepButtons = secondStep.getButtons();
        Assert.assertEquals(secondStepButtons.size(), 2);

        validateSecondStepFirstButton(secondStepButtons.get(0));

        validateSecondStepSecondButton(secondStepButtons.get(1));
    }

    protected void validateSecondStepFirstButton(StepButton secondStepFirstButton) {
        Assert.assertEquals(secondStepFirstButton.getCaption(), "Back");
        Assert.assertEquals(secondStepFirstButton.getStyleName(), "primary");
        Assert.assertTrue(secondStepFirstButton.isEnabled());

    }

    protected void validateSecondStepSecondButton(StepButton secondStepSecondButton) {
        Assert.assertEquals(secondStepSecondButton.getCaption(), "Finish");
        Assert.assertEquals(secondStepSecondButton.getStyleName(), "friendly");
        Assert.assertTrue(secondStepSecondButton.isEnabled());

    }
}
