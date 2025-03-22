public class Investment {
    private double amount;
    private double returnRate;
    private int months;
    private int startMonth;

    public Investment(double amount, double returnRate, int months, int startMonth) {
        this.amount = amount;
        this.returnRate = returnRate;
        this.months = months;
        this.startMonth = startMonth;
    }

    public double getAmount() {
        return amount;
    }

    public double getReturn() {
        return returnRate;
    }

    public int getMonths() {
        return months;
    }

    public int getStartMonth() {
        return startMonth;
    }
}
