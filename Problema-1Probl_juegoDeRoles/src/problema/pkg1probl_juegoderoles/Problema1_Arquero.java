/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problema.pkg1probl_juegoderoles;

/**
 *
 * @author ASUS
 */
public class Problema1_Arquero extends Problema1_Jugadores{
    private int flechas;
    private int punteria;

    public Problema1_Arquero(int flechas, int punteria, String armas, int fuerza, int velocidad, String id, String nombre) {
        super(armas, fuerza, velocidad, id, nombre);
        this.flechas = flechas;
        this.punteria = punteria;
    }

    public int getFlechas() { return flechas; }
    public void setFlechas(int flechas) { this.flechas = flechas; }
    public int getPunteria() { return punteria; }
    public void setPunteria(int punteria) { this.punteria = punteria; }

    @Override
    public int ataque() {
        if (flechas > 0) {
            flechas--;
            return nivelAtaque + fuerza + punteria;
        } else {
            System.out.println(nombre + " se quedó sin flechas.");
            return nivelAtaque + (fuerza / 2);
        }
    }

    @Override
    public int defensa() {
        return nivelDefensa + (velocidad / 2);
    }

    @Override
    public void subirNivel() {
        nivelExperiencia++;
        vida += 15;
        punteria += 5;
        flechas += 10;
    }

    @Override
    public int costoEnergiaHabilidad() {
        return 35;
    }

    @Override
    public int duracionCooldownHabilidad() {
        return 3;
    }

    /**
     * Habilidad especial del Arquero: "Lluvia de Flechas".
     * Dispara una ráfaga de 3 flechas; cada flecha disponible suma daño
     * extra. Si no quedan flechas, el efecto se reduce considerablemente.
     */
    @Override
    protected int efectoHabilidadEspecial() {
        int flechasDisparadas = Math.min(flechas, 3);
        flechas -= flechasDisparadas;

        int danioPorFlecha = nivelAtaque + punteria;
        int danioTotal = danioPorFlecha * Math.max(flechasDisparadas, 1);

        if (flechasDisparadas == 0) {
            System.out.println(nombre + " no tiene flechas para la lluvia y ataca con golpes débiles.");
            danioTotal = nivelAtaque + (fuerza / 2);
        } else {
            System.out.println(nombre + " dispara una ¡LLUVIA DE FLECHAS! ("
                    + flechasDisparadas + " flechas, " + danioTotal + " de daño total)");
        }
        return danioTotal;
    }

    @Override
    public String toString() {
        return "Arquero [" + nombre + "] | Nivel: " + nivelExperiencia 
         + " | Vida: " + vida + " | Flechas: " + flechas 
         + " | Puntería: " + punteria
         + " | Energía: " + energia + "/" + energiaMaxima
         + " | Cooldown: " + cooldownHabilidad;
    }
    
}
