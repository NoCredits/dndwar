package nl.playdnd.dasic.expression;

import nl.playdnd.dasic.interpreter.SourceCode;
import nl.playdnd.dasic.value.NumberValue;
import nl.playdnd.dasic.value.StringValue;
import nl.playdnd.dasic.value.Value;

/**
     * An operator expression evaluates two expressions and then performs some
     * arithmetic operation on the results.
     */
    public class OperatorExpression implements Expression {
        public OperatorExpression(Expression left, char operator,
                                  Expression right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }
        
        public Value evaluate(SourceCode globals) {
            Value leftVal = left.evaluate(globals);
            Value rightVal = right.evaluate(globals);
            
            switch (operator) {
            case '=':
                // Coerce to the left argument's type, then compare.
                if (leftVal instanceof NumberValue) {
                    return new NumberValue((leftVal.toNumber() ==
                                            rightVal.toNumber()) ? 1 : 0);
                } else {
                    return new NumberValue(leftVal.toString().equals(
                                           rightVal.toString()) ? 1 : 0);
                }
            case '!':
                if (leftVal instanceof NumberValue) {
                    return new NumberValue((leftVal.toNumber() ==
                                            rightVal.toNumber()) ? 0 : 1);
                } else {
                    return new NumberValue(leftVal.toString().equals(
                                           rightVal.toString()) ? 0 : 1);
                }
            case '+':
                // Addition if the left argument is a number, otherwise do
                // string concatenation.
                if (leftVal instanceof NumberValue) {
                    return new NumberValue(leftVal.toNumber() +
                                           rightVal.toNumber());
                } else {
                    return new StringValue(leftVal.toString() +
                            rightVal.toString());
                }
            case '-':
                return new NumberValue(leftVal.toNumber() -
                        rightVal.toNumber());
            case '*':
                return new NumberValue(leftVal.toNumber() *
                        rightVal.toNumber());
            case '/':
                return new NumberValue(leftVal.toNumber() /
                        rightVal.toNumber());
            case '<':
                // Coerce to the left argument's type, then compare.
                if (leftVal instanceof NumberValue) {
                    return new NumberValue((leftVal.toNumber() <
                                            rightVal.toNumber()) ? 1 : 0);
                } else {
                    return new NumberValue((leftVal.toString().compareTo(
                                           rightVal.toString()) < 0) ? 1 : 0);
                }
            case '>':
                // Coerce to the left argument's type, then compare.
                if (leftVal instanceof NumberValue) {
                    return new NumberValue((leftVal.toNumber() >
                                            rightVal.toNumber()) ? 1 : 0);
                } else {
                    return new NumberValue((leftVal.toString().compareTo(
                            rightVal.toString()) > 0) ? 1 : 0);
                }
            }
            throw new Error("Unknown operator.");
        }
        
        private final Expression left;
        private final char operator;
        private final Expression right;
    }