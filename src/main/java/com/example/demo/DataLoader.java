package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JobRepository jobRepository;

    @Override
    public void run(String... strings) throws Exception{
        if(roleRepository.count() <1) {
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role adminRole = roleRepository.findByRole("ADMIN");
            Role userRole = roleRepository.findByRole("USER");

            User user;
            user = new User("admin@admin.com", "password",
                "Amelia", "Anderson", true, "admin");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);

            user = new User("jim@jim.com", "jim",
                        "Jim", "Jimson", true, "jim");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("dave@dockersrus.com", "dave",
                    "Dave", "Davidson", true, "dave");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User ("sam@greeneggs.com", "sam",
                    "Sam", "Hamm", true, "sam");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            Job job;
            Date date = new Date();
            job = new Job(date,"Lawn mower","Mow my 4 acre lawn weekly",
                    "Jim","301-555-1234",userRepository.findByUsername("jim"));
            jobRepository.save(job);

            job = new Job(date,"Java Web Developer","Type and drink coffee all day",
                    "Dave","301-555-4321",userRepository.findByUsername("dave"));
            jobRepository.save(job);

            job = new Job(date,"Chef","Short order cooking - breakfast shift",
                    "Sam's Diner","301-555-5678",userRepository.findByUsername("sam"));
            jobRepository.save(job);

            job = new Job(date,"Junior Database Administrator","Assist in administering mid-size database",
                    "Amelia","301-555-8765",userRepository.findByUsername("admin"));
            jobRepository.save(job);

            job = new Job(date,"Dog Sitter","Walk, feed, and love my 3 dogs while I am at work",
                    "Amelia","301-555-8765",userRepository.findByUsername("admin"));
            jobRepository.save(job);
        }
    }
}
