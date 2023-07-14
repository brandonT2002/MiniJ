package Classes.Expressions;

import java.util.ArrayList;

import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Instructions.Function;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;

public class CallFunction extends Expression {
    String id;
    ArrayList<Expression> args;

    public CallFunction(int line, int column, String id, ArrayList<Expression> args) {
        super(line, column, TypeExp.CALL_FUNC);
        this.id = id;
        this.args = args;
    }

    public ReturnType exec(Env env) {
        Function func = env.getFunction(id);
        if (func != null) {
            Env envFunc = new Env(env.getGlobal(), "Funcion " + id);
            if (func.params.size() == args.size()) {
                for (int i = 0; i < func.params.size(); i++) {
                    ReturnType value = args.get(i).exec(env);
                    ReturnType param = func.params.get(i).exec(env);
                    if (value.type == param.type) {
                        envFunc.saveID(param.value.toString(), value.value, param.type, this.line, this.column);
                        continue;
                    }
                    // ERROR SEMATICO: NO COINCIDEN LOS TIPOS DE LOS PARAMETROS
                    return new ReturnType("ERROR", Type.NULL);
                }
                ReturnType execute = func.block.exec(envFunc);
                if (execute != null) {
                    // if ((TypeExp) execute.value == TypeExp.RETURN) {
                    // return new ReturnType("NULL", Type.NULL);
                    // }
                    // return execute;
                }
                return new ReturnType("NULL", Type.NULL);
            }
            // ERROR SEMANTICO: NO CONICIDE LA CANTIDAD DE PARAMETROS ENVIADOS CON LOS
            // RECIBIDOS
            return new ReturnType("ERROR", Type.NULL);
        }
        // ERROR SEMATICO: LA FUNCION QUE SE INITENTA LLAMAR NO ESTÁ DECLARADO
        return new ReturnType("ERROR", Type.NULL);
    }
}
