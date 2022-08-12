package sistema.os.sistemaos.dao;

import java.util.List;

import sistema.os.sistemaos.dominio.Produtos;

public interface ProdutosDAO {

    void save(Produtos produtos);

    void update(Produtos produtos);

    void delete(Long id);

    Produtos findById(Long id);

    List<Produtos> findAll();

    List<Produtos> buscarCodigo(String codigo);

    List<Produtos> buscarPorDescricao(String descricao);

}
