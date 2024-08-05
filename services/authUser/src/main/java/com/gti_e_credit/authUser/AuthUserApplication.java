package com.gti_e_credit.authUser;


import com.gti_e_credit.authUser.roleuser.RoleUser;
import com.gti_e_credit.authUser.roleuser.RoleUserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;




@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.gti_e_credit.authUser.roleuser", "com.gti_e_credit.authUser.roleuser.RoleUserRepo"})

public class AuthUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthUserApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleUserRepo roleRepo){
		return  args -> {
			if (roleRepo.findByName("USER").isEmpty()){
				roleRepo.save(
						RoleUser.builder().name("USER").build()
				);
			}
		};
	}



}
