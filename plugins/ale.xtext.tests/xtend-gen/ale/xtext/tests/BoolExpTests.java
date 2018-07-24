package ale.xtext.tests;

import ale.xtext.tests.AleInjectorProvider;
import ale.xtext.tests.AleTestHelper;
import boolexp.And;
import boolexp.BoolexpFactory;
import boolexp.Or;
import com.google.inject.Inject;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(AleInjectorProvider.class)
@SuppressWarnings("all")
public class BoolExpTests {
  @Inject
  @Extension
  private AleTestHelper _aleTestHelper;
  
  private final BoolexpFactory fact = BoolexpFactory.eINSTANCE;
  
  @Before
  public void setUp() {
    EPackage.Registry.INSTANCE.put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
  }
  
  @Test
  public void testPrintRevisitor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("behavior test");
    _builder.newLine();
    _builder.append("import ecore \"../testdata/boolexp/model/BoolExp.ecore\"");
    _builder.newLine();
    _builder.append("open abstract class Exp {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("abstract def String print()");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open abstract class BinaryExp {}");
    _builder.newLine();
    _builder.append("open abstract class Lit {}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open class Tru {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override String print() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return \"T\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open class Fals {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override String print() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return \"F\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open class And {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override String print() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return alg.$(obj.lhs).print() + \" && \" + alg.$(obj.rhs).print() ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open class Or {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override String print() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return alg.$(obj.lhs).print() + \" || \" + alg.$(obj.rhs).print()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    Or _createOr = this.fact.createOr();
    final Procedure1<Or> _function = (Or it) -> {
      And _createAnd = this.fact.createAnd();
      final Procedure1<And> _function_1 = (And it_1) -> {
        it_1.setLhs(this.fact.createTru());
        it_1.setRhs(this.fact.createFals());
      };
      And _doubleArrow = ObjectExtensions.<And>operator_doubleArrow(_createAnd, _function_1);
      it.setLhs(_doubleArrow);
      it.setRhs(this.fact.createTru());
    };
    Or _doubleArrow = ObjectExtensions.<Or>operator_doubleArrow(_createOr, _function);
    this._aleTestHelper.assertEvaluatesTo(this._aleTestHelper.call(this._aleTestHelper.with(_builder, _doubleArrow), "print"), "T && F || T");
  }
  
  @Test
  public void testEvalRevisitor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("behavior test");
    _builder.newLine();
    _builder.append("import ecore \"../testdata/boolexp/model/BoolExp.ecore\"");
    _builder.newLine();
    _builder.append("open abstract class Exp {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("abstract def boolean eval()");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open abstract class BinaryExp {}");
    _builder.newLine();
    _builder.append("open abstract class Lit {}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open class Tru {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override boolean eval() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return true");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open class Fals {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override boolean eval() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return false");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open class And {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override boolean eval() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return alg.$(obj.lhs).eval() && alg.$(obj.rhs).eval()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("open class Or {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override boolean eval() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return alg.$(obj.lhs).eval() || alg.$(obj.rhs).eval()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Or _createOr = this.fact.createOr();
    final Procedure1<Or> _function = (Or it) -> {
      And _createAnd = this.fact.createAnd();
      final Procedure1<And> _function_1 = (And it_1) -> {
        it_1.setLhs(this.fact.createTru());
        it_1.setRhs(this.fact.createFals());
      };
      And _doubleArrow = ObjectExtensions.<And>operator_doubleArrow(_createAnd, _function_1);
      it.setLhs(_doubleArrow);
      it.setRhs(this.fact.createTru());
    };
    Or _doubleArrow = ObjectExtensions.<Or>operator_doubleArrow(_createOr, _function);
    this._aleTestHelper.assertEvaluatesTo(this._aleTestHelper.call(this._aleTestHelper.with(_builder, _doubleArrow), "eval"), Boolean.valueOf(true));
  }
}
