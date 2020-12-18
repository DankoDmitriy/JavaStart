package JavaStart;

import JavaStart.bl.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionService extends Util implements PositionDao {
    private Connection connection = getConnection();

    @Override
    public Position add(Position position) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO POSITION (STATUS, POSITION_NAME, SUBORDINATION_LEVEL, DESCRIPTION, ID_DEPARTMENT) VALUES (?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setBoolean(1, position.isStatus());
            preparedStatement.setString(2, position.getPositionName());
            preparedStatement.setLong(3, position.getSubordinationLevel());
            preparedStatement.setString(4, position.getDescription());
            preparedStatement.setLong(5, position.getDepartment().getId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            position.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return position;
    }

    @Override
    public List<Position> getAll() {
        Statement statement = null;
        List<Position> list = new ArrayList<>();
        String sql = "SELECT ID, STATUS, POSITION_NAME, SUBORDINATION_LEVEL, DESCRIPTION, ID_DEPARTMENT FROM POSITION";

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getLong("ID"));
                position.setDescription(resultSet.getString("DESCRIPTION"));
                position.setStatus(resultSet.getBoolean("STATUS"));
                position.setPositionName(resultSet.getString("POSITION_NAME"));
                DepartmentService departmentService = new DepartmentService();
                position.setDepartment(departmentService.getById(resultSet.getLong("ID_DEPARTMENT")));
                list.add(position);
            }
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Position getById(Long id) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, STATUS, POSITION_NAME, SUBORDINATION_LEVEL, DESCRIPTION, ID_DEPARTMENT FROM POSITION WHERE ID=?";
        Position position = new Position();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                position.setStatus(resultSet.getBoolean("STATUS"));
                position.setPositionName(resultSet.getString("POSITION_NAME"));
                position.setDescription(resultSet.getString("DESCRIPTION"));
                position.setId(resultSet.getLong("ID"));
                position.setSubordinationLevel(resultSet.getLong("SUBORDINATION_LEVEL"));
                DepartmentService departmentService = new DepartmentService();
                position.setDepartment(departmentService.getById(position.getId()));
            } else {
                System.out.println("Not found data in table POSITION");
                System.out.println(sql + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return position;
    }

    @Override
    public Position update(Position position) {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE POSITION SET STATUS=?, POSITION_NAME=?, SUBORDINATION_LEVEL=?, DESCRIPTION=?, ID_DEPARTMENT=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, position.isStatus());
            preparedStatement.setString(2, position.getPositionName());
            preparedStatement.setLong(3, position.getSubordinationLevel());
            preparedStatement.setString(4, position.getDescription());
            preparedStatement.setLong(5, position.getDepartment().getId());
            preparedStatement.setLong(6, position.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return position;
    }

    @Override
    public void remove(Position position) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM POSITION WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, position.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM POSITION WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
