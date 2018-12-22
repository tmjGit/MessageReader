//package de.oio.tmj.addressbook.client.ui.lang;
//
//import com.google.gwt.core.client.GWT;
//
//import de.oio.tmj.addressbook.shared.conf.Prop;
//
//public class Language {
//	public static Texts messages=GWT.create(Texts.class);
//
////	public static void main(String[] args) {
////		Texts messages=GWT.create(Texts.class);
////		System.out.println("Language: edit="+messages.edit());
////	}
//	
//	public static String newEntry() {
//		return "neuer Eintrag";
//	}
//
//	public static String title() {
//		return "Adressbuch";
//	}
//
//	public static String edit() {
//		return "edit";
//	}
//
//	public static String ok() {
//		return "OK";
//	}
//
//	public static String entryName() {
//		return "Name";
//	}
//
//	public static String cancel() {
//		return "Abbrechen";
//	}
//
//	public static String personNameValidationErrorMessage() {
//		String s="Names must not be empty and must be shorter than {} characters!";
//		return s.replaceFirst("{}", String.valueOf( Prop.personNameMaxLength() ));
//
//	}
//
//	public static String phoneNumberValidationErrorMessage() {
//		return "That is not a valid phone number!";
//	}
//
//	public static String phoneLabelValidationErrorMessage() {
//		return "That is not a valid phone label!";
//	}
//
//}
