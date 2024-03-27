// DISCLAIMER: I DO NOT SUPPORT PEOPLE PLAGIARIZING MY CODE. I DO NOT TAKE RESPONSIBILITY FOR THE UNLAWFUL ACTIONS OF OTHERS.

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class parses any text file into its tokenised form (consult hashmap in the Tokenize method
 * see specific keywords).
 */
public class Lexer {

    private static class InvalidIdentifierNameException extends Exception {

        public InvalidIdentifierNameException(String errorMessage) {
            System.out.println(errorMessage);
        }
    }

    private static class InvalidSpecialCharacterException extends Exception {

        public InvalidSpecialCharacterException(String errorMessage) {
            System.out.println(errorMessage);
        }
    }

    static HashMap<String, String> symbols = new HashMap<>();

    /**
     * This method parses a text file and prints its tokenised form into the standard output.
     *
     * @param fileName - file directory wanting to be parsed
     * @throws InvalidIdentifierNameException
     * @throws InvalidSpecialCharacterException
     */
    public static void Tokenize(String fileName) {
        symbols.put("if", "IF");
        symbols.put("for", "FOR");
        symbols.put("while", "WHILE");
        symbols.put("procedure", "PROC");
        symbols.put("return", "RETURN");
        symbols.put("int", "INT");
        symbols.put("else", "ELSE");
        symbols.put("do", "DO");
        symbols.put("break", "BREAK");
        symbols.put("end", "END");

        symbols.put("=", "ASSIGN");
        symbols.put("+", "ADD_OP");
        symbols.put("-", "SUB_OP");
        symbols.put("*", "MUL_OP");
        symbols.put("/", "DIV_OP");
        symbols.put("%", "MOD_OP");
        symbols.put(">", "GT");
        symbols.put("<", "LT");
        symbols.put(">=", "GE");
        symbols.put("<=", "LE");

        symbols.put("++", "INC");
        symbols.put("(", "LP");
        symbols.put(")", "RP");
        symbols.put("{", "LB");
        symbols.put("}", "RB");
        symbols.put("|", "OR");
        symbols.put("&", "AND");
        symbols.put("==", "EE");
        symbols.put("!", "NEG");
        symbols.put(",", "COMMA");
        symbols.put(";", "SEMI");
        try (Scanner scan = new Scanner(new FileReader(fileName)).useDelimiter("")) {
            String file = "";
            while (scan.hasNext()) {
                file += scan.next();
            }
            file += " ";
            Scanner scan2 = new Scanner(file).useDelimiter("");
            String lex = "";
            String peek = "";
            String peek2 = "";
            if (scan2.hasNext()) {
                peek2 = scan2.next();
            }
            while (scan2.hasNext() || peek != null) {

                peek = peek2;
                try {
                    peek2 = scan2.next();
                } catch (NoSuchElementException e) {
                    //pretty much copied and pasted everything except for the peek + peek2 check since peek2 is null
                    if (!Character.isDigit(peek.charAt(0)) && !Character.isLetter(peek.charAt(0))) {//if special symbol
                        if (!lex.isEmpty()) {//if lex actually has something
                            if (symbols.containsKey(lex)) {//checks if lex string is keyword
                                System.out.println(symbols.get(lex));
                            } else if (isNumeric(lex)) {//checks if lex is a constant
                                System.out.println("INT_CONST");
                            } else {//lex must then be an idenfier
                                if (Character.isDigit(lex.charAt(0))) {//if invalid identifer
                                    throw new InvalidIdentifierNameException("SYNTAX ERROR: INVALID IDENTIFIER NAME");
                                } else {
                                    System.out.println("IDENT");
                                }
                            }
                            lex = "";//empties lex

                        } else if (symbols.containsKey(peek)) {//if special character is in hashmap
                            System.out.println(symbols.get(peek));
                        } else if (Character.isWhitespace(peek.charAt(0))) {
                            //just go forward
                        } else {
                            throw new InvalidSpecialCharacterException("SYNTAX ERROR: INVALID SPECIAL CHARACTER");
                        }
                    } else {//letter or number
                        lex += peek;
                        //System.out.println("LEX: " +lex);
                    }
                    break;
                }

                //System.out.println("PEEK: " + peek);
                if (!Character.isDigit(peek.charAt(0)) && !Character.isLetter(peek.charAt(0))) {//if special symbol
                    if (!lex.isEmpty()) {//if lex actually has something
                        if (symbols.containsKey(lex)) {//checks if lex string is keyword
                            System.out.println(symbols.get(lex));
                        } else if (isNumeric(lex)) {//checks if lex is a constant
                            System.out.println("INT_CONST");
                        } else {//lex must then be an idenfier
                            if (Character.isDigit(lex.charAt(0))) {//if invalid identifer
                                throw new InvalidIdentifierNameException("SYNTAX ERROR: INVALID IDENTIFIER NAME");
                            } else {
                                System.out.println("IDENT");
                            }
                        }
                        lex = "";//empties lex
                    }
                    if (symbols.containsKey(peek + peek2)) {
                        System.out.println(symbols.get(peek + peek2));
                        if (scan2.hasNext()) {
                            peek2 = scan2.next();
                        }

                    } else if (symbols.containsKey(peek)) {//if special character is in hashmap
                        System.out.println(symbols.get(peek));
                    } else if (Character.isWhitespace(peek.charAt(0))) {
                        //just go forward
                    } else {
                        throw new InvalidSpecialCharacterException("SYNTAX ERROR: INVALID SPECIAL CHARACTER");
                    }
                } else {//letter or number
                    lex += peek;
                    //System.out.println("LEX: " +lex);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (InvalidIdentifierNameException ex) {
        } catch (InvalidSpecialCharacterException ex) {
        }
    }

    /**
     * This method determins whether a string is a number or not.
     *
     * @param strNum - string wanting to be analysed
     * @return boolean - whether the number is numeric or not
     * @throws NumberFormatException
     */
    public static boolean isNumeric(String strNum) {
        try {
            int i = Integer.parseInt(strNum);//assuming int per instructions
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
