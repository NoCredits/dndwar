package nl.playdnd.dasic.statement.move;

import nl.playdnd.character.InlineValues;
import nl.playdnd.dasic.expression.Expression;
import nl.playdnd.dasic.interpreter.SourceCode;
import nl.playdnd.dasic.statement.Statement;
import nl.playdnd.global.FaceTo;



public class FaceToStatement implements Statement {

    public FaceToStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute(SourceCode sourceCode) {
        InlineValues stats = sourceCode.getStats();

        System.out.println("setFaceTo "+expression.evaluate(sourceCode).toString()  );
       stats.setFaceTo(FaceTo.getFace(expression.evaluate(sourceCode).toString()));
    }

    private final Expression expression;
}