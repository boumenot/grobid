package org.grobid.core.features;

/**
 * Created by boumenot on 7/17/2015.
 */
public interface FeatureTester {

    /**
     * Test if all the letters of the string are capital letters
     * (characters can be also digits which are then ignored)
     */
    boolean test_all_capital(String tok);

    /**
     * Test for the current string contains only digit
     */
    boolean test_number(String tok);

    /**
     * Test that the current string is a YEAR (1000 - 2999)
     */
    boolean test_year(String tok);

    /**
     * Test that current string is an email address.
     */
    boolean test_email(String tok);

    /**
     * Test that current string contains http.
     */
    boolean test_http(String tok);

    /**
     * Test that the current string is all punctuation.
     */
    boolean test_punct(String tok);

    /**
     * Test if the current string is a common name
     */
    boolean test_common(String tok);

    /**
     * Test if the current string is a first name
     */
    boolean test_first_names(String tok);

    /**
     * Test if the current string is a family name
     */
    boolean test_last_names(String tok);

    /**
     * Test if the current string is a first name or family name
     */
    boolean test_names(String tok);

    /**
     * Test if the current string refers to a month
     */
    boolean test_month(String tok);

    /**
     * Test for the current string contains at least one digit
     */
    boolean test_digit(String tok);
}
