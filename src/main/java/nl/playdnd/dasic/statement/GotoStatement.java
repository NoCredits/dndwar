package nl.playdnd.dasic.statement;

import nl.playdnd.dasic.interpreter.SourceCode;

/**
     * A "goto" statement jumps execution to another place in the program.
     */
    public class GotoStatement implements Statement {
        public GotoStatement(String label) {
            this.label = label;
        }
        
        public void execute(SourceCode sourceCode) {
            if (sourceCode.getLabels().containsKey(label)) {
                sourceCode.setCurrentStatement(sourceCode.getLabels().get(label).intValue());
            }
        }

        private final String label;

    }