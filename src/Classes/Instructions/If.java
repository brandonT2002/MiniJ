package Classes.Instructions;

import Classes.Abstracts.Expression;
import Classes.Abstracts.Sentence;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;

public class If extends Expression{
    Expression condition;
    Block block;
    Sentence except;
    public If(int line, int column, Expression condition, Block block, Sentence except){
        super(line, column, TypeExp.IF);
        this.condition = condition;
        this.block = block;
        this.except = except;
    }

    public ReturnType exec(Env env){
        ReturnType condition = this.condition.exec(env);
        if (Boolean.parseBoolean(condition.value.toString())){ // if(condicion)
            ReturnType block = this.block.exec(env);           //     instrucciones
            if (block.type != Type.NULL) {
                return block;
            }
            return new ReturnType("NULL", Type.NULL);
        }
        // else
        if (except != null) {
            ReturnType except = ((Expression) this.except).exec(env); // if | instrucciones_else
            if (except.type != Type.NULL){
                return except;
            }
        }
        return new ReturnType("NULL", Type.NULL);
    }
}
