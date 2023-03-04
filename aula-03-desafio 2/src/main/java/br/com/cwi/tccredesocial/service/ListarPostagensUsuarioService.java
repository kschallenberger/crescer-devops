package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.response.ListarPostagensUsuarioResponse;
import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.mapper.ListarPostagensUsuarioMapper;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.cwi.tccredesocial.domain.Situacao.ACEITO;

@Service
public class ListarPostagensUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;
    @Autowired
    private PostagemRepository postagemRepository;

    public Page<ListarPostagensUsuarioResponse> listar(Long idUsuario, Pageable pageable) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();
        Usuario usuarioSolicitado = buscaUsuarioService.porId(idUsuario);

        List<Amizade> amizadeEntreUsuLogadoESolicitado = usuarioLogado.getAmizades().stream()
                .filter(amizade -> (amizade.getUsuarioAmigo().getId().equals(usuarioSolicitado.getId()) &&
                        amizade.getSituacao().equals(ACEITO))).collect(Collectors.toList());

        if (amizadeEntreUsuLogadoESolicitado.isEmpty() && !idUsuario.equals(usuarioLogado.getId())) {
            return postagemRepository.findAllByUsuarioAndPublico(usuarioSolicitado, true, pageable)
                    .map(entity -> ListarPostagensUsuarioMapper.toResponse(usuarioLogado, entity));
        }
        return postagemRepository.findAllByUsuario(usuarioSolicitado, pageable)
                .map(entity -> ListarPostagensUsuarioMapper.toResponse(usuarioLogado, entity));
    }
}
