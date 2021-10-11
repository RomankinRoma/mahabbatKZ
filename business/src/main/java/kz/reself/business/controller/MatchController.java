package kz.reself.business.controller;

import kz.reself.business.service.IMatchService;
import kz.reself.dbstruct.model.Match;
import kz.reself.dbstruct.model.enam.ApprovementStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String respond(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam ApprovementStatus status) {
        return service.sendRespond(senderId, receiverId, status);
    }

}
