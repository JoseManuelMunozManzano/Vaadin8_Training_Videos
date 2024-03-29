package com.vaadin.training.framework.exercises.ex2;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ApplicationLayout extends VerticalLayout implements View {

	private HorizontalLayout layout;

	public ApplicationLayout() {
		Label header = new Label("This is the header. My height is 150 pixels");
		header.setWidth("100%");
		header.setStyleName("header");
		header.setHeight("150px");

		layout = new HorizontalLayout();
		layout.setSizeFull();
		createLabelLayout();

		Label footer = new Label(
				"This is the footer area. My height is 100 pixels");
		footer.setWidth("100%");
		footer.setStyleName("footer");
		footer.setHeight("100px");

		addComponent(header);
		addComponent(layout);

		addComponent(footer);

		setExpandRatio(layout, 1);
		setSizeFull();
		setSpacing(false);
		setMargin(false);
	}

	private void createLabelLayout() {
		Label navigation = new Label(
				"This is the navigation area. My width is 25% of the window.");
		navigation.setStyleName("navigation");
		navigation.setSizeFull();

		Label content = new Label("This is the content area");
		content.setStyleName("content");
		content.setSizeFull();

		layout.setSpacing(false);
		layout.addComponent(navigation);
		layout.addComponent(content);
		layout.setExpandRatio(navigation, 1);
		layout.setExpandRatio(content, 3);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if ("scroll".equals(event.getParameters())) {
			layout.removeAllComponents();
			createScrollLayout();
		}

	}

	private void createScrollLayout() {
		Panel panel = new Panel();
		final CssLayout panelContent = new CssLayout();
		panel.setContent(panelContent);
		panel.setSizeFull();

		Button addButton = new Button("Add", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Button button = createButton();
				panelContent.addComponent(button);
			}
		});

		layout.addComponent(addButton);
		layout.addComponent(panel);
		layout.setExpandRatio(addButton, 1);
		layout.setExpandRatio(panel, 3);
	}

	/**
	 * Ignore this method for now.
	 *
	 * @return
	 */
	private Button createButton() {
		NativeButton button = new NativeButton("Ignore");
		button.setHeight("200px");
		button.setWidth("200px");
		return button;
	}

}
