package de.oio.tmj.addressbook.server.service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

import de.oio.tmj.addressbook.shared.model.GWTguid;
import de.oio.tmj.addressbook.shared.model.GWTguidImpl;

@Component
public class IdGenerator{// implements de.oio.tmj.addressbook.shared.service.IdGenerator {
//	private int lastId;//TODO workaround until id is generated by DB.
//	
//	public int getNewId() {
//		return ++lastId;
//	}
	
//	@Override
//	public String createGUID() {
	public static GWTguid createGUID() {
//		return UUID.randomUUID().toString();
		Date date= new Date();
		long l=date.getTime();
//		String s=Long.toHexString(l);
//		int i=com.google.gwt.user.client.Random.nextInt(1000);
		int i = ThreadLocalRandom.current().nextInt(0, 1000);
//		s= s+Integer.toHexString(i);
		return new GWTguidImpl(Long.toHexString(l), Integer.toHexString(i));
//		return s;
	}

}
