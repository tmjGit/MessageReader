package de.oio.tmj.addressbook.client.rpc;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.oio.tmj.addressbook.shared.collection.IdentityArrayList;
import de.oio.tmj.addressbook.shared.model.Address;
import de.oio.tmj.addressbook.shared.model.Mail;
import de.oio.tmj.addressbook.shared.model.Person;
import de.oio.tmj.addressbook.shared.model.Phone;

@RemoteServiceRelativePath("../adrsrv")
public interface MessageReaderService extends RemoteService {
	String hello(String name);
	
//	void setItems(ArrayList<Person> items) throws RuntimeException;
	
	void replaceItem(Address oldItem, Address newItem) throws RuntimeException;
	void replaceItem(Mail oldItem, Mail newItem) throws RuntimeException;
	void replaceItem(Person oldItem, Person newItem) throws RuntimeException;
	void replaceItem(Phone oldItem, Phone newItem) throws RuntimeException;
	
	void removeItem(Address item) throws RuntimeException;
	void removeItem(Mail item) throws RuntimeException;
	void removeItem(Person item) throws RuntimeException;
	void removeItem(Phone item) throws RuntimeException;
	
	void addItem(Person item) throws RuntimeException;
	
	IdentityArrayList<Person> getItems() throws RuntimeException;
}
