package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaAmizadeService;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import br.com.cwi.tccredesocial.service.core.ValidaAceitarAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static br.com.cwi.tccredesocial.domain.Situacao.ACEITO;

@Service
public class AceitarAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;
    @Autowired
    private ValidaAceitarAmizadeService validaAceitarAmizadeService;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;
    @Autowired
    private BuscaAmizadeService buscaAmizadeService;

    @Transactional
    public void aceitar(Long idUsuarioAmigo) {
        Usuario usuarioAmigo = buscaUsuarioService.porId(idUsuarioAmigo);
        Usuario usuarioAtivo = usuarioAutenticadoService.get();

        validaAceitarAmizadeService.validar(usuarioAtivo, usuarioAmigo);

        Amizade amizadeAmigo = buscaAmizadeService.porUsuario(usuarioAmigo, usuarioAtivo);
        Amizade amizadeAtivo = buscaAmizadeService.porUsuario(usuarioAtivo, usuarioAmigo);

        amizadeAmigo.setSituacao(ACEITO);
        amizadeAmigo.setDataPedido(LocalDateTime.now());
        amizadeAtivo.setSituacao(ACEITO);
        amizadeAtivo.setDataPedido(LocalDateTime.now());

        amizadeRepository.save(amizadeAmigo);
        amizadeRepository.save(amizadeAtivo);
    }
}