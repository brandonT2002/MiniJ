package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Abstracts.Sentence;
import Classes.Env.Env;
import Classes.Utils.InitializeFor;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeSent;
public class For extends Expression {
    private InitializeFor inits;
    private Expression condition;
    private ArrayList<Sentence> updates;
    private Block instructions;
    public For(int line, int column, InitializeFor inits, Expression condition, ArrayList<Sentence> updates, Block instructions) {
        super(line, column, TypeExp.LOOP_FOR);
        this.inits = inits;
        this.condition = condition;
        this.updates = updates;
        this.instructions = instructions;
    }
    public ReturnType exec(Env env) {
        Env envFor = new Env(env, env.name + " For");
        if(inits != null) {
            if(inits.inits != null) {
                inits.inits.exec(envFor);
            }
            else if(inits.asigns != null) {
                for(AsignID asign : inits.asigns) {
                    asign.exec(envFor);
                }
            }
        }
        ReturnType condition = this.condition != null ? this.condition.exec(envFor) : new ReturnType(true, Type.BOOLEAN);
        while(Boolean.parseBoolean(condition.value.toString())) {
            ReturnType block = instructions.exec(envFor);
            if(block != null) {
                if(block.value == TypeExp.CONTINUE) {
                    for(Sentence update : updates) {
                        if(update.typeSent == TypeSent.EXPRESSION) {
                            ((Expression) update).exec(envFor);
                        }
                        else if(update.typeSent == TypeSent.INSTRUCTION) {
                            ((Instruction) update).exec(envFor);
                        }
                    }
                    condition = this.condition != null ? this.condition.exec(envFor) : new ReturnType(true, Type.BOOLEAN);
                }
                else if(block.value == TypeExp.BREAK) {
                    break;
                }
                return block;
            }
            for(Sentence update : updates) {
                if(update.typeSent == TypeSent.EXPRESSION) {
                    ((Expression) update).exec(envFor);
                }
                else if(update.typeSent == TypeSent.INSTRUCTION) {
                    ((Instruction) update).exec(envFor);
                }
            }
            condition = this.condition != null ? this.condition.exec(envFor) : new ReturnType(true, Type.BOOLEAN);
        }
        return null;
    }
}