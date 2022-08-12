package sistema.os.sistemaos.dao;

import java.util.List;

import sistema.os.sistemaos.dominio.Clientes;

public interface ClientesDAO {

    void save(Clientes clientes);

    void update(Clientes clientes);

    void delete(Long id);

    Clientes findById(Long id);

    List<Clientes> findAll();

    List<Clientes> buscarCPF(String cpf);

    List<Clientes> findByNome(String nome);

}
