package de.oio.tmj.addressbook.client.ui.widget;

import java.util.List;
import java.util.function.Consumer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Widget;
import de.oio.tmj.addressbook.client.service.IdGenerator;
import de.oio.tmj.addressbook.shared.model.Mail;

public class MailEditDialogbox extends EntityEditDialogbox<Mail>{
	@UiField ValidateableTextbox labelTextbox;
	@UiField ValidateableTextbox mailTextbox;
	private static MailEditDialogboxUiBinder uiBinder = GWT.create(MailEditDialogboxUiBinder.class);

	interface MailEditDialogboxUiBinder extends UiBinder<Widget, MailEditDialogbox> {	}
	
	public MailEditDialogbox() {
		super();
		setWidget(uiBinder.createAndBindUi(this));
		displayObject=new Mail(IdGenerator.createGUID(),"");
	}


	public static void openNewEntityEditDialogbox(Consumer<Mail> onEntityChanged) {
		MailEditDialogbox entityEditDialogbox=new MailEditDialogbox();
		entityEditDialogbox.init((Mail) null, onEntityChanged);
	}
	public static void openEntityEditDialogBox(List<Mail> list,Consumer<List<Mail>> onListChanged) {
		MailEditDialogbox entityEditDialogbox=new MailEditDialogbox();
		entityEditDialogbox.init(list,onListChanged);
	}
	
	public static void openEntityEditDialogBox(Mail entity, Consumer<Mail> onEntityChanged) {
		MailEditDialogbox etityEditDialogBox=new MailEditDialogbox();
		etityEditDialogBox.init(entity,onEntityChanged);
	}
	
	private void init(List<Mail> entitys,Consumer<List<Mail>> onListChanged) {
		init((Mail) null, entity->{
			entitys.add(entity);
			onListChanged.accept(entitys);
		});
	}

	@Override
	protected void init(Mail entity, Consumer<Mail> onEntityChanged) {
		if(null!=entity) {
			displayObject.setAll(entity);
		}
		
		labelTextbox.getElement().setAttribute("placeholder", "Label");
//		labelTextbox.addValidator(PersonValidator.createPersonNameValidator());
		labelTextbox.addValueChangeHandler(event-> {
			if(labelTextbox.validate()) {
				displayObject.setLabel(event.getValue());
			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
				((FocusWidget)event.getSource()).setFocus(true);
			}
		});

		mailTextbox.getElement().setAttribute("placeholder", "Mail");
//		mailTextbox.addValidator(PersonValidator.createPersonNameValidator());
		mailTextbox.addValueChangeHandler(event-> {
			if(mailTextbox.validate()) {
				displayObject.setAddress(event.getValue());
			}else {//TODO if validation fails, the focus should remain at this widget. Does not work properly, yet.
				((FocusWidget)event.getSource()).setFocus(true);
			}
		});
		
		loadData(displayObject);

		okButton.addClickHandler(e->{
			if( mailTextbox.validate() 
//					&& nickTextbox.validate() 
//					&& PersonValidator.birthdayPassesValidationOrAlert(birthdayPicker.getValue())
				){
				onEntityChanged.accept(displayObject);
				hide();
			}
		});
		cancelButton.addClickHandler(e->{
			hide();
		});
		
		center();
	}
	
	
	@Override
	protected  void loadData(Mail displayObject) { // displayPerson must not be null!
		if(null!=displayObject.getLabel()) {
			labelTextbox.setText(displayObject.getLabel());
		}
	
		if(null!=displayObject.getAddress()) {
			mailTextbox.setText(displayObject.getAddress());
		}
		
	}
	
	
}