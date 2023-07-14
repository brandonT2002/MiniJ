package Classes.Expressions;

import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Utils.Operations;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;

public class AddSub extends Instruction {
    private String id;
    private String sign;
    private Expression exp;
    private Type type;

    public AddSub(int line, int column, String id, String sign, Expression exp) {
        super(line, column, sign.equals("+=") ? TypeInst.ADD : TypeInst.SUB);
        this.id = id;
        this.sign = sign;
        this.exp = exp;
    }

    public void exec(Env env) {
        Symbol value = env.getValueID(id);
        if (value != null) {
            ReturnType value2 = exp.exec(env);
            int t1 = getType(value.type);
            int t2 = getType(value2.type);
            type = !(t1 == 5 || t2 == 5) ? Operations.mod[t1][t2] : Type.NULL;
            if (type != Type.NULL) {
                switch (this.sign) {
                    case "+=":
                        if (type == Type.INT) {
                            int result = Integer
                                    .parseInt(getValue(new ReturnType(value.value, value.type)).value.toString())
                                    + Integer.parseInt(getValue(value2).value.toString());
                            env.reasignID(id, new ReturnType(result, Type.INT));
                        }
                        if (type == Type.DOUBLE) {
                            double result = Double
                                    .parseDouble(getValue(new ReturnType(value.value, value.type)).value.toString())
                                    + Double.parseDouble(getValue(value2).value.toString());
                            env.reasignID(id, new ReturnType(result, Type.INT));
                        }
                        if (type == Type.STRING) {
                            String result = value.value.toString() + value2.value.toString();
                            env.reasignID(id, new ReturnType(result, Type.INT));
                        }
                    case "-=":
                        if (type == Type.INT) {
                            int result = Integer
                                    .parseInt(getValue(new ReturnType(value.value, value.type)).value.toString())
                                    - Integer.parseInt(getValue(value2).value.toString());
                            env.reasignID(id, new ReturnType(result, Type.INT));
                        }
                        if (type == Type.DOUBLE) {
                            double result = Double
                                    .parseDouble(getValue(new ReturnType(value.value, value.type)).value.toString())
                                    - Double.parseDouble(getValue(value2).value.toString());
                            env.reasignID(id, new ReturnType(result, Type.INT));
                        }
                }
            }
        }
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