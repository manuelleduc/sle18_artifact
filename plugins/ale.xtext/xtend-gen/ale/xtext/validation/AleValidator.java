package ale.xtext.validation;

import ale.xtext.ale.AbstractMethod;
import ale.xtext.ale.AleClass;
import ale.xtext.ale.AleImport;
import ale.xtext.ale.AlePackage;
import ale.xtext.ale.AleRoot;
import ale.xtext.ale.ConcreteMethod;
import ale.xtext.ale.DefMethod;
import ale.xtext.ale.EcoreImport;
import ale.xtext.ale.OverrideMethod;
import ale.xtext.utils.AleUtils;
import ale.xtext.utils.EcoreUtils;
import ale.xtext.validation.AbstractAleValidator;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class AleValidator extends AbstractAleValidator {
  @Inject
  @Extension
  private EcoreUtils _ecoreUtils;
  
  @Inject
  @Extension
  private AleUtils _aleUtils;
  
  public final static String SYNTAX_NOT_FOUND = "SYNTAX_NOT_FOUND";
  
  public final static String SEMANTICS_IMPORT_LOOP = "SEMANTICS_IMPORT_LOOP";
  
  public final static String UNKNOWN_OPENCLASS = "UNKNOWN_OPENCLASS";
  
  public final static String ABSTRACT_METHOD_NOT_IMPL = "ABSTRACT_METHOD_NOT_IMPL";
  
  public final static String NO_ABSTRACT_METHOD_IF_NO_SUBCLASS = "NO_ABSTRACT_METHOD_IF_NO_SUBCLASS";
  
  public final static String ALECLASS_NAME_UNIQUENESS = "ALECLASS_NAME_UNIQUENESS";
  
  public final static String OVERRIDE_MISSING = "OVERRIDE_MISSING";
  
  public final static String SUPERFLUOUS_OVERRIDE = "SUPERFLUOUS_OVERRIDE";
  
  public final static String NO_CONCRETE_IN_REQUIRED = "NO_CONCRETE_IN_REQUIRED";
  
  public final String CONCRETE_CLASS_WITH_ABSTRACT_METHODS = "CONCRETE_CLASS_WITH_ABSTRACT_METHODS";
  
  @Check
  public void checkValidSyntax(final EcoreImport syntax) {
    EPackage _loadEPackage = this._ecoreUtils.loadEPackage(syntax.getUri());
    boolean _tripleEquals = (_loadEPackage == null);
    if (_tripleEquals) {
      String _uri = syntax.getUri();
      String _plus = ("Couldn\'t not find an EPackage at the URI " + _uri);
      this.error(_plus, syntax, 
        AlePackage.Literals.ECORE_IMPORT__URI, 
        AleValidator.SYNTAX_NOT_FOUND);
    }
  }
  
  private void loadAllSemantics(final AleRoot root, final List<AleRoot> sems) {
    final Function1<AleImport, AleRoot> _function = (AleImport it) -> {
      return it.getRef();
    };
    final List<AleRoot> ales = ListExtensions.<AleImport, AleRoot>map(root.getAleImports(), _function);
    for (final AleRoot ale : ales) {
      boolean _contains = sems.contains(ale);
      boolean _not = (!_contains);
      if (_not) {
        sems.add(ale);
        this.loadAllSemantics(ale, sems);
      }
    }
  }
  
  @Check
  public void checkImportSemanticNonCyclic(final AleRoot root) {
    final ArrayList<AleRoot> recDeps = CollectionLiterals.<AleRoot>newArrayList();
    this.loadAllSemantics(root, recDeps);
    boolean _contains = recDeps.contains(root);
    if (_contains) {
      this.error("Ale dependencies loop", root, AlePackage.Literals.ALE_ROOT__NAME, AleValidator.SEMANTICS_IMPORT_LOOP);
    }
  }
  
  @Check
  public void checkIsOpenClassImported(final AleClass aleClass) {
    final Function1<EClass, Boolean> _function = (EClass it) -> {
      String _name = it.getName();
      String _name_1 = aleClass.getName();
      return Boolean.valueOf(Objects.equal(_name, _name_1));
    };
    boolean _exists = IterableExtensions.<EClass>exists(this._aleUtils.getAllEClasses(this._aleUtils.getRoot(aleClass)), _function);
    boolean _not = (!_exists);
    if (_not) {
      String _name = aleClass.getName();
      String _plus = ("Cannot find corresponding concept " + _name);
      this.error(_plus, aleClass, 
        AlePackage.Literals.ALE_CLASS__NAME, 
        AleValidator.UNKNOWN_OPENCLASS);
    }
  }
  
  @Check
  public void checkNoAbstractMethodsIfNoSubclasses(final AleClass aleCls) {
    final AleRoot root = this._aleUtils.getRoot(aleCls);
    final EClass eCls = this._aleUtils.getMatchingEClass(aleCls);
    if ((((!aleCls.isAbstract()) && (!this._ecoreUtils.hasRequiredAnnotation(eCls))) && (!IterableExtensions.<EClass>exists(this._aleUtils.getAllEClasses(root), ((Function1<EClass, Boolean>) (EClass it) -> {
      return Boolean.valueOf(it.getESuperTypes().contains(eCls));
    }))))) {
      final Consumer<AbstractMethod> _function = (AbstractMethod m) -> {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("The method ");
        String _name = m.getName();
        _builder.append(_name);
        _builder.append(" cannot be abstract as there are no subclasses to implement it.");
        this.error(_builder.toString(), m, 
          AlePackage.Literals.ALE_METHOD__NAME, 
          AleValidator.NO_ABSTRACT_METHOD_IF_NO_SUBCLASS);
      };
      Iterables.<AbstractMethod>filter(aleCls.getMethods(), AbstractMethod.class).forEach(_function);
    }
  }
  
  @Check
  public void checkAleClassUniqueness(final AleClass aleCls) {
    final Function1<AleClass, Boolean> _function = (AleClass it) -> {
      return Boolean.valueOf(((!Objects.equal(aleCls, it)) && Objects.equal(it.getName(), aleCls.getName())));
    };
    boolean _exists = IterableExtensions.<AleClass>exists(this._aleUtils.getRoot(aleCls).getClasses(), _function);
    if (_exists) {
      String _name = aleCls.getName();
      String _plus = ("Duplicate open-class " + _name);
      String _plus_1 = (_plus + " in ");
      String _name_1 = this._aleUtils.getRoot(aleCls).getName();
      String _plus_2 = (_plus_1 + _name_1);
      this.error(_plus_2, aleCls, 
        AlePackage.Literals.ALE_CLASS__NAME, 
        AleValidator.ALECLASS_NAME_UNIQUENESS);
    }
  }
  
  @Check
  public void checkAbstractMethodsAreImplemented(final AleClass aleCls) {
    final AleRoot root = this._aleUtils.getRoot(aleCls);
    final EClass eCls = this._aleUtils.getMatchingEClass(aleCls);
    if ((((!aleCls.isAbstract()) && (!this._ecoreUtils.hasRequiredAnnotation(eCls))) && (!IterableExtensions.<EClass>exists(this._aleUtils.getAllEClasses(root), ((Function1<EClass, Boolean>) (EClass it) -> {
      return Boolean.valueOf(it.getESuperTypes().contains(eCls));
    }))))) {
      final Iterable<AbstractMethod> abst = Iterables.<AbstractMethod>filter(this._aleUtils.getAllMethods(aleCls, true), AbstractMethod.class);
      final Function1<AbstractMethod, Boolean> _function = (AbstractMethod am) -> {
        final Function1<ConcreteMethod, Boolean> _function_1 = (ConcreteMethod cm) -> {
          return Boolean.valueOf(((!Objects.equal(cm, am)) && this._aleUtils.overrides(cm, am)));
        };
        boolean _exists = IterableExtensions.<ConcreteMethod>exists(Iterables.<ConcreteMethod>filter(this._aleUtils.getAllMethods(aleCls, true), ConcreteMethod.class), _function_1);
        return Boolean.valueOf((!_exists));
      };
      final Iterable<AbstractMethod> notImpl = IterableExtensions.<AbstractMethod>filter(abst, _function);
    }
  }
  
  @Check
  public void checkNoMissingOverride(final DefMethod m) {
    boolean _isEmpty = this._aleUtils.getOverridenMethods(m).isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      String _name = m.getName();
      String _plus = ("Method " + _name);
      String _plus_1 = (_plus + " must use the \'override\' keyword as it overrides a supertype method.");
      this.error(_plus_1, m, 
        AlePackage.Literals.ALE_METHOD__NAME, 
        AleValidator.OVERRIDE_MISSING);
    }
  }
  
  @Check
  public void checkSuperfluousOverride(final OverrideMethod m) {
    boolean _isEmpty = this._aleUtils.getOverridenMethods(m).isEmpty();
    if (_isEmpty) {
      String _name = m.getName();
      String _plus = ("Method " + _name);
      String _plus_1 = (_plus + " does not override a supertype method.");
      this.error(_plus_1, m, 
        AlePackage.Literals.ALE_METHOD__NAME, 
        AleValidator.SUPERFLUOUS_OVERRIDE);
    }
  }
  
  @Check
  public void checkNoConcreteMethodsForRequired(final AleClass cls) {
    final EClass eCls = this._aleUtils.getMatchingEClass(cls);
    final Iterable<ConcreteMethod> cMethods = Iterables.<ConcreteMethod>filter(cls.getMethods(), ConcreteMethod.class);
    if ((((eCls != null) && this._ecoreUtils.hasRequiredAnnotation(eCls)) && (!IterableExtensions.isEmpty(cMethods)))) {
      this.error(
        "Cannot insert concrete methods in @Required concepts.", cls, 
        AlePackage.Literals.ALE_CLASS__NAME, 
        AleValidator.NO_CONCRETE_IN_REQUIRED);
    }
  }
  
  @Check
  public void concreteClassWithAbstractMethods(final AleClass aleClass) {
    if (((!aleClass.isAbstract()) && (!IterableExtensions.isEmpty(Iterables.<AbstractMethod>filter(aleClass.getMethods(), AbstractMethod.class))))) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("A class with abstract methods must be declared abstract");
      this.error(_builder.toString(), aleClass, AlePackage.Literals.ALE_CLASS__NAME, this.CONCRETE_CLASS_WITH_ABSTRACT_METHODS);
    }
  }
}
