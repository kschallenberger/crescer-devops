package br.com.cwi.tccredesocial.security.controller;

import br.com.cwi.tccredesocial.security.controller.request.AlterarUsuarioRequest;
import br.com.cwi.tccredesocial.security.controller.request.UsuarioRequest;
import br.com.cwi.tccredesocial.security.controller.response.DetalhesUsuarioResponse;
import br.com.cwi.tccredesocial.security.controller.response.ListarUsuarioResponse;
import br.com.cwi.tccredesocial.security.controller.response.UsuarioResponse;
import br.com.cwi.tccredesocial.security.service.AlterarUsuarioService;
import br.com.cwi.tccredesocial.security.service.DetalhesUsuarioService;
import br.com.cwi.tccredesocial.security.service.IncluirUsuarioService;
import br.com.cwi.tccredesocial.security.service.ListarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;
    @Autowired
    private DetalhesUsuarioService detalhesUsuarioService;
    @Autowired
    private ListarUsuarioService listarUsuarioService;
    @Autowired
    private AlterarUsuarioService alterarUsuarioService;

    @PostMapping
    public UsuarioResponse incluir(@Valid @RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @PutMapping
    public void alterar(@Valid @RequestBody AlterarUsuarioRequest request) {
        alterarUsuarioService.alterar(request);
    }

    @GetMapping("/{idUsuario}")
    public DetalhesUsuarioResponse detalhes(@PathVariable Long idUsuario) {
        return detalhesUsuarioService.detalhes(idUsuario);
    }

    @GetMapping
    public Page<ListarUsuarioResponse> listarUsuarios(@RequestParam(required = false, defaultValue = "") String search, Pageable pageable) {
        return listarUsuarioService.listar(search, pageable);
    }
}
