package View;

import java.util.List;
import java.util.Locale;

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

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class CarrosPainel extends JPanel {
    // Atributos(componentes)
    private JButton cadastrar, apagar, editar;
    private JTextField carMarcaField, carModeloField, carAnoField, carPrecoField, carCorField, carPlacaField;
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
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Marca"));
        carMarcaField = new JTextField(20);
        inputPanel.add(carMarcaField);
        inputPanel.add(new JLabel("Modelo"));
        carModeloField = new JTextField(20);
        inputPanel.add(carModeloField);
        inputPanel.add(new JLabel("Ano"));
        carAnoField = new JTextField(20);
        inputPanel.add(carAnoField);
         inputPanel.add(new JLabel("Preço"));
        carPrecoField = new JTextField(20);
        inputPanel.add(carPrecoField);
         inputPanel.add(new JLabel("Cor"));
        carCorField = new JTextField(20);
        inputPanel.add(carCorField);
        inputPanel.add(new JLabel("Placa"));
        carPlacaField = new JTextField(20);
        inputPanel.add(carPlacaField);
        add(inputPanel);
        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(editar = new JButton("Editar"));
        botoes.add(apagar = new JButton("Apagar"));
        add(botoes);

        // Dicas de uso
        cadastrar.setToolTipText("Cadastrar novo carro");
        editar.setToolTipText("Editar registro selecionado");
        apagar.setToolTipText("Apagar registro selecionado");

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
                    carMarcaField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    carModeloField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    carAnoField.setText((String) table.getValueAt(linhaSelecionada, 2));
                     carPrecoField.setText((String) table.getValueAt(linhaSelecionada, 3));
                      carCorField.setText((String) table.getValueAt(linhaSelecionada, 4));
                    carPlacaField.setText((String) table.getValueAt(linhaSelecionada, 5));
                   
                }
            }
        });

        // Cria um objeto operacoes da classe CarrosControl para executar operações no
        // banco de dados
        CarrosControl operacoes = new CarrosControl(carros, tableModel, table);

        // Configura a ação do botão "cadastrar" para adicionar um novo registro no
        // banco de dados

        cadastrar.addActionListener(e -> {
            String marca = carMarcaField.getText();
            String modelo = carModeloField.getText();
            String ano = carAnoField.getText();
             String preco = carPrecoField.getText();
              String cor = carCorField.getText();
            String placa = carPlacaField.getText();
           
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
                carMarcaField.setText("");
                carModeloField.setText("");
                carAnoField.setText("");
                carPrecoField.setText("");
                carCorField.setText("");
                carPlacaField.setText("");
            } catch (NumberFormatException ex) {
                // Exibe uma mensagem de erro ao usuário se houver problemas na conversão
                JOptionPane.showMessageDialog(this, "Ano e Valor devem ser números válidos.", "Erro de Cadastro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tratamento do botao editar
        editar.addActionListener(e -> {
            operacoes.atualizar(carMarcaField.getText(), carModeloField.getText(), carAnoField.getText(), carPrecoField.getText(), carCorField.getText(), carPlacaField.getText());

            // Limpa os campos após a atualização, independentemente do sucesso ou falha
            carMarcaField.setText("");
            carModeloField.setText("");
            carAnoField.setText("");
             carPrecoField.setText("");
              carCorField.setText("");
            carPlacaField.setText("");
           
        });

        // Tratamento do botao apagar
        apagar.addActionListener(e -> {
            operacoes.apagar(carPlacaField.getText());

            // Limpa os campos após a exclusão, independentemente do sucesso ou falha
            carMarcaField.setText("");
            carModeloField.setText("");
            carAnoField.setText("");
             carPrecoField.setText("");
              carCorField.setText("");
            carPlacaField.setText("");
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