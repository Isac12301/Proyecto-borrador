package com.netshop.identity_service.controlador;

import com.netshop.identity_service.dto.AuthPeticiones;
import com.netshop.identity_service.dto.AuthRespuesta;
import com.netshop.identity_service.entity.Usuario;
import com.netshop.identity_service.repository.UsuarioRepositorio;
import com.netshop.identity_service.seguridad.JwtProveedor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final JwtProveedor jwtProveedor;

    @PostMapping("/login")
    public ResponseEntity<AuthRespuesta> autenticarUsuario(@RequestBody AuthPeticiones.LoginPeticion loginPeticion) {
        // Spring Security se encarga de verificar las credenciales
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginPeticion.email(), loginPeticion.contrasena())
        );

        // Si la autenticación es exitosa, generamos el token
        String token = jwtProveedor.generarToken(authentication);
        return ResponseEntity.ok(new AuthRespuesta(token));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody AuthPeticiones.RegistroPeticion registroPeticion) {
        if (usuarioRepositorio.findByEmail(registroPeticion.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: El email ya está en uso!");
        }

        // Creamos el nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(registroPeticion.email());
        // Encriptamos la contraseña antes de guardarla
        usuario.setContrasena(passwordEncoder.encode(registroPeticion.contrasena()));

        usuarioRepositorio.save(usuario);
        return ResponseEntity.ok("Usuario registrado exitosamente!");
    }
}
