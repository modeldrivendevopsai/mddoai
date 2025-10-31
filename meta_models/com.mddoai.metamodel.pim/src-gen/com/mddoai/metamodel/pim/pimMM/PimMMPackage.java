/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.pim.pimMM.PimMMFactory
 * @model kind="package"
 * @generated
 */
public interface PimMMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pimMM";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mddoai.com/mddoai/metamodel/pim";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "pimMM";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PimMMPackage eINSTANCE = com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl <em>Pipeline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPipeline()
	 * @generated
	 */
	int PIPELINE = 0;

	/**
	 * The feature id for the '<em><b>Stages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__STAGES = 0;

	/**
	 * The number of structural features of the '<em>Pipeline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Pipeline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.StageImpl <em>Stage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.StageImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getStage()
	 * @generated
	 */
	int STAGE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Tool</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__TOOL = 1;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__SCRIPT = 2;

	/**
	 * The number of structural features of the '<em>Stage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Stage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ScriptImpl <em>Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ScriptImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getScript()
	 * @generated
	 */
	int SCRIPT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__COMMANDS = 1;

	/**
	 * The number of structural features of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CommandsImpl <em>Commands</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.CommandsImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCommands()
	 * @generated
	 */
	int COMMANDS = 3;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMANDS__COMMANDS = 0;

	/**
	 * The number of structural features of the '<em>Commands</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMANDS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Commands</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMANDS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CommandImpl <em>Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.CommandImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCommand()
	 * @generated
	 */
	int COMMAND = 4;

	/**
	 * The feature id for the '<em><b>Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__COMMAND = 0;

	/**
	 * The number of structural features of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Pipeline <em>Pipeline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pipeline</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Pipeline
	 * @generated
	 */
	EClass getPipeline();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Pipeline#getStages <em>Stages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stages</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Pipeline#getStages()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Stages();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Stage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stage</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Stage
	 * @generated
	 */
	EClass getStage();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Stage#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Stage#getName()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Stage#getTool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tool</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Stage#getTool()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_Tool();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Stage#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Stage#getScript()
	 * @see #getStage()
	 * @generated
	 */
	EReference getStage_Script();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Script <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Script
	 * @generated
	 */
	EClass getScript();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Script#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Script#getName()
	 * @see #getScript()
	 * @generated
	 */
	EAttribute getScript_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Script#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Commands</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Script#getCommands()
	 * @see #getScript()
	 * @generated
	 */
	EReference getScript_Commands();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Commands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Commands</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Commands
	 * @generated
	 */
	EClass getCommands();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Commands#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Commands</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Commands#getCommands()
	 * @see #getCommands()
	 * @generated
	 */
	EReference getCommands_Commands();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Command</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Command
	 * @generated
	 */
	EClass getCommand();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Command#getCommand <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Command</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Command#getCommand()
	 * @see #getCommand()
	 * @generated
	 */
	EAttribute getCommand_Command();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PimMMFactory getPimMMFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl <em>Pipeline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPipeline()
		 * @generated
		 */
		EClass PIPELINE = eINSTANCE.getPipeline();

		/**
		 * The meta object literal for the '<em><b>Stages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__STAGES = eINSTANCE.getPipeline_Stages();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.StageImpl <em>Stage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.StageImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getStage()
		 * @generated
		 */
		EClass STAGE = eINSTANCE.getStage();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__NAME = eINSTANCE.getStage_Name();

		/**
		 * The meta object literal for the '<em><b>Tool</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__TOOL = eINSTANCE.getStage_Tool();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGE__SCRIPT = eINSTANCE.getStage_Script();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ScriptImpl <em>Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ScriptImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getScript()
		 * @generated
		 */
		EClass SCRIPT = eINSTANCE.getScript();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT__NAME = eINSTANCE.getScript_Name();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCRIPT__COMMANDS = eINSTANCE.getScript_Commands();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CommandsImpl <em>Commands</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.CommandsImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCommands()
		 * @generated
		 */
		EClass COMMANDS = eINSTANCE.getCommands();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMANDS__COMMANDS = eINSTANCE.getCommands_Commands();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CommandImpl <em>Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.CommandImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCommand()
		 * @generated
		 */
		EClass COMMAND = eINSTANCE.getCommand();

		/**
		 * The meta object literal for the '<em><b>Command</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMAND__COMMAND = eINSTANCE.getCommand_Command();

	}

} //PimMMPackage
