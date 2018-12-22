package de.oio.tmj.addressbook.client.ui.widget;

import java.util.List;
import java.util.function.Consumer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import de.oio.tmj.addressbook.client.rpc.MessageReaderService;
import de.oio.tmj.addressbook.client.rpc.MessageReaderServiceAsync;
import de.oio.tmj.addressbook.client.service.IdGenerator;
import de.oio.tmj.addressbook.client.validator.PersonValidator;
import de.oio.tmj.addressbook.shared.collection.IdentityArrayList;
import de.oio.tmj.addressbook.shared.model.Address;
import de.oio.tmj.addressbook.shared.model.Mail;
import de.oio.tmj.addressbook.shared.model.Person;
import de.oio.tmj.addressbook.shared.model.Phone;

public class PersonEditDialogbox extends EntityEditDialogbox<Person>{
	@UiField ValidateableTextbox nameTextbox;
	@UiField ValidateableTextbox nickTextbox;
	@UiField ListBox addressesListbox;
	@UiField ListBox phoneListbox;
	@UiField Button newMailButton;
	@UiField ListBox mailListbox;
	@UiField DatePicker birthdayPicker;
	@UiField TextBox notesTextbox;
	private static PersonEditDialogboxUiBinder uiBinder = GWT.create(PersonEditDialogboxUiBinder.class);

	interface PersonEditDialogboxUiBinder extends UiBinder<Widget, PersonEditDialogbox> {	}
	
	public PersonEditDialogbox() {
		super();
		setWidget(uiBinder.createAndBindUi(this));
		displayObject=new Person(IdGenerator.createGUID());
	}

 
	public static void openNewEntityEditDialogbox(Consumer<Person> onEntityChanged) {
		PersonEditDialogbox entityEditDialogbox=new PersonEditDialogbox();
		entityEditDialogbox.init((Person) null, onEntityChanged);
	}
	public static void openEntityEditDialogBox(List<Person> list,Consumer<List<Person>> onListChanged) {
		PersonEditDialogbox entityEditDialogBox=new PersonEditDialogbox();
		entityEditDialogBox.init(list,onListChanged);
	}
	
	public static void openEntityEditDialogBox(Person entity, Consumer<Person> onEntityChanged) {
		PersonEditDialogbox entryWidgetDialogBox=new PersonEditDialogbox();
		entryWidgetDialogBox.init(entity,onEntityChanged);
	}
	
	private void init(List<Person> entitys, Consumer<List<Person>> onListChanged) {
		init((Person) null, entity->{
			entitys.add(entity);
			onListChanged.accept(entitys);
		});
	}

