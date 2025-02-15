package com.judaocva.myadmincore.controllers;

import com.judaocva.myadmincore.models.dtos.users.UsersRequest;
import com.judaocva.myadmincore.services.users.UsersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/myadmin/users")
public class UsersController extends GenericController<UsersRequest, UUID> {

    public UsersController(UsersService service) {
        super(service);
    }

}
