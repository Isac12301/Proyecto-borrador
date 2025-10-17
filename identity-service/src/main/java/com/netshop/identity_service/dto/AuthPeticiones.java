package com.netshop.identity_service.dto;

public class AuthPeticiones {
    public record LoginPeticion(String email, String contrasena) {}
    public record RegistroPeticion(String email, String contrasena) {}
}
