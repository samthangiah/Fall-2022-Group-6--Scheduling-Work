package sru.groupsix.workOrder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import sru.groupsix.workOrder.User.User;
import sru.groupsix.workOrder.User.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	 @Autowired
	    private UserRepository repo;
	
	 @Autowired
	    private TestEntityManager entityManager;
	 @Test
	 public void testCreateUser() {
		 User user = new User();
		 user.setEmail("abc1089@sru.edu");
		   user.setPassword("abcd");
		    user.setFirstName("bob");
		    user.setLastName("bo");
		     
		    User savedUser = repo.save(user);
		     
		    User existUser = entityManager.find(User.class, savedUser.getId());
		     
		    assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	 }    
	 @Test
	 public void testFindUserByEmail() {
		 String email = "abc123@sru.edu";
		 
		 User user = repo.findByEmail(email);
		 assertThat(user).isNotNull();
	 }
}
