package de.oio.tmj.addressbook.client.validator;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;

import de.oio.tmj.addressbook.client.service.IdGenerator;
import de.oio.tmj.addressbook.client.ui.lang.AppMessages;
import de.oio.tmj.addressbook.shared.model.Phone;

public class PhoneValidator {
	private static AppMessages messages=GWT.create(AppMessages.class);

	public static void main(String[] args) {
		test("+49/40/389 29-0");
		test("+4\\9/40/38z cZ-0");
		test("0700-Call BMW");
		test("+700-Call BMW");
		test("0700+Call BMW");
	}

	private static void test(String number) {
		try {
			System.out.println("number="+number+", validates="+Phone.passesValidation(number)+", "+Phone.validationFailResult(number));
			Phone phone=new Phone(IdGenerator.createGUID(),number);
			System.out.println("phone="+phone.getNumber()+", clean="+phone.signifikat());
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		System.out.println();
	}

	public static boolean phonePassesValidationOrAlert(String number) {
		if(Phone.passesValidation(number)) {
			return true;
		}else {
			Window.alert(messages.phoneNumberValidationErrorMessage());
			return false;
		}
	}
//	public static boolean phonePassesValidation(String phone) {
//		return phone.matches(VALIDATION_MATCHER);
////		return "".equals(validationFailResult(phone)); 
//	}
	
//	public static String validationFailResult(String phone) {
//		return phone.replaceAll(PHONE_ALPHABET, "");
//	}
	
	public static Validator<String> createPhoneNumberValidator() {
		return new Validator<String>() {
			@Override
			public boolean validate(String value) {
				return Phone.passesValidation(value);
			}
			
			@Override
			public String getErrorMessage() {
				return messages.phoneNumberValidationErrorMessage();
			}
		};
	}
	
	
}
