import java.util.Scanner;
import model.Coche;


public class Menu {
    public static void main(String[] args ) throws InterruptedException {

        GestorCoches concesionario = new GestorCoches();

        Scanner scanner = new Scanner(System.in);
        String opcion;

        do {
            // Mostrar el menú de opciones
            System.out.println("Menú de opciones:");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por id");
            System.out.println("3. Consulta coche por id");
            System.out.println("4. Listado de coches");
            System.out.println("5. Convertir a CSV");
            System.out.println("6. Terminar el programa");
            System.out.print("Seleccione una opción: ");

            // Leer la opción seleccionada por el usuario
            opcion = scanner.next();

            // Manejar las opciones seleccionadas
            switch (opcion) {
                case "1":
                    System.out.println("Inserte la matrícula");
                    String matricula = scanner.next();

                    System.out.println("Inserte la marca");
                    String marca = scanner.next();

                    System.out.println("Inserte el modelo");
                    String modelo = scanner.next();

                    System.out.println("Inserte la color");
                    String color = scanner.next();

                    Coche coche = new Coche(matricula, marca, modelo, color);
                    concesionario.addNew(coche);


                    Thread.sleep(2000);
                    break;
                case "2":
                    System.out.println("Inserte el Id a borrar");
                    concesionario.deleteById(scanner.nextInt());
                    Thread.sleep(2000);
                    break;
                case "3":
                    System.out.println("Inserte el Id a buscar");
                    concesionario.findById(scanner.nextInt());
                    Thread.sleep(4000);
                    break;
                case "4":
                    concesionario.findAll();
                    Thread.sleep(4000);
                    break;
                case "5":
                    concesionario.toCsv();
                    Thread.sleep(2000);
                    break;
                case "6":
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del 1 al 6.");
                    Thread.sleep(2000);
            }
        } while (!opcion.equals("6"));
        System.out.println("Se ha seleccionado la opción: Terminar el programa");
        concesionario.overWriteFile();
        scanner.close();
        System.out.println("Programa terminado.");
    }
}


