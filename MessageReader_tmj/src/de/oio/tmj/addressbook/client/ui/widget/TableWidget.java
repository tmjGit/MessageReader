package de.oio.tmj.addressbook.client.ui.widget;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.ListDataProvider;
import de.oio.tmj.addressbook.client.MessageReader;
import de.oio.tmj.addressbook.shared.model.Person;

public class TableWidget<T> extends Composite {

	private final ListDataProvider<T> dataProvider;
	private final ListHandler<T> listHandler;
	private final CellTable<T> table;

//	public interface Callback {
//		void onEdit(T entry);
//
//		void onDelete(T entry);
//	}

	public TableWidget() {
//	public TableWidget(Callback callback) {
		table = new CellTable<T>();//Warum muss hier T angegeben werden? Zumindest der Eclipse compiler zeigt hier sonst <Object>!
		dataProvider = new ListDataProvider<T>();//Warum muss hier T angegeben werden? Zumindest der Eclipse compiler zeigt hier sonst <Object>!
		dataProvider.addDataDisplay(table);
		listHandler = new ListHandler<>(dataProvider.getList());
		table.addColumnSortHandler(listHandler);

//		Column<T, String> nameColumn = addTextColumn("Name",person->person.getName());
//
//		table.getColumnSortList().push(nameColumn);
//
//		Column<T, String> editButtonColumn = new Column<T, String>(new ButtonCell()) {
//			@Override
//			public String getValue(T object) {
//				return "Edit";
//			}
//		};
//		editButtonColumn.setFieldUpdater(new FieldUpdater<T, String>() {
//			@Override
//			public void update(int index, T object, String value) {
//				callback.onEdit(object);
//			}
//		});
//		table.addColumn(editButtonColumn);

		initWidget(table);
	}

	protected Column<T, String> addTextColumn(String label,Function<T,String> getItemValue) {
		Column<T, String> column = new Column<T, String>(new TextCell()) {
			@Override
			public String getValue(T item) {
				return getItemValue.apply(item);
			}
		};
		table.addColumn(column, label);
		column.setSortable(true);
		listHandler.setComparator(column, new Comparator<T>() {
			@Override
			public int compare(T item1, T item2) {
				return getItemValue.apply(item1).compareTo(getItemValue.apply(item2));
			}
		});
		return column;
	}

	protected Column<T, Number> addIntColumn(String label,Function<T,Integer> getItemValue) {
		Column<T, Number> column = new Column<T, Number>(new NumberCell()) {
			@Override
			public Integer getValue(T item) {
				return getItemValue.apply(item);
			}
		};
		table.addColumn(column, label);
		column.setSortable(true);
		listHandler.setComparator(column, new Comparator<T>() {
			@Override
			public int compare(T item1, T item2) {
				return getItemValue.apply(item1).compareTo(getItemValue.apply(item2));
			}
		});
		return column;
	}

	/**
	 * The list as parameter will not be used internally.
	 * Therefore the content of an internal list will be replaced by the objects' references.
	 * @param items
	 */
	public void setItems(List<T> items) {
//		List<T> list=dataProvider.getList();
//		AddressBook.display("TableWidget.setItems(persons):","new=",values,"old=",dataProvider.getList(),"listVar=",list,"clear...");
		dataProvider.getList().clear();
//		AddressBook.display("TableWidget.setItems(persons):","new=",values,"now=",dataProvider.getList(),"addAll...");
		dataProvider.getList().addAll(items);
//		AddressBook.display("TableWidget.setItems(persons):","new=",values,"now=",dataProvider.getList(),"sort...");
		ColumnSortEvent.fire(table, table.getColumnSortList());
	}
	
	public void replaceItem(T oldItem, T newItem) {
		dataProvider.getList().set( dataProvider.getList().indexOf(oldItem), newItem);
	}
	
	public void removeItem(T item) {
		dataProvider.getList().remove( dataProvider.getList().indexOf(item) );
	}
	
	public void addItem(T item) {
		dataProvider.getList().add( item );
	}
	
//	/**
//	 * The internal list must not be modified directly, so a copy is provided.
//	 * @return Copy of the list with the stored objects' references.
//	 */
//	public List<T> getItems(){
//		List<T> list=new ArrayList<T>();
//		list.addAll(dataProvider.getList());
//		return list;
//	}
	
	protected CellTable<T> getTable(){
		return table;
	}
}
