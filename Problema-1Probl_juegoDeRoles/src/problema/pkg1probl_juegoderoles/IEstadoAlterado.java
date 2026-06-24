/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public interface IEstadoAlterado extends Serializable {
    // Método para aplicar el efecto de daño, cura o restricción
    void aplicarEfecto(Problema1_Jugadores jugador);
    
    // Método para saber si el estado ya cumplió sus turnos y debe borrarse
    boolean haTerminado();

}
