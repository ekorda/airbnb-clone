package com.example.airbnbcloneback;

import com.example.airbnbcloneback.domain.AppRole;
import com.example.airbnbcloneback.domain.AppUser;
import com.example.airbnbcloneback.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@SpringBootApplication
public class AirbnbCloneBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbnbCloneBackApplication.class, args);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/*@Bean
	CommandLineRunner run(PropertyService propertyService){
		return args -> {
			PropertyDTO property = new PropertyDTO( 5, 3400.0,false
					,new Address("Iowa","Fairfield","1000 N","52557"));
			PropertyDTO propert1 = new PropertyDTO(3, 3400.0,false
					,new Address("Iowa","Ottumwa","1000 N","52557"));
			PropertyDTO property2 = new PropertyDTO(2, 3400,false
					,new Address("Iowa","Iowa City","1000 N","52557"));

			propertyService.addProperty(property);
			propertyService.addProperty(property2);
			propertyService.addProperty(propert1);

			Map<String, String> map = new HashMap<>(){{
				//put("occupied", "false");
				put("num-of-rooms", "3");
				put("location","Ottu");
			}};
			System.out.println(propertyService.getProperties(map));
		};
	}*/

    @Bean
    @Transactional
    CommandLineRunner run(UserService userService ) {
        return args -> {

            userService
                    .saveUser(
                            new AppUser(null, "Abe", "abe", "1234",
                                    Arrays.asList(new AppRole(null, AppRole.ADMIN), new AppRole(null, AppRole.LANDLORD), new AppRole(null, AppRole.TENANT)), null ));

        };
    }
}
