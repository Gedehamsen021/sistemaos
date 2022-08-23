package sistema.os.sistemaos.service.Fornecedores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistema.os.sistemaos.dao.Fornecedores.FornecedoresDAO;
import sistema.os.sistemaos.dominio.Fornecedores;

@Service @Transactional(readOnly = false)
public class FornecedoresServiceImpl implements FornecedoresService{

    @Autowired
    private FornecedoresDAO dao;

    @Override
    public void salvar(Fornecedores fornecedores) {
       dao.save(fornecedores);
    }

    @Override
    public void editar(Fornecedores fornecedores) {
        dao.update(fornecedores);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Fornecedores> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Fornecedores> buscarPorNome(String nome) {
        return dao.findByNome(nome);
    }

    @Override
    public List<Fornecedores> buscarCPF(String cpf) {
        return dao.findByCPF(cpf);
    }

    @Override
    public Fornecedores buscarPorId(Long id) {
        return dao.findById(id);
    }
}
