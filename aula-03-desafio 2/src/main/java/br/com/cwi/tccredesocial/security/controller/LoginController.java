package br.com.cwi.tccredesocial.security.controller;

import br.com.cwi.tccredesocial.security.controller.response.UsuarioResponse;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @PostMapping
    public UsuarioResponse login() {
        return usuarioAutenticadoService.getResponse();
    }
}
