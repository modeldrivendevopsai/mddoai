/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Key;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KeyImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KeyImpl#getFiles <em>Files</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KeyImpl#getFiles_commits <em>Files commits</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KeyImpl#getPrefix <em>Prefix</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KeyImpl extends MinimalEObjectImpl.Container implements Key {
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
	 * The cached value of the '{@link #getFiles() <em>Files</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFiles()
	 * @generated
	 * @ordered
	 */
	protected EList<String> files;

	/**
	 * The cached value of the '{@link #getFiles_commits() <em>Files commits</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFiles_commits()
	 * @generated
	 * @ordered
	 */
	protected EList<String> files_commits;

	/**
	 * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected String prefix = PREFIX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.KEY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.KEY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getFiles() {
		if (files == null) {
			files = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.KEY__FILES);
		}
		return files;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getFiles_commits() {
		if (files_commits == null) {
			files_commits = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.KEY__FILES_COMMITS);
		}
		return files_commits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPrefix() {
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrefix(String newPrefix) {
		String oldPrefix = prefix;
		prefix = newPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.KEY__PREFIX, oldPrefix, prefix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GitlabMMPackage.KEY__NAME:
			return getName();
		case GitlabMMPackage.KEY__FILES:
			return getFiles();
		case GitlabMMPackage.KEY__FILES_COMMITS:
			return getFiles_commits();
		case GitlabMMPackage.KEY__PREFIX:
			return getPrefix();
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
		case GitlabMMPackage.KEY__NAME:
			setName((String) newValue);
			return;
		case GitlabMMPackage.KEY__FILES:
			getFiles().clear();
			getFiles().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.KEY__FILES_COMMITS:
			getFiles_commits().clear();
			getFiles_commits().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.KEY__PREFIX:
			setPrefix((String) newValue);
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
		case GitlabMMPackage.KEY__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GitlabMMPackage.KEY__FILES:
			getFiles().clear();
			return;
		case GitlabMMPackage.KEY__FILES_COMMITS:
			getFiles_commits().clear();
			return;
		case GitlabMMPackage.KEY__PREFIX:
			setPrefix(PREFIX_EDEFAULT);
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
		case GitlabMMPackage.KEY__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case GitlabMMPackage.KEY__FILES:
			return files != null && !files.isEmpty();
		case GitlabMMPackage.KEY__FILES_COMMITS:
			return files_commits != null && !files_commits.isEmpty();
		case GitlabMMPackage.KEY__PREFIX:
			return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
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
		result.append(", files: ");
		result.append(files);
		result.append(", files_commits: ");
		result.append(files_commits);
		result.append(", prefix: ");
		result.append(prefix);
		result.append(')');
		return result.toString();
	}

} //KeyImpl
