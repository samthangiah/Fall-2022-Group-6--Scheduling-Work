package sru.groupsix.workOrder.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminDetailService implements UserDetailsService {

	@Autowired
	private AdminRepository repo;
	
	@Override
	public AdminDetail loadUserByUsername(String email) throws UsernameNotFoundException {
	      Admin admin = repo.findByEmail(email);
	        if (admin == null) {
	            throw new UsernameNotFoundException("admin not found");
	        }
	        return new AdminDetail(admin);
	    }
}
