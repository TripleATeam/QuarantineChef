package database;

import java.util.Arrays;
import java.util.Date;

public class Pantry {
    private Ingredient[] ingredients;
    private String[] expirations;
    private int[] quantities;

    /**
     * Returns a Pantry with the given arrays of Ingredients, expiration dates,
     * and quantities, respective to the parameters. Each of the ingredients,
     * expirations, and quantities MUST be of the same length.
     *
     * @param ingredients   An array of Ingredients representing ingredients
     * @param expirations   An array of Strings representing expiration dates of ingredients
     * @param quantities    An array of ints representing quantities of ingredients
     */
    public Pantry (Ingredient[] ingredients, String[] expirations, int[] quantities) {
        this.ingredients = ingredients;
        this.expirations = expirations;
        this.quantities = quantities;
    }

    /**
     * Returns a Pantry with the given arrays of Ingredients, expiration dates,
     * and quantities, respective to the parameters. Each of the ingredients,
     * expirations, and quantities MUST be of the same length.
     *
     * @param ingredients   An array of Ingredients representing ingredients
     * @param expirations   An array of Dates representing expiration dates of ingredients
     * @param quantities    An array of ints representing quantities of ingredients
     */
    public Pantry (Ingredient[] ingredients, Date[] expirations, int[] quantities) {
        this.ingredients = ingredients;
        this.expirations = DateArrToStringArr(expirations);
        this.quantities = quantities;
    }

    /**
     * Returns an array of Dates from a given array of Strings.
     * Assumes the Strings are in the form "DD-MM-YYYY" in order
     * to parse the data into Date objects.
     *
     * @param dates An array of Dates to be converted into Strings
     * @return      An array of Strings from the given Dates
     */
    private static String[] DateArrToStringArr(Date[] dates) {
        String[] retArr = new String[dates.length];
        StringBuilder sb;
        for (int i = 0; i < dates.length; i++) {
            Date d = dates[i];
            sb = new StringBuilder();
            sb.append(d.getDate());
            sb.append("-");
            sb.append(d.getMonth() + 1);
            sb.append("-");
            sb.append(d.getYear() + 1900);
            retArr[i] = sb.toString();
        }
        return retArr;
    }

    /**
     * Returns an array of the ingredients stored in the Pantry. The quantity
     * in quantities[i] will correspond to the Ingredient in Ingredients[i].
     *
     * @return the list of quantities.
     */
    public Ingredient[] getIngredients() {
        return ingredients;
    }

    /**
     * Returns a list of the quantities of ingredients stored in the Pantry.
     * The quantity in expirations[i] will correspond to the Ingredient in
     * Ingredients[i]. Returns the expiration dates in Date format.
     *
     * @return the list of expirations.
     */
    public Date[] getExpirations() {
        Date[] dateArr = new Date[expirations.length];
        for (int i = 0; i < expirations.length; i++) {
            dateArr[i] = stringToDate(expirations[i]);
        }
        return dateArr;
    }

    /**
     * Returns a list of the quantities of ingredients stored in the Pantry.
     * The quantity in expirations[i] will correspond to the Ingredient in
     * Ingredients[i]. Returns the expiration dates in String format.
     *
     * @return the list of expirations.
     */
    public String[] getExpirationsAsStrings() {
        return expirations;
    }

    /**
     * Returns a Date from a String in the form DD-MM-YYYY.
     *
     * @param date  the String to be parsed into a Date object
     * @return      a Date object representing the equivalent of
     *              the passed String.
     */
    private static Date stringToDate(String date) {
        if (date == null || date.equals("null")) {
            return null;
        }
        int firstDash = date.indexOf("-");
        // Fix to parse date in format Tue Jun 01 00:00:00 PDT 3920
        if (firstDash == -1) {
            return new Date(date);
        }
        int secondDash = date.indexOf("-", firstDash + 1);

        int day = Integer.parseInt(date.substring(0, firstDash));
        int month = Integer.parseInt(date.substring(firstDash + 1, secondDash));
        int year = Integer.parseInt(date.substring(secondDash + 1)) - 1900;
        return new Date(year, month - 1, day);
    }

    /**
     * Returns a list of the quantities of ingredients stored in the Pantry.
     * The quantity in quantities[i] will correspond to the Ingredient in
     * Ingredients[i].
     *
     * @return the list of quantities.
     */
    public int[] getQuantities() {
        return quantities;
    }

    /**
     * Returns a boolean denoting if another Object is equal to this one.
     * If the other Object is anything other than a Pantry with the exact
     * same values in the same fields, this will be false. If it is such
     * a Pantry, then this method will return true.
     *
     * @param other Another Object that this will be compared to
     * @return      A boolean that signifies if other is equal to this.
     */
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

    /**
     * Returns a String in the form "([Array of Ingredients], [Array of expiration dates],
     * [Array of quantities])".
     *
     * @return      a String in the proper form
     */
    @Override
    public String toString() {
        return "[" + Arrays.deepToString(this.ingredients) + ", " +
                Arrays.deepToString(this.expirations) + ", " +
                Arrays.toString(this.quantities) + "]";
    }
}
