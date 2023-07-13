package Classes.Expressions;

import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Utils.Operations;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;

public class Arithmetic extends Expression {
    private Type type;
    private Expression exp1;
    private Expression exp2;
    private String sign;

    public Arithmetic(int line, int column, Expression exp1, String sign, Expression exp2) {
        super(line, column, TypeExp.ARITHMETIC_OP);
        this.exp1 = exp1;
        this.sign = sign;
        this.exp2 = exp2;
        this.type = Type.NULL;
    }

    public ReturnType exec(Env env) {
        switch (this.sign) {
            case "+":
                return plus(env);
            case "-":
                if (exp1 != null) {
                    return minus(env);
                }
                return uminus(env);
            case "*":
                return pow(env);
            case "/":
                return div(env);
            case "^":
                return pow(env);
            case "%":
                return mod(env);
            default:
                return new ReturnType(-1, Type.NULL);
        }
    }

    public ReturnType plus(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.plus[t1][t2] : Type.NULL;
        if (type != Type.NULL) {
            if (type == Type.INT) {
                int result = Integer.parseInt(getValue(value1).value.toString())
                        + Integer.parseInt(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if (type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString())
                        + Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if (type == Type.STRING) {
                String result = value1.value.toString() + value2.value.toString();
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }

    public ReturnType minus(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.minus[t1][t2] : Type.NULL;
        if (type != Type.NULL) {
            if (type == Type.INT) {
                int result = Integer.parseInt(getValue(value1).value.toString())
                        - Integer.parseInt(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if (type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString())
                        - Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }

    public ReturnType uminus(Env env) {
        ReturnType value2 = exp2.exec(env);
        type = value2.type;
        if (type != Type.NULL) {
            if (type == Type.INT) {
                int result = -Integer.parseInt(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if (type == Type.DOUBLE) {
                double result = -Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }

    public ReturnType mult(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.mult[t1][t2] : Type.NULL;
        if (type != Type.NULL) {
            if (type == Type.INT) {
                int result = Integer.parseInt(getValue(value1).value.toString())
                        * Integer.parseInt(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
            if (type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString())
                        * Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }

    public ReturnType div(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.div[t1][t2] : Type.NULL;
        if (type != Type.NULL) {
            if (type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString())
                        / Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }

    public ReturnType pow(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.pow[t1][t2] : Type.NULL;
        if (type != Type.NULL) {
            if (type == Type.INT) {
                int result = (int) Math.pow(Integer.parseInt(getValue(value1).value.toString()),
                        Integer.parseInt(getValue(value2).value.toString()));
                return new ReturnType(result, type);
            }
            if (type == Type.DOUBLE) {
                double result = Math.pow(Double.parseDouble(getValue(value1).value.toString()),
                        Double.parseDouble(getValue(value2).value.toString()));
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }

    public ReturnType mod(Env env) {
        ReturnType value1 = exp1.exec(env);
        ReturnType value2 = exp2.exec(env);
        int t1 = getType(value1.type);
        int t2 = getType(value2.type);
        type = !(t1 == 5 || t2 == 5) ? Operations.mod[t1][t2] : Type.NULL;
        if (type != Type.NULL) {
            if (type == Type.DOUBLE) {
                double result = Double.parseDouble(getValue(value1).value.toString())
                        % Double.parseDouble(getValue(value2).value.toString());
                return new ReturnType(result, type);
            }
        }
        return new ReturnType(-1, type);
    }

    public int getType(Type type) {
        if (type == Type.INT)
            return 0;
        if (type == Type.DOUBLE)
            return 1;
        if (type == Type.BOOLEAN)
            return 2;
        if (type == Type.CHAR)
            return 3;
        if (type == Type.STRING)
            return 4;
        return 5;
    }

    public ReturnType getValue(ReturnType value) {
        if (value.type == Type.BOOLEAN) {
            if (value.value.toString().equals("true")) {
                return new ReturnType(1, Type.INT);
            }
            return new ReturnType(0, Type.INT);
        }
        if (value.type == Type.CHAR) {
            return new ReturnType((int) value.value.toString().charAt(0), Type.INT);
        }
        return value;
    }
}
