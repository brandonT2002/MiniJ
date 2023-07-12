package Classes.Abstracts;

import Classes.Env.Env;
import Classes.Utils.ReturnType;
import Classes.Utils.TypeInst;
import Classes.Utils.TypeSent;

public abstract class Expression extends Sentence {
    public TypeInst typeInst;

    public Expression(int line, int column, TypeInst typeInst) {
        super(line, column, TypeSent.EXPRESSION);
        this.typeInst = typeInst;
    }

    public abstract ReturnType exec(Env env);
}
