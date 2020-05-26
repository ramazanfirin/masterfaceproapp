package com.mastertek.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Terminal controller
 */
@RestController
@RequestMapping("/api/terminal")
public class TerminalResource {

    private final Logger log = LoggerFactory.getLogger(TerminalResource.class);

    /**
    * POST strangerSubscription
    */
    @PostMapping("/stranger-subscription")
    public String strangerSubscription() {
        return "strangerSubscription";
    }

    /**
    * POST verifySubscription
    */
    @PostMapping("/verify-subscription")
    public String verifySubscription() {
        return "verifySubscription";
    }

}
