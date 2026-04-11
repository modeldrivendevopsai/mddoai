/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mount Read Only</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MountReadOnlyImpl#getWork <em>Work</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MountReadOnlyImpl#getExternals <em>Externals</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MountReadOnlyImpl#getTools <em>Tools</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MountReadOnlyImpl#getTasks <em>Tasks</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MountReadOnlyImpl extends MinimalEObjectImpl.Container implements MountReadOnly {
	/**
	 * The default value of the '{@link #getWork() <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWork()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean WORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWork() <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWork()
	 * @generated
	 * @ordered
	 */
	protected Boolean work = WORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getExternals() <em>Externals</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternals()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean EXTERNALS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExternals() <em>Externals</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternals()
	 * @generated
	 * @ordered
	 */
	protected Boolean externals = EXTERNALS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTools() <em>Tools</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTools()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean TOOLS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTools() <em>Tools</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTools()
	 * @generated
	 * @ordered
	 */
	protected Boolean tools = TOOLS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTasks() <em>Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTasks()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean TASKS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTasks() <em>Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTasks()
	 * @generated
	 * @ordered
	 */
	protected Boolean tasks = TASKS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MountReadOnlyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.MOUNT_READ_ONLY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getWork() {
		return work;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWork(Boolean newWork) {
		Boolean oldWork = work;
		work = newWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.MOUNT_READ_ONLY__WORK, oldWork,
					work));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getExternals() {
		return externals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExternals(Boolean newExternals) {
		Boolean oldExternals = externals;
		externals = newExternals;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.MOUNT_READ_ONLY__EXTERNALS,
					oldExternals, externals));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getTools() {
		return tools;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTools(Boolean newTools) {
		Boolean oldTools = tools;
		tools = newTools;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.MOUNT_READ_ONLY__TOOLS, oldTools,
					tools));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getTasks() {
		return tasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTasks(Boolean newTasks) {
		Boolean oldTasks = tasks;
		tasks = newTasks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.MOUNT_READ_ONLY__TASKS, oldTasks,
					tasks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__WORK:
			return getWork();
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__EXTERNALS:
			return getExternals();
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__TOOLS:
			return getTools();
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__TASKS:
			return getTasks();
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
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__WORK:
			setWork((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__EXTERNALS:
			setExternals((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__TOOLS:
			setTools((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__TASKS:
			setTasks((Boolean) newValue);
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
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__WORK:
			setWork(WORK_EDEFAULT);
			return;
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__EXTERNALS:
			setExternals(EXTERNALS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__TOOLS:
			setTools(TOOLS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__TASKS:
			setTasks(TASKS_EDEFAULT);
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
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__WORK:
			return WORK_EDEFAULT == null ? work != null : !WORK_EDEFAULT.equals(work);
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__EXTERNALS:
			return EXTERNALS_EDEFAULT == null ? externals != null : !EXTERNALS_EDEFAULT.equals(externals);
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__TOOLS:
			return TOOLS_EDEFAULT == null ? tools != null : !TOOLS_EDEFAULT.equals(tools);
		case AzuredevopsMMPackage.MOUNT_READ_ONLY__TASKS:
			return TASKS_EDEFAULT == null ? tasks != null : !TASKS_EDEFAULT.equals(tasks);
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
		result.append(" (work: ");
		result.append(work);
		result.append(", externals: ");
		result.append(externals);
		result.append(", tools: ");
		result.append(tools);
		result.append(", tasks: ");
		result.append(tasks);
		result.append(')');
		return result.toString();
	}

} //MountReadOnlyImpl
