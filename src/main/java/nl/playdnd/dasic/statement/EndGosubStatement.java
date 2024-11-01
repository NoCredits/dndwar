package nl.playdnd.dasic.statement;

import nl.playdnd.dasic.interpreter.SourceCode;

public class EndGosubStatement implements Statement {


       public EndGosubStatement() {
       }
        
        public void execute(SourceCode sourceCode) {
            //System.out.println(sourceCode.getCurrentStatement());
            int ret = GosubStatement.stack.pop();;
            sourceCode.setCurrentStatement(ret);
        }

    }