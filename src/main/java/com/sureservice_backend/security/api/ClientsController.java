package com.sureservice_backend.security.api;


import com.sureservice_backend.security.domain.service.UserService;
import com.sureservice_backend.security.domain.service.communication.RegisterRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "sureservice")
@Tag(name = "Clients", description = "sign-up, sign-in, update clients")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {

    private final UserService userService;

    public ClientsController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("clients/sign-up")
    public ResponseEntity<?> registerClient(@Valid @RequestBody RegisterRequest request) {
        return userService.registerClient(request);
    }


}
