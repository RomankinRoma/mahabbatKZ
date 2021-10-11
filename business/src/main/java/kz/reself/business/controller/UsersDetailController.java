package kz.reself.business.controller;

import kz.reself.business.service.IUserDetailService;
import kz.reself.dbstruct.model.Interest;
import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-detail")
public class UsersDetailController {

    @Autowired
    private IUserDetailService service;

    @GetMapping("/{userId}")
    public UsersDetail getById(@PathVariable Long userId) {
        return service.getById(userId);
    }

    @PutMapping("/interest/insert/{userId}")
    public UsersDetail addInterest(@PathVariable Long userId,
                                   @RequestBody List<String> interests) {
        return service.addInterest(userId, interests);
    }

    @GetMapping("/recommend/{udId}")
    public List<UsersDetail> getRecommendationList(@PathVariable Long udId) {
        return service.getRecommendList(udId);
    }
}
