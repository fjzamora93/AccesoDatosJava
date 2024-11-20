package DAO;
import database.DBConnection;
import database.DBScheme;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PasajeroDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PasajeroDAO() {

    }

    /**Añadir nuevo pasajero*/
    public int addNew(Pasajero pasajero) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
                "INSERT INTO %s (%s, %s, %s) VALUES(?,?,?)",
                DBScheme.TAB_PAS,
                DBScheme.COL_PAS_NOMBRE, DBScheme.COL_PAS_EDAD, DBScheme.COL_PAS_PESO
                );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setInt(3, pasajero.getPeso());
        return preparedStatement.executeUpdate();
    }

    /**Borrar pasajero por id*/
    public int deleteById(int id) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                DBScheme.TAB_PAS, DBScheme.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }

    /**Consulta pasajero por id*/
    public Pasajero findById(int id) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                DBScheme.TAB_PAS, DBScheme.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Pasajero> passengerList = getResultados(resultSet);
        if (!passengerList.isEmpty()){
            passengerList.get(0).showDetails();
            return passengerList.get(0);
        } else {
            System.out.println("Sin coincidencias de búsqueda");
            return null;
        }

    }

    /**Listar todos los pasajeros*/
    public ArrayList<Pasajero> findAll() throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
                "SELECT * FROM %s",
                DBScheme.TAB_PAS);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Pasajero> passengerList = getResultados(resultSet);
        for (Pasajero pas:passengerList){
            pas.showDetails();
        }
        return passengerList;
    }

    /**Añadir pasajero a coche, id de un pasajero y el id de un coche, y lo añadirá al coche DB.
    //Sería una buena opción mostrar todos los coches disponibles.*/
    public int addPasToCar(int idPas, int idCoche) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
                "INSERT INTO %s (%s, %s) VALUES (?, ?)",
                DBScheme.TAB_COCHE_PAS,
                DBScheme.COL_PAS_ID,
                DBScheme.COL_COCHE_ID );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idPas);
        preparedStatement.setInt(2, idCoche);
        return preparedStatement.executeUpdate();
    }


    /**Eliminar pasajero de coche, pedirá un id pasajero y el id coche, y lo eliminará del coche en base de datos.
    // Sería una buena opción mostrar todos los coches y sus pasajeros asociados.*/
    public int deletePassOnCar(int idPas, int idCoche) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?",
                DBScheme.TAB_COCHE_PAS,
                DBScheme.COL_PAS_ID,
                DBScheme.COL_COCHE_ID );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idPas);
        preparedStatement.setInt(2, idCoche);
        int rowCount = preparedStatement.executeUpdate();
        return rowCount;
    }

    /**MOSTRAR TODOS LOS COCHES Y SUS PASAJEROS ASOCIADOS*/
    public void displayPassengersPerCar() throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
                "SELECT * FROM %s ",
                DBScheme.TAB_COCHE_PAS
        );
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int idPas = resultSet.getInt(DBScheme.COL_PAS_ID);
            int idCoche = resultSet.getInt(DBScheme.COL_COCHE_ID);
            String pasajeroEnCoche = "Pasajero con id: " + idPas + " está en el coche con id: " + idCoche;
            System.out.println(pasajeroEnCoche);
        }
}
 /**Listar todos los pasajeros de un coche, el programa pedirá el id de un coche, y mostrará todos los pasajeros de él.*/
    public ArrayList<Pasajero> findPasOnCar(int idCoche) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format(
                "SELECT * FROM %s WHERE %s IN (SELECT %s FROM %s WHERE %s = ?)",
                DBScheme.TAB_PAS,
                DBScheme.COL_ID,
                DBScheme.COL_PAS_ID,
                DBScheme.TAB_COCHE_PAS,
                DBScheme.COL_COCHE_ID
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);
        resultSet = preparedStatement.executeQuery();

        return getResultados(resultSet);

    }

    private ArrayList<Pasajero> getResultados(ResultSet datosResultantes) throws SQLException {
        ArrayList<Pasajero> listaResultado = new ArrayList<>();
        while (datosResultantes.next()){
            String nombre = resultSet.getString(DBScheme.COL_PAS_NOMBRE);
            int edad = resultSet.getInt(DBScheme.COL_PAS_EDAD);
            int peso = resultSet.getInt(DBScheme.COL_PAS_PESO);
            listaResultado.add(mapearPasajero(nombre,edad,peso));
        }
        return listaResultado;
    }

    private Pasajero mapearPasajero(String nombre, int edad, int peso){
        return new Pasajero(nombre, edad, peso);
    }

}
