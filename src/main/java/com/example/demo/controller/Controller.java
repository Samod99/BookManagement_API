package com.example.demo.controller;


import com.example.demo.model.Books;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/v1/")
public class Controller {



   @Autowired
     UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
     LoginRepository loginRepository;



////////////////user controller/////////////////////////



    @GetMapping(path = "/users")
    public List<User>getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/users/new-users")
    public User newUser(@RequestBody User user){

        return userRepository.save(user);
    }



    @DeleteMapping(path = "/users/no_more-users/{id}")
    public void deleteUSer(@PathVariable Integer id){

        userRepository.deleteById(id);
    }

    @GetMapping(path = "/users/getById/{id}")
    User FindUSerId(@PathVariable int id){

        return userRepository.SearchByID(id);
    }



////////////////////////////books controller////////////////////////////////////////



    @PostMapping(path = "/books")
    public List<Books>getAllBooks(){

        return bookRepository.findAll();
    }

    @PostMapping ("/books/new-books")
    public Books newBook(@RequestBody Books book){

        return bookRepository.save(book);
    }

    @GetMapping(path = "/books/getById/{id}")
  Books FindUsingId(@PathVariable int id){

        return bookRepository.SearchByID(id);
    }

    @PutMapping(path = "/books/update-books")
   public Books updateBooks(@RequestBody Books book){

        return bookRepository.save(book);
    }

    @DeleteMapping(path = "/books/no_more-books/{id}")
    public void deleteBook(@PathVariable Integer id){

        bookRepository.deleteById(id);
    }




    //////////////////////login///////////////////////////////////




    @PostMapping(path = "/login")
    String getLoginDetails(@RequestBody Login log) {

        Login log1 = loginRepository.chekUser(log.getUser_name());
        if (log.getUser_name().equals(log1.getUser_name())) {


            if (log.getPassword().equals(log1.getPassword())) {
                return ("Login complete"+" "+log1.getUser_name());
            } else {
                return "Login failed";
            }
        } else {
            System.out.println("Login failed"+" "+log1.getUser_name());
            return ("Login failed");
        }

    }


}

