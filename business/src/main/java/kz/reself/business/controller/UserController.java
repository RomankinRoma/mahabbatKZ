package kz.reself.business.controller;

import kz.reself.business.service.IUserService;
import kz.reself.dbstruct.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping
    public Users create(Users user) {
        return service.create(user);
    }

    @GetMapping("/another")
    public List<Users> getUserFromAnotherClass() {
        return service.getUserDef();
    }

    @GetMapping("/check")
    public void getCheck() {
        service.getCheck();
    }
}
