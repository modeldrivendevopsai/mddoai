/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Download Build Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadBuildStepImpl#getDownloadBuild <em>Download Build</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadBuildStepImpl#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadBuildStepImpl#getPath <em>Path</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadBuildStepImpl#getPatterns <em>Patterns</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DownloadBuildStepImpl extends StepImpl implements DownloadBuildStep {
	/**
	 * The default value of the '{@link #getDownloadBuild() <em>Download Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownloadBuild()
	 * @generated
	 * @ordered
	 */
	protected static final String DOWNLOAD_BUILD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDownloadBuild() <em>Download Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownloadBuild()
	 * @generated
	 * @ordered
	 */
	protected String downloadBuild = DOWNLOAD_BUILD_EDEFAULT;

	/**
	 * The default value of the '{@link #getArtifact() <em>Artifact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifact()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifact() <em>Artifact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifact()
	 * @generated
	 * @ordered
	 */
	protected String artifact = ARTIFACT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getPatterns() <em>Patterns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPatterns()
	 * @generated
	 * @ordered
	 */
	protected static final String PATTERNS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPatterns() <em>Patterns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPatterns()
	 * @generated
	 * @ordered
	 */
	protected String patterns = PATTERNS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DownloadBuildStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.DOWNLOAD_BUILD_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDownloadBuild() {
		return downloadBuild;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDownloadBuild(String newDownloadBuild) {
		String oldDownloadBuild = downloadBuild;
		downloadBuild = newDownloadBuild;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__DOWNLOAD_BUILD, oldDownloadBuild, downloadBuild));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getArtifact() {
		return artifact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArtifact(String newArtifact) {
		String oldArtifact = artifact;
		artifact = newArtifact;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__ARTIFACT,
					oldArtifact, artifact));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATH,
					oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPatterns() {
		return patterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPatterns(String newPatterns) {
		String oldPatterns = patterns;
		patterns = newPatterns;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATTERNS,
					oldPatterns, patterns));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__DOWNLOAD_BUILD:
			return getDownloadBuild();
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__ARTIFACT:
			return getArtifact();
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATH:
			return getPath();
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATTERNS:
			return getPatterns();
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
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__DOWNLOAD_BUILD:
			setDownloadBuild((String) newValue);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__ARTIFACT:
			setArtifact((String) newValue);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATH:
			setPath((String) newValue);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATTERNS:
			setPatterns((String) newValue);
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
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__DOWNLOAD_BUILD:
			setDownloadBuild(DOWNLOAD_BUILD_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__ARTIFACT:
			setArtifact(ARTIFACT_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATH:
			setPath(PATH_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATTERNS:
			setPatterns(PATTERNS_EDEFAULT);
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
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__DOWNLOAD_BUILD:
			return DOWNLOAD_BUILD_EDEFAULT == null ? downloadBuild != null
					: !DOWNLOAD_BUILD_EDEFAULT.equals(downloadBuild);
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__ARTIFACT:
			return ARTIFACT_EDEFAULT == null ? artifact != null : !ARTIFACT_EDEFAULT.equals(artifact);
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATH:
			return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP__PATTERNS:
			return PATTERNS_EDEFAULT == null ? patterns != null : !PATTERNS_EDEFAULT.equals(patterns);
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
		result.append(" (downloadBuild: ");
		result.append(downloadBuild);
		result.append(", artifact: ");
		result.append(artifact);
		result.append(", path: ");
		result.append(path);
		result.append(", patterns: ");
		result.append(patterns);
		result.append(')');
		return result.toString();
	}

} //DownloadBuildStepImpl
