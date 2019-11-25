package pl.controllers;

import java.util.function.Predicate;

import pl.Controller;
import pl.Model;

public class ClientController implements Controller<Model> {

	@Override
	public Model get(int id) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public Model get(Predicate<?> p) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void put(Model t) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void post(Model t) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

}
