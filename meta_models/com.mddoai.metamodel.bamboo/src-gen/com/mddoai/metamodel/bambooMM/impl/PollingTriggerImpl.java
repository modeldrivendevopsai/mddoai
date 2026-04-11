/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.PollingTrigger;
import com.mddoai.metamodel.bambooMM.TriggerCondition;

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
 * An implementation of the model object '<em><b>Polling Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PollingTriggerImpl#getPeriod <em>Period</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PollingTriggerImpl#getCronExpression <em>Cron Expression</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PollingTriggerImpl#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PollingTriggerImpl#getConditions <em>Conditions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PollingTriggerImpl extends TriggerImpl implements PollingTrigger {
	/**
	 * The default value of the '{@link #getPeriod() <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriod()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PERIOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPeriod() <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriod()
	 * @generated
	 * @ordered
	 */
	protected Integer period = PERIOD_EDEFAULT;

	/**
	 * The default value of the '{@link #getCronExpression() <em>Cron Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCronExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String CRON_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCronExpression() <em>Cron Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCronExpression()
	 * @generated
	 * @ordered
	 */
	protected String cronExpression = CRON_EXPRESSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRepositories() <em>Repositories</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepositories()
	 * @generated
	 * @ordered
	 */
	protected EList<String> repositories;

	/**
	 * The cached value of the '{@link #getConditions() <em>Conditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditions()
	 * @generated
	 * @ordered
	 */
	protected EList<TriggerCondition> conditions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PollingTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.POLLING_TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getPeriod() {
		return period;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPeriod(Integer newPeriod) {
		Integer oldPeriod = period;
		period = newPeriod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.POLLING_TRIGGER__PERIOD, oldPeriod,
					period));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCronExpression(String newCronExpression) {
		String oldCronExpression = cronExpression;
		cronExpression = newCronExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.POLLING_TRIGGER__CRON_EXPRESSION,
					oldCronExpression, cronExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getRepositories() {
		if (repositories == null) {
			repositories = new EDataTypeUniqueEList<String>(String.class, this,
					BambooPackage.POLLING_TRIGGER__REPOSITORIES);
		}
		return repositories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TriggerCondition> getConditions() {
		if (conditions == null) {
			conditions = new EObjectContainmentEList<TriggerCondition>(TriggerCondition.class, this,
					BambooPackage.POLLING_TRIGGER__CONDITIONS);
		}
		return conditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.POLLING_TRIGGER__CONDITIONS:
			return ((InternalEList<?>) getConditions()).basicRemove(otherEnd, msgs);
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
		case BambooPackage.POLLING_TRIGGER__PERIOD:
			return getPeriod();
		case BambooPackage.POLLING_TRIGGER__CRON_EXPRESSION:
			return getCronExpression();
		case BambooPackage.POLLING_TRIGGER__REPOSITORIES:
			return getRepositories();
		case BambooPackage.POLLING_TRIGGER__CONDITIONS:
			return getConditions();
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
		case BambooPackage.POLLING_TRIGGER__PERIOD:
			setPeriod((Integer) newValue);
			return;
		case BambooPackage.POLLING_TRIGGER__CRON_EXPRESSION:
			setCronExpression((String) newValue);
			return;
		case BambooPackage.POLLING_TRIGGER__REPOSITORIES:
			getRepositories().clear();
			getRepositories().addAll((Collection<? extends String>) newValue);
			return;
		case BambooPackage.POLLING_TRIGGER__CONDITIONS:
			getConditions().clear();
			getConditions().addAll((Collection<? extends TriggerCondition>) newValue);
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
		case BambooPackage.POLLING_TRIGGER__PERIOD:
			setPeriod(PERIOD_EDEFAULT);
			return;
		case BambooPackage.POLLING_TRIGGER__CRON_EXPRESSION:
			setCronExpression(CRON_EXPRESSION_EDEFAULT);
			return;
		case BambooPackage.POLLING_TRIGGER__REPOSITORIES:
			getRepositories().clear();
			return;
		case BambooPackage.POLLING_TRIGGER__CONDITIONS:
			getConditions().clear();
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
		case BambooPackage.POLLING_TRIGGER__PERIOD:
			return PERIOD_EDEFAULT == null ? period != null : !PERIOD_EDEFAULT.equals(period);
		case BambooPackage.POLLING_TRIGGER__CRON_EXPRESSION:
			return CRON_EXPRESSION_EDEFAULT == null ? cronExpression != null
					: !CRON_EXPRESSION_EDEFAULT.equals(cronExpression);
		case BambooPackage.POLLING_TRIGGER__REPOSITORIES:
			return repositories != null && !repositories.isEmpty();
		case BambooPackage.POLLING_TRIGGER__CONDITIONS:
			return conditions != null && !conditions.isEmpty();
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
		result.append(" (period: ");
		result.append(period);
		result.append(", cronExpression: ");
		result.append(cronExpression);
		result.append(", repositories: ");
		result.append(repositories);
		result.append(')');
		return result.toString();
	}

} //PollingTriggerImpl
