package minicomputer.hardware;

public class Computer {
    private final String name;
    private final CPU cpu;
    private final RAM ram;
    private final Storage storage;
    private boolean poweredOn;

    public Computer(String name, String cpuModel, int cores, int clockMHz,
            int ramMB, String storageName, int storageGB) {
        if (name == null || name.trim().equals("")) {
            name = "Computer";
        }
        this.name = name;
        this.cpu = new CPU(cpuModel, cores, clockMHz);
        this.ram = new RAM(ramMB);
        this.storage = new Storage(storageName, storageGB);
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
        String status = "OFF";
        if (poweredOn) {
            status = "ON";
        }
        return "Computer: " + name + " (" + status + ")\n"
                + cpu.getSpecifications() + "\n"
                + "RAM: " + ram.getTotalMB() + " MB total\n"
                + storage.getSpecifications();
    }
}
