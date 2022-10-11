package br.edu.ifsul.bcc.lpoo.cv.model.dao;

import br.edu.ifsul.bcc.lpoo.cv.model.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenciaJPA implements InterfacePersistencia {
    
    // precisa criar uma EntityManager para fazer as operações no banco

    public EntityManagerFactory factory;
    public EntityManager entity;

    public PersistenciaJPA(){
        
        factory = Persistence.createEntityManagerFactory("pu_db_cv");
        entity = factory.createEntityManager();  
        
    }
    
    @Override
    public Boolean conexaoAberta() {
        
        return entity.isOpen();  
        
    }

    @Override
    public void fecharConexao() {
        
        entity.close(); 
        
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
       
        return entity.find(c, id);   
        
    }

    @Override
    public void persist(Object o) throws Exception {
        
        entity.getTransaction().begin();
        entity.persist(o);
        entity.getTransaction().commit();
        
    }

    @Override
    public void remover(Object o) throws Exception {
        
        entity.getTransaction().begin();
        entity.remove(o);
        entity.getTransaction().commit();
        
    }
    
    // consulta nomeada
    @Override
    public List<Produto> listProdutos() throws Exception {
        
        // cria o nome da consulta, que tem a query na classe correspondente
        return entity.createNamedQuery("Produto.orderbyid").getResultList();
        
    }
    
    @Override
    public List<Fornecedor> listFornecedores() throws Exception {
        
        return entity.createNamedQuery("Fornecedor.orderbynome").getResultList();
        
    }
    
    @Override
    public List<Receita> listReceitas() throws Exception {
        
        return entity.createNamedQuery("Receita.orderbyid").getResultList();
        
    }
    
}