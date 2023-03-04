package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import br.com.cwi.tccredesocial.service.core.ValidaSolicitarAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.tccredesocial.mapper.SolicitarAmizadeMapper.*;

@Service
public class SolicitarAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;
    @Autowired
    private ValidaSolicitarAmizadeService validaSolicitarAmizadeService;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;

    @Transactional
    public void solicitar(Long idUsuarioAmigo) {
        Usuario usuarioAmigo = buscaUsuarioService.porId(idUsuarioAmigo);
        Usuario usuarioLogado = usuarioAutenticadoService.get();

        validaSolicitarAmizadeService.validar(usuarioLogado, usuarioAmigo);

        Amizade amizadeAtivo = toEntityAtivo(usuarioLogado, usuarioAmigo);
        Amizade amizadeAmigo = toEntityAmigo(usuarioLogado, usuarioAmigo);

        amizadeRepository.save(amizadeAtivo);
        amizadeRepository.save(amizadeAmigo);
    }
}
