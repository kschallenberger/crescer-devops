package br.com.cwi.tccredesocial.controller;

import br.com.cwi.tccredesocial.controller.request.AlterarComentarioRequest;
import br.com.cwi.tccredesocial.controller.request.AlterarPostagemRequest;
import br.com.cwi.tccredesocial.controller.request.IncluirComentarioRequest;
import br.com.cwi.tccredesocial.controller.request.IncluirPostagemRequest;
import br.com.cwi.tccredesocial.controller.response.IdResponse;
import br.com.cwi.tccredesocial.controller.response.ListarPostagemCompletaResponse;
import br.com.cwi.tccredesocial.controller.response.ListarPostagensAmigosResponse;
import br.com.cwi.tccredesocial.controller.response.ListarPostagensUsuarioResponse;
import br.com.cwi.tccredesocial.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/posts")
public class PostagemController {

    @Autowired
    private IncluirPostagemService incluirPostagemService;
    @Autowired
    private AlterarPostagemService alterarPostagemService;
    @Autowired
    private ApagarPostagemService apagarPostagemService;
    @Autowired
    private ListarPostagemCompletaService listarPostagemCompletaService;
    @Autowired
    private ListarPostagensUsuarioService listarPostagensUsuarioService;
    @Autowired
    private ListarPostagensAmigosService listarPostagensAmigosService;
    @Autowired
    private IncluirComentarioService incluirComentarioService;
    @Autowired
    private AlterarComentarioService alterarComentarioService;
    @Autowired
    private ApagarComentarioService apagarComentarioService;
    @Autowired
    private CurtirPostagemService curtirPostagemService;
    @Autowired
    private RemoverCurtirPostagemService removerCurtirPostagemService;

    @PostMapping
    @ResponseStatus(CREATED)
    public IdResponse incluir(@Valid @RequestBody IncluirPostagemRequest request) {
        return incluirPostagemService.incluir(request);
    }

    @PutMapping("/{idPostagem}")
    public void alterar(@PathVariable Long idPostagem, @Valid @RequestBody AlterarPostagemRequest request) {
        alterarPostagemService.alterar(idPostagem, request);
    }

    @DeleteMapping("/{idPostagem}")
    public void apagar(@PathVariable Long idPostagem) {
        apagarPostagemService.apagar(idPostagem);
    }

    @GetMapping("/{idPostagem}")
    public Page<ListarPostagemCompletaResponse> listarPostagemComComentarios(@PathVariable Long idPostagem, Pageable pageable) {
        return listarPostagemCompletaService.listar(idPostagem, pageable);
    }

    @GetMapping("/{idUsuario}/publico")
    public Page<ListarPostagensUsuarioResponse> listarPostagensUsuario(@PathVariable Long idUsuario, Pageable pageable) {
        return listarPostagensUsuarioService.listar(idUsuario, pageable);
    }

    @GetMapping
    public Page<ListarPostagensAmigosResponse> listarPostagensAmigos(Pageable pageable) {
        return listarPostagensAmigosService.listar(pageable);
    }

    @PostMapping("/{idPostagem}/comentarios")
    @ResponseStatus(CREATED)
    public IdResponse inserirComentario(@PathVariable Long idPostagem, @Valid @RequestBody IncluirComentarioRequest request) {
        return incluirComentarioService.incluir(idPostagem, request);
    }

    @PutMapping("/comentarios/{idComentario}")
    public void alterarComentario(@PathVariable Long idComentario, @Valid @RequestBody AlterarComentarioRequest request) {
        alterarComentarioService.alterar(idComentario, request);
    }

    @DeleteMapping("/comentarios/{idComentario}")
    public void apagarComentario(@PathVariable Long idComentario) {
        apagarComentarioService.apagar(idComentario);
    }

    @PostMapping("/{idPostagem}/curtir")
    @ResponseStatus(CREATED)
    public void curtir(@PathVariable Long idPostagem) {
        curtirPostagemService.curtir(idPostagem);
    }

    @DeleteMapping("/{idPostagem}/curtir")
    public void removerCurtir(@PathVariable Long idPostagem) {
        removerCurtirPostagemService.remover(idPostagem);
    }
}