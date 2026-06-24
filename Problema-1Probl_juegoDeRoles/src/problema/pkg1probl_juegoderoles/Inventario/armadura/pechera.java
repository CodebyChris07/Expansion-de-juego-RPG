package problema.pkg1probl_juegoderoles.Inventario.armadura;

import java.util.ArrayList;

public class pechera extends armadura {
    
    private static final String[] ENCANTAMIENTOS_DISPONIBLES = {
        "Protección IV", "Proyectiles IV", "Thorns III"
    };

    public pechera(String nombre, String material) {
        super(0, new ArrayList<>(), nombre, 0, material);
        switch (material) {
            case "hierro":    this.durabilidad = 240; this.defensa = 6;  break;
            case "diamante":  this.durabilidad = 528; this.defensa = 8;  break;
            case "netherita": this.durabilidad = 592; this.defensa = 9;  break;
            default:          this.durabilidad = 240; this.defensa = 6;  break;
        }
    }

    @Override
    public int Desgaste() {
        if (durabilidad > 0) {
            durabilidad--;
        } else {
            System.out.println(nombre + " está rota, no se puede usar.");
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
