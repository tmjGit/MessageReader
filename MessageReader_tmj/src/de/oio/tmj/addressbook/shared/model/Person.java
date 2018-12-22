package de.oio.tmj.addressbook.shared.model;

import java.util.Date;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Person extends EntityBase{
	private static final long serialVersionUID = -3746087189424637397L;
	private static Function<Person, String> calculateDisplayStringFunction=person->person.getName();
	private int id;
//	private String guid;
	private String name;
	private String nick;
	private Date birthday;
	private String notes="";
	private List<Mail> mails=new ArrayList<>();
	private List<Address> addresses=new ArrayList<>();
	private List<Phone> phones=new ArrayList<>();
//	private AppMessages messages=GWT.create(AppMessages.class);// Destroys the serializabitily!
//	private Image thumbnail;
	
	
	protected Person() { }
	public Person(GWTguid guid) {
		super(guid);
	}
	
	public void setAll(Person person) throws NullPointerException {
		if(null==person) {
			throw new NullPointerException();
		}
		this.id=person.getId();
		this.name=person.getName();
		this.nick=person.getNick();
		this.birthday=person.getBirthday();
		this.notes=person.getNotes();
		this.mails=person.getMails();
		this.addresses=person.getAddresses();
		this.phones=person.getPhones();
//		this.thumbnail=person.getThumbnails();
	}
	

	public static void setCalculateDisplayString(Function<Person,String> calculateDisplayString) {
		calculateDisplayStringFunction=calculateDisplayString;
	}
	public String getDisplayString() {
		return calculateDisplayStringFunction.apply(this);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) throws NullPointerException {
		if(null==name) {
			throw new NullPointerException();//messages.personNameValidationErrorMessage());
		}
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) throws NullPointerException {
		if(null==nick) {
			throw new NullPointerException();//messages.personNameValidationErrorMessage());
		}
		this.nick = nick;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) throws NullPointerException {
		this.birthday = birthday;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) throws NullPointerException {
		this.notes = notes;
	}
	public List<Mail> getMails() {
		return mails;
	}
	public void setMails(List<Mail> mails) throws NullPointerException {
		this.mails = mails;
	}
	public void addMail(Mail mail) throws NullPointerException {
		getMails().add(mail);
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) throws NullPointerException {
		this.addresses = addresses;
	}
	public void addAddress(Address address) throws NullPointerException {
		getAddresses().add(address);
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) throws NullPointerException {
		this.phones = phones;
	}
	public void addPhone(Phone phone) throws NullPointerException {
		getPhones().add(phone);
	}
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", identityGUID=" + getIdentityGUID() + ", name=" + name + ", nick=" + nick 
				+ ", birthday=" + birthday 
				+ ", notes=" + notes
				+ ", mails=" + listToString(mails) + ", addresses=" + listToString(addresses) + ", phones=" + listToString(phones) + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + id;
		result = prime * result + ((mails == null) ? 0 : mails.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (id != other.id)
			return false;
		if (mails == null) {
			if (other.mails != null)
				return false;
		} else if (!mails.equals(other.mails))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (phones == null) {
			if (other.phones != null)
				return false;
		} else if (!phones.equals(other.phones))
			return false;
		return true;
	}

	private String listToString(List<?> list) {
		if(null==list) {
			return "null";
		}else {
			return Arrays.toString(list.toArray());
		}
	}
	
	
}
