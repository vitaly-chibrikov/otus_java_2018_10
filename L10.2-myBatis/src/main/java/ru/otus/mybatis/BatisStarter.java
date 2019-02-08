package ru.otus.mybatis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * @author sergey
 * created on 02.10.18.
 */
public class BatisStarter {
    private static final String URL = "jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1";
    private final Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        BatisStarter demo = new BatisStarter();
        demo.createTable();
        demo.insertRecords();
        new BatisDemo().start();
    }

    private BatisStarter() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
    }

    private void createTable() throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("create table test(id int, name varchar(50))")) {
            pst.executeUpdate();
        }
    }

    private void insertRecords() throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("insert into test(id, name) values (?, ?)")) {
            Savepoint savePoint = this.connection.setSavepoint("savePointName");
            try {
                int rowCount = 0;
                for (int idx = 0; idx < 100; idx++) {
                    pst.setInt(1, idx);
                    pst.setString(2, "NameValue_" + idx);
                    rowCount += pst.executeUpdate();
                }
                this.connection.commit();
                System.out.println("inserted rowCount:" + rowCount);
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                System.out.println(ex.getMessage());
            }
        }
    }
}
