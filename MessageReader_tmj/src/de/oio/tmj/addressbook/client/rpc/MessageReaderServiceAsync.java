package de.oio.tmj.addressbook.client.rpc;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.oio.tmj.addressbook.shared.collection.IdentityArrayList;
import de.oio.tmj.addressbook.shared.model.Address;
import de.oio.tmj.addressbook.shared.model.Mail;
import de.oio.tmj.addressbook.shared.model.Person;
import de.oio.tmj.addressbook.shared.model.Phone;

public interface MessageReaderServiceAsync {
	//Asynchrones Interface für Callback nötig, da der Client asynchron arbeitet.
	//Serverseitig wird es synchron umgesetzt.
	void hello(String name, AsyncCallback<String> callback);

//	void setItems(ArrayList<Person> items, AsyncCallback<Void> callback);

	void replaceItem(Address oldItem, Address newItem, AsyncCallback<Void> callback) throws RuntimeException;
	void replaceItem(Mail oldItem, Mail newItem, AsyncCallback<Void> callback) throws RuntimeException;
	void replaceItem(Person oldItem, Person newItem, AsyncCallback<Void> callback) throws RuntimeException;
	void replaceItem(Phone oldItem, Phone newItem, AsyncCallback<Void> callback) throws RuntimeException;

	void removeItem(Address item, AsyncCallback<Void> callback) throws RuntimeException;
	void removeItem(Mail item, AsyncCallback<Void> callback) throws RuntimeException;
	void removeItem(Person item, AsyncCallback<Void> callback) throws RuntimeException;
	void removeItem(Phone item, AsyncCallback<Void> callback) throws RuntimeException;

	void addItem(Person item, AsyncCallback<Void> callback) throws RuntimeException;

	void getItems(AsyncCallback<IdentityArrayList<Person>> callback) throws RuntimeException;

}
