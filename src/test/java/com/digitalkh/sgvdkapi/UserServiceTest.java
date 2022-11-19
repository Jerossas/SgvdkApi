package com.digitalkh.sgvdkapi;

import static com.digitalkh.sgvdkapi.user.model.ERole.ROLE_ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.digitalkh.sgvdkapi.user.model.Role;
import com.digitalkh.sgvdkapi.user.model.User;
import com.digitalkh.sgvdkapi.user.repository.UserRepository;
import com.digitalkh.sgvdkapi.user.service.UserService;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	private User user;

	@Test
	public void loadUserByUsernameShouldReturnAnInstanceOfUserDetailsWhenUsernameIsPresentedInTheDatabase() {
		String userEmail = "kevin.holguin@gmail.com";
		
		user = new User("Kevin Andres", "Holguín Bedoya", "3104219934", "kevin.holguin@gmail.com",
				new BCryptPasswordEncoder().encode("admin"), new Role(ROLE_ADMIN));
		
		when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(user));
		
		assertThat(userService.loadUserByUsername(userEmail) instanceof UserDetails);
	}
	
	@Test
	public void loadUserByUsernameShouldReturnAnExceptionWhenUsernameIsNotPresentedInTheDatabase() {
		String userEmail = "mosqueralozanojhoanferley@gmail.com";
		
		user = new User("Kevin Andres", "Holguín Bedoya", "3104219934", "kevin.holguin@gmail.com",
				new BCryptPasswordEncoder().encode("admin"), new Role(ROLE_ADMIN));
		
		userRepository.save(user);
		
		assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(userEmail));
	}
	
	@Test
	public void viewPersonalInfomationMethodShouldReturnANulPointerExceptionIfAnyUserLoggedIn() {
	
		assertThrows(NullPointerException.class, () -> userService.viewProfile());
	}
}
