package br.com.cwi.tccredesocial.domain;

import br.com.cwi.tccredesocial.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
@Entity
public class Amizade {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private Situacao situacao;
    private LocalDateTime dataPedido;

    @ManyToOne
    @JoinColumn(name = "usuario_id_ativo")
    private Usuario usuarioAtivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id_amigo")
    private Usuario usuarioAmigo;

}
