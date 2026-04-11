/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Repository Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl#getRepository <em>Repository</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl#getRef <em>Ref</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl#getTrigger <em>Trigger</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RepositoryResourceImpl extends MinimalEObjectImpl.Container implements RepositoryResource {
	/**
	 * The default value of the '{@link #getRepository() <em>Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepository()
	 * @generated
	 * @ordered
	 */
	protected static final String REPOSITORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRepository() <em>Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepository()
	 * @generated
	 * @ordered
	 */
	protected String repository = REPOSITORY_EDEFAULT;

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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final REPOSITORY_TYPE TYPE_EDEFAULT = REPOSITORY_TYPE.GIT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected REPOSITORY_TYPE type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndpoint() <em>Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndpoint()
	 * @generated
	 * @ordered
	 */
	protected static final String ENDPOINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndpoint() <em>Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndpoint()
	 * @generated
	 * @ordered
	 */
	protected String endpoint = ENDPOINT_EDEFAULT;

	/**
	 * The default value of the '{@link #getRef() <em>Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRef()
	 * @generated
	 * @ordered
	 */
	protected static final String REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRef() <em>Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRef()
	 * @generated
	 * @ordered
	 */
	protected String ref = REF_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected Trigger trigger;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RepositoryResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.REPOSITORY_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRepository() {
		return repository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRepository(String newRepository) {
		String oldRepository = repository;
		repository = newRepository;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.REPOSITORY_RESOURCE__REPOSITORY,
					oldRepository, repository));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.REPOSITORY_RESOURCE__NAME,
					oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public REPOSITORY_TYPE getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(REPOSITORY_TYPE newType) {
		REPOSITORY_TYPE oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.REPOSITORY_RESOURCE__TYPE,
					oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEndpoint(String newEndpoint) {
		String oldEndpoint = endpoint;
		endpoint = newEndpoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.REPOSITORY_RESOURCE__ENDPOINT,
					oldEndpoint, endpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRef() {
		return ref;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRef(String newRef) {
		String oldRef = ref;
		ref = newRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.REPOSITORY_RESOURCE__REF, oldRef,
					ref));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Trigger getTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrigger(Trigger newTrigger, NotificationChain msgs) {
		Trigger oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.REPOSITORY_RESOURCE__TRIGGER, oldTrigger, newTrigger);
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
	public void setTrigger(Trigger newTrigger) {
		if (newTrigger != trigger) {
			NotificationChain msgs = null;
			if (trigger != null)
				msgs = ((InternalEObject) trigger).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.REPOSITORY_RESOURCE__TRIGGER, null, msgs);
			if (newTrigger != null)
				msgs = ((InternalEObject) newTrigger).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.REPOSITORY_RESOURCE__TRIGGER, null, msgs);
			msgs = basicSetTrigger(newTrigger, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.REPOSITORY_RESOURCE__TRIGGER,
					newTrigger, newTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__TRIGGER:
			return basicSetTrigger(null, msgs);
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
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__REPOSITORY:
			return getRepository();
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__NAME:
			return getName();
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__TYPE:
			return getType();
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__ENDPOINT:
			return getEndpoint();
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__REF:
			return getRef();
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__TRIGGER:
			return getTrigger();
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
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__REPOSITORY:
			setRepository((String) newValue);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__NAME:
			setName((String) newValue);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__TYPE:
			setType((REPOSITORY_TYPE) newValue);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__ENDPOINT:
			setEndpoint((String) newValue);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__REF:
			setRef((String) newValue);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__TRIGGER:
			setTrigger((Trigger) newValue);
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
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__REPOSITORY:
			setRepository(REPOSITORY_EDEFAULT);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__ENDPOINT:
			setEndpoint(ENDPOINT_EDEFAULT);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__REF:
			setRef(REF_EDEFAULT);
			return;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__TRIGGER:
			setTrigger((Trigger) null);
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
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__REPOSITORY:
			return REPOSITORY_EDEFAULT == null ? repository != null : !REPOSITORY_EDEFAULT.equals(repository);
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__TYPE:
			return type != TYPE_EDEFAULT;
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__ENDPOINT:
			return ENDPOINT_EDEFAULT == null ? endpoint != null : !ENDPOINT_EDEFAULT.equals(endpoint);
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__REF:
			return REF_EDEFAULT == null ? ref != null : !REF_EDEFAULT.equals(ref);
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE__TRIGGER:
			return trigger != null;
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
		result.append(" (repository: ");
		result.append(repository);
		result.append(", name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(", endpoint: ");
		result.append(endpoint);
		result.append(", ref: ");
		result.append(ref);
		result.append(')');
		return result.toString();
	}

} //RepositoryResourceImpl
