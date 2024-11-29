import database.DbConnection;

import java.sql.SQLException;
import database.DbConnection;
import database.DbScheme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Usuario;
import dao.UsuarioDAO;


/**
 *
 * Crea una aplicación que permita gestionar usuarios tanto en ficheros como en base de datos.
 * Para ello la aplicación tendrá la siguiente funcionalidad mediante un menú.

- Registrar usuarios: pedirá los datos del usuario a guardar en la base de datos.
 Los datos que pedirá son: nombre, apellido, correo y pass. No podrá haber dos usuarios con el mismo correo en la base de datos.

 - Listar usuarios: mostrará id y nombre de todos los usuarios de la base de datos

 - Comprobar credenciales: pedirá correo y contraseña de un usuario y mostrará el mensaje de login correcto o login incorrecto

 - Exportar usuarios: creará un fichero llamado usuarios.obj con todos los usuarios de la base de datos

Para subir el proyecto, crea un .zip con la carpeta del proyecto completa
*/
public class MainMenu {
    static Scanner scanner;
    static String input;
    static Connection connection;
    static UsuarioDAO usuarioDAO;

    public static void main (String[] args) throws SQLException {
        connection = new DbConnection().getConnection();
        scanner = new Scanner(System.in);
        usuarioDAO = new UsuarioDAO();
        input = "";

        while (!input.equals("exit")){
            System.out.println("Examen de ACCESO A DATOS");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Comprobar credenciales ");
            System.out.println("4. Exportar usuarios a obj ");

            System.out.println("Introduce una opcion");
            input = scanner.nextLine();

            switch (input){
                case "1":
                    System.out.println("Filas modificadas: ");
                    System.out.println(retrieveData());
                    break;
                case "2":
                    System.out.println(usuarioDAO.findAll());
                    break;
                case "3":
                    System.out.println(login());
                    break;
                case "4":
                    usuarioDAO.exportToObj();
                    break;
                default:
                    System.out.println("Opcion no válida");

            }
        }
    }


    public static int retrieveData() throws SQLException {
        System.out.println("Inserte nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Inserte apellido: ");
        String apellido = scanner.nextLine();
        System.out.println("Inserte correo: ");
        String correo = scanner.nextLine();
        System.out.println("Inserte contraseña: ");
        String contraseña = scanner.nextLine();

        return usuarioDAO.addNew(new Usuario(nombre, apellido, correo, contraseña));
    }


    public static Usuario login() throws SQLException {
        System.out.println("Inserte correo: ");
        String correo = scanner.nextLine();

        System.out.println("Inserte contraseña: ");
        String contraseña = scanner.nextLine();

        return usuarioDAO.checkCredentials(correo, contraseña);
    }




}
