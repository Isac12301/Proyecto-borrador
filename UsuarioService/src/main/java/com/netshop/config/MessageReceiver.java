package com.netshop.config;

import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    public void procesarMensaje(String mensaje) {
        System.out.println("📩 Mensaje recibido en UsuarioService: " + mensaje);
    }
}