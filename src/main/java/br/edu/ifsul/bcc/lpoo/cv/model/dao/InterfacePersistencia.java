package br.edu.ifsul.bcc.lpoo.cv.model.dao;

import br.edu.ifsul.bcc.lpoo.cv.model.*;
import java.util.List;

public interface InterfacePersistencia {
    
    public Boolean conexaoAberta();
    
    public void fecharConexao();
    
    public Object find(Class c, Object id) throws Exception;
    
    public void persist(Object o) throws Exception;
    
    public void remover(Object o) throws Exception;
    
    public List<Produto> listProdutos() throws Exception;
    
    public List<Fornecedor> listFornecedores() throws Exception;
    
    public List<Receita> listReceitas() throws Exception;
    
    public List<Procedimento> listProcedimentos() throws Exception;

}