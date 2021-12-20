package kz.reself.business.controller;

import kz.reself.business.service.IMatchService;
import kz.reself.dbstruct.model.Match;
import kz.reself.dbstruct.model.UsersDetail;
import kz.reself.dbstruct.model.enam.ApprovementStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private IMatchService service;

    @PostMapping
    public Match createMatchRequest(@RequestBody Match match) {
        return service.create(match);
    }

    @GetMapping("/notification-respond")
    public String respond(@RequestParam String email, @RequestParam Long receiverId, @RequestParam ApprovementStatus status) {
        return service.sendRespond(email, receiverId, status);
    }

    @GetMapping("/waiting-me/{email}")
    public List<UsersDetail> getRequestsForMe(@PathVariable String email) {
        return this.service.getRequestsFromUsers(email);
    }

}
