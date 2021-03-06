/**
 * generated by Xtext 2.12.0
 */
package ale.xtext.tests;

import ale.xtext.ale.AleRoot;
import ale.xtext.tests.AleInjectorProvider;
import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(AleInjectorProvider.class)
@SuppressWarnings("all")
public class AleParsingTest {
  @Inject
  private ParseHelper<AleRoot> parseHelper;
  
  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("behavior fsmprinting");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import ecore \"platform:/resource/minifsm/model/MiniFsm.ecore\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("open class Machine {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def String print() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return obj.states.map[s|alg.$(s).print()].join(\"\\n\")");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("open class State {");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def String print() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"State \" + obj.name + \"\\n\" + obj.out.map[t|\"\\t\" + alg.$(t).print()].join(\"\\n\")");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("open class FinalState {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("override String print() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"* \" + alg.state(obj).print();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("open class Transition {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def String print() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return obj.event + \" => \" + obj.tgt.name");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      final AleRoot result = this.parseHelper.parse(_builder);
      Assert.assertNotNull(result);
      Assert.assertTrue(result.eResource().getErrors().isEmpty());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
