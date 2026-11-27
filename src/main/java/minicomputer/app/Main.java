package minicomputer.app;

import java.util.Scanner;
import minicomputer.hardware.Computer;
import minicomputer.hardware.RAM;
import minicomputer.hardware.Storage;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Computer computer = createComputer(input);
        if (computer != null) {
            runMenu(input, computer);
        }
        input.close();
        System.out.println("Goodbye.");
    }

    private static Computer createComputer(Scanner input) {
        System.out.println("=== Set up your computer ===");
        String name = readName(input, "Computer name: ");
        String model = readName(input, "CPU model: ");
        int cores = readNumber(input, "CPU cores: ", 1);
        int speed = readNumber(input, "CPU speed (MHz): ", 1);
        int ramMB = readNumber(input, "RAM capacity (MB): ", 1);
        String storageName = readName(input, "Storage name: ");
        int storageGB = readNumber(input, "Storage capacity (GB): ", 1);

        if (name == null || model == null || storageName == null || storageGB == -1) {
            return null;
        }
        return new Computer(name, model, cores, speed, ramMB, storageName, storageGB);
    }

    private static void runMenu(Scanner input, Computer computer) {
        RAM ram = computer.getRam();
        Storage storage = computer.getStorage();
        int choice = 1;

        while (choice > 0) {
            printMenu();
            choice = readNumber(input, "Choice: ", 0);

            if (choice == 1) {
                if (computer.powerOn()) {
                    System.out.println("Computer powered on.");
                } else {
                    System.out.println("Computer is already on.");
                }
            } else if (choice == 2) {
                if (computer.shutDown()) {
                    System.out.println("Computer shut down.");
                } else {
                    System.out.println("Computer is already off.");
                }
            } else if (choice == 3) {
                if (computer.isPoweredOn()) {
                    System.out.println("Power status: ON");
                } else {
                    System.out.println("Power status: OFF");
                }
            } else if (choice == 4) {
                System.out.println(computer.getSpecifications());
            } else if (choice == 5) {
                System.out.println("RAM total: " + ram.getTotalMB() + " MB");
                System.out.println("RAM used: " + ram.getUsedMB() + " MB");
                System.out.println("RAM available: " + ram.getAvailableMB() + " MB");
            } else if (choice == 6) {
                int amount = readNumber(input, "Amount to allocate (MB): ", 1);
                if (amount == -1) {
                    return;
                }
                if (ram.allocate(amount)) {
                    System.out.println("RAM allocated.");
                } else {
                    System.out.println("Not enough available RAM.");
                }
            } else if (choice == 7) {
                int amount = readNumber(input, "Amount to release (MB): ", 1);
                if (amount == -1) {
                    return;
                }
                if (ram.release(amount)) {
                    System.out.println("RAM released.");
                } else {
                    System.out.println("Cannot release more RAM than is in use.");
                }
            } else if (choice == 8) {
                System.out.println(storage.getSpecifications());
            } else if (choice == 9) {
                int amount = readNumber(input, "Amount to store (GB): ", 1);
                if (amount == -1) {
                    return;
                }
                if (storage.store(amount)) {
                    System.out.println("Data stored.");
                } else {
                    System.out.println("Not enough available storage.");
                }
            } else if (choice == 10) {
                int amount = readNumber(input, "Amount to remove (GB): ", 1);
                if (amount == -1) {
                    return;
                }
                if (storage.remove(amount)) {
                    System.out.println("Data removed.");
                } else {
                    System.out.println("Cannot remove more data than is stored.");
                }
            } else if (choice > 10) {
                System.out.println("Choose one of the listed options.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== MiniComputer Menu ===");
        System.out.println("1. Power on");
        System.out.println("2. Shut down");
        System.out.println("3. Show power status");
        System.out.println("4. Show computer specifications");
        System.out.println("5. Show RAM usage");
        System.out.println("6. Allocate RAM");
        System.out.println("7. Release RAM");
        System.out.println("8. Show storage capacity");
        System.out.println("9. Store data");
        System.out.println("10. Remove data");
        System.out.println("0. Exit");
    }

    private static String readName(Scanner input, String prompt) {
        System.out.print(prompt);
        while (input.hasNextLine()) {
            String name = input.nextLine().trim();
            if (!name.equals("")) {
                return name;
            }
            System.out.println("Please enter a name.");
            System.out.print(prompt);
        }
        return null;
    }

    private static int readNumber(Scanner input, String prompt, int minimum) {
        System.out.print(prompt);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner numberInput = new Scanner(line);
            if (numberInput.hasNextInt()) {
                int number = numberInput.nextInt();
                if (number >= minimum && !numberInput.hasNext()) {
                    numberInput.close();
                    return number;
                }
            }
            numberInput.close();
            System.out.println("Enter a whole number of at least " + minimum + ".");
            System.out.print(prompt);
        }
        return -1;
    }
}
