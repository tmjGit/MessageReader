package de.oio.tmj.addressbook.client.ui.widget;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;
import de.oio.tmj.addressbook.shared.model.Person;

public class PersonTable extends TableWidget<Person> {

	public interface Callback {
		void onEdit(Person entry);
		void onRemove(Person entry);
	}

	public PersonTable(Callback callback) {
		super();
		addIntColumn("ID",person->person.getId());
		Column<Person, String> nameColumn = addTextColumn("Name",person->person.getName());
		addTextColumn("Nick",person->person.getNick());
		addTextColumn("Adresse",person->{
				switch( person.getAddresses().size() ) {
				case 0: return "–";
				case 1: return person.getAddresses().get(0).toString(); 
				}
			return String.valueOf( "("+person.getAddresses().size() +")"); 
			});
		addTextColumn("Telefon",person->String.valueOf( person.getPhones().size() ));

		getTable().getColumnSortList().push(nameColumn);

		Column<Person, String> editButtonColumn = new Column<Person, String>(new ButtonCell()) {
			@Override
			public String getValue(Person object) {
				return "Edit";
			}
		};
		editButtonColumn.setFieldUpdater(new FieldUpdater<Person, String>() {
			@Override
			public void update(int index, Person item, String value) {
				callback.onEdit(item);
			}
		});
		getTable().addColumn(editButtonColumn);

		Column<Person, String> removeButtonColumn = new Column<Person, String>(new ButtonCell()) {
			@Override
			public String getValue(Person person) {
				return "Remove";
			}
		};
		removeButtonColumn.setFieldUpdater(new FieldUpdater<Person, String>() {
			@Override
			public void update(int index, Person item, String value) {
				callback.onRemove(item);
			}
		});
		getTable().addColumn(removeButtonColumn);
	}
	
}
