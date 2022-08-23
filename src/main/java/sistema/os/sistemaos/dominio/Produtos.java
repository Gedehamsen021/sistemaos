package sistema.os.sistemaos.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.NumberFormat;

@Entity // Classe é uma entidade
@Table(name = "PRODUTOS") // Tabela USUARIOS vinculada a esta entidade
public class Produtos extends EntidadeAbstrata<Long> {

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Size(min = 3, message = "Produto com nome muito curto")
    @Column(name = "descricao", nullable = false, length = 32)
    private String descricao;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;

    @NumberFormat(pattern = "#,###.00")
    @Column(name = "preco", precision = 15, scale = 2, nullable = false)
    @Type(type = "big_decimal")
    private BigDecimal preco;

    @Column(name = "grupo")
    private String grupo;

    
    @Column(name = "fornecedor")
    private String fornecedor;

    @Column(name = "linha")
    private String linha;


    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Integer getEstoque() {
        return this.estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String getGrupo() {
        return this.grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFornecedor() {
        return this.fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getLinha() {
        return this.linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }


    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }


}
