package br.edu.ifsul.bcc.lpoo.cv.gui;

import br.edu.ifsul.bcc.lpoo.cv.Controle;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFramePrincipal extends JFrame {
    
    private Controle controle;
    public CardLayout cardLayout;
    public JPanel painel;
    
    public JFramePrincipal(Controle controle) {
        
        this.controle = controle;
        iniciarComponentes();
        
    }

    private void iniciarComponentes() {
    
        this.setTitle("Projeto Clínica Veterinária");
        this.setMinimumSize(new Dimension(600,600));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        
        cardLayout = new CardLayout();
        painel = new JPanel();
        painel.setLayout(cardLayout);
        this.add(painel);
        
    }
    
    public void addTela(JPanel p, String nome){   
        painel.add(p, nome);
    }
    
    public void mostraTela(String nome){
        cardLayout.show(painel, nome);
    }
    
}
