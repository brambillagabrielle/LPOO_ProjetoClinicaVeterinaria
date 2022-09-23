package br.edu.ifsul.bcc.lpoo.cv.test;

import br.edu.ifsul.bcc.lpoo.cv.model.dao.PersistenciaJPA;
import org.junit.Test;

public class TestPersistenciaJPA {
    
    @Test
    public void testConexaoJPA() {
        
        PersistenciaJPA jpa = new PersistenciaJPA();
        
        if(jpa.conexaoAberta()) {
            
            System.out.println("Executou o teste de conexão JPA! :)");
            jpa.fecharConexao();
            
        } else {
            
            System.out.println("NÃO executou o teste de conexão JPA :(");
            
        }
        
    }
    
}