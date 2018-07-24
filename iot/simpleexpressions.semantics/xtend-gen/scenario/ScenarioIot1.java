package scenario;

import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import simpleexpressions.SEBooleanBinaryExpression;
import simpleexpressions.SEBooleanBinaryOperator;
import simpleexpressions.SEBooleanValue;
import simpleexpressions.SEBooleanVariable;
import simpleexpressions.SimpleexpressionsFactory;
import simpleexpressions_exec.revisitor.impl.Simpleexpressions_execRevisitor;

@SuppressWarnings("all")
public class ScenarioIot1 {
  public static void main(final String[] args) {
    @Extension
    final SimpleexpressionsFactory simpleExpressionFactory = SimpleexpressionsFactory.eINSTANCE;
    final Simpleexpressions_execRevisitor rev = new Simpleexpressions_execRevisitor() {
    };
    SEBooleanBinaryExpression _createSEBooleanBinaryExpression = simpleExpressionFactory.createSEBooleanBinaryExpression();
    final Procedure1<SEBooleanBinaryExpression> _function = (SEBooleanBinaryExpression it) -> {
      it.setOperator(SEBooleanBinaryOperator.AND);
      SEBooleanVariable _createSEBooleanVariable = simpleExpressionFactory.createSEBooleanVariable();
      final Procedure1<SEBooleanVariable> _function_1 = (SEBooleanVariable it_1) -> {
        SEBooleanValue _createSEBooleanValue = simpleExpressionFactory.createSEBooleanValue();
        final Procedure1<SEBooleanValue> _function_2 = (SEBooleanValue it_2) -> {
          it_2.setValue(true);
        };
        SEBooleanValue _doubleArrow = ObjectExtensions.<SEBooleanValue>operator_doubleArrow(_createSEBooleanValue, _function_2);
        it_1.setCurrentValue(_doubleArrow);
      };
      SEBooleanVariable _doubleArrow = ObjectExtensions.<SEBooleanVariable>operator_doubleArrow(_createSEBooleanVariable, _function_1);
      it.setOperand1(_doubleArrow);
      SEBooleanVariable _createSEBooleanVariable_1 = simpleExpressionFactory.createSEBooleanVariable();
      final Procedure1<SEBooleanVariable> _function_2 = (SEBooleanVariable it_1) -> {
        SEBooleanValue _createSEBooleanValue = simpleExpressionFactory.createSEBooleanValue();
        final Procedure1<SEBooleanValue> _function_3 = (SEBooleanValue it_2) -> {
          it_2.setValue(false);
        };
        SEBooleanValue _doubleArrow_1 = ObjectExtensions.<SEBooleanValue>operator_doubleArrow(_createSEBooleanValue, _function_3);
        it_1.setCurrentValue(_doubleArrow_1);
      };
      SEBooleanVariable _doubleArrow_1 = ObjectExtensions.<SEBooleanVariable>operator_doubleArrow(_createSEBooleanVariable_1, _function_2);
      it.setOperand2(_doubleArrow_1);
      SEBooleanVariable _createSEBooleanVariable_2 = simpleExpressionFactory.createSEBooleanVariable();
      final Procedure1<SEBooleanVariable> _function_3 = (SEBooleanVariable it_1) -> {
        it_1.setCurrentValue(simpleExpressionFactory.createSEBooleanValue());
        it_1.setName("scenario1");
      };
      SEBooleanVariable _doubleArrow_2 = ObjectExtensions.<SEBooleanVariable>operator_doubleArrow(_createSEBooleanVariable_2, _function_3);
      it.setAssignee(_doubleArrow_2);
    };
    final SEBooleanBinaryExpression model = ObjectExtensions.<SEBooleanBinaryExpression>operator_doubleArrow(_createSEBooleanBinaryExpression, _function);
    rev.$(model).execute();
    InputOutput.<String>println(rev.$(model.getAssignee()).print());
  }
}
