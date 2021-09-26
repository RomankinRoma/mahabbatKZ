package kz.reself.crmservice.controller;

import kz.reself.crmservice.service.IUserService;
import kz.reself.dbstruct.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/user"))
@AllArgsConstructor
public class UserController {

    private final IUserService service;

    @PostMapping
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }
}
