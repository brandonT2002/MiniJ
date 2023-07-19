package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Utils.IDValue;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class InitID extends Instruction {
    ArrayList<IDValue> inits;
    Type type;
    public InitID(int line, int column, ArrayList<IDValue> inits, Type type) {
        super(line, column, TypeInst.INIT_ID);
        this.inits = inits;
        this.type = type;
    }
    public void exec(Env env) {
        for(IDValue idvalue : inits) {
            if(idvalue.value != null) {
                ReturnType value = idvalue.value.exec(env);
                env.saveID(idvalue.id, value.value, type, line, column);
            }
            else {
                switch(type) {
                    case INT:
                        env.saveID(idvalue.id, 0, type, line, column);
                        break;
                    case DOUBLE:
                        env.saveID(idvalue.id, 0.0, type, line, column);
                        break;
                    case BOOLEAN:
                        env.saveID(idvalue.id, true, type, line, column);
                        break;
                    case CHAR:
                        env.saveID(idvalue.id, '0', type, line, column);
                        break;
                    case STRING:
                        env.saveID(idvalue.id, "", type, line, column);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}