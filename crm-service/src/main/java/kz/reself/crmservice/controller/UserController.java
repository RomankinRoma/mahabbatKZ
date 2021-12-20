package kz.reself.crmservice.controller;

import kz.reself.crmservice.service.IUserService;
import kz.reself.dbstruct.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/email/{email}")
    public Users getUserByEmail(@PathVariable String email) {
        return service.getByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") Long id, @RequestBody Users user) {
        try {
            if (service.updateUser(id, user) != null) {
                return ResponseEntity.ok(service.updateUser(id, user));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAll() {
        try {
            return ResponseEntity.ok(service.getAllUsers());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
