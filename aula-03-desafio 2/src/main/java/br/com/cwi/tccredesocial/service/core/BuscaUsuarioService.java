package br.com.cwi.tccredesocial.service.core;

import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BuscaUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario porId(Long idUsuarioSolicitado) {
        return usuarioRepository.findById(idUsuarioSolicitado)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Usuario Solicitado não encontrado"));
    }
}
