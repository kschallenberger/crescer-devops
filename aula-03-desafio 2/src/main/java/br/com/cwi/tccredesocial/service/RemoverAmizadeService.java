package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaAmizadeService;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemoverAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;
    @Autowired
    private BuscaAmizadeService buscaAmizadeService;

    @Transactional
    public void remover(Long idUsuarioAmigo) {
        Usuario usuarioAmigo = buscaUsuarioService.porId(idUsuarioAmigo);
        Usuario usuarioAtivo = usuarioAutenticadoService.get();

        Amizade amizadeAmigo = buscaAmizadeService.porUsuario(usuarioAmigo, usuarioAtivo);
        Amizade amizadeAtivo = buscaAmizadeService.porUsuario(usuarioAtivo, usuarioAmigo);

        amizadeRepository.delete(amizadeAmigo);
        amizadeRepository.delete(amizadeAtivo);
    }

}
