package com.digitalkh.sgvdkapi;

import static com.digitalkh.sgvdkapi.user.model.ERole.ROLE_ADMIN;
import static com.digitalkh.sgvdkapi.user.model.ERole.ROLE_USER;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.digitalkh.sgvdkapi.streaming.enumeration.EAccountType;
import com.digitalkh.sgvdkapi.streaming.model.Account;
import com.digitalkh.sgvdkapi.streaming.model.AccountProfile;
import com.digitalkh.sgvdkapi.streaming.model.AccountType;
import com.digitalkh.sgvdkapi.streaming.repository.AccountProfileRepository;
import com.digitalkh.sgvdkapi.streaming.repository.AccountRepository;
import com.digitalkh.sgvdkapi.streaming.repository.AccountTypeRepository;
import com.digitalkh.sgvdkapi.user.model.Role;
import com.digitalkh.sgvdkapi.user.model.User;
import com.digitalkh.sgvdkapi.user.repository.RoleRepository;
import com.digitalkh.sgvdkapi.user.repository.UserRepository;

@SpringBootApplication
public class SgvdkapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgvdkapiApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner lineRunner(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AccountRepository accountRepository,
			AccountTypeRepository accountTypeRepository, AccountProfileRepository profileRepository) {
		return args -> {

			AccountType disney = accountTypeRepository.save(new AccountType(EAccountType.DISNEY));
			AccountType netflix = accountTypeRepository.save(new AccountType(EAccountType.NETFLIX));
			roleRepository.save(new Role(ROLE_USER));

			User admin = new User("Kevin Andres", "Holguín Bedoya", "3104219934",
					"kevin.holguin@gmail.com", new BCryptPasswordEncoder().encode("admin"), new Role(ROLE_ADMIN));
			
			admin.setEnabled(true);
			
			userRepository.save(admin);

			Account joan = accountRepository
					.save(new Account("joan.mosquera@gmail.com", "joan123", disney, "Cuenta de disney de una pantalla",
							25500L, "http://localhost:8080/api/public/account/image/DINEY.jfif", new ArrayList<>()));

			Account tom = accountRepository.save(
					new Account("tom.mendez@gmail.com", "tom123", netflix, "Cuenta de netflix de cuatro pantallas",
							25500L, "http://localhost:8080/api/public/account/image/NETFLIX.jfif", new ArrayList<>()));

			@SuppressWarnings("unused")
			Account linda = accountRepository
					.save(new Account("linda@gmail.com", "linda123", disney, "Cuenta de Disney de cuatro pantallas",
							25500L, "http://localhost:8080/api/public/account/image/DISNEY.jfif", new ArrayList<>()));

			String[] names = { "Andres", "Maria Jose", "Alejandro", "Karen", "Rodrigo", "Roberto", "Alvaro" };
			AccountType[] types = { netflix, disney };
			String[] domain = { "@gmail.com", "@outlook.com" };

			int r = 0;

			for (String name : names) {
				r = new Random().nextInt(2);
				accountRepository.save(
						new Account(name + domain[r], name + "123", types[r], "La misma descripcion para todos xd",
								25000L, "http://localhost:8080/api/public/account/image/" + types[r].getName().name(),
								new ArrayList<>()));
			}

			profileRepository.save(new AccountProfile(null, "user1", "1234", joan));
			profileRepository.save(new AccountProfile(null, "user2", "4321", joan));
			profileRepository.save(new AccountProfile(null, "user1", "1234", tom));
		};
	}
}
