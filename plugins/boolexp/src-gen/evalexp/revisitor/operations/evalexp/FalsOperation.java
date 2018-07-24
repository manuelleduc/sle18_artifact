package evalexp.revisitor.operations.evalexp;

import evalexp.revisitor.operations.evalexp.ExpOperation;
import evalexp.revisitor.operations.evalexp.LitOperation;

@SuppressWarnings("all")
public interface FalsOperation extends LitOperation, ExpOperation {
  public abstract boolean eval();
}
