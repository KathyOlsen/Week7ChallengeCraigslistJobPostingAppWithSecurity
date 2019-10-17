package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;

public interface JobRepository extends CrudRepository<Job, Long> {

    ArrayList<Job> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAuthorContainingIgnoreCase
            (String search1, String search2, String search3);

}
