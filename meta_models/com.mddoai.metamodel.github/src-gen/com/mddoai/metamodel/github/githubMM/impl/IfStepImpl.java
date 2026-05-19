/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.IfStep;
import com.mddoai.metamodel.github.githubMM.Step;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.IfStepImpl#getIfCondition <em>If Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.IfStepImpl#getThenRun <em>Then Run</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IfStepImpl extends AbstractStepImpl implements IfStep {
	/**
	 * The cached value of the '{@link #getIfCondition() <em>If Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIfCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression ifCondition;

	/**
	 * The cached value of the '{@link #getThenRun() <em>Then Run</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenRun()
	 * @generated
	 * @ordered
	 */
	protected Step thenRun;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.IF_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getIfCondition() {
		return ifCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIfCondition(Expression newIfCondition, NotificationChain msgs) {
		Expression oldIfCondition = ifCondition;
		ifCondition = newIfCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.IF_STEP__IF_CONDITION, oldIfCondition, newIfCondition);
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
	public void setIfCondition(Expression newIfCondition) {
		if (newIfCondition != ifCondition) {
			NotificationChain msgs = null;
			if (ifCondition != null)
				msgs = ((InternalEObject) ifCondition).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.IF_STEP__IF_CONDITION, null, msgs);
			if (newIfCondition != null)
				msgs = ((InternalEObject) newIfCondition).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.IF_STEP__IF_CONDITION, null, msgs);
			msgs = basicSetIfCondition(newIfCondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.IF_STEP__IF_CONDITION, newIfCondition,
					newIfCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Step getThenRun() {
		return thenRun;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThenRun(Step newThenRun, NotificationChain msgs) {
		Step oldThenRun = thenRun;
		thenRun = newThenRun;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.IF_STEP__THEN_RUN, oldThenRun, newThenRun);
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
	public void setThenRun(Step newThenRun) {
		if (newThenRun != thenRun) {
			NotificationChain msgs = null;
			if (thenRun != null)
				msgs = ((InternalEObject) thenRun).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.IF_STEP__THEN_RUN, null, msgs);
			if (newThenRun != null)
				msgs = ((InternalEObject) newThenRun).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.IF_STEP__THEN_RUN, null, msgs);
			msgs = basicSetThenRun(newThenRun, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.IF_STEP__THEN_RUN, newThenRun,
					newThenRun));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.IF_STEP__IF_CONDITION:
			return basicSetIfCondition(null, msgs);
		case GithubMMPackage.IF_STEP__THEN_RUN:
			return basicSetThenRun(null, msgs);
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
		case GithubMMPackage.IF_STEP__IF_CONDITION:
			return getIfCondition();
		case GithubMMPackage.IF_STEP__THEN_RUN:
			return getThenRun();
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
		case GithubMMPackage.IF_STEP__IF_CONDITION:
			setIfCondition((Expression) newValue);
			return;
		case GithubMMPackage.IF_STEP__THEN_RUN:
			setThenRun((Step) newValue);
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
		case GithubMMPackage.IF_STEP__IF_CONDITION:
			setIfCondition((Expression) null);
			return;
		case GithubMMPackage.IF_STEP__THEN_RUN:
			setThenRun((Step) null);
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
		case GithubMMPackage.IF_STEP__IF_CONDITION:
			return ifCondition != null;
		case GithubMMPackage.IF_STEP__THEN_RUN:
			return thenRun != null;
		}
		return super.eIsSet(featureID);
	}

} //IfStepImpl
