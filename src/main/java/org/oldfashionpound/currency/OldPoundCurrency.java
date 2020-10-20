package org.oldfashionpound.currency;

public class OldPoundCurrency {

    private static final int SHILLINGS_IN_POUND = 20;
    private static final int PENCE_IN_SHILLING = 12;

    private int penceDivisionRemainder;
    private int pounds;
    private int shillings;
    private int pence;

    public OldPoundCurrency(int pounds, int shillings, int pence) {
        this.pounds = pounds;
        this.shillings = shillings;
        this.pence = pence;
        normalize();
    }

    public String getAmount() {
        return String.format("%dp %ds %dd", pounds, shillings, pence);
    }

    private void normalize() {
        shillings += pence / PENCE_IN_SHILLING;
        pence = pence % PENCE_IN_SHILLING;

        pounds += shillings / SHILLINGS_IN_POUND;
        shillings = shillings % SHILLINGS_IN_POUND;
    }

    public void add(OldPoundCurrency amountToAdd) {
        pence += amountToAdd.getPence();
        shillings += amountToAdd.getShillings();
        pounds += amountToAdd.getPounds();
        normalize();
    }

    public void subtract(OldPoundCurrency amountToSubtract) {
        if (pence - amountToSubtract.getPence() < 0) {
            pence += PENCE_IN_SHILLING;
            shillings--;
        }
        pence -= amountToSubtract.getPence();

        if (shillings - amountToSubtract.getShillings() < 0) {
            shillings += SHILLINGS_IN_POUND;
            pounds--;
        }
        shillings -= amountToSubtract.getShillings();

        pounds -= amountToSubtract.getPounds();
    }

    public void multiply(int value) {
        pence *= value;
        shillings *= value;
        pounds *= value;
        normalize();
    }

    public void divide(int value) {
        int poundsReminder = pounds % value;
        pounds /= value;
        if (poundsReminder > 0)
            shillings += poundsReminder * SHILLINGS_IN_POUND;

        int shillingsRemainder = shillings % value;
        shillings /= value;
        if (shillingsRemainder > 0)
            pence += shillingsRemainder * PENCE_IN_SHILLING;

        penceDivisionRemainder = pence % value;
        pence /= value;
    }

    public String getFormattedReminder() {
        if (penceDivisionRemainder == 0) return "()";

        StringBuilder builder = new StringBuilder();
        builder.append("(");

        if (penceDivisionRemainder > PENCE_IN_SHILLING) {
            int s = penceDivisionRemainder / PENCE_IN_SHILLING;
            penceDivisionRemainder %= PENCE_IN_SHILLING;
            builder.append(String.format("%ds ", s));
        }

        if (penceDivisionRemainder > 0)
            builder.append(String.format("%dd", penceDivisionRemainder));

        builder.append(")");
        return builder.toString();
    }

    public int getPounds() {
        return pounds;
    }

    public int getShillings() {
        return shillings;
    }

    public int getPence() {
        return pence;
    }

}
