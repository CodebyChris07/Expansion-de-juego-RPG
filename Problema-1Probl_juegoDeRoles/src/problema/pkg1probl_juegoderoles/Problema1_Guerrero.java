/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

/**
 *
 * @author ASUS
 */
public class Problema1_Guerrero extends Problema1_Jugadores {

    private int golpes;
    private int escudo;

    public Problema1_Guerrero(String armas, int fuerza, int velocidad, String id, String nombre) {
        super(armas, fuerza, velocidad, id, nombre);
        this.golpes = 0;
        this.escudo = 10;
    }

    public int getGolpes() {
        return golpes;
    }

    public int getEscudo() {
        return escudo;
    }

    public boolean furia() {
        return vida <= 15;
    }

    public boolean critico() {
        return golpes >= 3;
    }

    @Override
    public int ataque() {
        golpes++;
        int danioBase = nivelAtaque + fuerza;
        if (critico()) {
            golpes = 0;
            danioBase = danioBase * 2;
        }
        if (furia()) {
            danioBase = danioBase * 2;
        }
        return danioBase;
    }

    @Override
    public int defensa() {
        return nivelDefensa + escudo + (velocidad / 3);
    }

    @Override
    public void subirNivel() {
        nivelExperiencia++;
        vida += 15;
    }

    @Override
    public String toString() {
        return "Guerrero [" + nombre + "] | Nivel: " + nivelExperiencia
                + " | Vida: " + vida + " | Escudo: " + escudo
                + " | Golpes: " + golpes;
    }
}
