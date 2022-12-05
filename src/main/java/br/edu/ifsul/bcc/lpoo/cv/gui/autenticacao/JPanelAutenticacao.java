package br.edu.ifsul.bcc.lpoo.cv.gui.autenticacao;

import br.edu.ifsul.bcc.lpoo.cv.Controle;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class JPanelAutenticacao extends JPanel implements ActionListener {
    
    private Controle controle;
    private GridBagLayout gridLayout;
    private GridBagConstraints posicionador;
    private JLabel lblCPF;
    private JLabel lblSenha;
    private JTextField txfCPF;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Border defaultBorder;
    
    public JPanelAutenticacao(Controle controle) {
        
        this.controle = controle;
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes() {
        
        gridLayout = new GridBagLayout();
        this.setLayout(gridLayout);
        
        lblCPF = new JLabel("CPF:");        
        lblCPF.setToolTipText("lblCPF");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 0;
        this.add(lblCPF, posicionador);
        
        txfCPF = new JTextField(10);
        txfCPF.setFocusable(true);
        txfCPF.setToolTipText("txfCPF");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 1;
        defaultBorder = txfCPF.getBorder();
        this.add(txfCPF, posicionador);
        
        lblSenha = new JLabel("Senha:");        
        lblSenha.setToolTipText("lblSenha");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 0;
        this.add(lblSenha, posicionador);
        
        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);
        psfSenha.setToolTipText("psfSenha");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 1;
        this.add(psfSenha, posicionador);
        
        btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);
        btnLogar.setToolTipText("btnLogar");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;
        posicionador.gridx = 1;
        btnLogar.addActionListener(this);
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals(btnLogar.getActionCommand())) {
            
            if (txfCPF.getText().trim().length() == 11) {
                
                txfCPF.setBorder(new LineBorder(Color.green,1));
                
                if (new String(psfSenha.getPassword()).trim().length() >= 3 ){

                    psfSenha.setBorder(new LineBorder(Color.green,1));

                    controle.autenticar(txfCPF.getText().trim(), new String(psfSenha.getPassword()).trim());

                } else {

                    JOptionPane.showMessageDialog(this, "Informe Senha com 3 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);
                    psfSenha.setBorder(new LineBorder(Color.red,1));
                    psfSenha.requestFocus();                        

                }
                
            } else {
                
                JOptionPane.showMessageDialog(this, "Informe CPf com 11 dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);                    
                txfCPF.setBorder(new LineBorder(Color.red,1));
                txfCPF.requestFocus();
                
            }
            
        }
        
    }
    
}
