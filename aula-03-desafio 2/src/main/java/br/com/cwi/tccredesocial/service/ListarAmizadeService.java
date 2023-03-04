package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.response.ListarAmizadesResponse;
import br.com.cwi.tccredesocial.mapper.ListarAmizadesMapper;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<ListarAmizadesResponse> listar(String search, Pageable pageable) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();

        return amizadeRepository
                .findAllByUsuarioAtivoAndUsuarioAmigoNomeContainingIgnoreCaseOrUsuarioAtivoAndUsuarioAmigoEmailContainingIgnoreCase
                        (usuarioLogado, search, usuarioLogado, search, pageable).map(ListarAmizadesMapper::toResponse);
    }
}
