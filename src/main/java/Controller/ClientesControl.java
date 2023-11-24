package Controller;

import java.util.List;
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

    public void cadastrar(String nome, String cpf, String telefone, String email, String endereco) {
        new ClientesDAO().cadastrar(nome, cpf, telefone, email, endereco);
        atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
    }

    public void atualizar(String nome, String cpf, String telefone, String email, String endereco) {
        new ClientesDAO().atualizar(nome, cpf, telefone, email, endereco);
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    public void apagar(String cpf) {
        new ClientesDAO().apagar(cpf);
        // Atualiza a tabela após a exclusão
        atualizarTabela();
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

        // Limpa todas as linhas existentes na tabela
        tableModel.setRowCount(0);

        // Adiciona os clientes à tabela
        for (Clientes cliente : clientes) {
            tableModel.addRow(new Object[] { cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail(), cliente.getEndereco()});
        }
    }
}
