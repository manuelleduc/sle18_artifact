package ale.xtext.tests;

import ale.xtext.tests.AleInjectorProvider;
import ale.xtext.tests.AleTestHelper;
import com.google.inject.Inject;
import dispatch.A;
import dispatch.B;
import dispatch.C;
import dispatch.Container;
import dispatch.D;
import dispatch.DispatchFactory;
import dispatch.E;
import dispatch.F;
import dispatch.G;
import java.util.Collections;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(AleInjectorProvider.class)
@SuppressWarnings("all")
public class DispatchTests {
  @Inject
  @Extension
  private AleTestHelper _aleTestHelper;
  
  private final DispatchFactory fact = DispatchFactory.eINSTANCE;
  
  @Before
  public void setUp() {
    EPackage.Registry.INSTANCE.put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
  }
  
  @Test
  public void testNoOverride() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("behavior test");
    _builder.newLine();
    _builder.append("import ecore \"../testdata/dispatch/model/Dispatch.ecore\"");
    _builder.newLine();
    _builder.append("open class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def String foo() { return \"A::foo()\" }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("open class Container {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def java.util.List<String> collect() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return obj.objs.map[alg.$(it).foo()]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Container _createContainer = this.fact.createContainer();
    final Procedure1<Container> _function = (Container it) -> {
      EList<A> _objs = it.getObjs();
      A _createA = this.fact.createA();
      _objs.add(_createA);
      EList<A> _objs_1 = it.getObjs();
      B _createB = this.fact.createB();
      _objs_1.add(_createB);
      EList<A> _objs_2 = it.getObjs();
      C _createC = this.fact.createC();
      _objs_2.add(_createC);
      EList<A> _objs_3 = it.getObjs();
      D _createD = this.fact.createD();
      _objs_3.add(_createD);
      EList<A> _objs_4 = it.getObjs();
      E _createE = this.fact.createE();
      _objs_4.add(_createE);
      EList<A> _objs_5 = it.getObjs();
      F _createF = this.fact.createF();
      _objs_5.add(_createF);
      EList<A> _objs_6 = it.getObjs();
      G _createG = this.fact.createG();
      _objs_6.add(_createG);
    };
    Container _doubleArrow = ObjectExtensions.<Container>operator_doubleArrow(_createContainer, _function);
    this._aleTestHelper.assertEvaluatesTo(this._aleTestHelper.call(this._aleTestHelper.with(_builder, _doubleArrow), "collect"), 
      Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("A::foo()", "A::foo()", "A::foo()", "A::foo()", "A::foo()", "A::foo()", "A::foo()")));
  }
  
  @Test
  public void testAllOverride() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("behavior test");
    _builder.newLine();
    _builder.append("import ecore \"../testdata/dispatch/model/Dispatch.ecore\"");
    _builder.newLine();
    _builder.append("open class A { def String foo() { return \"A::foo()\" } }");
    _builder.newLine();
    _builder.append("open class B { override String foo() { return \"B::foo()\" } }");
    _builder.newLine();
    _builder.append("open class C { override String foo() { return \"C::foo()\" } }");
    _builder.newLine();
    _builder.append("open class D { override String foo() { return \"D::foo()\" } }");
    _builder.newLine();
    _builder.append("open class E { override String foo() { return \"E::foo()\" } }");
    _builder.newLine();
    _builder.append("open class F { override String foo() { return \"F::foo()\" } }");
    _builder.newLine();
    _builder.append("open class G { override String foo() { return \"G::foo()\" } }");
    _builder.newLine();
    _builder.append("open class Container {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def java.util.List<String> collect() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return obj.objs");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append(".map[alg.$(it).foo()]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Container _createContainer = this.fact.createContainer();
    final Procedure1<Container> _function = (Container it) -> {
      EList<A> _objs = it.getObjs();
      A _createA = this.fact.createA();
      _objs.add(_createA);
      EList<A> _objs_1 = it.getObjs();
      B _createB = this.fact.createB();
      _objs_1.add(_createB);
      EList<A> _objs_2 = it.getObjs();
      C _createC = this.fact.createC();
      _objs_2.add(_createC);
      EList<A> _objs_3 = it.getObjs();
      D _createD = this.fact.createD();
      _objs_3.add(_createD);
      EList<A> _objs_4 = it.getObjs();
      E _createE = this.fact.createE();
      _objs_4.add(_createE);
      EList<A> _objs_5 = it.getObjs();
      F _createF = this.fact.createF();
      _objs_5.add(_createF);
      EList<A> _objs_6 = it.getObjs();
      G _createG = this.fact.createG();
      _objs_6.add(_createG);
    };
    Container _doubleArrow = ObjectExtensions.<Container>operator_doubleArrow(_createContainer, _function);
    this._aleTestHelper.assertEvaluatesTo(this._aleTestHelper.call(this._aleTestHelper.with(_builder, _doubleArrow), "collect"), 
      Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("A::foo()", "B::foo()", "C::foo()", "D::foo()", "E::foo()", "F::foo()", "G::foo()")));
  }
  
  @Test
  public void testImplicitSuperCall1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("behavior test");
    _builder.newLine();
    _builder.append("import ecore \"../testdata/dispatch/model/Dispatch.ecore\"");
    _builder.newLine();
    _builder.append("open class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def String foo() { return \"A::foo()\" }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("open class B {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override String foo() { return \"B::foo()\" }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("open class D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override String foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return super.foo();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this._aleTestHelper.assertEvaluatesTo(this._aleTestHelper.call(this._aleTestHelper.with(_builder, this.fact.createD()), "foo"), "B::foo()");
  }
  
  @Test
  public void testImplicitSuperCall2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("behavior test");
    _builder.newLine();
    _builder.append("import ecore \"../testdata/dispatch/model/Dispatch.ecore\"");
    _builder.newLine();
    _builder.append("open class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def String foo() { return \"A::foo()\" }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("open class D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override String foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return super.foo();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this._aleTestHelper.assertEvaluatesTo(this._aleTestHelper.call(this._aleTestHelper.with(_builder, this.fact.createD()), "foo"), "A::foo()");
  }
  
  @Test
  public void testExplicitSuperCall() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("behavior test");
    _builder.newLine();
    _builder.append("import ecore \"../testdata/dispatch/model/Dispatch.ecore\"");
    _builder.newLine();
    _builder.append("open class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def String foo() { return \"A::foo()\" }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("open class B {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override String foo() { return \"B::foo()\" }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("open class D {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override String foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return alg.dispatch__A(obj).foo() + \" -- \" + alg.dispatch__B(obj).foo()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this._aleTestHelper.assertEvaluatesTo(this._aleTestHelper.call(this._aleTestHelper.with(_builder, this.fact.createD()), "foo"), "A::foo() -- B::foo()");
  }
}
