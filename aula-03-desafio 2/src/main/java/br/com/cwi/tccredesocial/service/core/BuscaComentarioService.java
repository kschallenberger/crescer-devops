package br.com.cwi.tccredesocial.service.core;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BuscaComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario porId(Long idComentario) {
        return comentarioRepository.findById(idComentario)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Comentário não encontrado!"));
    }
}
