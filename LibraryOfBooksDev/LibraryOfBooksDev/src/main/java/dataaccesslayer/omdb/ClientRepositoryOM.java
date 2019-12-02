package dataaccesslayer.omdb;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dataaccesslayer.Repositorable;
import entities.Client;
import lombok.NonNull;

public class ClientRepositoryOM implements Repositorable<Client> {
	// private final Logger log = LogManager.getLogger();
	private OMDataBase db = OMDataBase.getInstance();
	private List<Client> cachedLink = db.getClients();

	@Override
	public Client get(int id) throws NoSuchElementException {
		return new Client(cachedLink.stream().filter(x -> x.getId() == id).findAny().get());
	}

	@Override
	public List<Client> get(@NonNull Predicate<Client> p) {
		return cachedLink.stream().filter(p).map(x -> new Client(x)).collect(Collectors.toList());
	}

	@Override
	public void create(@NonNull Client element) {
		db.generateId(element);
		cachedLink.add(new Client(element));
	}

	@Override
	public Client delete(int id) throws NoSuchElementException {
		Optional<Client> tmpOptional = cachedLink.stream().filter(x -> x.getId() == id).findAny();
		cachedLink.remove(tmpOptional.get());
		return tmpOptional.get();
	}

	@Override
	public List<Client> delete(@NonNull Predicate<Client> p) {
		List<Client> remElements = new ArrayList<>();
		cachedLink.stream().filter(p).forEach(x -> remElements.add(delete(x.getId())));
		return remElements;
	}

	@Override
	public void update(@NonNull Client element) throws NoSuchElementException {
		Client findedElement = cachedLink.stream().filter(x -> x.getId() == element.getId()).findAny().get();
		cachedLink.set(cachedLink.indexOf(findedElement), element);
	}
}
