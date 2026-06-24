package problema.pkg1probl_juegoderoles;

import problema.pkg1probl_juegoderoles.Inventario.armas.arco;

public class Problema1_Arquero extends Problema1_Jugadores {

    private int flechas;
    private int punteria;
    private arco arco;

    public Problema1_Arquero(int flechas, int punteria, int fuerza, int velocidad, String id, String nombre,
            arco arco) {
        super(fuerza, velocidad, id, nombre);
        this.flechas = flechas;
        this.punteria = punteria;
        this.arco = arco;
        agregarObjeto(arco);
        equiparArma(arco);
    }

    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        this.flechas = flechas;
    }

    public int getPunteria() {
        return punteria;
    }

    public void setPunteria(int punteria) {
        this.punteria = punteria;
    }

    @Override
    public int calcularAtaque() {
        if (flechas > 0) {
            flechas--;
            int danioArma = 0;
            if (armaEquipada != null) {
                danioArma = armaEquipada.getDanio();
                armaEquipada.Desgaste();
            }
            return nivelAtaque + fuerza + punteria + danioArma;
        } else {
            System.out.println(nombre + " se quedó sin flechas.");
            return nivelAtaque + (fuerza / 2);
        }
    }

    @Override
    public int calcularDefensa() {
        int defensaArmadura = 0;
        if (armaduraEquipada != null) {
            defensaArmadura = armaduraEquipada.getDefensa();
        }
        return nivelDefensa + (velocidad / 2) + defensaArmadura;
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

     * Habilidad especial del Arquero: "Lluvia de Flechas". Dispara una ráfaga
     * de 3 flechas; cada flecha disponible suma daño extra. Si no quedan
     * flechas, el efecto se reduce considerablemente.

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

        return "Arquero [" + nombre + "] | Nivel: " + nivelExperiencia
                + " | Vida: " + vida + " | Flechas: " + flechas

                + " | Puntería: " + punteria
                + " | Energía: " + energia + "/" + energiaMaxima
                + " | Cooldown: " + cooldownHabilidad;

                + " | Puntería: " + punteria;


    }
}
