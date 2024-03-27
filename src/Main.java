import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TreeSet<HumanBeing> humanBeings = new TreeSet<>();

        System.out.println("Добро пожаловать! Для получения справки введите 'help'.");

        while (true) {
            System.out.print("Введите команду: ");
            String input = scanner.nextLine().trim();

            String[] commandParts = input.split("\\s+", 2);
            String command = commandParts[0].toLowerCase();

            switch (command) {
                case "help":
                    printHelp();
                    break;
                case "exit":
                    System.out.println("Завершение программы.");
                    return;
                case "add":
                    if (commandParts.length < 2) {
                        System.out.println("Неверный формат команды. Используйте 'add {name}'.");
                        break;
                    }
                    String nameToAdd = commandParts[1];
                    HumanBeing newHuman = new HumanBeing(nameToAdd);
                    humanBeings.add(newHuman);
                    System.out.println("Элемент добавлен в коллекцию.");
                    break;
                default:
                    System.out.println("Неверная команда. Введите 'help' для получения справки.");
            }
        }
    }

    private static void printHelp() {
        System.out.println("Список доступных команд:");
        System.out.println("help : выводит справку по доступным командам");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("add {name} : добавить новый элемент в коллекцию");
    }
}
