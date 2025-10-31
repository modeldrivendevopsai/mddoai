/**
 */
package com.mddoai.metamodel.swarch.impl;

import com.mddoai.metamodel.swarch.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SwarchFactoryImpl extends EFactoryImpl implements SwarchFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SwarchFactory init() {
		try {
			SwarchFactory theSwarchFactory = (SwarchFactory) EPackage.Registry.INSTANCE
					.getEFactory(SwarchPackage.eNS_URI);
			if (theSwarchFactory != null) {
				return theSwarchFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SwarchFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwarchFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case SwarchPackage.SOFTWARE_ARCHITECTURE:
			return createSoftwareArchitecture();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SoftwareArchitecture createSoftwareArchitecture() {
		SoftwareArchitectureImpl softwareArchitecture = new SoftwareArchitectureImpl();
		return softwareArchitecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SwarchPackage getSwarchPackage() {
		return (SwarchPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SwarchPackage getPackage() {
		return SwarchPackage.eINSTANCE;
	}

} //SwarchFactoryImpl
