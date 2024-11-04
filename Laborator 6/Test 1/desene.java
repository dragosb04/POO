class Figura {
    private float arie;

    public Figura (float arie){
        this.arie = arie;
    }

    public float getArie(){
        return arie;
    }

    public boolean equals (Object o){
        if (o instanceof Figura){
            Figura that = (Figura) o;
            return that.getArie() == arie;
        }
        return false;
    }
}

class Desen {
    private String titlu;
    private Figura[] figuri = new Figura[1024];
    private int indexFiguri;

    public Desen (String titlu){
        this.titlu = titlu;
    }

    public boolean adaugaFigura (Figura obj){
        if (indexFiguri < 1024){
            for (int i = 0; i < indexFiguri; i++){
                if (obj == figuri[i])
                    return false;
            }
            
            figuri[indexFiguri++] = obj;
            return true;
        }
        return false;
    }

    public String toString (){
        String result = "Titlu desen: " + titlu + "\n";
        for (int i = 0; i < indexFiguri; i++){
            result += "Figura[" + i + "]: \n    Arie: " + figuri[i].getArie() + "\n";
        }
        return result;
    }

    public float medieArie (){
        if (indexFiguri == 0)
            return 0;
        
        float suma = 0;
        for (int i = 0; i < indexFiguri; i++)
            suma+= figuri[i].getArie();
        
        return (suma / indexFiguri);
    }

}

class Main {
    public static void main(String[] args) {
        Figura figura1 = new Figura(15.5f);
        Figura figura2 = new Figura(22.0f);
        Figura figura3 = new Figura(15.5f);
        Figura figura4 = new Figura(30.0f);


        Desen desen = new Desen("Desen");

        desen.adaugaFigura(figura1);
        desen.adaugaFigura(figura2);
        desen.adaugaFigura(figura3); 
        desen.adaugaFigura(figura4);
        desen.adaugaFigura(figura3);

        System.out.println(desen);
        System.out.println("Media arie: " + desen.medieArie());
    }
}
