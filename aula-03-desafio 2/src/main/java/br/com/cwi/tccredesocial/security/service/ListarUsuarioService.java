package br.com.cwi.tccredesocial.security.service;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.controller.response.ListarUsuarioResponse;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.mapper.ListarUsuarioMapper;
import br.com.cwi.tccredesocial.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class ListarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private AmizadeRepository amizadeRepository;

    public Page<ListarUsuarioResponse> listar(String search, Pageable pageable) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();
        List<Amizade> amizades = new ArrayList<>();
        if (nonNull(usuarioLogado)) {
            amizades = amizadeRepository.findByUsuarioAtivo(usuarioLogado);
        }
        List<Amizade> finalAmizades = amizades;
        return usuarioRepository.findAllByAtivoAndNomeContainingIgnoreCaseOrAtivoAndEmailContainingIgnoreCase
                        (true, search, true, search, pageable)
                .map(usuario -> ListarUsuarioMapper.toResponse(usuario, finalAmizades));
    }
}
