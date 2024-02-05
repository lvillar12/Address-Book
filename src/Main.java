import java.io.*;
import java.util.*;

class Main {
    private final Map<String, String> contacts;

    public Main() {
        contacts = new HashMap<>();
    }

    public void load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                contacts.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    public void save(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " , " + entry.getValue());
        }
    }

    public void create(String number, String name) {
        contacts.put(number, name);
    }

    public void delete(String number) {
        contacts.remove(number);
    }

    public void interactiveMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Guardar cambios");
            System.out.println("5. Cargar contactos");
            System.out.println("6. Salir");
            System.out.print("Ingrese su opción: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    list();
                    break;
                case 2:
                    System.out.print("Ingrese el número de teléfono: ");
                    String number = scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String name = scanner.nextLine();
                    create(number, name);
                    break;
                case 3:
                    System.out.print("Ingrese el número de teléfono del contacto a borrar: ");
                    String numberToDelete = scanner.nextLine();
                    delete(numberToDelete);
                    break;
                case 4:
                    save("contacts.txt");
                    break;
                case 5:
                    load("contacts.txt");
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (choice != 6);
        scanner.close();
    }

    public static void main(String[] args) {
        Main addressBook = new Main();
        addressBook.load("contacts.txt");
        addressBook.interactiveMenu();
    }
}