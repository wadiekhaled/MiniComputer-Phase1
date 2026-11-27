package minicomputer.hardware;

public class Storage {
    private final String name;
    private final int totalGB;
    private int usedGB;

    public Storage(String name, int totalGB) {
        if (name == null || name.trim().equals("")) {
            name = "Storage";
        }
        if (totalGB <= 0) {
            totalGB = 1;
        }
        this.name = name;
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
