package problema.pkg1probl_juegoderoles.Inventario.armas;

import java.util.ArrayList;

public class arco extends armas {
    private int carga;
    
    private static final String[] ENCANTAMIENTOS_DISPONIBLES = {
        "Poder V", "Infinidad I", "Retroceso II"
    };

    public arco(String nombre, int danio, String tipoDealcance, int carga) {
        super(0, new ArrayList<>(), nombre, danio, tipoDealcance);
        this.carga = carga;
        this.durabilidad = 384;
    }

    public int getCarga() { return carga; }
    public void setCarga(int carga) { this.carga = carga; }

    @Override
    public int Desgaste() {
        if (durabilidad > 0) {
            durabilidad--;
            carga++;
        } else {
            System.out.println(nombre + " está roto, no se puede usar.");
        }
        return durabilidad;
    }

    @Override
    public String encantar(String encantamiento) {
        for (int i = 0; i < ENCANTAMIENTOS_DISPONIBLES.length; i++) {
            String e = ENCANTAMIENTOS_DISPONIBLES[i];
            if (e.equals(encantamiento) && !this.encantamiento.contains(e)) {
                this.encantamiento.add(e);
                return "Encantamiento aplicado: " + e;
            }
        }
        return "Encantamiento no válido.";
    }
}