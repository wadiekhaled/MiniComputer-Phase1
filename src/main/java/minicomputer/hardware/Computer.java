package minicomputer.hardware;

public class Computer {
    private final String name;
    private final CPU cpu;
    private final RAM ram;
    private final Storage storage;
    private boolean poweredOn;

    public Computer(String name, CPU cpu, RAM ram, Storage storage) {
        if (name == null || name.isBlank() || cpu == null || ram == null || storage == null) {
            throw new IllegalArgumentException("A computer needs a name, CPU, RAM and storage.");
        }
        this.name = name.trim();
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
    }

    public boolean powerOn() {
        if (poweredOn) {
            return false;
        }
        cpu.resetRegisters();
        poweredOn = true;
        return true;
    }

    public boolean shutDown() {
        if (!poweredOn) {
            return false;
        }
        poweredOn = false;
        return true;
    }

    public boolean isPoweredOn() {
        return poweredOn;
    }

    public CPU getCpu() {
        return cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public String getSpecifications() {
        return "Computer: " + name + " (" + (poweredOn ? "ON" : "OFF") + ")"
                + System.lineSeparator() + cpu.getSpecifications()
                + System.lineSeparator() + "RAM: " + ram.getTotalMB() + " MB total"
                + System.lineSeparator() + storage.getSpecifications();
    }
}
