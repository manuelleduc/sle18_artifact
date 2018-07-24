package ale.xtext.jvmmodel;

import ale.xtext.ale.AbstractMethod;
import ale.xtext.ale.AleClass;
import ale.xtext.ale.AleFactory;
import ale.xtext.ale.AleMethod;
import ale.xtext.ale.AleRoot;
import ale.xtext.ale.ConcreteMethod;
import ale.xtext.utils.AleUtils;
import ale.xtext.utils.EcoreUtils;
import ale.xtext.utils.NamingUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public class AleJvmModelInferrer extends AbstractModelInferrer {
  @Data
  public static class ResolvedClass {
    private final AleClass aleCls;
    
    public final EClass eCls;
    
    private final GenClass genCls;
    
    public ResolvedClass(final AleClass aleCls, final EClass eCls, final GenClass genCls) {
      super();
      this.aleCls = aleCls;
      this.eCls = eCls;
      this.genCls = genCls;
    }
    
    @Override
    @Pure
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.aleCls== null) ? 0 : this.aleCls.hashCode());
      result = prime * result + ((this.eCls== null) ? 0 : this.eCls.hashCode());
      return prime * result + ((this.genCls== null) ? 0 : this.genCls.hashCode());
    }
    
    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      AleJvmModelInferrer.ResolvedClass other = (AleJvmModelInferrer.ResolvedClass) obj;
      if (this.aleCls == null) {
        if (other.aleCls != null)
          return false;
      } else if (!this.aleCls.equals(other.aleCls))
        return false;
      if (this.eCls == null) {
        if (other.eCls != null)
          return false;
      } else if (!this.eCls.equals(other.eCls))
        return false;
      if (this.genCls == null) {
        if (other.genCls != null)
          return false;
      } else if (!this.genCls.equals(other.genCls))
        return false;
      return true;
    }
    
    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("aleCls", this.aleCls);
      b.add("eCls", this.eCls);
      b.add("genCls", this.genCls);
      return b.toString();
    }
    
    @Pure
    public AleClass getAleCls() {
      return this.aleCls;
    }
    
    @Pure
    public EClass getECls() {
      return this.eCls;
    }
    
    @Pure
    public GenClass getGenCls() {
      return this.genCls;
    }
  }
  
  private AleRoot root;
  
  private EPackage pkg;
  
  private GenModel gm;
  
  private List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> resolved = CollectionLiterals.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>newArrayList();
  
  private JvmTypeReference cachedRevSignature = null;
  
  @Inject
  @Extension
  private JvmTypesBuilder _jvmTypesBuilder;
  
  @Inject
  @Extension
  private EcoreUtils _ecoreUtils;
  
  @Inject
  @Extension
  private NamingUtils _namingUtils;
  
  @Inject
  @Extension
  private AleUtils _aleUtils;
  
  private Object preProcess() {
    List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> _xblockexpression = null;
    {
      this.pkg = this._ecoreUtils.loadEPackage(this.root.getEcoreImport().getUri());
      this.gm = this._ecoreUtils.loadCorrespondingGenmodel(this.root.getEcoreImport().getUri());
      if (((this.pkg == null) || (this.gm == null))) {
        return Boolean.valueOf(false);
      }
      final Consumer<EClass> _function = (EClass eCls) -> {
        final Function1<AleClass, Boolean> _function_1 = (AleClass it) -> {
          String _name = it.getName();
          String _name_1 = eCls.getName();
          return Boolean.valueOf(Objects.equal(_name, _name_1));
        };
        boolean _exists = IterableExtensions.<AleClass>exists(this.root.getClasses(), _function_1);
        boolean _not = (!_exists);
        if (_not) {
          EList<AleClass> _classes = this.root.getClasses();
          AleClass _createAleClass = AleFactory.eINSTANCE.createAleClass();
          final Procedure1<AleClass> _function_2 = (AleClass it) -> {
            it.setName(eCls.getName());
            it.setAbstract(eCls.isAbstract());
          };
          AleClass _doubleArrow = ObjectExtensions.<AleClass>operator_doubleArrow(_createAleClass, _function_2);
          this._jvmTypesBuilder.<AleClass>operator_add(_classes, _doubleArrow);
        }
      };
      this._ecoreUtils.getAllClasses(this.pkg).forEach(_function);
      final ArrayList<AleJvmModelInferrer.ResolvedClass> resolved = CollectionLiterals.<AleJvmModelInferrer.ResolvedClass>newArrayList();
      final Function1<AleClass, String> _function_1 = (AleClass it) -> {
        return it.getName();
      };
      final Consumer<AleClass> _function_2 = (AleClass aleCls) -> {
        final Function1<EClass, Boolean> _function_3 = (EClass it) -> {
          String _name = it.getName();
          String _name_1 = aleCls.getName();
          return Boolean.valueOf(Objects.equal(_name, _name_1));
        };
        final EClass eCls = IterableExtensions.<EClass>findFirst(this._ecoreUtils.getAllClasses(this.pkg), _function_3);
        GenClass _xifexpression = null;
        if ((eCls != null)) {
          _xifexpression = this._ecoreUtils.getGenClass(eCls, this.gm);
        }
        final GenClass genCls = _xifexpression;
        if ((((aleCls != null) && (eCls != null)) && (genCls != null))) {
          AleJvmModelInferrer.ResolvedClass _resolvedClass = new AleJvmModelInferrer.ResolvedClass(aleCls, eCls, genCls);
          resolved.add(_resolvedClass);
        } else {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Class ");
          _builder.append(aleCls);
          _builder.append(" has not been resolved");
          InputOutput.<String>println(_builder.toString());
        }
      };
      IterableExtensions.<AleClass, String>sortBy(this.root.getClasses(), _function_1).forEach(_function_2);
      _xblockexpression = this.resolved = this.buildExtendedFactoryNames(resolved);
    }
    return _xblockexpression;
  }
  
  public List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> buildExtendedFactoryNames(final List<AleJvmModelInferrer.ResolvedClass> classes) {
    List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> _xblockexpression = null;
    {
      final Function1<AleJvmModelInferrer.ResolvedClass, Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>> _function = (AleJvmModelInferrer.ResolvedClass it) -> {
        Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>> _xblockexpression_1 = null;
        {
          final List<AleJvmModelInferrer.ResolvedClass> st = CollectionLiterals.<AleJvmModelInferrer.ResolvedClass>newArrayList();
          final Consumer<EClass> _function_1 = (EClass stp) -> {
            final Function1<AleJvmModelInferrer.ResolvedClass, Boolean> _function_2 = (AleJvmModelInferrer.ResolvedClass it_1) -> {
              return Boolean.valueOf(Objects.equal(it_1.eCls, stp));
            };
            st.add(IterableExtensions.<AleJvmModelInferrer.ResolvedClass>findFirst(classes, _function_2));
          };
          it.eCls.getEAllSuperTypes().forEach(_function_1);
          st.add(it);
          final Function1<AleJvmModelInferrer.ResolvedClass, Boolean> _function_2 = (AleJvmModelInferrer.ResolvedClass it_1) -> {
            return Boolean.valueOf(((it_1 != null) && (it_1.eCls != null)));
          };
          final Function1<AleJvmModelInferrer.ResolvedClass, EList<EClass>> _function_3 = (AleJvmModelInferrer.ResolvedClass it_1) -> {
            return it_1.eCls.getESuperTypes();
          };
          final Function1<EList<EClass>, Boolean> _function_4 = (EList<EClass> it_1) -> {
            int _size = it_1.size();
            return Boolean.valueOf((_size > 1));
          };
          final Function1<EClass, AleJvmModelInferrer.ResolvedClass> _function_5 = (EClass stp) -> {
            final Function1<AleJvmModelInferrer.ResolvedClass, Boolean> _function_6 = (AleJvmModelInferrer.ResolvedClass it_1) -> {
              return Boolean.valueOf(Objects.equal(it_1.eCls, stp));
            };
            return IterableExtensions.<AleJvmModelInferrer.ResolvedClass>findFirst(classes, _function_6);
          };
          Iterable<AleJvmModelInferrer.ResolvedClass> _map = IterableExtensions.<EClass, AleJvmModelInferrer.ResolvedClass>map(Iterables.<EClass>concat(IterableExtensions.<EList<EClass>>filter(IterableExtensions.<AleJvmModelInferrer.ResolvedClass, EList<EClass>>map(IterableExtensions.<AleJvmModelInferrer.ResolvedClass>filter(st, _function_2), _function_3), _function_4)), _function_5);
          _xblockexpression_1 = Pair.<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>of(it, _map);
        }
        return _xblockexpression_1;
      };
      final List<Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>> a = ListExtensions.<AleJvmModelInferrer.ResolvedClass, Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>>map(classes, _function);
      final Function<Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>, String> _function_1 = (Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>> t) -> {
        return t.getKey().eCls.getName();
      };
      final Function<Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>, String> _function_2 = (Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>> t) -> {
        return t.getKey().eCls.getEPackage().getName();
      };
      final List<Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>> b = IterableExtensions.<Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>>sortWith(a, 
        Comparator.<Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>, String>comparing(_function_1).<String>thenComparing(_function_2));
      final Function1<Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>, List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>> _function_3 = (Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>> p) -> {
        List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> _xblockexpression_1 = null;
        {
          final List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> ret = CollectionLiterals.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>newArrayList();
          final AleJvmModelInferrer.ResolvedClass k = p.getKey();
          Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> _mappedTo = Pair.<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>of(k, null);
          ret.add(_mappedTo);
          final Iterable<AleJvmModelInferrer.ResolvedClass> pv = p.getValue();
          List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> _xifexpression = null;
          if ((pv != null)) {
            final Function1<AleJvmModelInferrer.ResolvedClass, Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> _function_4 = (AleJvmModelInferrer.ResolvedClass l) -> {
              return Pair.<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>of(k, l);
            };
            _xifexpression = IterableExtensions.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>toList(IterableExtensions.<AleJvmModelInferrer.ResolvedClass, Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>map(pv, _function_4));
          } else {
            _xifexpression = CollectionLiterals.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>newArrayList();
          }
          final List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> ll = _xifexpression;
          ret.addAll(ll);
          _xblockexpression_1 = ret;
        }
        return _xblockexpression_1;
      };
      final List<List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>> c = ListExtensions.<Pair<AleJvmModelInferrer.ResolvedClass, Iterable<AleJvmModelInferrer.ResolvedClass>>, List<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>>map(b, _function_3);
      _xblockexpression = IterableExtensions.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>toList(Iterables.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>concat(c));
    }
    return _xblockexpression;
  }
  
  protected void _infer(final AleRoot modelRoot, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    this.root = modelRoot;
    this.preProcess();
    this.inferRevisitorImplementation(acceptor);
    final Consumer<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> _function = (Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> it) -> {
      this.inferOperationInterface(it, acceptor);
      boolean _hasRequiredAnnotation = this._ecoreUtils.hasRequiredAnnotation(it.getKey().eCls);
      boolean _not = (!_hasRequiredAnnotation);
      if (_not) {
        this.inferOperationImplementation(it, acceptor);
      }
    };
    this.resolved.forEach(_function);
  }
  
  private void inferRevisitorImplementation(final IJvmDeclaredTypeAcceptor acceptor) {
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      it.setInterface(true);
      EList<JvmTypeReference> _superTypes = it.getSuperTypes();
      final Function1<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>, JvmTypeReference> _function_1 = (Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> it_1) -> {
        return this.toOperationInterfaceType(it_1.getKey().aleCls);
      };
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this._namingUtils.getRevisitorInterfaceFqn(this.pkg), ((JvmTypeReference[])Conversions.unwrapArray(ListExtensions.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>, JvmTypeReference>map(this.resolved, _function_1), JvmTypeReference.class)));
      this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _typeRef);
      final Function1<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>, Boolean> _function_2 = (Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> it_1) -> {
        return Boolean.valueOf((it_1.getKey().eCls != null));
      };
      final Function1<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>, Boolean> _function_3 = (Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> it_1) -> {
        boolean _isAbstract = it_1.getKey().eCls.isAbstract();
        return Boolean.valueOf((!_isAbstract));
      };
      final Consumer<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>> _function_4 = (Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> r) -> {
        final JvmTypeReference returnType = this.toOperationInterfaceType(r.getKey().aleCls);
        EList<JvmMember> _members = it.getMembers();
        final Procedure1<JvmOperation> _function_5 = (JvmOperation it_1) -> {
          EList<JvmAnnotationReference> _annotations = it_1.getAnnotations();
          JvmAnnotationReference _annotationRef = this._annotationTypesBuilder.annotationRef(Override.class);
          this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, _annotationRef);
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(r.getKey().aleCls, this._namingUtils.getVarName(r.getKey().eCls), this._typeReferenceBuilder.typeRef(r.getKey().genCls.getQualifiedInterfaceName()));
          this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          StringConcatenationClient _xifexpression = null;
          if ((this._aleUtils.isGenerated(r.getKey().aleCls) || (this._aleUtils.findNearestGeneratedParent(r.getKey().aleCls) != null))) {
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("return new ");
                String _qualifiedName = AleJvmModelInferrer.this.toOperationImplType(r.getKey().aleCls).getQualifiedName();
                _builder.append(_qualifiedName);
                _builder.append("(");
                String _varName = AleJvmModelInferrer.this._namingUtils.getVarName(r.getKey().eCls);
                _builder.append(_varName);
                _builder.append(", this);");
              }
            };
            _xifexpression = _client;
          } else {
            StringConcatenationClient _client_1 = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("return null;");
              }
            };
            _xifexpression = _client_1;
          }
          this._jvmTypesBuilder.setBody(it_1, _xifexpression);
        };
        JvmOperation _method = this._jvmTypesBuilder.toMethod(r.getKey().aleCls, this.getDenotationName(r), returnType, _function_5);
        this._jvmTypesBuilder.<JvmOperation>operator_add(_members, _method);
      };
      IterableExtensions.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>filter(IterableExtensions.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>>filter(this.resolved, _function_2), _function_3).forEach(_function_4);
    };
    acceptor.<JvmGenericType>accept(this._jvmTypesBuilder.toClass(this.root, this._namingUtils.getRevisitorInterfaceFqn(this.root)), _function);
  }
  
  public String getDenotationName(final Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> pcls) {
    String _xblockexpression = null;
    {
      final EClass cls = pcls.getKey().eCls;
      String _xifexpression = null;
      AleJvmModelInferrer.ResolvedClass _value = pcls.getValue();
      boolean _tripleEquals = (_value == null);
      if (_tripleEquals) {
        _xifexpression = this._namingUtils.getDenotationName(cls);
      } else {
        StringConcatenation _builder = new StringConcatenation();
        String _denotationName = this._namingUtils.getDenotationName(cls);
        _builder.append(_denotationName);
        _builder.append("__AS__");
        String _denotationName_1 = this._namingUtils.getDenotationName(pcls.getValue().eCls);
        _builder.append(_denotationName_1);
        _xifexpression = _builder.toString();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  private void inferOperationInterface(final Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> r, final IJvmDeclaredTypeAcceptor acceptor) {
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      it.setInterface(true);
      EList<JvmTypeReference> _superTypes = it.getSuperTypes();
      final Function1<AleClass, Boolean> _function_1 = (AleClass it_1) -> {
        return Boolean.valueOf(((!Objects.equal(it_1, r.getKey().aleCls)) && this._aleUtils.isGenerated(it_1)));
      };
      final Function1<AleClass, JvmTypeReference> _function_2 = (AleClass it_1) -> {
        return this._typeReferenceBuilder.typeRef(this._namingUtils.getOperationInterfaceFqn(it_1));
      };
      Iterable<JvmTypeReference> _map = IterableExtensions.<AleClass, JvmTypeReference>map(IterableExtensions.<AleClass>filter(this._aleUtils.getAllAleClasses(r.getKey().eCls, this.root), _function_1), _function_2);
      this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _map);
      EList<JvmMember> _members = it.getMembers();
      final Function1<AleMethod, JvmOperation> _function_3 = (AleMethod m) -> {
        final Procedure1<JvmOperation> _function_4 = (JvmOperation it_1) -> {
          it_1.setAbstract(true);
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          final Function1<JvmFormalParameter, JvmFormalParameter> _function_5 = (JvmFormalParameter it_2) -> {
            return this._jvmTypesBuilder.<JvmFormalParameter>cloneWithProxies(it_2);
          };
          List<JvmFormalParameter> _map_1 = ListExtensions.<JvmFormalParameter, JvmFormalParameter>map(m.getParams(), _function_5);
          this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _map_1);
        };
        return this._jvmTypesBuilder.toMethod(m, m.getName(), m.getType(), _function_4);
      };
      List<JvmOperation> _map_1 = ListExtensions.<AleMethod, JvmOperation>map(r.getKey().aleCls.getMethods(), _function_3);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members, _map_1);
    };
    acceptor.<JvmGenericType>accept(this._jvmTypesBuilder.toClass(r.getKey().aleCls, this._namingUtils.getOperationInterfaceFqn(r.getKey().aleCls)), _function);
  }
  
  private void inferOperationImplementation(final Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> r, final IJvmDeclaredTypeAcceptor acceptor) {
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      final AleClass superOp = this._aleUtils.findNearestGeneratedParent(r.getKey().aleCls);
      it.setAbstract(r.getKey().aleCls.isAbstract());
      EList<JvmTypeReference> _superTypes = it.getSuperTypes();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this._namingUtils.getOperationInterfaceFqn(r.getKey().aleCls));
      this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _typeRef);
      if (((superOp != null) && (!(superOp.isAbstract() || IterableExtensions.<EClass>exists(r.getKey().eCls.getESuperTypes(), ((Function1<EClass, Boolean>) (EClass it_1) -> {
        return Boolean.valueOf(this._ecoreUtils.hasRequiredAnnotation(it_1));
      })))))) {
        EList<JvmTypeReference> _superTypes_1 = it.getSuperTypes();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this._namingUtils.getOperationImplFqn(superOp));
        this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes_1, _typeRef_1);
      }
      final JvmTypeReference asig = this.getAlgSignature();
      EList<JvmMember> _members = it.getMembers();
      JvmField _field = this._jvmTypesBuilder.toField(r.getKey().aleCls, "obj", this._typeReferenceBuilder.typeRef(r.getKey().genCls.getQualifiedInterfaceName()));
      this._jvmTypesBuilder.<JvmField>operator_add(_members, _field);
      EList<JvmMember> _members_1 = it.getMembers();
      JvmField _field_1 = this._jvmTypesBuilder.toField(r.getKey().aleCls, "alg", asig);
      this._jvmTypesBuilder.<JvmField>operator_add(_members_1, _field_1);
      EList<JvmMember> _members_2 = it.getMembers();
      final Procedure1<JvmConstructor> _function_1 = (JvmConstructor it_1) -> {
        EList<JvmFormalParameter> _parameters = it_1.getParameters();
        JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(r.getKey().aleCls, "obj", this._typeReferenceBuilder.typeRef(r.getKey().genCls.getQualifiedInterfaceName()));
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
        JvmFormalParameter _parameter_1 = this._jvmTypesBuilder.toParameter(r.getKey().aleCls, "alg", asig);
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            {
              if (((superOp != null) && (!(superOp.isAbstract() || IterableExtensions.<EClass>exists(r.getKey().eCls.getESuperTypes(), ((Function1<EClass, Boolean>) (EClass it_2) -> {
                return Boolean.valueOf(AleJvmModelInferrer.this._ecoreUtils.hasRequiredAnnotation(it_2));
              })))))) {
                _builder.append("super(obj, alg);");
              }
            }
            _builder.newLineIfNotEmpty();
            _builder.append("this.obj = obj;");
            _builder.newLine();
            _builder.append("this.alg = alg;");
            _builder.newLine();
          }
        };
        this._jvmTypesBuilder.setBody(it_1, _client);
      };
      JvmConstructor _constructor = this._jvmTypesBuilder.toConstructor(r.getKey().aleCls, _function_1);
      this._jvmTypesBuilder.<JvmConstructor>operator_add(_members_2, _constructor);
      final EList<AleMethod> methods = r.getKey().aleCls.getMethods();
      EList<JvmMember> _members_3 = it.getMembers();
      final Function1<AleMethod, Boolean> _function_2 = (AleMethod it_1) -> {
        return Boolean.valueOf((it_1 instanceof ConcreteMethod));
      };
      final Function1<AleMethod, JvmOperation> _function_3 = (AleMethod m) -> {
        final Procedure1<JvmOperation> _function_4 = (JvmOperation it_1) -> {
          it_1.setAbstract((m instanceof AbstractMethod));
          EList<JvmAnnotationReference> _annotations = it_1.getAnnotations();
          JvmAnnotationReference _annotationRef = this._annotationTypesBuilder.annotationRef(Override.class);
          this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, _annotationRef);
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          final Function1<JvmFormalParameter, JvmFormalParameter> _function_5 = (JvmFormalParameter it_2) -> {
            return this._jvmTypesBuilder.<JvmFormalParameter>cloneWithProxies(it_2);
          };
          List<JvmFormalParameter> _map = ListExtensions.<JvmFormalParameter, JvmFormalParameter>map(m.getParams(), _function_5);
          this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _map);
          if ((m instanceof ConcreteMethod)) {
            boolean _contains = r.getKey().aleCls.getMethods().contains(m);
            if (_contains) {
              this._jvmTypesBuilder.setBody(it_1, ((ConcreteMethod)m).getBlock());
            }
          }
        };
        return this._jvmTypesBuilder.toMethod(m, m.getName(), m.getType(), _function_4);
      };
      Iterable<JvmOperation> _map = IterableExtensions.<AleMethod, JvmOperation>map(IterableExtensions.<AleMethod>filter(methods, _function_2), _function_3);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members_3, _map);
    };
    acceptor.<JvmGenericType>accept(this._jvmTypesBuilder.toClass(r.getKey().aleCls, this._namingUtils.getOperationImplFqn(r.getKey().aleCls)), _function);
  }
  
  private JvmTypeReference getAlgSignature() {
    if ((this.cachedRevSignature == null)) {
      final Function1<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>, JvmTypeReference> _function = (Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass> it) -> {
        return this._typeReferenceBuilder.wildcardExtends(this.toOperationInterfaceType(it.getKey().aleCls));
      };
      this.cachedRevSignature = this._typeReferenceBuilder.typeRef(this._namingUtils.getRevisitorInterfaceFqn(this.pkg), ((JvmTypeReference[])Conversions.unwrapArray(ListExtensions.<Pair<AleJvmModelInferrer.ResolvedClass, AleJvmModelInferrer.ResolvedClass>, JvmTypeReference>map(this.resolved, _function), JvmTypeReference.class)));
    }
    return this.cachedRevSignature;
  }
  
  private JvmTypeReference toOperationInterfaceType(final AleClass aleCls) {
    JvmTypeReference _xifexpression = null;
    boolean _isGenerated = this._aleUtils.isGenerated(aleCls);
    if (_isGenerated) {
      _xifexpression = this._typeReferenceBuilder.typeRef(this._namingUtils.getOperationInterfaceFqn(aleCls));
    } else {
      JvmTypeReference _xifexpression_1 = null;
      AleClass _findNearestGeneratedParent = this._aleUtils.findNearestGeneratedParent(aleCls);
      boolean _tripleNotEquals = (_findNearestGeneratedParent != null);
      if (_tripleNotEquals) {
        _xifexpression_1 = this._typeReferenceBuilder.typeRef(this._namingUtils.getOperationInterfaceFqn(this._aleUtils.findNearestGeneratedParent(aleCls)));
      } else {
        _xifexpression_1 = this._typeReferenceBuilder.typeRef(Object.class);
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  private JvmTypeReference toOperationImplType(final AleClass aleCls) {
    JvmTypeReference _xifexpression = null;
    boolean _isGenerated = this._aleUtils.isGenerated(aleCls);
    if (_isGenerated) {
      _xifexpression = this._typeReferenceBuilder.typeRef(this._namingUtils.getOperationImplFqn(aleCls));
    } else {
      JvmTypeReference _xifexpression_1 = null;
      AleClass _findNearestGeneratedParent = this._aleUtils.findNearestGeneratedParent(aleCls);
      boolean _tripleNotEquals = (_findNearestGeneratedParent != null);
      if (_tripleNotEquals) {
        _xifexpression_1 = this._typeReferenceBuilder.typeRef(this._namingUtils.getOperationImplFqn(this._aleUtils.findNearestGeneratedParent(aleCls)));
      } else {
        _xifexpression_1 = this._typeReferenceBuilder.typeRef(Object.class);
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public void infer(final EObject modelRoot, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (modelRoot instanceof AleRoot) {
      _infer((AleRoot)modelRoot, acceptor, isPreIndexingPhase);
      return;
    } else if (modelRoot != null) {
      _infer(modelRoot, acceptor, isPreIndexingPhase);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(modelRoot, acceptor, isPreIndexingPhase).toString());
    }
  }
}
