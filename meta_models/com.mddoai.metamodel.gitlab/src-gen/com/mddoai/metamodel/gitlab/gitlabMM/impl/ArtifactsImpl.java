/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports;
import com.mddoai.metamodel.gitlab.gitlabMM.Artifacts;
import com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Artifacts</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl#getExpireIn <em>Expire In</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl#getExposeAs <em>Expose As</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl#getUntracked <em>Untracked</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl#getExclude <em>Exclude</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl#getReports <em>Reports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArtifactsImpl extends MinimalEObjectImpl.Container implements Artifacts {
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
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<String> paths;

	/**
	 * The default value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected static final ArtifactsWhenType WHEN_EDEFAULT = ArtifactsWhenType.ON_SUCCESS;

	/**
	 * The cached value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected ArtifactsWhenType when = WHEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getExpireIn() <em>Expire In</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpireIn()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPIRE_IN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpireIn() <em>Expire In</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpireIn()
	 * @generated
	 * @ordered
	 */
	protected String expireIn = EXPIRE_IN_EDEFAULT;

	/**
	 * The default value of the '{@link #getExposeAs() <em>Expose As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExposeAs()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPOSE_AS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExposeAs() <em>Expose As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExposeAs()
	 * @generated
	 * @ordered
	 */
	protected String exposeAs = EXPOSE_AS_EDEFAULT;

	/**
	 * The default value of the '{@link #getUntracked() <em>Untracked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUntracked()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean UNTRACKED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUntracked() <em>Untracked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUntracked()
	 * @generated
	 * @ordered
	 */
	protected Boolean untracked = UNTRACKED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExclude() <em>Exclude</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<String> exclude;

	/**
	 * The cached value of the '{@link #getReports() <em>Reports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReports()
	 * @generated
	 * @ordered
	 */
	protected ArtifactReports reports;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArtifactsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.ARTIFACTS;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACTS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getPaths() {
		if (paths == null) {
			paths = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.ARTIFACTS__PATHS);
		}
		return paths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ArtifactsWhenType getWhen() {
		return when;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWhen(ArtifactsWhenType newWhen) {
		ArtifactsWhenType oldWhen = when;
		when = newWhen == null ? WHEN_EDEFAULT : newWhen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACTS__WHEN, oldWhen, when));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getExpireIn() {
		return expireIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExpireIn(String newExpireIn) {
		String oldExpireIn = expireIn;
		expireIn = newExpireIn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACTS__EXPIRE_IN, oldExpireIn,
					expireIn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getExposeAs() {
		return exposeAs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExposeAs(String newExposeAs) {
		String oldExposeAs = exposeAs;
		exposeAs = newExposeAs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACTS__EXPOSE_AS, oldExposeAs,
					exposeAs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getUntracked() {
		return untracked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUntracked(Boolean newUntracked) {
		Boolean oldUntracked = untracked;
		untracked = newUntracked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACTS__UNTRACKED, oldUntracked,
					untracked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getExclude() {
		if (exclude == null) {
			exclude = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.ARTIFACTS__EXCLUDE);
		}
		return exclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ArtifactReports getReports() {
		return reports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReports(ArtifactReports newReports, NotificationChain msgs) {
		ArtifactReports oldReports = reports;
		reports = newReports;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.ARTIFACTS__REPORTS, oldReports, newReports);
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
	public void setReports(ArtifactReports newReports) {
		if (newReports != reports) {
			NotificationChain msgs = null;
			if (reports != null)
				msgs = ((InternalEObject) reports).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.ARTIFACTS__REPORTS, null, msgs);
			if (newReports != null)
				msgs = ((InternalEObject) newReports).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.ARTIFACTS__REPORTS, null, msgs);
			msgs = basicSetReports(newReports, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACTS__REPORTS, newReports,
					newReports));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.ARTIFACTS__REPORTS:
			return basicSetReports(null, msgs);
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
		case GitlabMMPackage.ARTIFACTS__NAME:
			return getName();
		case GitlabMMPackage.ARTIFACTS__PATHS:
			return getPaths();
		case GitlabMMPackage.ARTIFACTS__WHEN:
			return getWhen();
		case GitlabMMPackage.ARTIFACTS__EXPIRE_IN:
			return getExpireIn();
		case GitlabMMPackage.ARTIFACTS__EXPOSE_AS:
			return getExposeAs();
		case GitlabMMPackage.ARTIFACTS__UNTRACKED:
			return getUntracked();
		case GitlabMMPackage.ARTIFACTS__EXCLUDE:
			return getExclude();
		case GitlabMMPackage.ARTIFACTS__REPORTS:
			return getReports();
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
		case GitlabMMPackage.ARTIFACTS__NAME:
			setName((String) newValue);
			return;
		case GitlabMMPackage.ARTIFACTS__PATHS:
			getPaths().clear();
			getPaths().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.ARTIFACTS__WHEN:
			setWhen((ArtifactsWhenType) newValue);
			return;
		case GitlabMMPackage.ARTIFACTS__EXPIRE_IN:
			setExpireIn((String) newValue);
			return;
		case GitlabMMPackage.ARTIFACTS__EXPOSE_AS:
			setExposeAs((String) newValue);
			return;
		case GitlabMMPackage.ARTIFACTS__UNTRACKED:
			setUntracked((Boolean) newValue);
			return;
		case GitlabMMPackage.ARTIFACTS__EXCLUDE:
			getExclude().clear();
			getExclude().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.ARTIFACTS__REPORTS:
			setReports((ArtifactReports) newValue);
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
		case GitlabMMPackage.ARTIFACTS__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACTS__PATHS:
			getPaths().clear();
			return;
		case GitlabMMPackage.ARTIFACTS__WHEN:
			setWhen(WHEN_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACTS__EXPIRE_IN:
			setExpireIn(EXPIRE_IN_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACTS__EXPOSE_AS:
			setExposeAs(EXPOSE_AS_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACTS__UNTRACKED:
			setUntracked(UNTRACKED_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACTS__EXCLUDE:
			getExclude().clear();
			return;
		case GitlabMMPackage.ARTIFACTS__REPORTS:
			setReports((ArtifactReports) null);
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
		case GitlabMMPackage.ARTIFACTS__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case GitlabMMPackage.ARTIFACTS__PATHS:
			return paths != null && !paths.isEmpty();
		case GitlabMMPackage.ARTIFACTS__WHEN:
			return when != WHEN_EDEFAULT;
		case GitlabMMPackage.ARTIFACTS__EXPIRE_IN:
			return EXPIRE_IN_EDEFAULT == null ? expireIn != null : !EXPIRE_IN_EDEFAULT.equals(expireIn);
		case GitlabMMPackage.ARTIFACTS__EXPOSE_AS:
			return EXPOSE_AS_EDEFAULT == null ? exposeAs != null : !EXPOSE_AS_EDEFAULT.equals(exposeAs);
		case GitlabMMPackage.ARTIFACTS__UNTRACKED:
			return UNTRACKED_EDEFAULT == null ? untracked != null : !UNTRACKED_EDEFAULT.equals(untracked);
		case GitlabMMPackage.ARTIFACTS__EXCLUDE:
			return exclude != null && !exclude.isEmpty();
		case GitlabMMPackage.ARTIFACTS__REPORTS:
			return reports != null;
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
		result.append(", paths: ");
		result.append(paths);
		result.append(", when: ");
		result.append(when);
		result.append(", expireIn: ");
		result.append(expireIn);
		result.append(", exposeAs: ");
		result.append(exposeAs);
		result.append(", untracked: ");
		result.append(untracked);
		result.append(", exclude: ");
		result.append(exclude);
		result.append(')');
		return result.toString();
	}

} //ArtifactsImpl
