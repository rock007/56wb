package sam.wb.db.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "system_roles")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@NotNull
	String authority;
	
	@ManyToMany(mappedBy = "roles")
	Set<Account> users = new HashSet<Account>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Set<Account> getUsers() {
		return users;
	}

	public void setUsers(Set<Account> users) {
		this.users = users;
	}
	
}