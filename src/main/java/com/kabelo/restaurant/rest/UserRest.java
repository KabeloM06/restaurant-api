package com.kabelo.restaurant.rest;

import com.kabelo.restaurant.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true)Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true)Map<String, String> requstMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUsers();
}
