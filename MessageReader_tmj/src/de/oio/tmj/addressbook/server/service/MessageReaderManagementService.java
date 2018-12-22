package de.oio.tmj.addressbook.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.oio.tmj.addressbook.shared.collection.IdentityArrayList;
import de.oio.tmj.addressbook.shared.model.Address;
import de.oio.tmj.addressbook.shared.model.Mail;
import de.oio.tmj.addressbook.shared.model.Person;
import de.oio.tmj.addressbook.shared.model.Phone;

@Component
public class MessageReaderManagementService {
	private static final long serialVersionUID = 1L;
	private IdentityArrayList<Person> persons;
//	private ArrayList<Person> persons;//=PersonMock.createPersons();//TODO JPA
	private final IdGenerator generator;
	
	@Autowired
	public MessageReaderManagementService(IdGenerator generator,PersonMock personMock) {
		System.out.println(this.getClass().getName()+" init **************");//TODO debug
		this.generator=generator;
		persons=personMock.createPersons();
//		persons=personMock.getPersons();
	}
//	@Autowired
//	public MessageReaderManagementService(IdGenerator generator) {
//		System.out.println(this.getClass().getName()+" init **************");//TODO debug
//		this.generator=generator;
//	}

		public String hello(String name) {
			return "Hello, "+name+"!";
		}

//		@Override
//		public void setItems(ArrayList<Person> items)  throws RuntimeException{
//			persons.clear();
////			AddressBook.display("TableWidget.setItems(persons):","new=",values,"now=",dataProvider.getList(),"addAll...");
//			persons.addAll(items);
////			AddressBook.display("TableWidget.setItems(persons):","new=",values,"now=",dataProvider.getList(),"sort...");
////			ColumnSortEvent.fire(table, table.getColumnSortList());
//		}

		public void replaceItem(Address oldItem, Address newItem)  throws RuntimeException{
//			addresses.set( addresses.indexOf(oldItem), newItem);
		}
		public void replaceItem(Mail oldItem, Mail newItem)  throws RuntimeException{
//			mails.set( mails.indexOf(oldItem), newItem);
		}
		public void replaceItem(Person oldItem, Person newItem) throws RuntimeException{
//			System.out.println(this.getClass().getSimpleName()+".replaceItem: persons="+Arrays.toString(persons.toArray()));
//			Person person=persons.get(0);
//			System.out.println(this.getClass().getSimpleName()+".replaceItem: person1="+persons.indexOf(person)+"="+person);
//			person=persons.get(1);
//			System.out.println(this.getClass().getSimpleName()+".replaceItem: person2="+persons.indexOf(person)+"="+person);
//			System.out.println(this.getClass().getSimpleName()+"******************************************");
			int index=persons.indexOf(oldItem);
//			System.out.println(this.getClass().getSimpleName()+".replaceItem: oldItem="+index+"="+oldItem+"\n   newItem="+newItem);
			if(-1<index) {
				persons.set( index, newItem);
			}else {
				System.err.println("Error: This Person could not be found in the list! "+oldItem);
				throw new RuntimeException("Error: This Person could not be found in the list! "+oldItem);
			}
		}
		public void replaceItem(Phone oldItem, Phone newItem) throws RuntimeException {
//			phones.set( phones.indexOf(oldItem), newItem);
		}

		public void removeItem(Address item)  throws RuntimeException{
//			item.registerWithId(()->generator.getNewId());
//			addresses.remove( addresses.indexOf(item) );
		}
		public void removeItem(Mail item) throws RuntimeException {
//			item.registerWithId(()->generator.getNewId());
//			mails.remove( mails.indexOf(item) );
		}
		public void removeItem(Person item) throws RuntimeException {
//			item.registerWithId(()->generator.getNewId());
			persons.remove( persons.indexOf(item) );
		}
		public void removeItem(Phone item) throws RuntimeException {
//			item.registerWithId(()->generator.getNewId());
//			phones.remove( phones.indexOf(item) );
		}

		public void addItem(Person item)  throws RuntimeException{
//			int id=generator.getNewId();
//			System.out.println("AMS item="+item+", new ID="+id);
//			item.registerWithId(()->generator.getNewId());//PersonDAO.register(item);
			persons.add( item );
		}

		public IdentityArrayList<Person> getItems()  throws RuntimeException{
//			System.out.println(this.getClass().getSimpleName()+".getItems: persons="+Arrays.toString(persons.toArray()));
//			Person person=persons.get(0);
//			System.out.println(this.getClass().getSimpleName()+".getItems: person1="+persons.indexOf(person)+"="+person);
//			person=persons.get(1);
//			System.out.println(this.getClass().getSimpleName()+".getItems: person2="+persons.indexOf(person)+"="+person);
			return persons;
		}

	
}
