package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.request.IncluirPostagemRequest;
import br.com.cwi.tccredesocial.controller.response.IdResponse;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.validator.IncluirPostagemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static br.com.cwi.tccredesocial.mapper.IdResponseMapper.toResponse;
import static br.com.cwi.tccredesocial.mapper.IncluirPostagemMapper.toEntity;

@Service
public class IncluirPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private IncluirPostagemValidator incluirPostagemValidator;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Transactional
    public IdResponse incluir(IncluirPostagemRequest request) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();

        incluirPostagemValidator.validar(request);

        Postagem post = toEntity(request);
        post.setUsuario(usuarioLogado);
        post.setDataInclusao(LocalDateTime.now());

        postagemRepository.save(post);

        return toResponse(post.getId());
    }
}
