/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.DockerContainer;
import com.mddoai.metamodel.pim.pimMM.Expression;
import com.mddoai.metamodel.pim.pimMM.Job;
import com.mddoai.metamodel.pim.pimMM.Matrix;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl#getIfCondition <em>If Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl#getMatrix <em>Matrix</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl#getPrevious <em>Previous</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl#getNext <em>Next</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl#getMaxAttempts <em>Max Attempts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl#getAllowFailure <em>Allow Failure</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class JobImpl extends PipelineBlockImpl implements Job {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	 * The cached value of the '{@link #getServices() <em>Services</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EList<DockerContainer> services;

	/**
	 * The cached value of the '{@link #getMatrix() <em>Matrix</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatrix()
	 * @generated
	 * @ordered
	 */
	protected Matrix matrix;

	/**
	 * The cached value of the '{@link #getPrevious() <em>Previous</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrevious()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> previous;

	/**
	 * The cached value of the '{@link #getNext() <em>Next</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNext()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> next;

	/**
	 * The default value of the '{@link #getMaxAttempts() <em>Max Attempts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxAttempts()
	 * @generated
	 * @ordered
	 */
	protected static final Integer MAX_ATTEMPTS_EDEFAULT = Integer.valueOf(1);

	/**
	 * The cached value of the '{@link #getMaxAttempts() <em>Max Attempts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxAttempts()
	 * @generated
	 * @ordered
	 */
	protected Integer maxAttempts = MAX_ATTEMPTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ALLOW_FAILURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected Boolean allowFailure = ALLOW_FAILURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JobImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PimMMPackage.Literals.JOB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.JOB__ID, oldId, id));
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
					PimMMPackage.JOB__IF_CONDITION, oldIfCondition, newIfCondition);
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
						EOPPOSITE_FEATURE_BASE - PimMMPackage.JOB__IF_CONDITION, null, msgs);
			if (newIfCondition != null)
				msgs = ((InternalEObject) newIfCondition).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.JOB__IF_CONDITION, null, msgs);
			msgs = basicSetIfCondition(newIfCondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.JOB__IF_CONDITION, newIfCondition,
					newIfCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DockerContainer> getServices() {
		if (services == null) {
			services = new EObjectContainmentEList<DockerContainer>(DockerContainer.class, this,
					PimMMPackage.JOB__SERVICES);
		}
		return services;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Matrix getMatrix() {
		return matrix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMatrix(Matrix newMatrix, NotificationChain msgs) {
		Matrix oldMatrix = matrix;
		matrix = newMatrix;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PimMMPackage.JOB__MATRIX,
					oldMatrix, newMatrix);
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
	public void setMatrix(Matrix newMatrix) {
		if (newMatrix != matrix) {
			NotificationChain msgs = null;
			if (matrix != null)
				msgs = ((InternalEObject) matrix).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.JOB__MATRIX, null, msgs);
			if (newMatrix != null)
				msgs = ((InternalEObject) newMatrix).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.JOB__MATRIX, null, msgs);
			msgs = basicSetMatrix(newMatrix, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.JOB__MATRIX, newMatrix, newMatrix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Job> getPrevious() {
		if (previous == null) {
			previous = new EObjectWithInverseResolvingEList.ManyInverse<Job>(Job.class, this,
					PimMMPackage.JOB__PREVIOUS, PimMMPackage.JOB__NEXT);
		}
		return previous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Job> getNext() {
		if (next == null) {
			next = new EObjectWithInverseResolvingEList.ManyInverse<Job>(Job.class, this, PimMMPackage.JOB__NEXT,
					PimMMPackage.JOB__PREVIOUS);
		}
		return next;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getMaxAttempts() {
		return maxAttempts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxAttempts(Integer newMaxAttempts) {
		Integer oldMaxAttempts = maxAttempts;
		maxAttempts = newMaxAttempts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.JOB__MAX_ATTEMPTS, oldMaxAttempts,
					maxAttempts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAllowFailure() {
		return allowFailure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAllowFailure(Boolean newAllowFailure) {
		Boolean oldAllowFailure = allowFailure;
		allowFailure = newAllowFailure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.JOB__ALLOW_FAILURE, oldAllowFailure,
					allowFailure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.JOB__PREVIOUS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getPrevious()).basicAdd(otherEnd, msgs);
		case PimMMPackage.JOB__NEXT:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getNext()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.JOB__IF_CONDITION:
			return basicSetIfCondition(null, msgs);
		case PimMMPackage.JOB__SERVICES:
			return ((InternalEList<?>) getServices()).basicRemove(otherEnd, msgs);
		case PimMMPackage.JOB__MATRIX:
			return basicSetMatrix(null, msgs);
		case PimMMPackage.JOB__PREVIOUS:
			return ((InternalEList<?>) getPrevious()).basicRemove(otherEnd, msgs);
		case PimMMPackage.JOB__NEXT:
			return ((InternalEList<?>) getNext()).basicRemove(otherEnd, msgs);
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
		case PimMMPackage.JOB__ID:
			return getId();
		case PimMMPackage.JOB__IF_CONDITION:
			return getIfCondition();
		case PimMMPackage.JOB__SERVICES:
			return getServices();
		case PimMMPackage.JOB__MATRIX:
			return getMatrix();
		case PimMMPackage.JOB__PREVIOUS:
			return getPrevious();
		case PimMMPackage.JOB__NEXT:
			return getNext();
		case PimMMPackage.JOB__MAX_ATTEMPTS:
			return getMaxAttempts();
		case PimMMPackage.JOB__ALLOW_FAILURE:
			return getAllowFailure();
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
		case PimMMPackage.JOB__ID:
			setId((String) newValue);
			return;
		case PimMMPackage.JOB__IF_CONDITION:
			setIfCondition((Expression) newValue);
			return;
		case PimMMPackage.JOB__SERVICES:
			getServices().clear();
			getServices().addAll((Collection<? extends DockerContainer>) newValue);
			return;
		case PimMMPackage.JOB__MATRIX:
			setMatrix((Matrix) newValue);
			return;
		case PimMMPackage.JOB__PREVIOUS:
			getPrevious().clear();
			getPrevious().addAll((Collection<? extends Job>) newValue);
			return;
		case PimMMPackage.JOB__NEXT:
			getNext().clear();
			getNext().addAll((Collection<? extends Job>) newValue);
			return;
		case PimMMPackage.JOB__MAX_ATTEMPTS:
			setMaxAttempts((Integer) newValue);
			return;
		case PimMMPackage.JOB__ALLOW_FAILURE:
			setAllowFailure((Boolean) newValue);
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
		case PimMMPackage.JOB__ID:
			setId(ID_EDEFAULT);
			return;
		case PimMMPackage.JOB__IF_CONDITION:
			setIfCondition((Expression) null);
			return;
		case PimMMPackage.JOB__SERVICES:
			getServices().clear();
			return;
		case PimMMPackage.JOB__MATRIX:
			setMatrix((Matrix) null);
			return;
		case PimMMPackage.JOB__PREVIOUS:
			getPrevious().clear();
			return;
		case PimMMPackage.JOB__NEXT:
			getNext().clear();
			return;
		case PimMMPackage.JOB__MAX_ATTEMPTS:
			setMaxAttempts(MAX_ATTEMPTS_EDEFAULT);
			return;
		case PimMMPackage.JOB__ALLOW_FAILURE:
			setAllowFailure(ALLOW_FAILURE_EDEFAULT);
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
		case PimMMPackage.JOB__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case PimMMPackage.JOB__IF_CONDITION:
			return ifCondition != null;
		case PimMMPackage.JOB__SERVICES:
			return services != null && !services.isEmpty();
		case PimMMPackage.JOB__MATRIX:
			return matrix != null;
		case PimMMPackage.JOB__PREVIOUS:
			return previous != null && !previous.isEmpty();
		case PimMMPackage.JOB__NEXT:
			return next != null && !next.isEmpty();
		case PimMMPackage.JOB__MAX_ATTEMPTS:
			return MAX_ATTEMPTS_EDEFAULT == null ? maxAttempts != null : !MAX_ATTEMPTS_EDEFAULT.equals(maxAttempts);
		case PimMMPackage.JOB__ALLOW_FAILURE:
			return ALLOW_FAILURE_EDEFAULT == null ? allowFailure != null : !ALLOW_FAILURE_EDEFAULT.equals(allowFailure);
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
		result.append(" (id: ");
		result.append(id);
		result.append(", maxAttempts: ");
		result.append(maxAttempts);
		result.append(", allowFailure: ");
		result.append(allowFailure);
		result.append(')');
		return result.toString();
	}

} //JobImpl
