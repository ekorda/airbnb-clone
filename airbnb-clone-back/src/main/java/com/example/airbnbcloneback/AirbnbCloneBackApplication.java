package com.example.airbnbcloneback;

import com.example.airbnbcloneback.domain.*;
import com.example.airbnbcloneback.dtos.LeaseDTO;
import com.example.airbnbcloneback.service.PropertyService;
import com.example.airbnbcloneback.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AirbnbCloneBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbCloneBackApplication.class, args);
	}


	@Bean
	BCryptPasswordEncoder getBCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(PropertyService propertyService, UserService userService){
//		return args -> {
//			Property property = new Property(null, 5, 3400,true,null,null
//					,new Address("Iowa","Fairfield","1000 N","52557"));
//            AppUser tenant = new AppUser(null, "Sam","sam","1234",null);
//			AppRole role = new AppRole(null, "ROLE_TENANT");
//			tenant.addRole(role);
//			Property property1 = propertyService.addProperty(property);
//			AppUser user = userService.saveUser(tenant);
//			LeaseDTO leaseDTO = new LeaseDTO(user.getId(),property1.getId(),3,12000);
//			propertyService.leaseProperty(leaseDTO);
//		};
//	}

//	@Bean
//	CommandLineRunner run(PropertyService propertyService){
//		return args -> {
//			Property property = new Property(null, 5, 3400,false,null,null
//					,new Address("Iowa","Fairfield","1000 N","52557"));
//			Property propert1 = new Property(null, 3, 3400,false,null,null
//					,new Address("Iowa","Ottumwa","1000 N","52557"));
//			Property property2 = new Property(null, 2, 3400,false,null,null
//					,new Address("Iowa","Iowa City","1000 N","52557"));
//
//			propertyService.addProperty(property);
//			propertyService.addProperty(property2);
//			propertyService.addProperty(propert1);
//
//			Map<String, String> map = new HashMap<>(){{
//				//put("occupied", "false");
//				put("num-of-rooms", "3");
//				put("location","Ottu");
//			}};
//			System.out.println(propertyService.getProperties(map));
//		};
//	}

//	@Bean
//	CommandLineRunner run(PropertyService propertyService){
//		return args -> {
//			Property property = new Property(null,5,3500,false,null, null);
//			Property property1 = new Property(null, 7, 3400, false,null, null);
//			Property property2 = new Property(null, 7, 3400, false, null,null);
//
//			property.addHistory(new PropertyHistory(null, property,LocalDate.now().minusMonths(4), LocalDate.now().minusMonths(3)));
//			property.addHistory(new PropertyHistory(null,property,LocalDate.now().minusMonths(3), LocalDate.now().plusMonths(1)));
//			propertyService.addProperty(property);
//
//			property1.addHistory(new PropertyHistory(null, property1,LocalDate.now().minusMonths(4), LocalDate.now().minusMonths(1)));
//			property1.addHistory(new PropertyHistory(null,property1,LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(2)));
//			propertyService.addProperty(property1);
//
//			property2.addHistory(new PropertyHistory(null, property2,LocalDate.now().minusMonths(4), LocalDate.now().minusMonths(1)));
//			property2.addHistory(new PropertyHistory(null,property2,LocalDate.now().minusMonths(2), LocalDate.now().plusMonths(3)));
//			propertyService.addProperty(property2);
//
//			//System.out.println(propertyService.getPropertiesWhoseLeaseEndsBefore(LocalDate.now().plusMonths(3)));
//			System.out.println(propertyService.getLastNPropertiesRented(2));
//			//System.out.println(propertyService.getProperties().get(0));
//		};
//	}

//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.saveRole(new AppRole(null, "ROLE_ADMIN"));
//			userService.saveRole(new AppRole(null, "ROLE_LANDLORD"));
//			userService.saveRole(new AppRole(null, "ROLE_TENANT"));
//
//
//			userService.saveUser(new AppUser(null, "Abe", "abe", "1234", new ArrayList<>()));
//			userService.saveUser(new AppUser(null, "Kebe", "kebe", "1234", new ArrayList<>()));
//			userService.saveUser(new AppUser(null, "Key", "key", "1234", new ArrayList<>()));
//
//			userService.addRoleToUser("abe", "ROLE_ADMIN");
//			userService.addRoleToUser("kebe", "ROLE_LANDLORD");
//			userService.addRoleToUser("key", "ROLE_TENANT");
//			userService.addRoleToUser("key", "ROLE_LANDLORD");
//
//		};
//	}

}
