package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Clientes;

public class ClientesControl {

    private List<Clientes> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    public ClientesControl(List<Clientes> clientes, DefaultTableModel tableModel, JTable table) {
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;
    }

    private void atualizarTabela() {

        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        clientes = new ClientesDAO().listarTodos();
        // Obtém os clientes atualizados do banco de dados
        for (Clientes clientes : clientes) {
            // Adiciona os dados de cada clientes como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { clientes.getNome(), clientes.getCpf(),
                    clientes.getTelefone(), clientes.getEmail(), clientes.getEndereco() });
        }
    }
    // Método para ação concluída
 private void acaoFeita(String mensagem) {
    JOptionPane.showMessageDialog(null, mensagem, "Ação Concluída", JOptionPane.INFORMATION_MESSAGE);
}
// Método para validar o telefone como numero
private boolean verificarTel(String telefone) {
    try {
        Integer.parseInt(telefone);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
// Método para validar o cpf como numero
private boolean verificarCPF(String cpf) {
    try {
        Integer.parseInt(cpf);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
// metodo para verificar se o nome esta completada como valor alfabetico
private boolean verificarNome(String nome) {
    return nome.chars().noneMatch(Character::isDigit);
}

// Método para cadastrar um novo carro no banco de dados
public void cadastrar(String nome, String cpf, String telefone, String email, String endereco) {
    if (!verificarCPF(cpf) || !verificarTel(telefone) || !verificarNome(nome)) {
        JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        return;
    }

    new ClientesDAO().cadastrar(nome, cpf, telefone, email, endereco);

    // Chama o método de cadastro no banco de dados
    atualizarTabela(); // Atualiza a tabela de exibição após o cadastro

    acaoFeita("Cliente cadastrado!");
}
// Método para atualizar os dados de um carro no banco de dados
public void atualizar(String nome, String cpf, String telefone, String email, String endereco) {
    int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente atualizar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
    if (!verificarCPF(cpf) || !verificarTel(telefone) || !verificarNome(nome)) {
        JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        return;
    }

        new ClientesDAO().cadastrar(nome, cpf, telefone, email, endereco);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização

        acaoFeita("Cadastro atualizado com sucesso!");
    }
    // Método para apagar um carro do banco de dados
 public void apagar(String cpf) {
    int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
    if (resposta == JOptionPane.YES_OPTION) {
        new ClientesDAO().apagar(cpf);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão

        acaoFeita("Cadastro apagado com sucesso!");
    }
}
}


