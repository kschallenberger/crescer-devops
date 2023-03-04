package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.response.ListarPostagemCompletaResponse;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.mapper.ListarPostagemCompletaMapper;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaPostagemService;
import br.com.cwi.tccredesocial.validator.UsuarioTemAcessoPostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarPostagemCompletaService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private BuscaPostagemService buscaPostagemService;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<ListarPostagemCompletaResponse> listar(Long idPostagem, Pageable pageable) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Postagem post = buscaPostagemService.porId(idPostagem);

        UsuarioTemAcessoPostValidator.validar(usuarioLogado, post);

        return comentarioRepository.findAllByPostagemId(idPostagem, pageable)
                .map(comentario -> ListarPostagemCompletaMapper.toResponse(usuarioLogado, comentario));
    }
}
