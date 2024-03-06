package com.gamesstorebe;

import com.gamesstorebe.entity.Cart;
import com.gamesstorebe.entity.Role;
import com.gamesstorebe.entity.User;
import com.gamesstorebe.repository.CartRepository;
import com.gamesstorebe.repository.RoleRepository;
import com.gamesstorebe.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class GamesStoreBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamesStoreBeApplication.class, args);
    }
    @Bean
    CommandLineRunner run(RoleRepository roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncode,
                          CartRepository cartRepository){
        return args ->{
            if(roleRepository.findByRole("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role(1, "ADMIN"));
            roleRepository.save(new Role(2, "USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User("admin@example.com", passwordEncode.encode("password"),"","",null,"","",true, roles);
            userRepository.save(admin);
        };
    }
}
