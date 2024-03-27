import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TreeSet<HumanBeing> humanBeings = new TreeSet<>();

        System.out.println("Добро пожаловать! Для получения справки введите 'help'.");

        while (true) {
            System.out.print("Введите команду: ");
            String input = scanner.nextLine().trim();

            String[] commandParts = input.split("\\s+", 3);
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
                case "update":
                    if (commandParts.length < 3) {
                        System.out.println("Неверный формат команды. Используйте 'update id {name}'.");
                        break;
                    }
                    long idToUpdate = Long.parseLong(commandParts[1]);
                    String newName = commandParts[2];
                    boolean updated = false;
                    for (HumanBeing human : humanBeings) {
                        if (human.getId() == idToUpdate) {
                            human.setName(newName);
                            updated = true;
                            System.out.println("Элемент с id " + idToUpdate + " обновлен.");
                            break;
                        }
                    }
                    if (!updated) {
                        System.out.println("Элемент с id " + idToUpdate + " не найден.");
                    }
                    break;
                case "remove_by_id":
                    if (commandParts.length < 2) {
                        System.out.println("Неверный формат команды. Используйте 'remove_by_id {id}'.");
                        break;
                    }
                    long idToRemove = Long.parseLong(commandParts[1]);
                    boolean removed = false;
                    for (HumanBeing human : humanBeings) {
                        if (human.getId() == idToRemove) {
                            humanBeings.remove(human);
                            removed = true;
                            System.out.println("Элемент с id " + idToRemove + " удален из коллекции.");
                            break;
                        }
                    }
                    if (!removed) {
                        System.out.println("Элемент с id " + idToRemove + " не найден.");
                    }
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
        System.out.println("update id {name} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
    }
}

