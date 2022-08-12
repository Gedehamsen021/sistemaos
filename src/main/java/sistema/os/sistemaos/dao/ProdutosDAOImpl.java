package sistema.os.sistemaos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import sistema.os.sistemaos.dominio.Produtos;

//TODO:Documentar depois
@Repository
public class ProdutosDAOImpl extends DaoAbstrato<Produtos, Long> implements ProdutosDAO{

    @Override
    public List<Produtos> buscarCodigo(String codigo) {
        String jpql = new StringBuilder("select p from Produtos p where p.codigo = ?1")
        .toString();
        return createQuery(jpql, codigo);
    }

    @Override
    public List<Produtos> buscarPorDescricao(String descricao) {
        return createQuery("select c from Produtos c where c.descricao like concat('%',?1,'%')", descricao);
    }
    
}
