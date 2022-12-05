package br.edu.ifsul.bcc.lpoo.cv;

import javax.swing.JOptionPane;

public class ProjetoClinicaVeterinaria {
    
    private Controle controle;
    
    public ProjetoClinicaVeterinaria() {
        
        try {
            
            controle = new Controle();
            
            if (controle.conectarBD()) 
                controle.iniciaComponentes();
            else
                JOptionPane.showMessageDialog(null, "Não conectou ao BD!", "Conexão BD", JOptionPane.ERROR_MESSAGE);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar com o BD!", "Conexão BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            
        }
        
    }
    
    public static void main(String args[]) {
        new ProjetoClinicaVeterinaria();
    }
    
}
