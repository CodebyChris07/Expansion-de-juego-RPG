/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

/**
 *
 * @author ASUS
 */
public class Congelado implements IEstadoAlterado{
    private int turnosRestantes;

    public Congelado(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }

    @Override
    public void aplicarEfecto(Problema1_Jugadores jugador) {
        // lo ponemos en false para que no pueda atacar
        jugador.setPuedeAtacar(false);
        
        //Restamos un turno de duración
        turnosRestantes--;
        
       
        System.out.println("¡" + jugador.getNombre() + " está CONGELADO y no puede atacar! (Turnos restantes: " + turnosRestantes + ")");
    }

    @Override
    public boolean haTerminado() {
        // La misma lógica de caducidad que el veneno
        return turnosRestantes <= 0;
    }
}
