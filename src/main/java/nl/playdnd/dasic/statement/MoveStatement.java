package nl.playdnd.dasic.statement;

import nl.playdnd.dasic.expression.Expression;
import nl.playdnd.dasic.interpreter.SourceCode;

/**
     * A "print" statement evaluates an expression, converts the result to a
     * string, and displays it to the user.
     */
    public class MoveStatement implements Statement {
        public MoveStatement(Expression expression) {
            this.expression = expression;
        }
        
        public void execute(SourceCode sourceCode) {
            System.out.println(expression.evaluate(sourceCode).toString());
        }

        private final Expression expression;
    }