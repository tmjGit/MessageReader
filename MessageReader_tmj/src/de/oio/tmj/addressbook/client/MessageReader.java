package de.oio.tmj.addressbook.client;

import java.util.Arrays;
import java.util.List;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import de.oio.tmj.addressbook.client.rpc.MessageReaderService;
import de.oio.tmj.addressbook.client.rpc.MessageReaderServiceAsync;
import de.oio.tmj.addressbook.client.ui.bundle.Resources;
import de.oio.tmj.addressbook.client.ui.bundle.Resources.Style;
import de.oio.tmj.addressbook.client.ui.lang.AppMessages;
import de.oio.tmj.addressbook.client.ui.widget.PersonEditDialogbox;
import de.oio.tmj.addressbook.client.ui.widget.PersonTable;
import de.oio.tmj.addressbook.client.ui.widget.PersonTable.Callback;
import de.oio.tmj.addressbook.shared.collection.IdentityArrayList;
import de.oio.tmj.addressbook.shared.model.Person;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MessageReader implements EntryPoint {
	private PersonTable table;

//	static {
//		Fascade.FACADES.put(true, ClientFacadeImpl.INSTANCE); // client side
//	}
		
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		MessageReaderServiceAsync addressbookService=GWT.create(MessageReaderService.class);
//		IdGenerator idGenerator=new de.oio.tmj.addressbook.client.service.IdGenerator();
//		idGenerator.idGenerator=idGenerator;
		AppMessages texts=GWT.create(AppMessages.class);
		Resources resources=GWT.create(Resources.class);
		Style style=resources.style();
		style.ensureInjected();
		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.PX) ; // besser aber komplizierter richtig zu designen: Unit.EM
		dockLayoutPanel.addNorth(new Label(texts.title()), 100);

		FlowPanel flowPanel = new FlowPanel();//FlowPanel = <div/>
		dockLayoutPanel.add(flowPanel);
		flowPanel.add(new Image(resources.addressbook()));
		Button addPersonButton = new Button(texts.newEntry());
		flowPanel.add(addPersonButton);
		Button loadPersonsButton = new Button("Load Persons");
		flowPanel.add(loadPersonsButton);
		
		table = new PersonTable(new Callback() {
			@Override
			public void onEdit(Person oldValue) {
//				System.out.println("AddressBook.onEdits: oldValue="+"="+oldValue);
				PersonEditDialogbox.openEntityEditDialogBox(oldValue,newValue-> {
					addressbookService.replaceItem(oldValue, newValue, new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							// For robustness, we load the complete list after each change. In performance is critical, this may be altered.
//							table.replaceItem(oldValue, newValue);	
							addressbookService.getItems(new AsyncCallback<IdentityArrayList<Person>>() {
								@Override
								public void onSuccess(IdentityArrayList<Person> list) {
									table.setItems(list);
								}
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Liste konnte nicht geladen werden! ");
									GWT.log("Liste konnte nicht geladen werden! ",caught);		
								}
							});
						}
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Änderungen konnten nicht auf dem Server gespeichert werden!");
							GWT.log("Änderungen konnten nicht auf dem Server gespeichert werden!",caught);		
						}
					});
				});
			}
			@Override
			public void onRemove(Person item) {
				addressbookService.removeItem(item, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						table.removeItem(item);
					}
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Änderungen konnten nicht auf dem Server gespeichert werden!");
						GWT.log("Änderungen konnten nicht auf dem Server gespeichert werden!",caught);		
					}
				});
			}
		});
		flowPanel.add(table);
		
		addPersonButton.addClickHandler(event->{ 
//			PersonEditDialogbox.openNewPersonDialogBox(person->table.addItem(person) );
			PersonEditDialogbox.openNewEntityEditDialogbox(person->addressbookService.addItem(person, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					table.addItem(person);
				}
				@Override
				public void onFailure(Throwable caught) {
//					Window.alert("Daten konnten nicht auf dem Server gespeichert werden!");
					GWT.log("Daten konnten nicht auf dem Server gespeichert werden!",caught);		
				}
			} ) );
		});
		
		loadPersonsButton.addClickHandler(event->{
			addressbookService.getItems(new AsyncCallback<IdentityArrayList<Person>>() {
				@Override
				public void onSuccess(IdentityArrayList<Person> list) {
					table.setItems(list);
				}
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Liste konnte nicht geladen werden! ");
					GWT.log("Liste konnte nicht geladen werden! ",caught);		
				}
			});
		});
		
		RootLayoutPanel.get() // Browserfenster
			.add(dockLayoutPanel);

	}


	public static String listToString(List list) {//TODO debug
		String s="";
		for(Object item:list) {
			s=s+" * " + item;
		}
		return s.substring(3);
	}
	
	public static void display(Object... objects) {
		String s="";
		for(Object element:objects) {
			if(element instanceof List) {
				s=s+listToString((List)element);
			}else {
				s=s+"\n"+element;
			}
		}
		if(s.startsWith("\n")) {
			s=s.substring(1);
		}
		Window.alert(s);
	}

}
