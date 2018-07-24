package ale.xtext.utils;

import ale.xtext.ale.AbstractMethod;
import ale.xtext.ale.AleClass;
import ale.xtext.ale.AleImport;
import ale.xtext.ale.AleMethod;
import ale.xtext.ale.AleRoot;
import ale.xtext.ale.ConcreteMethod;
import ale.xtext.ale.EcoreImport;
import ale.xtext.utils.EcoreUtils;
import ale.xtext.utils.NamingUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.StandardTypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.util.CommonTypeComputationServices;

@SuppressWarnings("all")
public class AleUtils {
  @Inject
  @Extension
  private EcoreUtils _ecoreUtils;
  
  @Inject
  private NamingUtils namingUtils;
  
  @Inject
  private CommonTypeComputationServices services;
  
  public AleClass findNearestGeneratedParent(final AleClass cls) {
    final Function1<AleClass, Boolean> _function = (AleClass it) -> {
      return Boolean.valueOf((!Objects.equal(it, cls)));
    };
    final Function1<AleClass, Boolean> _function_1 = (AleClass it) -> {
      return Boolean.valueOf(this.isGenerated(it));
    };
    return IterableExtensions.<AleClass>findFirst(IterableExtensions.<AleClass>filter(this.getAllAleClasses(this.getMatchingEClass(cls), this.getRoot(cls)), _function), _function_1);
  }
  
  public List<AleRoot> getAllParents(final AleRoot root, final boolean includeSelf) {
    final HashSet<AleRoot> ret = CollectionLiterals.<AleRoot>newHashSet();
    if (includeSelf) {
      ret.add(root);
    }
    EList<AleImport> _aleImports = null;
    if (root!=null) {
      _aleImports=root.getAleImports();
    }
    boolean _tripleNotEquals = (_aleImports != null);
    if (_tripleNotEquals) {
      EList<AleImport> _aleImports_1 = null;
      if (root!=null) {
        _aleImports_1=root.getAleImports();
      }
      final Function1<AleImport, Boolean> _function = (AleImport it) -> {
        return Boolean.valueOf((it != null));
      };
      final Consumer<AleImport> _function_1 = (AleImport it) -> {
        this.getAllParentsRec(it.getRef(), ret);
      };
      IterableExtensions.<AleImport>filter(_aleImports_1, _function).forEach(_function_1);
    }
    final Function1<AleRoot, Boolean> _function_2 = (AleRoot it) -> {
      return Boolean.valueOf((it != null));
    };
    return IterableExtensions.<AleRoot>toList(IterableExtensions.<AleRoot>filter(ret, _function_2));
  }
  
  public List<AleClass> getAllAleClasses(final AleRoot root) {
    final Function1<AleRoot, EList<AleClass>> _function = (AleRoot it) -> {
      return it.getClasses();
    };
    return IterableExtensions.<AleClass>toList(Iterables.<AleClass>concat(ListExtensions.<AleRoot, EList<AleClass>>map(this.getAllParents(root, true), _function)));
  }
  
  public AleRoot getRoot(final AleClass cls) {
    EObject _eContainer = cls.eContainer();
    return ((AleRoot) _eContainer);
  }
  
  protected EClass _getMatchingEClass(final AleClass cls) {
    final Function1<EClass, Boolean> _function = (EClass it) -> {
      String _name = it.getName();
      String _name_1 = cls.getName();
      return Boolean.valueOf(Objects.equal(_name, _name_1));
    };
    return IterableExtensions.<EClass>findFirst(this.getAllEClasses(this.getRoot(cls)), _function);
  }
  
  public boolean isAbstract(final AleClass cls) {
    boolean _isEmpty = IterableExtensions.isEmpty(Iterables.<AbstractMethod>filter(this.getAllMethods(cls, false), AbstractMethod.class));
    return (!_isEmpty);
  }
  
  public List<EPackage> getAllEPackages(final AleRoot root) {
    final List<AleRoot> roots = this.getAllParents(root, true);
    final Function1<AleRoot, Boolean> _function = (AleRoot it) -> {
      return Boolean.valueOf((it != null));
    };
    final Function1<AleRoot, String> _function_1 = (AleRoot it) -> {
      EcoreImport _ecoreImport = it.getEcoreImport();
      String _uri = null;
      if (_ecoreImport!=null) {
        _uri=_ecoreImport.getUri();
      }
      return _uri;
    };
    final Function1<String, EPackage> _function_2 = (String it) -> {
      return this._ecoreUtils.loadEPackage(it);
    };
    return IterableExtensions.<EPackage>toList(IterableExtensions.<String, EPackage>map(IterableExtensions.<String>filterNull(IterableExtensions.<AleRoot, String>map(IterableExtensions.<AleRoot>filter(roots, _function), _function_1)), _function_2));
  }
  
