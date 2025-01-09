package com.achilles.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/demo")
@Slf4j
public class DemoController {


    @GetMapping(path = "/test")
    public String getConfig(){
        log.info("-----------------***************Achilles********");
        return "------------------***************Achilles******************__________________________________";
    }



}
