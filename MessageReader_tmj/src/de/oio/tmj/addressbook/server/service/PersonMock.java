package de.oio.tmj.addressbook.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.oio.tmj.addressbook.shared.collection.IdentityArrayList;
import de.oio.tmj.addressbook.shared.model.Address;
import de.oio.tmj.addressbook.shared.model.Person;

@Component
public class PersonMock {
//	private static IdentityArrayList<Person> persons;

	//	@Autowired
//	private IdGenerator generator;

//	public static Person newPerson() {
//		Person person=new Person(()->generator.getNewId());
//		person.setName("abc");
//		return person;
//	}
//	{
//		createPersons();
//	}
	
	public IdentityArrayList<Person> createPersons(){
		Person person1=new Person(IdGenerator.createGUID());//()->generator.getNewId());
		person1.setName("Hugo");
		person1.setNick("Hug");
//		Address address1=new Address(()->generator.getNewId(),"Meierstraße 7, 22987 Hamburg, Germany");
		Address address1=new Address(IdGenerator.createGUID(),"Meierstraße 7, 22987 Hamburg, Germany");
		person1.addAddress(address1);
//		address1=new Address(()->generator.getNewId(),"Müllerweg 12","bei Meier");
		address1=new Address(IdGenerator.createGUID(),"Müllerweg 12","bei Meier");
		person1.addAddress(address1);
				
		Person person2=new Person(IdGenerator.createGUID());
//		Person person2=new Person(()->generator.getNewId());
		person2.setName("Marko");
		person2.setNick("Mark");
		address1=new Address(IdGenerator.createGUID(),"Am Weier 1, 11111 Berlin, Germany");
//		address1=new Address(()->generator.getNewId(),"Am Weier 1, 11111 Berlin, Germany");
		person2.addAddress(address1);
		address1=new Address(IdGenerator.createGUID(),"Badebach","Insel");
//		address1=new Address(()->generator.getNewId(),"Badebach","Insel");
		person2.addAddress(address1);
		
		IdentityArrayList<Person> persons=new IdentityArrayList<>();
		persons.add(person1);
		persons.add(person2);
		
//		System.out.println(this.getClass().getSimpleName()+".createPersons: person1="+persons.indexOf(person1)+"="+person1);
//		System.out.println(this.getClass().getSimpleName()+".createPersons: person2="+persons.indexOf(person2)+"="+person2);
		
//		if(null==PersonMock.persons) {
//			System.out.println(this.getClass().getSimpleName()+": persons=null -> create.");
//			PersonMock.persons=persons;
//		}else {
//			System.out.println(this.getClass().getSimpleName()+": persons!=null -> ok");
//		}
		
		return persons;
	}
	
//	public static IdentityArrayList<Person> getPersons(){
//		return persons;
//	}
	
//	public ArrayList<Person> createPersons(){
//		Person person1=new Person(()->generator.getNewId());
//		person1.setName("Hugo");
//		person1.setNick("Hug");
//		Address address1=new Address(()->generator.getNewId(),"Meierstraße 7, 22987 Hamburg, Germany");
//		person1.addAddress(address1);
//		address1=new Address(()->generator.getNewId(),"Müllerweg 12","bei Meier");
//		person1.addAddress(address1);
//				
//		Person person2=new Person(()->generator.getNewId());
//		person2.setName("Marko");
//		person2.setNick("Mark");
//		address1=new Address(()->generator.getNewId(),"Am Weier 1, 11111 Berlin, Germany");
//		person2.addAddress(address1);
//		address1=new Address(()->generator.getNewId(),"Badebach","Insel");
//		person2.addAddress(address1);
//		
//		ArrayList<Person> persons=new ArrayList<>();
//		persons.add(person1);
//		persons.add(person2);
//		
//		return persons;
//	}
}
