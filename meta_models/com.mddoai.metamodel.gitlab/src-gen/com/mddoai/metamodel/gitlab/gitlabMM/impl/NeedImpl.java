/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Need;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Need</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl#getJob <em>Job</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl#isArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl#getProject <em>Project</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl#getRef <em>Ref</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl#getPipeline <em>Pipeline</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl#isOptional <em>Optional</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NeedImpl extends MinimalEObjectImpl.Container implements Need {
	/**
	 * The default value of the '{@link #getJob() <em>Job</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJob()
	 * @generated
	 * @ordered
	 */
	protected static final String JOB_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJob() <em>Job</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJob()
	 * @generated
	 * @ordered
	 */
	protected String job = JOB_EDEFAULT;

	/**
	 * The default value of the '{@link #isArtifacts() <em>Artifacts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isArtifacts()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ARTIFACTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isArtifacts() <em>Artifacts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isArtifacts()
	 * @generated
	 * @ordered
	 */
	protected boolean artifacts = ARTIFACTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getProject() <em>Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProject()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProject() <em>Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProject()
	 * @generated
	 * @ordered
	 */
	protected String project = PROJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getRef() <em>Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRef()
	 * @generated
	 * @ordered
	 */
	protected static final String REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRef() <em>Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRef()
	 * @generated
	 * @ordered
	 */
	protected String ref = REF_EDEFAULT;

	/**
	 * The default value of the '{@link #getPipeline() <em>Pipeline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPipeline()
	 * @generated
	 * @ordered
	 */
	protected static final String PIPELINE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPipeline() <em>Pipeline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPipeline()
	 * @generated
	 * @ordered
	 */
	protected String pipeline = PIPELINE_EDEFAULT;

	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected boolean optional = OPTIONAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NeedImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.NEED;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getJob() {
		return job;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setJob(String newJob) {
		String oldJob = job;
		job = newJob;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.NEED__JOB, oldJob, job));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isArtifacts() {
		return artifacts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArtifacts(boolean newArtifacts) {
		boolean oldArtifacts = artifacts;
		artifacts = newArtifacts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.NEED__ARTIFACTS, oldArtifacts,
					artifacts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProject() {
		return project;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProject(String newProject) {
		String oldProject = project;
		project = newProject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.NEED__PROJECT, oldProject, project));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRef() {
		return ref;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRef(String newRef) {
		String oldRef = ref;
		ref = newRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.NEED__REF, oldRef, ref));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPipeline() {
		return pipeline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPipeline(String newPipeline) {
		String oldPipeline = pipeline;
		pipeline = newPipeline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.NEED__PIPELINE, oldPipeline,
					pipeline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOptional() {
		return optional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOptional(boolean newOptional) {
		boolean oldOptional = optional;
		optional = newOptional;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.NEED__OPTIONAL, oldOptional,
					optional));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GitlabMMPackage.NEED__JOB:
			return getJob();
		case GitlabMMPackage.NEED__ARTIFACTS:
			return isArtifacts();
		case GitlabMMPackage.NEED__PROJECT:
			return getProject();
		case GitlabMMPackage.NEED__REF:
			return getRef();
		case GitlabMMPackage.NEED__PIPELINE:
			return getPipeline();
		case GitlabMMPackage.NEED__OPTIONAL:
			return isOptional();
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
		case GitlabMMPackage.NEED__JOB:
			setJob((String) newValue);
			return;
		case GitlabMMPackage.NEED__ARTIFACTS:
			setArtifacts((Boolean) newValue);
			return;
		case GitlabMMPackage.NEED__PROJECT:
			setProject((String) newValue);
			return;
		case GitlabMMPackage.NEED__REF:
			setRef((String) newValue);
			return;
		case GitlabMMPackage.NEED__PIPELINE:
			setPipeline((String) newValue);
			return;
		case GitlabMMPackage.NEED__OPTIONAL:
			setOptional((Boolean) newValue);
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
		case GitlabMMPackage.NEED__JOB:
			setJob(JOB_EDEFAULT);
			return;
		case GitlabMMPackage.NEED__ARTIFACTS:
			setArtifacts(ARTIFACTS_EDEFAULT);
			return;
		case GitlabMMPackage.NEED__PROJECT:
			setProject(PROJECT_EDEFAULT);
			return;
		case GitlabMMPackage.NEED__REF:
			setRef(REF_EDEFAULT);
			return;
		case GitlabMMPackage.NEED__PIPELINE:
			setPipeline(PIPELINE_EDEFAULT);
			return;
		case GitlabMMPackage.NEED__OPTIONAL:
			setOptional(OPTIONAL_EDEFAULT);
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
		case GitlabMMPackage.NEED__JOB:
			return JOB_EDEFAULT == null ? job != null : !JOB_EDEFAULT.equals(job);
		case GitlabMMPackage.NEED__ARTIFACTS:
			return artifacts != ARTIFACTS_EDEFAULT;
		case GitlabMMPackage.NEED__PROJECT:
			return PROJECT_EDEFAULT == null ? project != null : !PROJECT_EDEFAULT.equals(project);
		case GitlabMMPackage.NEED__REF:
			return REF_EDEFAULT == null ? ref != null : !REF_EDEFAULT.equals(ref);
		case GitlabMMPackage.NEED__PIPELINE:
			return PIPELINE_EDEFAULT == null ? pipeline != null : !PIPELINE_EDEFAULT.equals(pipeline);
		case GitlabMMPackage.NEED__OPTIONAL:
			return optional != OPTIONAL_EDEFAULT;
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
		result.append(" (job: ");
		result.append(job);
		result.append(", artifacts: ");
		result.append(artifacts);
		result.append(", project: ");
		result.append(project);
		result.append(", ref: ");
		result.append(ref);
		result.append(", pipeline: ");
		result.append(pipeline);
		result.append(", optional: ");
		result.append(optional);
		result.append(')');
		return result.toString();
	}

} //NeedImpl
