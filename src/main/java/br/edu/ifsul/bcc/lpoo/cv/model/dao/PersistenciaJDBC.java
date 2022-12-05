package br.edu.ifsul.bcc.lpoo.cv.model.dao;

import br.edu.ifsul.bcc.lpoo.cv.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
            
        } else if (o instanceof Receita) {
            
            Receita r = (Receita) o;
            
            if (r.getId() == null) {
                
                PreparedStatement ps = this.con.prepareStatement(
                        "INSERT INTO tb_receita (id, orientacao, consulta_id)"
                                + " VALUES (NEXTVAL('seq_receita_id'), ?, ?) RETURNING id"
                );
                
                ps.setString(1, r.getOrientacao());
                ps.setInt(2, r.getConsulta().getId());
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    r.setId(rs.getInt("id"));
                }
                
                if (r.getProdutos() != null && !r.getProdutos().isEmpty()) {
                    
                    for (Produto p : r.getProdutos()) {
                        
                        PreparedStatement ps2 = this.con.prepareStatement(
                                "INSERT INTO tb_receita_produto(receita_id, produto_id"
                                        + " VALUES (?, ?)"
                        );
                        
                        ps2.setInt(1, r.getId());
                        ps2.setInt(2, p.getId());
                        
                        ps2.execute();
                        ps2.close();
                        
                    }
                    
                }
                
            } else {
                
                PreparedStatement ps = this.con.prepareStatement(
                        "UPDATE tb_receita SET orientacao = ?, consulta_id = ?"
                                + " WHERE id = ?"
                );
                
                ps.setString(1, r.getOrientacao());
                ps.setInt(2, r.getConsulta().getId());
                ps.setInt(3, r.getId());
                
                ps.execute();
                                
                PreparedStatement ps2 = this.con.prepareStatement(
                        "DELETE FROM tb_receita_produto WHERE receita_id = ?"
                );
                
                ps2.setInt(1, r.getId());
                
                ps2.execute();
                
                if (r.getProdutos() != null && !r.getProdutos().isEmpty()) {
                    
                    for (Produto p : r.getProdutos()) {
                        
                        PreparedStatement ps3 = this.con.prepareStatement(
                                "INSERT INTO tb_receita_produto(receita_id, produto_id"
                                        + " VALUES (?, ?)"
                        );
                        
                        ps2.setInt(1, r.getId());
                        ps2.setInt(2, p.getId());
                        
                        ps2.execute();
                        ps2.close();
                        
                    }
                
                }
                
            }
            
        }else if (o instanceof Funcionario){
            
            Funcionario f = (Funcionario) o;
            
            if(f.getDataCadastro() == null){
                
                PreparedStatement ps = this.con.prepareStatement("INSERT INTO tb_pessoa (tipo, cpf, rg, nome, senha, datacadastro) "
                                                               + "VALUES ('U', ?, ?, ?, ?, ?) RETURNING datacadastro; ");
                ps.setString(1, f.getCpf());
                ps.setString(2, f.getRg());
                ps.setString(3, f.getNome());
                ps.setString(4, f.getSenha());
                ps.setDate(5, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                
                ResultSet rs  = ps.executeQuery();
                if(rs.next()){
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(rs.getDate("datacadastro").getTime());
                    f.setDataCadastro(c);
                }
                rs.close();
                ps.close();
                
                ps = this.con.prepareStatement("INSERT INTO tb_funcionario (cpf, ctps, pis, cargo) "
                                                               + "VALUES (?, ?, ?, ?); ");
                ps.setString(1, f.getCpf());
                ps.setString(2, f.getCtps());
                ps.setString(3, f.getPis());
                ps.setString(4, f.getCargo().toString());
                
                ps.execute();
                ps.close();
                
                System.out.println("Funcionário inserido!");
                
                
            }else{
                
                PreparedStatement ps = this.con.prepareStatement("UPDATE tb_pessoa SET rg = ?, nome = ?, senha = ? WHERE cpf = ? ");
                ps.setString(1, f.getRg());
                ps.setString(2, f.getNome());
                ps.setString(3, f.getSenha());
                ps.setString(4, f.getCpf());                
                
                ps.execute();
                ps.close();
                
                ps = this.con.prepareStatement("UPDATE tb_funcionario SET ctps = ?, pis = ?, cargo = ? WHERE cpf = ? ");
                ps.setString(1, f.getCtps());
                ps.setString(2, f.getPis());
                ps.setString(3, f.getCargo().toString());
                ps.setString(4, f.getCpf());
                
                ps.execute();
                ps.close();
                
                System.out.println("Funcionario alterado!");
                
            }
            
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
            
        } else if(o instanceof Funcionario){
           
           Funcionario f = (Funcionario) o;
           
           PreparedStatement ps = this.con.prepareStatement("DELETE FROM tb_funcionario WHERE cpf = ? ");
           ps.setString(1, f.getCpf());
           
           ps.execute();
           ps.close();
           
           ps = this.con.prepareStatement("DELETE FROM tb_pessoa WHERE cpf = ? ");
           ps.setString(1, f.getCpf());
           
           ps.execute();
           ps.close();
        
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
        
        List<Receita> lista = null;
        
        PreparedStatement ps = this.con.prepareStatement(
                "SELECT r.id, r.orientacao, r.consulta_id"
                        + " FROM tb_receita r, tb_consulta c"
                        + " WHERE r.consulta_id = c.id"
        );
        
        ResultSet rs = ps.executeQuery();
        
        lista = new ArrayList();
        
        while(rs.next()) {
            
            Receita r = new Receita();
            
            r.setId(rs.getInt("id"));
            r.setOrientacao(rs.getString("orientacao"));
            
            Consulta c = new Consulta();
            
            c.setId(rs.getInt("consulta_id"));
            r.setConsulta(c);
            
            PreparedStatement ps2 = this.con.prepareStatement(
                    "SELECT p.id, p.nome FROM tb_produto p, tb_receita_produto rp "
                            + " WHERE p.id = rp.produto_id AND rp.receita_id = ? ORDER BY rp.produto_id ASC"
            );
            
            ps2.setInt(1, r.getId());
            
            ResultSet rs2 = ps.executeQuery();
            
            while(rs2.next()) {
                
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                
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
    public List<Funcionario> listFuncionarios() throws Exception {
        
        List<Funcionario> lista;
        
        PreparedStatement ps = this.con.prepareStatement(" select p.cpf, p.rg, p.nome, p.senha, f.cargo, f.ctps, f.pis, p.datacadastro "
                                                        + " from tb_pessoa p, tb_funcionario f where p.cpf=f.cpf order by p.datacadastro asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
            Funcionario f = new Funcionario();
            f.setCpf(rs.getString("cpf"));
            f.setRg(rs.getString("rg"));
            f.setNome(rs.getString("nome"));
            f.setSenha(rs.getString("senha"));
            f.setCargo(Cargo.getCargo(rs.getString("cargo")));
            f.setCtps(rs.getString("ctps"));
            f.setPis(rs.getString("pis"));            
            Calendar c = Calendar.getInstance();                
            c.setTimeInMillis(rs.getDate("datacadastro").getTime());                
            f.setDataCadastro(c);
             
            lista.add(f);
             
        }
         
        return lista;
        
    }
    
    public Funcionario doLogin(String cpf, String senha) throws Exception {

        Funcionario funcionario = null;

        PreparedStatement ps = this.con.prepareStatement(
                "SELECT p.cpf, to_char(p.datacadastro, 'dd/mm/yyyy') AS datacadastro, p.nome"
                        + " FROM tb_pessoa p WHERE p.cpf = ? AND p.senha = ?"
        );

        ps.setString(1, cpf);
        ps.setString(2, senha);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            funcionario = new Funcionario();
            funcionario.setCpf(rs.getString("cpf"));

        }
        
        ps.close();
        
        return funcionario;

    }
    
}
