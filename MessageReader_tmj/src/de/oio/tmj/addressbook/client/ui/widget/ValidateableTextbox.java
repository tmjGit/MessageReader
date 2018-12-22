package de.oio.tmj.addressbook.client.ui.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.TextBox;

import de.oio.tmj.addressbook.client.validator.Validator;

public class ValidateableTextbox extends TextBox {

	private static final String TEXTBOX_VALIDATION_ERROR_STYLE = "error-text-box";
	private String errorMessage = "";
	private List<Validator<String>> validators = new ArrayList<>();

	public ValidateableTextbox() {
		super();
	}

	public ValidateableTextbox(String name) {
		this();
		setName(name);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void addValidator(Validator<String> validator) {
		validators.add(validator);
	}

	public boolean validate() {
		boolean validationResult = true;
		for (Validator<String> validator : validators) {
			validationResult = validator.validate(getValue());
			errorMessage = validator.getErrorMessage();
			if (!validationResult) { break; }
		}
		setErrorStyles(validationResult);
		return validationResult;
	}

	private void setErrorStyles(boolean validationResult) {
		if (validationResult) {
			removeStyleName(TEXTBOX_VALIDATION_ERROR_STYLE);
			setTitle("");
		} else {
			addStyleName(TEXTBOX_VALIDATION_ERROR_STYLE);
			setTitle(errorMessage);
		}
	}

	@Override
	public void setValue(String s) {
		removeStyleDependentName(TEXTBOX_VALIDATION_ERROR_STYLE);
		super.setValue(s);
	}

	@Override
	public String getValue() {
		return super.getValue();
	}
} 