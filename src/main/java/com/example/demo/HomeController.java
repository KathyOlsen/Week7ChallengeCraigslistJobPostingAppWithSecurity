package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String listJobs(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @RequestMapping("/secure")
    public String secure(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        model.addAttribute("user", userService.getUser());
        return "secure";
    }

    @GetMapping("/addjob")
    public String jobForm(Model model){
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/processjob")
    public String processForm(@Valid Job job,
                              BindingResult result){
        if(result.hasErrors()){
            return "jobform";
        }
        Date date = new Date();
        job.setPostedDate(date);
        job.setUser(userService.getUser());
        jobRepository.save(job);
        return "redirect:/secure";
    }

    @PostMapping("/processsearch")
    public String processSearch(Model model,
                                @RequestParam(name="search") String search){
        model.addAttribute("user", userService.getUser());
        String[] searchS = search.split(" ");
        Set<Job> jobs = new HashSet<>();
        if(searchS.length == 1) {
            model.addAttribute("jobs", jobRepository
                    .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAuthorContainingIgnoreCase(search, search, search));
        }else{
            for(String word : searchS){
                ArrayList<Job> jobHolder = jobRepository
                        .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAuthorContainingIgnoreCase(word, word, word);
                for(Job x : jobHolder){
                    jobs.add(x);
                }
            }
            model.addAttribute("jobs", jobs);
        }
        return "listSearchResults";
    }

    @RequestMapping("/detail_job/{id}")
    public String showJob(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobRepository.findById(id).get());
        return "showdetail";
    }

    @RequestMapping("/update/{id}")
    public String updateJob(@PathVariable("id") long id, Model model){
        model.addAttribute("job", jobRepository.findById(id).get());
        return "jobform";
    }

    @RequestMapping("/delete/{id}")
    public String delJob(@PathVariable("id") long id, Model model){
        jobRepository.deleteById(id);
        return "redirect:/secure";
    }

}
