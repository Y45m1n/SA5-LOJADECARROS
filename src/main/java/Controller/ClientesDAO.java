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

    private Connection connection;
    private List<Clientes> clientes;

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

    public List<Clientes> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clientes = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM clientes");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Clientes cliente = new Clientes(
                    rs.getString("nome"), 
                    rs.getString("cpf"), 
                    rs.getString("telefone"), 
                    rs.getString("email"), 
                    rs.getString("endereco")
                );
                clientes.add(cliente); 
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return clientes; 
    }

    public void cadastrar(String nome, String cpf, String telefone, String email, String endereco) {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setString(5, endereco);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar dados no banco de dados.", e);
        } 
    }

    public void atualizar(String nome, String cpf, String telefone, String email, String endereco) {
        String sql = "UPDATE clientes SET nome=?, telefone=?, email=?, endereco=? WHERE cpf=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, email);
            stmt.setString(4, endereco);
            stmt.setString(5, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } 
    }

    public void apagar(String cpf) {
        String sql = "DELETE FROM clientes WHERE cpf=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } 
    }
}