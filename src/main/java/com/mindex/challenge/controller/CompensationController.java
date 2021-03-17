package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation/create")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation to create is: [{}]", compensation);

        return compensationService.create(compensation);
    }

    @GetMapping("/compensation/read/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received compensation to read for employeeId is: [{}]", id);

        return compensationService.read(id);
    }
}
