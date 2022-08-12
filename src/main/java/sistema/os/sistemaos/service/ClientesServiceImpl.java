package sistema.os.sistemaos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistema.os.sistemaos.dao.ClientesDAO;
import sistema.os.sistemaos.dominio.Clientes;

//TODO:Documentar aqui
@Service @Transactional(readOnly = false)
public class ClientesServiceImpl implements ClientesService{
    
    @Autowired
    private ClientesDAO dao;

    @Override
    public void salvar(Clientes cliente) {
        dao.save(cliente);
    }

    @Override
    public void editar(Clientes cliente) {
        dao.update(cliente);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override @Transactional(readOnly = true)
    public Clientes buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override @Transactional(readOnly = true)
    public List<Clientes> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean temOS(Long id) {
        if(buscarPorId(id).getOS().isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Clientes> buscarCPF(String cpf) {
        return dao.buscarCPF(cpf);
    }

    @Override
    public List<Clientes> buscarPorNome(String nome) {
        return dao.findByNome(nome);
    }
}