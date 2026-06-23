/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

/**
 *
 * @author ASUS
 */
public class Envenenado implements IEstadoAlterado {

    private int turnosRestantes;
    private int danioPorTurno;

    public Envenenado(int turnosRestantes, int danioPorTurno) {
        this.turnosRestantes = turnosRestantes;
        this.danioPorTurno = danioPorTurno;
    }

    @Override
    public void aplicarEfecto(Problema1_Jugadores jugador) {
        jugador.recibirDanio(danioPorTurno);
        // Restamos el turno primero para que el mensaje de consola sea exacto
        turnosRestantes--;
        System.out.println("¡" + jugador.getNombre() + " sufre " + danioPorTurno
                + " puntos de daño por VENENO! (Turnos restantes del veneno: " + turnosRestantes + ")");
    }

    @Override
    public boolean haTerminado() {
        // Si los turnos llegan a 0 (o menos, por seguridad), devuelve true para que el jugador lo borre
        return turnosRestantes <= 0;
    }

}
