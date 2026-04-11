/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cron Schedule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CronScheduleImpl#getCron <em>Cron</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CronScheduleImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CronScheduleImpl#getBatch <em>Batch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CronScheduleImpl#getAlways <em>Always</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CronScheduleImpl#getBranches <em>Branches</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CronScheduleImpl extends MinimalEObjectImpl.Container implements CronSchedule {
	/**
	 * The default value of the '{@link #getCron() <em>Cron</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCron()
	 * @generated
	 * @ordered
	 */
	protected static final String CRON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCron() <em>Cron</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCron()
	 * @generated
	 * @ordered
	 */
	protected String cron = CRON_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getBatch() <em>Batch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatch()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BATCH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBatch() <em>Batch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatch()
	 * @generated
	 * @ordered
	 */
	protected Boolean batch = BATCH_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlways() <em>Always</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlways()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ALWAYS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlways() <em>Always</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlways()
	 * @generated
	 * @ordered
	 */
	protected Boolean always = ALWAYS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBranches() <em>Branches</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranches()
	 * @generated
	 * @ordered
	 */
	protected IncludeExcludeFilters branches;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CronScheduleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.CRON_SCHEDULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCron() {
		return cron;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCron(String newCron) {
		String oldCron = cron;
		cron = newCron;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CRON_SCHEDULE__CRON, oldCron,
					cron));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CRON_SCHEDULE__DISPLAY_NAME,
					oldDisplayName, displayName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getBatch() {
		return batch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBatch(Boolean newBatch) {
		Boolean oldBatch = batch;
		batch = newBatch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CRON_SCHEDULE__BATCH, oldBatch,
					batch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAlways() {
		return always;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAlways(Boolean newAlways) {
		Boolean oldAlways = always;
		always = newAlways;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CRON_SCHEDULE__ALWAYS, oldAlways,
					always));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IncludeExcludeFilters getBranches() {
		return branches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBranches(IncludeExcludeFilters newBranches, NotificationChain msgs) {
		IncludeExcludeFilters oldBranches = branches;
		branches = newBranches;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CRON_SCHEDULE__BRANCHES, oldBranches, newBranches);
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
	public void setBranches(IncludeExcludeFilters newBranches) {
		if (newBranches != branches) {
			NotificationChain msgs = null;
			if (branches != null)
				msgs = ((InternalEObject) branches).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CRON_SCHEDULE__BRANCHES, null, msgs);
			if (newBranches != null)
				msgs = ((InternalEObject) newBranches).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CRON_SCHEDULE__BRANCHES, null, msgs);
			msgs = basicSetBranches(newBranches, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CRON_SCHEDULE__BRANCHES,
					newBranches, newBranches));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.CRON_SCHEDULE__BRANCHES:
			return basicSetBranches(null, msgs);
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
		case AzuredevopsMMPackage.CRON_SCHEDULE__CRON:
			return getCron();
		case AzuredevopsMMPackage.CRON_SCHEDULE__DISPLAY_NAME:
			return getDisplayName();
		case AzuredevopsMMPackage.CRON_SCHEDULE__BATCH:
			return getBatch();
		case AzuredevopsMMPackage.CRON_SCHEDULE__ALWAYS:
			return getAlways();
		case AzuredevopsMMPackage.CRON_SCHEDULE__BRANCHES:
			return getBranches();
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
		case AzuredevopsMMPackage.CRON_SCHEDULE__CRON:
			setCron((String) newValue);
			return;
		case AzuredevopsMMPackage.CRON_SCHEDULE__DISPLAY_NAME:
			setDisplayName((String) newValue);
			return;
		case AzuredevopsMMPackage.CRON_SCHEDULE__BATCH:
			setBatch((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.CRON_SCHEDULE__ALWAYS:
			setAlways((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.CRON_SCHEDULE__BRANCHES:
			setBranches((IncludeExcludeFilters) newValue);
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
		case AzuredevopsMMPackage.CRON_SCHEDULE__CRON:
			setCron(CRON_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CRON_SCHEDULE__DISPLAY_NAME:
			setDisplayName(DISPLAY_NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CRON_SCHEDULE__BATCH:
			setBatch(BATCH_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CRON_SCHEDULE__ALWAYS:
			setAlways(ALWAYS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CRON_SCHEDULE__BRANCHES:
			setBranches((IncludeExcludeFilters) null);
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
		case AzuredevopsMMPackage.CRON_SCHEDULE__CRON:
			return CRON_EDEFAULT == null ? cron != null : !CRON_EDEFAULT.equals(cron);
		case AzuredevopsMMPackage.CRON_SCHEDULE__DISPLAY_NAME:
			return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
		case AzuredevopsMMPackage.CRON_SCHEDULE__BATCH:
			return BATCH_EDEFAULT == null ? batch != null : !BATCH_EDEFAULT.equals(batch);
		case AzuredevopsMMPackage.CRON_SCHEDULE__ALWAYS:
			return ALWAYS_EDEFAULT == null ? always != null : !ALWAYS_EDEFAULT.equals(always);
		case AzuredevopsMMPackage.CRON_SCHEDULE__BRANCHES:
			return branches != null;
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
		result.append(" (cron: ");
		result.append(cron);
		result.append(", displayName: ");
		result.append(displayName);
		result.append(", batch: ");
		result.append(batch);
		result.append(", always: ");
		result.append(always);
		result.append(')');
		return result.toString();
	}

} //CronScheduleImpl
