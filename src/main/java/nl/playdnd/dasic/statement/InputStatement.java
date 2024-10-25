package nl.playdnd.dasic.statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import nl.playdnd.dasic.interpreter.SourceCode;
import nl.playdnd.dasic.value.NumberValue;
import nl.playdnd.dasic.value.StringValue;

 /**
     * An "input" statement reads input from the user and stores it in a
     * variable.
     */
    public class InputStatement implements Statement {
        public InputStatement(String name) {
            this.name = name;
        }
        
        public void execute(SourceCode sourceCode) {
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader lineIn = new BufferedReader(converter);

            try {
               // String input = sourceCode.getLineIn().readLine();
               String input = lineIn.readLine();
                
                // Store it as a number if possible, otherwise use a string.
                try {
                    double value = Double.parseDouble(input);
                    sourceCode.getVariables().put(name, new NumberValue(value));
                } catch (NumberFormatException e) {
                    sourceCode.getVariables().put(name, new StringValue(input));
                }
            } catch (IOException e1) {
                // HACK: Just ignore the problem.
            }
        }

        private final String name;
    }
