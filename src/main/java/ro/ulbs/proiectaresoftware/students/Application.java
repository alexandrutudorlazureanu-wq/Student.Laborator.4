package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Path caleStudenti = Paths.get("studenti.txt");
        Path caleNote = Paths.get("note_anon.txt");


        Map<Integer, Student> mapaStudenti = new HashMap<>();


        try {


            if (Files.exists(caleStudenti)) {

                List<String> liniiStudenti = Files.readAllLines(caleStudenti);

                for (String linie : liniiStudenti) {

                    if (linie.trim().isEmpty()) continue;


                    String[] date = linie.split(",");

                    int matricol = Integer.parseInt(date[0].trim());

                    String prenume = date[1].trim();

                    String nume = date[2].trim();

                    String formatie = date[3].trim();


                    Student student = new Student(matricol, prenume, nume, formatie);

                    mapaStudenti.put(matricol, student);
                }
            } else {
                System.out.println("Eroare: Fisierul studenti.txt nu exista!");

                return;
            }



            if (Files.exists(caleNote)) {
                List<String> liniiNote = Files.readAllLines(caleNote);

                for (String linie : liniiNote) {

                    if (linie.trim().isEmpty()) continue;


                    String[] date = linie.split(",");

                    int matricol = Integer.parseInt(date[0].trim());

                    float nota = Float.parseFloat(date[1].trim());





                    if (mapaStudenti.containsKey(matricol)) {

                        mapaStudenti.get(matricol).setNota(nota);

                    }
                }
            } else {

                System.out.println("Fisierul note nu exista!");
                return;

            }



            System.out.println(String.format("%-15s %-15s %-15s %-15s %-10s",
                    "numar matricol", "prenume", "nume", "formatieDeStudiu", "nota"));
            System.out.println("   ");

            for (Map.Entry<Integer, Student> entry : mapaStudenti.entrySet()) {
                System.out.println(entry.getValue());
            }

        } catch (IOException e) {
            System.out.println("Eroare:  " + e.getMessage());

        }


        catch (NumberFormatException e) {

            System.out.println("Eroare:  " + e.getMessage());

        }
    }
}