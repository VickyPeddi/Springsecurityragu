package com.example.springsecurityRaghu.controller;


import com.example.springsecurityRaghu.model.User;
import com.example.springsecurityRaghu.service.MyUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testuser")
public class Homecontroller {
    @Autowired
    public MyUserservice userservice;
//	@Autowired
//    Jwtutil jwtutil;
//	@Autowired
//	private AuthenticationManager authenticationManager;

    @RequestMapping("/")
    public ResponseEntity<?> getallusers() {
        return ResponseEntity.status(HttpStatus.OK).body(userservice.getallUsers());
    }

    @RequestMapping("/user/{id}")
    public ResponseEntity<?> getoneUser(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(userservice.getoneUser(id));
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userservice.createuser(user));
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<?> deleteuser(@PathVariable int id) {
        userservice.deleteuser(id);
        return ResponseEntity.status(HttpStatus.GONE).body("user is deleted by id " + id);
    }

//    @PostMapping("/generatetoken")
//    public String generateToken(@RequestBody Jwtrequest authrequest) throws Exception {
//        System.out.println("befor" + authrequest.getUsername());
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
//        } catch (Exception e) {
//            throw new Exception("Invalid username and password");
//        }
//        User userdetails = userservice.getuserbyUsername(authrequest.getUsername());
//        String token = jwtutil.generateToken(userdetails.getUsername());
//        System.out.println("Username is " + authrequest.getUsername() + "token is " + token);
//        return token;
//    }


    @ExceptionHandler(Exception.class)
    public String getexception() {
        return new Exception().getMessage();

    }
}
