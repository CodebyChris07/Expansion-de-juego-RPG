package problema.pkg1probl_juegoderoles;

import java.util.ArrayList;
import java.util.Random;

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

        Random random = new Random();
        System.out.println("\n¡Comienza el combate entre " + j1.getNombre() + " y " + j2.getNombre() + "!");
        while (j1.estaVivo() && j2.estaVivo()) {

            int danio1 = calcularDanioDelTurno(j1, j2, random);


            // --- TURNO JUGADOR 1 ---
            // Reseteamos el  ataque y aplicamos efectos (veneno, congelado, etc)
            j1.setPuedeAtacar(true);
            j1.evaluarEstados();

            // Solo atacamos si sigue vivo y no está congelado
            if (j1.estaVivo() && j1.isPuedeAtacar()) {
                int danio1 = Math.max(0, j1.ataque() - j2.defensa());
                j2.recibirDanio(danio1);
                System.out.println(j1.getNombre() + " ataca a " + j2.getNombre() + " con " + danio1 + " de daño.");

                // --- PROBABILIDAD DE ESTADOS ---
                Random prob = new Random();
                int suerte = prob.nextInt(100); // Genera un número del 0 al 99

                // 15% de probabilidad de ENVENENAR al rival
                if (suerte < 15 && !j2.tieneEstado(Envenenado.class)) {
                    j2.recibirEstados(new Envenenado(3, 5)); // 3 turnos, 5 de daño
                    System.out.println("¡" + j1.getNombre() + " ha envenenado a " + j2.getNombre() + "!");
                } // 10% de probabilidad de CONGELAR al rival
                else if (suerte >= 15 && suerte < 25 && !j2.tieneEstado(Congelado.class)) {
                    j2.recibirEstados(new Congelado(1)); // 1 turno sin atacar
                    System.out.println("¡" + j1.getNombre() + " ha congelado a " + j2.getNombre() + "!");
                } // 15% de probabilidad de AUMENTAR SU PROPIA FUERZA
                else if (suerte >= 25 && suerte < 40 && !j1.tieneEstado(AumentarFuerza.class)) {
                    j1.recibirEstados(new AumentarFuerza(3, 10)); // 3 turnos, +10 de fuerza
                }

            } else if (!j1.isPuedeAtacar()) {
                System.out.println(j1.getNombre() + " está impedido para atacar.");
            }
            int danio1 = Math.max(0, j1.calcularAtaque() - j2.calcularDefensa());
            j2.recibirDanio(danio1);
            System.out.println(j1.getNombre() + " ataca a " + j2.getNombre() + " con " + danio1 + " de daño.");

            if (!j2.estaVivo()) {
                break;
            }


            int danio2 = calcularDanioDelTurno(j2, j1, random);

            // --- TURNO JUGADOR 2 ---
            j2.setPuedeAtacar(true);
            j2.evaluarEstados();

            if (j2.estaVivo() && j2.isPuedeAtacar()) {
                int danio2 = Math.max(0, j2.ataque() - j1.defensa());
                j1.recibirDanio(danio2);
                System.out.println(j2.getNombre() + " ataca a " + j1.getNombre() + " con " + danio2 + " de daño.");

                // --- PROBABILIDAD DE ESTADOS PARA EL JUGADOR 2 ---
                Random prob = new Random();
                int suerte = prob.nextInt(100);

                // 15% de probabilidad de ENVENENAR al rival 
                if (suerte < 15 && !j1.tieneEstado(Envenenado.class)) {
                    j1.recibirEstados(new Envenenado(3, 5)); // 3 turnos, 5 de daño
                    System.out.println("¡" + j2.getNombre() + " ha envenenado a " + j1.getNombre() + "!");
                } // 10% de probabilidad de CONGELAR al rival 
                else if (suerte >= 15 && suerte < 25 && !j1.tieneEstado(Congelado.class)) {
                    j1.recibirEstados(new Congelado(1)); // 1 turno sin atacar
                    System.out.println("¡" + j2.getNombre() + " ha congelado a " + j1.getNombre() + "!");
                } // 15% de probabilidad de AUMENTAR SU PROPIA FUERZA (j2)
                else if (suerte >= 25 && suerte < 40 && !j2.tieneEstado(AumentarFuerza.class)) {
                    j2.recibirEstados(new AumentarFuerza(3, 10)); // 3 turnos, +10 de fuerza
                }
                // -------------------------------------------------

            } else if (!j2.isPuedeAtacar()) {
                System.out.println(j2.getNombre() + " está impedido para atacar.");
            }
            int danio2 = Math.max(0, j2.calcularAtaque() - j1.calcularDefensa());

            j1.recibirDanio(danio2);
            System.out.println(j2.getNombre() + " ataca a " + j1.getNombre() + " con " + danio2 + " de daño.");

            // Al finalizar la ronda completa, ambos personajes avanzan su turno
            // (reduce cooldown y regenera energía).
            j1.avanzarTurno();
            j2.avanzarTurno();
        }

        }
    }

    /**
     * Calcula el daño que un atacante infringe a su rival en el turno
     * actual. Con una probabilidad del 30% el atacante intenta usar su
     * habilidad especial; si no está disponible (cooldown o energía
     * insuficiente) se captura la excepción y se recurre al ataque normal.
     */
    private static int calcularDanioDelTurno(Problema1_Jugadores atacante, Problema1_Jugadores defensor, Random random) {
        boolean intentaHabilidad = random.nextInt(100) < 30;

        if (intentaHabilidad) {
            try {
                int danioHabilidad = atacante.usarHabilidadEspecial();
                return Math.max(0, danioHabilidad - defensor.defensa());
            } catch (Problema1_SinEnergiaException ex) {
                System.out.println(ex.getMessage() + " Realiza un ataque normal en su lugar.");
            }
        }

        return Math.max(0, atacante.ataque() - defensor.defensa());
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