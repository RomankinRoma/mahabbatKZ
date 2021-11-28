package kz.reself.authservice.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(("/auth"))
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return "good job adil";
    }
}
