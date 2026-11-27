package minicomputer.hardware;

public class RAM {
    private final int totalMB;
    private int usedMB;

    public RAM(int totalMB) {
        if (totalMB <= 0) {
            totalMB = 1;
        }
        this.totalMB = totalMB;
    }

    public int getTotalMB() {
        return totalMB;
    }

    public int getUsedMB() {
        return usedMB;
    }

    public int getAvailableMB() {
        return totalMB - usedMB;
    }

    public boolean allocate(int amountMB) {
        if (amountMB <= 0 || amountMB > getAvailableMB()) {
            return false;
        }
        usedMB += amountMB;
        return true;
    }

    public boolean release(int amountMB) {
        if (amountMB <= 0 || amountMB > usedMB) {
            return false;
        }
        usedMB -= amountMB;
        return true;
    }
}
