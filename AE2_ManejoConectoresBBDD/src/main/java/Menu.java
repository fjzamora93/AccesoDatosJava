import java.sql.SQLException;
import java.util.Scanner;
import database.DBConnection;
import DAO.CochesDAO;
import DAO.PasajeroDAO;
import model.Coche;
import model.Pasajero;


public class Menu {
    static Scanner scanner;
    static CochesDAO cochesDao;
    static PasajeroDAO pasajerosDao;
    static String option;
    static DBConnection dbConnection;

    public static void main(String[] args ) throws InterruptedException, SQLException {
        String option;
        cochesDao = new CochesDAO();
        pasajerosDao = new PasajeroDAO();
        scanner = new Scanner(System.in);


        dbConnection = new DBConnection();

        do {
            dbConnection.getConnection();
            showMenu();
            option = switchOption(scanner.next());

        } while (!option.equals("0"));
        System.out.println("Se ha seleccionado la opción: Terminar el programa");
        scanner.close();
        System.out.println("Programa terminado.");
    }


    /**Despliega el menú principal del programa*/
    private static void showMenu(){
        System.out.println("Menú de opciones:");
        System.out.println("1. Añadir nuevo coche");
        System.out.println("2. Borrar coche por id");
        System.out.println("3. Consulta coche por id");
        System.out.println("4. Listado de coches");
        System.out.println("5. Actualizar un coche");
        System.out.println("6. SUBMENÚ: Gestión de pasajeros");
        System.out.println("0. Terminar el programa");
        System.out.print("Seleccione una opción: ");
    }

    /**Despliega un submenú para gestionar los pasajeros*/
    public static void showSubmenu(){
        System.out.println("Submenú de gestión de pasajeros:");
        System.out.println("1. Añadir nuevo pasajero");
        System.out.println("2. Borrar pasajero por id");
        System.out.println("3. Consulta pasajero por id");
        System.out.println("4. Listado de todos los pasajeros");
        System.out.println("5. Añadir pasajero a coche");
        System.out.println("6. Eliminar pasajero de un coche");
        System.out.println("7. Listar pasajeros de un coche");
        System.out.print("Marque cualquier otra opción para volver al menú principal: ");
    }

    public static String switchOption(String option) throws InterruptedException, SQLException {
        int rowCount;
        switch (option) {
            case "1":
                rowCount = cochesDao.addNew(retrieveDataCar());
                System.out.println("Filas afectadas: " + rowCount);
                Thread.sleep(2000);
                break;
            case "2":
                System.out.println("Inserte el Id a borrar");
                rowCount = cochesDao.deleteById(scanner.nextInt());
                System.out.println("Filas afectadas: " + rowCount);
                Thread.sleep(2000);
                break;
            case "3":
                System.out.println("Inserte el Id a buscar");
                cochesDao.findById(scanner.nextInt());
                Thread.sleep(4000);
                break;
            case "4":
                cochesDao.findAll();
                Thread.sleep(4000);
                break;
            case "5":
                System.out.println("Inserte el Id del coche que queires remplazar");
                int idToUpdate = scanner.nextInt();
                rowCount = cochesDao.replaceOne(retrieveDataCar(), idToUpdate );
                System.out.println("Filas afectadas: " + rowCount);
                Thread.sleep(4000);
                break;
            case "6":
                showSubmenu();
                switchOptionSubmenu(scanner.next());
                break;
            default:
                System.out.println("Opción no válida. Por favor, elija una opción del 1 al 5.");
                Thread.sleep(2000);
        }
        return option;
    }


    public static String switchOptionSubmenu(String option) throws InterruptedException, SQLException {
        int rowCount;
        switch (option) {
            case "1":
                pasajerosDao.addNew(retrieveDataPassenger());
                Thread.sleep(2000);
                break;
            case "2":
                System.out.println("Inserte el Id a borrar");
                pasajerosDao.deleteById(scanner.nextInt());
                Thread.sleep(2000);
                break;
            case "3":
                System.out.println("Inserte el Id a buscar");
                pasajerosDao.findById(scanner.nextInt());
                Thread.sleep(4000);
                break;
            case "4":
                pasajerosDao.findAll();
                Thread.sleep(4000);
                break;
            case "5":
                System.out.println("5. Añadir pasajero a coche");
                pasajerosDao.addPasToCar(scanner.nextInt(), scanner.nextInt());
                break;
            case "6":
                System.out.println("6. Eliminar pasajero de un coche");
                pasajerosDao.deletePassOnCar(scanner.nextInt(), scanner.nextInt());
                break;
            case "7":
                System.out.println("7. Listar pasajeros de un coche");
                pasajerosDao.findPasOnCar(scanner.nextInt());
                break;
            default:
                System.out.println("Volviendo al menú principal");
                Thread.sleep(2000);
        }
        return option;
    }

    /**Obtiene los datos de un coche y devuelve el objeto completo*/
    public static Coche retrieveDataCar(){
        System.out.println("Inserte la matrícula");
        String matricula = scanner.next();
        System.out.println("Inserte la marca");
        String marca = scanner.next();
        System.out.println("Inserte el modelo");
        String modelo = scanner.next();
        System.out.println("Inserte la color");
        String color = scanner.next();

        return new Coche(matricula, marca, modelo, color);
    }

    /**Obtiene los datos de un pasajero y devuelve el objeto completo*/
    public static Pasajero retrieveDataPassenger(){
        System.out.println("Inserte el nombre");
        String nombre = scanner.next();
        System.out.println("Inserte la edad");
        int edad = scanner.nextInt();
        System.out.println("Inserte el peso");
        int peso = scanner.nextInt();

        return new Pasajero(nombre, edad, peso);
    }

}


