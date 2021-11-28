package kz.reself.business.service.impl;

import kz.reself.dbstruct.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class Producer {

    private static final String TOPIC = "match_requests";

    @Autowired
    private KafkaTemplate<String, Match> kafkaTemplate;

    public String matchRequestNotify(Match match) {
        System.out.println(String.format("#### -> Producing match request to notification service -> %s", match));
        this.kafkaTemplate.send(TOPIC, match);
        return "Success";
    }
}
