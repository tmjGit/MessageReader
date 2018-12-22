package de.oio.tmj.addressbook.client.validator;

public abstract class Validator<T> {

	public String errorMessage;

	public abstract boolean validate(T value);

	public abstract String getErrorMessage();
}
