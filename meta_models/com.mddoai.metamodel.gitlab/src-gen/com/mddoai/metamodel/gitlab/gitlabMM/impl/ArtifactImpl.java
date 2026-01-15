/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.Artifact;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Path;
import com.mddoai.metamodel.gitlab.gitlabMM.Report;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
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
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl#getReports <em>Reports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl#getExpireIn <em>Expire In</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl#getExpose_as <em>Expose as</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl#isUntracked <em>Untracked</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl#getExclude <em>Exclude</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArtifactImpl extends MinimalEObjectImpl.Container implements Artifact {
	/**
	 * The default value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected static final String WHEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected String when = WHEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReports() <em>Reports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReports()
	 * @generated
	 * @ordered
	 */
	protected Report reports;

	/**
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<Path> paths;

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
	 * The default value of the '{@link #getExpose_as() <em>Expose as</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpose_as()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPOSE_AS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpose_as() <em>Expose as</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpose_as()
	 * @generated
	 * @ordered
	 */
	protected String expose_as = EXPOSE_AS_EDEFAULT;

	/**
	 * The default value of the '{@link #isUntracked() <em>Untracked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUntracked()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNTRACKED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUntracked() <em>Untracked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUntracked()
	 * @generated
	 * @ordered
	 */
	protected boolean untracked = UNTRACKED_EDEFAULT;

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
		return GitlabMMPackage.Literals.ARTIFACT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWhen() {
		return when;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWhen(String newWhen) {
		String oldWhen = when;
		when = newWhen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACT__WHEN, oldWhen, when));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Report getReports() {
		return reports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReports(Report newReports, NotificationChain msgs) {
		Report oldReports = reports;
		reports = newReports;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.ARTIFACT__REPORTS, oldReports, newReports);
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
	public void setReports(Report newReports) {
		if (newReports != reports) {
			NotificationChain msgs = null;
			if (reports != null)
				msgs = ((InternalEObject) reports).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.ARTIFACT__REPORTS, null, msgs);
			if (newReports != null)
				msgs = ((InternalEObject) newReports).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.ARTIFACT__REPORTS, null, msgs);
			msgs = basicSetReports(newReports, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACT__REPORTS, newReports,
					newReports));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Path> getPaths() {
		if (paths == null) {
			paths = new EObjectContainmentEList<Path>(Path.class, this, GitlabMMPackage.ARTIFACT__PATHS);
		}
		return paths;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACT__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACT__EXPIRE_IN, oldExpireIn,
					expireIn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getExpose_as() {
		return expose_as;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExpose_as(String newExpose_as) {
		String oldExpose_as = expose_as;
		expose_as = newExpose_as;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACT__EXPOSE_AS, oldExpose_as,
					expose_as));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUntracked() {
		return untracked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUntracked(boolean newUntracked) {
		boolean oldUntracked = untracked;
		untracked = newUntracked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACT__UNTRACKED, oldUntracked,
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
			exclude = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.ARTIFACT__EXCLUDE);
		}
		return exclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.ARTIFACT__REPORTS:
			return basicSetReports(null, msgs);
		case GitlabMMPackage.ARTIFACT__PATHS:
			return ((InternalEList<?>) getPaths()).basicRemove(otherEnd, msgs);
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
		case GitlabMMPackage.ARTIFACT__WHEN:
			return getWhen();
		case GitlabMMPackage.ARTIFACT__REPORTS:
			return getReports();
		case GitlabMMPackage.ARTIFACT__PATHS:
			return getPaths();
		case GitlabMMPackage.ARTIFACT__NAME:
			return getName();
		case GitlabMMPackage.ARTIFACT__EXPIRE_IN:
			return getExpireIn();
		case GitlabMMPackage.ARTIFACT__EXPOSE_AS:
			return getExpose_as();
		case GitlabMMPackage.ARTIFACT__UNTRACKED:
			return isUntracked();
		case GitlabMMPackage.ARTIFACT__EXCLUDE:
			return getExclude();
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
		case GitlabMMPackage.ARTIFACT__WHEN:
			setWhen((String) newValue);
			return;
		case GitlabMMPackage.ARTIFACT__REPORTS:
			setReports((Report) newValue);
			return;
		case GitlabMMPackage.ARTIFACT__PATHS:
			getPaths().clear();
			getPaths().addAll((Collection<? extends Path>) newValue);
			return;
		case GitlabMMPackage.ARTIFACT__NAME:
			setName((String) newValue);
			return;
		case GitlabMMPackage.ARTIFACT__EXPIRE_IN:
			setExpireIn((String) newValue);
			return;
		case GitlabMMPackage.ARTIFACT__EXPOSE_AS:
			setExpose_as((String) newValue);
			return;
		case GitlabMMPackage.ARTIFACT__UNTRACKED:
			setUntracked((Boolean) newValue);
			return;
		case GitlabMMPackage.ARTIFACT__EXCLUDE:
			getExclude().clear();
			getExclude().addAll((Collection<? extends String>) newValue);
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
		case GitlabMMPackage.ARTIFACT__WHEN:
			setWhen(WHEN_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACT__REPORTS:
			setReports((Report) null);
			return;
		case GitlabMMPackage.ARTIFACT__PATHS:
			getPaths().clear();
			return;
		case GitlabMMPackage.ARTIFACT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACT__EXPIRE_IN:
			setExpireIn(EXPIRE_IN_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACT__EXPOSE_AS:
			setExpose_as(EXPOSE_AS_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACT__UNTRACKED:
			setUntracked(UNTRACKED_EDEFAULT);
			return;
		case GitlabMMPackage.ARTIFACT__EXCLUDE:
			getExclude().clear();
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
		case GitlabMMPackage.ARTIFACT__WHEN:
			return WHEN_EDEFAULT == null ? when != null : !WHEN_EDEFAULT.equals(when);
		case GitlabMMPackage.ARTIFACT__REPORTS:
			return reports != null;
		case GitlabMMPackage.ARTIFACT__PATHS:
			return paths != null && !paths.isEmpty();
		case GitlabMMPackage.ARTIFACT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case GitlabMMPackage.ARTIFACT__EXPIRE_IN:
			return EXPIRE_IN_EDEFAULT == null ? expireIn != null : !EXPIRE_IN_EDEFAULT.equals(expireIn);
		case GitlabMMPackage.ARTIFACT__EXPOSE_AS:
			return EXPOSE_AS_EDEFAULT == null ? expose_as != null : !EXPOSE_AS_EDEFAULT.equals(expose_as);
		case GitlabMMPackage.ARTIFACT__UNTRACKED:
			return untracked != UNTRACKED_EDEFAULT;
		case GitlabMMPackage.ARTIFACT__EXCLUDE:
			return exclude != null && !exclude.isEmpty();
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
		result.append(" (when: ");
		result.append(when);
		result.append(", name: ");
		result.append(name);
		result.append(", expireIn: ");
		result.append(expireIn);
		result.append(", expose_as: ");
		result.append(expose_as);
		result.append(", untracked: ");
		result.append(untracked);
		result.append(", exclude: ");
		result.append(exclude);
		result.append(')');
		return result.toString();
	}

} //ArtifactImpl
