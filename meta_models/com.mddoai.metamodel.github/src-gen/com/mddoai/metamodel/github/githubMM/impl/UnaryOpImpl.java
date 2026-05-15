/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.UnaryOp;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unary Op</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.UnaryOpImpl#getChildExpr <em>Child Expr</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class UnaryOpImpl extends ExpressionImpl implements UnaryOp {
	/**
	 * The cached value of the '{@link #getChildExpr() <em>Child Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildExpr()
	 * @generated
	 * @ordered
	 */
	protected Expression childExpr;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnaryOpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.UNARY_OP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getChildExpr() {
		return childExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChildExpr(Expression newChildExpr, NotificationChain msgs) {
		Expression oldChildExpr = childExpr;
		childExpr = newChildExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.UNARY_OP__CHILD_EXPR, oldChildExpr, newChildExpr);
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
	public void setChildExpr(Expression newChildExpr) {
		if (newChildExpr != childExpr) {
			NotificationChain msgs = null;
			if (childExpr != null)
				msgs = ((InternalEObject) childExpr).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.UNARY_OP__CHILD_EXPR, null, msgs);
			if (newChildExpr != null)
				msgs = ((InternalEObject) newChildExpr).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.UNARY_OP__CHILD_EXPR, null, msgs);
			msgs = basicSetChildExpr(newChildExpr, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.UNARY_OP__CHILD_EXPR, newChildExpr,
					newChildExpr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.UNARY_OP__CHILD_EXPR:
			return basicSetChildExpr(null, msgs);
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
		case GithubMMPackage.UNARY_OP__CHILD_EXPR:
			return getChildExpr();
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
		case GithubMMPackage.UNARY_OP__CHILD_EXPR:
			setChildExpr((Expression) newValue);
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
		case GithubMMPackage.UNARY_OP__CHILD_EXPR:
			setChildExpr((Expression) null);
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
		case GithubMMPackage.UNARY_OP__CHILD_EXPR:
			return childExpr != null;
		}
		return super.eIsSet(featureID);
	}

} //UnaryOpImpl
