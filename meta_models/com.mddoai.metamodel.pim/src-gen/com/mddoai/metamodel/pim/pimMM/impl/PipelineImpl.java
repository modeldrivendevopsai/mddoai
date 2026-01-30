/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.Job;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.pim.pimMM.Pipeline;
import com.mddoai.metamodel.pim.pimMM.Trigger;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pipeline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl#getJobStreams <em>Job Streams</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PipelineImpl extends PipelineBlockImpl implements Pipeline {
	/**
	 * The cached value of the '{@link #getTriggers() <em>Triggers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggers()
	 * @generated
	 * @ordered
	 */
	protected EList<Trigger> triggers;

	/**
	 * The cached value of the '{@link #getJobStreams() <em>Job Streams</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJobStreams()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> jobStreams;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PipelineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PimMMPackage.Literals.PIPELINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Trigger> getTriggers() {
		if (triggers == null) {
			triggers = new EObjectContainmentEList<Trigger>(Trigger.class, this, PimMMPackage.PIPELINE__TRIGGERS);
		}
		return triggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Job> getJobStreams() {
		if (jobStreams == null) {
			jobStreams = new EObjectResolvingEList<Job>(Job.class, this, PimMMPackage.PIPELINE__JOB_STREAMS);
		}
		return jobStreams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.PIPELINE__TRIGGERS:
			return ((InternalEList<?>) getTriggers()).basicRemove(otherEnd, msgs);
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
		case PimMMPackage.PIPELINE__TRIGGERS:
			return getTriggers();
		case PimMMPackage.PIPELINE__JOB_STREAMS:
			return getJobStreams();
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
		case PimMMPackage.PIPELINE__TRIGGERS:
			getTriggers().clear();
			getTriggers().addAll((Collection<? extends Trigger>) newValue);
			return;
		case PimMMPackage.PIPELINE__JOB_STREAMS:
			getJobStreams().clear();
			getJobStreams().addAll((Collection<? extends Job>) newValue);
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
		case PimMMPackage.PIPELINE__TRIGGERS:
			getTriggers().clear();
			return;
		case PimMMPackage.PIPELINE__JOB_STREAMS:
			getJobStreams().clear();
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
		case PimMMPackage.PIPELINE__TRIGGERS:
			return triggers != null && !triggers.isEmpty();
		case PimMMPackage.PIPELINE__JOB_STREAMS:
			return jobStreams != null && !jobStreams.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PipelineImpl
