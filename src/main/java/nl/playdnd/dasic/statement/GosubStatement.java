package nl.playdnd.dasic.statement;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

import nl.playdnd.dasic.interpreter.SourceCode;


    public class GosubStatement implements Statement {

        
        public static  Stack<Integer> stack = new Stack<>();

       public GosubStatement(String label) {
            this.label = label;
        }
        
        public void execute(SourceCode sourceCode) {
            
            stack.push(sourceCode.getCurrentStatement());

            if (sourceCode.getLabels().containsKey(label)) {
                sourceCode.setCurrentStatement(sourceCode.getLabels().get(label).intValue());
            }
        }

        private final String label;

    }