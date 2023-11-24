package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.Carros;

public class CarrosControl {
    // Atributos
    private List<Carros> carros;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor
    public CarrosControl(List<Carros> carros, DefaultTableModel tableModel, JTable table) {
        this.carros = carros;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        carros = new CarrosDAO().listarTodos();
        // Obtém os carros atualizados do banco de dados
        for (Carros carro : carros) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { carro.getMarca(), carro.getModelo(),
                    carro.getAno(), carro.getPreco(), carro.getCor(), carro.getPlaca() });
        }
    }
 // Método para ação concluída
 private void acaoFeita(String mensagem) {
    JOptionPane.showMessageDialog(null, mensagem, "Ação Concluída", JOptionPane.INFORMATION_MESSAGE);
}

    // Método para validar o ano como numero
    private boolean verificarAno(String ano) {
        try {
            Integer.parseInt(ano);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para validar o valor como numero
    private boolean verificarPreco(String preco) {
        try {
            Double.parseDouble(preco);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    // metodo para verificar se a cor esta completada como valor alfabetico
    private boolean verificarCor(String cor) {
        return cor.chars().noneMatch(Character::isDigit);
    }

 // Método para cadastrar um novo carro no banco de dados
    public void cadastrar(String marca, String modelo, String ano, String preco, String cor, String placa) {
        if (!verificarAno(ano) || !verificarPreco(preco) || !verificarCor(cor)) {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new CarrosDAO().cadastrar(marca, modelo, ano, preco, cor, placa);

        // Chama o método de cadastro no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após o cadastro

        acaoFeita("Carro cadastrado!");
    }

   // Método para atualizar os dados de um carro no banco de dados
   public void atualizar(String marca, String modelo, String ano, String preco, String cor, String placa) {
    int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente atualizar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
    if (resposta == JOptionPane.YES_OPTION) {
          if (!verificarAno(ano) || !verificarPreco(preco) || !verificarCor(cor)) {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new CarrosDAO().atualizar(marca, modelo, ano, preco, cor, placa);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização

        acaoFeita("Cadastro atualizado com sucesso!");
    }
}
    
 // Método para apagar um carro do banco de dados
 public void apagar(String placa) {
    int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
    if (resposta == JOptionPane.YES_OPTION) {
        new CarrosDAO().apagar(placa);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão

        acaoFeita("Cadastro apagado com sucesso!");
    }
}
   
}
