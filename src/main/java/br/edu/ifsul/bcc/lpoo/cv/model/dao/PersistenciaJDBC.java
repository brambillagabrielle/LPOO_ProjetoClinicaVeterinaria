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
    
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_cv";
    private Connection con = null;
    
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
        
        if (o instanceof Produto) {
            
            Produto p = (Produto) o;
            
            if (p.getId() == null) {
                
                PreparedStatement ps = this.con.prepareStatement(
                        "INSERT INTO tb_produto (id, nome, valor, quantidade, tipoproduto, fornecedor_pessoa_cpf)"
                        + " VALUES (NEXTVAL('seq_produto_id'), ?, ?, ?, ?, ?) RETURNING id;"
                );
                
                ps.setString(1, p.getNome());
                ps.setFloat(2, p.getValor());
                ps.setInt(3, p.getQuantidade());
                ps.setString(4, p.getTipoProduto().name());
                ps.setString(5, p.getFornecedor().getCpf());
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    p.setId(rs.getInt("id"));
                }
                
            } else {
                
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

        throw new UnsupportedOperationException("NÃO IMPLEMENTADO AINDA!");
        
    }
    
}
