package model;

public class CurrentAccount extends Account {
    private static double AIR = 0.005; // 0.5% Interest Rate
    private double overdraftLimit;

    public CurrentAccount(int id, int custNo, double balance, double overdraftLimit) {
        super(id, custNo, balance, java.time.LocalDate.now());
        this.overdraftLimit = overdraftLimit;
    }

    public static double getAIR() { return AIR; }
    public static void setAIR(double air) { AIR = air; }

    public double getOverdraftLimit() { return overdraftLimit; }
    public void setOverdraftLimit(double overdraftLimit) { this.overdraftLimit = overdraftLimit; }
}
