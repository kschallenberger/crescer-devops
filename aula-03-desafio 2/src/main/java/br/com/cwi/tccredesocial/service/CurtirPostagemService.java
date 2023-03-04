package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaPostagemService;
import br.com.cwi.tccredesocial.validator.CurtirPostagemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurtirPostagemService {

    @Autowired
    private BuscaPostagemService buscaPostagemService;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private PostagemRepository postagemRepository;

    public void curtir(Long idPostagem) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Postagem post = buscaPostagemService.porId(idPostagem);
        CurtirPostagemValidator.validar(usuarioLogado, post);

        post.adicionarCurtida(usuarioLogado);

        postagemRepository.save(post);
    }
}
