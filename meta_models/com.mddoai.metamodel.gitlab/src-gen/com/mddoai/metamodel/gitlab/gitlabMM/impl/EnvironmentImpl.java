/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.Environment;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Kubernetes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Environment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl#getOnStop <em>On Stop</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl#getAction <em>Action</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl#getAutoStopIn <em>Auto Stop In</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl#getKubernetes <em>Kubernetes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnvironmentImpl extends MinimalEObjectImpl.Container implements Environment {
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
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnStop() <em>On Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStop()
	 * @generated
	 * @ordered
	 */
	protected static final String ON_STOP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnStop() <em>On Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStop()
	 * @generated
	 * @ordered
	 */
	protected String onStop = ON_STOP_EDEFAULT;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected String action = ACTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAutoStopIn() <em>Auto Stop In</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoStopIn()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTO_STOP_IN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAutoStopIn() <em>Auto Stop In</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoStopIn()
	 * @generated
	 * @ordered
	 */
	protected String autoStopIn = AUTO_STOP_IN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getKubernetes() <em>Kubernetes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKubernetes()
	 * @generated
	 * @ordered
	 */
	protected Kubernetes kubernetes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnvironmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.ENVIRONMENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ENVIRONMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ENVIRONMENT__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOnStop() {
		return onStop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOnStop(String newOnStop) {
		String oldOnStop = onStop;
		onStop = newOnStop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ENVIRONMENT__ON_STOP, oldOnStop,
					onStop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAction(String newAction) {
		String oldAction = action;
		action = newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ENVIRONMENT__ACTION, oldAction,
					action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAutoStopIn() {
		return autoStopIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAutoStopIn(String newAutoStopIn) {
		String oldAutoStopIn = autoStopIn;
		autoStopIn = newAutoStopIn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ENVIRONMENT__AUTO_STOP_IN,
					oldAutoStopIn, autoStopIn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Kubernetes getKubernetes() {
		return kubernetes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKubernetes(Kubernetes newKubernetes, NotificationChain msgs) {
		Kubernetes oldKubernetes = kubernetes;
		kubernetes = newKubernetes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.ENVIRONMENT__KUBERNETES, oldKubernetes, newKubernetes);
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
	public void setKubernetes(Kubernetes newKubernetes) {
		if (newKubernetes != kubernetes) {
			NotificationChain msgs = null;
			if (kubernetes != null)
				msgs = ((InternalEObject) kubernetes).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.ENVIRONMENT__KUBERNETES, null, msgs);
			if (newKubernetes != null)
				msgs = ((InternalEObject) newKubernetes).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.ENVIRONMENT__KUBERNETES, null, msgs);
			msgs = basicSetKubernetes(newKubernetes, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ENVIRONMENT__KUBERNETES,
					newKubernetes, newKubernetes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.ENVIRONMENT__KUBERNETES:
			return basicSetKubernetes(null, msgs);
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
		case GitlabMMPackage.ENVIRONMENT__NAME:
			return getName();
		case GitlabMMPackage.ENVIRONMENT__URL:
			return getUrl();
		case GitlabMMPackage.ENVIRONMENT__ON_STOP:
			return getOnStop();
		case GitlabMMPackage.ENVIRONMENT__ACTION:
			return getAction();
		case GitlabMMPackage.ENVIRONMENT__AUTO_STOP_IN:
			return getAutoStopIn();
		case GitlabMMPackage.ENVIRONMENT__KUBERNETES:
			return getKubernetes();
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
		case GitlabMMPackage.ENVIRONMENT__NAME:
			setName((String) newValue);
			return;
		case GitlabMMPackage.ENVIRONMENT__URL:
			setUrl((String) newValue);
			return;
		case GitlabMMPackage.ENVIRONMENT__ON_STOP:
			setOnStop((String) newValue);
			return;
		case GitlabMMPackage.ENVIRONMENT__ACTION:
			setAction((String) newValue);
			return;
		case GitlabMMPackage.ENVIRONMENT__AUTO_STOP_IN:
			setAutoStopIn((String) newValue);
			return;
		case GitlabMMPackage.ENVIRONMENT__KUBERNETES:
			setKubernetes((Kubernetes) newValue);
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
		case GitlabMMPackage.ENVIRONMENT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GitlabMMPackage.ENVIRONMENT__URL:
			setUrl(URL_EDEFAULT);
			return;
		case GitlabMMPackage.ENVIRONMENT__ON_STOP:
			setOnStop(ON_STOP_EDEFAULT);
			return;
		case GitlabMMPackage.ENVIRONMENT__ACTION:
			setAction(ACTION_EDEFAULT);
			return;
		case GitlabMMPackage.ENVIRONMENT__AUTO_STOP_IN:
			setAutoStopIn(AUTO_STOP_IN_EDEFAULT);
			return;
		case GitlabMMPackage.ENVIRONMENT__KUBERNETES:
			setKubernetes((Kubernetes) null);
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
		case GitlabMMPackage.ENVIRONMENT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case GitlabMMPackage.ENVIRONMENT__URL:
			return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
		case GitlabMMPackage.ENVIRONMENT__ON_STOP:
			return ON_STOP_EDEFAULT == null ? onStop != null : !ON_STOP_EDEFAULT.equals(onStop);
		case GitlabMMPackage.ENVIRONMENT__ACTION:
			return ACTION_EDEFAULT == null ? action != null : !ACTION_EDEFAULT.equals(action);
		case GitlabMMPackage.ENVIRONMENT__AUTO_STOP_IN:
			return AUTO_STOP_IN_EDEFAULT == null ? autoStopIn != null : !AUTO_STOP_IN_EDEFAULT.equals(autoStopIn);
		case GitlabMMPackage.ENVIRONMENT__KUBERNETES:
			return kubernetes != null;
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
		result.append(", url: ");
		result.append(url);
		result.append(", onStop: ");
		result.append(onStop);
		result.append(", action: ");
		result.append(action);
		result.append(", autoStopIn: ");
		result.append(autoStopIn);
		result.append(')');
		return result.toString();
	}

} //EnvironmentImpl
