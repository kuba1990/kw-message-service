package com.bi.service.controller;

import com.bi.service.exception.ResourceBadRequestException;
import com.bi.service.model.mongodb.PepPerson;
import com.bi.service.repositoriesMongoDB.PEPMessagesRepository;
import com.bi.service.serviceMongodb.PEPMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/start")
@CrossOrigin
public class PEPMessagesController {

    private PEPMessagesRepository pEPMessagesRepository;

    private PEPMessageService pepMessageService;

    public PEPMessagesController(PEPMessagesRepository pEPMessagesRepository, PEPMessageService pepMessageService) {
        this.pEPMessagesRepository = pEPMessagesRepository;
        this.pepMessageService = pepMessageService;
    }

    @GetMapping(params = "amount")
    public void getSortedOntolgizedMessage(@RequestParam(required = false, value = "amount", defaultValue = "10000") int amount) {

        if (amount <= 0 || amount > 10000) {
            throw new ResourceBadRequestException();
        }
        List<PepPerson> messages = pepMessageService.migrate(amount);
        log.info("Fetched {} message(s) from MongoDb", amount);

    }
}