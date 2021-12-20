package kz.reself.banservice.controller;

import kz.reself.banservice.repository.UsersDetailRepository;
import kz.reself.banservice.service.IUserDetailService;
import kz.reself.banservice.service.IUserService;
import kz.reself.dbstruct.model.Users;
import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private IUserDetailService iUserDetailService;

    @GetMapping("/count/{id}")
    public Integer getUserReportCount(@PathVariable Long id) {
        UsersDetail usersDetail = iUserDetailService.getById(id);
        return usersDetail.getReportCounter();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsersDetail> reportUser (@PathVariable("id") Long id, @RequestBody UsersDetail user) {
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


}
