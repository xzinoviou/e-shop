package com.xzinoviou.eshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/public")
public class PublicController {


    @GetMapping
    public ResponseEntity<Map<String, Object>> getPublicContent() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "This is a public resource!");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
