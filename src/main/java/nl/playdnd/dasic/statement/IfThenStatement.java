package nl.playdnd.dasic.statement;

import nl.playdnd.dasic.expression.Expression;
import nl.playdnd.dasic.interpreter.Variables;

/**
     * An if then statement jumps execution to another place in the program, but
     * only if an expression evaluates to something other than 0.
     */
    public class IfThenStatement implements Statement {
        public IfThenStatement(Expression condition, String label) {
            this.condition = condition;
            this.label = label;
        }
        
        public void execute(Variables globals) {
            if (globals.getLabels().containsKey(label)) {
                double value = condition.evaluate(globals).toNumber();
                if (value != 0) {
                    globals.setCurrentStatement(globals.getLabels().get(label).intValue());
                }
            }
        }

        private final Expression condition;
        private final String label;
    }
    