package sru.groupsix.workOrder.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	      User user = repo.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        return new Userdetail(user);
	    }
}
