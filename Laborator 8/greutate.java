abstract class Greutate {

    abstract public int capacitate();

}

class Greutate_Simpla extends Greutate{
    private int capacitate;

    public Greutate_Simpla (int capacitate) {
        this.capacitate = capacitate;
    }

    public int capacitate(){
        return capacitate;
    }
}

class Greutate_Dubla extends Greutate {
    private Greutate g1, g2;

    public Greutate_Dubla(Greutate g1, Greutate g2){
        this.g1 = g1;
        this.g2 = g2;
    }

    public int capacitate(){
        return g1.capacitate() + g2.capacitate();
    }

    public void setGreutate1(Greutate g){
        g1 = g;
    }

    public void setGreutate2(Greutate g){
        g2 = g;
    }
}

class Greutate_Multipla extends Greutate {
    Greutate greutati[];

    public Greutate_Multipla(Greutate[] g){
        this.greutati = g;
    }

    public int capacitate(){
        int sum = 0;
        for (Greutate g : greutati){
            sum += g.capacitate();
        }

        return sum;
    }
}
class greutate {
    public static void main(String[] args) {

        Greutate simpla1 = new Greutate_Simpla(10);
        Greutate simpla2 = new Greutate_Simpla(20);

        Greutate dubla = new Greutate_Dubla(simpla1, simpla2);

        Greutate[] greutati = {simpla1, dubla};
        Greutate multipla = new Greutate_Multipla(greutati);

        System.out.println("Capacitate simpla1: " + simpla1.capacitate());
        System.out.println("Capacitate simpla2: " + simpla2.capacitate());
        System.out.println("Capacitate dubla: " + dubla.capacitate());
        System.out.println("Capacitate multipla: " + multipla.capacitate());
    }
}
