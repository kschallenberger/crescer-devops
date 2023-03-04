package br.com.cwi.tccredesocial.security.service;

import br.com.cwi.tccredesocial.security.controller.response.DetalhesUsuarioResponse;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.cwi.tccredesocial.security.mapper.DetalhesUsuarioMapper.toResponse;

@Service
public class DetalhesUsuarioService {

    @Autowired
    private BuscaUsuarioService buscaUsuarioService;

    public DetalhesUsuarioResponse detalhes(Long idUsuario) {
        return toResponse(buscaUsuarioService.porId(idUsuario));
    }
}
