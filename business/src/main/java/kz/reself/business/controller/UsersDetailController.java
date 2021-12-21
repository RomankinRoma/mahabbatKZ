package kz.reself.business.controller;

import kz.reself.business.repository.UsersDetailRepository;
import kz.reself.business.service.IUserDetailService;
import kz.reself.business.service.impl.UserServiceImpl;
import kz.reself.dbstruct.model.Interest;
import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-detail")
public class UsersDetailController {

    @Autowired
    private IUserDetailService service;

    @Autowired
    private UsersDetailRepository usersDetailRepository;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public UsersDetail addFullInfo(@RequestBody UsersDetail usersDetail, @RequestParam String email) {
        return this.service.addFullInfo(usersDetail, email);
    }

    @GetMapping("/byEmail/{email}")
    public UsersDetail getByEmail(@PathVariable String email) {
        return this.service.getDetailByEmail(email);
    }

    @GetMapping("/{userId}")
    public UsersDetail getById(@PathVariable Long userId) {
        return service.getById(userId);
    }

    @PutMapping("/interest/insert/{userId}")
    public UsersDetail addInterest(@PathVariable Long userId,
                                   @RequestBody List<String> interests) {
        return service.addInterest(userId, interests);
    }

    @GetMapping("/recommend/{userId}")
    public List<UsersDetail> getRecommendationList(@PathVariable Long userId) {
        return service.getRecommendList(userId);
    }

    @GetMapping("/loc-gen")
    public List<UsersDetail> getRecommendationList(@RequestParam Map<String, String> param) {
        return service.getUsersDetailByFilter(param);
    }

    @GetMapping("/id/{email}")
    public Long getUserId(@PathVariable String email) {
        return userService.getUserIdByEmail(email);
    }

    @GetMapping("/recommend-email/{email}")
    public List<UsersDetail> getRecommendationListByEmail(@PathVariable String email) {
        return service.getRecommendListByEmail(email);
    }
}
