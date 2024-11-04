//import java.io.File;
import java.util.Scanner;

class ReadFile {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Printing the file passed in:");
            while(sc.hasNextLine()) System.out.println(sc.nextLine());
        }
    }

}