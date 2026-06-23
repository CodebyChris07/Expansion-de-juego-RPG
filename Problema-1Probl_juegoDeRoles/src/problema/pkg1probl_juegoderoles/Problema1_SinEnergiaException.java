/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

/**
 * Excepción lanzada cuando un personaje intenta usar una habilidad especial
 * sin cumplir los requisitos necesarios (energía insuficiente o habilidad
 * en cooldown).
 *
 * @author ASUS
 */
public class Problema1_SinEnergiaException extends Exception {

    public Problema1_SinEnergiaException(String mensaje) {
        super(mensaje);
    }
}
