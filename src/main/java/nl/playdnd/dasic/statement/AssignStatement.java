package nl.playdnd.dasic.statement;

import nl.playdnd.dasic.expression.Expression;
import nl.playdnd.dasic.interpreter.SourceCode;

/**
     * An assignment statement evaluates an expression and stores the result in
     * a variable.
     */
    public class AssignStatement implements Statement {
        public AssignStatement(String name, Expression value) {
            this.name = name;
            this.value = value;
        }
        
        public void execute(SourceCode sourceCode) {
            sourceCode.getVariables().put(name, value.evaluate(sourceCode));
        }

        private final String name;
        private final Expression value;
    }
    