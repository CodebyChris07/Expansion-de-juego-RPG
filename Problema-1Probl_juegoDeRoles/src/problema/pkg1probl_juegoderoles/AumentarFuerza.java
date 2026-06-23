/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

/**
 *
 * @author ASUS
 */
public class AumentarFuerza implements IEstadoAlterado {

    private int turnos;
    private int bonoFuerza;
    private boolean aplicado = true;

    public AumentarFuerza(int turnos, int bonoFuerza) {
        this.turnos = turnos;
        this.bonoFuerza = bonoFuerza;
    }

    @Override
    public void aplicarEfecto(Problema1_Jugadores jugador) {
        if (!aplicado) {
            jugador.setFuerza(jugador.getFuerza() + bonoFuerza);
            aplicado = true;

        }
        turnos--;
        if (turnos <= 0) {
            jugador.setFuerza(jugador.getFuerza() - bonoFuerza);

        }

    }

    @Override
    public boolean haTerminado() {
    }

}
