package sistema.os.sistemaos.dao.Clientes;

import java.util.List;

import org.springframework.stereotype.Repository;

import sistema.os.sistemaos.dao.DaoAbstrato;
import sistema.os.sistemaos.dominio.Clientes;

//TODO:Documentar depois
@Repository
public class ClientesDAOImpl extends DaoAbstrato<Clientes, Long> implements ClientesDAO{
    @Override
    public List<Clientes> findByCPF(String cpf){
        String jpql = new StringBuilder("select c from Clientes c where c.cpf = ?1")
        .toString();
        return createQuery(jpql, cpf);
    }

    @Override
    public List<Clientes> findByNome(String nome) {
        return createQuery("select c from Clientes c where c.nome like concat('%',?1,'%')", nome);
    }
}
