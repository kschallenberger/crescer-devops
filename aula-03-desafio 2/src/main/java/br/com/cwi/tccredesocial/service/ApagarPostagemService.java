package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaPostagemService;
import br.com.cwi.tccredesocial.validator.ApagarPostagemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApagarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private BuscaPostagemService buscaPostagemService;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private ApagarPostagemValidator apagarPostagemValidator;

    @Transactional
    public void apagar(Long idPostagem) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Postagem post = buscaPostagemService.porId(idPostagem);

        apagarPostagemValidator.validar(usuarioLogado, post);

        comentarioRepository.deleteAllByPostagemId(post.getId());
        postagemRepository.delete(post);
    }
}
