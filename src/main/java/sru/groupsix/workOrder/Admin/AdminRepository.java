package sru.groupsix.workOrder.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	Admin findByEmail(String email);
	 
}