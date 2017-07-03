package com.artbox.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mykola.khazanovych on 6/30/2017.
 */
public class RegistrationValidator {
    private static Pattern pattern;
    private static Matcher matcher;

    //Suppress default costructor for noninstantiability. Approach by Josh Bloch
    private RegistrationValidator(){
        throw new AssertionError( "This class is just a collection of utilities!" );
    }

    /*
    ^			           # start of the line
    [_A-Za-z0-9-\.]{4,31}  # must contain no less than 4 characters including "_" and ".", but no more than 31
    [A-Za-z]{1}            # If group after @ contains more than one character, the first one ought to be non-numeric
    (\.[A-Za-z]{2,3})      # after the dot we need only two ar three characters
    *                      # (it could be optional)
    $			           # end of the line
    */
    public static boolean validateEmail( String email ) {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\.]{4,31}@"
                        + "[A-Za-z]{1}([A-Za-z0-9-])*(\\.[A-Za-z]{2,3})*$";
        pattern = Pattern.compile( EMAIL_PATTERN );
        matcher = pattern.matcher( email );
        return matcher.matches();
    }

    //Password should contain any symbol, and be from 5 to 25 symbols long!"
    public static boolean validatePassword( String password, String passwordRepeat ) {
        final String PASSWORD_PATTERN =
                "^.{5,25}$";
        pattern = Pattern.compile( PASSWORD_PATTERN );
        matcher = pattern.matcher( password );
        return ( matcher.matches() && password.equals( passwordRepeat ) );
    }
}
