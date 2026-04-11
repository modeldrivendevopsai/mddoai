/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.ArtifactDownloadItem;
import com.mddoai.metamodel.bambooMM.ArtifactDownloadTask;
import com.mddoai.metamodel.bambooMM.BambooPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Artifact Download Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadTaskImpl#getSourcePlan <em>Source Plan</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadTaskImpl#getDestination <em>Destination</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadTaskImpl#getArtifacts <em>Artifacts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArtifactDownloadTaskImpl extends TaskImpl implements ArtifactDownloadTask {
	/**
	 * The default value of the '{@link #getSourcePlan() <em>Source Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePlan()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_PLAN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourcePlan() <em>Source Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePlan()
	 * @generated
	 * @ordered
	 */
	protected String sourcePlan = SOURCE_PLAN_EDEFAULT;

	/**
	 * The default value of the '{@link #getDestination() <em>Destination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestination()
	 * @generated
	 * @ordered
	 */
	protected static final String DESTINATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDestination() <em>Destination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestination()
	 * @generated
	 * @ordered
	 */
	protected String destination = DESTINATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArtifacts() <em>Artifacts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifacts()
	 * @generated
	 * @ordered
	 */
	protected EList<ArtifactDownloadItem> artifacts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArtifactDownloadTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.ARTIFACT_DOWNLOAD_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSourcePlan() {
		return sourcePlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSourcePlan(String newSourcePlan) {
		String oldSourcePlan = sourcePlan;
		sourcePlan = newSourcePlan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ARTIFACT_DOWNLOAD_TASK__SOURCE_PLAN,
					oldSourcePlan, sourcePlan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDestination() {
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDestination(String newDestination) {
		String oldDestination = destination;
		destination = newDestination;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ARTIFACT_DOWNLOAD_TASK__DESTINATION,
					oldDestination, destination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ArtifactDownloadItem> getArtifacts() {
		if (artifacts == null) {
			artifacts = new EObjectContainmentEList<ArtifactDownloadItem>(ArtifactDownloadItem.class, this,
					BambooPackage.ARTIFACT_DOWNLOAD_TASK__ARTIFACTS);
		}
		return artifacts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__ARTIFACTS:
			return ((InternalEList<?>) getArtifacts()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__SOURCE_PLAN:
			return getSourcePlan();
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__DESTINATION:
			return getDestination();
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__ARTIFACTS:
			return getArtifacts();
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
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__SOURCE_PLAN:
			setSourcePlan((String) newValue);
			return;
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__DESTINATION:
			setDestination((String) newValue);
			return;
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__ARTIFACTS:
			getArtifacts().clear();
			getArtifacts().addAll((Collection<? extends ArtifactDownloadItem>) newValue);
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
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__SOURCE_PLAN:
			setSourcePlan(SOURCE_PLAN_EDEFAULT);
			return;
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__DESTINATION:
			setDestination(DESTINATION_EDEFAULT);
			return;
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__ARTIFACTS:
			getArtifacts().clear();
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
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__SOURCE_PLAN:
			return SOURCE_PLAN_EDEFAULT == null ? sourcePlan != null : !SOURCE_PLAN_EDEFAULT.equals(sourcePlan);
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__DESTINATION:
			return DESTINATION_EDEFAULT == null ? destination != null : !DESTINATION_EDEFAULT.equals(destination);
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK__ARTIFACTS:
			return artifacts != null && !artifacts.isEmpty();
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
		result.append(" (sourcePlan: ");
		result.append(sourcePlan);
		result.append(", destination: ");
		result.append(destination);
		result.append(')');
		return result.toString();
	}

} //ArtifactDownloadTaskImpl