  public List<EClass> getAllEClasses(final AleRoot root) {
    return this._ecoreUtils.getAllClasses(this.getAllEPackages(root));
  }
  
  public List<AleClass> getAleClasses(final EClass eCls, final AleRoot root) {
    final Function1<AleClass, Boolean> _function = (AleClass it) -> {
      String _name = it.getName();
      String _name_1 = eCls.getName();
      return Boolean.valueOf(Objects.equal(_name, _name_1));
    };
    return IterableExtensions.<AleClass>toList(IterableExtensions.<AleClass>filter(this.getAllAleClasses(root), _function));
  }
  
  public List<AleClass> getAllAleClasses(final EClass eCls, final AleRoot root) {
    final ArrayList<AleClass> ret = CollectionLiterals.<AleClass>newArrayList();
    List<AleClass> _aleClasses = this.getAleClasses(eCls, root);
    Iterables.<AleClass>addAll(ret, _aleClasses);
    EList<EClass> _eAllSuperTypes = null;
    if (eCls!=null) {
      _eAllSuperTypes=eCls.getEAllSuperTypes();
    }
    boolean _tripleNotEquals = (_eAllSuperTypes != null);
    if (_tripleNotEquals) {
      final Comparator<EClass> _function = (EClass a, EClass b) -> {
        int _xifexpression = (int) 0;
        boolean _contains = a.getEAllSuperTypes().contains(b);
        if (_contains) {
          _xifexpression = (-1);
        } else {
          int _xifexpression_1 = (int) 0;
          boolean _contains_1 = b.getEAllSuperTypes().contains(a);
          if (_contains_1) {
            _xifexpression_1 = 1;
          } else {
            _xifexpression_1 = 0;
          }
          _xifexpression = _xifexpression_1;
        }
        return _xifexpression;
      };
      final Function1<EClass, List<AleClass>> _function_1 = (EClass it) -> {
        return this.getAleClasses(it, root);
      };
      Iterable<AleClass> _flatten = Iterables.<AleClass>concat(ListExtensions.<EClass, List<AleClass>>map(IterableExtensions.<EClass>sortWith(eCls.getEAllSuperTypes(), _function), _function_1));
      Iterables.<AleClass>addAll(ret, _flatten);
    }
    return ret;
  }
  
  public List<AleMethod> getAllMethods(final AleClass aleCls, final boolean withOverride) {
    final ArrayList<AleMethod> ret = CollectionLiterals.<AleMethod>newArrayList();
    final EClass correspondingEClass = this.getMatchingEClass(aleCls);
    final Function1<AleClass, EList<AleMethod>> _function = (AleClass it) -> {
      return it.getMethods();
    };
    final Comparator<AleMethod> _function_1 = (AleMethod a, AleMethod b) -> {
      int _xifexpression = (int) 0;
      if ((a instanceof ConcreteMethod)) {
        _xifexpression = (-1);
      } else {
        int _xifexpression_1 = (int) 0;
        if ((b instanceof ConcreteMethod)) {
          _xifexpression_1 = 1;
        } else {
          _xifexpression_1 = 0;
        }
        _xifexpression = _xifexpression_1;
      }
      return _xifexpression;
    };
    final Comparator<AleMethod> _function_2 = (AleMethod a, AleMethod b) -> {
      int _xifexpression = (int) 0;
      boolean _overrides = this.overrides(a, b);
      if (_overrides) {
        _xifexpression = (-1);
      } else {
        int _xifexpression_1 = (int) 0;
        boolean _overrides_1 = this.overrides(b, a);
        if (_overrides_1) {
          _xifexpression_1 = 1;
        } else {
          _xifexpression_1 = 0;
        }
        _xifexpression = _xifexpression_1;
      }
      return _xifexpression;
    };
    final Comparator<AleMethod> _function_3 = (AleMethod a, AleMethod b) -> {
      int _xifexpression = (int) 0;
      EObject _eContainer = a.eContainer();
      EObject _eContainer_1 = b.eContainer();
      boolean _contains = this.getMatchingEClass(((AleClass) _eContainer)).getEAllSuperTypes().contains(
        this.getMatchingEClass(((AleClass) _eContainer_1)));
      if (_contains) {
        _xifexpression = (-1);
      } else {
        int _xifexpression_1 = (int) 0;
        EObject _eContainer_2 = b.eContainer();
        EObject _eContainer_3 = a.eContainer();
        boolean _contains_1 = this.getMatchingEClass(((AleClass) _eContainer_2)).getEAllSuperTypes().contains(
          this.getMatchingEClass(((AleClass) _eContainer_3)));
        if (_contains_1) {
          _xifexpression_1 = 1;
        } else {
          _xifexpression_1 = 0;
        }
        _xifexpression = _xifexpression_1;
      }
      return _xifexpression;
    };
    final Consumer<AleMethod> _function_4 = (AleMethod m1) -> {
      if ((withOverride || (!IterableExtensions.<AleMethod>exists(ret, ((Function1<AleMethod, Boolean>) (AleMethod m2) -> {
        return Boolean.valueOf(this.overrides(m2, m1));
      }))))) {
        ret.add(m1);
      }
    };
    IterableExtensions.<AleMethod>sortWith(IterableExtensions.<AleMethod>sortWith(IterableExtensions.<AleMethod>sortWith(Iterables.<AleMethod>concat(ListExtensions.<AleClass, EList<AleMethod>>map(this.getAllAleClasses(correspondingEClass, this.getRoot(aleCls)), _function)), _function_1), _function_2), _function_3).forEach(_function_4);
    return ret;
  }
  
