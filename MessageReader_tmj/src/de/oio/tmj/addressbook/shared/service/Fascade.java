package de.oio.tmj.addressbook.shared.service;

import java.util.Map;
import com.google.gwt.dev.util.collect.HashMap;

public interface Fascade {
    Map<Boolean, Fascade> FACADES = new HashMap<Boolean, Fascade>();
}