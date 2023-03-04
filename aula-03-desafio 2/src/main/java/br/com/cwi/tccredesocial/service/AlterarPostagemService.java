package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.request.AlterarPostagemRequest;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaPostagemService;
import br.com.cwi.tccredesocial.validator.AlterarPostagemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlterarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private BuscaPostagemService buscaPostagemService;
    @Autowired
    private AlterarPostagemValidator alterarPostagemValidator;

    @Transactional
    public void alterar(Long idPostagem, AlterarPostagemRequest request) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Postagem post = buscaPostagemService.porId(idPostagem);

        alterarPostagemValidator.validar(usuarioLogado, post);

        post.setPublico(request.isPublico());

        postagemRepository.save(post);
    }
}
