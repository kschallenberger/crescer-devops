package br.com.cwi.tccredesocial.security.service;

import br.com.cwi.tccredesocial.security.controller.request.UsuarioRequest;
import br.com.cwi.tccredesocial.security.controller.response.UsuarioResponse;
import br.com.cwi.tccredesocial.security.domain.Permissao;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.repository.UsuarioRepository;
import br.com.cwi.tccredesocial.security.service.core.ValidaEmailUnico;
import br.com.cwi.tccredesocial.security.validator.IncluirUsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.cwi.tccredesocial.security.domain.Funcao.USUARIO;
import static br.com.cwi.tccredesocial.security.mapper.UsuarioMapper.toEntity;
import static br.com.cwi.tccredesocial.security.mapper.UsuarioMapper.toResponse;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IncluirUsuarioValidator incluirUsuarioValidator;
    @Autowired
    private ValidaEmailUnico validaEmailUnico;

    public UsuarioResponse incluir(UsuarioRequest request) {
        incluirUsuarioValidator.validar(request);
        validaEmailUnico.validar(request.getEmail());
        Usuario usuario = toEntity(request);
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));
        usuario.adicionarPermissao(getPermissaoPadrao());
        usuario.setAtivo(true);

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }

    private Permissao getPermissaoPadrao() {
        Permissao permissao = new Permissao();
        permissao.setFuncao(USUARIO);
        return permissao;
    }
}
