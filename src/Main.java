import java.util.Scanner;
import java.util.TreeSet;
import java.io.*;
import java.util.Date;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TreeSet<HumanBeing> humanBeings = loadCollectionFromFile();

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
                case "save":
                    if (commandParts.length < 2) {
                        System.out.println("Неверный формат команды. Используйте 'save {filename}'.");
                        break;
                    }
                    String filename = commandParts[1];
                    saveCollectionToFile(humanBeings, filename);
                    break;
                case "info":
                    System.out.println("Информация о коллекции:");
                    System.out.println("Тип коллекции: TreeSet<HumanBeing>");
                    System.out.println("Дата инициализации: " + new Date());
                    System.out.println("Количество элементов в коллекции: " + humanBeings.size());
                    break;
                case "show":
                    if (humanBeings.isEmpty()) {
                        System.out.println("Коллекция пуста.");
                    } else {
                        System.out.println("Элементы коллекции:");
                        for (HumanBeing human : humanBeings) {
                            System.out.println(human);
                        }
                    }
                    break;
                case "clear":
                    humanBeings.clear();
                    System.out.println("Коллекция очищена.");
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
        System.out.println("save {filename} : сохранить коллекцию в файл");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("clear : очистить коллекцию");
    }
    private static void saveCollectionToFile(TreeSet<HumanBeing> collection, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (HumanBeing human : collection) {
                writer.println(human.getId() + "," + human.getName());
            }
            System.out.println("Коллекция сохранена в файл " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при сохранении файла " + filename + ": " + e.getMessage());
        }
    }

    private static TreeSet<HumanBeing> loadCollectionFromFile() {
        TreeSet<HumanBeing> collection = new TreeSet<>();
        System.out.print("Введите имя файла для загрузки коллекции: ");
        String filename = scanner.nextLine().trim();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                long id = Long.parseLong(parts[0]);
                String name = parts[1];
                collection.add(new HumanBeing(id, name));
            }
            System.out.println("Коллекция загружена из файла " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке файла " + filename + ": " + e.getMessage());
            System.out.println("Создана новая пустая коллекция.");
        }
        return collection;
    }
}

