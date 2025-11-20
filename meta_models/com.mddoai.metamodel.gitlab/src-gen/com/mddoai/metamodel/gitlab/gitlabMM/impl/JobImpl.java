/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.Artifact;
import com.mddoai.metamodel.gitlab.gitlabMM.BeforeScript;
import com.mddoai.metamodel.gitlab.gitlabMM.Dependencies;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Job;
import com.mddoai.metamodel.gitlab.gitlabMM.Only;
import com.mddoai.metamodel.gitlab.gitlabMM.Script;
import com.mddoai.metamodel.gitlab.gitlabMM.Tags;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getStage <em>Stage</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getScript <em>Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getBeforeScript <em>Before Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getOnly <em>Only</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getWhen <em>When</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JobImpl extends MinimalEObjectImpl.Container implements Job {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getArtifacts() <em>Artifacts</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifacts()
	 * @generated
	 * @ordered
	 */
	protected Artifact artifacts;

	/**
	 * The cached value of the '{@link #getScript() <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected Script script;

	/**
	 * The cached value of the '{@link #getBeforeScript() <em>Before Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeforeScript()
	 * @generated
	 * @ordered
	 */
	protected BeforeScript beforeScript;

	/**
	 * The default value of the '{@link #getImage() <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected static final String IMAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImage() <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected String image = IMAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected Tags tags;

	/**
	 * The cached value of the '{@link #getOnly() <em>Only</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnly()
	 * @generated
	 * @ordered
	 */
	protected Only only;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected Dependencies dependencies;

	/**
	 * The default value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected static final String WHEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected String when = WHEN_EDEFAULT;

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
		return GitlabMMPackage.Literals.JOB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__STAGE, oldStage, stage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Artifact getArtifacts() {
		return artifacts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArtifacts(Artifact newArtifacts, NotificationChain msgs) {
		Artifact oldArtifacts = artifacts;
		artifacts = newArtifacts;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.JOB__ARTIFACTS, oldArtifacts, newArtifacts);
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
	public void setArtifacts(Artifact newArtifacts) {
		if (newArtifacts != artifacts) {
			NotificationChain msgs = null;
			if (artifacts != null)
				msgs = ((InternalEObject) artifacts).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__ARTIFACTS, null, msgs);
			if (newArtifacts != null)
				msgs = ((InternalEObject) newArtifacts).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__ARTIFACTS, null, msgs);
			msgs = basicSetArtifacts(newArtifacts, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__ARTIFACTS, newArtifacts,
					newArtifacts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Script getScript() {
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScript(Script newScript, NotificationChain msgs) {
		Script oldScript = script;
		script = newScript;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__SCRIPT,
					oldScript, newScript);
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
	public void setScript(Script newScript) {
		if (newScript != script) {
			NotificationChain msgs = null;
			if (script != null)
				msgs = ((InternalEObject) script).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__SCRIPT, null, msgs);
			if (newScript != null)
				msgs = ((InternalEObject) newScript).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__SCRIPT, null, msgs);
			msgs = basicSetScript(newScript, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__SCRIPT, newScript, newScript));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BeforeScript getBeforeScript() {
		return beforeScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBeforeScript(BeforeScript newBeforeScript, NotificationChain msgs) {
		BeforeScript oldBeforeScript = beforeScript;
		beforeScript = newBeforeScript;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.JOB__BEFORE_SCRIPT, oldBeforeScript, newBeforeScript);
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
	public void setBeforeScript(BeforeScript newBeforeScript) {
		if (newBeforeScript != beforeScript) {
			NotificationChain msgs = null;
			if (beforeScript != null)
				msgs = ((InternalEObject) beforeScript).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__BEFORE_SCRIPT, null, msgs);
			if (newBeforeScript != null)
				msgs = ((InternalEObject) newBeforeScript).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__BEFORE_SCRIPT, null, msgs);
			msgs = basicSetBeforeScript(newBeforeScript, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__BEFORE_SCRIPT, newBeforeScript,
					newBeforeScript));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getImage() {
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImage(String newImage) {
		String oldImage = image;
		image = newImage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__IMAGE, oldImage, image));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tags getTags() {
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTags(Tags newTags, NotificationChain msgs) {
		Tags oldTags = tags;
		tags = newTags;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__TAGS,
					oldTags, newTags);
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
	public void setTags(Tags newTags) {
		if (newTags != tags) {
			NotificationChain msgs = null;
			if (tags != null)
				msgs = ((InternalEObject) tags).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__TAGS,
						null, msgs);
			if (newTags != null)
				msgs = ((InternalEObject) newTags).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__TAGS,
						null, msgs);
			msgs = basicSetTags(newTags, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__TAGS, newTags, newTags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Only getOnly() {
		return only;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOnly(Only newOnly, NotificationChain msgs) {
		Only oldOnly = only;
		only = newOnly;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__ONLY,
					oldOnly, newOnly);
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
	public void setOnly(Only newOnly) {
		if (newOnly != only) {
			NotificationChain msgs = null;
			if (only != null)
				msgs = ((InternalEObject) only).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__ONLY,
						null, msgs);
			if (newOnly != null)
				msgs = ((InternalEObject) newOnly).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__ONLY,
						null, msgs);
			msgs = basicSetOnly(newOnly, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__ONLY, newOnly, newOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Dependencies getDependencies() {
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDependencies(Dependencies newDependencies, NotificationChain msgs) {
		Dependencies oldDependencies = dependencies;
		dependencies = newDependencies;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.JOB__DEPENDENCIES, oldDependencies, newDependencies);
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
	public void setDependencies(Dependencies newDependencies) {
		if (newDependencies != dependencies) {
			NotificationChain msgs = null;
			if (dependencies != null)
				msgs = ((InternalEObject) dependencies).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__DEPENDENCIES, null, msgs);
			if (newDependencies != null)
				msgs = ((InternalEObject) newDependencies).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__DEPENDENCIES, null, msgs);
			msgs = basicSetDependencies(newDependencies, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__DEPENDENCIES, newDependencies,
					newDependencies));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWhen() {
		return when;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWhen(String newWhen) {
		String oldWhen = when;
		when = newWhen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__WHEN, oldWhen, when));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.JOB__ARTIFACTS:
			return basicSetArtifacts(null, msgs);
		case GitlabMMPackage.JOB__SCRIPT:
			return basicSetScript(null, msgs);
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
			return basicSetBeforeScript(null, msgs);
		case GitlabMMPackage.JOB__TAGS:
			return basicSetTags(null, msgs);
		case GitlabMMPackage.JOB__ONLY:
			return basicSetOnly(null, msgs);
		case GitlabMMPackage.JOB__DEPENDENCIES:
			return basicSetDependencies(null, msgs);
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
		case GitlabMMPackage.JOB__NAME:
			return getName();
		case GitlabMMPackage.JOB__STAGE:
			return getStage();
		case GitlabMMPackage.JOB__ARTIFACTS:
			return getArtifacts();
		case GitlabMMPackage.JOB__SCRIPT:
			return getScript();
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
			return getBeforeScript();
		case GitlabMMPackage.JOB__IMAGE:
			return getImage();
		case GitlabMMPackage.JOB__TAGS:
			return getTags();
		case GitlabMMPackage.JOB__ONLY:
			return getOnly();
		case GitlabMMPackage.JOB__DEPENDENCIES:
			return getDependencies();
		case GitlabMMPackage.JOB__WHEN:
			return getWhen();
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
		case GitlabMMPackage.JOB__NAME:
			setName((String) newValue);
			return;
		case GitlabMMPackage.JOB__STAGE:
			setStage((String) newValue);
			return;
		case GitlabMMPackage.JOB__ARTIFACTS:
			setArtifacts((Artifact) newValue);
			return;
		case GitlabMMPackage.JOB__SCRIPT:
			setScript((Script) newValue);
			return;
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
			setBeforeScript((BeforeScript) newValue);
			return;
		case GitlabMMPackage.JOB__IMAGE:
			setImage((String) newValue);
			return;
		case GitlabMMPackage.JOB__TAGS:
			setTags((Tags) newValue);
			return;
		case GitlabMMPackage.JOB__ONLY:
			setOnly((Only) newValue);
			return;
		case GitlabMMPackage.JOB__DEPENDENCIES:
			setDependencies((Dependencies) newValue);
			return;
		case GitlabMMPackage.JOB__WHEN:
			setWhen((String) newValue);
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
		case GitlabMMPackage.JOB__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__STAGE:
			setStage(STAGE_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__ARTIFACTS:
			setArtifacts((Artifact) null);
			return;
		case GitlabMMPackage.JOB__SCRIPT:
			setScript((Script) null);
			return;
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
			setBeforeScript((BeforeScript) null);
			return;
		case GitlabMMPackage.JOB__IMAGE:
			setImage(IMAGE_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__TAGS:
			setTags((Tags) null);
			return;
		case GitlabMMPackage.JOB__ONLY:
			setOnly((Only) null);
			return;
		case GitlabMMPackage.JOB__DEPENDENCIES:
			setDependencies((Dependencies) null);
			return;
		case GitlabMMPackage.JOB__WHEN:
			setWhen(WHEN_EDEFAULT);
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
		case GitlabMMPackage.JOB__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case GitlabMMPackage.JOB__STAGE:
			return STAGE_EDEFAULT == null ? stage != null : !STAGE_EDEFAULT.equals(stage);
		case GitlabMMPackage.JOB__ARTIFACTS:
			return artifacts != null;
		case GitlabMMPackage.JOB__SCRIPT:
			return script != null;
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
			return beforeScript != null;
		case GitlabMMPackage.JOB__IMAGE:
			return IMAGE_EDEFAULT == null ? image != null : !IMAGE_EDEFAULT.equals(image);
		case GitlabMMPackage.JOB__TAGS:
			return tags != null;
		case GitlabMMPackage.JOB__ONLY:
			return only != null;
		case GitlabMMPackage.JOB__DEPENDENCIES:
			return dependencies != null;
		case GitlabMMPackage.JOB__WHEN:
			return WHEN_EDEFAULT == null ? when != null : !WHEN_EDEFAULT.equals(when);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", stage: ");
		result.append(stage);
		result.append(", image: ");
		result.append(image);
		result.append(", when: ");
		result.append(when);
		result.append(')');
		return result.toString();
	}

} //JobImpl
