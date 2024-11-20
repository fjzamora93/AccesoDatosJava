package database;

import java.sql.*;

public class DBConnection {

    private static Connection connection;

    public void createConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%s/%s", DBScheme.HOST, DBScheme.PORT, DBScheme.DATABASE);
        connection = DriverManager.getConnection(url, DBScheme.USER, DBScheme.PASSWORD);
        System.out.println("Conexión establecida correctamente.");
        listDatabases();
    }


    public Connection getConnection() {
        if (connection == null) {
            try {
                System.out.println("Estableciendo conexión");
                createConnection();
            } catch (SQLException e) {
                System.out.println("Error al establecer conexión");
                throw new RuntimeException(e);
            }
        }
        return connection;
    }


    public void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Método para listar las bases de datos disponibles
    private void listDatabases() throws SQLException {
        String query = "SHOW DATABASES";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet =  preparedStatement.executeQuery();
        System.out.println("Bases de datos disponibles:");
        while (resultSet.next()){
            System.out.println("- " + resultSet.getString(1));
        }

    }

}
