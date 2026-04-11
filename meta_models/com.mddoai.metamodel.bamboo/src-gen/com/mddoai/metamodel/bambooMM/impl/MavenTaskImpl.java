/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.MavenTask;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Maven Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl#getExecutable <em>Executable</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl#getJdk <em>Jdk</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl#getGoal <em>Goal</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl#getTests <em>Tests</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl#getWorkingDir <em>Working Dir</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl#getProjectFile <em>Project File</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl#getUseReturnCode <em>Use Return Code</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MavenTaskImpl extends TaskImpl implements MavenTask {
	/**
	 * The default value of the '{@link #getExecutable() <em>Executable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutable()
	 * @generated
	 * @ordered
	 */
	protected static final String EXECUTABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExecutable() <em>Executable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutable()
	 * @generated
	 * @ordered
	 */
	protected String executable = EXECUTABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getJdk() <em>Jdk</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJdk()
	 * @generated
	 * @ordered
	 */
	protected static final String JDK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJdk() <em>Jdk</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJdk()
	 * @generated
	 * @ordered
	 */
	protected String jdk = JDK_EDEFAULT;

	/**
	 * The default value of the '{@link #getGoal() <em>Goal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGoal()
	 * @generated
	 * @ordered
	 */
	protected static final String GOAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGoal() <em>Goal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGoal()
	 * @generated
	 * @ordered
	 */
	protected String goal = GOAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getTests() <em>Tests</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTests()
	 * @generated
	 * @ordered
	 */
	protected static final String TESTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTests() <em>Tests</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTests()
	 * @generated
	 * @ordered
	 */
	protected String tests = TESTS_EDEFAULT;

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
	 * The default value of the '{@link #getProjectFile() <em>Project File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectFile()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjectFile() <em>Project File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectFile()
	 * @generated
	 * @ordered
	 */
	protected String projectFile = PROJECT_FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUseReturnCode() <em>Use Return Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseReturnCode()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean USE_RETURN_CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUseReturnCode() <em>Use Return Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseReturnCode()
	 * @generated
	 * @ordered
	 */
	protected Boolean useReturnCode = USE_RETURN_CODE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MavenTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.MAVEN_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getExecutable() {
		return executable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExecutable(String newExecutable) {
		String oldExecutable = executable;
		executable = newExecutable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.MAVEN_TASK__EXECUTABLE, oldExecutable,
					executable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getJdk() {
		return jdk;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setJdk(String newJdk) {
		String oldJdk = jdk;
		jdk = newJdk;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.MAVEN_TASK__JDK, oldJdk, jdk));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getGoal() {
		return goal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGoal(String newGoal) {
		String oldGoal = goal;
		goal = newGoal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.MAVEN_TASK__GOAL, oldGoal, goal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTests() {
		return tests;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTests(String newTests) {
		String oldTests = tests;
		tests = newTests;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.MAVEN_TASK__TESTS, oldTests, tests));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.MAVEN_TASK__ENVIRONMENT, oldEnvironment,
					environment));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.MAVEN_TASK__WORKING_DIR, oldWorkingDir,
					workingDir));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProjectFile() {
		return projectFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProjectFile(String newProjectFile) {
		String oldProjectFile = projectFile;
		projectFile = newProjectFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.MAVEN_TASK__PROJECT_FILE,
					oldProjectFile, projectFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getUseReturnCode() {
		return useReturnCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUseReturnCode(Boolean newUseReturnCode) {
		Boolean oldUseReturnCode = useReturnCode;
		useReturnCode = newUseReturnCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.MAVEN_TASK__USE_RETURN_CODE,
					oldUseReturnCode, useReturnCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.MAVEN_TASK__EXECUTABLE:
			return getExecutable();
		case BambooPackage.MAVEN_TASK__JDK:
			return getJdk();
		case BambooPackage.MAVEN_TASK__GOAL:
			return getGoal();
		case BambooPackage.MAVEN_TASK__TESTS:
			return getTests();
		case BambooPackage.MAVEN_TASK__ENVIRONMENT:
			return getEnvironment();
		case BambooPackage.MAVEN_TASK__WORKING_DIR:
			return getWorkingDir();
		case BambooPackage.MAVEN_TASK__PROJECT_FILE:
			return getProjectFile();
		case BambooPackage.MAVEN_TASK__USE_RETURN_CODE:
			return getUseReturnCode();
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
		case BambooPackage.MAVEN_TASK__EXECUTABLE:
			setExecutable((String) newValue);
			return;
		case BambooPackage.MAVEN_TASK__JDK:
			setJdk((String) newValue);
			return;
		case BambooPackage.MAVEN_TASK__GOAL:
			setGoal((String) newValue);
			return;
		case BambooPackage.MAVEN_TASK__TESTS:
			setTests((String) newValue);
			return;
		case BambooPackage.MAVEN_TASK__ENVIRONMENT:
			setEnvironment((String) newValue);
			return;
		case BambooPackage.MAVEN_TASK__WORKING_DIR:
			setWorkingDir((String) newValue);
			return;
		case BambooPackage.MAVEN_TASK__PROJECT_FILE:
			setProjectFile((String) newValue);
			return;
		case BambooPackage.MAVEN_TASK__USE_RETURN_CODE:
			setUseReturnCode((Boolean) newValue);
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
		case BambooPackage.MAVEN_TASK__EXECUTABLE:
			setExecutable(EXECUTABLE_EDEFAULT);
			return;
		case BambooPackage.MAVEN_TASK__JDK:
			setJdk(JDK_EDEFAULT);
			return;
		case BambooPackage.MAVEN_TASK__GOAL:
			setGoal(GOAL_EDEFAULT);
			return;
		case BambooPackage.MAVEN_TASK__TESTS:
			setTests(TESTS_EDEFAULT);
			return;
		case BambooPackage.MAVEN_TASK__ENVIRONMENT:
			setEnvironment(ENVIRONMENT_EDEFAULT);
			return;
		case BambooPackage.MAVEN_TASK__WORKING_DIR:
			setWorkingDir(WORKING_DIR_EDEFAULT);
			return;
		case BambooPackage.MAVEN_TASK__PROJECT_FILE:
			setProjectFile(PROJECT_FILE_EDEFAULT);
			return;
		case BambooPackage.MAVEN_TASK__USE_RETURN_CODE:
			setUseReturnCode(USE_RETURN_CODE_EDEFAULT);
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
		case BambooPackage.MAVEN_TASK__EXECUTABLE:
			return EXECUTABLE_EDEFAULT == null ? executable != null : !EXECUTABLE_EDEFAULT.equals(executable);
		case BambooPackage.MAVEN_TASK__JDK:
			return JDK_EDEFAULT == null ? jdk != null : !JDK_EDEFAULT.equals(jdk);
		case BambooPackage.MAVEN_TASK__GOAL:
			return GOAL_EDEFAULT == null ? goal != null : !GOAL_EDEFAULT.equals(goal);
		case BambooPackage.MAVEN_TASK__TESTS:
			return TESTS_EDEFAULT == null ? tests != null : !TESTS_EDEFAULT.equals(tests);
		case BambooPackage.MAVEN_TASK__ENVIRONMENT:
			return ENVIRONMENT_EDEFAULT == null ? environment != null : !ENVIRONMENT_EDEFAULT.equals(environment);
		case BambooPackage.MAVEN_TASK__WORKING_DIR:
			return WORKING_DIR_EDEFAULT == null ? workingDir != null : !WORKING_DIR_EDEFAULT.equals(workingDir);
		case BambooPackage.MAVEN_TASK__PROJECT_FILE:
			return PROJECT_FILE_EDEFAULT == null ? projectFile != null : !PROJECT_FILE_EDEFAULT.equals(projectFile);
		case BambooPackage.MAVEN_TASK__USE_RETURN_CODE:
			return USE_RETURN_CODE_EDEFAULT == null ? useReturnCode != null
					: !USE_RETURN_CODE_EDEFAULT.equals(useReturnCode);
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
		result.append(" (executable: ");
		result.append(executable);
		result.append(", jdk: ");
		result.append(jdk);
		result.append(", goal: ");
		result.append(goal);
		result.append(", tests: ");
		result.append(tests);
		result.append(", environment: ");
		result.append(environment);
		result.append(", workingDir: ");
		result.append(workingDir);
		result.append(", projectFile: ");
		result.append(projectFile);
		result.append(", useReturnCode: ");
		result.append(useReturnCode);
		result.append(')');
		return result.toString();
	}

} //MavenTaskImpl
