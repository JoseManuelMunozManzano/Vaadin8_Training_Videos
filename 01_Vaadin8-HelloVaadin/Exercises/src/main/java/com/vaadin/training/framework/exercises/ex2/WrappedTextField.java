package com.vaadin.training.framework.exercises.ex2;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class WrappedTextField extends CustomComponent {

	private static final long serialVersionUID = 1L;

	private TextField textField;
	private Label errorMessageLabel;

	public WrappedTextField() {
		// TODO Create an instance of TextField and Label and assign them to the
		// fields declared above
		textField = new TextField();
		errorMessageLabel = new Label();

		// TODO Create a new HorizontalLayout and set it as the composition root
		// of this CustomComponent
		final HorizontalLayout content = new HorizontalLayout();

		// TODO Add the textField and errorMessageLabel to the HorizontalLayout
		content.addComponent(textField);
		content.addComponent(errorMessageLabel);

		// Importantísimo añadir esto para que funcione.
		setCompositionRoot(content);
	}

	public String getText() {
		return textField.getValue();
	}

	public void setErrorMessage(String errorMessage) {
		errorMessageLabel.setValue(errorMessage);
	}
}
