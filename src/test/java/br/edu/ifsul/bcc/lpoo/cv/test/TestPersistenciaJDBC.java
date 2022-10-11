package br.edu.ifsul.bcc.lpoo.cv.test;

import br.edu.ifsul.bcc.lpoo.cv.model.Consulta;
import br.edu.ifsul.bcc.lpoo.cv.model.Fornecedor;
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
                // método get para casos de ASSOCIAÇÃO
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
    
    /*
    
    EXEMPLOS:
    
    @Test
    public void testPersistenciaReceita() throws Exception {
        
        
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJDBC jpa = new PersistenciaJDBC();
        if(jpa.conexaoAberta()){
            
            
            List<Receita> lista = jpa.listReceitas();
            if(lista.isEmpty()){
            
                Receita r = new Receita();
                r.setOrientacao("teste de receita. ");
                r.setConsulta(getConsulta(jpa));                
                r.setProdutos(jpa.listProdutos());
                
                jpa.persist(r);
                System.out.println("Incluiu a receita: "+ r.getId());
                
                
            }else{
                
                for(Receita r : lista){
                    jpa.remover(r);
                }
                
                System.out.println("Removeu as "+lista.size()+" receitas existentes.");
            }
            
            jpa.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        
    }
    
    private Consulta getConsulta( PersistenciaJDBC jpa ) throws Exception {
        
        //criar um objeto do tipo PersistenciaJPA.     
        if(jpa.conexaoAberta()){
            
            List<Consulta> lista = jpa.listConsultas();
            if(lista.isEmpty()){
            
                Consulta c = new Consulta();
                c.setObservacao("teste de consulta");
                c.setData(Calendar.getInstance());
                c.setData_retorno(Calendar.getInstance());                        
                c.setValor(0f);
                c.setMedico(getMedico(jpa));
                c.setPet(getPet(jpa));//implementar um metodo de teste para retornar o Pet.
                jpa.persist(c);
                                
                return c;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
    
    private Fornecedor getFornecedor( PersistenciaJDBC jpa ) throws Exception {
        //criar um objeto do tipo PersistenciaJPA.     
        if(jpa.conexaoAberta()){
            
            List<Fornecedor> lista = jpa.listFornecedores();
            if(lista.isEmpty()){
            
                Fornecedor f = new Fornecedor();
                f.setNome("teste");
                f.setCnpj("08316535000");
                f.setIe("");
                f.setData_cadastro(Calendar.getInstance());
                f.setNome("Laboratório Santa Inês");
                f.setRg("123");
                f.setSenha("123");
                f.setCpf("00001337788");
                jpa.persist(f);
                                
                return f;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
    
    private Cliente getCliente( PersistenciaJDBC jpa ) throws Exception {
        //criar um objeto do tipo PersistenciaJPA.     
        if(jpa.conexaoAberta()){
            
            List<Cliente> lista = jpa.listClientes();
            if(lista.isEmpty()){
            
                Cliente c = new Cliente();
                c.setNome("teste");
                
                c.setData_ultima_visita(Calendar.getInstance());
                c.setNome("Cliente");
                c.setRg("123");
                c.setSenha("123");
                c.setCpf("00001337785");
                jpa.persist(c);
                                
                return c;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
        
    private Medico getMedico( PersistenciaJDBC jpa ) throws Exception {
        
        //criar um objeto do tipo PersistenciaJPA.     
        if(jpa.conexaoAberta()){
            
            List<Medico> lista = jpa.listMedicos();
            if(lista.isEmpty()){
            
                Medico m = new Medico();
                m.setNome("teste de teste");                
                m.setNumero_crmv("123546");
                m.setRg("123");
                m.setSenha("123");
                m.setCpf("00001337733");
                jpa.persist(m);
                                
                return m;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
    
    private Pet getPet( PersistenciaJDBC jdbc ) throws Exception {
        
        //criar um objeto do tipo.     
        if(jdbc.conexaoAberta()){
            
            List<Pet> lista = jdbc.listPets();
            if(lista.isEmpty()){
            
                Pet p = new Pet();
                p.setData_nascimento(Calendar.getInstance());                
                p.setNome("Pet de Teste");
                p.setObservacao("não consta.");
                p.setRaca(getRaca(jdbc));
                p.setCliente(getCliente(jdbc));
                jdbc.persist(p);
                                
                return p;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
    
    private Raca getRaca( PersistenciaJDBC jdbc ) throws Exception {
        
        //criar um objeto do tipo.     
        if(jdbc.conexaoAberta()){
            
            List<Raca> lista = jdbc.listRacas();
            if(lista.isEmpty()){
            
                Raca r = new Raca();
                r.setNome("Raca de teste");   
                r.setEspecie(getEspecie(jdbc));
                jdbc.persist(r);
                                
                return r;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
    
    private Especie getEspecie( PersistenciaJDBC jdbc ) throws Exception {
        
        //criar um objeto do tipo.     
        if(jdbc.conexaoAberta()){
            
            List<Especie> lista = jdbc.listEspecies();
            if(lista.isEmpty()){
            
                Especie e = new Especie();
                e.setNome("Especie de teste");   
               
                jdbc.persist(e);
                                
                return e;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
    
    */
    
}