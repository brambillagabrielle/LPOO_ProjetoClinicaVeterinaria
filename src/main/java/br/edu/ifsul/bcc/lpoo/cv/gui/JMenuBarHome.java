package br.edu.ifsul.bcc.lpoo.cv.gui;

import br.edu.ifsul.bcc.lpoo.cv.Controle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JMenuBarHome extends JMenuBar implements ActionListener {
    
    private Controle controle;
    private JMenu menuArquivo;
    private JMenuItem menuItemLogout;
    private JMenuItem menuItemSair;
    private JMenu menuCadastro;
    private JMenuItem menuItemFuncionario;    
    private JMenuItem menuItemFuncionarioDesigner;  

    public JMenuBarHome(Controle controle) {
        
        this.controle = controle;
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes() {
        
        menuArquivo = new JMenu("Arquivo");
        menuArquivo.setMnemonic(KeyEvent.VK_A);
        menuArquivo.setToolTipText("Arquivo");
        menuArquivo.setFocusable(true);
                
        menuItemSair = new JMenuItem("Sair");
        menuItemSair.setToolTipText("Sair");
        menuItemSair.setFocusable(true);
        
        menuItemLogout = new JMenuItem("Logout");
        menuItemLogout.setToolTipText("Logout");
        menuItemLogout.setFocusable(true);
        
        menuItemLogout.addActionListener(this);
        menuItemLogout.setActionCommand("menu_logout");
        menuArquivo.add(menuItemLogout);

        menuItemSair.addActionListener(this);
        menuItemSair.setActionCommand("menu_sair");
        menuArquivo.add(menuItemSair);

        menuCadastro = new JMenu("Cadastros");
        menuCadastro.setMnemonic(KeyEvent.VK_C);
        menuCadastro.setToolTipText("Cadastro");
        menuCadastro.setFocusable(true);
        
        menuItemFuncionario = new JMenuItem("Funcionário");
        menuItemFuncionario.setToolTipText("Funcionario");
        menuItemFuncionario.setFocusable(true);

        menuItemFuncionario.addActionListener(this);
        menuItemFuncionario.setActionCommand("menu_funcionario");
        menuCadastro.add(menuItemFuncionario);  
                      
        this.add(menuArquivo);
        this.add(menuCadastro);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals(menuItemSair.getActionCommand())) {
        
            int d = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema? ", "Sair", JOptionPane.YES_NO_OPTION);
            
            if(d == 0)        
                System.exit(0);
            
            
        } else if (e.getActionCommand().equals(menuItemFuncionario.getActionCommand()))
            controle.mostraTela("tela_funcionario");  
        else if(e.getActionCommand().equals(menuItemLogout.getActionCommand())) {
            //->controle.showTela("tela_autenticacao");  
        } else if(e.getActionCommand().equals(menuItemFuncionarioDesigner.getActionCommand())) {
            //controle.showTela("tela_jogador_designer");
        }
        
    }
    
}
