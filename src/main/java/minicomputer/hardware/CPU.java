package minicomputer.hardware;

public class CPU {
    private final String model;
    private final int cores;
    private final int clockMHz;
    private int accumulator;
    private int programCounter;

    public CPU(String model, int cores, int clockMHz) {
        if (model == null || model.trim().equals("")) {
            model = "CPU";
        }
        if (cores <= 0) {
            cores = 1;
        }
        if (clockMHz <= 0) {
            clockMHz = 1;
        }
        this.model = model;
        this.cores = cores;
        this.clockMHz = clockMHz;
        resetRegisters();
    }

    public int getAccumulator() {
        return accumulator;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void resetRegisters() {
        accumulator = 0;
        programCounter = 0;
    }

    public String getSpecifications() {
        return "CPU: " + model + ", " + cores + " cores, " + clockMHz + " MHz";
    }
}
