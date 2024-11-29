import database.DbConnection;

import java.sql.SQLException;
import database.DbConnection;
import database.DbScheme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainMenu {
    public static void main (String[] args) throws SQLException {
        System.out.println("Examen de ACCESO A DATOS");
        Connection connection = new DbConnection().getConnection();
    }
}
