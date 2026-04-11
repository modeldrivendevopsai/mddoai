/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.Job;
import com.mddoai.metamodel.bambooMM.Stage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.StageImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.StageImpl#isManual <em>Manual</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.StageImpl#isFinalStage <em>Final Stage</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.StageImpl#getJobRefs <em>Job Refs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StageImpl extends MinimalEObjectImpl.Container implements Stage {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isManual() <em>Manual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManual()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANUAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isManual() <em>Manual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isManual()
	 * @generated
	 * @ordered
	 */
	protected boolean manual = MANUAL_EDEFAULT;

	/**
	 * The default value of the '{@link #isFinalStage() <em>Final Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinalStage()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_STAGE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinalStage() <em>Final Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinalStage()
	 * @generated
	 * @ordered
	 */
	protected boolean finalStage = FINAL_STAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getJobRefs() <em>Job Refs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJobRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> jobRefs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.STAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.STAGE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isManual() {
		return manual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setManual(boolean newManual) {
		boolean oldManual = manual;
		manual = newManual;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.STAGE__MANUAL, oldManual, manual));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFinalStage() {
		return finalStage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFinalStage(boolean newFinalStage) {
		boolean oldFinalStage = finalStage;
		finalStage = newFinalStage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.STAGE__FINAL_STAGE, oldFinalStage,
					finalStage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Job> getJobRefs() {
		if (jobRefs == null) {
			jobRefs = new EObjectResolvingEList<Job>(Job.class, this, BambooPackage.STAGE__JOB_REFS);
		}
		return jobRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.STAGE__NAME:
			return getName();
		case BambooPackage.STAGE__MANUAL:
			return isManual();
		case BambooPackage.STAGE__FINAL_STAGE:
			return isFinalStage();
		case BambooPackage.STAGE__JOB_REFS:
			return getJobRefs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case BambooPackage.STAGE__NAME:
			setName((String) newValue);
			return;
		case BambooPackage.STAGE__MANUAL:
			setManual((Boolean) newValue);
			return;
		case BambooPackage.STAGE__FINAL_STAGE:
			setFinalStage((Boolean) newValue);
			return;
		case BambooPackage.STAGE__JOB_REFS:
			getJobRefs().clear();
			getJobRefs().addAll((Collection<? extends Job>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case BambooPackage.STAGE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case BambooPackage.STAGE__MANUAL:
			setManual(MANUAL_EDEFAULT);
			return;
		case BambooPackage.STAGE__FINAL_STAGE:
			setFinalStage(FINAL_STAGE_EDEFAULT);
			return;
		case BambooPackage.STAGE__JOB_REFS:
			getJobRefs().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case BambooPackage.STAGE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case BambooPackage.STAGE__MANUAL:
			return manual != MANUAL_EDEFAULT;
		case BambooPackage.STAGE__FINAL_STAGE:
			return finalStage != FINAL_STAGE_EDEFAULT;
		case BambooPackage.STAGE__JOB_REFS:
			return jobRefs != null && !jobRefs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", manual: ");
		result.append(manual);
		result.append(", finalStage: ");
		result.append(finalStage);
		result.append(')');
		return result.toString();
	}

} //StageImpl
