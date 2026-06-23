/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

/**
 *
 * @author ASUS
 */
public class Problema1_Mago extends Problema1_Jugadores {

    private int mana;
    private String afinidad;

    public Problema1_Mago(int mana, String afinidad, String armas, int fuerza, int velocidad, String id, String nombre) {
        super(armas, fuerza, velocidad, id, nombre);
        this.mana = mana;
        this.afinidad = afinidad;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getAfinidad() {
        return afinidad;
    }

    public void setAfinidad(String afinidad) {
        this.afinidad = afinidad;
    }

    @Override
    public int ataque() {
        int danioBase = nivelAtaque + (fuerza / 2);
        if (afinidad.equalsIgnoreCase("Fuego")) {
            return danioBase += 15;
        } else if (afinidad.equalsIgnoreCase("Agua")) {
            return danioBase += 5;
        } else if (afinidad.equalsIgnoreCase("Tierra")) {
            return danioBase += 8;
        } else if (afinidad.equalsIgnoreCase("Aire")) {
            return danioBase += 12;
        } else {
            return danioBase += 2;
        }
    }

    @Override
    public int defensa() {
        int defensaBase = nivelDefensa + (velocidad / 3);
        if (afinidad.equalsIgnoreCase("Fuego")) {
            return defensaBase += 2;
        } else if (afinidad.equalsIgnoreCase("Agua")) {
            return defensaBase += 12;
        } else if (afinidad.equalsIgnoreCase("Tierra")) {
            return defensaBase += 15;
        } else if (afinidad.equalsIgnoreCase("Aire")) {
            return defensaBase += 5;
        } else {
            return defensaBase += 2;
        }
    }

    @Override
    public void subirNivel() {
        nivelExperiencia++;
        vida += 15;
        mana += 30;
    }

    @Override
    public String toString() {
        return "[Mago de " + afinidad + "] " + super.toString() + " | Mana: " + mana;
    }

}
