package kz.reself.crmservice.controller;

import kz.reself.crmservice.service.IUserService;
import kz.reself.dbstruct.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/user"))
@AllArgsConstructor
public class UserController {

    private final IUserService service;

    @PostMapping
    public Users create(@RequestBody Users user) {
        return service.create(user);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }
}
