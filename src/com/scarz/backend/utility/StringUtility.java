package com.scarz.backend.utility;

public class StringUtility {
    /**
     * Adds padding to the left of the target string with the specified character and count
     * @param target String to add padding to
     * @param character Padding character to add
     * @param count Amount of padding to add
     * @return Copy of the target string with padding added to the left
     */
    public static String PadLeft(String target, char character, int count) {
        if (count - target.length() <= 0)
            return target;

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < count - target.length(); i++)
            output.append(character);
        output.append(target);

        return output.toString();
    }

    /**
     * Adds padding to the right of the target string with the specified character and count
     * @param target String to add padding to
     * @param character Padding character to add
     * @param count Amount of padding to add
     * @return Copy of the target string with padding added to the right
     */
    public static String PadRight(String target, char character, int count) {
        if (count - target.length() <= 0)
            return target;

        StringBuilder output = new StringBuilder(target);
        for (int i = 0; i < count - target.length(); i++)
            output.append(character);

        return output.toString();
    }
}
