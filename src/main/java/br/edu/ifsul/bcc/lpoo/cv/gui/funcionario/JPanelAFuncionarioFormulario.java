package br.edu.ifsul.bcc.lpoo.cv.gui.funcionario;

import br.edu.ifsul.bcc.lpoo.cv.Controle;
import br.edu.ifsul.bcc.lpoo.cv.model.Cargo;
import br.edu.ifsul.bcc.lpoo.cv.model.Funcionario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class JPanelAFuncionarioFormulario extends JPanel implements ActionListener {
    
    private JPanelAFuncionario pnlAFuncionario;
    private Controle controle;
    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;
    private JPanel pnlDadosCadastrais;    
    private JPanel pnlCentroDadosCadastrais;
    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblCPF;
    private JTextField txfCPF;
    private JLabel lblNome;
    private JTextField txfNome;
    private JLabel lblSenha;
    private JPasswordField txfSenha;
    private JLabel lblCargo;
    private JComboBox cbxCargo;
    private JLabel lblDataCadastro;
    private JTextField txfDataCadastro;
    private JLabel lblRG;
    private JTextField txfRG;
    private JLabel lblCTPS;
    private JTextField txfCTPS;
    private JLabel lblPIS;
    private JTextField txfPIS;
    private Funcionario funcionario;
    private SimpleDateFormat format;
    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;
    
    public JPanelAFuncionarioFormulario(JPanelAFuncionario pnlAFuncionario, Controle controle) {
        
        this.pnlAFuncionario = pnlAFuncionario;
        this.controle = controle;
        iniciaComponentes();
        
    }
    
    public void populaComboCargo() {   
        
        cbxCargo.removeAllItems();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxCargo.getModel();
        model.addElement("Selecione");           
        model.addElement(Cargo.ATENDENTE);
        model.addElement(Cargo.ADESTRADOR);
        model.addElement(Cargo.AUXILIAR_VETERINARIO); 
        
    }
    
    public Funcionario getFuncionariobyFormulario() {        

        if (txfCPF.getText().trim().length() == 11 && 
            txfNome.getText().trim().length() > 0 &&
            txfPIS.getText().trim().length() > 0 &&
            txfCTPS.getText().trim().length() > 0 &&
            new String(txfSenha.getPassword()).trim().length() > 3 && 
            cbxCargo.getSelectedIndex() > 0 && 
            txfRG.getText().trim().length() > 0) {

            Funcionario f = new Funcionario();
            f.setCpf(txfCPF.getText().trim());    
            f.setRg(txfRG.getText().trim());
            f.setSenha(new String(txfSenha.getPassword()).trim());
            f.setCargo((Cargo) cbxCargo.getSelectedItem());
            f.setPis(txfPIS.getText().trim());
            f.setCtps(txfCTPS.getText().trim());
            f.setNome(txfNome.getText().trim());
                
            if (funcionario != null)
                f.setDataCadastro(funcionario.getDataCadastro());
                        
            return f;
            
         }

         return null;
         
    }
    
    public void setFuncionarioFormulario(Funcionario f) {
        
        if (f == null) {
            
            txfCPF.setText("");
            txfCPF.setEditable(true);
            txfRG.setText("");
            txfSenha.setText("");
            cbxCargo.setSelectedIndex(0);
            txfNome.setText("");
            txfDataCadastro.setText("");
            txfPIS.setText("");
            txfCTPS.setText("");            
            funcionario = null;
            
        } else {
            
            funcionario = f;
            txfCPF.setEditable(false);
            txfCPF.setText(funcionario.getCpf());
            txfSenha.setText(funcionario.getSenha());
            cbxCargo.getModel().setSelectedItem(funcionario.getCargo());
            txfRG.setText(funcionario.getRg());
            txfNome.setText(funcionario.getNome()); 
            txfPIS.setText(funcionario.getPis());
            txfCTPS.setText(funcionario.getCtps());
            txfDataCadastro.setText(format.format(f.getDataCadastro().getTime()));                                 
        
        }

    }
    
    private void iniciaComponentes(){
        
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        tbpAbas = new JTabbedPane();
        this.add(tbpAbas, BorderLayout.CENTER);
        
        pnlDadosCadastrais = new JPanel();
        gridBagLayoutDadosCadastrais = new GridBagLayout();
        pnlDadosCadastrais.setLayout(gridBagLayoutDadosCadastrais);
        
        lblCPF = new JLabel("CPF:");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblCPF, posicionador);
        
        txfCPF = new JTextField(20);        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfCPF, posicionador); 
          
        lblRG = new JLabel("RG:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblRG, posicionador);
        
        txfRG = new JTextField(20);        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfRG, posicionador);
      
        lblNome = new JLabel("Nome:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblNome, posicionador);
        
        txfNome = new JTextField(30);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfNome, posicionador);
        
        lblSenha = new JLabel("Senha:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblSenha, posicionador);
        
        txfSenha = new JPasswordField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfSenha, posicionador);
                
        lblPIS = new JLabel("PIS:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblPIS, posicionador);
                
        txfPIS = new JTextField(5);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfPIS, posicionador);
            
        lblCTPS = new JLabel("CTPS:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblCTPS, posicionador);
                
        txfCTPS = new JTextField(5);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(txfCTPS, posicionador);
     
        
        lblCargo = new JLabel("Cargo:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(lblCargo, posicionador); 
                
        cbxCargo = new JComboBox();
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlDadosCadastrais.add(cbxCargo, posicionador);
                
                            
        lblDataCadastro = new JLabel("Data de Cadastro:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;
        posicionador.gridx = 0;
        pnlDadosCadastrais.add(lblDataCadastro, posicionador);  
        
        txfDataCadastro = new JTextField(20);
        txfDataCadastro.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;
        posicionador.gridx = 1;
        pnlDadosCadastrais.add(txfDataCadastro, posicionador);       
        
        tbpAbas.addTab("Dados Cadastrais", pnlDadosCadastrais);
        
        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());
        
        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);  
        btnGravar.setToolTipText("btnGravarFuncionario"); 
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_funcionario");
        
        pnlSul.add(btnGravar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    
        btnCancelar.setToolTipText("btnCancelarFuncionario");
        btnCancelar.setActionCommand("botao_cancelar_formulario_funcionario");
        
        pnlSul.add(btnCancelar);
        
        this.add(pnlSul, BorderLayout.SOUTH);
        
        format = new SimpleDateFormat("dd/MM/yyyy");
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){
            
            Funcionario f = getFuncionariobyFormulario();
            
            if (f != null){

                try {
                    
                    pnlAFuncionario.getControle().getConexaoJDBC().persist(f);
                    JOptionPane.showMessageDialog(this, "Funcionário armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
                    pnlAFuncionario.mostrarTela("tela_funcionario_listagem");

                } catch (Exception e) {
                    
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Funcionário: " + e.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    
                }

            } else
                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            
            
        } else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand()))
            pnlAFuncionario.mostrarTela("tela_funcionario_listagem");
        
    }
    
}
