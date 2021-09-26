package kz.reself.business.controller;

import kz.reself.business.service.IUserService;
import kz.reself.dbstruct.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("/another")
    public List<Users> getUserFromAnotherClass() {
        return service.getUserDef();
    }
}
