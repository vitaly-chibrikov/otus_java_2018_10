package ru.otus.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * @author sergey
 * created on 01.10.18.
 */
public class H2demo {
    private static final String URL = "jdbc:h2:mem:";
    private final Connection connection;

    public static void main(String[] args) throws SQLException {
        H2demo demo = new H2demo();
        demo.createTable();
        int id = 1;
        demo.insertRecord(id);
        demo.selectRecord(id);
        demo.close();
    }

    public H2demo() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
    }

    private void createTable() throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("create table test(id int, name varchar(50))")) {
            pst.executeUpdate();
        }
    }

    private void insertRecord(int id) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("insert into test(id, name) values (?, ?)")) {
            Savepoint savePoint = this.connection.setSavepoint("savePointName");
            pst.setInt(1, id);
            pst.setString(2, "NameValue");
            try {
                int rowCount = pst.executeUpdate();
                this.connection.commit();
                System.out.println("inserted rowCount:" + rowCount);
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                System.out.println(ex.getMessage());
            }
        }
    }

    private void selectRecord(int id) throws SQLException {
        try (PreparedStatement pst = this.connection.prepareStatement("select name from test where id  = ?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                System.out.print("name:");
                if (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }
        }
    }

    public void close() throws SQLException {
        this.connection.close();
    }

}
