package de.oio.tmj.addressbook.shared.conf;

import com.google.gwt.i18n.client.Constants;

public interface AppProperties extends Constants{
	@DefaultStringValue("yyyy-MM-dd HH:mm:ss") String datetimeformat();
	@DefaultIntValue(50) int personNameMaxLength();
}
