interface Risky {
    public double getRisk();
}

class Membru{
    private int varsta;
    private String nume;

    public Membru (int varsta, String nume){
        this.varsta = varsta;
        this.nume = nume;
    }

    public Membru (String nume, int varsta){
        this.varsta = varsta;
        this.nume = nume;
    }

    public int getVarsta(){
        return varsta;
    }

    public String getNume(){
        return nume;
    }
}

abstract class Project implements Risky{
    private Membru manager;
    private String titlu;
    private String obiectiv;
    private long fonduri;
    private int nrMembri;
    
    abstract public void addMember(Membru m);
    
    public Project (Membru manager, String titlu, String obiectiv, long fonduri, int nrMembri){
        this.manager = manager;
        this.titlu = titlu;
        this.obiectiv = obiectiv;
        this.fonduri = fonduri;
        this.nrMembri = nrMembri;
    }

    public int getNrMembri(){
        return nrMembri;
    }
    
    public long getFonduri(){
        return fonduri;
    }

    public String getTitlu(){
        return titlu;
    }

    public String getObiectiv(){
        return obiectiv;
    }

    public Membru getManager(){
        return manager;
    }

    public void setIndex(int index){
        this.nrMembri= index;
    }
}

class Comercial extends Project {
    private String deadline;
    private long fonduriMarketing;
    private int echipe;
    private Membru[] participanti;
    
    public Comercial (Membru manager, String titlu, String obiectiv, long fonduri, int nrMembri, String deadline, long fonduriMarketing, int echipe){
        super(manager, titlu, obiectiv, fonduri, nrMembri);
        this.deadline = deadline;
        this.fonduriMarketing = fonduriMarketing;
        this.echipe = echipe;
        this.participanti = new Membru[15];
    }

    public void addMember(Membru m) {
        if (getNrMembri() < 15){
            participanti[getNrMembri()] = m;
            setIndex(getNrMembri() + 1);
        }
    }

    public double getRisk(){
        return (double) echipe * 3 / getNrMembri() / (getFonduri() - fonduriMarketing);
    }

    public String getDeadline(){
        return deadline;
    }
}

class Militar extends Project {
    private String parola;
    private Membru[] participanti;

    public Militar (String parola, Membru manager, String titlu, String obiectiv, long fonduri, int nrMembri){
        super(manager, titlu, obiectiv, fonduri, nrMembri);
        this.parola = parola;
        this.participanti = new Membru[15];
    }

    public double getRisk(){
        return (double) getNrMembri() / parola.length() / getFonduri();
    }

    public void addMember(Membru m) {
        if (getNrMembri() < 15){
            participanti[getNrMembri()] = m;
            setIndex(getNrMembri() + 1);
        }
    }
}

class OpenSource extends Project {
    private String mailingList;
    private Membru[] participanti;

    public OpenSource (String mailingList, Membru manager, String titlu, String obiectiv, long fonduri, int nrMembri){
        super(manager, titlu, obiectiv, fonduri, nrMembri);
        this.mailingList = mailingList;
        this.participanti = new Membru[15];
    }

    public double getRisk() {
        return (double) getNrMembri() / getFonduri();
    }

    public String getMailListing(){
        return mailingList;
    }

    public void addMember(Membru m) {
        if (getNrMembri() == participanti.length) {
            Membru[] membriNoi = new Membru[participanti.length * 2];

            for (int i = 0; i < participanti.length; i++) {
                membriNoi[i] = participanti[i];
            }

            participanti = membriNoi;
        }
        participanti[getNrMembri()] = m;
        setIndex(getNrMembri() + 1);
    }
}

class InvestmentCompany {
    private Project[] proiecte;
    private int nrProiecte;

    public InvestmentCompany (Project[] proiecte){
        this.proiecte = proiecte;
        nrProiecte = 0;
    }

    public void addProject(Project p) {
        if (nrProiecte == proiecte.length) { // Dacă vectorul este plin, îl redimensionăm
            Project[] proiecteNoi = new Project[proiecte.length * 2];
            
            // Copiere manuală a proiectelor
            for (int i = 0; i < proiecte.length; i++) {
                proiecteNoi[i] = proiecte[i];
            }
            
            proiecte = proiecteNoi; // Actualizăm referința
        }
        
        proiecte[nrProiecte] = p; // Adăugăm proiectul
        nrProiecte++;             // Creștem numărul de proiecte
    }

    public Project getBestInvestment() {
        if (nrProiecte == 0) {
            return null; // Dacă nu sunt proiecte, returnăm null
        }

        Project bestProject = proiecte[0];
        double minRisk = bestProject.getRisk();

        for (int i = 1; i < nrProiecte; i++) {
            double risk = proiecte[i].getRisk();
            if (risk < minRisk) {
                minRisk = risk;
                bestProject = proiecte[i];
            }
        }

        return bestProject;
    }

    public static void main(String[] args) {

        Membru manager1 = new Membru(40, "Alice");
        Membru manager2 = new Membru(35, "Bob");
        Membru manager3 = new Membru(45, "Charlie");

        InvestmentCompany company = new InvestmentCompany(new Project[2]);

        Project proiect1 = new Comercial(manager1, "E-Commerce", "Creșterea vânzărilor online", 100000, 10, "2024-12-31", 20000, 3);
        Project proiect2 = new Militar("parola123", manager2, "Apărare", "Securitate națională", 500000, 15);
        Project proiect3 = new OpenSource("opensource@example.com", manager3, "Software liber", "Contribuție globală", 50000, 5);

        company.addProject(proiect1);
        company.addProject(proiect2);
        company.addProject(proiect3);

        Project bestInvestment = company.getBestInvestment();
        if (bestInvestment != null) {
            System.out.println("Cel mai bun proiect pentru investiție este: " + bestInvestment.getTitlu());
            System.out.println("Risc: " + bestInvestment.getRisk());
        } else {
            System.out.println("Nu există proiecte disponibile pentru investiție.");
        }
    }
}