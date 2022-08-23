package sistema.os.sistemaos.dao.Fornecedores;

import java.util.List;

import sistema.os.sistemaos.dominio.Fornecedores;

public interface FornecedoresDAO {

    void save(Fornecedores fornecedores);

    void update(Fornecedores fornecedores);

    void delete(Long id);

    Fornecedores findById(Long id);

    List<Fornecedores> findAll();

    List<Fornecedores> findByNome(String nome);

    List<Fornecedores> findByCPF(String cpf);
    
}
