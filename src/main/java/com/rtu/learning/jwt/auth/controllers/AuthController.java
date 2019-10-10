package com.rtu.learning.jwt.auth.controllers;

import com.rtu.learning.jwt.auth.configs.JwtTokenUtil;
import com.rtu.learning.jwt.auth.models.JwtRequest;
import com.rtu.learning.jwt.auth.models.JwtResponse;
import com.rtu.learning.jwt.auth.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtUserDetailsService userDetailsService;

  @PostMapping("/authenticate")
  public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
    final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponse(token));
  }
}
