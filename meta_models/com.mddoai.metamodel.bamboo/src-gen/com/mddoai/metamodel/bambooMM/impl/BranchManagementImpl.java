/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY;
import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BranchDelete;
import com.mddoai.metamodel.bambooMM.BranchIntegration;
import com.mddoai.metamodel.bambooMM.BranchManagement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Branch Management</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl#getCreate <em>Create</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl#getCreatePattern <em>Create Pattern</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl#getAcceptFork <em>Accept Fork</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl#getDelete <em>Delete</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl#getIntegration <em>Integration</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl#getLinkToJira <em>Link To Jira</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BranchManagementImpl extends MinimalEObjectImpl.Container implements BranchManagement {
	/**
	 * The default value of the '{@link #getCreate() <em>Create</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreate()
	 * @generated
	 * @ordered
	 */
	protected static final BRANCH_CREATE_STRATEGY CREATE_EDEFAULT = BRANCH_CREATE_STRATEGY.FOR_NEW_BRANCH;

	/**
	 * The cached value of the '{@link #getCreate() <em>Create</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreate()
	 * @generated
	 * @ordered
	 */
	protected BRANCH_CREATE_STRATEGY create = CREATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreatePattern() <em>Create Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreatePattern()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATE_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreatePattern() <em>Create Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreatePattern()
	 * @generated
	 * @ordered
	 */
	protected String createPattern = CREATE_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getAcceptFork() <em>Accept Fork</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAcceptFork()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ACCEPT_FORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAcceptFork() <em>Accept Fork</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAcceptFork()
	 * @generated
	 * @ordered
	 */
	protected Boolean acceptFork = ACCEPT_FORK_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDelete() <em>Delete</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelete()
	 * @generated
	 * @ordered
	 */
	protected BranchDelete delete;

	/**
	 * The cached value of the '{@link #getIntegration() <em>Integration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegration()
	 * @generated
	 * @ordered
	 */
	protected BranchIntegration integration;

	/**
	 * The default value of the '{@link #getLinkToJira() <em>Link To Jira</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkToJira()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean LINK_TO_JIRA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLinkToJira() <em>Link To Jira</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkToJira()
	 * @generated
	 * @ordered
	 */
	protected Boolean linkToJira = LINK_TO_JIRA_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BranchManagementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.BRANCH_MANAGEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BRANCH_CREATE_STRATEGY getCreate() {
		return create;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCreate(BRANCH_CREATE_STRATEGY newCreate) {
		BRANCH_CREATE_STRATEGY oldCreate = create;
		create = newCreate == null ? CREATE_EDEFAULT : newCreate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_MANAGEMENT__CREATE, oldCreate,
					create));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreatePattern() {
		return createPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCreatePattern(String newCreatePattern) {
		String oldCreatePattern = createPattern;
		createPattern = newCreatePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_MANAGEMENT__CREATE_PATTERN,
					oldCreatePattern, createPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAcceptFork() {
		return acceptFork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAcceptFork(Boolean newAcceptFork) {
		Boolean oldAcceptFork = acceptFork;
		acceptFork = newAcceptFork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_MANAGEMENT__ACCEPT_FORK,
					oldAcceptFork, acceptFork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchDelete getDelete() {
		return delete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDelete(BranchDelete newDelete, NotificationChain msgs) {
		BranchDelete oldDelete = delete;
		delete = newDelete;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					BambooPackage.BRANCH_MANAGEMENT__DELETE, oldDelete, newDelete);
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
	public void setDelete(BranchDelete newDelete) {
		if (newDelete != delete) {
			NotificationChain msgs = null;
			if (delete != null)
				msgs = ((InternalEObject) delete).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_MANAGEMENT__DELETE, null, msgs);
			if (newDelete != null)
				msgs = ((InternalEObject) newDelete).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_MANAGEMENT__DELETE, null, msgs);
			msgs = basicSetDelete(newDelete, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_MANAGEMENT__DELETE, newDelete,
					newDelete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchIntegration getIntegration() {
		return integration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIntegration(BranchIntegration newIntegration, NotificationChain msgs) {
		BranchIntegration oldIntegration = integration;
		integration = newIntegration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					BambooPackage.BRANCH_MANAGEMENT__INTEGRATION, oldIntegration, newIntegration);
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
	public void setIntegration(BranchIntegration newIntegration) {
		if (newIntegration != integration) {
			NotificationChain msgs = null;
			if (integration != null)
				msgs = ((InternalEObject) integration).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_MANAGEMENT__INTEGRATION, null, msgs);
			if (newIntegration != null)
				msgs = ((InternalEObject) newIntegration).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_MANAGEMENT__INTEGRATION, null, msgs);
			msgs = basicSetIntegration(newIntegration, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_MANAGEMENT__INTEGRATION,
					newIntegration, newIntegration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getLinkToJira() {
		return linkToJira;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLinkToJira(Boolean newLinkToJira) {
		Boolean oldLinkToJira = linkToJira;
		linkToJira = newLinkToJira;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_MANAGEMENT__LINK_TO_JIRA,
					oldLinkToJira, linkToJira));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.BRANCH_MANAGEMENT__DELETE:
			return basicSetDelete(null, msgs);
		case BambooPackage.BRANCH_MANAGEMENT__INTEGRATION:
			return basicSetIntegration(null, msgs);
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
		case BambooPackage.BRANCH_MANAGEMENT__CREATE:
			return getCreate();
		case BambooPackage.BRANCH_MANAGEMENT__CREATE_PATTERN:
			return getCreatePattern();
		case BambooPackage.BRANCH_MANAGEMENT__ACCEPT_FORK:
			return getAcceptFork();
		case BambooPackage.BRANCH_MANAGEMENT__DELETE:
			return getDelete();
		case BambooPackage.BRANCH_MANAGEMENT__INTEGRATION:
			return getIntegration();
		case BambooPackage.BRANCH_MANAGEMENT__LINK_TO_JIRA:
			return getLinkToJira();
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
		case BambooPackage.BRANCH_MANAGEMENT__CREATE:
			setCreate((BRANCH_CREATE_STRATEGY) newValue);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__CREATE_PATTERN:
			setCreatePattern((String) newValue);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__ACCEPT_FORK:
			setAcceptFork((Boolean) newValue);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__DELETE:
			setDelete((BranchDelete) newValue);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__INTEGRATION:
			setIntegration((BranchIntegration) newValue);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__LINK_TO_JIRA:
			setLinkToJira((Boolean) newValue);
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
		case BambooPackage.BRANCH_MANAGEMENT__CREATE:
			setCreate(CREATE_EDEFAULT);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__CREATE_PATTERN:
			setCreatePattern(CREATE_PATTERN_EDEFAULT);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__ACCEPT_FORK:
			setAcceptFork(ACCEPT_FORK_EDEFAULT);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__DELETE:
			setDelete((BranchDelete) null);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__INTEGRATION:
			setIntegration((BranchIntegration) null);
			return;
		case BambooPackage.BRANCH_MANAGEMENT__LINK_TO_JIRA:
			setLinkToJira(LINK_TO_JIRA_EDEFAULT);
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
		case BambooPackage.BRANCH_MANAGEMENT__CREATE:
			return create != CREATE_EDEFAULT;
		case BambooPackage.BRANCH_MANAGEMENT__CREATE_PATTERN:
			return CREATE_PATTERN_EDEFAULT == null ? createPattern != null
					: !CREATE_PATTERN_EDEFAULT.equals(createPattern);
		case BambooPackage.BRANCH_MANAGEMENT__ACCEPT_FORK:
			return ACCEPT_FORK_EDEFAULT == null ? acceptFork != null : !ACCEPT_FORK_EDEFAULT.equals(acceptFork);
		case BambooPackage.BRANCH_MANAGEMENT__DELETE:
			return delete != null;
		case BambooPackage.BRANCH_MANAGEMENT__INTEGRATION:
			return integration != null;
		case BambooPackage.BRANCH_MANAGEMENT__LINK_TO_JIRA:
			return LINK_TO_JIRA_EDEFAULT == null ? linkToJira != null : !LINK_TO_JIRA_EDEFAULT.equals(linkToJira);
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
		result.append(" (create: ");
		result.append(create);
		result.append(", createPattern: ");
		result.append(createPattern);
		result.append(", acceptFork: ");
		result.append(acceptFork);
		result.append(", linkToJira: ");
		result.append(linkToJira);
		result.append(')');
		return result.toString();
	}

} //BranchManagementImpl
