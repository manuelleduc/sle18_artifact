package tfsmprinting.revisitor.operations.tfsmprinting;

import fsmprinting.revisitor.operations.fsmprinting.TransitionOperation;

@SuppressWarnings("all")
public interface TimedTransitionOperation extends TransitionOperation, tfsmprinting.revisitor.operations.tfsmprinting.TransitionOperation {
  public abstract String print();
}
