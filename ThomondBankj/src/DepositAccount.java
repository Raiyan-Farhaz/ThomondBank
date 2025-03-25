public class DepositAccount extends Account{
    private static double AIR = 0.02; // 2% Interest Rate

    public DepositAccount(int id, int custNo, double balance) {
        super(id, custNo, balance, java.time.LocalDate.now());
    }
    public static double getAIR() { return AIR; }
    public static void setAIR(double air) { AIR = air; }



}
