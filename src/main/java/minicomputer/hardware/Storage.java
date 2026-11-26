package minicomputer.hardware;

public class Storage {
    private final String name;
    private final int totalGB;
    private int usedGB;

    public Storage(String name, int totalGB) {
        if (name == null || name.isBlank() || totalGB <= 0) {
            throw new IllegalArgumentException("Enter a storage name and positive capacity.");
        }
        this.name = name.trim();
        this.totalGB = totalGB;
    }

    public int getAvailableGB() {
        return totalGB - usedGB;
    }

    public boolean store(int amountGB) {
        if (amountGB <= 0 || amountGB > getAvailableGB()) {
            return false;
        }
        usedGB += amountGB;
        return true;
    }

    public boolean remove(int amountGB) {
        if (amountGB <= 0 || amountGB > usedGB) {
            return false;
        }
        usedGB -= amountGB;
        return true;
    }

    public String getSpecifications() {
        return "Storage: " + name + ", " + totalGB + " GB total, "
                + usedGB + " GB used, " + getAvailableGB() + " GB available";
    }
}
