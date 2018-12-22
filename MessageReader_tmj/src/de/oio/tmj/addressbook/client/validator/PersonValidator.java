package de.oio.tmj.addressbook.client.validator;

import java.util.Date;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;

import de.oio.tmj.addressbook.client.ui.lang.AppMessages;
import de.oio.tmj.addressbook.shared.conf.AppProperties;
import de.oio.tmj.addressbook.shared.model.Address;
import de.oio.tmj.addressbook.shared.model.Mail;
import de.oio.tmj.addressbook.shared.model.Person;
import de.oio.tmj.addressbook.shared.model.Phone;

public class PersonValidator {
	private static AppProperties prop=GWT.create(AppProperties.class);
	private static AppMessages messages=GWT.create(AppMessages.class);

	//	public static void main(String[] args) {
//		Person person=new Person();
//		Mail mail=new Mail("test","hallo@faa.boo");
//		person.addMail(mail);
//		Address 		address=new Address("Müllerweg 12","bei Meier");
//		address.addPart("München");
//		person.addAddress(address);
//		Phone phone=new Phone("+700-Call BMW");
//		person.addPhone(phone);
//		
//		System.out.println("person="+person);
//	}
//	
//	public void setAll(Person person){
//		this.id=person.getId();
//		this.name=person.getName();
//		this.nick=person.getNick();
//		this.birthday=person.getBirthday();
//		this.notes=person.getNotes();
//		this.mails=person.getMails();
//		this.addresses=person.getAddresses();
//		this.phones=person.getPhones();
////		this.thumbnail=person.getThumbnails();
//	}
	
	public static boolean namePassesValidationOrAlert(String name) {
//		String message=nameValidationErrorMessages(name);
//		if("".equals(message)) {
//			return true;
//		}else {
//			Window.alert(message);
//			return false;
//		}
		if(namePassesValidation(name)) {
			return true;
		}else {
			Window.alert(messages.personNameValidationErrorMessage());
			return false;
		}
	}
//	public static String nameValidationErrorMessages(String name) {
//		if(namePassesValidation(name)) {
//			return ""; // no error and no error message
//		}else {
//			return Language.personNameValidationErrorMessage();
//		}
//	}
	public static boolean namePassesValidation(String name) {
		return (0<name.length() && prop.personNameMaxLength()>name.length());
	}
	
	public static Validator<String> createPersonNameValidator() {
		return new Validator<String>() {
			@Override
			public boolean validate(String value) {
				return PersonValidator.namePassesValidation(value);
			}
			
			@Override
			public String getErrorMessage() {
				return messages.personNameValidationErrorMessage();
			}
		};
	}

	public static boolean birthdayPassesValidationOrAlert(Date date) {
//		String message=birthdayValidationErrorMessages(date);
//		if("".equals(message)) {
//			return true;
//		}else {
//			Window.alert(message);
//			return false;
//		}
		if(birthdayPassesValidation(date)) {
			return true;
		}else {
			Window.alert("bla fasel");
			return false;
		}
	}
//	public static String birthdayValidationErrorMessages(Date date) {
//		if(birthdayPassesValidation(date)) {
//			return ""; // no error and no error message
//		}else {
//			return "bla fasel";
//		}
//	}
	public static boolean birthdayPassesValidation(Date date) {
		return true; // TODO 		
	}
	
	public static Validator<Date> createBirthdayValidator() {
		return new Validator<Date>() {
			@Override
			public boolean validate(Date value) {
				return PersonValidator.birthdayPassesValidation(value);
			}
			
			@Override
			public String getErrorMessage() {
				return "Dummy msg: messages.birthdayValidationErrorMessages()";//TODO
			}
		};
	}
	
	
}
