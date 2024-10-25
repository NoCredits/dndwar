package nl.playdnd.dasic.expression;

import nl.playdnd.dasic.interpreter.Variables;
import nl.playdnd.dasic.value.NumberValue;
import nl.playdnd.dasic.value.Value;

/**
     * A variable expression evaluates to the current value stored in that
     * variable.
     */
    public class VariableExpression implements Expression {
        public VariableExpression(String name) {
            this.name = name;
        }
        
        public Value evaluate(Variables globals) {
            if (globals.getVariables().containsKey(name)) {
                return globals.getVariables().get(name);
            }
            return new NumberValue(0);
        }
        
        private final String name;
    }