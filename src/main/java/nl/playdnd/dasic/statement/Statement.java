package nl.playdnd.dasic.statement;

import nl.playdnd.dasic.interpreter.SourceCode;

// Abstract syntax tree (AST) ----------------------------------------------

    // These classes define the syntax tree data structures. This is how code is
    // represented internally in a way that's easy for the interpreter to
    // understand.
    //
    // HACK: Unlike most real compilers or interpreters, the logic to execute
    // the code is baked directly into these classes. Typically, it would be
    // separated out so that the AST us just a static data structure.

    /**
     * Base interface for a Jasic statement. The different supported statement
     * types like "print" and "goto" implement this.
     */
    public interface Statement {
        /**
         * Statements implement this to actually perform whatever behavior the
         * statement causes. "print" statements will display text here, "goto"
         * statements will change the current statement, etc.
         */
        void execute(SourceCode sourceCode);
    }
