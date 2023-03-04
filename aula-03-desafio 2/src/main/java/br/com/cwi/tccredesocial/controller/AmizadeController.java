package br.com.cwi.tccredesocial.controller;

import br.com.cwi.tccredesocial.controller.response.ListarAmizadesResponse;
import br.com.cwi.tccredesocial.service.AceitarAmizadeService;
import br.com.cwi.tccredesocial.service.ListarAmizadeService;
import br.com.cwi.tccredesocial.service.RemoverAmizadeService;
import br.com.cwi.tccredesocial.service.SolicitarAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/amizades")
public class AmizadeController {

    @Autowired
    private SolicitarAmizadeService solicitarAmizadeService;
    @Autowired
    private AceitarAmizadeService aceitarAmizadeService;
    @Autowired
    private RemoverAmizadeService removerAmizadeService;
    @Autowired
    private ListarAmizadeService listarAmizadeService;

    @PostMapping("/{idUsuarioSolicitado}")
    @ResponseStatus(CREATED)
    public void solicitarAmizade(@PathVariable Long idUsuarioSolicitado) {
        solicitarAmizadeService.solicitar(idUsuarioSolicitado);
    }

    @PutMapping("/{idUsuarioSolicitado}")
    @ResponseStatus(ACCEPTED)
    public void aceitarAmizade(@PathVariable Long idUsuarioSolicitado) {
        aceitarAmizadeService.aceitar(idUsuarioSolicitado);
    }

    @GetMapping
    public Page<ListarAmizadesResponse> listarAmizades(@RequestParam(required = false, defaultValue = "") String search, Pageable pageable) {
        return listarAmizadeService.listar(search, pageable);
    }

    @DeleteMapping("/{idUsuarioSolicitado}")
    @ResponseStatus(NO_CONTENT)
    public void removerAmizade(@PathVariable Long idUsuarioSolicitado) {
        removerAmizadeService.remover(idUsuarioSolicitado);
    }

}
