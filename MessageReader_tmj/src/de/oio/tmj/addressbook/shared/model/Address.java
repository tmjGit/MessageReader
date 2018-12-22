package de.oio.tmj.addressbook.shared.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Address extends EntityBase{
	private static final long serialVersionUID = -8040213151274101012L;
	private static Function<Address, String> calculateDisplayStringFunction=address->("".equals(address.label)?"":address.label+": ")+address.asString();
	private int id;
	private String label="";
	private List<String> parts;
	private static String partSeparator=", ";
	
	private Address() { }
	protected Address(GWTguid guid) {
		super(guid);
	}
	
	public Address(GWTguid guid,String... parts) {
		this(guid);
		replaceParts(parts);
	}
	public Address(GWTguid guid,Iterable<String> parts) {
		this(guid);
		replaceParts(parts);
	}

	public String asString() {
		String partsString="";
		for(String part:parts) {
			partsString=partsString+partSeparator+part;
		}
		partsString=partsString.substring(partSeparator.length());
		if(!"".equals(label)) {
			return label+": "+partsString;
		}else {
			return partsString;
		}
	}
	
	public void setAll(Address address) {
		if(null==address) {
			throw new NullPointerException();
		}
		this.id=address.id;
		this.label=address.label;
		replaceParts(address.parts);
	}

	public void replaceParts(String... parts) {
		this.parts=new ArrayList<>();
		for(String part:parts) {
			this.parts.add(part);
		}
		
	}
	public void replaceParts(Iterable<String> parts) {
		this.parts=new ArrayList<>();
		for(String part:parts) {
			this.parts.add(part);
		}
	}
	
	public static void setCalculateDisplayString(Function<Address,String> calculateDisplayString) {
		calculateDisplayStringFunction=calculateDisplayString;
	}
	public String getDisplayString() {
		return calculateDisplayStringFunction.apply(this);
	}
	public List<String> getParts() {
		return parts;
	}
	public void setParts(List<String> parts) {
		this.parts = parts;
	}
	public void addPart(String part) {
		getParts().add(part);
	}
	public static String getPartSeparator() {
		return partSeparator;
	}
	public static void setPartSeparator(String partSeparator) {
		Address.partSeparator = partSeparator;
	}
	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((parts == null) ? 0 : parts.hashCode());
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
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (parts == null) {
			if (other.parts != null)
				return false;
		} else if (!parts.equals(other.parts))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", identityGUID=" + getIdentityGUID() + ", asString=" + asString() + "]";
	}
	
	
	
}
