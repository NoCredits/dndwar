package nl.playdnd.dasic.interpreter;

import java.util.*;

import nl.playdnd.dasic.statement.Statement;
import nl.playdnd.dasic.token.Token;
import nl.playdnd.dasic.token.TokenType;
import nl.playdnd.dasic.token.TokenizeState;
import nl.playdnd.player.Vars;

public class Interpreter {

    /**
     * This is where the magic happens. This runs the code through the parsing
     * pipeline to generate the AST. Then it executes each statement. It keeps
     * track of the current line in a member variable that the statement objects
     * have access to. This lets "goto" and "if then" do flow control by simply
     * setting the index of the current statement.
     *
     * In an interpreter that didn't mix the interpretation logic in with the
     * AST node classes, this would be doing a lot more work.
     * 
     * @param source A string containing the source code of a .jas script to
     *               interpret.
     */

    Vars vars;

    public void interpret(Variables globals) {

        this.vars = vars;
        // Tokenize.
        List<Token> tokens = tokenize(globals.getSource());

        // Parse.
        Parser parser = new Parser(tokens);
        List<Statement> statements = parser.parse(globals.getLabels());

        // Interpret until we're done.
        globals.setCurrentStatement(0);
        while (globals.getCurrentStatement() < statements.size()) {
            int thisStatement = globals.getCurrentStatement();
            globals.setCurrentStatement(globals.getCurrentStatement() + 1);
            statements.get(thisStatement).execute(globals);
        }
    }

    // Tokenizing (lexing) -----------------------------------------------------

    /**
     * This function takes a script as a string of characters and chunks it into
     * a sequence of tokens. Each token is a meaningful unit of program, like a
     * variable name, a number, a string, or an operator.
     */
    private static List<Token> tokenize(String source) {
        List<Token> tokens = new ArrayList<Token>();

        String token = "";
        TokenizeState state = TokenizeState.DEFAULT;

        // Many tokens are a single character, like operators and ().
        String charTokens = "\n=+-*/<>(){}";
        TokenType[] tokenTypes = { TokenType.LINE, TokenType.EQUALS,
                TokenType.OPERATOR, TokenType.OPERATOR, TokenType.OPERATOR,
                TokenType.OPERATOR, TokenType.OPERATOR, TokenType.OPERATOR,
                TokenType.LEFT_PAREN, TokenType.RIGHT_PAREN,
                TokenType.LEFT_CURLYBRACE, TokenType.RIGHT_CURLYBRACE
        };

        // Scan through the code one character at a time, building up the list
        // of tokens.
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (state) {
                case DEFAULT:
                    if (charTokens.indexOf(c) != -1) {
                        tokens.add(new Token(Character.toString(c),
                                tokenTypes[charTokens.indexOf(c)]));
                    } else if (Character.isLetter(c)) {
                        token += c;
                        state = TokenizeState.WORD;
                    } else if (Character.isDigit(c)) {
                        token += c;
                        state = TokenizeState.NUMBER;
                    } else if (c == '"') {
                        state = TokenizeState.STRING;
                    } else if (c == '\'') {
                        state = TokenizeState.COMMENT;
                    }
                    break;

                case WORD:
                    if (Character.isLetterOrDigit(c)) {
                        token += c;
                    } else if (c == ':') {
                        tokens.add(new Token(token, TokenType.LABEL));
                        token = "";
                        state = TokenizeState.DEFAULT;
                    } else {
                        tokens.add(new Token(token, TokenType.WORD));
                        token = "";
                        state = TokenizeState.DEFAULT;
                        i--; // Reprocess this character in the default state.
                    }
                    break;

                case NUMBER:
                    // HACK: Negative numbers and floating points aren't supported.
                    // To get a negative number, just do 0 - <your number>.
                    // To get a floating point, divide.
                    if (Character.isDigit(c)) {
                        token += c;
                    } else {
                        tokens.add(new Token(token, TokenType.NUMBER));
                        token = "";
                        state = TokenizeState.DEFAULT;
                        i--; // Reprocess this character in the default state.
                    }
                    break;

                case STRING:
                    if (c == '"') {
                        tokens.add(new Token(token, TokenType.STRING));
                        token = "";
                        state = TokenizeState.DEFAULT;
                    } else {
                        token += c;
                    }
                    break;

                case COMMENT:
                    if (c == '\n') {
                        state = TokenizeState.DEFAULT;
                    }
                    break;
            }
        }

        // HACK: Silently ignore any in-progress token when we run out of
        // characters. This means that, for example, if a script has a string
        // that's missing the closing ", it will just ditch it.
        return tokens;
    }

}
