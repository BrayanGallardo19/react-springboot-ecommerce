package ecommerce.ecommerce.service;

import ecommerce.ecommerce.dto.AuthResponse;
import ecommerce.ecommerce.dto.LoginRequest;
import ecommerce.ecommerce.dto.RegisterRequest;
import ecommerce.ecommerce.entity.User;
import ecommerce.ecommerce.enums.UserRole;
import ecommerce.ecommerce.repository.UserRepository;
import ecommerce.ecommerce.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(UserRole.CLIENT);
        userRepository.save(user);

        return buildResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return buildResponse(user);
    }

    public AuthResponse buildResponse(User user) {
        String token = jwtService.generateToken(user);
        return new AuthResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getFirstName() + " " + user.getLastName(),
            user.getRole().name()
        );
    }
}
