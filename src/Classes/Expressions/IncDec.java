package Classes.Expressions;

import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Env.Symbol;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;

public class IncDec extends Expression {
    private String id;
    private String sign;

    public IncDec(int line, int column, String id, String sign) {
        super(line, column, sign.equals("++") ? TypeExp.INC : TypeExp.DEC);
        this.id = id;
        this.sign = sign;
    }

    public ReturnType exec(Env env) {
        Symbol value = env.getValueID(id);
        if (value != null) {
            int v;
            switch (this.sign) {
                case "++":
                    v = Integer.parseInt(value.value.toString()) + 1;
                    env.reasignID(id, new ReturnType(v, Type.INT));
                    return new ReturnType(v, Type.INT);
                case "--":
                    v = Integer.parseInt(value.value.toString()) - 1;
                    env.reasignID(id, new ReturnType(v, Type.INT));
                    return new ReturnType(v, Type.INT);
            }
        }
        return new ReturnType(-1, Type.NULL);
    }
}