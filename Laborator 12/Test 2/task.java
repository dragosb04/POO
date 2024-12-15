import java.util.ArrayList;

abstract class Task {
    abstract public boolean execute(double sec);
}

class SimpleTask extends Task {
    private String nume;
    private double timp;

    public SimpleTask(String nume, double timp) {
        this.nume = nume;
        this.timp = timp;
    }

    public SimpleTask(double timp, String nume) {
        this.nume = nume;
        this.timp = timp;
    }

    public double getTime() {
        return timp;
    }

    public boolean execute(double sec) {
        if (timp > sec) {
            timp -= sec;
            return false;
        } else {
            timp = 0;
            return true;
        }
    }

    public String toString() {
        return "Name: " + nume + " Time: " + timp;
    }
}

class ComposedTask extends Task {
    private String nume;
    private ArrayList<SimpleTask> seq = new ArrayList<SimpleTask>();

    public ComposedTask(String nume, ArrayList<SimpleTask> seq) {
        this.seq = seq;
        this.nume = nume;
    }

    public ComposedTask(ArrayList<SimpleTask> seq, String nume) {
        this.seq = seq;
        this.nume = nume;
    }

    public boolean execute(double sec) {
        for (SimpleTask s : seq) {
            if (s.getTime() > 0) {
                double time_before = s.getTime();
                if (s.execute(sec) == false) {
                    return false;
                }
                double time_after = s.getTime();
                sec -= (time_before - time_after);
            }
        }

        return true;
    }

    public String toString() {
        String result = "Name: " + nume + ", Content:\n";

        for (SimpleTask s : seq)
            result += "   " + s + "\n";

        return result;
    }
}

class Procesor {
    private ArrayList<Task> seq = new ArrayList<Task>();

    public Procesor(ArrayList<Task> seq) {
        this.seq = seq;
    }

    public void finishAllTasks() {
        for (Task t : seq) {
            while (t.execute(5) == false)
                ;
        }
    }

    public String toString() {
        String result = "Procesor: ";

        for (Task s : seq) {
            result += s;
        }

        return result;
    }

}

class Main {
    public static void main(String[] args) {

        SimpleTask t1 = new SimpleTask("Simple_Task_1", 5);
        SimpleTask t2 = new SimpleTask("Simple_Task_2", 10);
        
        ArrayList<SimpleTask> simpleTasks = new ArrayList<>();
        simpleTasks.add(t1);
        simpleTasks.add(t2);

        ComposedTask composedTask = new ComposedTask("Composed_Task", simpleTasks);

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(composedTask);

        Procesor procesor = new Procesor(tasks);
        System.out.println(procesor);
        procesor.finishAllTasks();

        System.out.println(procesor);
    }
}