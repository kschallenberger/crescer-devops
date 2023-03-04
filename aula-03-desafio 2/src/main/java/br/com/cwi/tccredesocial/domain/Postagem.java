package br.com.cwi.tccredesocial.domain;

import br.com.cwi.tccredesocial.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
@Entity
public class Postagem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String imagem;
    private LocalDateTime dataInclusao;
    private boolean publico;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "curtida",
            joinColumns = @JoinColumn(name = "postagem_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> curtidas = new ArrayList<>();

    @OneToMany(mappedBy = "postagem")
    private List<Comentario> comentarios = new ArrayList<>();

    public void adicionarComentario(Comentario novoComentario) {
        novoComentario.setPostagem(this);
        this.comentarios.add(novoComentario);
    }

    public void adicionarCurtida(Usuario usuario) {
        this.curtidas.add(usuario);
    }

    public void removerCurtida(Usuario usuario) {
        this.curtidas.remove(usuario);
    }
}
