/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class Problema1_Combate {

    public static boolean puedenCombatir(Problema1_Jugadores j1, Problema1_Jugadores j2) {
        boolean mismoNivel = j1.getNivelExperiencia() == j2.getNivelExperiencia();
        boolean mismoAtaque = j1.getNivelAtaque() == j2.getNivelAtaque();
        boolean mismoTipo = j1.getClass() == j2.getClass();

        if (mismoNivel && mismoAtaque && mismoTipo) {
            return false;
        }
        return true;
    }

    public static void iniciarCombate(Problema1_Jugadores j1, Problema1_Jugadores j2) {
        if (!puedenCombatir(j1, j2)) {
            System.out.println("No pueden combatir: mismo nivel, ataque y tipo.");
            return;
        }

        System.out.println("\n¡Comienza el combate entre " + j1.getNombre() + " y " + j2.getNombre() + "!");

        while (j1.estaVivo() && j2.estaVivo()) {
            int danio1 = Math.max(0, j1.ataque() - j2.defensa());
            j2.recibirDanio(danio1);
            System.out.println(j1.getNombre() + " ataca a " + j2.getNombre() + " con " + danio1 + " de daño.");

            if (!j2.estaVivo()) {
                break;
            }

            int danio2 = Math.max(0, j2.ataque() - j1.defensa());
            j1.recibirDanio(danio2);
            System.out.println(j2.getNombre() + " ataca a " + j1.getNombre() + " con " + danio2 + " de daño.");
        }

        Problema1_Jugadores ganador = j1.estaVivo() ? j1 : j2;
        System.out.println("¡" + ganador.getNombre() + " gana el combate!");
        ganador.subirNivel();
    }

    public static void batallaAleatoria(Problema1_Usuario u1, Problema1_Usuario u2) {
        Random random = new Random();

        while (hayVivos(u1.getEquipo()) && hayVivos(u2.getEquipo())) {
            Problema1_Jugadores p1 = elegirVivoAleatorio(u1.getEquipo(), random);
            Problema1_Jugadores p2 = elegirVivoAleatorio(u2.getEquipo(), random);

            if (p1 == null || p2 == null) {
                break;
            }

            int intentos = 0;
            while (!puedenCombatir(p1, p2) && intentos < 20) {
                p1 = elegirVivoAleatorio(u1.getEquipo(), random);
                p2 = elegirVivoAleatorio(u2.getEquipo(), random);
                intentos++;
            }
            if (intentos >= 20) {
                System.out.println("No se encontró un combate válido, terminando batalla.");
                break;
            }
            iniciarCombate(p1, p2);
        }

        int vivos1 = contarVivos(u1.getEquipo());
        int vivos2 = contarVivos(u2.getEquipo());

        System.out.println("\n" + u1.getNombreUsuario() + " tiene " + vivos1 + " personajes en pie.");
        System.out.println(u2.getNombreUsuario() + " tiene " + vivos2 + " personajes en pie.");

        if (vivos1 > vivos2) {
            System.out.println(u1.getNombreUsuario() + " gana la batalla!");
        } else if (vivos2 > vivos1) {
            System.out.println(u2.getNombreUsuario() + " gana la batalla!");
        } else {
            System.out.println("¡Empate!");
        }
    }

    private static Problema1_Jugadores elegirVivoAleatorio(ArrayList<Problema1_Jugadores> equipo, Random random) {
        ArrayList<Problema1_Jugadores> vivos = new ArrayList<>();
        for (Problema1_Jugadores j : equipo) {
            if (j.estaVivo()) {
                vivos.add(j);
            }
        }
        if (vivos.isEmpty()) {
            return null;
        }
        return vivos.get(random.nextInt(vivos.size()));
    }

    private static boolean hayVivos(ArrayList<Problema1_Jugadores> equipo) {
        return contarVivos(equipo) > 0;
    }

    private static int contarVivos(ArrayList<Problema1_Jugadores> equipo) {
        int contador = 0;
        for (Problema1_Jugadores j : equipo) {
            if (j.estaVivo()) {
                contador++;
            }
        }
        return contador;
    }

}
