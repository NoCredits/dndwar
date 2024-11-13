package nl.playdnd.dasic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.arena.ArenaGUI;
import nl.playdnd.character.DnDEntityMoveable;
import nl.playdnd.character.InlineValues;
import nl.playdnd.character.DnDCharacter;
import nl.playdnd.character.Vars;
import nl.playdnd.dasic.interpreter.Interpreter;
import nl.playdnd.dasic.interpreter.SourceCode;

/**
 * This defines a single class that contains an entire interpreter for a
 * language very similar to the original BASIC. Everything is here (albeit in
 * very simplified form): tokenizing, parsing, and interpretation. The file is
 * organized in phases, with each appearing roughly in the order that they
 * occur when a program is run. You should be able to read this top-down to walk
 * through the entire process of loading and running a program.
 * 
 * Jasic language syntax
 * ---------------------
 * 
 * Comments start with ' and proceed to the end of the line:
 * 
 * print "hi there" ' this is a comment
 * 
 * Numbers and strings are supported. Strings should be in "double quotes", and
 * only positive integers can be parsed (though numbers are double internally).
 * 
 * Variables are identified by name which must start with a letter and can
 * contain letters or numbers. Case is significant for names and keywords.
 * 
 * Each statement is on its own line. Optionally, a line may have a label before
 * the statement. A label is a name that ends with a colon:
 * 
 * foo:
 * 
 * 
 * The following statements are supported:
 * 
 * <name> = <expression>
 * Evaluates the expression and assigns the result to the given named
 * variable. All variables are globally scoped.
 * 
 * pi = (314159 / 10000)
 * 
 * print <expression>
 * Evaluates the expression and prints the result.
 * 
 * print "hello, " + "world"
 * 
 * input <name>
 * Reads in a line of input from the user and stores it in the variable with
 * the given name.
 * 
 * input guess
 * 
 * goto <label>
 * Jumps to the statement after the label with the given name.
 * 
 * goto loop
 * 
 * if <expression> then <label>
 * Evaluates the expression. If it evaluates to a non-zero number, then
 * jumps to the statement after the given label.
 * 
 * if a < b then dosomething
 * 
 * 
 * The following expressions are supported:
 * 
 * <expression> = <expression>
 * Evaluates to 1 if the two expressions are equal, 0 otherwise.
 * 
 * <expression> + <expression>
 * If the left-hand expression is a number, then adds the two expressions,
 * otherwise concatenates the two strings.
 * 
 * <expression> - <expression>
 * <expression> * <expression>
 * <expression> / <expression>
 * <expression> < <expression>
 * <expression> > <expression>
 * You can figure it out.
 * 
 * <name>
 * A name in an expression simply returns the value of the variable with
 * that name. If the variable was never set, it defaults to 0.
 * 
 * All binary operators have the same precedence. Sorry, I had to cut corners
 * somewhere.
 * 
 * To keep things simple, I've omitted some stuff or hacked things a bit. When
 * possible, I'll leave a "HACK" note there explaining what and why. If you
 * make your own interpreter, you'll want to address those.
 * 
 * @author Bob Nystrom
 */

@Getter
@Setter
public class DasicAI {

    private SourceCode sourceCode;

    /**
     * Constructs a new Dasic instance. The instance stores the global state of
     * the interpreter such as the values of all of the variables and the
     * current statement.
     */
    public DasicAI(DnDCharacter character) {
        sourceCode = new SourceCode(character);
        sourceCode.setStats(character.stats);
    }

    public DasicAI(String file, DnDCharacter character) {
        sourceCode = new SourceCode(character,readFile(file));
        sourceCode.setStats(character.stats);
    }

    public void interpret(DnDCharacter character) {
        Interpreter.interpret(character, sourceCode);
    }

    public void interpret(DnDCharacter character, String source) {
        sourceCode.setSource(source);
        Interpreter.interpret(character, sourceCode);

    }

    // Utility stuff -----------------------------------------------------------

    /**
     * Reads the file from the given path and returns its contents as a single
     * string.
     * 
     * @param path Path to the text file to read.
     * @return The contents of the file or null if the load failed.
     * @throws IOException
     */
    private static String readFile(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);

            try {
                InputStreamReader input = new InputStreamReader(stream,
                        Charset.defaultCharset());
                Reader reader = new BufferedReader(input);

                StringBuilder builder = new StringBuilder();
                char[] buffer = new char[8192];
                int read;

                while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                    builder.append(buffer, 0, read);
                }

                // HACK: The parser expects every statement to end in a newline,
                // even the very last one, so we'll just tack one on here in
                // case the file doesn't have one.
                builder.append("\n");

                return builder.toString();
                // return a;
            } finally {
                stream.close();
            }
        } catch (IOException ex) {
            return null;
        }
    }

}
