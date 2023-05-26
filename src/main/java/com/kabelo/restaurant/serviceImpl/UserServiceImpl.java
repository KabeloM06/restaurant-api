package com.kabelo.restaurant.serviceImpl;

<<<<<<< HEAD
import com.kabelo.restaurant.POJO.User;
import com.kabelo.restaurant.constants.RestaurantConstants;
import com.kabelo.restaurant.dao.UserDao;
import com.kabelo.restaurant.service.UserService;
import com.kabelo.restaurant.utils.RestaurantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.kabelo.restaurant.constants.RestaurantConstants;
import com.kabelo.restaurant.service.UserService;
import com.kabelo.restaurant.utils.RestaurantUtils;
import lombok.extern.slf4j.Slf4j;
>>>>>>> origin/master
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
<<<<<<< HEAD
import java.util.Objects;
=======
>>>>>>> origin/master

@Slf4j
@Service
public class UserServiceImpl implements UserService {

<<<<<<< HEAD
    @Autowired
    UserDao userDao;

=======
>>>>>>> origin/master
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        if(validateSignUpMap(requestMap)){
<<<<<<< HEAD
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

=======

        }
        else {return RestaurantUtils.getResponseEntity(RestaurantConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
        return null;
>>>>>>> origin/master
    }

    private boolean validateSignUpMap(Map<String, String> requestMap){
        if (
                requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

<<<<<<< HEAD
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

=======
>>>>>>> origin/master
}
