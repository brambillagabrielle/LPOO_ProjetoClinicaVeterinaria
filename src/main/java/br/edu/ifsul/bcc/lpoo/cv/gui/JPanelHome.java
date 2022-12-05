package br.edu.ifsul.bcc.lpoo.cv.gui;

import br.edu.ifsul.bcc.lpoo.cv.Controle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JPanelHome extends JPanel {
    
    private JLabel lblMensagem;        
    private JLabel lblImagem;
    private JLabel lblData;
    private BorderLayout layoutGeo;
    private Controle controle;
        
    public JPanelHome(Controle controle){

        this.controle = controle;
        iniciarComponentes();
            
    }
        
    private void iniciarComponentes(){

        layoutGeo = new BorderLayout();
        this.setLayout(layoutGeo);

        lblMensagem = new JLabel("Tela de Boas Vindas ao Sistema!");
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblMensagem, BorderLayout.NORTH);

        //lblImagem = new JLabel(new ImageIcon(JPanelHome.class.getResource("/images/logo_ifsul_color.png")));
        //this.add(lblImagem, BorderLayout.CENTER);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");       

        lblData = new JLabel(df.format(c.getTime()));
        lblData.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        lblData.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblData, BorderLayout.SOUTH);   

    }
    
}
