/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Publish Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PublishStepImpl#getPublish <em>Publish</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PublishStepImpl#getArtifact <em>Artifact</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PublishStepImpl extends StepImpl implements PublishStep {
	/**
	 * The default value of the '{@link #getPublish() <em>Publish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublish()
	 * @generated
	 * @ordered
	 */
	protected static final String PUBLISH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPublish() <em>Publish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublish()
	 * @generated
	 * @ordered
	 */
	protected String publish = PUBLISH_EDEFAULT;

	/**
	 * The default value of the '{@link #getArtifact() <em>Artifact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifact()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifact() <em>Artifact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifact()
	 * @generated
	 * @ordered
	 */
	protected String artifact = ARTIFACT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PublishStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.PUBLISH_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPublish() {
		return publish;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPublish(String newPublish) {
		String oldPublish = publish;
		publish = newPublish;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PUBLISH_STEP__PUBLISH,
					oldPublish, publish));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getArtifact() {
		return artifact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArtifact(String newArtifact) {
		String oldArtifact = artifact;
		artifact = newArtifact;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PUBLISH_STEP__ARTIFACT,
					oldArtifact, artifact));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.PUBLISH_STEP__PUBLISH:
			return getPublish();
		case AzuredevopsMMPackage.PUBLISH_STEP__ARTIFACT:
			return getArtifact();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case AzuredevopsMMPackage.PUBLISH_STEP__PUBLISH:
			setPublish((String) newValue);
			return;
		case AzuredevopsMMPackage.PUBLISH_STEP__ARTIFACT:
			setArtifact((String) newValue);
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
		case AzuredevopsMMPackage.PUBLISH_STEP__PUBLISH:
			setPublish(PUBLISH_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PUBLISH_STEP__ARTIFACT:
			setArtifact(ARTIFACT_EDEFAULT);
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
		case AzuredevopsMMPackage.PUBLISH_STEP__PUBLISH:
			return PUBLISH_EDEFAULT == null ? publish != null : !PUBLISH_EDEFAULT.equals(publish);
		case AzuredevopsMMPackage.PUBLISH_STEP__ARTIFACT:
			return ARTIFACT_EDEFAULT == null ? artifact != null : !ARTIFACT_EDEFAULT.equals(artifact);
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
		result.append(" (publish: ");
		result.append(publish);
		result.append(", artifact: ");
		result.append(artifact);
		result.append(')');
		return result.toString();
	}

} //PublishStepImpl
