package de.oio.tmj.addressbook.client.ui.bundle;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.CssResource;

public interface Resources extends ClientBundle{
	@Source("MessageReader.gss")//google style sheet
	Style style();
	
	ImageResource addressbook();
	
	ImageResource validationError();
	

	public interface Style extends CssResource {
		String entry();
		String dialogboxWidget();
		String label();
		String textBox();
	}

}
