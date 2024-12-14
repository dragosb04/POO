class Card {
    private String numar;
    private float sold;

    public Card (String numar, float sold){
        this.numar = numar;
        this.sold = sold;
    }

    public Card (float sold, String numar){
        this.numar = numar;
        this.sold = sold;
    }

    public String getNumar (){
        return numar;
    }

    public float getSold (){
        return sold;
    }
}

class Portofel {
    private String proprietar;
    private Card[] carduri = new Card[6];
    private int index;

    public Portofel(String proprietar){
        this.proprietar = proprietar;
    }

    public boolean adaugaCard(String numar, float sold){

        if (index < 6){
            for (int i = 0; i < index; i++){
                if (carduri[i].getSold() == sold && (carduri[i].getNumar()).equals(numar))
                    return false;
            }
            carduri[index++] = new Card(sold, numar);
            return true;
        }

        return false;
    }

    public String getProprietar(){
        return proprietar;
    }

    public String toString (){
        String result = "Portofel " + proprietar + "\n";

        for (int i = 0; i < index; i++)
            result += "Card [" + i +"]\n   " + carduri[i].getNumar() + " - " + carduri[i].getSold() + "\n";

        return result;
    }

    public float calculeazaSold (){
        float suma = 0;
        for (int i = 0; i < index; i++)
            suma += carduri[i].getSold();

        return suma;
    }
}

class Main {
    public static void main (String[] args){
        Portofel portofel = new Portofel("Ion Popescu");

        System.out.println("Adaugare card 1: " + portofel.adaugaCard("1234-5678-9012-3456", 1000.50f));
        System.out.println("Adaugare card 2: " + portofel.adaugaCard("9876-5432-1098-7654", 500.75f));
        System.out.println("Adaugare card 3: " + portofel.adaugaCard("1234-5678-9012-3456", 1000.50f));

        System.out.println(portofel);

        System.out.println("Sold total: " + portofel.calculeazaSold());
    
    }
}