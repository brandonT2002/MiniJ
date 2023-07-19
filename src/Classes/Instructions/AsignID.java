package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeInst;
public class AsignID extends Instruction {
    private String id;
    private Expression value;
    public AsignID(int line, int column, String id, Expression value) {
        super(line, column, TypeInst.ASIGN_ID);
        this.id = id;
        this.value = value;
    }
    public void exec(Env env) {
        ReturnType value = this.value.exec(env);
        env.reasignID(id, value);
    }
}