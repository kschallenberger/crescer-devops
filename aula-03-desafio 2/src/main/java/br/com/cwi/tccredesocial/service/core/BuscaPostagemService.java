package br.com.cwi.tccredesocial.service.core;

import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BuscaPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    public Postagem porId(Long idPostagem) {
        return postagemRepository.findById(idPostagem)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Post não encontrado!"));
    }
}
