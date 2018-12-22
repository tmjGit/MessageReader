package de.oio.tmj.addressbook.server.rpc;

import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.oio.tmj.addressbook.client.rpc.MessageReaderService;
import de.oio.tmj.addressbook.server.dao.PersonDAO;
import de.oio.tmj.addressbook.server.service.MessageReaderManagementService;
import de.oio.tmj.addressbook.server.service.PersonMock;
import de.oio.tmj.addressbook.shared.collection.IdentityArrayList;
import de.oio.tmj.addressbook.shared.model.Address;
import de.oio.tmj.addressbook.shared.model.Mail;
import de.oio.tmj.addressbook.shared.model.Person;
import de.oio.tmj.addressbook.shared.model.Phone;

public class MessageReaderServiceImpl extends RemoteServiceServlet implements MessageReaderService {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MessageReaderManagementService messageReaderManagementService;

	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);//config enthält die Daten aus web.xml inkl. der Listener
		// Diese Nicht-Spring-Komponente mit Spring verknüpfen, damit obiges das Autowired funktionieren kann:
		WebApplicationContextUtils.findWebApplicationContext(config.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
	}
	
	@Override
	public String hello(String name) {
		return "Hello, "+name+"!";
	}

//	@Override
//	public void setItems(ArrayList<Person> items)  throws RuntimeException{
//		persons.clear();
////		AddressBook.display("TableWidget.setItems(persons):","new=",values,"now=",dataProvider.getList(),"addAll...");
//		persons.addAll(items);
////		AddressBook.display("TableWidget.setItems(persons):","new=",values,"now=",dataProvider.getList(),"sort...");
////		ColumnSortEvent.fire(table, table.getColumnSortList());
//	}

	@Override
	public void replaceItem(Address oldItem, Address newItem)  throws RuntimeException{
		messageReaderManagementService.replaceItem(oldItem,newItem);
	}
	@Override
	public void replaceItem(Mail oldItem, Mail newItem) throws RuntimeException {
		messageReaderManagementService.replaceItem(oldItem,newItem);
	}
	@Override
	public void replaceItem(Person oldItem, Person newItem) throws RuntimeException {
		messageReaderManagementService.replaceItem(oldItem,newItem);
	}
	@Override
	public void replaceItem(Phone oldItem, Phone newItem)  throws RuntimeException{
		messageReaderManagementService.replaceItem(oldItem,newItem);
	}

	@Override
	public void removeItem(Address item)  throws RuntimeException {
		messageReaderManagementService.removeItem(item);
	}
	@Override
	public void removeItem(Mail item)  throws RuntimeException {
		messageReaderManagementService.removeItem(item);
	}
	@Override
	public void removeItem(Person item)  throws RuntimeException {
		messageReaderManagementService.removeItem(item);
	}
	@Override
	public void removeItem(Phone item)  throws RuntimeException {
		messageReaderManagementService.removeItem(item);
	}

	@Override
	public void addItem(Person item)  throws RuntimeException {
		messageReaderManagementService.addItem(item);
	}

	@Override
	public IdentityArrayList<Person> getItems()  throws RuntimeException {
		return messageReaderManagementService.getItems();

	}

}

			
