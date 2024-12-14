class Benzinarie {
    private int benzinaDisponibila;
    private Masina[] masini = new Masina[10];
    private int index = 0;

    public Benzinarie (int benzina){
        benzinaDisponibila = benzina;
    }

    public boolean alimenteazaMasina (Masina data){
        if (benzinaDisponibila < data.getLitri()){
            if (index < 10){
                masini[index++] = data;
                return true;
            }
            return false;
        }
        else{
            benzinaDisponibila -= data.getLitri();
            return true;
        }
    }

    public String toString (){
        String result = "Coada benzinarie: \n";
        for (int i = 0; i < index; i++)
            result += "Masina [" + i + "]\n   Nr. inmatriculare: " + masini[i].getNrInmatriculare() + "\n   Cantitate necesara: " + masini[i].getLitri() + "\n";

        return result;
    }

    public void alimenteazaBenzinarie (int cantitateAdaugata){
        benzinaDisponibila += cantitateAdaugata;
        for (int i = 0; i < index; i++){
            if (masini[i].getLitri() <= benzinaDisponibila){
                benzinaDisponibila -= masini[i].getLitri();
                for (int j = i; j < index - 1; j++){
                    masini[j] = masini[j + 1];
                }
                i--;
                index--;
            }
            else break;
        }
    }
}

class Masina {
    private String nrInmatriculare;
    private int litri = 0;

    public Masina (int litri, String nrInmatriculare){
        this.litri = litri;
        this.nrInmatriculare = nrInmatriculare;
    }

    public Masina (String nrInmatriculare, int litri){
        this.litri = litri;
        this.nrInmatriculare = nrInmatriculare;
    }

    public int getLitri(){
        return litri;
    }

    public String getNrInmatriculare(){
        return nrInmatriculare;
    }
}

class Main {
    public static void main(String[] args) {

        Benzinarie benzinarie = new Benzinarie(50);

        Masina masina1 = new Masina(20, "DJ 12 PCD");
        Masina masina2 = new Masina(30, "OT 46 GHS");
        Masina masina3 = new Masina(50, "B 789 KLS");
        
        System.out.println("Alimentare masina 1: " + benzinarie.alimenteazaMasina(masina1));
        System.out.println("Alimentare masina 2: " + benzinarie.alimenteazaMasina(masina2));
        System.out.println("Alimentare masina 3: " + benzinarie.alimenteazaMasina(masina3));

        benzinarie.alimenteazaBenzinarie(55);      
        
        System.out.println(benzinarie);
        System.out.println("Alimentare masina 2: " + benzinarie.alimenteazaMasina(masina2));
        System.out.println(benzinarie);
    }
}
