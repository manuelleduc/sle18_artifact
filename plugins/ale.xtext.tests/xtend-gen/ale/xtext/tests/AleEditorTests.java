package ale.xtext.tests;

import ale.xtext.ale.AlePackage;
import ale.xtext.ale.AleRoot;
import ale.xtext.tests.AleInjectorProvider;
import ale.xtext.validation.AleValidator;
import com.google.inject.Inject;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.util.JavaVersion;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(AleInjectorProvider.class)
@SuppressWarnings("all")
public class AleEditorTests {
  @Inject
  @Extension
  private ParseHelper<AleRoot> _parseHelper;
  
  @Inject
  @Extension
  private CompilationTestHelper compilationHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  private final static String header = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("behavior test");
      _builder.newLine();
      _builder.append("import ecore \"../testdata/boolexp/model/BoolExp.ecore\"");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  @Before
  public void setUp() {
    EPackage.Registry.INSTANCE.put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
    this.compilationHelper.setJavaVersion(JavaVersion.JAVA8);
  }
  
  @Test
  public void testInvalidEcore() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("behavior test");
      _builder.newLine();
      _builder.append("import ecore \"data/metamodels/404.ecore\"");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder).getEcoreImport(), 
        AlePackage.Literals.ECORE_IMPORT, 
        AleValidator.SYNTAX_NOT_FOUND);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testOpenExistingClass() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(AleEditorTests.header);
      _builder.newLineIfNotEmpty();
      _builder.append("open class Exp {}");
      _builder.newLine();
      _builder.append("open class BinaryExp {}");
      _builder.newLine();
      _builder.append("open class Lit {}");
      _builder.newLine();
      _builder.append("open class And {}");
      _builder.newLine();
      _builder.append("open class Or {}");
      _builder.newLine();
      _builder.append("open class Tru {}");
      _builder.newLine();
      _builder.append("open class Fals {}");
      _builder.newLine();
      this._validationTestHelper.assertNoIssues(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testOpenUnknownClass() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(AleEditorTests.header);
      _builder.newLineIfNotEmpty();
      _builder.append("open class A {}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), 
        AlePackage.Literals.ALE_CLASS, 
        AleValidator.UNKNOWN_OPENCLASS);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testAbstractMethodsAreImplemented() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(AleEditorTests.header);
      _builder.newLineIfNotEmpty();
      _builder.append("open abstract class Exp {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("abstract def void foo()");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("open class Lit {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), 
        AlePackage.Literals.ALE_CLASS, 
        AleValidator.ABSTRACT_METHOD_NOT_IMPL);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testOverrideIsHere() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(AleEditorTests.header);
      _builder.newLineIfNotEmpty();
      _builder.append("open class Exp {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("abstract def void foo()");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("open class And { def void foo() {} }");
      _builder.newLine();
      _builder.append("open class Or { override void foo() {} }");
      _builder.newLine();
      _builder.append("open class Tru { override void foo() {} }");
      _builder.newLine();
      _builder.append("open class Fals { override void foo() {} }");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), 
        AlePackage.Literals.ALE_METHOD, 
        AleValidator.OVERRIDE_MISSING);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testOverrideIsHere2() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(AleEditorTests.header);
      _builder.newLineIfNotEmpty();
      _builder.append("open class Exp {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def void foo() {}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("open class And { def void foo() {} }");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), 
        AlePackage.Literals.ALE_METHOD, 
        AleValidator.OVERRIDE_MISSING);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testNoSuperfluousOverride() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(AleEditorTests.header);
      _builder.newLineIfNotEmpty();
      _builder.append("open class Exp {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("abstract def void foo()");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("open class And { override void foo() {} }");
      _builder.newLine();
      _builder.append("open class Or { override void foo() {} }");
      _builder.newLine();
      _builder.append("open class Tru { override void foo() {} }");
      _builder.newLine();
      _builder.append("open class Fals {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("override void foo() {}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("override void bar() {}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(_builder), 
        AlePackage.Literals.ALE_METHOD, 
        AleValidator.SUPERFLUOUS_OVERRIDE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
