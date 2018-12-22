package de.oio.tmj.addressbook.shared.model;

public class GWTguidImpl implements GWTguid {
	private static final long serialVersionUID = 8291769261237904798L;
	private String guid="";
	
	public GWTguidImpl(String... parts) {
		for(String s:parts) {
			guid=guid+s;
		}
	}

	@Override
	public String canonical() {
		return guid;
	}

	@Override
	public String toString() {
		return "GWTguidImpl [guid=" + guid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guid == null) ? 0 : guid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GWTguidImpl other = (GWTguidImpl) obj;
		if (guid == null) {
			if (other.guid != null)
				return false;
		} else if (!guid.equals(other.guid))
			return false;
		return true;
	}
	
	
}
