package com.example.airbnbcloneback;

import com.example.airbnbcloneback.domain.*;
import com.example.airbnbcloneback.dtos.LeaseDTO;
import com.example.airbnbcloneback.dtos.PropertyDTO;
import com.example.airbnbcloneback.service.PropertyService;
import com.example.airbnbcloneback.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class AirbnbCloneBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbnbCloneBackApplication.class, args);
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	CommandLineRunner run(PropertyService propertyService, UserService userService){
		return args -> {

            AppUser tenant = userService
                    .saveUser(
                            new AppUser(null, "Abe", "abe", "1234",
                                    Arrays.asList(new AppRole(null, AppRole.ADMIN), new AppRole(null, AppRole.LANDLORD), new AppRole(null, AppRole.TENANT)),null));
            PropertyDTO propertyDTO = new PropertyDTO(null,5,3500,true
                    ,new Address("Iowa","Fairfield","1000 N","52557"));

		   PropertyDTO property = propertyService.addProperty(propertyDTO);
           //property.addHistory(new PropertyHistory(3000, tenant, property, LocalDate.now(),LocalDate.now().plusMonths(3)));

            PropertyDTO propertyDTO1 = new PropertyDTO(null,5,3500,true
                    ,new Address("Iowa","Fairfield","1000 N","52557"));

            PropertyDTO property1 = propertyService.addProperty(propertyDTO1);
            //property1.addHistory(new PropertyHistory(3000, tenant, property, LocalDate.now(),LocalDate.now().plusMonths(3)));

            LeaseDTO leaseDTO = new LeaseDTO(tenant.getId(),property.getId(),3,12000);
			propertyService.leaseProperty(leaseDTO);

            propertyService.listProperty(property.getId());
            LeaseDTO leaseDTO2 = new LeaseDTO(tenant.getId(),property.getId(),3,12000);

            propertyService.leaseProperty(leaseDTO2);

            LeaseDTO leaseDTO1 = new LeaseDTO(tenant.getId(),property1.getId(),3,12000);
            propertyService.leaseProperty(leaseDTO1);

           //System.out.println(propertyService.getTotalIncomePerLocation("Fairfield"));
		};
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
//			PropertyDTO property = new PropertyDTO( 5, 3400.0,false
//					,new Address("Iowa","Fairfield","1000 N","52557"));
//			PropertyDTO propert1 = new PropertyDTO(3, 3400.0,false
//					,new Address("Iowa","Ottumwa","1000 N","52557"));
//			PropertyDTO property2 = new PropertyDTO(2, 3400,false
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


//    @Bean
//    @Transactional
//    CommandLineRunner run(UserService userService ) {
//        return args -> {
//
//            userService
//                    .saveUser(
//                            new AppUser(null, "Abe", "abe", "1234",
//                                    Arrays.asList(new AppRole(null, AppRole.ADMIN), new AppRole(null, AppRole.LANDLORD), new AppRole(null, AppRole.TENANT)),null));
//
//        };
//    }
}
