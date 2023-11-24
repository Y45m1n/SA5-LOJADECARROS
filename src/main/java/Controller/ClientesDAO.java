package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Clientes;

public class ClientesDAO {

    // atributo
    private Connection connection;
    private List<Clientes> clientes;

    // Construtor
    public ClientesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes (NOME VARCHAR(255),CPF VARCHAR(255) PRIMARY KEY, TELEFONE VARCHAR(255),EMAIL VARCHAR(255), ENDERECO VARCHAR(255))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public void cadastrar(String nome, String cpf, String telefone, String email, String endereco) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO clientes (nome, cpf, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setString(5, endereco);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void atualizar(String nome, String cpf, String telefone, String email, String endereco) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa
        String sql = "UPDATE clientes SET nome=?, WHERE cpf=?, telefone=?, email=?, endereco=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setString(5, endereco);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void apagar(String cpf) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM clientes WHERE cpf=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public List<Clientes> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        clientes = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM clientes");
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String endereco = resultSet.getString("endereco");
                clientes.add(new Clientes(nome, cpf, telefone, email, endereco));
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, resultSet);
        }
        return clientes; // Retorna a lista de clientes recuperados do banco de dados
    }
}
