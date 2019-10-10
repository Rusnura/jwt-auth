package com.rtu.learning.jwt.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.MessageFormat;

@RestController
public class APIController {
  @GetMapping("/hello")
  public ResponseEntity<String> getHello(@RequestParam(defaultValue = "anonymous") String name) {
    return ResponseEntity.ok(MessageFormat.format("Hello, {0}! How are you?", name));
  }
}
