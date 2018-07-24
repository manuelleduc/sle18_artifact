/**
 * generated by Xtext 2.14.0
 */
package ale.xtext.ale.impl;

import ale.xtext.ale.AbstractMethod;
import ale.xtext.ale.AleClass;
import ale.xtext.ale.AleFactory;
import ale.xtext.ale.AleImport;
import ale.xtext.ale.AleMethod;
import ale.xtext.ale.AlePackage;
import ale.xtext.ale.AleRoot;
import ale.xtext.ale.ConcreteMethod;
import ale.xtext.ale.DefMethod;
import ale.xtext.ale.EcoreImport;
import ale.xtext.ale.OverrideMethod;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.xtext.common.types.TypesPackage;

import org.eclipse.xtext.xbase.XbasePackage;

import org.eclipse.xtext.xtype.XtypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AlePackageImpl extends EPackageImpl implements AlePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass aleRootEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ecoreImportEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass aleImportEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass aleClassEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass aleMethodEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass concreteMethodEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass abstractMethodEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass defMethodEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass overrideMethodEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see ale.xtext.ale.AlePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private AlePackageImpl()
  {
    super(eNS_URI, AleFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   *
   * <p>This method is used to initialize {@link AlePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static AlePackage init()
  {
    if (isInited) return (AlePackage)EPackage.Registry.INSTANCE.getEPackage(AlePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredAlePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    AlePackageImpl theAlePackage = registeredAlePackage instanceof AlePackageImpl ? (AlePackageImpl)registeredAlePackage : new AlePackageImpl();

    isInited = true;

    // Initialize simple dependencies
    TypesPackage.eINSTANCE.eClass();
    XbasePackage.eINSTANCE.eClass();
    XtypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theAlePackage.createPackageContents();

    // Initialize created meta-data
    theAlePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theAlePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(AlePackage.eNS_URI, theAlePackage);
    return theAlePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAleRoot()
  {
    return aleRootEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAleRoot_Name()
  {
    return (EAttribute)aleRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAleRoot_JavaImports()
  {
    return (EReference)aleRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAleRoot_EcoreImport()
  {
    return (EReference)aleRootEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAleRoot_AleImports()
  {
    return (EReference)aleRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAleRoot_Classes()
  {
    return (EReference)aleRootEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEcoreImport()
  {
    return ecoreImportEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEcoreImport_Uri()
  {
    return (EAttribute)ecoreImportEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAleImport()
  {
    return aleImportEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAleImport_Ref()
  {
    return (EReference)aleImportEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAleClass()
  {
    return aleClassEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAleClass_Abstract()
  {
    return (EAttribute)aleClassEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAleClass_Name()
  {
    return (EAttribute)aleClassEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAleClass_Methods()
  {
    return (EReference)aleClassEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAleMethod()
  {
    return aleMethodEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAleMethod_Type()
  {
    return (EReference)aleMethodEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAleMethod_Name()
  {
    return (EAttribute)aleMethodEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAleMethod_Params()
  {
    return (EReference)aleMethodEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConcreteMethod()
  {
    return concreteMethodEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConcreteMethod_Block()
  {
    return (EReference)concreteMethodEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAbstractMethod()
  {
    return abstractMethodEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDefMethod()
  {
    return defMethodEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOverrideMethod()
  {
    return overrideMethodEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AleFactory getAleFactory()
  {
    return (AleFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    aleRootEClass = createEClass(ALE_ROOT);
    createEAttribute(aleRootEClass, ALE_ROOT__NAME);
    createEReference(aleRootEClass, ALE_ROOT__JAVA_IMPORTS);
    createEReference(aleRootEClass, ALE_ROOT__ECORE_IMPORT);
    createEReference(aleRootEClass, ALE_ROOT__ALE_IMPORTS);
    createEReference(aleRootEClass, ALE_ROOT__CLASSES);

    ecoreImportEClass = createEClass(ECORE_IMPORT);
    createEAttribute(ecoreImportEClass, ECORE_IMPORT__URI);

    aleImportEClass = createEClass(ALE_IMPORT);
    createEReference(aleImportEClass, ALE_IMPORT__REF);

    aleClassEClass = createEClass(ALE_CLASS);
    createEAttribute(aleClassEClass, ALE_CLASS__ABSTRACT);
    createEAttribute(aleClassEClass, ALE_CLASS__NAME);
    createEReference(aleClassEClass, ALE_CLASS__METHODS);

    aleMethodEClass = createEClass(ALE_METHOD);
    createEReference(aleMethodEClass, ALE_METHOD__TYPE);
    createEAttribute(aleMethodEClass, ALE_METHOD__NAME);
    createEReference(aleMethodEClass, ALE_METHOD__PARAMS);

    concreteMethodEClass = createEClass(CONCRETE_METHOD);
    createEReference(concreteMethodEClass, CONCRETE_METHOD__BLOCK);

    abstractMethodEClass = createEClass(ABSTRACT_METHOD);

    defMethodEClass = createEClass(DEF_METHOD);

    overrideMethodEClass = createEClass(OVERRIDE_METHOD);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    XtypePackage theXtypePackage = (XtypePackage)EPackage.Registry.INSTANCE.getEPackage(XtypePackage.eNS_URI);
    TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);
    XbasePackage theXbasePackage = (XbasePackage)EPackage.Registry.INSTANCE.getEPackage(XbasePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    concreteMethodEClass.getESuperTypes().add(this.getAleMethod());
    abstractMethodEClass.getESuperTypes().add(this.getAleMethod());
    defMethodEClass.getESuperTypes().add(this.getConcreteMethod());
    overrideMethodEClass.getESuperTypes().add(this.getConcreteMethod());

    // Initialize classes and features; add operations and parameters
    initEClass(aleRootEClass, AleRoot.class, "AleRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAleRoot_Name(), ecorePackage.getEString(), "name", null, 0, 1, AleRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAleRoot_JavaImports(), theXtypePackage.getXImportSection(), null, "javaImports", null, 0, 1, AleRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAleRoot_EcoreImport(), this.getEcoreImport(), null, "ecoreImport", null, 0, 1, AleRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAleRoot_AleImports(), this.getAleImport(), null, "aleImports", null, 0, -1, AleRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAleRoot_Classes(), this.getAleClass(), null, "classes", null, 0, -1, AleRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ecoreImportEClass, EcoreImport.class, "EcoreImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEcoreImport_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, EcoreImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(aleImportEClass, AleImport.class, "AleImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAleImport_Ref(), this.getAleRoot(), null, "ref", null, 0, 1, AleImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(aleClassEClass, AleClass.class, "AleClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAleClass_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, AleClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAleClass_Name(), ecorePackage.getEString(), "name", null, 0, 1, AleClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAleClass_Methods(), this.getAleMethod(), null, "methods", null, 0, -1, AleClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(aleMethodEClass, AleMethod.class, "AleMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAleMethod_Type(), theTypesPackage.getJvmTypeReference(), null, "type", null, 0, 1, AleMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAleMethod_Name(), ecorePackage.getEString(), "name", null, 0, 1, AleMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAleMethod_Params(), theTypesPackage.getJvmFormalParameter(), null, "params", null, 0, -1, AleMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(concreteMethodEClass, ConcreteMethod.class, "ConcreteMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConcreteMethod_Block(), theXbasePackage.getXExpression(), null, "block", null, 0, 1, ConcreteMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(abstractMethodEClass, AbstractMethod.class, "AbstractMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(defMethodEClass, DefMethod.class, "DefMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(overrideMethodEClass, OverrideMethod.class, "OverrideMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //AlePackageImpl
