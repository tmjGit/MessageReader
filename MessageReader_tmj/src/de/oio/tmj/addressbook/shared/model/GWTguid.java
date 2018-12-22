package de.oio.tmj.addressbook.shared.model;

import java.io.Serializable;

public interface GWTguid extends Serializable {
	/**
	 * @return 8-4-4-4-12 String representation of the GUID
	 */
	String canonical();

}
