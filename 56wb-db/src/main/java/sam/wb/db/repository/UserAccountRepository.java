package sam.wb.db.repository;

import org.springframework.data.repository.CrudRepository;

import sam.wb.db.entity.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
	
	UserAccount findById(Long id);
	UserAccount findByName(String username);
	
}