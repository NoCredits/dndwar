package nl.playdnd.dasic.statement;
import java.io.IOException;

import nl.playdnd.dasic.interpreter.Variables;
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
        
        public void execute(Variables globals) {
            try {
                String input = globals.getLineIn().readLine();
                
                // Store it as a number if possible, otherwise use a string.
                try {
                    double value = Double.parseDouble(input);
                    globals.getVariables().put(name, new NumberValue(value));
                } catch (NumberFormatException e) {
                    globals.getVariables().put(name, new StringValue(input));
                }
            } catch (IOException e1) {
                // HACK: Just ignore the problem.
            }
        }

        private final String name;
    }
