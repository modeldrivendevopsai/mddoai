/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pipeline Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl#getPipeline <em>Pipeline</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl#getProject <em>Project</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl#getSource <em>Source</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl#getBranch <em>Branch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl#getTrigger <em>Trigger</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PipelineResourceImpl extends MinimalEObjectImpl.Container implements PipelineResource {
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
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected String source = SOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getBranch() <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranch()
	 * @generated
	 * @ordered
	 */
	protected static final String BRANCH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBranch() <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranch()
	 * @generated
	 * @ordered
	 */
	protected String branch = BRANCH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected EList<String> tags;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected PipelineResourceTrigger trigger;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PipelineResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.PIPELINE_RESOURCE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE_RESOURCE__PIPELINE,
					oldPipeline, pipeline));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE_RESOURCE__PROJECT,
					oldProject, project));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(String newSource) {
		String oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE_RESOURCE__SOURCE,
					oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE_RESOURCE__VERSION,
					oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBranch() {
		return branch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBranch(String newBranch) {
		String oldBranch = branch;
		branch = newBranch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE_RESOURCE__BRANCH,
					oldBranch, branch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getTags() {
		if (tags == null) {
			tags = new EDataTypeUniqueEList<String>(String.class, this, AzuredevopsMMPackage.PIPELINE_RESOURCE__TAGS);
		}
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PipelineResourceTrigger getTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrigger(PipelineResourceTrigger newTrigger, NotificationChain msgs) {
		PipelineResourceTrigger oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE_RESOURCE__TRIGGER, oldTrigger, newTrigger);
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
	public void setTrigger(PipelineResourceTrigger newTrigger) {
		if (newTrigger != trigger) {
			NotificationChain msgs = null;
			if (trigger != null)
				msgs = ((InternalEObject) trigger).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE_RESOURCE__TRIGGER, null, msgs);
			if (newTrigger != null)
				msgs = ((InternalEObject) newTrigger).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE_RESOURCE__TRIGGER, null, msgs);
			msgs = basicSetTrigger(newTrigger, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE_RESOURCE__TRIGGER,
					newTrigger, newTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__TRIGGER:
			return basicSetTrigger(null, msgs);
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
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__PIPELINE:
			return getPipeline();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__PROJECT:
			return getProject();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__SOURCE:
			return getSource();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__VERSION:
			return getVersion();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__BRANCH:
			return getBranch();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__TAGS:
			return getTags();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__TRIGGER:
			return getTrigger();
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
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__PIPELINE:
			setPipeline((String) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__PROJECT:
			setProject((String) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__SOURCE:
			setSource((String) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__VERSION:
			setVersion((String) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__BRANCH:
			setBranch((String) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__TAGS:
			getTags().clear();
			getTags().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__TRIGGER:
			setTrigger((PipelineResourceTrigger) newValue);
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
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__PIPELINE:
			setPipeline(PIPELINE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__PROJECT:
			setProject(PROJECT_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__SOURCE:
			setSource(SOURCE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__BRANCH:
			setBranch(BRANCH_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__TAGS:
			getTags().clear();
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__TRIGGER:
			setTrigger((PipelineResourceTrigger) null);
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
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__PIPELINE:
			return PIPELINE_EDEFAULT == null ? pipeline != null : !PIPELINE_EDEFAULT.equals(pipeline);
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__PROJECT:
			return PROJECT_EDEFAULT == null ? project != null : !PROJECT_EDEFAULT.equals(project);
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__SOURCE:
			return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__BRANCH:
			return BRANCH_EDEFAULT == null ? branch != null : !BRANCH_EDEFAULT.equals(branch);
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__TAGS:
			return tags != null && !tags.isEmpty();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE__TRIGGER:
			return trigger != null;
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
		result.append(" (pipeline: ");
		result.append(pipeline);
		result.append(", project: ");
		result.append(project);
		result.append(", source: ");
		result.append(source);
		result.append(", version: ");
		result.append(version);
		result.append(", branch: ");
		result.append(branch);
		result.append(", tags: ");
		result.append(tags);
		result.append(')');
		return result.toString();
	}

} //PipelineResourceImpl
