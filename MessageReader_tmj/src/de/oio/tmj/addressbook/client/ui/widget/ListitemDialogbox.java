package de.oio.tmj.addressbook.client.ui.widget;

import java.util.function.Consumer;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.oio.tmj.addressbook.client.service.IdGenerator;
import de.oio.tmj.addressbook.client.ui.bundle.Resources;
import de.oio.tmj.addressbook.client.ui.bundle.Resources.Style;
import de.oio.tmj.addressbook.client.ui.lang.AppMessages;
import de.oio.tmj.addressbook.client.validator.PersonValidator;
import de.oio.tmj.addressbook.shared.model.Address;
import de.oio.tmj.addressbook.shared.model.Mail;
import de.oio.tmj.addressbook.shared.model.Person;
import de.oio.tmj.addressbook.shared.model.Phone;

public class ListitemDialogbox {
	private AppMessages messages=GWT.create(AppMessages.class);
	private Style style;
	private boolean validated=true;
	private Address displayPerson=new Address(IdGenerator.createGUID());
	private TextBox idTextbox;
	private TextBox labelTextbox;
	private ListBox partsListbox;
	private TextBox separatorTextbox;
	
	public ListitemDialogbox() {
		Resources resources=GWT.create(Resources.class);
		style=resources.style();
		style.ensureInjected();
	}


	public static void openCreateAddressWithDialogBox(Consumer<Address> onAddressCreated) {
		ListitemDialogbox listitemDialogBox=new ListitemDialogbox();
		listitemDialogBox.openAddressWithDialogBox(onAddressCreated);
	}
	
	public static void openCreateAddressDialogBox(Address address, Consumer<Address> onAddressChanged) {
		ListitemDialogbox entryWidgetDialogBox=new ListitemDialogbox();
		entryWidgetDialogBox.openEditAddressDialogBox_(address,onAddressChanged);
	}
	
	private void openAddressWithDialogBox(Consumer<Address> onAddressCreated) {
		openEditAddressDialogBox_(null, person->{
			Address address=new Address(IdGenerator.createGUID());
			onAddressCreated.accept(address);
		});
	}

	private void openEditAddressDialogBox_(Address address, Consumer<Address> onAddressChanged) {
		if(null!=address) {
			displayPerson.setAll(address);
		}
		DialogBox dialogBox=new DialogBox();
		FlowPanel dialogContent=new FlowPanel();
		
		idTextbox=new TextBox();
		idTextbox.addStyleName(style.dialogboxWidget());
//		idTextbox.getElement().setAttribute("placeholder", messages.entryName());
		idTextbox.setEnabled(false);
//		idTextbox.addValueChangeHandler(event-> {
//			validated=PersonValidator.namePassesValidationOrAlert(event.getValue());
//			if(validated) {
//				displayPerson.setName(event.getValue());
//			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//				((FocusWidget)event.getSource()).setFocus(true);
//			}
//		});
		dialogContent.add(idTextbox);
		
		separatorTextbox=new TextBox();
		separatorTextbox.addStyleName(style.dialogboxWidget());
//		separatorTextbox.getElement().setAttribute("placeholder", messages.entryNick());
//		if(null!=displayPerson.getNick()) {
//			separatorTextbox.setText(displayPerson.getNick());
//		}
		separatorTextbox.addValueChangeHandler(event-> {
			validated=PersonValidator.namePassesValidationOrAlert(event.getValue());
			if(validated) {
				Address.setPartSeparator(event.getValue());
			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
				((FocusWidget)event.getSource()).setFocus(true);
			}
		});
		dialogContent.add(separatorTextbox);
		
		partsListbox=new ListBox();
		partsListbox.addStyleName(style.dialogboxWidget());
//		partsListbox.getElement().setAttribute("placeholder", messages.entryNick());
//		if(null!=displayPerson.getMails()) {
////			partsListbox.setText(displayPerson.getMails());
//			for(Mail mail:displayPerson.getMails()) {
//				partsListbox.addItem(mail.toString());//TODO clickable, editable
//			}
////			partsListbox-Item.addValueChangeHandler(event-> {
////				validated=true;//PhoneValidator.phonePassesValidationOrAlert(event.getValue());
////				if(validated) {
////					Mail mail=partsListbox-Item.getSource();
////					mail.set-Inhalt(event.getValue());//		displayPerson.setMails(event.getValue());
////				}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
////					((FocusWidget)event.getSource()).setFocus(true);
////				}
////			});
//		}
////		partsListbox.addValueChangeHandler(event-> {//Telefonnummer hinzugefügt oder gelöscht?
////			validated=true;//MailValidator.mailPassesValidationOrAlert(event.getValue());
////			if(validated) {
////				...   displayPerson.setMails(event.getValue());
////			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
////				((FocusWidget)event.getSource()).setFocus(true);
////			}
////		});
		dialogContent.add(partsListbox);
			
		labelTextbox=new TextBox();
		labelTextbox.addStyleName(style.dialogboxWidget());
//		labelTextbox.getElement().setAttribute("placeholder", messages.entryNotes());
//		if(null!=displayPerson.getNotes()) {
//			labelTextbox.setText(displayPerson.getNotes());
//		}
		labelTextbox.addValueChangeHandler(event-> {
//			validated=PersonValidator.namePassesValidationOrAlert(event.getValue());
//			if(validated) {
				address.setLabel(event.getValue());
//			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//				((FocusWidget)event.getSource()).setFocus(true);
//			}
		});
		dialogContent.add(labelTextbox);
		
		loadData(address);

		Button okButton=new Button(messages.ok());		
		okButton.addClickHandler(e->{
			if(	validated ) {
				onAddressChanged.accept(displayPerson);
				dialogBox.hide();
			}
		});
		dialogContent.add(okButton);
		Button cancelButton=new Button(messages.cancel());		
		cancelButton.addClickHandler(e->{
			validated=true;
			dialogBox.hide();
		});
		dialogContent.add(cancelButton);
		dialogBox.add(dialogContent);
		dialogBox.center();
	}
	
	
	private void loadData(Address displayAddress) { // displayPerson must not be null!
		idTextbox.setText(String.valueOf(displayAddress.getId()));

		if(null!=displayAddress.getLabel()) {
			labelTextbox.setText(displayAddress.getLabel());
		}
		
		if(null!=displayAddress.getParts()) {
//			partsListbox.setText(displayAddress.getParts());
			for(String part:displayAddress.getParts()) {
				partsListbox.addItem(part);//TODO clickable, editable
			}
//			partsListbox-Item.addValueChangeHandler(event-> {
//				validated=true;//PhoneValidator.phonePassesValidationOrAlert(event.getValue());
//				if(validated) {
//					Mail mail=partsListbox-Item.getSource();
//					mail.set-Inhalt(event.getValue());//		displayPerson.setMails(event.getValue());
//				}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//					((FocusWidget)event.getSource()).setFocus(true);
//				}
//			});
		}
//		partsListbox.addValueChangeHandler(event-> {//Telefonnummer hinzugefügt oder gelöscht?
//			validated=true;//MailValidator.mailPassesValidationOrAlert(event.getValue());
//			if(validated) {
//				...   displayPerson.setMails(event.getValue());
//			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//				((FocusWidget)event.getSource()).setFocus(true);
//			}
//		});
		
//		if(null!=displayAddress.getNick()) {
//			separatorTextbox.setText(displayAddress.getNick());
//		}
	}
	
	
}
