package View;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import Controller.CarrosControl;
import Controller.CarrosDAO;
import Model.Carros;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CarrosPainel extends JPanel {
    // Atributos(componentes)
    private JButton cadastrar, apagar, editar;
    private JTextField marcaText, modeloText, anoText, precoText, corText, placaText;
    private List<Carros> carros;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor(GUI-JPanel)
    public CarrosPainel() {
        super();

        // entrada de dados
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Cadastro Carros"));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Marca"));
        marcaText = new JTextField(20);
        inputPanel.add(marcaText);
        inputPanel.add(new JLabel("Modelo"));
        modeloText = new JTextField(20);
        inputPanel.add(modeloText);
        inputPanel.add(new JLabel("Ano"));
        anoText = new JTextField(20);
        inputPanel.add(anoText);
         inputPanel.add(new JLabel("Preço"));
        precoText = new JTextField(20);
        inputPanel.add(precoText);
         inputPanel.add(new JLabel("Cor"));
        corText = new JTextField(20);
        inputPanel.add(corText);
        inputPanel.add(new JLabel("Placa"));
        placaText = new JTextField(20);
        inputPanel.add(placaText);
        add(inputPanel);
        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        cadastrar.setBackground(Color.GREEN);
        botoes.add(editar = new JButton("Editar"));
        editar.setBackground(Color.ORANGE);
        botoes.add(apagar = new JButton("Apagar"));
        apagar.setBackground(Color.RED);
        add(botoes);


        // tabela de carros
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Marca", "Modelo", "Ano", "Preço", "Cor","Placa"});
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // Cria o banco de dados caso não tenha sido criado
        new CarrosDAO().criaTabela();
        // incluindo elementos do banco na criação do painel
        atualizarTabela();

        // tratamento de Eventos
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    marcaText.setText((String) table.getValueAt(linhaSelecionada, 0));
                    modeloText.setText((String) table.getValueAt(linhaSelecionada, 1));
                    anoText.setText((String) table.getValueAt(linhaSelecionada, 2));
                     precoText.setText((String) table.getValueAt(linhaSelecionada, 3));
                      corText.setText((String) table.getValueAt(linhaSelecionada, 4));
                    placaText.setText((String) table.getValueAt(linhaSelecionada, 5));
                   
                }
            }
        });

        // Cria um objeto operacoes da classe CarrosControl para executar operações no
        // banco de dados
        CarrosControl operacoes = new CarrosControl(carros, tableModel, table);

        // Configura a ação do botão "cadastrar" para adicionar um novo registro no
        // banco de dados

        cadastrar.addActionListener(e -> {
            String marca = marcaText.getText();
            String modelo = modeloText.getText();
            String ano = anoText.getText();
             String preco = precoText.getText();
              String cor = corText.getText();
            String placa = placaText.getText();
           
            // Verifica se os campos obrigatórios não estão vazios
            if (marca.isEmpty() || modelo.isEmpty() || ano.isEmpty() || preco.isEmpty() || cor.isEmpty()|| placa.isEmpty()) {
                // Exibe uma mensagem de erro ao usuário
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro de Cadastro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {

                // Agora você pode chamar o método cadastrar com os dados válidos
                operacoes.cadastrar(marca, modelo, ano, preco, cor, placa);

                // Limpa os campos após o cadastro bem-sucedido
                marcaText.setText("");
                modeloText.setText("");
                anoText.setText("");
                precoText.setText("");
                corText.setText("");
                placaText.setText("");
            } catch (NumberFormatException ex) {
                // Exibe uma mensagem de erro ao usuário se houver problemas na conversão
                JOptionPane.showMessageDialog(this, "Ano e Valor devem ser números válidos.", "Erro de Cadastro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tratamento do botao editar
        editar.addActionListener(e -> {
            operacoes.atualizar(marcaText.getText(), modeloText.getText(), anoText.getText(), precoText.getText(), corText.getText(), placaText.getText());

            // Limpa os campos após a atualização, independentemente do sucesso ou falha
            marcaText.setText("");
            modeloText.setText("");
            anoText.setText("");
             precoText.setText("");
              corText.setText("");
            placaText.setText("");
           
        });

        // Tratamento do botao apagar
        apagar.addActionListener(e -> {
            operacoes.apagar(placaText.getText());

            // Limpa os campos após a exclusão, independentemente do sucesso ou falha
            marcaText.setText("");
            modeloText.setText("");
            anoText.setText("");
             precoText.setText("");
              corText.setText("");
            placaText.setText("");
        });
    }

    // Metodos (Atualizar tabela)
    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        carros = new CarrosDAO().listarTodos();
        // Obtém os carros atualizados do banco de dados
       
        for (Carros carro : carros) {

            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] {
                carro.getMarca(), 
                carro.getModelo(),
                carro.getAno(), 
                carro.getPreco(), 
                carro.getCor(), 
                carro.getPlaca() 
                   
            });
        }
    }

}