package de.oio.tmj.addressbook.shared.model;

import java.util.function.Function;

public class Mail extends EntityBase{
	private static final long serialVersionUID = -2564985022258959326L;
	private int id;
	private String address;
	private String label="";
	private static Function<Mail, String> calculateDisplayStringFunction=mail->("".equals(mail.label)?"":mail.label+": ")+mail.address;
	
	protected Mail() { }

	protected Mail(GWTguid guid) {
		super(guid);
	}
	
	public Mail(GWTguid guid,String address) {
		this(guid);
		setAddress(address);
	}
	public Mail(GWTguid guid,String label,String address) {
		this(guid,address);
		this.label=label;
	}
	
	public void setAll(Mail mail) throws NullPointerException {
		if(null==mail) {
			throw new NullPointerException();
		}
		this.id=mail.getId();
		this.address=mail.getAddress();
		this.label=mail.getLabel();
	}

	public String getAddress() {
		return address;
	}
	
	public static void setCalculateDisplayString(Function<Mail,String> calculateDisplayString) {
		calculateDisplayStringFunction=calculateDisplayString;
	}
	public String getDisplayString() {
		return calculateDisplayStringFunction.apply(this);
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + id;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		Mail other = (Mail) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id != other.id)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Mail [id=" + id + ", identityGUID=" + getIdentityGUID() + ", address=" + address + ", label=" + label + "]";
	}

}
