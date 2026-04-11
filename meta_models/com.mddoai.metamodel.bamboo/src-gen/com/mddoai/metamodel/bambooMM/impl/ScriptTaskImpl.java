/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.ScriptTask;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Script Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl#getInterpreter <em>Interpreter</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl#getScripts <em>Scripts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl#getFile <em>File</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl#getArgument <em>Argument</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl#getWorkingDir <em>Working Dir</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScriptTaskImpl extends TaskImpl implements ScriptTask {
	/**
	 * The default value of the '{@link #getInterpreter() <em>Interpreter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterpreter()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERPRETER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterpreter() <em>Interpreter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterpreter()
	 * @generated
	 * @ordered
	 */
	protected String interpreter = INTERPRETER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScripts() <em>Scripts</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScripts()
	 * @generated
	 * @ordered
	 */
	protected EList<String> scripts;

	/**
	 * The default value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFile() <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFile()
	 * @generated
	 * @ordered
	 */
	protected String file = FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getArgument() <em>Argument</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArgument()
	 * @generated
	 * @ordered
	 */
	protected static final String ARGUMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArgument() <em>Argument</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArgument()
	 * @generated
	 * @ordered
	 */
	protected String argument = ARGUMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnvironment() <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironment()
	 * @generated
	 * @ordered
	 */
	protected static final String ENVIRONMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnvironment() <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironment()
	 * @generated
	 * @ordered
	 */
	protected String environment = ENVIRONMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getWorkingDir() <em>Working Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkingDir()
	 * @generated
	 * @ordered
	 */
	protected static final String WORKING_DIR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkingDir() <em>Working Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkingDir()
	 * @generated
	 * @ordered
	 */
	protected String workingDir = WORKING_DIR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScriptTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.SCRIPT_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInterpreter() {
		return interpreter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterpreter(String newInterpreter) {
		String oldInterpreter = interpreter;
		interpreter = newInterpreter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.SCRIPT_TASK__INTERPRETER,
					oldInterpreter, interpreter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getScripts() {
		if (scripts == null) {
			scripts = new EDataTypeUniqueEList<String>(String.class, this, BambooPackage.SCRIPT_TASK__SCRIPTS);
		}
		return scripts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFile() {
		return file;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFile(String newFile) {
		String oldFile = file;
		file = newFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.SCRIPT_TASK__FILE, oldFile, file));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getArgument() {
		return argument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArgument(String newArgument) {
		String oldArgument = argument;
		argument = newArgument;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.SCRIPT_TASK__ARGUMENT, oldArgument,
					argument));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEnvironment() {
		return environment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnvironment(String newEnvironment) {
		String oldEnvironment = environment;
		environment = newEnvironment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.SCRIPT_TASK__ENVIRONMENT,
					oldEnvironment, environment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWorkingDir() {
		return workingDir;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWorkingDir(String newWorkingDir) {
		String oldWorkingDir = workingDir;
		workingDir = newWorkingDir;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.SCRIPT_TASK__WORKING_DIR, oldWorkingDir,
					workingDir));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.SCRIPT_TASK__INTERPRETER:
			return getInterpreter();
		case BambooPackage.SCRIPT_TASK__SCRIPTS:
			return getScripts();
		case BambooPackage.SCRIPT_TASK__FILE:
			return getFile();
		case BambooPackage.SCRIPT_TASK__ARGUMENT:
			return getArgument();
		case BambooPackage.SCRIPT_TASK__ENVIRONMENT:
			return getEnvironment();
		case BambooPackage.SCRIPT_TASK__WORKING_DIR:
			return getWorkingDir();
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
		case BambooPackage.SCRIPT_TASK__INTERPRETER:
			setInterpreter((String) newValue);
			return;
		case BambooPackage.SCRIPT_TASK__SCRIPTS:
			getScripts().clear();
			getScripts().addAll((Collection<? extends String>) newValue);
			return;
		case BambooPackage.SCRIPT_TASK__FILE:
			setFile((String) newValue);
			return;
		case BambooPackage.SCRIPT_TASK__ARGUMENT:
			setArgument((String) newValue);
			return;
		case BambooPackage.SCRIPT_TASK__ENVIRONMENT:
			setEnvironment((String) newValue);
			return;
		case BambooPackage.SCRIPT_TASK__WORKING_DIR:
			setWorkingDir((String) newValue);
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
		case BambooPackage.SCRIPT_TASK__INTERPRETER:
			setInterpreter(INTERPRETER_EDEFAULT);
			return;
		case BambooPackage.SCRIPT_TASK__SCRIPTS:
			getScripts().clear();
			return;
		case BambooPackage.SCRIPT_TASK__FILE:
			setFile(FILE_EDEFAULT);
			return;
		case BambooPackage.SCRIPT_TASK__ARGUMENT:
			setArgument(ARGUMENT_EDEFAULT);
			return;
		case BambooPackage.SCRIPT_TASK__ENVIRONMENT:
			setEnvironment(ENVIRONMENT_EDEFAULT);
			return;
		case BambooPackage.SCRIPT_TASK__WORKING_DIR:
			setWorkingDir(WORKING_DIR_EDEFAULT);
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
		case BambooPackage.SCRIPT_TASK__INTERPRETER:
			return INTERPRETER_EDEFAULT == null ? interpreter != null : !INTERPRETER_EDEFAULT.equals(interpreter);
		case BambooPackage.SCRIPT_TASK__SCRIPTS:
			return scripts != null && !scripts.isEmpty();
		case BambooPackage.SCRIPT_TASK__FILE:
			return FILE_EDEFAULT == null ? file != null : !FILE_EDEFAULT.equals(file);
		case BambooPackage.SCRIPT_TASK__ARGUMENT:
			return ARGUMENT_EDEFAULT == null ? argument != null : !ARGUMENT_EDEFAULT.equals(argument);
		case BambooPackage.SCRIPT_TASK__ENVIRONMENT:
			return ENVIRONMENT_EDEFAULT == null ? environment != null : !ENVIRONMENT_EDEFAULT.equals(environment);
		case BambooPackage.SCRIPT_TASK__WORKING_DIR:
			return WORKING_DIR_EDEFAULT == null ? workingDir != null : !WORKING_DIR_EDEFAULT.equals(workingDir);
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
		result.append(" (interpreter: ");
		result.append(interpreter);
		result.append(", scripts: ");
		result.append(scripts);
		result.append(", file: ");
		result.append(file);
		result.append(", argument: ");
		result.append(argument);
		result.append(", environment: ");
		result.append(environment);
		result.append(", workingDir: ");
		result.append(workingDir);
		result.append(')');
		return result.toString();
	}

} //ScriptTaskImpl
