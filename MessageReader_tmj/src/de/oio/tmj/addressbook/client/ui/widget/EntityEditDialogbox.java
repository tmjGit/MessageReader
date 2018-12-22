package de.oio.tmj.addressbook.client.ui.widget;

import java.util.List;
import java.util.function.Consumer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

import de.oio.tmj.addressbook.client.ui.bundle.Resources;
import de.oio.tmj.addressbook.client.ui.bundle.Resources.Style;
import de.oio.tmj.addressbook.client.ui.lang.AppMessages;
import de.oio.tmj.addressbook.client.ui.widget.PersonEditDialogbox.PersonEditDialogboxUiBinder;
import de.oio.tmj.addressbook.shared.model.EntityBase;
import de.oio.tmj.addressbook.shared.model.Mail;
import de.oio.tmj.addressbook.shared.model.Person;

public abstract class EntityEditDialogbox<T extends EntityBase> extends DialogBox{
	@UiField(provided=true)	Style style;
	@UiField AppMessages messages;//	@UiField(provided=true)	AppMessages messages;//private AppMessages messages=GWT.create(AppMessages.class);
	@UiField Button okButton;
	@UiField Button cancelButton;
//	private static DialogWidgetUiBinder uiBinder;
	protected Resources resources=GWT.create(Resources.class);
	protected T displayObject;
	
//	interface DialogWidgetUiBinder extends UiBinder<Widget, DialogWidget> {	}

	public EntityEditDialogbox() {
//		Resources resources=GWT.create(Resources.class);
		style=resources.style();
//		style.ensureInjected();
//		setWidget(uiBinder.createAndBindUi(this));
	}
	
//	public static void openNewEntityEditDialogbox(Consumer<T> onEntityChanged) {
//		EntityEditDialogbox<T> entityEditDialogbox=new MailEditDialogbox();
//		entityEditDialogbox.init((T) null, onEntityChanged);
//	}
//	public static void openEntityEditDialogBox(List<T> list,Consumer<List<T>> onListChanged) {
//		EntityEditDialogbox<T> entityEditDialogbox=new EntityEditDialogbox<>();
//		entityEditDialogbox.init(list,onListChanged);
//	}

//	public static void openEntityEditDialogBox(Person entity, Consumer<T> onEntityChanged) {
//		EntityEditDialogbox<T> entryWidgetDialogBox=new PersonEditDialogbox();
//		entryWidgetDialogBox.init(entity,onEntityChanged);
//	}
	
//	private void init(List<T> entitys,Consumer<List<T>> onListChanged) {
//		init((T) null, entity->{
//			entitys.add(entity);
//			onListChanged.accept(entitys);
//		});
//	}

	protected abstract void init(T entity, Consumer<T> onEntityChanged);// {
//		if(null!=entity) {
//			displayObject.setAll(entity);
//		}
	
	protected abstract void loadData(T displayObject); // displayObject must not be null!


}
