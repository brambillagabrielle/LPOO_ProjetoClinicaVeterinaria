package br.edu.ifsul.bcc.lpoo.cv.test;

import br.edu.ifsul.bcc.lpoo.cv.model.Cargo;
import br.edu.ifsul.bcc.lpoo.cv.model.Fornecedor;
import br.edu.ifsul.bcc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.cv.model.Produto;
import br.edu.ifsul.bcc.lpoo.cv.model.Receita;
import br.edu.ifsul.bcc.lpoo.cv.model.TipoProduto;
import br.edu.ifsul.bcc.lpoo.cv.model.dao.PersistenciaJDBC;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

public class TestPersistenciaJDBC {
    
    // @Test
    public void testConexaoJDBC() throws Exception {
        
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        
        if(jdbc.conexaoAberta()) {
            
            System.out.println("A conexão JDBC funcionou! :)");
            jdbc.fecharConexao();
            
        } else {
            System.out.println("A conexão JDBC NÃO funcionou :(");
        }
        
    }
    
    // @Test
    public void testListProduto() throws Exception {
        
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        
        if (jdbc.conexaoAberta()) {
            
            List<Produto> lista = jdbc.listProdutos();
            
            if (!lista.isEmpty()) {
                
                for(Produto p : lista) {
                    System.out.println("Produto: " + p);
                }
                
            } else {
                System.out.println("Produto não contém nenhum registro!");
            }
            
        } else {
            System.out.println("Conexão JDBC falhou!");
        }
        
    }
    
    @Test
    public void testPersistenciaProduto() throws Exception {
        
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        
        if (jdbc.conexaoAberta()) {
            
            List<Produto> lista = jdbc.listProdutos();
            
            if (lista.isEmpty()) {
                
                Produto p = new Produto();
                p.setNome("Vermífugo");
                p.setValor(30.00F);
                p.setQuantidade(10);
                p.setTipoProduto(TipoProduto.MEDICAMENTO);
                p.setFornecedor(getFornecedor(jdbc));
                
                jdbc.persist(p);
                System.out.println("Novo registro de Produto com ID: " + p.getId());
                
            } else {
                
                for (Produto p : lista) {
                    
                    jdbc.remover(p);
                    
                }
                
                System.out.println("Removeu todos os registros de Produto!");
                
            }
            
            jdbc.fecharConexao();
            
        } else {
            System.out.println("Conexão JDBC falhou!");
        }
        
    }
    
    private Fornecedor getFornecedor(PersistenciaJDBC jdbc) throws Exception {
        
        if(jdbc.conexaoAberta()) {
            
            List<Fornecedor> lista = jdbc.listFornecedores();
            
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
                
                jdbc.persist(f);
                
                return f;
            
            } else {
                return lista.get(0);
            }
            
        } else {
            System.out.println("Conexão JDBC falhou!");
        }
        
        return null;
        
    }
    
    @Test
    public void testPersistenciaReceita() throws Exception {
        
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        
        if (jdbc.conexaoAberta()) {
            
            // recupera através do método list os registros na tabela receita
            List<Receita> lista = jdbc.listReceitas();
            
            if(lista.isEmpty()) {
                
                Receita r = new Receita();
                r.setOrientacao("Receita teste");
                //r.setConsulta(getConsulta(jdbc);
                r.setProdutos(jdbc.listProdutos());
                
                jdbc.persist(r);
                System.out.println("Receita inserida: " + r.getId()); // pode mostrar o id pois foi retornado na operação
                
            } else {
                
                for (Receita r : lista)
                    jdbc.remover(r);
                
                System.out.println("Removeu todos os registros de Receita existentes!");
                
            }
            
            jdbc.fecharConexao();
            
        } else {
            System.out.println("Falha ao estabelecer a conexão!");
        }
        
    }
    
    @Test
    public void testPersistenciaFuncionario() throws Exception {
        
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        
        if(jdbc.conexaoAberta()){
            System.out.println("Conectou no BD via JPA...");
            
            List<Funcionario> listF = jdbc.listFuncionarios();
            if(listF.isEmpty()){
                
                Funcionario f = new Funcionario();
                f.setCpf("12345678910");
                f.setSenha("1234");
                f.setRg("123456789");
                f.setNome("teste");
                f.setPis("123");
                f.setCep("12345000");
                f.setCtps("456");
                f.setCargo(Cargo.ATENDENTE);
                
                jdbc.persist(f);
                
                System.out.println("Cadastrou o Funcionario "+f.getCpf());
                
            }else{
                
                for(Funcionario f : listF){
                    
                    System.out.println("Funcionario CPF: " + f.getCpf());
                    f.setCargo(Cargo.ADESTRADOR);
                    jdbc.persist(f);
                    System.out.println("Alterou o funcionário");
                    jdbc.remover(f);
                    System.out.println("Removeu o funcionário");
                    
                }
                
            }     
            
            jdbc.fecharConexao();
            
        } else
            System.out.println("Não conectou no BD...");
        
    }
    
}