package ale.xtext.compiler;

import ale.xtext.ale.AleImport;
import ale.xtext.ale.AleRoot;
import ale.xtext.ale.EcoreImport;
import ale.xtext.utils.AleUtils;
import ale.xtext.utils.EcoreUtils;
import ale.xtext.utils.NamingUtils;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class AleJvmModelGenerator extends JvmModelGenerator {
  @Inject
  @Extension
  private AleUtils _aleUtils;
  
  @Inject
  @Extension
  private EcoreUtils _ecoreUtils;
  
  @Inject
  @Extension
  private NamingUtils _namingUtils;
  
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    EObject _head = IteratorExtensions.<EObject>head(input.getAllContents());
    final AleRoot aleRoot = ((AleRoot) _head);
    super.doGenerate(input, fsa);
  }
  
  public GenModel generateGenmodel(final Resource input, final EPackage mainPackage, final AleRoot aleRoot) {
    try {
      GenModel _xblockexpression = null;
      {
        final String projectName = input.getURI().segmentsList().get(1);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("/");
        _builder.append(projectName);
        _builder.append("/model/");
        String _name = aleRoot.getName();
        _builder.append(_name);
        _builder.append(".genmodel");
        final URI ecoreFileUri = URI.createPlatformResourceURI(_builder.toString(), true);
        final ResourceSetImpl resourceSet = new ResourceSetImpl();
        Map<String, Object> _extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
        XMIResourceFactoryImpl _xMIResourceFactoryImpl = new XMIResourceFactoryImpl();
        _extensionToFactoryMap.put("genmodel", _xMIResourceFactoryImpl);
        final Resource resource = resourceSet.createResource(ecoreFileUri);
        ((XMIResource) resource).getDefaultSaveOptions().put(XMLResource.OPTION_ENCODING, "UTF-8");
        final Function1<AleImport, EcoreImport> _function = (AleImport it) -> {
          return it.getRef().getEcoreImport();
        };
        final Function1<EcoreImport, GenModel> _function_1 = (EcoreImport it) -> {
          return this._ecoreUtils.loadCorrespondingGenmodel(it.getUri());
        };
        final Function1<GenModel, List<GenPackage>> _function_2 = (GenModel it) -> {
          return IterableExtensions.<GenPackage>toList(it.getGenPackages());
        };
        final Set<GenPackage> ugp = IterableExtensions.<GenPackage>toSet(Iterables.<GenPackage>concat(ListExtensions.<GenModel, List<GenPackage>>map(ListExtensions.<EcoreImport, GenModel>map(ListExtensions.<AleImport, EcoreImport>map(aleRoot.getAleImports(), _function), _function_1), _function_2)));
        EcoreImport _ecoreImport = aleRoot.getEcoreImport();
        boolean _tripleNotEquals = (_ecoreImport != null);
        if (_tripleNotEquals) {
          ugp.addAll(this._ecoreUtils.loadCorrespondingGenmodel(aleRoot.getEcoreImport().getUri()).getGenPackages());
        }
        GenModel _createGenModel = GenModelFactory.eINSTANCE.createGenModel();
        final Procedure1<GenModel> _function_3 = (GenModel it) -> {
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("/");
          _builder_1.append(projectName);
          _builder_1.append("/src-gen");
          it.setModelDirectory(_builder_1.toString());
          it.setModelPluginID(projectName);
          it.setModelName(StringExtensions.toFirstUpper(aleRoot.getName()));
          it.setComplianceLevel(GenJDKLevel.JDK80_LITERAL);
          it.setCopyrightFields(false);
          EList<GenPackage> _usedGenPackages = it.getUsedGenPackages();
          Iterables.<GenPackage>addAll(_usedGenPackages, ugp);
          it.setOperationReflection(true);
          it.setImportOrganizing(true);
          EList<String> _foreignModel = it.getForeignModel();
          StringConcatenation _builder_2 = new StringConcatenation();
          String _name_1 = aleRoot.getName();
          _builder_2.append(_name_1);
          _builder_2.append(".ecore");
          _foreignModel.add(_builder_2.toString());
          EList<GenPackage> _genPackages = it.getGenPackages();
          GenPackage _createGenPackage = GenModelFactory.eINSTANCE.createGenPackage();
          final Procedure1<GenPackage> _function_4 = (GenPackage it_1) -> {
            it_1.setPrefix(StringExtensions.toFirstUpper(aleRoot.getName()));
            it_1.setDisposableProviderFactory(true);
            it_1.setEcorePackage(mainPackage);
            EList<GenClass> _genClasses = it_1.getGenClasses();
            final Function1<EClass, GenClass> _function_5 = (EClass eClass) -> {
              GenClass _createGenClass = GenModelFactory.eINSTANCE.createGenClass();
              final Procedure1<GenClass> _function_6 = (GenClass it_2) -> {
                it_2.setEcoreClass(eClass);
              };
              return ObjectExtensions.<GenClass>operator_doubleArrow(_createGenClass, _function_6);
            };
            Iterable<GenClass> _map = IterableExtensions.<EClass, GenClass>map(Iterables.<EClass>filter(mainPackage.getEClassifiers(), EClass.class), _function_5);
            Iterables.<GenClass>addAll(_genClasses, _map);
          };
          GenPackage _doubleArrow = ObjectExtensions.<GenPackage>operator_doubleArrow(_createGenPackage, _function_4);
          _genPackages.add(_doubleArrow);
        };
        final GenModel genModel = ObjectExtensions.<GenModel>operator_doubleArrow(_createGenModel, _function_3);
        resource.getContents().add(genModel);
        resource.save(null);
        _xblockexpression = genModel;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public EPackage generateEcore(final Resource input, final AleRoot aleRoot) {
    try {
      EPackage _xblockexpression = null;
      {
        @Extension
        final EcoreFactory factory = EcoreFactory.eINSTANCE;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("/");
        String _get = input.getURI().segmentsList().get(1);
        _builder.append(_get);
        _builder.append("/model/");
        String _name = aleRoot.getName();
        _builder.append(_name);
        _builder.append(".ecore");
        final URI ecoreFileUri = URI.createPlatformResourceURI(_builder.toString(), true);
        final ResourceSetImpl resourceSet = new ResourceSetImpl();
        Map<String, Object> _extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
        EcoreResourceFactoryImpl _ecoreResourceFactoryImpl = new EcoreResourceFactoryImpl();
        _extensionToFactoryMap.put("ecore", _ecoreResourceFactoryImpl);
        final Resource resource = resourceSet.createResource(ecoreFileUri);
        EPackage _createEPackage = factory.createEPackage();
        final Procedure1<EPackage> _function = (EPackage it) -> {
          it.setName(aleRoot.getName());
          it.setNsPrefix(aleRoot.getName());
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("http://");
          String _name_1 = aleRoot.getName();
          _builder_1.append(_name_1);
          it.setNsURI(_builder_1.toString());
        };
        final EPackage ecorePackage = ObjectExtensions.<EPackage>operator_doubleArrow(_createEPackage, _function);
        resource.getContents().add(ecorePackage);
        resource.save(null);
        _xblockexpression = ecorePackage;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
