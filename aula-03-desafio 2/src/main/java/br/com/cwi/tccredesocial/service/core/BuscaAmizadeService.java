package br.com.cwi.tccredesocial.service.core;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BuscaAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    public Amizade porUsuario(Usuario usuarioAtivo, Usuario usuarioAmigo) {
        Amizade amizade = amizadeRepository.findByUsuarioAtivoAndUsuarioAmigo(usuarioAtivo, usuarioAmigo);
        if (isNull(amizade))
            throw new ResponseStatusException(BAD_REQUEST, "Amizade não encontrada");
        return amizade;
    }
}
