/**
 */
package com.mddoai.metamodel.swarch.impl;

import com.mddoai.metamodel.swarch.SoftwareArchitecture;
import com.mddoai.metamodel.swarch.SwarchFactory;
import com.mddoai.metamodel.swarch.SwarchPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SwarchPackageImpl extends EPackageImpl implements SwarchPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softwareArchitectureEClass = null;

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
	 * @see com.mddoai.metamodel.swarch.SwarchPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SwarchPackageImpl() {
		super(eNS_URI, SwarchFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SwarchPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SwarchPackage init() {
		if (isInited)
			return (SwarchPackage) EPackage.Registry.INSTANCE.getEPackage(SwarchPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredSwarchPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		SwarchPackageImpl theSwarchPackage = registeredSwarchPackage instanceof SwarchPackageImpl
				? (SwarchPackageImpl) registeredSwarchPackage
				: new SwarchPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theSwarchPackage.createPackageContents();

		// Initialize created meta-data
		theSwarchPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSwarchPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SwarchPackage.eNS_URI, theSwarchPackage);
		return theSwarchPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSoftwareArchitecture() {
		return softwareArchitectureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSoftwareArchitecture_Name() {
		return (EAttribute) softwareArchitectureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSoftwareArchitecture_Type() {
		return (EAttribute) softwareArchitectureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSoftwareArchitecture_Build_system() {
		return (EAttribute) softwareArchitectureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSoftwareArchitecture_Language() {
		return (EAttribute) softwareArchitectureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSoftwareArchitecture_Unit_test() {
		return (EAttribute) softwareArchitectureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSoftwareArchitecture_Health_check() {
		return (EAttribute) softwareArchitectureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSoftwareArchitecture_Lint_check() {
		return (EAttribute) softwareArchitectureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SwarchFactory getSwarchFactory() {
		return (SwarchFactory) getEFactoryInstance();
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
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		softwareArchitectureEClass = createEClass(SOFTWARE_ARCHITECTURE);
		createEAttribute(softwareArchitectureEClass, SOFTWARE_ARCHITECTURE__NAME);
		createEAttribute(softwareArchitectureEClass, SOFTWARE_ARCHITECTURE__TYPE);
		createEAttribute(softwareArchitectureEClass, SOFTWARE_ARCHITECTURE__BUILD_SYSTEM);
		createEAttribute(softwareArchitectureEClass, SOFTWARE_ARCHITECTURE__LANGUAGE);
		createEAttribute(softwareArchitectureEClass, SOFTWARE_ARCHITECTURE__UNIT_TEST);
		createEAttribute(softwareArchitectureEClass, SOFTWARE_ARCHITECTURE__HEALTH_CHECK);
		createEAttribute(softwareArchitectureEClass, SOFTWARE_ARCHITECTURE__LINT_CHECK);
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
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(softwareArchitectureEClass, SoftwareArchitecture.class, "SoftwareArchitecture", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSoftwareArchitecture_Name(), ecorePackage.getEString(), "name", null, 1, 1,
				SoftwareArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoftwareArchitecture_Type(), ecorePackage.getEString(), "type", null, 1, 1,
				SoftwareArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoftwareArchitecture_Build_system(), ecorePackage.getEString(), "build_system", null, 1, 1,
				SoftwareArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoftwareArchitecture_Language(), ecorePackage.getEString(), "language", null, 1, 1,
				SoftwareArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoftwareArchitecture_Unit_test(), ecorePackage.getEString(), "unit_test", null, 0, 1,
				SoftwareArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoftwareArchitecture_Health_check(), ecorePackage.getEString(), "health_check", null, 0, 1,
				SoftwareArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoftwareArchitecture_Lint_check(), ecorePackage.getEString(), "lint_check", null, 0, 1,
				SoftwareArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //SwarchPackageImpl
