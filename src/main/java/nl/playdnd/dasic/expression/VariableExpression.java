package nl.playdnd.dasic.expression;

import nl.playdnd.dasic.interpreter.SourceCode;
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
        
        public Value evaluate(SourceCode sourceCode) {
            if (sourceCode.getVariables().containsKey(name)) {
                return sourceCode.getVariables().get(name);
            }
            return new NumberValue(0);
        }
        
        private final String name;
    }