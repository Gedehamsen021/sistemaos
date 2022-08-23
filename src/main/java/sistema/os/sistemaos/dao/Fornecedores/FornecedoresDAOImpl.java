package sistema.os.sistemaos.dao.Fornecedores;

import java.util.List;

import org.springframework.stereotype.Repository;

import sistema.os.sistemaos.dao.DaoAbstrato;
import sistema.os.sistemaos.dominio.Fornecedores;

@Repository
public class FornecedoresDAOImpl extends DaoAbstrato<Fornecedores, Long> implements FornecedoresDAO{

    @Override
    public List<Fornecedores> findByNome(String nome) {
        return createQuery("select c from Produtos c where c.descricao like concat('%',?1,'%')", nome);
    }

    @Override
    public List<Fornecedores> findByCPF(String cpf){
        String jpql = new StringBuilder("select c from Clientes c where c.cpf = ?1")
        .toString();
        return createQuery(jpql, cpf);
    }
    
}