	@Override
	protected void init(Person entity, Consumer<Person> onEntityChanged) {
		MessageReaderServiceAsync addressbookService=GWT.create(MessageReaderService.class);
		if(null!=entity) {
			displayObject.setAll(entity);
		}
		
		nameTextbox.getElement().setAttribute("placeholder", messages.entryName());
		nameTextbox.addValidator(PersonValidator.createPersonNameValidator());
		nameTextbox.addValueChangeHandler(event-> {
			if(nameTextbox.validate()) {
				displayObject.setName(event.getValue());
			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
				((FocusWidget)event.getSource()).setFocus(true);
			}
		});
		
		nickTextbox.getElement().setAttribute("placeholder", messages.entryNick());
		nickTextbox.addValidator(PersonValidator.createPersonNameValidator());
		nickTextbox.addValueChangeHandler(event-> {
			if(nickTextbox.validate()) {
				displayObject.setNick(event.getValue());
			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
				((FocusWidget)event.getSource()).setFocus(true);
			}
		});
		
//		addressesListbox.getElement().setAttribute("placeholder", messages.entryNick());
//		if(null!=displayObject.getAddresses()) {
////			addressesListbox.setText(displayObject.getAddresses());
//			for(Address address:displayObject.getAddresses()) {
//				addressesListbox.addItem(address.toString());//TODO clickable, editable
//			}
////			addressesListbox-Item.addValueChangeHandler(event-> {
////				validated=true;//PersonValidator.namePassesValidationOrAlert(event.getValue());
////				if(validated) {
////					Address address=addressesListbox-Item.getSource();
////					address.set-Inhalt(event.getValue());//		displayObject.setAddresses(event.getValue());
////				}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
////					((FocusWidget)event.getSource()).setFocus(true);
////				}
////			});
//		}
////		addressesListbox.addValueChangeHandler(event-> {//Adresse hinzugefügt oder gelöscht?
////			validated=true;//PersonValidator.namePassesValidationOrAlert(event.getValue());
////			if(validated) {
////				...   displayObject.setAddresses(event.getValue());
////			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
////				((FocusWidget)event.getSource()).setFocus(true);
////			}
////		});
		
//		phoneListbox.getElement().setAttribute("placeholder", messages.entryNick());
//		if(null!=displayObject.getPhones()) {
////			phoneListbox.setText(displayObject.getPhones());
//			for(Phone phone:displayObject.getPhones()) {
//				phoneListbox.addItem(phone.toString());//TODO clickable, editable
//			}
////			phoneListbox-Item.addValueChangeHandler(event-> {
////				validated=true;//PhoneValidator.phonePassesValidationOrAlert(event.getValue());
////				if(validated) {
////					Phone phone=phoneListbox-Item.getSource();
////					phone.set-Inhalt(event.getValue());//		displayObject.setPhones(event.getValue());
////				}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
////					((FocusWidget)event.getSource()).setFocus(true);
////				}
////			});
//		}
////		phoneListbox.addValueChangeHandler(event-> {//Telefonnummer hinzugefügt oder gelöscht?
////			validated=true;//PhoneValidator.phonePassesValidationOrAlert(event.getValue());
////			if(validated) {
////				...   displayObject.setPhones(event.getValue());
////			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
////				((FocusWidget)event.getSource()).setFocus(true);
////			}
////		});
		
		newMailButton.addClickHandler(event->{
			MailEditDialogbox.openNewEntityEditDialogbox(mail->{
					displayObject.addMail(mail);
					mailListbox.addItem(mail.getDisplayString());
			});
		});
		
//		mailListbox.getElement().setAttribute("placeholder", messages.entryNick());
//		if(null!=displayObject.getMails()) {
////			mailListbox.setText(displayObject.getMails());
//			for(Mail mail:displayObject.getMails()) {
//				mailListbox.addItem(mail.toString());//TODO clickable, editable
//			}
////			mailListbox-Item.addValueChangeHandler(event-> {
////				validated=true;//PhoneValidator.phonePassesValidationOrAlert(event.getValue());
////				if(validated) {
////					Mail mail=mailListbox-Item.getSource();
////					mail.set-Inhalt(event.getValue());//		displayObject.setMails(event.getValue());
////				}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
////					((FocusWidget)event.getSource()).setFocus(true);
////				}
////			});
//		}
////		mailListbox.addValueChangeHandler(event-> {//Telefonnummer hinzugefügt oder gelöscht?
////			validated=true;//MailValidator.mailPassesValidationOrAlert(event.getValue());
////			if(validated) {
////				...   displayObject.setMails(event.getValue());
////			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
////				((FocusWidget)event.getSource()).setFocus(true);
////			}
////		});
		
//		final TextBox tb = new TextBox();
		mailListbox.addKeyDownHandler(new KeyDownHandler() {
		    @Override
		    public void onKeyDown(KeyDownEvent event) {
		    	int key=event.getNativeKeyCode();
	    		int index=mailListbox.getSelectedIndex();
		    	if(KeyCodes.KEY_DELETE==key) {
		    		if(0<=index) {
		    			displayObject.getMails().remove(index);
		    			mailListbox.removeItem(index);
		    		}
		    	}else if(KeyCodes.KEY_ENTER==key) {
		    		if(0<=index) {
		    			Mail oldValue=displayObject.getMails().get(index);
		    			MailEditDialogbox.openEntityEditDialogBox(oldValue,newValue->{
		    				displayObject.getMails().set(index,newValue);// displayObject.replaceItem(oldValue,newValue);
		    				mailListbox.setItemText(index,newValue.getDisplayString());
		    			});
		    		}
		    	}else {
			    	Window.alert("KeyDownEvent="+key+", delete?="+KeyCodes.KEY_DELETE+", index="+index);
		    	}
		    }
		});
//		Button b = new Button("keyevent");
//		b.addClickHandler(new ClickHandler() {
//		    @Override
//		    public void onClick(ClickEvent event) {
//		    DomEvent.fireNativeEvent(Document.get().createKeyDownEvent(false, false, false, false, KeyCodes.KEY_ENTER), tb);
//		    }
//		});
//		RootPanel.get().add(tb);
//		RootPanel.get().add(b);
		
		
//		if(null!=displayObject.getBirthday()) {// TODO: Wie kann man das Datum programmatisch setzen? So, dass die anzeige dahin springt!
//			birthdayPicker.setValue(displayObject.getBirthday(),true);
//		}
		birthdayPicker.addValueChangeHandler(event-> {
			if( PersonValidator.birthdayPassesValidationOrAlert(event.getValue()) ) {
				displayObject.setBirthday(event.getValue());
			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
				((FocusWidget)event.getSource()).setFocus(true);
			}
		});
		
		notesTextbox.getElement().setAttribute("placeholder", messages.entryNotes());
//		notesTextbox.addValidator(PersonValidator.createPersonNameValidator());
		notesTextbox.addValueChangeHandler(event-> {
//			if(((ValidateableTextbox)event.getSource()).validate()) {
				displayObject.setNotes(event.getValue());
//			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//				((FocusWidget)event.getSource()).setFocus(true);
//			}
		});
		
		loadData(displayObject);

		okButton.addClickHandler(e->{
			if( nameTextbox.validate() 
					&& nickTextbox.validate() 
					&& PersonValidator.birthdayPassesValidationOrAlert(birthdayPicker.getValue())
				){
				onEntityChanged.accept(displayObject);
				hide();
			}
		});
		cancelButton.addClickHandler(e->{
			hide();
		});
		
		center();
	}
	
	
	@Override
	protected  void loadData(Person displayObject) { // displayObject must not be null!
		if(null!=displayObject.getName()) {
			nameTextbox.setText(displayObject.getName());
		}
		
		if(null!=displayObject.getNick()) {
			nickTextbox.setText(displayObject.getNick());
		}
		
		if(null!=displayObject.getAddresses()) {
//			addressesListbox.setText(displayObject.getAddresses());
			for(Address address:displayObject.getAddresses()) {
				addressesListbox.addItem(address.getDisplayString());//TODO clickable, editable
			}
//			addressesListbox-Item.addValueChangeHandler(event-> {
//				validated=true;//PersonValidator.namePassesValidationOrAlert(event.getValue());
//				if(validated) {
//					Address address=addressesListbox-Item.getSource();
//					address.set-Inhalt(event.getValue());//		displayObject.setAddresses(event.getValue());
//				}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//					((FocusWidget)event.getSource()).setFocus(true);
//				}
//			});
		}
//		addressesListbox.addValueChangeHandler(event-> {//Adresse hinzugefügt oder gelöscht?
//			validated=true;//PersonValidator.namePassesValidationOrAlert(event.getValue());
//			if(validated) {
//				...   displayObject.setAddresses(event.getValue());
//			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//				((FocusWidget)event.getSource()).setFocus(true);
//			}
//		});

		if(null!=displayObject.getPhones()) {
//			phoneListbox.setText(displayObject.getPhones());
			for(Phone phone:displayObject.getPhones()) {
				phoneListbox.addItem(phone.getDisplayString());//TODO clickable, editable
			}
//			phoneListbox-Item.addValueChangeHandler(event-> {
//				validated=true;//PhoneValidator.phonePassesValidationOrAlert(event.getValue());
//				if(validated) {
//					Phone phone=phoneListbox-Item.getSource();
//					phone.set-Inhalt(event.getValue());//		displayObject.setPhones(event.getValue());
//				}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//					((FocusWidget)event.getSource()).setFocus(true);
//				}
//			});
		}
//		phoneListbox.addValueChangeHandler(event-> {//Telefonnummer hinzugefügt oder gelöscht?
//			validated=true;//PhoneValidator.phonePassesValidationOrAlert(event.getValue());
//			if(validated) {
//				...   displayObject.setPhones(event.getValue());
//			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//				((FocusWidget)event.getSource()).setFocus(true);
//			}
//		});

		if(null!=displayObject.getMails()) {
//			mailListbox.setText(displayObject.getMails());
			for(Mail mail:displayObject.getMails()) {
				mailListbox.addItem(mail.getDisplayString());//TODO clickable, editable
			}
//			mailListbox-Item.addValueChangeHandler(event-> {
//				validated=true;//PhoneValidator.phonePassesValidationOrAlert(event.getValue());
//				if(validated) {
//					Mail mail=mailListbox-Item.getSource();
//					mail.set-Inhalt(event.getValue());//		displayObject.setMails(event.getValue());
//				}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//					((FocusWidget)event.getSource()).setFocus(true);
//				}
//			});
		}
//		mailListbox.addValueChangeHandler(event-> {//Telefonnummer hinzugefügt oder gelöscht?
//			validated=true;//MailValidator.mailPassesValidationOrAlert(event.getValue());
//			if(validated) {
//				...   displayObject.setMails(event.getValue());
//			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
//				((FocusWidget)event.getSource()).setFocus(true);
//			}
//		});

		if(null!=displayObject.getBirthday()) {
			birthdayPicker.setValue(displayObject.getBirthday(),true);
		}

		if(null!=displayObject.getNotes()) {
			notesTextbox.setText(displayObject.getNotes());
		}
	}
	
	
}