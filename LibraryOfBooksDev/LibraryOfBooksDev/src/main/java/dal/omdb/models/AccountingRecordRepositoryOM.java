package dal.omdb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dal.Repositorable;
import dal.omdb.OMDataBase;
import entities.AccountingRecord;
import lombok.NonNull;

public class AccountingRecordRepositoryOM implements Repositorable<AccountingRecord> {
	private final Logger log = LogManager.getLogger();
	private OMDataBase db = OMDataBase.getInstance();
	private List<AccountingRecord> cachedLink = db.getAccountingRecords();

	@Override
	public AccountingRecord get(int id) throws NoSuchElementException {
		return cachedLink.stream().filter(x -> x.getId() == id).findAny().get();
	}

	@Override
	public List<AccountingRecord> get(@NonNull Predicate<AccountingRecord> p) {
		return cachedLink.stream().filter(p).collect(Collectors.toList());
	}

	@Override
	public void create(@NonNull AccountingRecord element) {
		db.generateId(element);
		cachedLink.add(element);
	}

	@Override
	public AccountingRecord delete(int id) throws NoSuchElementException {
		Optional<AccountingRecord> tmpOptional = cachedLink.stream().filter(x -> x.getId() == id).findAny();
		cachedLink.remove(tmpOptional.get());
		return tmpOptional.get();
	}

	@Override
	public List<AccountingRecord> delete(@NonNull Predicate<AccountingRecord> p) {
		List<AccountingRecord> remElements = new ArrayList<>();
		cachedLink.stream().filter(p).forEach(x -> remElements.add(delete(x.getId())));
		return remElements;
	}

	@Override
	public void update(@NonNull AccountingRecord element) {
		// db.update(element, db.getAccountingRecords());
		try {
			AccountingRecord findedElement = cachedLink.stream().filter(x -> x.getId() == element.getId()).findAny()
					.get();
			cachedLink.set(cachedLink.indexOf(findedElement), element);
		} catch (NoSuchElementException nsee) {
			cachedLink.add(element);
			log.warn("Updated element does not exist, but was re-created");
		}

	}

}
