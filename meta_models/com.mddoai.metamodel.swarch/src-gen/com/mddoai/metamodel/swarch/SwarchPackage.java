/**
 */
package com.mddoai.metamodel.swarch;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.swarch.SwarchFactory
 * @model kind="package"
 * @generated
 */
public interface SwarchPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "swarch";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mddoai.com/mddoai/metamodel/swarch";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "swarch";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SwarchPackage eINSTANCE = com.mddoai.metamodel.swarch.impl.SwarchPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl <em>Software Architecture</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl
	 * @see com.mddoai.metamodel.swarch.impl.SwarchPackageImpl#getSoftwareArchitecture()
	 * @generated
	 */
	int SOFTWARE_ARCHITECTURE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ARCHITECTURE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ARCHITECTURE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Build system</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ARCHITECTURE__BUILD_SYSTEM = 2;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ARCHITECTURE__LANGUAGE = 3;

	/**
	 * The feature id for the '<em><b>Unit test</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ARCHITECTURE__UNIT_TEST = 4;

	/**
	 * The feature id for the '<em><b>Health check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ARCHITECTURE__HEALTH_CHECK = 5;

	/**
	 * The feature id for the '<em><b>Lint check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ARCHITECTURE__LINT_CHECK = 6;

	/**
	 * The number of structural features of the '<em>Software Architecture</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ARCHITECTURE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Software Architecture</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_ARCHITECTURE_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture <em>Software Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software Architecture</em>'.
	 * @see com.mddoai.metamodel.swarch.SoftwareArchitecture
	 * @generated
	 */
	EClass getSoftwareArchitecture();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.swarch.SoftwareArchitecture#getName()
	 * @see #getSoftwareArchitecture()
	 * @generated
	 */
	EAttribute getSoftwareArchitecture_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.swarch.SoftwareArchitecture#getType()
	 * @see #getSoftwareArchitecture()
	 * @generated
	 */
	EAttribute getSoftwareArchitecture_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getBuild_system <em>Build system</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Build system</em>'.
	 * @see com.mddoai.metamodel.swarch.SoftwareArchitecture#getBuild_system()
	 * @see #getSoftwareArchitecture()
	 * @generated
	 */
	EAttribute getSoftwareArchitecture_Build_system();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see com.mddoai.metamodel.swarch.SoftwareArchitecture#getLanguage()
	 * @see #getSoftwareArchitecture()
	 * @generated
	 */
	EAttribute getSoftwareArchitecture_Language();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getUnit_test <em>Unit test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit test</em>'.
	 * @see com.mddoai.metamodel.swarch.SoftwareArchitecture#getUnit_test()
	 * @see #getSoftwareArchitecture()
	 * @generated
	 */
	EAttribute getSoftwareArchitecture_Unit_test();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getHealth_check <em>Health check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Health check</em>'.
	 * @see com.mddoai.metamodel.swarch.SoftwareArchitecture#getHealth_check()
	 * @see #getSoftwareArchitecture()
	 * @generated
	 */
	EAttribute getSoftwareArchitecture_Health_check();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getLint_check <em>Lint check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lint check</em>'.
	 * @see com.mddoai.metamodel.swarch.SoftwareArchitecture#getLint_check()
	 * @see #getSoftwareArchitecture()
	 * @generated
	 */
	EAttribute getSoftwareArchitecture_Lint_check();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SwarchFactory getSwarchFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl <em>Software Architecture</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl
		 * @see com.mddoai.metamodel.swarch.impl.SwarchPackageImpl#getSoftwareArchitecture()
		 * @generated
		 */
		EClass SOFTWARE_ARCHITECTURE = eINSTANCE.getSoftwareArchitecture();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_ARCHITECTURE__NAME = eINSTANCE.getSoftwareArchitecture_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_ARCHITECTURE__TYPE = eINSTANCE.getSoftwareArchitecture_Type();

		/**
		 * The meta object literal for the '<em><b>Build system</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_ARCHITECTURE__BUILD_SYSTEM = eINSTANCE.getSoftwareArchitecture_Build_system();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_ARCHITECTURE__LANGUAGE = eINSTANCE.getSoftwareArchitecture_Language();

		/**
		 * The meta object literal for the '<em><b>Unit test</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_ARCHITECTURE__UNIT_TEST = eINSTANCE.getSoftwareArchitecture_Unit_test();

		/**
		 * The meta object literal for the '<em><b>Health check</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_ARCHITECTURE__HEALTH_CHECK = eINSTANCE.getSoftwareArchitecture_Health_check();

		/**
		 * The meta object literal for the '<em><b>Lint check</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_ARCHITECTURE__LINT_CHECK = eINSTANCE.getSoftwareArchitecture_Lint_check();

	}

} //SwarchPackage
