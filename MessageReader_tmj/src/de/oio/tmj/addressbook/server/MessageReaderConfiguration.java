package de.oio.tmj.addressbook.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import de.oio.tmj.addressbook.server.service.ServiceDomain;

@Configuration // Spring Bescheid sagen, dass diese Klasse Spring Konfigurationen enthält.
//@ComponentScan(basePackages="de.oio.tmj.addressbook.server.service")//dort soll nach Komponenten gesucht werden.
@ComponentScan(basePackageClasses=ServiceDomain.class)//In dem Package dieser Klasse soll nach Komponenten gesucht werden.
public interface MessageReaderConfiguration {

}
