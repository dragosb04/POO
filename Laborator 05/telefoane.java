import java.io.File;
import java.util.Random;
import java.util.Scanner;

class Telefon {
    private String numeProprietar;
    private String[] recente = new String[100];
    private int index;

    public Telefon (String numeProprietar){
        this.numeProprietar = numeProprietar;
    }

    public boolean apeleaza (Telefon data){
        if (data.index < 100 && this != data){
            data.recente[(data.index)++] = numeProprietar;
            return true;
        }

        return false;
    }

    public String getProprietar (){
        return numeProprietar;
    }

    public Integer numarDeApeluri (String nume){
        Integer cnt = 0;
        for (int i = 0; i < index; i++){
            if (recente[i].equals(nume))
                cnt++;
        }
        return cnt;
    }

    public String toString (){
        String result = numeProprietar + "\n   ";
        for (int i = 0; i < index; i++)
            result += recente[i] + ", ";
        
        return result;
    }

    public int getNrRecente(){
        return index;
    }

    public String[] getNumeApelant(){
        return recente;
    }
}

class Main {
    public static void main (String[] args){
        try{
            File file = new File ("in.txt");
            Scanner input = new Scanner(file); // fie Scanner input = new Scanner(System.in); pentru a citi de la tastatura

            System.out.println("Numar telefoane: ");
            int n = Integer.parseInt(input.nextLine());

            Telefon[] telefoane = new Telefon[n];
            for (int i = 0; i < n; i++){
                String nume = input.nextLine();
                telefoane[i] = new Telefon(nume);
            }

            int A = Integer.parseInt(input.nextLine());
            Random rand = new Random();
            for (int i = 0; i < A; i++)
                telefoane[(rand.nextInt(n))].apeleaza(telefoane[(rand.nextInt(n))]);
            
            for (int i = 0; i < n; i++)
                System.out.println(telefoane[i]);
            
            String numeCautat = input.nextLine();
            for (int i = 0; i < n; i++){
                int aparitii = 0;
                for (int j = 0; j < telefoane[i].getNrRecente(); j++){
                    if (telefoane[i].getNumeApelant()[j].equals(numeCautat))
                        aparitii++;
                }
                System.out.println(numeCautat + " a sunat pe " + telefoane[i].getProprietar() + " de " + aparitii + " ori.");
            }
            input.close();
            System.out.println("Input inchis.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
}