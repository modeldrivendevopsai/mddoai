/**
 */
package com.mddoai.metamodel.swarch;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.swarch.SwarchPackage
 * @generated
 */
public interface SwarchFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SwarchFactory eINSTANCE = com.mddoai.metamodel.swarch.impl.SwarchFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Software Architecture</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Software Architecture</em>'.
	 * @generated
	 */
	SoftwareArchitecture createSoftwareArchitecture();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SwarchPackage getSwarchPackage();

} //SwarchFactory
