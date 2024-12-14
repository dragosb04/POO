import java.util.*;

class Sir extends Tip {
    String atribut;

    public Sir(String obj) {
        atribut = obj;
    }

    public String getTip() {
        return "Tip: String";
    }

    public String toString() {
        return atribut;
    }

    public String getAtribut() {
        return atribut;
    }

    public boolean equals(Object obj) {
        Sir o = (Sir) obj;
        if (obj instanceof Sir) {
            return atribut.equals(o.getAtribut());
        }
        return false;
    }
}

class Intreg extends Tip {
    private int atribut;

    public Intreg(int o) {
        atribut = o;
    }

    public String getTip() {
        return "Tip: Intreg";
    }

    public int getAtribut() {
        return atribut;
    }

    public String toString() {
        String result = "";
        result += atribut + " ";
        return result;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Intreg) {
            Intreg o = (Intreg) obj;
            return o.getAtribut() == atribut;
        }
        return false;
    }
}

abstract class Tip {
    abstract public String getTip();

    abstract public String toString();
}

class Colectie {
    private ArrayList<Tip> atribut = new ArrayList<Tip>();

    public String getTip() {
        return "Tip: Colectie";
    }

    public String toString() {
        String result = "(";
        Iterator<Tip> it = atribut.iterator();
        while (it.hasNext()) {
            result += it.next().toString();
            if (it.hasNext()) {
                result += ", ";
            }
        }
        result += ")";
        return result;
    }

    public ArrayList<Tip> getAttributes() {
        return atribut;
    }

    public void adauga(Tip o) {
        atribut.add(o);
    }

    public boolean equals(Object obiect) {
        if (obiect == null || getClass() != obiect.getClass()) {
            return false;
        }
        Colectie o = (Colectie) obiect;
        if (o.getAttributes().size() != atribut.size()) {
            return false;
        }
        Iterator<Tip> it1 = o.getAttributes().iterator();
        Iterator<Tip> it2 = atribut.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next())) {
                return false;
            }
        }
        return true;
    }
}

class Main {
    public static void main(String[] args) {
        Colectie colectie1 = new Colectie();
        Colectie colectie2 = new Colectie();

        Sir o1 = new Sir("Ana");
        Sir o2 = new Sir("Andrei");

        Intreg obj1 = new Intreg(2);
        Intreg obj2 = new Intreg(4);
        Intreg obj3 = new Intreg(5);
        Intreg obj4 = new Intreg(1);

        colectie1.adauga(obj1);
        colectie1.adauga(obj2);
        colectie1.adauga(obj3);
        colectie1.adauga(o2);

        colectie2.adauga(obj1);
        colectie2.adauga(obj2);
        colectie2.adauga(new Intreg(5));
        colectie2.adauga(o1);
        colectie2.adauga(obj4);

        System.out.println(colectie1);
        System.out.println(colectie2);

        System.out.println(colectie1.equals(colectie2));
    }
}
