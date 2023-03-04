package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.request.IncluirComentarioRequest;
import br.com.cwi.tccredesocial.controller.response.IdResponse;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.mapper.IdResponseMapper;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaPostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.tccredesocial.mapper.IncluirComentarioMapper.toEntity;

@Service
public class IncluirComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private BuscaPostagemService buscaPostagemService;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Transactional
    public IdResponse incluir(Long idPostagem, IncluirComentarioRequest request) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Postagem post = buscaPostagemService.porId(idPostagem);

        Comentario novoComentario = toEntity(usuarioLogado, request);
        post.adicionarComentario(novoComentario);

        comentarioRepository.save(novoComentario);

        return IdResponseMapper.toResponse(novoComentario.getId());
    }
}
