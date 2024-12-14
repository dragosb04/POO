import java.util.ArrayList;

abstract class Twitch {
    abstract public double calculeazaVenit(int min);
}


class Subscriber extends Twitch{
    private String nume;
    private int nivel;

    public Subscriber(String nume, int nivel) {
        this.nume = nume;
        this.nivel = nivel;
    }

    public double calculeazaVenit(int min){
        return 1.5 * min * nivel;
    }

    public String toString() {
        return nume + " - subscriber - " + nivel;
    }

}

class Creator extends Twitch {
    private String nume;
    private ArrayList<Subscriber> subscriberi = new ArrayList<Subscriber>();

    public Creator(String nume) {
        this.nume = nume;
    }

    public void adaugaSubscriber(Subscriber subscriber){
        subscriberi.add(subscriber);
    }

    public double calculeazaVenit(int nr){
        double result = 0.0;
        for (Subscriber subscriber : subscriberi) {
            result += subscriber.calculeazaVenit(nr);
        }
        return result;
    }

    public String toString() {
        String result = nume + "\n";
        for (Subscriber subscriber : subscriberi) {
            result += subscriber.toString() + "\n";
        }

        return result;
    }
}

class Platforma {
    private ArrayList <Twitch> utilizatori = new ArrayList<Twitch>();

    public boolean adaugaUtilizator(Twitch utilizator){
        if (utilizatori.size() < 1000){
            utilizatori.add(utilizator);
            return true;
        }
        return false;
    }
    
    public Twitch determinaVIP(int min) {
        Twitch maxim = null;
        for (Twitch util : utilizatori) {
            if (maxim == null)
                maxim = util;
            else if (maxim.calculeazaVenit(min) < util.calculeazaVenit(min))
                maxim = util;
        }
        return maxim;
    }
}

class Main {
    public static void main(String[] args) {

        Platforma platforma = new Platforma();
    
        Creator creator1 = new Creator("Creator1");
        Creator creator2 = new Creator("Creator2");
        Creator creator3 = new Creator("Creator3");
    
        Subscriber sub1 = new Subscriber("Sub1", 1);
        Subscriber sub2 = new Subscriber("Sub2", 2);
        Subscriber sub3 = new Subscriber("Sub3", 3);
        Subscriber sub4 = new Subscriber("Sub4", 4);
    
        creator1.adaugaSubscriber(sub1);
        creator2.adaugaSubscriber(sub2);
        creator2.adaugaSubscriber(sub3);
        creator3.adaugaSubscriber(sub4);
    
        platforma.adaugaUtilizator(creator1);
        platforma.adaugaUtilizator(creator2);
        platforma.adaugaUtilizator(creator3);
    
        int minute = 30;
    
        System.out.println("Creator1 Venit: " + creator1.calculeazaVenit(minute));
        System.out.println("Creator2 Venit: " + creator2.calculeazaVenit(minute));
        System.out.println("Creator3 Venit: " + creator3.calculeazaVenit(minute));
    
        Twitch vip = platforma.determinaVIP(minute);
        System.out.println("Utilizatorul VIP: " + vip);
    }
    
}