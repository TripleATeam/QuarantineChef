package database;

import java.util.Arrays;
import java.util.Date;

public class Pantry {
    private Ingredient[] ingredients;
    private String[] expirations;
    private int[] quantities;

    public Pantry (Ingredient[] ingredients, String[] expirations, int[] quantities) {
        this.ingredients = ingredients;
        this.expirations = expirations;
        this.quantities = quantities;
    }

    public Pantry (Ingredient[] ingredients, Date[] expirations, int[] quantities) {
        this.ingredients = ingredients;
        this.expirations = DateArrToStringArr(expirations);
        this.quantities = quantities;
    }

    // Made comment
    private static String[] DateArrToStringArr(Date[] dates) {
        String[] retArr = new String[dates.length];
        StringBuilder sb;
        for (int i = 0; i < dates.length; i++) {
            Date d = dates[i];
            sb = new StringBuilder();
            sb.append(d.getDate());
            sb.append("-");
            sb.append(d.getMonth());
            sb.append("-");
            sb.append(d.getYear() + 1900);
            retArr[i] = sb.toString();
        }
        return retArr;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public Date[] getExpirations() {
        Date[] dateArr = new Date[expirations.length];
        for (int i = 0; i < expirations.length; i++) {
            dateArr[i] = stringToDate(expirations[i]);
        }
        return dateArr;
    }

    private static Date stringToDate(String date) {
        int firstDash = date.indexOf("-");
        // Fix to parse date in format Tue Jun 01 00:00:00 PDT 3920
        if (firstDash == -1) {
            return new Date(date);
        }
        int secondDash = date.indexOf("-", firstDash + 1);

        int day = Integer.parseInt(date.substring(0, firstDash));
        int month = Integer.parseInt(date.substring(firstDash + 1, secondDash));
        int year = Integer.parseInt(date.substring(secondDash + 1));
        return new Date(year, month - 1, day);
    }

    public int[] getQuantities() {
        return quantities;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pantry)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        Pantry otherPan = (Pantry) other;
        boolean bool1 = Arrays.equals(ingredients, otherPan.ingredients);
        boolean bool2 = Arrays.equals(expirations, otherPan.expirations);
        boolean bool3 = Arrays.equals(quantities, otherPan.quantities);
        return bool1 && bool2 && bool3;
    }

    @Override
    public String toString() {
        return "[" + Arrays.deepToString(this.ingredients) + ", " +
                Arrays.deepToString(this.expirations) + ", " +
                Arrays.toString(this.quantities) + "]";
    }
}
