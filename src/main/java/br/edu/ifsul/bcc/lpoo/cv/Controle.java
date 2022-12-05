package br.edu.ifsul.bcc.lpoo.cv;

import br.edu.ifsul.bcc.lpoo.cv.gui.JFramePrincipal;
import br.edu.ifsul.bcc.lpoo.cv.gui.JMenuBarHome;
import br.edu.ifsul.bcc.lpoo.cv.gui.JPanelHome;
import br.edu.ifsul.bcc.lpoo.cv.gui.funcionario.JPanelAFuncionario;
import br.edu.ifsul.bcc.lpoo.cv.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.bcc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.cv.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;

public class Controle {
    
    private PersistenciaJDBC conexaoJDBC;
    private JFramePrincipal frame;
    private JPanelAutenticacao telaAutenticacao;
    private JMenuBarHome menuBar;
    private JPanelAFuncionario telaFuncionario;
    private JPanelHome telaHome;
    
    public Controle() {
        
    }
    
    public boolean conectarBD() throws Exception {
        
        conexaoJDBC = new PersistenciaJDBC();
        
        if (getConexaoJDBC() != null) {
            return getConexaoJDBC().conexaoAberta();
        }
        
        return false;
        
    }
    
    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
    
    public void iniciaComponentes() {
        
        frame = new JFramePrincipal(this);
        
        telaAutenticacao = new JPanelAutenticacao(this);
        frame.addTela(telaAutenticacao, "tela_autenticacao");
        frame.mostraTela("tela_autenticacao");
        
        menuBar = new JMenuBarHome(this);
        telaHome = new JPanelHome(this);
        telaFuncionario = new JPanelAFuncionario(this);
        
        frame.addTela(telaAutenticacao, "tela_autenticacao");
        frame.addTela(telaHome, "tela_home");
        frame.addTela(telaFuncionario, "tela_funcionario");
        
        frame.setVisible(true);
        
    }
    
    public void autenticar(String cpf, String senha) {
        
        try {
            
            Funcionario f = conexaoJDBC.doLogin(cpf, senha);
            
            if (f != null) {
                
                JOptionPane.showMessageDialog(telaAutenticacao, "Funcionario " + f.getCpf() + " autenticado com sucesso!");
                
                frame.setJMenuBar(menuBar);
                frame.mostraTela("tela_home");
                
            } else
                JOptionPane.showMessageDialog(telaAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(telaAutenticacao, "Erro ao executar a autenticação no BD", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }
            
    }
    
    public void mostraTela(String nomeTela){
        
         if (nomeTela.equals("tela_funcionario")){
             
            telaFuncionario.mostrarTela("tela_funcionario_listagem");
            frame.mostraTela(nomeTela);
            
         }
         
    }
    
}
