package problema.pkg1probl_juegoderoles;

import problema.pkg1probl_juegoderoles.Inventario.armas.espada;

public class Problema1_Guerrero extends Problema1_Jugadores {

    private int golpes;
    private int escudo;
    private espada espada;

    public Problema1_Guerrero(int fuerza, int velocidad, String id, String nombre, espada espada) {
        super(fuerza, velocidad, id, nombre);
        this.golpes = 0;
        this.escudo = 10;
        this.espada = espada;
        agregarObjeto(espada);
        equiparArma(espada);
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
    public int calcularAtaque() {
        golpes++;
        int danioArma = 0;
        if (armaEquipada != null) {
            danioArma = armaEquipada.getDanio();
            armaEquipada.Desgaste();
        }
        int danioBase = nivelAtaque + fuerza + danioArma;
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
    public int calcularDefensa() {
        int defensaArmadura = 0;
        if (armaduraEquipada != null) {
            defensaArmadura = armaduraEquipada.getDefensa();
        }
        return nivelDefensa + escudo + (velocidad / 3) + defensaArmadura;
    }

    @Override
    public void subirNivel() {
        nivelExperiencia++;
        vida += 15;
    }

    @Override
    public int costoEnergiaHabilidad() {
        return 30;
    }

    @Override
    public int duracionCooldownHabilidad() {
        return 3;
    }

    /**
     * Habilidad especial del Guerrero: "Golpe Devastador".
     * Inflige un golpe de altísimo daño basado en su fuerza y nivel de
     * ataque, e ignora el escudo del enemigo (se aplica como daño puro).
     */
    @Override
    protected int efectoHabilidadEspecial() {
        int danio = (nivelAtaque + fuerza) * 3;
        System.out.println(nombre + " desata un ¡GOLPE DEVASTADOR! (" + danio + " de daño puro)");
        return danio;
    }

    @Override
    public String toString() {
        return "Guerrero [" + nombre + "] | Nivel: " + nivelExperiencia
                + " | Vida: " + vida + " | Escudo: " + escudo
                + " | Golpes: " + golpes
                + " | Energía: " + energia + "/" + energiaMaxima
                + " | Cooldown: " + cooldownHabilidad;
    }
}