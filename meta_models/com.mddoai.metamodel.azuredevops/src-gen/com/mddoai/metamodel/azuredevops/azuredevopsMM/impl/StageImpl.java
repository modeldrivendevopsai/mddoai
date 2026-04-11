/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobElement;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateContext;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getStage <em>Stage</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getLockBehavior <em>Lock Behavior</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getIsSkippable <em>Is Skippable</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getPool <em>Pool</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getJobs <em>Jobs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl#getTemplateContext <em>Template Context</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StageImpl extends StageElementImpl implements Stage {
	/**
	 * The default value of the '{@link #getStage() <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected static final String STAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStage() <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected String stage = STAGE_EDEFAULT;

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
	 * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected String condition = CONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLockBehavior() <em>Lock Behavior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLockBehavior()
	 * @generated
	 * @ordered
	 */
	protected static final LOCK_BEHAVIOR LOCK_BEHAVIOR_EDEFAULT = LOCK_BEHAVIOR.SEQUENTIAL;

	/**
	 * The cached value of the '{@link #getLockBehavior() <em>Lock Behavior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLockBehavior()
	 * @generated
	 * @ordered
	 */
	protected LOCK_BEHAVIOR lockBehavior = LOCK_BEHAVIOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getTrigger() <em>Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected static final STAGE_TRIGGER TRIGGER_EDEFAULT = STAGE_TRIGGER.MANUAL;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected STAGE_TRIGGER trigger = TRIGGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsSkippable() <em>Is Skippable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSkippable()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_SKIPPABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsSkippable() <em>Is Skippable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSkippable()
	 * @generated
	 * @ordered
	 */
	protected Boolean isSkippable = IS_SKIPPABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDependsOn() <em>Depends On</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependsOn()
	 * @generated
	 * @ordered
	 */
	protected EList<String> dependsOn;

	/**
	 * The cached value of the '{@link #getPool() <em>Pool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPool()
	 * @generated
	 * @ordered
	 */
	protected Pool pool;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableDefinition> variables;

	/**
	 * The cached value of the '{@link #getJobs() <em>Jobs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJobs()
	 * @generated
	 * @ordered
	 */
	protected EList<JobElement> jobs;

	/**
	 * The cached value of the '{@link #getTemplateContext() <em>Template Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateContext()
	 * @generated
	 * @ordered
	 */
	protected TemplateContext templateContext;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.STAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStage() {
		return stage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStage(String newStage) {
		String oldStage = stage;
		stage = newStage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STAGE__STAGE, oldStage, stage));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STAGE__DISPLAY_NAME,
					oldDisplayName, displayName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCondition(String newCondition) {
		String oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STAGE__CONDITION, oldCondition,
					condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LOCK_BEHAVIOR getLockBehavior() {
		return lockBehavior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLockBehavior(LOCK_BEHAVIOR newLockBehavior) {
		LOCK_BEHAVIOR oldLockBehavior = lockBehavior;
		lockBehavior = newLockBehavior == null ? LOCK_BEHAVIOR_EDEFAULT : newLockBehavior;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STAGE__LOCK_BEHAVIOR,
					oldLockBehavior, lockBehavior));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public STAGE_TRIGGER getTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTrigger(STAGE_TRIGGER newTrigger) {
		STAGE_TRIGGER oldTrigger = trigger;
		trigger = newTrigger == null ? TRIGGER_EDEFAULT : newTrigger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STAGE__TRIGGER, oldTrigger,
					trigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsSkippable() {
		return isSkippable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsSkippable(Boolean newIsSkippable) {
		Boolean oldIsSkippable = isSkippable;
		isSkippable = newIsSkippable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STAGE__IS_SKIPPABLE,
					oldIsSkippable, isSkippable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getDependsOn() {
		if (dependsOn == null) {
			dependsOn = new EDataTypeUniqueEList<String>(String.class, this, AzuredevopsMMPackage.STAGE__DEPENDS_ON);
		}
		return dependsOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Pool getPool() {
		return pool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPool(Pool newPool, NotificationChain msgs) {
		Pool oldPool = pool;
		pool = newPool;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.STAGE__POOL, oldPool, newPool);
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
	public void setPool(Pool newPool) {
		if (newPool != pool) {
			NotificationChain msgs = null;
			if (pool != null)
				msgs = ((InternalEObject) pool).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.STAGE__POOL, null, msgs);
			if (newPool != null)
				msgs = ((InternalEObject) newPool).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.STAGE__POOL, null, msgs);
			msgs = basicSetPool(newPool, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STAGE__POOL, newPool, newPool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<VariableDefinition> getVariables() {
		if (variables == null) {
			variables = new EObjectContainmentEList<VariableDefinition>(VariableDefinition.class, this,
					AzuredevopsMMPackage.STAGE__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<JobElement> getJobs() {
		if (jobs == null) {
			jobs = new EObjectContainmentEList<JobElement>(JobElement.class, this, AzuredevopsMMPackage.STAGE__JOBS);
		}
		return jobs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateContext getTemplateContext() {
		return templateContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTemplateContext(TemplateContext newTemplateContext, NotificationChain msgs) {
		TemplateContext oldTemplateContext = templateContext;
		templateContext = newTemplateContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.STAGE__TEMPLATE_CONTEXT, oldTemplateContext, newTemplateContext);
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
	public void setTemplateContext(TemplateContext newTemplateContext) {
		if (newTemplateContext != templateContext) {
			NotificationChain msgs = null;
			if (templateContext != null)
				msgs = ((InternalEObject) templateContext).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.STAGE__TEMPLATE_CONTEXT, null, msgs);
			if (newTemplateContext != null)
				msgs = ((InternalEObject) newTemplateContext).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.STAGE__TEMPLATE_CONTEXT, null, msgs);
			msgs = basicSetTemplateContext(newTemplateContext, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STAGE__TEMPLATE_CONTEXT,
					newTemplateContext, newTemplateContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.STAGE__POOL:
			return basicSetPool(null, msgs);
		case AzuredevopsMMPackage.STAGE__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.STAGE__JOBS:
			return ((InternalEList<?>) getJobs()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.STAGE__TEMPLATE_CONTEXT:
			return basicSetTemplateContext(null, msgs);
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
		case AzuredevopsMMPackage.STAGE__STAGE:
			return getStage();
		case AzuredevopsMMPackage.STAGE__DISPLAY_NAME:
			return getDisplayName();
		case AzuredevopsMMPackage.STAGE__CONDITION:
			return getCondition();
		case AzuredevopsMMPackage.STAGE__LOCK_BEHAVIOR:
			return getLockBehavior();
		case AzuredevopsMMPackage.STAGE__TRIGGER:
			return getTrigger();
		case AzuredevopsMMPackage.STAGE__IS_SKIPPABLE:
			return getIsSkippable();
		case AzuredevopsMMPackage.STAGE__DEPENDS_ON:
			return getDependsOn();
		case AzuredevopsMMPackage.STAGE__POOL:
			return getPool();
		case AzuredevopsMMPackage.STAGE__VARIABLES:
			return getVariables();
		case AzuredevopsMMPackage.STAGE__JOBS:
			return getJobs();
		case AzuredevopsMMPackage.STAGE__TEMPLATE_CONTEXT:
			return getTemplateContext();
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
		case AzuredevopsMMPackage.STAGE__STAGE:
			setStage((String) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__DISPLAY_NAME:
			setDisplayName((String) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__CONDITION:
			setCondition((String) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__LOCK_BEHAVIOR:
			setLockBehavior((LOCK_BEHAVIOR) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__TRIGGER:
			setTrigger((STAGE_TRIGGER) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__IS_SKIPPABLE:
			setIsSkippable((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__DEPENDS_ON:
			getDependsOn().clear();
			getDependsOn().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__POOL:
			setPool((Pool) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__VARIABLES:
			getVariables().clear();
			getVariables().addAll((Collection<? extends VariableDefinition>) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__JOBS:
			getJobs().clear();
			getJobs().addAll((Collection<? extends JobElement>) newValue);
			return;
		case AzuredevopsMMPackage.STAGE__TEMPLATE_CONTEXT:
			setTemplateContext((TemplateContext) newValue);
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
		case AzuredevopsMMPackage.STAGE__STAGE:
			setStage(STAGE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STAGE__DISPLAY_NAME:
			setDisplayName(DISPLAY_NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STAGE__CONDITION:
			setCondition(CONDITION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STAGE__LOCK_BEHAVIOR:
			setLockBehavior(LOCK_BEHAVIOR_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STAGE__TRIGGER:
			setTrigger(TRIGGER_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STAGE__IS_SKIPPABLE:
			setIsSkippable(IS_SKIPPABLE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STAGE__DEPENDS_ON:
			getDependsOn().clear();
			return;
		case AzuredevopsMMPackage.STAGE__POOL:
			setPool((Pool) null);
			return;
		case AzuredevopsMMPackage.STAGE__VARIABLES:
			getVariables().clear();
			return;
		case AzuredevopsMMPackage.STAGE__JOBS:
			getJobs().clear();
			return;
		case AzuredevopsMMPackage.STAGE__TEMPLATE_CONTEXT:
			setTemplateContext((TemplateContext) null);
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
		case AzuredevopsMMPackage.STAGE__STAGE:
			return STAGE_EDEFAULT == null ? stage != null : !STAGE_EDEFAULT.equals(stage);
		case AzuredevopsMMPackage.STAGE__DISPLAY_NAME:
			return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
		case AzuredevopsMMPackage.STAGE__CONDITION:
			return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
		case AzuredevopsMMPackage.STAGE__LOCK_BEHAVIOR:
			return lockBehavior != LOCK_BEHAVIOR_EDEFAULT;
		case AzuredevopsMMPackage.STAGE__TRIGGER:
			return trigger != TRIGGER_EDEFAULT;
		case AzuredevopsMMPackage.STAGE__IS_SKIPPABLE:
			return IS_SKIPPABLE_EDEFAULT == null ? isSkippable != null : !IS_SKIPPABLE_EDEFAULT.equals(isSkippable);
		case AzuredevopsMMPackage.STAGE__DEPENDS_ON:
			return dependsOn != null && !dependsOn.isEmpty();
		case AzuredevopsMMPackage.STAGE__POOL:
			return pool != null;
		case AzuredevopsMMPackage.STAGE__VARIABLES:
			return variables != null && !variables.isEmpty();
		case AzuredevopsMMPackage.STAGE__JOBS:
			return jobs != null && !jobs.isEmpty();
		case AzuredevopsMMPackage.STAGE__TEMPLATE_CONTEXT:
			return templateContext != null;
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
		result.append(" (stage: ");
		result.append(stage);
		result.append(", displayName: ");
		result.append(displayName);
		result.append(", condition: ");
		result.append(condition);
		result.append(", lockBehavior: ");
		result.append(lockBehavior);
		result.append(", trigger: ");
		result.append(trigger);
		result.append(", isSkippable: ");
		result.append(isSkippable);
		result.append(", dependsOn: ");
		result.append(dependsOn);
		result.append(')');
		return result.toString();
	}

} //StageImpl
