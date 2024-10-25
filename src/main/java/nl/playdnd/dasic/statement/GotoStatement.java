package nl.playdnd.dasic.statement;

import nl.playdnd.dasic.interpreter.Variables;

/**
     * A "goto" statement jumps execution to another place in the program.
     */
    public class GotoStatement implements Statement {
        public GotoStatement(String label) {
            this.label = label;
        }
        
        public void execute(Variables globals) {
            if (globals.getLabels().containsKey(label)) {
                globals.setCurrentStatement(globals.getLabels().get(label).intValue());
            }
        }

        private final String label;

    }