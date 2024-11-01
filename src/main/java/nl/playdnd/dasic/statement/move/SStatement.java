package nl.playdnd.dasic.statement.move;

import nl.playdnd.character.InlineValues;
import nl.playdnd.dasic.expression.Expression;
import nl.playdnd.dasic.interpreter.SourceCode;
import nl.playdnd.dasic.statement.Statement;
import nl.playdnd.dasic.value.NumberValue;
import nl.playdnd.dasic.value.Value;
import nl.playdnd.global.FaceTo;



public class SStatement implements Statement {

    
    public SStatement(Expression expression) {
        this.expression = expression;
    }

    public SStatement() {
        this.expression = new Expression() {

            @Override
            public Value evaluate(SourceCode globals) {
                return new NumberValue((int) 1);
            }
        };
    }

    @Override
    public void execute(SourceCode sourceCode) {
        InlineValues stats = sourceCode.getStats();
        
        stats.faceTo(FaceTo.SOUTH);
        System.out.println(stats.getName() + " faces "+stats.getFaceTo()+" moves "+expression.evaluate(sourceCode).toString() );
        stats.move((int) (double) Double.valueOf(expression.evaluate(sourceCode).toString()));

    }

    
    private final Expression expression;

}