package pl.coderslab;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;


    public static void printOptions(String[] tab) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option: " + ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  //wprowadzamy nade z konsoli
        while (scanner.hasNextLine()) {
            String choice = scanner.nextLine(); //tryb wyboru opcji


            switch (choice) {

                case "exit": //pobieranie z metody dla działania po wpisaniu exit
                    saveFile(FILE_NAME, tasks);
                    System.out.println(ConsoleColors.RED + "Bye, bye.");
                    System.exit(0);
                    break;
                case "add":
                    addTask();
                    break;
            }

        }
    }

    private static void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task date");
        String taskDate = scanner.nextLine();
        System.out.println("is your task important: true/false");
        String isImportant = scanner.nextLine();
        tasks = Arrays.copyOf(tasks, tasks.length +1); //pobieram elementy z tablicy z pliku i zwiekszam o kolejny do umozliwienia dopisania kolejnego wiersza
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = description; //obsluga nowego wiersza
        tasks[tasks.length - 1][1] = taskDate;
        tasks[tasks.length - 1][2] = isImportant;
    }

    public static void saveFile(String fileName, String[][] tab) {
        Path dir = Paths.get(fileName);

        String [] rows = new String[tasks.length];
        for (int i = 0; i < tab.length; i++) {
            rows [i] = String.join(",", tab[i]); //pobieram elementy z tablicy i wpiuje do pamieci
        }

        try {
            Files.write(dir, Arrays.asList(rows)); //zapis calej tablicy z pamieci do pliku
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}