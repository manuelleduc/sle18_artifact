package evalsimpleal.revisitor.operations;

import evalsimpleal.revisitor.operations.ArithOperation;
import simpleALEnv.runtime.Env;

@SuppressWarnings("all")
public interface ALVarRefOperation extends ArithOperation {
  public abstract int eval(final Env env);
}