  public AleClass getContainingAleClass(final AleMethod m) {
    EObject _eContainer = m.eContainer();
    return ((AleClass) _eContainer);
  }
  
  public List<AleMethod> getOverridenMethods(final AleMethod m) {
    final Function1<AleMethod, Boolean> _function = (AleMethod mp) -> {
      return Boolean.valueOf(((!Objects.equal(m, mp)) && this.overrides(m, mp)));
    };
    return IterableExtensions.<AleMethod>toList(IterableExtensions.<AleMethod>filter(this.getAllMethods(this.getContainingAleClass(m), true), _function));
  }
  
  public boolean isGenerated(final AleClass aleCls) {
    return true;
  }
  
  public boolean overrides(final AleMethod m1, final AleMethod m2) {
    return ((Objects.equal(m1.getName(), m2.getName()) && (((m1.getType() == null) && (m2.getType() == null)) || this.isSubtypeOf(m1.getType(), m2.getType()))) && 
      this.parametersEqual(m1.getParams(), m2.getParams()));
  }
  
  private boolean parametersEqual(final List<JvmFormalParameter> p1, final List<JvmFormalParameter> p2) {
    int _size = p1.size();
    int _size_1 = p2.size();
    boolean _notEquals = (_size != _size_1);
    if (_notEquals) {
      return false;
    }
    int _size_2 = p1.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size_2, true);
    for (final Integer i : _doubleDotLessThan) {
      String _qualifiedName = p1.get((i).intValue()).getParameterType().getQualifiedName();
      String _qualifiedName_1 = p2.get((i).intValue()).getParameterType().getQualifiedName();
      boolean _notEquals_1 = (!Objects.equal(_qualifiedName, _qualifiedName_1));
      if (_notEquals_1) {
        return false;
      }
    }
    return true;
  }
  
  public boolean isSubtypeOf(final JvmTypeReference r1, final JvmTypeReference r2) {
    boolean _xifexpression = false;
    if (((r1 != null) && (r2 != null))) {
      _xifexpression = this.toLightweightTypeReference(r1, r1.eResource()).isSubtypeOf(r2.getType());
    } else {
      _xifexpression = false;
    }
    return _xifexpression;
  }
  
  public LightweightTypeReference toLightweightTypeReference(final JvmTypeReference typeRef, final Resource context) {
    LightweightTypeReference _xifexpression = null;
    if (((typeRef != null) && (context != null))) {
      _xifexpression = new StandardTypeReferenceOwner(this.services, context).toLightweightTypeReference(typeRef);
    } else {
      _xifexpression = null;
    }
    return _xifexpression;
  }
  
  private void getAllParentsRec(final AleRoot root, final Set<AleRoot> ret) {
    ret.add(root);
    final Consumer<AleImport> _function = (AleImport it) -> {
      this.getAllParentsRec(it.getRef(), ret);
    };
    root.getAleImports().forEach(_function);
  }
  
  public EClass getMatchingEClass(final AleClass cls) {
    return _getMatchingEClass(cls);
  }
}
