package br.edu.ifsul.bcc.lpoo.cv.gui.funcionario;

import br.edu.ifsul.bcc.lpoo.cv.Controle;
import br.edu.ifsul.bcc.lpoo.cv.model.Funcionario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JPanelAFuncionarioListagem extends JPanel implements ActionListener {

    private JPanelAFuncionario pnlAFuncionario;
    private Controle controle;
    private BorderLayout borderLayout;
    private JPanel pnlNorte;
    private JLabel lblFiltro;
    private JTextField txfFiltro;
    private JButton btnFiltro;
    private JPanel pnlCentro;
    private JScrollPane scpListagem;
    private JTable tblListagem;
    private DefaultTableModel modeloTabela;
    private JPanel pnlSul;
    private JButton btnNovo;
    private JButton btnAlterar;
    private JButton btnRemover;
    private SimpleDateFormat format;
    
    public JPanelAFuncionarioListagem(JPanelAFuncionario pnlAFuncionario, Controle controle){
        
        this.pnlAFuncionario = pnlAFuncionario;
        this.controle = controle;
        iniciaComponentes();
        
    }
    
    public void populaTabela(){
        
        DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();

        model.setRowCount(0);

        try {

            List<Funcionario> listFuncionarios = controle.getConexaoJDBC().listFuncionarios();
            for(Funcionario f : listFuncionarios){
                                
                model.addRow(new Object[]{
                    f.getCpf(),                                         
                    f.getRg(), 
                    f.getNome(), 
                    f.getCargo(),  
                    format.format(f.getDataCadastro().getTime())
                });

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Erro ao listar Funcionários:" + e.getLocalizedMessage(), "Jogadores", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            
        }        
        
    }
    
    private void iniciaComponentes(){
        
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        pnlNorte = new JPanel();
        pnlNorte.setLayout(new FlowLayout());
        
        lblFiltro = new JLabel("Filtrar por CPF:");
        pnlNorte.add(lblFiltro);
        
        txfFiltro = new JTextField(20);
        pnlNorte.add(txfFiltro);
        
        btnFiltro = new JButton("Filtrar");
        btnFiltro.addActionListener(this);
        btnFiltro.setFocusable(true);   
        btnFiltro.setToolTipText("btnFiltrar"); 
        btnFiltro.setActionCommand("botao_filtro");
        pnlNorte.add(btnFiltro);
        
        this.add(pnlNorte, BorderLayout.NORTH);
        
        pnlCentro = new JPanel();
        pnlCentro.setLayout(new BorderLayout());
        
            
        scpListagem = new JScrollPane();
        tblListagem =  new JTable();
        
        modeloTabela = new DefaultTableModel(
            new String [] {
                    "CPF", "RG", "Nome", "Cargo", "Data de Cadastro"
            }, 0);
        
        tblListagem.setModel(modeloTabela);
        scpListagem.setViewportView(tblListagem);
    
        pnlCentro.add(scpListagem, BorderLayout.CENTER);
        this.add(pnlCentro, BorderLayout.CENTER);
        
        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());
        
        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(this);
        btnNovo.setFocusable(true);   
        btnNovo.setToolTipText("btnNovo");
        btnNovo.setMnemonic(KeyEvent.VK_N);
        btnNovo.setActionCommand("botao_novo");
        
        pnlSul.add(btnNovo);
        
        btnAlterar = new JButton("Editar");
        btnAlterar.addActionListener(this);
        btnAlterar.setFocusable(true); 
        btnAlterar.setToolTipText("btnAlterar");
        btnAlterar.setActionCommand("botao_alterar");
        
        pnlSul.add(btnAlterar);
        
        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(this);
        btnRemover.setFocusable(true);   
        btnRemover.setToolTipText("btnRemvoer");
        btnRemover.setActionCommand("botao_remover");
        
        pnlSul.add(btnRemover);
        
        
        this.add(pnlSul, BorderLayout.SOUTH);
      
        format = new SimpleDateFormat("dd/MM/yyyy");
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    
        if (arg0.getActionCommand().equals(btnNovo.getActionCommand())) {
            
            pnlAFuncionario.mostrarTela("tela_funcionario_formulario");
            pnlAFuncionario.getFormulario().setFuncionarioFormulario(null);                       
            
        } else if(arg0.getActionCommand().equals(btnAlterar.getActionCommand())) {
            
            
            int indice = tblListagem.getSelectedRow();
            if(indice > -1) {

                DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();
                Vector linha = (Vector) model.getDataVector().get(indice);
                Funcionario f = (Funcionario) linha.get(0);
                pnlAFuncionario.mostrarTela("tela_funcionario_formulario");
                pnlAFuncionario.getFormulario().setFuncionarioFormulario(f); 

            } else
                JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            
            
        } else if (arg0.getActionCommand().equals(btnRemover.getActionCommand())){
            
            
            int indice = tblListagem.getSelectedRow();
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); 
                Vector linha = (Vector) model.getDataVector().get(indice);
                Funcionario f = (Funcionario) linha.get(0); 

                try {
                    
                    pnlAFuncionario.getControle().getConexaoJDBC().remover(f);
                    JOptionPane.showMessageDialog(this, "Funcionario removido!", "Funcionario", JOptionPane.INFORMATION_MESSAGE);
                    populaTabela();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Funcionario -:"+ex.getLocalizedMessage(), "Funcionario", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }                        

            } else
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            
        }
    
    }
    
}
