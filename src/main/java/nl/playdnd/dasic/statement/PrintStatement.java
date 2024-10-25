package nl.playdnd.dasic.statement;

import nl.playdnd.dasic.expression.Expression;
import nl.playdnd.dasic.interpreter.Variables;

/**
     * A "print" statement evaluates an expression, converts the result to a
     * string, and displays it to the user.
     */
    public class PrintStatement implements Statement {
        public PrintStatement(Expression expression) {
            this.expression = expression;
        }
        
        public void execute(Variables globals) {
            System.out.println(expression.evaluate(globals).toString());
        }

        private final Expression expression;
    }