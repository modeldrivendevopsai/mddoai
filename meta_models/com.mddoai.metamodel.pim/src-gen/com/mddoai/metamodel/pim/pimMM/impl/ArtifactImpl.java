/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.Artifact;
import com.mddoai.metamodel.pim.pimMM.Expression;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

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
 * An implementation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.ArtifactImpl#getArtifactName <em>Artifact Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.ArtifactImpl#getIncludePaths <em>Include Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.ArtifactImpl#getExcludePaths <em>Exclude Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.ArtifactImpl#getRetentionTime <em>Retention Time</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.ArtifactImpl#isStore <em>Store</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArtifactImpl extends NonConditionalStepImpl implements Artifact {
	/**
	 * The cached value of the '{@link #getArtifactName() <em>Artifact Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactName()
	 * @generated
	 * @ordered
	 */
	protected Expression artifactName;

	/**
	 * The cached value of the '{@link #getIncludePaths() <em>Include Paths</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludePaths()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> includePaths;

	/**
	 * The cached value of the '{@link #getExcludePaths() <em>Exclude Paths</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludePaths()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> excludePaths;

	/**
	 * The cached value of the '{@link #getRetentionTime() <em>Retention Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRetentionTime()
	 * @generated
	 * @ordered
	 */
	protected Expression retentionTime;

	/**
	 * The default value of the '{@link #isStore() <em>Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStore()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STORE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStore() <em>Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStore()
	 * @generated
	 * @ordered
	 */
	protected boolean store = STORE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArtifactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PimMMPackage.Literals.ARTIFACT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getArtifactName() {
		return artifactName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArtifactName(Expression newArtifactName, NotificationChain msgs) {
		Expression oldArtifactName = artifactName;
		artifactName = newArtifactName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PimMMPackage.ARTIFACT__ARTIFACT_NAME, oldArtifactName, newArtifactName);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArtifactName(Expression newArtifactName) {
		if (newArtifactName != artifactName) {
			NotificationChain msgs = null;
			if (artifactName != null)
				msgs = ((InternalEObject) artifactName).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.ARTIFACT__ARTIFACT_NAME, null, msgs);
			if (newArtifactName != null)
				msgs = ((InternalEObject) newArtifactName).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.ARTIFACT__ARTIFACT_NAME, null, msgs);
			msgs = basicSetArtifactName(newArtifactName, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.ARTIFACT__ARTIFACT_NAME, newArtifactName,
					newArtifactName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getIncludePaths() {
		if (includePaths == null) {
			includePaths = new EObjectContainmentEList<Expression>(Expression.class, this,
					PimMMPackage.ARTIFACT__INCLUDE_PATHS);
		}
		return includePaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getExcludePaths() {
		if (excludePaths == null) {
			excludePaths = new EObjectContainmentEList<Expression>(Expression.class, this,
					PimMMPackage.ARTIFACT__EXCLUDE_PATHS);
		}
		return excludePaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getRetentionTime() {
		return retentionTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRetentionTime(Expression newRetentionTime, NotificationChain msgs) {
		Expression oldRetentionTime = retentionTime;
		retentionTime = newRetentionTime;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PimMMPackage.ARTIFACT__RETENTION_TIME, oldRetentionTime, newRetentionTime);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRetentionTime(Expression newRetentionTime) {
		if (newRetentionTime != retentionTime) {
			NotificationChain msgs = null;
			if (retentionTime != null)
				msgs = ((InternalEObject) retentionTime).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.ARTIFACT__RETENTION_TIME, null, msgs);
			if (newRetentionTime != null)
				msgs = ((InternalEObject) newRetentionTime).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.ARTIFACT__RETENTION_TIME, null, msgs);
			msgs = basicSetRetentionTime(newRetentionTime, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.ARTIFACT__RETENTION_TIME,
					newRetentionTime, newRetentionTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isStore() {
		return store;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStore(boolean newStore) {
		boolean oldStore = store;
		store = newStore;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.ARTIFACT__STORE, oldStore, store));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.ARTIFACT__ARTIFACT_NAME:
			return basicSetArtifactName(null, msgs);
		case PimMMPackage.ARTIFACT__INCLUDE_PATHS:
			return ((InternalEList<?>) getIncludePaths()).basicRemove(otherEnd, msgs);
		case PimMMPackage.ARTIFACT__EXCLUDE_PATHS:
			return ((InternalEList<?>) getExcludePaths()).basicRemove(otherEnd, msgs);
		case PimMMPackage.ARTIFACT__RETENTION_TIME:
			return basicSetRetentionTime(null, msgs);
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
		case PimMMPackage.ARTIFACT__ARTIFACT_NAME:
			return getArtifactName();
		case PimMMPackage.ARTIFACT__INCLUDE_PATHS:
			return getIncludePaths();
		case PimMMPackage.ARTIFACT__EXCLUDE_PATHS:
			return getExcludePaths();
		case PimMMPackage.ARTIFACT__RETENTION_TIME:
			return getRetentionTime();
		case PimMMPackage.ARTIFACT__STORE:
			return isStore();
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
		case PimMMPackage.ARTIFACT__ARTIFACT_NAME:
			setArtifactName((Expression) newValue);
			return;
		case PimMMPackage.ARTIFACT__INCLUDE_PATHS:
			getIncludePaths().clear();
			getIncludePaths().addAll((Collection<? extends Expression>) newValue);
			return;
		case PimMMPackage.ARTIFACT__EXCLUDE_PATHS:
			getExcludePaths().clear();
			getExcludePaths().addAll((Collection<? extends Expression>) newValue);
			return;
		case PimMMPackage.ARTIFACT__RETENTION_TIME:
			setRetentionTime((Expression) newValue);
			return;
		case PimMMPackage.ARTIFACT__STORE:
			setStore((Boolean) newValue);
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
		case PimMMPackage.ARTIFACT__ARTIFACT_NAME:
			setArtifactName((Expression) null);
			return;
		case PimMMPackage.ARTIFACT__INCLUDE_PATHS:
			getIncludePaths().clear();
			return;
		case PimMMPackage.ARTIFACT__EXCLUDE_PATHS:
			getExcludePaths().clear();
			return;
		case PimMMPackage.ARTIFACT__RETENTION_TIME:
			setRetentionTime((Expression) null);
			return;
		case PimMMPackage.ARTIFACT__STORE:
			setStore(STORE_EDEFAULT);
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
		case PimMMPackage.ARTIFACT__ARTIFACT_NAME:
			return artifactName != null;
		case PimMMPackage.ARTIFACT__INCLUDE_PATHS:
			return includePaths != null && !includePaths.isEmpty();
		case PimMMPackage.ARTIFACT__EXCLUDE_PATHS:
			return excludePaths != null && !excludePaths.isEmpty();
		case PimMMPackage.ARTIFACT__RETENTION_TIME:
			return retentionTime != null;
		case PimMMPackage.ARTIFACT__STORE:
			return store != STORE_EDEFAULT;
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
		result.append(" (store: ");
		result.append(store);
		result.append(')');
		return result.toString();
	}

} //ArtifactImpl
