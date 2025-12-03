package com.example.embabel.controller;

import com.embabel.agent.api.common.Ai;
import com.embabel.agent.api.common.autonomy.AgentInvocation;
import com.embabel.agent.core.AgentPlatform;
import com.embabel.agent.core.ProcessOptions;
import com.example.embabel.model.City;
import com.example.embabel.model.EmbabelEnquiryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class EmbabelController {

    private static final Logger log = LoggerFactory.getLogger(EmbabelController.class);
    private final AgentPlatform agentPlatform;
    private final Ai ai;

    public EmbabelController(AgentPlatform agentPlatform, Ai ai) {
        this.agentPlatform = agentPlatform;
        this.ai = ai;
    }

    @PostMapping("/chat-1")
    public City embabelChat1(@RequestBody EmbabelEnquiryRequest enquiryRequest) {

        var agentInvocation = AgentInvocation.create(agentPlatform, City.class);
        City city = agentInvocation.invoke(enquiryRequest);
        log.info("chat1:: Result city: {}", city);
        return city;
    }

    @PostMapping("/chat-2")
    public City embabelChat2(@RequestBody EmbabelEnquiryRequest enquiryRequest) {

        var agentInvocation = AgentInvocation.builder(agentPlatform)
            .options(ProcessOptions.builder().verbosity(v-> v.showPrompts(true)).build()).build(City.class);
        City city = agentInvocation.invoke(enquiryRequest);
        log.info("chat2:: Result city: {}", city);
        return city;
    }

}
