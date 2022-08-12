package sistema.os.sistemaos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistema.os.sistemaos.dao.ServicosDAO;
import sistema.os.sistemaos.dominio.Servicos;


//TODO:Documentar aqui
@Service @Transactional(readOnly = false)
public class ServicosServiceImpl implements ServicosService {

    @Autowired
    private ServicosDAO dao;

    @Override
    public void salvar(Servicos servicos) {
       dao.save(servicos);
    }

    @Override
    public void editar(Servicos servicos) {
        dao.update(servicos);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override @Transactional(readOnly = true)
    public Servicos buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override @Transactional(readOnly = true)
    public List<Servicos> buscarTodos() {
        return dao.findAll();
    }
}
