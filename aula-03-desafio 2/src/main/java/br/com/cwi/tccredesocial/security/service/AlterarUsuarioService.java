package br.com.cwi.tccredesocial.security.service;

import br.com.cwi.tccredesocial.security.controller.request.AlterarUsuarioRequest;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.mapper.AlterarUsuarioMapper;
import br.com.cwi.tccredesocial.security.repository.UsuarioRepository;
import br.com.cwi.tccredesocial.security.validator.AlterarUsuarioValidator;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private AlterarUsuarioValidator alterarUsuarioValidator;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void alterar(AlterarUsuarioRequest request) {
        Long usuarioId = usuarioAutenticadoService.getId();
        Usuario usuario = buscaUsuarioService.porId(usuarioId);

        alterarUsuarioValidator.validar(request);

        Usuario usuarioAlterado = AlterarUsuarioMapper.toEntity(usuario, request);

        usuarioRepository.save(usuarioAlterado);
    }
}
