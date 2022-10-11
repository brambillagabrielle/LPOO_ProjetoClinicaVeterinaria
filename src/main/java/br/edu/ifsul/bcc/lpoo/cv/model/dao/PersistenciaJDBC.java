package br.edu.ifsul.bcc.lpoo.cv.model.dao;

import br.edu.ifsul.bcc.lpoo.cv.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaJDBC implements InterfacePersistencia {
    
    // dados para fazer a conexão, pois não tem uma unidade de persistência
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_cv";
    private Connection con = null;
    
    // estabelece a conexão
    public PersistenciaJDBC() throws Exception {
        
        Class.forName(DRIVER);
        System.out.println("Estabelecendo conexão JDBC com " + URL + "...");
        
        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);
        
    }

    @Override
    public Boolean conexaoAberta() {
        
        try {
            
            if (con != null)
                return !con.isClosed();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
        
    }

    @Override
    public void fecharConexao() {
        
        try {
            
            this.con.close();
            System.out.println("Conexão JDBC foi fechada!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public Object find(Class c, Object id) throws Exception {

        throw new UnsupportedOperationException("NÃO IMPLEMENTADO AINDA!");

    }

    @Override
    public void persist(Object o) throws Exception {
        
        // a persistência precisa ser feita para cada classe separadamente        
        if (o instanceof Produto) {
            
            Produto p = (Produto) o;
            
            if (p.getId() == null) {
                
                // prepara uma query para inserir na tabela
                PreparedStatement ps = this.con.prepareStatement(
                        "INSERT INTO tb_produto (id, nome, valor, quantidade, tipoproduto, fornecedor_pessoa_cpf)"
                        + " VALUES (NEXTVAL('seq_produto_id'), ?, ?, ?, ?, ?) RETURNING id;"
                );
                
                // os valores são pegos pelo que foi passado, então a query possui esses ? que correspondem a ordem abaixo
                ps.setString(1, p.getNome());
                ps.setFloat(2, p.getValor());
                ps.setInt(3, p.getQuantidade());
                ps.setString(4, p.getTipoProduto().name());
                ps.setString(5, p.getFornecedor().getCpf());
                
                // a query é executada e o id é resgatado para poder ser visualizado
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    p.setId(rs.getInt("id"));
                }
                
            } else {
                
                // prepara uma query para dar um update, funcionando na mesma lógica
                PreparedStatement ps = this.con.prepareStatement(
                        "UPDATE tb_produto SET nome = ?, valor = ?, quantidade = ?, tipoproduto = ?, fornecedor_pessoa_cpf = ?"
                        + " WHERE id = ?;"
                );
                
                ps.setString(1, p.getNome());
                ps.setFloat(2, p.getValor());
                ps.setInt(3, p.getQuantidade());
                ps.setString(4, p.getTipoProduto().name());
                ps.setString(5, p.getFornecedor().getCpf());
                ps.setInt(6, p.getId());
                
                ps.execute();
                
            }
            
        } else if (o instanceof Fornecedor) {
            
        }
        
        /*
        
        EXEMPLOS:
        
        // COM AGREGAÇÃO:
        
        else if (o instanceof Receita){
            
            
            Receita r = (Receita) o;
            if(r.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_receita (id, orientacao, consulta_id) "
                                                               + "values (nextval('seq_receita_id'), ?, ?) returning id;");
                ps.setString(1, r.getOrientacao());
                ps.setInt(2,  r.getConsulta().getId());
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    r.setId(rs.getInt("id"));
                }
        
                if(r.getProdutos() != null && !r.getProdutos().isEmpty()){
                    
                    for(Produto p : r.getProdutos()){
                        
                        PreparedStatement ps2 = this.con.prepareStatement("insert into tb_receita_produto (receita_id, produto_id) "
                                                               + "values (?, ?) ");
               
                        ps2.setInt(1,  r.getId());
                        ps2.setInt(2,  p.getId());
                        
                        ps2.execute();
                        
                        ps2.close();
                        
                    }                                        
                    
                }
                rs.close();
                ps.close();
                
            }else{
               
                PreparedStatement ps = this.con.prepareStatement("update tb_receita set orientacao = ?, consulta_id = ? "
                                                               + "where id = ?");
                ps.setString(1, r.getOrientacao());
                ps.setInt(2,  r.getConsulta().getId());
                ps.setInt(3, r.getId());
                
                ps.execute();
                        
                //remove as linhas na tabela associativa
                PreparedStatement ps2 = this.con.prepareStatement("delete from tb_receita_produto where receita_id = ?");
                ps2.setInt(1, r.getId());
                
                ps2.execute();
                
                if(r.getProdutos() != null && !r.getProdutos().isEmpty()){
                    
                    for(Produto p : r.getProdutos()){
                        
                        PreparedStatement ps3 = this.con.prepareStatement("insert into tb_receita_produto (receita_id, produto_id) "
                                                               + "values (?, ?) ");
               
                        ps3.setInt(1,  r.getId());
                        ps3.setInt(2,  p.getId());
                        
                        ps3.execute();
                        
                        ps3.close();
                        
                    }                                        
                    
                }               
                
            }
            
        }else if (o instanceof Pet){
            
            Pet p = (Pet) o;
            if(p.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pet (id, nome, data_nascimento, observacao, raca_id, cliente_cpf) "
                                                               + "values (nextval('seq_pet_id'), ?, ?, ?, ?, ?) returning id;");
                ps.setString(1, p.getNome());
                ps.setDate(2, new java.sql.Date(p.getData_nascimento().getTimeInMillis()));
                ps.setString(3, p.getObservacao());
                ps.setInt(4, p.getRaca().getId());
                ps.setString(5, p.getCliente().getCpf());
                
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    p.setId(rs.getInt("id"));
                }
                
            } else {
                
                PreparedStatement ps = this.con.prepareStatement("update tb_pet set nome = ?, data_nascimento = ? "
                                                               + "where id = ?; ");
                
                ps.setString(1, p.getNome());
                ps.setDate(2, new java.sql.Date(p.getData_nascimento().getTimeInMillis()));
                ps.setInt(3, p.getId());
               
                //..implementar os demais campos...
                ps.execute();
            
            }
            
            
        }else if (o instanceof Raca){
            
            Raca r = (Raca) o;
            
            if(r.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_raca (id, nome, especie_id) "
                                                               + "values (nextval('seq_raca_id'), ?, ?) returning id;");
                ps.setString(1, r.getNome());
                ps.setInt(2, r.getEspecie().getId());
                
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    r.setId(rs.getInt("id"));
                }
                
            } else {
                
                //implementar a alteracao ...
            
            }
            
        }else if (o instanceof Especie){
            
            Especie e = (Especie) o;
            
            if(e.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_especie (id, nome) "
                                                               + "values (nextval('seq_especie_id'), ?) returning id;");
                ps.setString(1, e.getNome());
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    e.setId(rs.getInt("id"));
                }
                
            } else {
                
                //implementar a alteracao ...
            
            }
            
         
        }else if (o instanceof Fornecedor){
            
        }else if (o instanceof Medico){
            
            Medico m = (Medico) o;
            
            if(m.getData_cadastro() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (tipo, cpf, rg, nome, senha, data_cadastro) "
                                                               + "values ('M', ?, ?, ?, ?, ?); ");
                ps.setString(1, m.getCpf());
                ps.setString(2, m.getRg());
                ps.setString(3, m.getNome());
                ps.setString(4, m.getSenha());
                ps.setDate(5, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                
                ps.execute();
                ps.close();
                
                ps = this.con.prepareStatement("insert into tb_medico (cpf, numero_crmv) "
                                                               + "values (?, ?); ");
                ps.setString(1, m.getCpf());
                ps.setString(2, m.getNumero_crmv());
                
                ps.execute();
                ps.close();
                
                System.out.println("inseriu o medico ...");
                
                
                
            } else {
                
                //implementar a alteracao ...
            
            }
            
            
            
        }else if (o instanceof Cliente){
            
            Cliente c = (Cliente) o;
            
            if(c.getData_cadastro() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (tipo, cpf, rg, nome, senha, data_cadastro) "
                                                               + "values ('C', ?, ?, ?, ?, ?); ");
                ps.setString(1, c.getCpf());
                ps.setString(2, c.getRg());
                ps.setString(3, c.getNome());
                ps.setString(4, c.getSenha());
                ps.setDate(5, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                
                ps.execute();
                ps.close();
                
                ps = this.con.prepareStatement("insert into tb_cliente (cpf, data_ultima_visita) "
                                                               + "values (?, ?); ");
                ps.setString(1, c.getCpf());
                ps.setDate(2, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                
                ps.execute();
                ps.close();
                
                System.out.println("inseriu o cliente ...");
                
                
                
            } else {
                
                //implementar a alteracao ...
            
            }
            
        }else if (o instanceof Procedimento){
            
        }else if (o instanceof Consulta){
            
            Consulta c = (Consulta) o;
            if(c.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_consulta (id, "
                                                                                        + "data, "
                                                                                        + "data_retorno, "
                                                                                        + "observacao, "
                                                                                        + "valor, "
                                                                                        + "medico_cpf, "
                                                                                        + "pet_id) values "
                                                                                        + "( nextval('seq_consulta_id'), "
                                                                                        + " ?,"
                                                                                        + " ?,"
                                                                                        + " ?,"
                                                                                        + " ?,"
                                                                                        + " ?,"
                                                                                        + " ?) returning id ");
                
                ps.setDate(1, new java.sql.Date(c.getData().getTimeInMillis()));
                ps.setDate(2, new java.sql.Date(c.getData_retorno().getTimeInMillis()));
                ps.setString(3, c.getObservacao());
                ps.setFloat(4, c.getValor());
                ps.setString(5, c.getMedico().getCpf());
                ps.setInt(6, c.getPet().getId());
                
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()){
                    c.setId(rs.getInt("id"));
                }
                
            }else{
                
                
            }
            
            
        }
        
        */
        
    }

    @Override
    public void remover(Object o) throws Exception {

        if (o instanceof Produto) {
            
            Produto p = (Produto) o;
            
            PreparedStatement ps = this.con.prepareStatement(
                    "DELETE FROM tb_produto WHERE id = ?"
            );
            
            ps.setInt(1, p.getId());
            
            ps.execute();
            
        } else if (o instanceof Fornecedor) {
            
        }
        
    }

    @Override
    public List<Produto> listProdutos() throws Exception {
        
        List<Produto> lista;
        
        PreparedStatement ps = this.con.prepareStatement(
                "SELECT p.id, p.nome, p.valor, p.quantidade, p.tipoproduto, p.fornecedor_pessoa_cpf, f.ie"
                + " FROM tb_produto p, tb_fornecedor f WHERE p.fornecedor_pessoa_cpf = f.cpf ORDER BY id ASC"
        );
        
        ResultSet rs = ps.executeQuery();
        
        lista = new ArrayList();
        
        // vai adicionar cada um dos registros na lista para retornar ela
        while (rs.next()) {
            
            Fornecedor f = new Fornecedor();
            f.setCpf("fornecedor_pessoa_cpf");
            f.setIe("ie");
            
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getFloat("valor"));
            p.setQuantidade(rs.getInt("quantidade"));
            if (rs.getString("tipoproduto").equals(TipoProduto.MEDICAMENTO.name()))
                p.setTipoProduto(TipoProduto.MEDICAMENTO);
            else if (rs.getString("tipoproduto").equals(TipoProduto.CONSULTA.name()))
                p.setTipoProduto(TipoProduto.CONSULTA);
            p.setFornecedor(f);
            
            lista.add(p);
            
        }
        
        return lista;
        
    }

    @Override
    public List<Fornecedor> listFornecedores() throws Exception {

        List<Fornecedor> lista;
        
        PreparedStatement ps = this.con.prepareStatement("SELECT p.cpf, p.nome FROM tb_pessoa p, tb_fornecedor f"
                 + " WHERE p.cpf = f.cpf ORDER BY p.cpf ASC");

        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();;
         
        while (rs.next()) {
             
            Fornecedor f = new Fornecedor();
            f.setCpf(rs.getString("cpf"));
            f.setNome(rs.getString("nome"));
                  
            lista.add(f);
             
        }
         
        return lista;
        
    }
    
    @Override
    public List<Receita> listReceitas() throws Exception {
        
        System.out.println("AINDA NÃO IMPLEMENTADO!");
        return null;
        
    }
    
    /*
    @Override
    public List<Receita> listReceitas() throws Exception {
        
        List<Receita> lista = null;
        
        PreparedStatement ps = this.con.prepareStatement(" select r.id, r.orientacao, r.consulta_id "
                                                        + " from tb_receita r, tb_consulta c where r.consulta_id=c.id order by r.id asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
            Receita r = new Receita();
            r.setId(rs.getInt("id"));
            r.setOrientacao(rs.getString("orientacao"));
            Consulta c = new Consulta();
            c.setId(rs.getInt("consulta_id"));
            r.setConsulta(c);
            
            PreparedStatement ps2 = this.con.prepareStatement("select p.id, p.nome "
                                                        + " from tb_produto p, tb_receita_produto rp where p.id=rp.produto_id and rp.receita_id = ? order by rp.produto_id asc");
            
            ps2.setInt(1, r.getId());
            
            ResultSet rs2 = ps2.executeQuery();
            
            while(rs2.next()){
                
                Produto p = new Produto();
                p.setId(rs2.getInt("id"));
                p.setNome(rs2.getString("nome"));
                
                r.setProduto(p);
            }
            rs2.close();
            ps2.close();
            
            lista.add(r);
             
        }
         
        rs.close();
        ps.close();
        
        return lista;
        
        
    }

    @Override
    public List<Procedimento> listProcedimentos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Consulta> listConsultas() throws Exception {
        
        List<Consulta> lista;
        
        PreparedStatement ps = this.con.prepareStatement(" select c.id, c.data, c.data_retorno, c.observacao, c.valor, c.medico_cpf, c.pet_id "
                                                       + " from tb_consulta c order by c.id asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
             Consulta c = new Consulta();
             c.setId(rs.getInt("id"));
             Calendar cData = Calendar.getInstance();
             cData.setTimeInMillis(rs.getDate("data").getTime());
             c.setData(cData);
             
             //... recuperar os demais campos.
             
             //... recuperar as receitas da consulta
             
             
             lista.add(c);
             
        }
         
        return lista;
    }

    @Override
    public List<Medico> listMedicos() throws Exception {
        
        List<Medico> lista;
        
        PreparedStatement ps = this.con.prepareStatement(" select p.cpf, p.nome "
                                                        + " from tb_pessoa p, tb_medico m where p.cpf=m.cpf order by p.cpf asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
             Medico m = new Medico();
             m.setCpf(rs.getString("cpf"));
             m.setNome(rs.getString("nome"));
             
             lista.add(m);
             
        }
         
        return lista;
    }

    @Override
    public List<Pet> listPets() throws Exception {
        
        List<Pet> lista;
        
        PreparedStatement ps = this.con.prepareStatement(" select p.id "
                                                        + " from tb_pet p order by p.id asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
             Pet p = new Pet();
             p.setId(rs.getInt("id"));
             
             lista.add(p);
             
        }
         
        return lista;
    }

    @Override
    public List<Raca> listRacas() throws Exception {
        
        List<Raca> lista;
        
        PreparedStatement ps = this.con.prepareStatement(" select r.id "
                                                        + " from tb_raca r order by r.id asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
             Raca r = new Raca();
             r.setId(rs.getInt("id"));
             
             lista.add(r);
             
        }
         
        return lista;
    }
    
    @Override
    public List<Especie> listEspecies() throws Exception {
        
        List<Especie> lista;
        
        PreparedStatement ps = this.con.prepareStatement(" select e.id "
                                                        + " from tb_especie e order by e.id asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
             Especie e = new Especie();
             e.setId(rs.getInt("id"));
             
             lista.add(e);
             
        }
         
        return lista;
    }

    @Override
    public List<Cliente> listClientes() throws Exception {
        
        List<Cliente> lista;
        
        PreparedStatement ps = this.con.prepareStatement(" select p.cpf, p.nome "
                                                        + " from tb_pessoa p, tb_cliente c where p.cpf=c.cpf order by p.cpf asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
             Cliente c = new Cliente();
             c.setCpf(rs.getString("cpf"));
             c.setNome(rs.getString("nome"));
             
             lista.add(c);
             
        }
         
        return lista;
    }
    */
    
}
