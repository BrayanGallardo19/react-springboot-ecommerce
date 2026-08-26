package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.entity.User;
import ecommerce.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/client/me")
    @PreAuthorize("hasAnyRole('ADMIN','WORKER','CLIENT')")
    public ResponseEntity<User> getClientProfile(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(userRepository.findByEmail(email).orElseThrow());
    }

    @GetMapping("/worker/dashboard")
    @PreAuthorize("hasAnyRole('ADMIN','WORKER')")
    public ResponseEntity<String> workerDashboard() {
        return ResponseEntity.ok("Panel del trabajador disponible");
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Panel del administrador disponible");
    }
}
