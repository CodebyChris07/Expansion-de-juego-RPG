package problema.pkg1probl_juegoderoles.Inventario.armadura;

import java.util.ArrayList;

public class pantalones extends armadura {
    
    private static final String[] ENCANTAMIENTOS_DISPONIBLES = {
        "Protección IV", "Agilidad III", "Irrompibilidad III"
    };

    public pantalones(String nombre, String material) {
        super(0, new ArrayList<>(), nombre, 0, material);
        switch (material) {
            case "hierro":    this.durabilidad = 225; this.defensa = 5;  break;
            case "diamante":  this.durabilidad = 495; this.defensa = 6;  break;
            case "netherita": this.durabilidad = 555; this.defensa = 7;  break;
            default:          this.durabilidad = 225; this.defensa = 5;  break;
        }
    }

    @Override
    public int Desgaste() {
        if (durabilidad > 0) {
            durabilidad--;
        } else {
            System.out.println(nombre + " están rotos, no se pueden usar.");
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