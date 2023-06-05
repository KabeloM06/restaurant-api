package com.kabelo.restaurant.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {



    private Integer id;
    private String contactNumber;
    private String email;
    private String name;
    private String status;

    public UserWrapper(Integer id, String contactNumber, String email, String name, String status) {
        this.id = id;
        this.contactNumber = contactNumber;
        this.email = email;
        this.name = name;
        this.status = status;
    }
}
