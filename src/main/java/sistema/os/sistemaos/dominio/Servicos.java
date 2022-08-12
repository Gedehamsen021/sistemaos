package sistema.os.sistemaos.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity //Classe é uma entidade
@Table(name = "ORDEMS") //Tabela ORDEMS vinculada a esta entidade
public class Servicos extends EntidadeAbstrata<Long>{

    @ManyToOne
    @JoinColumn(name = "cliente_id_fk")
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id_fk", nullable = false)
    private Produtos produto;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status", length = 20)
    private String status;

    public Clientes getCliente() {
        return this.cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }


    public Produtos getProduto() {
        return this.produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }


    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
