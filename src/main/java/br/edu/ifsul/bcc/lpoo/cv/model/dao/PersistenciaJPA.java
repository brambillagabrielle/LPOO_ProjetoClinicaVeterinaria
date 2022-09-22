package br.edu.ifsul.bcc.lpoo.cv.model.dao;

public class PersistenciaJPA implements InterfacePersistencia {

    @Override
    public Boolean conexaoAberta() {
        // NÃO IMPLEMENTADO
        return null;
    }

    @Override
    public void fecharConexao() {
        // NÃO IMPLEMENTADO
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        // NÃO IMPLEMENTADO
        return null;
    }

    @Override
    public void persist(Object o) throws Exception {
        // NÃO IMPLEMENTADO
    }

    @Override
    public void remover(Object o) throws Exception {
        // NÃO IMPLEMENTADO
    }

}