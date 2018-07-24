package ale.xtext.tests;

import ale.xtext.ale.AleRoot;
import ale.xtext.tests.AleCompileResult;
import ale.xtext.utils.NamingUtils;
import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.JavaVersion;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.junit.Assert;

@SuppressWarnings("all")
public class AleTestHelper {
  @Inject
  @Extension
  private NamingUtils _namingUtils;
  
  @Inject
  @Extension
  private ParseHelper<AleRoot> _parseHelper;
  
  @Inject
  @Extension
  private CompilationTestHelper compilationHelper;
  
  public Object invokeRevisitorMethod(final CompilationTestHelper.Result res, final Object obj, final String rvName, final String methodName, final Object[] args) {
    try {
      final Class<?> rvIntf = res.getCompiledClasses().get(rvName);
      final Function1<Method, Boolean> _function = (Method it) -> {
        return Boolean.valueOf((Objects.equal(it.getName(), "$") && IterableExtensions.<Parameter>head(((Iterable<Parameter>)Conversions.doWrapArray(it.getParameters()))).getType().isAssignableFrom(obj.getClass())));
      };
      final Method dollar = IterableExtensions.<Method>findFirst(((Iterable<Method>)Conversions.doWrapArray(rvIntf.getMethods())), _function);
      final Constructor<MethodHandles.Lookup> construct = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
      construct.setAccessible(true);
      ClassLoader _classLoader = res.getClassLoader();
      final Object rvImpl = Proxy.newProxyInstance(_classLoader, 
        new Class[] { rvIntf }, 
        new InvocationHandler() {
          @Override
          public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
            boolean _isDefault = method.isDefault();
            if (_isDefault) {
              final Class<?> decl = method.getDeclaringClass();
              return construct.newInstance(decl, Integer.valueOf(MethodHandles.Lookup.PRIVATE)).unreflectSpecial(method, decl).bindTo(proxy).invokeWithArguments(args);
            }
            return null;
          }
        });
      final Object operationObj = dollar.invoke(rvImpl, obj);
      final Function1<Method, Boolean> _function_1 = (Method it) -> {
        String _name = it.getName();
        return Boolean.valueOf(Objects.equal(_name, methodName));
      };
      final Method method = IterableExtensions.<Method>findFirst(((Iterable<Method>)Conversions.doWrapArray(operationObj.getClass().getMethods())), _function_1);
      return method.invoke(operationObj, args);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public Object invokeRevisitorMethod(final CompilationTestHelper.Result res, final Object obj, final String rvName, final String methodName) {
    return this.invokeRevisitorMethod(res, obj, rvName, methodName, null);
  }
  
  public AleCompileResult with(final CharSequence pgm, final Object m) {
    try {
      AleCompileResult _aleCompileResult = new AleCompileResult();
      final Procedure1<AleCompileResult> _function = (AleCompileResult it) -> {
        try {
          it.setModel(m);
          it.setAleRoot(this._parseHelper.parse(pgm));
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      final AleCompileResult aleRes = ObjectExtensions.<AleCompileResult>operator_doubleArrow(_aleCompileResult, _function);
      this.compilationHelper.setJavaVersion(JavaVersion.JAVA8);
      final IAcceptor<CompilationTestHelper.Result> _function_1 = (CompilationTestHelper.Result res) -> {
        aleRes.setCompileRes(res);
      };
      this.compilationHelper.compile(pgm, _function_1);
      boolean _isEmpty = aleRes.getCompileRes().getErrorsAndWarnings().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        Assert.fail(aleRes.getCompileRes().getErrorsAndWarnings().toString());
      }
      return aleRes;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public AleCompileResult with(final CharSequence pgm, final String model) {
    final ResourceSetImpl rs = new ResourceSetImpl();
    final Resource res = rs.getResource(URI.createURI(model), true);
    return this.with(pgm, IterableExtensions.<EObject>head(res.getContents()));
  }
  
  public Object call(final AleCompileResult res, final String methodName) {
    return this.invokeRevisitorMethod(res.getCompileRes(), 
      res.getModel(), 
      this._namingUtils.getRevisitorInterfaceFqn(res.getAleRoot()), methodName);
  }
  
  public void assertEvaluatesTo(final Object actual, final Object expected) {
    Assert.assertEquals(expected, actual);
  }
}
