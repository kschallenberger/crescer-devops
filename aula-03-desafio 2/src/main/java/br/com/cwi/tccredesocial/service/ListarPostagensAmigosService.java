package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.response.ListarPostagensAmigosResponse;
import br.com.cwi.tccredesocial.mapper.ListarPostagensAmigosMapper;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.cwi.tccredesocial.domain.Situacao.ACEITO;

@Service
public class ListarPostagensAmigosService {

    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<ListarPostagensAmigosResponse> listar(Pageable pageable) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();

        return postagemRepository.findAllDistinctByUsuarioIdOrUsuarioAmizadesUsuarioAmigoIdAndUsuarioAmizadesSituacao
                (usuarioLogado.getId(), usuarioLogado.getId(), ACEITO, pageable)
                .map(entity -> ListarPostagensAmigosMapper.toResponse(usuarioLogado, entity));
    }
}

