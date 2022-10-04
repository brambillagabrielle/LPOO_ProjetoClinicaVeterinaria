package br.edu.ifsul.bcc.lpoo.cv.test;

import br.edu.ifsul.bcc.lpoo.cv.model.Fornecedor;
import br.edu.ifsul.bcc.lpoo.cv.model.Produto;
import br.edu.ifsul.bcc.lpoo.cv.model.TipoProduto;
import br.edu.ifsul.bcc.lpoo.cv.model.dao.PersistenciaJPA;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

public class TestPersistenciaJPA {
    
    // @Test
    public void testConexaoJPA() {
        
        PersistenciaJPA jpa = new PersistenciaJPA();
        
        if(jpa.conexaoAberta()) {
            
            System.out.println("A conexão JPA funcionou! :)");
            jpa.fecharConexao();
            
        } else {
            System.out.println("A conexão JPA NÃO funcionou :(");
        }
        
    }
    
    // @Test
    public void testListProduto() throws Exception {
        
        PersistenciaJPA jpa = new PersistenciaJPA();
        
        if (jpa.conexaoAberta()) {
            
            List<Produto> lista = jpa.listProdutos();
            
            if (!lista.isEmpty()) {
                
                for(Produto p : lista) {
                    System.out.println("Produto: " + p);
                }
                
            } else {
                System.out.println("Produto não contém nenhum registro!");
            }
            
        } else {
            System.out.println("Conexão JPA falhou!");
        }
        
    }
    
    // @Test
    public void testPersistenciaProduto() throws Exception {
        
        PersistenciaJPA jpa = new PersistenciaJPA();
        
        if (jpa.conexaoAberta()) {
            
            List<Produto> lista = jpa.listProdutos();
            
            if (lista.isEmpty()) {
                
                Produto p = new Produto();
                p.setNome("Vermífugo");
                p.setValor(30.00F);
                p.setQuantidade(10);
                p.setTipoProduto(TipoProduto.MEDICAMENTO);
                p.setFornecedor(getFornecedor(jpa));
                
                jpa.persist(p);
                System.out.println("Novo registro de Produto com ID: " + p.getId());
                
            } else {
                
                for (Produto p : lista) {
                    
                    p.setNome(p.getNome() + " NOVO");
                    jpa.persist(p);
                    
                    jpa.remover(p);
                    
                }
                
                System.out.println("Removeu todos os registros de Produto!");
                
            }
            
            jpa.fecharConexao();
            
        } else {
            System.out.println("Conexão JPA falhou!");
        }
        
    }
    
    private Fornecedor getFornecedor(PersistenciaJPA jpa) throws Exception {
        
        if(jpa.conexaoAberta()) {
            
            List<Fornecedor> lista = jpa.listFornecedores();
            
            if (lista.isEmpty()) {
                
                Fornecedor f = new Fornecedor();
                
                f.setCpf("12345678910");
                f.setRg("9876543210");
                f.setNome("João");
                f.setSenha("123456");
                f.setNumeroCelular("");
                f.setEmail("joao@produtos-veterinarios.com");
                f.setDataCadastro(Calendar.getInstance());
                f.setDataNascimento(Calendar.getInstance());
                f.setCep("");
                f.setEndereco("Rua Principal, 1234");
                f.setComplemento("Sala 101");
                f.setIe("");
                f.setCnpj("");
                
                jpa.persist(f);
                
                return f;
            
            } else {
                return lista.get(0);
            }
            
        } else {
            System.out.println("Conexão JPA falhou!");
        }
        
        return null;
        
    }
    
}