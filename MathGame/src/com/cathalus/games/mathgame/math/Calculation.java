package com.cathalus.games.mathgame.math;

public class Calculation {

    private Operator op;
    private Type type;
    private int a, b;

    public enum Type
    {
        FIRST_BLANK, SECOND_BLANK, THIRD_BLANK, SOLUTION;
    }

    public enum Operator
    {
        PLUS {
            public int calculate(int a, int b) {
                return a+b;
            }
            public String toString() {return "+";}
        }, MINUS {
            public int calculate(int a, int b) {
                return a-b;
        }
            public String toString() {return "-";}
    };

        public abstract int calculate(int a, int b);
        public abstract String toString();
    }

    public Calculation(Operator op, Type type, int a, int b)
    {
        this.type = type;
        this.op = op;
        this.a = a;
        this.b = b;
    }

    public int getResult()
    {
        return this.op.calculate(a,b);
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String aToString()
    {
        if(type == Type.FIRST_BLANK)
            return "_";
        return Integer.toString(a);
    }

    public String bToString()
    {
        if(type == Type.SECOND_BLANK)
            return "_";
        return Integer.toString(b);
    }

    public String resultToString()
    {
        if(type == Type.THIRD_BLANK)
            return "_";
        return Integer.toString(getResult());
    }

    public Operator getOP()
    {
        return op;
    }

    public Type getType()
    {
        return type;
    }

    @Override
    public String toString() {
        switch (type)
        {
            case FIRST_BLANK:
                return "_"+op.toString()+b+"="+getResult();
            case SECOND_BLANK:
                return a+op.toString()+"_"+"="+getResult();
            case THIRD_BLANK:
                return a+op.toString()+b+"=_";
            case SOLUTION:
                return a+op.toString()+b+getResult();
        }
        return "[ERROR] couldn't create calculation";
    }
}