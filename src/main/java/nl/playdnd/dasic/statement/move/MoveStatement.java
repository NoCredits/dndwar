package nl.playdnd.dasic.statement.move;

import nl.playdnd.character.InlineValues;
import nl.playdnd.dasic.expression.Expression;
import nl.playdnd.dasic.interpreter.SourceCode;
import nl.playdnd.dasic.statement.Statement;
import nl.playdnd.global.FaceTo;



public class MoveStatement implements Statement {

    
    public MoveStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute(SourceCode sourceCode) {
        InlineValues stats = sourceCode.getStats();
        System.out.println(stats.getName() + " moves "+expression.evaluate(sourceCode).toString() );

        stats.move((int) (double) Double.valueOf(expression.evaluate(sourceCode).toString()));

    }

    
    private final Expression expression;

}