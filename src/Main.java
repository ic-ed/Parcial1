import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap <String, Short> shoesInformation =  new HashMap <>();
        HashMap <String, Short> shoesStartingInventory =  new HashMap <>();
        /* Yo hice mitad del programa antes de mirar que deberia dupicar el inventario inicial, y me dio pereza de cambiar casi 100% del codigo
           Cambiando el hashmap original y haciendo que tenga una array ahi, entonces hice un hashmap nuevo clon de shoesInformation :)
           Si funciona y no explota, es buena práctica.*/
        String shoeName;
        short shoeAmount;
        short shoeAmountToAdd;
        short sellAmount;
        byte choice;

        //*////////////////////////////////////////////////////////////////////*//

        do {

            System.out.println("-------------- MENÚ --------------");
            System.out.println("1. Agregar zapato");
            System.out.println("2. Ver stock disponible de X producto");
            System.out.println("3. Realizar una venta");
            System.out.println("4. Agregar stock a X producto");
            System.out.println("5. Información de inventario");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            choice = scanner.nextByte();
            System.out.println("\n----------------------------------");
            System.out.println("Opción Elegida: " + choice);

            //*////////////////////////////////////////////////////////////////////*//

            switch (choice) {
                case 1:
                    System.out.println("-------------- Agregar zapato --------------");
                    scanner.nextLine(); // Exorcizando el maldito \n porque se iba esa vayna
                    System.out.print("Digite el nombre del zapato: ");
                    shoeName = scanner.nextLine().toLowerCase(); // Hacendo el input lowercase para la conveniencia
                    System.out.print("Cantidad disponible en stock (Solo números): ");
                    shoeAmount = scanner.nextShort();
                    shoesInformation.put(shoeName, shoeAmount);
                    shoesStartingInventory.put(shoeName, shoeAmount);
                    System.out.println("Zapato " + shoeName + " agregado con éxito al inventario " +
                            "con un stock de: " + shoeAmount);
                    break;

                case 2:
                    System.out.println("-------------- Ver stock disponible de X producto --------------");
                    scanner.nextLine();
                    System.out.print("Digite el nombre del zapato: ");
                    shoeName = scanner.nextLine().toLowerCase();
                    if (shoesInformation.containsKey(shoeName)) {
                        System.out.println("La cantidad disponible en stock del zapato: " + shoeName + " es de: " +
                                shoesInformation.get(shoeName) );
                    } else {
                        System.out.println("Ese zapato no existe en inventario, vuelve al menu, y selecciona la " +
                                "opcion 1 para agregar un nuevo zapato.");
                    }
                    break;

                case 3:
                    System.out.println("-------------- Menú de Ventas --------------");
                    scanner.nextLine();
                    System.out.print("Digite el nombre del zapato que vas vender: ");
                    shoeName = scanner.nextLine().toLowerCase();
                    System.out.print("Cuanto que planeas vender: ");
                    sellAmount = scanner.nextShort();
                    if (shoesInformation.containsKey(shoeName) && shoesInformation.get(shoeName) != null
                            && shoesInformation.get(shoeName) >= sellAmount) {

                        System.out.println("Hay stock para hacer la venta. Calculando nuevo stock de: " + shoeName);
                        shoesInformation.put(shoeName, (short) (shoesInformation.get(shoeName) - sellAmount));

                        System.out.println("Nuevo stock calculado. Nuevo stock para " + shoeName + " es de: " +
                                shoesInformation.get(shoeName) );
                    } else {
                        System.out.println("""
                                No es posible hacer la venta en ese momento por una de las razones:\s
                                1. El zapato no existe en el inventario.
                                2. No hay zapatos suficiente en stock.\
                                
                                3. La cantidad de venta es mayor que la cantidad de zapatos en stock.""");
                    }
                    break;

                case 4:
                    System.out.println("-------------- Agregar Stock a un Zapato --------------");
                    scanner.nextLine();
                    System.out.print("Digite el nombre del zapato que vas agregar stock: ");
                    shoeName = scanner.nextLine().toLowerCase();
                    System.out.print("Cuanto de stock desea adicionar al inventario de " + shoeName + ": ");
                    shoeAmountToAdd = scanner.nextShort();

                    if (shoesInformation.containsKey(shoeName)) {
                        shoesInformation.put(shoeName, (short) (shoesInformation.get(shoeName) + shoeAmountToAdd));
                        System.out.println("Stock agregado! Nueva cantidad de " + shoeName + " en stock es de: " +
                                shoesInformation.get(shoeName));
                    } else {
                        System.out.println("No es posible agregar stock a ese zapato porque no existe en inventario.");
                    }
                    break;

                case 5:
                    System.out.println("-------------- Información del Stock --------------");
                    if (shoesInformation.isEmpty()) {
                        System.out.println("Ahem. No hay zapatos en inventario....");
                    } else {
                        System.out.println("Zapatos: ");
                        for (String key : shoesStartingInventory.keySet()) {
                            System.out.println(key + " - " + shoesInformation.get(key));
                        } // for end
                    } // if end
                    break;
                case 6:
                    System.out.println("-------------- Saliendo --------------");
                    break;
                default:
                    System.out.println("La opción selecionada no es valida. Intente de nuevo.");

            } // Switch end

            //*////////////////////////////////////////////////////////////////////*//
            for (String key : shoesInformation.keySet()) {
                if (shoesInformation.get(key) <= 0) { // duplica el inventario inicial que se haya vendido totalmente
                    System.out.println("El zapato " + key + " no tiene ningun stock mas en el inventario.\n" +
                            "Intentando reponer el stock...");
                    shoesInformation.put(key, (short) (shoesStartingInventory.get(key) * 2)); // ni idea del porqué, pero tuve que hacer casting
                    System.out.println("La reposición fue un éxito, la nueva cantidad de " + key + " en inventario" +
                            " es de: " +  shoesInformation.get(key));
                } // if end
            } // for end


        } while ( choice != 6 );

        scanner.close();

    } // method end
} // class end