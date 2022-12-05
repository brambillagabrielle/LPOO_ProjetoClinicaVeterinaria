package br.edu.ifsul.bcc.lpoo.cv.gui.funcionario;

import br.edu.ifsul.bcc.lpoo.cv.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class JPanelAFuncionario extends JPanel {
    
    private CardLayout cardLayout;
    private Controle controle;
    private JPanelAFuncionarioFormulario formulario;
    private JPanelAFuncionarioListagem listagem;

    public JPanelAFuncionario(Controle controle) {
        
        this.controle = controle;
        iniciarComponentes();
        
    }
    
    public void iniciarComponentes() {
        
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        formulario = new JPanelAFuncionarioFormulario(this, controle);
        listagem = new JPanelAFuncionarioListagem(this, controle);
        
        this.add(getFormulario(), "tela_funcionario_formulario");
        this.add(listagem, "tela_funcionario_listagem");
        
    }
    
    public void mostrarTela(String nomeTela) {
        
        if (nomeTela.equals("tela_funcionario_listagem")) {
            listagem.populaTabela();
        } else if (nomeTela.equals("tela_funcionario_formulario")) {
            getFormulario().populaComboCargo();
        }
        
        cardLayout.show(this, nomeTela);
        
    }
    
    public Controle getControle() {
        return controle;
    }

    public JPanelAFuncionarioFormulario getFormulario() {
        return formulario;
    }
    
}
