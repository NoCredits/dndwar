package nl.playdnd.dasic.value;

import nl.playdnd.dasic.interpreter.Variables;

/**
     * A inline value.
     */
    public class InlineValue implements Value {
        public InlineValue(String value) {
            this.value = value;
        }
        
        @Override public String toString() { return value; }
        public double toNumber() { return Double.parseDouble(value); }
        public Value evaluate(Variables globals) { return this; }

        private final String value;
    }
