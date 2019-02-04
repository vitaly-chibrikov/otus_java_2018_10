package ru.otus.executor;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbExecutorImpl<T> implements DbExecutor<T> {

    private final Connection connection;

    public DbExecutorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insertRecord(String sql, List<String> params) throws SQLException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for(int idx = 0; idx < params.size(); idx++) {
                pst.setString(idx + 1, params.get(idx));
            }
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            this.connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Optional<T> selectRecord(String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException {
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return Optional.ofNullable(rsHandler.apply(rs));
            }
        }
    }
}
