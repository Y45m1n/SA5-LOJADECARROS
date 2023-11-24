package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import Controller.ClientesControl;
import Controller.ClientesDAO;
import Model.Clientes;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ClientesPainel extends JPanel {
    private JButton cadastrar, editar, apagar;
    private JTextField nomeText, cpfText, telText, emailText, enderecoText;
    private List<Clientes> clientes;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    public ClientesPainel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Componentes
        add(new JLabel("Cadastro Clientes"));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Nome"));
        nomeText = new JTextField(20);
        inputPanel.add(nomeText);

        inputPanel.add(new JLabel("CPF"));
        cpfText = new JTextField(20);
        inputPanel.add(cpfText);

        inputPanel.add(new JLabel("Telefone"));
        telText = new JTextField(20);
        inputPanel.add(telText);

        inputPanel.add(new JLabel("E-mail"));
        emailText = new JTextField(20);
        inputPanel.add(emailText);

        inputPanel.add(new JLabel("Endereço"));
        enderecoText = new JTextField(20);
        inputPanel.add(enderecoText);

        add(inputPanel);

        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        cadastrar.setBackground(Color.GREEN);
        botoes.add(editar = new JButton("Editar"));
        editar.setBackground(Color.ORANGE);
        botoes.add(apagar = new JButton("Apagar"));
        apagar.setBackground(Color.RED);
        add(botoes);

        

        // Tratamentos de eventos para os botões e a tabela

        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Nome", "CPF", "Telefone", "Email", "Endereco" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // Criar banco de dados caso nao exista
        new ClientesDAO().criaTabela();
        // Inclusao de elementos do banco na criação do painel
        atualizarTabela();

        // Tratamento de eventos na tabela
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    // Preencha os campos de texto com os dados da linha selecionada
                    nomeText.setText((String) table.getValueAt(linhaSelecionada, 0));
                    cpfText.setText((String) table.getValueAt(linhaSelecionada, 1));
                    telText.setText((String) table.getValueAt(linhaSelecionada, 2));
                    emailText.setText((String) table.getValueAt(linhaSelecionada, 3));
                    enderecoText.setText((String) table.getValueAt(linhaSelecionada, 4));
                }
            }
        });

        // Cria um objeto operacoes da classe ClientesControl para executar operações no
        // banco de dados
        ClientesControl operacoes = new ClientesControl(clientes, tableModel, table);

        // Tratamento do botão "Cadastrar"
        cadastrar.addActionListener(e -> {
            // Código para cadastrar um novo cliente
            String nome = nomeText.getText();
            String cpf = cpfText.getText();
            String telefone = telText.getText();
            String email = emailText.getText();
            String endereco = enderecoText.getText();

            // Verifica se os campos obrigatórios não estão vazios
            if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty() || endereco.isEmpty()) {
                // Exibe uma mensagem de erro ao usuário
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro de Cadastro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int cpfInt = Integer.parseInt(cpf); // Converte o cpf para inteiro
                int telefoneInt = Integer.parseInt(telefone); // Converte o telefone para inteiro

                // Chama o método cadastrar com os dados válidos
                operacoes.cadastrar(nome, Integer.toString(cpfInt), Double.toString(telefoneInt), email, endereco);

                // Atualiza a tabela após o cadastro bem-sucedido
                atualizarTabela();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "CPF e telefone devem ser números válidos.", "Erro de Cadastro",
                        JOptionPane.ERROR_MESSAGE);
            }

            // Limpar campos após o cadastro bem-sucedido
            nomeText.setText("");
            cpfText.setText("");
            telText.setText("");
            emailText.setText("");
            enderecoText.setText("");
        });

        // Tratamento do botão "Editar"
        editar.addActionListener(e -> {
            nomeText.setText("");
            cpfText.setText("");
            telText.setText("");
            emailText.setText("");
            enderecoText.setText("");
        });

        // Tratamento do botão "Apagar"
        apagar.addActionListener(e -> {
            
            // Limpa os campos após a exclusão, independentemente do sucesso ou falha
            operacoes.apagar(cpfText.getText());
            nomeText.setText("");
            cpfText.setText("");
            telText.setText("");
            emailText.setText("");
            enderecoText.setText("");
        });
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        clientes = new ClientesDAO().listarTodos();
        // Obtém os clientes atualizados do banco de dados
        for (Clientes clientes : clientes) {
            // Adiciona os dados de cada cliente como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] {
                    clientes.getNome(),
                    clientes.getCpf(),
                    clientes.getTelefone(),
                    clientes.getEmail(),
                    clientes.getEndereco(),
            });
        }
    }
}
