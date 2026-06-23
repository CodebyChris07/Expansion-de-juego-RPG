/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Problema1_juegoDeRoles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   BIENVENIDO AL SISTEMA DE COMBATE RPG  ");
        System.out.println("=========================================");

        // ---------------------------------------------------------
        // 1. REGISTRO DEL JUGADOR 1 
        // ---------------------------------------------------------
        System.out.print("\nIngrese el nombre del Jugador 1: ");
        String nombreU1 = entrada.nextLine();
        Problema1_Usuario pablo = new Problema1_Usuario(nombreU1, "U001");

        System.out.print("¿Cuántos personajes deseas crear para tu equipo? (1 a 5): ");
        int cant1 = entrada.nextInt();
        entrada.nextLine(); // Limpiar el buffer

        // Validación básica por si el usuario pone más de 5 o menos de 1
        if (cant1 > 5) {
            cant1 = 5;
        }
        if (cant1 < 1) {
            cant1 = 1;
        }

        System.out.println("Armando equipo para " + pablo.getNombreUsuario() + "...");

        for (int i = 1; i <= cant1; i++) {
            System.out.println("\nPersonaje " + i + " de " + cant1 + ":");
            System.out.println("1. Guerrero");
            System.out.println("2. Mago");
            System.out.println("3. Arquero");
            System.out.print("Elija la clase (1-3): ");
            int opcion = entrada.nextInt();
            entrada.nextLine(); // Limpieza de buffer

            System.out.print("Nombre del personaje: ");
            String nombrePer = entrada.nextLine();
            String idPer = "P1-" + i;

            if (opcion == 1) {
                Problema1_Guerrero g = new Problema1_Guerrero("Espada", 20, 15, idPer, nombrePer);
                pablo.agregarPersonaje(g);
            } else if (opcion == 2) {
                System.out.print("Elemento (Fuego/Agua/Tierra/Aire): ");
                String elemento = entrada.nextLine();
                Problema1_Mago m = new Problema1_Mago(50, elemento, "Bastón", 12, 18, idPer, nombrePer);
                pablo.agregarPersonaje(m);
            } else if (opcion == 3) {
                Problema1_Arquero a = new Problema1_Arquero(20, 15, "Arco", 14, 20, idPer, nombrePer);
                pablo.agregarPersonaje(a);
            } else {
                System.out.println("Opción no válida. Se asigna un Guerrero por defecto.");
                Problema1_Guerrero g = new Problema1_Guerrero("Espada", 20, 15, idPer, nombrePer);
                pablo.agregarPersonaje(g);
            }
        }

        // ---------------------------------------------------------
        // 2. REGISTRO DEL JUGADOR 2 (Dinámico)
        // ---------------------------------------------------------
        System.out.print("\nIngrese el nombre del Jugador 2: ");
        String nombreU2 = entrada.nextLine();
        Problema1_Usuario ana = new Problema1_Usuario(nombreU2, "U002");

        System.out.print("¿Cuántos personajes deseas crear para tu equipo? (1 a 5): ");
        int cant2 = entrada.nextInt();
        entrada.nextLine(); // Limpieza de buffer

        if (cant2 > 5) {
            cant2 = 5;
        }
        if (cant2 < 1) {
            cant2 = 1;
        }

        System.out.println("Armando equipo para " + ana.getNombreUsuario() + "...");

        for (int i = 1; i <= cant2; i++) {
            System.out.println("\nPersonaje " + i + " de " + cant2 + ":");
            System.out.println("1. Guerrero");
            System.out.println("2. Mago");
            System.out.println("3. Arquero");
            System.out.print("Elija la clase (1-3): ");
            int opcion = entrada.nextInt();
            entrada.nextLine(); // Limpiar el buffer

            System.out.print("Nombre del personaje: ");
            String nombrePer = entrada.nextLine();
            String idPer = "P2-" + i;

            if (opcion == 1) {
                Problema1_Guerrero g = new Problema1_Guerrero("Hacha", 18, 14, idPer, nombrePer);
                ana.agregarPersonaje(g);
            } else if (opcion == 2) {
                System.out.print("Elemento (Fuego/Agua/Tierra/Aire): ");
                String elemento = entrada.nextLine();
                Problema1_Mago m = new Problema1_Mago(40, elemento, "Vara", 10, 20, idPer, nombrePer);
                ana.agregarPersonaje(m);
            } else if (opcion == 3) {
                Problema1_Arquero a = new Problema1_Arquero(25, 12, "Ballesta", 15, 15, idPer, nombrePer);
                ana.agregarPersonaje(a);
            } else {
                System.out.println("Opción no válida. Se asigna un Guerrero por defecto.");
                Problema1_Guerrero g = new Problema1_Guerrero("Hacha", 18, 14, idPer, nombrePer);
                ana.agregarPersonaje(g);
            }
        }

        // ---------------------------------------------------------
        // 3. MOSTRAR EQUIPOS INICIALES Y COMBATIR
        // ---------------------------------------------------------
        System.out.println("\n--- EQUIPOS REGISTRADOS ---");
        pablo.mostrarPersonajes();
        ana.mostrarPersonajes();

        System.out.println("\nPresione ENTER para iniciar la Batalla Aleatoria...");
        entrada.nextLine();

        // Llamamos a la clase de combate
        Problema1_Combate.batallaAleatoria(pablo, ana);

        // ---------------------------------------------------------
        // 4. MOSTRAR ESTADÍSTICAS FINALES
        // ---------------------------------------------------------
        System.out.println("\n=========================================");
        System.out.println("   ESTADÍSTICAS FINALES POST-BATALLA     ");
        System.out.println("=========================================");
        pablo.mostrarPersonajes();
        ana.mostrarPersonajes();

        // ---------------------------------------------------------
        // 5. GUARDAR DATOS 
        // ---------------------------------------------------------
        System.out.println("\nGuardando resultados en usuarios.dat...");
        Problema1_EscrituraArchivoSecuencial escritura = new Problema1_EscrituraArchivoSecuencial("usuarios.dat");
        escritura.establecerRegistroUsuario(pablo);
        escritura.establecerSalida();
        escritura.establecerRegistroUsuario(ana);
        escritura.establecerSalida();
        escritura.cerrarArchivo();

        System.out.println("Datos guardados correctamente.");
        entrada.close();
    }
}
