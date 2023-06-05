package com.kabelo.restaurant.serviceImpl;

import com.kabelo.restaurant.JWT.CustomerUserDetailsService;
import com.kabelo.restaurant.JWT.JwtFilter;
import com.kabelo.restaurant.JWT.JwtUtils;
import com.kabelo.restaurant.POJO.User;
import com.kabelo.restaurant.constants.RestaurantConstants;
import com.kabelo.restaurant.dao.UserDao;
import com.kabelo.restaurant.service.UserService;
import com.kabelo.restaurant.utils.RestaurantUtils;
import com.kabelo.restaurant.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Objects;



@Slf4j
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
        if(validateSignUpMap(requestMap)){

            User user = userDao.findByEmailId(requestMap.get("email"));
            if(Objects.isNull(user)){
                userDao.save(getUserFromMap(requestMap));
                return RestaurantUtils.getResponseEntity("Registration Successful", HttpStatus.OK);
            }
            else {
                return RestaurantUtils.getResponseEntity("Email Already Exists!", HttpStatus.BAD_REQUEST);
            }
        }
        else {return RestaurantUtils.getResponseEntity(RestaurantConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }

    }catch (Exception ex){
            ex.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private boolean validateSignUpMap(Map<String, String> requestMap){
        if (
                requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }


    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");

        return user;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
       log.info("Inside login");
       try{
            //Authenticate user
           Authentication auth = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
           );
           if(auth.isAuthenticated()){
               if(customerUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                   return new ResponseEntity<String>("{\"token\":\""+jwtUtils.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
                           customerUserDetailsService.getUserDetail().getRole()) + "\"}",
                   HttpStatus.OK);
               }
               //if user is not approved
               else{
                   return new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval."+"\"}", HttpStatus.BAD_REQUEST);
               }
           }

       }catch (Exception ex){
           log.error("{}",ex);
       }
        return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials."+"\"}", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
       try{
           if(jwtFilter.isAdmin()){

           }else{
               return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
           }

       }catch(Exception ex){
           ex.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
