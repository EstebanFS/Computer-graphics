package escena;

/**
 *
 * @author LABREDES
 */
public class Esfera extends Entidad{
    Punto centro;
    double radio;

    public Esfera(Punto centro, double radio) {
        this.centro = centro;
        this.radio = radio;
    }

    @Override
    public String toString() {
        return "Esfera{" + "centro=" + centro + "radio=" + radio + '}';
    }


}

