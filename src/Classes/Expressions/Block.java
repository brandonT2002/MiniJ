package Classes.Expressions;

import java.util.ArrayList;

import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Abstracts.Sentence;
import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
import Classes.Utils.TypeSent;

public class Block extends Expression {
    ArrayList<Sentence> instructions;

    public Block(int line, int column, ArrayList<Sentence> instructions) {
        super(line, column, TypeExp.BLOCK_INST);
        this.instructions = instructions;
    }

    public ReturnType exec(Env env) {
        Env newEnv = new Env(env, env.name);
        Expression exp;
        Instruction inst;
        ReturnType ret;
        for (Sentence instruction : instructions) {
            if (instruction.typeSent == TypeSent.EXPRESSION) {
                exp = (Expression) instruction;
                ret = exp.exec(newEnv);
                if (ret != null && ret.type != Type.NULL && exp.typeExp != TypeExp.RETURN) {
                    return ret;
                }
            } else if (instruction.typeSent == TypeSent.INSTRUCTION) {
                inst = (Instruction) instruction;
                inst.exec(newEnv);
            }
        }
        return new ReturnType("NULL", Type.NULL);
    }
}
