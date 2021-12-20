package kz.reself.event.controller;

import kz.reself.dbstruct.model.Users;
import kz.reself.event.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/addEvent")
    public Users addEvent(@RequestParam String email, @RequestParam Long eventId) {
        return this.userService.addEvent(email, eventId);
    }
}
