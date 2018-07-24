package ale.xtext.utils;

import ale.xtext.ale.AleClass;
import ale.xtext.ale.AleRoot;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class NamingUtils {
  public String getRootName(final AleClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    EObject _eContainer = cls.eContainer();
    String _name = ((AleRoot) _eContainer).getName();
    _builder.append(_name);
    return _builder.toString();
  }
  
  public String getRevisitorPackageFqn(final EPackage pkg) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = null;
    if (pkg!=null) {
      _name=pkg.getName();
    }
    _builder.append(_name);
    _builder.append(".revisitor");
    return _builder.toString();
  }
  
  public String getRevisitorInterfaceName(final EPackage pkg) {
    StringConcatenation _builder = new StringConcatenation();
    String _firstUpper = StringExtensions.toFirstUpper(pkg.getName());
    _builder.append(_firstUpper);
    _builder.append("Revisitor");
    return _builder.toString();
  }
  
  public String getRevisitorInterfaceFqn(final EPackage pkg) {
    StringConcatenation _builder = new StringConcatenation();
    String _revisitorPackageFqn = this.getRevisitorPackageFqn(pkg);
    _builder.append(_revisitorPackageFqn);
    _builder.append(".");
    String _revisitorInterfaceName = this.getRevisitorInterfaceName(pkg);
    _builder.append(_revisitorInterfaceName);
    return _builder.toString();
  }
  
  public String getRevisitorInterfacePath(final EPackage pkg) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("src/");
    String _name = pkg.getName();
    _builder.append(_name);
    _builder.append("/revisitor");
    return _builder.toString();
  }
  
  public String getRevisitorPackageFqn(final AleRoot root) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = root.getName();
    _builder.append(_name);
    _builder.append(".revisitor");
    return _builder.toString();
  }
  
  public String getRevisitorInterfaceName(final AleRoot root) {
    StringConcatenation _builder = new StringConcatenation();
    String _firstUpper = StringExtensions.toFirstUpper(root.getName());
    _builder.append(_firstUpper);
    _builder.append("Revisitor");
    return _builder.toString();
  }
  
  public String getRevisitorInterfaceFqn(final AleRoot root) {
    StringConcatenation _builder = new StringConcatenation();
    String _revisitorPackageFqn = this.getRevisitorPackageFqn(root);
    _builder.append(_revisitorPackageFqn);
    _builder.append(".impl.");
    String _revisitorInterfaceName = this.getRevisitorInterfaceName(root);
    _builder.append(_revisitorInterfaceName);
    return _builder.toString();
  }
  
  public String getOperationPackageFqn(final AleClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    String _rootName = this.getRootName(cls);
    _builder.append(_rootName);
    _builder.append(".revisitor.operations.");
    String _name = EcoreUtil2.<AleRoot>getContainerOfType(cls, AleRoot.class).getName();
    _builder.append(_name);
    return _builder.toString();
  }
  
  public String getOperationInterfaceName(final AleClass aleCls) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = aleCls.getName();
    _builder.append(_name);
    _builder.append("Operation");
    return _builder.toString();
  }
  
  public String getOperationInterfaceFqn(final AleClass aleCls) {
    StringConcatenation _builder = new StringConcatenation();
    String _operationPackageFqn = this.getOperationPackageFqn(aleCls);
    _builder.append(_operationPackageFqn);
    _builder.append(".");
    String _operationInterfaceName = this.getOperationInterfaceName(aleCls);
    _builder.append(_operationInterfaceName);
    return _builder.toString();
  }
  
  public String getOperationImplPackageFqn(final AleClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    String _operationPackageFqn = this.getOperationPackageFqn(cls);
    _builder.append(_operationPackageFqn);
    _builder.append(".impl");
    return _builder.toString();
  }
  
  public String getOperationImplName(final AleClass aleCls) {
    StringConcatenation _builder = new StringConcatenation();
    String _operationInterfaceName = this.getOperationInterfaceName(aleCls);
    _builder.append(_operationInterfaceName);
    _builder.append("Impl");
    return _builder.toString();
  }
  
  public String getOperationImplFqn(final AleClass aleCls) {
    StringConcatenation _builder = new StringConcatenation();
    String _operationImplPackageFqn = this.getOperationImplPackageFqn(aleCls);
    _builder.append(_operationImplPackageFqn);
    _builder.append(".");
    String _operationImplName = this.getOperationImplName(aleCls);
    _builder.append(_operationImplName);
    return _builder.toString();
  }
  
  public String getTypeParamName(final EClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    String _firstUpper = StringExtensions.toFirstUpper(cls.getEPackage().getName().replaceAll("\\.", ""));
    _builder.append(_firstUpper);
    _builder.append("__");
    String _name = cls.getName();
    _builder.append(_name);
    _builder.append("T");
    return _builder.toString();
  }
  
  public String getDenotationName(final EClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    String _firstLower = StringExtensions.toFirstLower(cls.getEPackage().getName());
    _builder.append(_firstLower);
    _builder.append("__");
    String _name = cls.getName();
    _builder.append(_name);
    return _builder.toString();
  }
  
  public String getDenotationName(final Pair<EClass, EClass> cls) {
    StringConcatenation _builder = new StringConcatenation();
    String _denotationName = this.getDenotationName(cls.getKey());
    _builder.append(_denotationName);
    {
      EClass _value = cls.getValue();
      boolean _tripleNotEquals = (_value != null);
      if (_tripleNotEquals) {
        _builder.append("__AS__");
        String _denotationName_1 = this.getDenotationName(cls.getValue());
        _builder.append(_denotationName_1);
      }
    }
    return _builder.toString();
  }
  
  public String getDenotationName(final EClass parent, final EClass child) {
    StringConcatenation _builder = new StringConcatenation();
    String _denotationName = this.getDenotationName(parent);
    _builder.append(_denotationName);
    _builder.append("_");
    String _denotationName_1 = this.getDenotationName(child);
    _builder.append(_denotationName_1);
    return _builder.toString();
  }
  
  public String getVarName(final EClass cls) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("it");
    return _builder.toString();
  }
}
