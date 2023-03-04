package br.com.cwi.tccredesocial.service.core;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.domain.Situacao;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.com.cwi.tccredesocial.domain.Situacao.ACEITO;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaAceitarAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    public void validar(Usuario usuarioLogado, Usuario usuarioSolicitado) {
        if (usuarioLogado.getId().equals(usuarioSolicitado.getId()))
            throw new ResponseStatusException(BAD_REQUEST, "Id do usuário é igual ao usuário solicitante");
        Amizade amizade = amizadeRepository.findByUsuarioAtivoAndUsuarioAmigo(usuarioLogado, usuarioSolicitado);
        if (nonNull(amizade)) {
            if (amizade.getSituacao() == ACEITO) {
                throw new ResponseStatusException(BAD_REQUEST, "O usuário já é seu amigo");
            }
            if (amizade.getSituacao() == Situacao.SOLICITADO) {
                throw new ResponseStatusException(BAD_REQUEST, "Aguarde o usuário aceitar o pedido de amizade!");
            }
        } else {
            throw new ResponseStatusException(BAD_REQUEST, "Não existe um pedido de amizade pendente");
        }
    }
}
