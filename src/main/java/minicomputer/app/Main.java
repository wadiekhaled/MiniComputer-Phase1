package minicomputer.app;

import java.util.NoSuchElementException;
import java.util.Scanner;
import minicomputer.hardware.CPU;
import minicomputer.hardware.Computer;
import minicomputer.hardware.RAM;
import minicomputer.hardware.Storage;

public class Main {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Computer computer = createComputer(input);
            runMenu(input, computer);
        } catch (NoSuchElementException e) {
            System.out.println("Input ended. Goodbye.");
        }
    }

    private static Computer createComputer(Scanner input) {
        System.out.println("=== Configure MiniComputer ===");
        String name = readName(input, "Computer name: ");
        String cpuModel = readName(input, "CPU model: ");
        int cores = readAmount(input, "CPU cores: ");
        int clockMHz = readAmount(input, "CPU clock speed (MHz): ");
        int ramMB = readAmount(input, "RAM capacity (MB): ");
        String storageName = readName(input, "Storage name: ");
        int storageGB = readAmount(input, "Storage capacity (GB): ");

        CPU cpu = new CPU(cpuModel, cores, clockMHz);
        RAM ram = new RAM(ramMB);
        Storage storage = new Storage(storageName, storageGB);
        return new Computer(name, cpu, ram, storage);
    }

    private static void runMenu(Scanner input, Computer computer) {
        RAM ram = computer.getRam();
        Storage storage = computer.getStorage();

        while (true) {
            printMenu();
            switch (readLine(input, "Choice: ")) {
                case "1":
                    System.out.println(computer.powerOn() ? "Computer powered on." : "Computer is already on.");
                    break;
                case "2":
                    System.out.println(computer.shutDown() ? "Computer shut down." : "Computer is already off.");
                    break;
                case "3":
                    System.out.println("Power status: " + (computer.isPoweredOn() ? "ON" : "OFF"));
                    break;
                case "4":
                    System.out.println(computer.getSpecifications());
                    break;
                case "5":
                    System.out.println("RAM: " + ram.getTotalMB() + " MB total, "
                            + ram.getUsedMB() + " MB used, " + ram.getAvailableMB() + " MB available");
                    break;
                case "6":
                    int toAllocate = readAmount(input, "Amount to allocate (MB): ");
                    System.out.println(ram.allocate(toAllocate) ? "RAM allocated." : "Not enough available RAM.");
                    break;
                case "7":
                    int toRelease = readAmount(input, "Amount to release (MB): ");
                    System.out.println(ram.release(toRelease) ? "RAM released." : "Cannot release more RAM than is in use.");
                    break;
                case "8":
                    System.out.println(storage.getSpecifications());
                    break;
                case "9":
                    int toStore = readAmount(input, "Amount to store (GB): ");
                    System.out.println(storage.store(toStore) ? "Data stored." : "Not enough available storage.");
                    break;
                case "10":
                    int toRemove = readAmount(input, "Amount to remove (GB): ");
                    System.out.println(storage.remove(toRemove) ? "Data removed." : "Cannot remove more data than is stored.");
                    break;
                case "0":
                    System.out.println("Goodbye.");
                    return;
                default:
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

    private static String readLine(Scanner input, String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    private static String readName(Scanner input, String prompt) {
        while (true) {
            String value = readLine(input, prompt);
            if (!value.isBlank()) {
                return value;
            }
            System.out.println("Please enter a name.");
        }
    }

    private static int readAmount(Scanner input, String prompt) {
        while (true) {
            try {
                int value = Integer.parseInt(readLine(input, prompt));
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Print the same message for text, decimals and numbers outside the int range.
            }
            System.out.println("Enter a positive whole number.");
        }
    }

}
