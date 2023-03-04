package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.request.AlterarComentarioRequest;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaComentarioService;
import br.com.cwi.tccredesocial.validator.AlterarComentarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private BuscaComentarioService buscaComentarioService;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private AlterarComentarioValidator alterarComentarioValidator;

    public void alterar(Long idComentario, AlterarComentarioRequest request) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Comentario comentario = buscaComentarioService.porId(idComentario);
        alterarComentarioValidator.validar(usuarioLogado, comentario);

        comentario.setTexto(request.getTexto());

        comentarioRepository.save(comentario);
    }
}
