package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaComentarioService;
import br.com.cwi.tccredesocial.validator.ApagarComentarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApagarComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private BuscaComentarioService buscaComentarioService;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private ApagarComentarioValidator apagarComentarioValidator;

    @Transactional
    public void apagar(Long idComentario) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Comentario comentario = buscaComentarioService.porId(idComentario);

        apagarComentarioValidator.validar(usuarioLogado, comentario);

        comentarioRepository.delete(comentario);
    }
}
