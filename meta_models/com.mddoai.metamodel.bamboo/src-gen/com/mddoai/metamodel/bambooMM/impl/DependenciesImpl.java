/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY;
import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.Dependencies;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dependencies</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DependenciesImpl#isRequireAllStagesPassing <em>Require All Stages Passing</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DependenciesImpl#isEnabledForBranches <em>Enabled For Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DependenciesImpl#getBlockStrategy <em>Block Strategy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DependenciesImpl#getPlans <em>Plans</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DependenciesImpl extends MinimalEObjectImpl.Container implements Dependencies {
	/**
	 * The default value of the '{@link #isRequireAllStagesPassing() <em>Require All Stages Passing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequireAllStagesPassing()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRE_ALL_STAGES_PASSING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequireAllStagesPassing() <em>Require All Stages Passing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequireAllStagesPassing()
	 * @generated
	 * @ordered
	 */
	protected boolean requireAllStagesPassing = REQUIRE_ALL_STAGES_PASSING_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnabledForBranches() <em>Enabled For Branches</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabledForBranches()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_FOR_BRANCHES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabledForBranches() <em>Enabled For Branches</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabledForBranches()
	 * @generated
	 * @ordered
	 */
	protected boolean enabledForBranches = ENABLED_FOR_BRANCHES_EDEFAULT;

	/**
	 * The default value of the '{@link #getBlockStrategy() <em>Block Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockStrategy()
	 * @generated
	 * @ordered
	 */
	protected static final BLOCK_STRATEGY BLOCK_STRATEGY_EDEFAULT = BLOCK_STRATEGY.DONT_BLOCK;

	/**
	 * The cached value of the '{@link #getBlockStrategy() <em>Block Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockStrategy()
	 * @generated
	 * @ordered
	 */
	protected BLOCK_STRATEGY blockStrategy = BLOCK_STRATEGY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPlans() <em>Plans</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlans()
	 * @generated
	 * @ordered
	 */
	protected EList<String> plans;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DependenciesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.DEPENDENCIES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRequireAllStagesPassing() {
		return requireAllStagesPassing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequireAllStagesPassing(boolean newRequireAllStagesPassing) {
		boolean oldRequireAllStagesPassing = requireAllStagesPassing;
		requireAllStagesPassing = newRequireAllStagesPassing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.DEPENDENCIES__REQUIRE_ALL_STAGES_PASSING, oldRequireAllStagesPassing,
					requireAllStagesPassing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isEnabledForBranches() {
		return enabledForBranches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnabledForBranches(boolean newEnabledForBranches) {
		boolean oldEnabledForBranches = enabledForBranches;
		enabledForBranches = newEnabledForBranches;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.DEPENDENCIES__ENABLED_FOR_BRANCHES,
					oldEnabledForBranches, enabledForBranches));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BLOCK_STRATEGY getBlockStrategy() {
		return blockStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBlockStrategy(BLOCK_STRATEGY newBlockStrategy) {
		BLOCK_STRATEGY oldBlockStrategy = blockStrategy;
		blockStrategy = newBlockStrategy == null ? BLOCK_STRATEGY_EDEFAULT : newBlockStrategy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.DEPENDENCIES__BLOCK_STRATEGY,
					oldBlockStrategy, blockStrategy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getPlans() {
		if (plans == null) {
			plans = new EDataTypeUniqueEList<String>(String.class, this, BambooPackage.DEPENDENCIES__PLANS);
		}
		return plans;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.DEPENDENCIES__REQUIRE_ALL_STAGES_PASSING:
			return isRequireAllStagesPassing();
		case BambooPackage.DEPENDENCIES__ENABLED_FOR_BRANCHES:
			return isEnabledForBranches();
		case BambooPackage.DEPENDENCIES__BLOCK_STRATEGY:
			return getBlockStrategy();
		case BambooPackage.DEPENDENCIES__PLANS:
			return getPlans();
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
		case BambooPackage.DEPENDENCIES__REQUIRE_ALL_STAGES_PASSING:
			setRequireAllStagesPassing((Boolean) newValue);
			return;
		case BambooPackage.DEPENDENCIES__ENABLED_FOR_BRANCHES:
			setEnabledForBranches((Boolean) newValue);
			return;
		case BambooPackage.DEPENDENCIES__BLOCK_STRATEGY:
			setBlockStrategy((BLOCK_STRATEGY) newValue);
			return;
		case BambooPackage.DEPENDENCIES__PLANS:
			getPlans().clear();
			getPlans().addAll((Collection<? extends String>) newValue);
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
		case BambooPackage.DEPENDENCIES__REQUIRE_ALL_STAGES_PASSING:
			setRequireAllStagesPassing(REQUIRE_ALL_STAGES_PASSING_EDEFAULT);
			return;
		case BambooPackage.DEPENDENCIES__ENABLED_FOR_BRANCHES:
			setEnabledForBranches(ENABLED_FOR_BRANCHES_EDEFAULT);
			return;
		case BambooPackage.DEPENDENCIES__BLOCK_STRATEGY:
			setBlockStrategy(BLOCK_STRATEGY_EDEFAULT);
			return;
		case BambooPackage.DEPENDENCIES__PLANS:
			getPlans().clear();
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
		case BambooPackage.DEPENDENCIES__REQUIRE_ALL_STAGES_PASSING:
			return requireAllStagesPassing != REQUIRE_ALL_STAGES_PASSING_EDEFAULT;
		case BambooPackage.DEPENDENCIES__ENABLED_FOR_BRANCHES:
			return enabledForBranches != ENABLED_FOR_BRANCHES_EDEFAULT;
		case BambooPackage.DEPENDENCIES__BLOCK_STRATEGY:
			return blockStrategy != BLOCK_STRATEGY_EDEFAULT;
		case BambooPackage.DEPENDENCIES__PLANS:
			return plans != null && !plans.isEmpty();
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
		result.append(" (requireAllStagesPassing: ");
		result.append(requireAllStagesPassing);
		result.append(", enabledForBranches: ");
		result.append(enabledForBranches);
		result.append(", blockStrategy: ");
		result.append(blockStrategy);
		result.append(", plans: ");
		result.append(plans);
		result.append(')');
		return result.toString();
	}

} //DependenciesImpl
