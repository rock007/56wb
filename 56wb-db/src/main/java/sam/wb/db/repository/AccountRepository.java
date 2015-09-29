package sam.wb.db.repository;

import org.springframework.data.repository.CrudRepository;

import sam.wb.db.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	Account findById(Long id);
	Account findByUsername(String username);
}