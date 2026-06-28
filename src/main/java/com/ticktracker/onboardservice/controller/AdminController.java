package com.ticktracker.onboardservice.controller;

import com.ticktracker.onboardservice.enums.Status;
import com.ticktracker.onboardservice.model.User;
import com.ticktracker.onboardservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

   @PostMapping("/users/{status}")
   public ResponseEntity<Page<User>> getUsers(
           @PathVariable
           @RequestParam(required = false) String status,
           @RequestParam(required = false , defaultValue = "1") int pageSize,
           @RequestParam(required = false , defaultValue = "5") int pageNo,
           @RequestParam(required = false , defaultValue = "name") String sortBy,
           @RequestParam(required = false , defaultValue = "ASC") String sortDirection

   )
   {

       Sort sort = Sort.by(sortBy);
       if(sortDirection.equalsIgnoreCase("DESC"))
       {
           sort=sort.descending();
       }
       else {
          sort=sort.ascending();
       }
       Pageable page =  PageRequest.of(pageNo-1,pageSize , sort);
       return ResponseEntity.ok(userService.getUsers(status,page));

   }





    @GetMapping
    public String demoEndpoint()
    {
        return "Hi , This is a demo endpoint to test security features";
    }
}
