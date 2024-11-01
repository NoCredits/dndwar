package nl.playdnd.dasic.statement;

import nl.playdnd.character.InlineValues;
import nl.playdnd.dasic.expression.Expression;
import nl.playdnd.dasic.interpreter.SourceCode;
import nl.playdnd.global.FaceTo;



public class FaceToStatement implements Statement {
    public FaceToStatement(String text) {
        this.face = text;
    }

    @Override
    public void execute(SourceCode sourceCode) {
        InlineValues stats = sourceCode.getStats();

        
        System.out.println("FACE FACE "+face  );
       stats.setFaceTo(FaceTo.getFace(face));
    }

    private final String face;
}