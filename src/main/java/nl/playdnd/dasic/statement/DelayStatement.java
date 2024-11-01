package nl.playdnd.dasic.statement;


import nl.playdnd.character.InlineValues;
import nl.playdnd.dasic.expression.Expression;
import nl.playdnd.dasic.interpreter.SourceCode;
import nl.playdnd.global.FaceTo;



public class DelayStatement implements Statement {
    public DelayStatement(String text) {
        this.delay = Double.valueOf(text);
    }

    @Override
    public void execute(SourceCode sourceCode) {
        InlineValues stats = sourceCode.getStats();

        System.out.println("waiting "+delay  );

        nl.playdnd.global.Util.sleep((int) (double)(delay));


    }

    private final Double delay;
}