/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Maven Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.MavenTask#getExecutable <em>Executable</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.MavenTask#getJdk <em>Jdk</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.MavenTask#getGoal <em>Goal</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.MavenTask#getTests <em>Tests</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.MavenTask#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.MavenTask#getWorkingDir <em>Working Dir</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.MavenTask#getProjectFile <em>Project File</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.MavenTask#getUseReturnCode <em>Use Return Code</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getMavenTask()
 * @model
 * @generated
 */
public interface MavenTask extends Task {
	/**
	 * Returns the value of the '<em><b>Executable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executable</em>' attribute.
	 * @see #setExecutable(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getMavenTask_Executable()
	 * @model required="true"
	 * @generated
	 */
	String getExecutable();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.MavenTask#getExecutable <em>Executable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executable</em>' attribute.
	 * @see #getExecutable()
	 * @generated
	 */
	void setExecutable(String value);

	/**
	 * Returns the value of the '<em><b>Jdk</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jdk</em>' attribute.
	 * @see #setJdk(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getMavenTask_Jdk()
	 * @model required="true"
	 * @generated
	 */
	String getJdk();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.MavenTask#getJdk <em>Jdk</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jdk</em>' attribute.
	 * @see #getJdk()
	 * @generated
	 */
	void setJdk(String value);

	/**
	 * Returns the value of the '<em><b>Goal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goal</em>' attribute.
	 * @see #setGoal(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getMavenTask_Goal()
	 * @model required="true"
	 * @generated
	 */
	String getGoal();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.MavenTask#getGoal <em>Goal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Goal</em>' attribute.
	 * @see #getGoal()
	 * @generated
	 */
	void setGoal(String value);

	/**
	 * Returns the value of the '<em><b>Tests</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tests</em>' attribute.
	 * @see #setTests(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getMavenTask_Tests()
	 * @model
	 * @generated
	 */
	String getTests();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.MavenTask#getTests <em>Tests</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tests</em>' attribute.
	 * @see #getTests()
	 * @generated
	 */
	void setTests(String value);

	/**
	 * Returns the value of the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment</em>' attribute.
	 * @see #setEnvironment(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getMavenTask_Environment()
	 * @model
	 * @generated
	 */
	String getEnvironment();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.MavenTask#getEnvironment <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment</em>' attribute.
	 * @see #getEnvironment()
	 * @generated
	 */
	void setEnvironment(String value);

	/**
	 * Returns the value of the '<em><b>Working Dir</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Dir</em>' attribute.
	 * @see #setWorkingDir(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getMavenTask_WorkingDir()
	 * @model
	 * @generated
	 */
	String getWorkingDir();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.MavenTask#getWorkingDir <em>Working Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Dir</em>' attribute.
	 * @see #getWorkingDir()
	 * @generated
	 */
	void setWorkingDir(String value);

	/**
	 * Returns the value of the '<em><b>Project File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project File</em>' attribute.
	 * @see #setProjectFile(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getMavenTask_ProjectFile()
	 * @model
	 * @generated
	 */
	String getProjectFile();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.MavenTask#getProjectFile <em>Project File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project File</em>' attribute.
	 * @see #getProjectFile()
	 * @generated
	 */
	void setProjectFile(String value);

	/**
	 * Returns the value of the '<em><b>Use Return Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Return Code</em>' attribute.
	 * @see #setUseReturnCode(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getMavenTask_UseReturnCode()
	 * @model
	 * @generated
	 */
	Boolean getUseReturnCode();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.MavenTask#getUseReturnCode <em>Use Return Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Return Code</em>' attribute.
	 * @see #getUseReturnCode()
	 * @generated
	 */
	void setUseReturnCode(Boolean value);

} // MavenTask
