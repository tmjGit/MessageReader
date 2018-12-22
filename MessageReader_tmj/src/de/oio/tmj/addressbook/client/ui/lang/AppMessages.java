package de.oio.tmj.addressbook.client.ui.lang;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.Messages;
import de.oio.tmj.addressbook.shared.conf.AppProperties;

public interface AppMessages extends Messages{
	@DefaultMessage("cancel") String cancel();
	@DefaultMessage("edit") String edit();
	@DefaultMessage("entryName") String entryName();
	@DefaultMessage("entryNick") String entryNick();
	@DefaultMessage("entryNotes") String entryNotes();
	@DefaultMessage("newEntry") String newEntry();
	@DefaultMessage("ok") String ok();

	/**
	 * Don't use this Method. Use the preconfigured overload {@link personNameValidationErrorMessage()} instead.
	 * @param personNameMaxLength – configureable maximum. The same value used for validation should be inserted here!
	 * @return – The error message with incorporated maximum number.
	 */
	@DefaultMessage("personNameValidationErrorMessage maxLength{0}") String personNameValidationErrorMessage(int personNameMaxLength);
	
	default String personNameValidationErrorMessage() {
		AppProperties prop=GWT.create(AppProperties.class);
		return personNameValidationErrorMessage(prop.personNameMaxLength());

	}

	@DefaultMessage("phoneNumberValidationErrorMessage") String phoneNumberValidationErrorMessage();
	@DefaultMessage("phoneLabelValidationErrorMessage") String phoneLabelValidationErrorMessage();
	@DefaultMessage("title") String title();


}