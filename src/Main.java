import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Добро пожаловать! Для получения справки введите 'help'.");

        while (true) {
            System.out.print("Введите команду: ");
            input = scanner.nextLine().trim();

            switch (input) {
                case "help":
                    printHelp();
                    break;
                case "exit":
                    System.out.println("Завершение программы.");
                    return;
                default:
                    System.out.println("Неверная команда. Введите 'help' для получения справки.");
            }
        }
    }

    private static void printHelp() {
        System.out.println("Список доступных команд:");
        System.out.println("help : выводит справку по доступным командам");
        System.out.println("exit : завершить программу (без сохранения в файл)");
    }
}