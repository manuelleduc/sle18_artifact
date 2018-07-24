package ale.xtext.tests;

import ale.xtext.ale.AleRoot;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;

@SuppressWarnings("all")
public class AleCompileResult {
  @Accessors
  private AleRoot aleRoot;
  
  @Accessors
  private CompilationTestHelper.Result compileRes;
  
  @Accessors
  private Object model;
  
  @Pure
  public AleRoot getAleRoot() {
    return this.aleRoot;
  }
  
  public void setAleRoot(final AleRoot aleRoot) {
    this.aleRoot = aleRoot;
  }
  
  @Pure
  public CompilationTestHelper.Result getCompileRes() {
    return this.compileRes;
  }
  
  public void setCompileRes(final CompilationTestHelper.Result compileRes) {
    this.compileRes = compileRes;
  }
  
  @Pure
  public Object getModel() {
    return this.model;
  }
  
  public void setModel(final Object model) {
    this.model = model;
  }
}
