package sistema.os.sistemaos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import sistema.os.sistemaos.dao.ProdutosDAO;
import sistema.os.sistemaos.dominio.Produtos;

@Service
@Transactional(readOnly = false)
public class ProdutosServiceImpl implements ProdutosService {

    @Autowired
    private ProdutosDAO dao;

    @Override
    public void salvar(Produtos produtos) {
        dao.save(produtos);
    }

    @Override
    public void editar(Produtos produtos) {
        dao.update(produtos);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public List<Produtos> buscarPorCodigo(String codigo) {
        return dao.buscarCodigo(codigo);
    }

    @Override
    public List<Produtos> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean temOS(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Produtos> buscarPorDescricao(String descricao) {
        return dao.buscarPorDescricao(descricao);
    }

}
