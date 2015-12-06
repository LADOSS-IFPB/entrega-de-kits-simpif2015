package br.com.simpif.service.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.com.simpif.service.Services;

public class SimpifApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public SimpifApplication() {
		this.singletons.add(new Services());
	}

	public Set<Class<?>> setSingletons() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}
