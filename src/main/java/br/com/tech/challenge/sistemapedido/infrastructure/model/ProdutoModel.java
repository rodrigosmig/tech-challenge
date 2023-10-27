package br.com.tech.challenge.sistemapedido.infrastructure.model;

import br.com.tech.challenge.sistemapedido.core.domain.Categoria;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Nome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private BigDecimal preco;
    private String descricao;
    private String imagens;
}
