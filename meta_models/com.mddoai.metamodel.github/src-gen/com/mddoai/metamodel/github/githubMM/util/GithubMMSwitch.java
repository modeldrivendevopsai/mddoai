/**
 */
package com.mddoai.metamodel.github.githubMM.util;

import com.mddoai.metamodel.github.githubMM.AbstractStep;
import com.mddoai.metamodel.github.githubMM.Agent;
import com.mddoai.metamodel.github.githubMM.Always;
import com.mddoai.metamodel.github.githubMM.And;
import com.mddoai.metamodel.github.githubMM.BinaryOp;
import com.mddoai.metamodel.github.githubMM.BooleanLiteral;
import com.mddoai.metamodel.github.githubMM.BuiltInFunctionCall;
import com.mddoai.metamodel.github.githubMM.Cancelled;
import com.mddoai.metamodel.github.githubMM.Command;
import com.mddoai.metamodel.github.githubMM.Comparison;
import com.mddoai.metamodel.github.githubMM.Concat;
import com.mddoai.metamodel.github.githubMM.ConcurrencyGroup;
import com.mddoai.metamodel.github.githubMM.Container;
import com.mddoai.metamodel.github.githubMM.Contains;
import com.mddoai.metamodel.github.githubMM.Defaults;
import com.mddoai.metamodel.github.githubMM.DotOp;
import com.mddoai.metamodel.github.githubMM.DoubleLiteral;
import com.mddoai.metamodel.github.githubMM.EndsWith;
import com.mddoai.metamodel.github.githubMM.Equality;
import com.mddoai.metamodel.github.githubMM.EventTypeTrigger;
import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.Failure;
import com.mddoai.metamodel.github.githubMM.Format;
import com.mddoai.metamodel.github.githubMM.FromJSON;
import com.mddoai.metamodel.github.githubMM.GitHubContext;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.HashFiles;
import com.mddoai.metamodel.github.githubMM.IfStep;
import com.mddoai.metamodel.github.githubMM.Input;
import com.mddoai.metamodel.github.githubMM.InputTrigger;
import com.mddoai.metamodel.github.githubMM.IntegerLiteral;
import com.mddoai.metamodel.github.githubMM.Job;
import com.mddoai.metamodel.github.githubMM.Join;
import com.mddoai.metamodel.github.githubMM.Literal;
import com.mddoai.metamodel.github.githubMM.LogicalOp;
import com.mddoai.metamodel.github.githubMM.Matrix;
import com.mddoai.metamodel.github.githubMM.MatrixAxis;
import com.mddoai.metamodel.github.githubMM.MatrixCombination;
import com.mddoai.metamodel.github.githubMM.Not;
import com.mddoai.metamodel.github.githubMM.Or;
import com.mddoai.metamodel.github.githubMM.Output;
import com.mddoai.metamodel.github.githubMM.PERMISSIONS;
import com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES;
import com.mddoai.metamodel.github.githubMM.Parameter;
import com.mddoai.metamodel.github.githubMM.PullRequestTargetTrigger;
import com.mddoai.metamodel.github.githubMM.PullRequestTrigger;
import com.mddoai.metamodel.github.githubMM.PushTrigger;
import com.mddoai.metamodel.github.githubMM.ScheduleTrigger;
import com.mddoai.metamodel.github.githubMM.ScriptJob;
import com.mddoai.metamodel.github.githubMM.Secret;
import com.mddoai.metamodel.github.githubMM.SpecifiedBranchesTrigger;
import com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger;
import com.mddoai.metamodel.github.githubMM.StagingEnvironment;
import com.mddoai.metamodel.github.githubMM.StandardEventTrigger;
import com.mddoai.metamodel.github.githubMM.StartsWith;
import com.mddoai.metamodel.github.githubMM.StatusCheck;
import com.mddoai.metamodel.github.githubMM.Step;
import com.mddoai.metamodel.github.githubMM.StringLiteral;
import com.mddoai.metamodel.github.githubMM.Success;
import com.mddoai.metamodel.github.githubMM.ToJSON;
import com.mddoai.metamodel.github.githubMM.Trigger;
import com.mddoai.metamodel.github.githubMM.UnaryOp;
import com.mddoai.metamodel.github.githubMM.Value;
import com.mddoai.metamodel.github.githubMM.VariableDeclaration;
import com.mddoai.metamodel.github.githubMM.VariableReference;
import com.mddoai.metamodel.github.githubMM.Workflow;
import com.mddoai.metamodel.github.githubMM.WorkflowCallJob;
import com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger;
import com.mddoai.metamodel.github.githubMM.WorkflowDispatchTrigger;
import com.mddoai.metamodel.github.githubMM.WorkflowRunTrigger;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage
 * @generated
 */
public class GithubMMSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GithubMMPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GithubMMSwitch() {
		if (modelPackage == null) {
			modelPackage = GithubMMPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case GithubMMPackage.WORKFLOW: {
			Workflow workflow = (Workflow) theEObject;
			T result = caseWorkflow(workflow);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.JOB: {
			Job job = (Job) theEObject;
			T result = caseJob(job);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.SCRIPT_JOB: {
			ScriptJob scriptJob = (ScriptJob) theEObject;
			T result = caseScriptJob(scriptJob);
			if (result == null)
				result = caseJob(scriptJob);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.WORKFLOW_CALL_JOB: {
			WorkflowCallJob workflowCallJob = (WorkflowCallJob) theEObject;
			T result = caseWorkflowCallJob(workflowCallJob);
			if (result == null)
				result = caseJob(workflowCallJob);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.AGENT: {
			Agent agent = (Agent) theEObject;
			T result = caseAgent(agent);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.CONTAINER: {
			Container container = (Container) theEObject;
			T result = caseContainer(container);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.SERVICE: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, Container> service = (Map.Entry<String, Container>) theEObject;
			T result = caseService(service);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.STAGING_ENVIRONMENT: {
			StagingEnvironment stagingEnvironment = (StagingEnvironment) theEObject;
			T result = caseStagingEnvironment(stagingEnvironment);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.TRIGGER: {
			Trigger trigger = (Trigger) theEObject;
			T result = caseTrigger(trigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.EVENT_TYPE_TRIGGER: {
			EventTypeTrigger eventTypeTrigger = (EventTypeTrigger) theEObject;
			T result = caseEventTypeTrigger(eventTypeTrigger);
			if (result == null)
				result = caseTrigger(eventTypeTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.SPECIFIED_BRANCHES_TRIGGER: {
			SpecifiedBranchesTrigger specifiedBranchesTrigger = (SpecifiedBranchesTrigger) theEObject;
			T result = caseSpecifiedBranchesTrigger(specifiedBranchesTrigger);
			if (result == null)
				result = caseTrigger(specifiedBranchesTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER: {
			SpecifiedPathsTrigger specifiedPathsTrigger = (SpecifiedPathsTrigger) theEObject;
			T result = caseSpecifiedPathsTrigger(specifiedPathsTrigger);
			if (result == null)
				result = caseTrigger(specifiedPathsTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.STANDARD_EVENT_TRIGGER: {
			StandardEventTrigger standardEventTrigger = (StandardEventTrigger) theEObject;
			T result = caseStandardEventTrigger(standardEventTrigger);
			if (result == null)
				result = caseEventTypeTrigger(standardEventTrigger);
			if (result == null)
				result = caseTrigger(standardEventTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.INPUT_TRIGGER: {
			InputTrigger inputTrigger = (InputTrigger) theEObject;
			T result = caseInputTrigger(inputTrigger);
			if (result == null)
				result = caseTrigger(inputTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.WORKFLOW_RUN_TRIGGER: {
			WorkflowRunTrigger workflowRunTrigger = (WorkflowRunTrigger) theEObject;
			T result = caseWorkflowRunTrigger(workflowRunTrigger);
			if (result == null)
				result = caseSpecifiedBranchesTrigger(workflowRunTrigger);
			if (result == null)
				result = caseTrigger(workflowRunTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.PULL_REQUEST_TRIGGER: {
			PullRequestTrigger pullRequestTrigger = (PullRequestTrigger) theEObject;
			T result = casePullRequestTrigger(pullRequestTrigger);
			if (result == null)
				result = caseEventTypeTrigger(pullRequestTrigger);
			if (result == null)
				result = caseSpecifiedBranchesTrigger(pullRequestTrigger);
			if (result == null)
				result = caseSpecifiedPathsTrigger(pullRequestTrigger);
			if (result == null)
				result = caseTrigger(pullRequestTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.PULL_REQUEST_TARGET_TRIGGER: {
			PullRequestTargetTrigger pullRequestTargetTrigger = (PullRequestTargetTrigger) theEObject;
			T result = casePullRequestTargetTrigger(pullRequestTargetTrigger);
			if (result == null)
				result = casePullRequestTrigger(pullRequestTargetTrigger);
			if (result == null)
				result = caseEventTypeTrigger(pullRequestTargetTrigger);
			if (result == null)
				result = caseSpecifiedBranchesTrigger(pullRequestTargetTrigger);
			if (result == null)
				result = caseSpecifiedPathsTrigger(pullRequestTargetTrigger);
			if (result == null)
				result = caseTrigger(pullRequestTargetTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.PUSH_TRIGGER: {
			PushTrigger pushTrigger = (PushTrigger) theEObject;
			T result = casePushTrigger(pushTrigger);
			if (result == null)
				result = caseSpecifiedBranchesTrigger(pushTrigger);
			if (result == null)
				result = caseSpecifiedPathsTrigger(pushTrigger);
			if (result == null)
				result = caseTrigger(pushTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.SCHEDULE_TRIGGER: {
			ScheduleTrigger scheduleTrigger = (ScheduleTrigger) theEObject;
			T result = caseScheduleTrigger(scheduleTrigger);
			if (result == null)
				result = caseTrigger(scheduleTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.WORKFLOW_CALL_TRIGGER: {
			WorkflowCallTrigger workflowCallTrigger = (WorkflowCallTrigger) theEObject;
			T result = caseWorkflowCallTrigger(workflowCallTrigger);
			if (result == null)
				result = caseInputTrigger(workflowCallTrigger);
			if (result == null)
				result = caseTrigger(workflowCallTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.WORKFLOW_DISPATCH_TRIGGER: {
			WorkflowDispatchTrigger workflowDispatchTrigger = (WorkflowDispatchTrigger) theEObject;
			T result = caseWorkflowDispatchTrigger(workflowDispatchTrigger);
			if (result == null)
				result = caseInputTrigger(workflowDispatchTrigger);
			if (result == null)
				result = caseTrigger(workflowDispatchTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.PERMISSION: {
			@SuppressWarnings("unchecked")
			Map.Entry<PERMISSION_SCOPES, PERMISSIONS> permission = (Map.Entry<PERMISSION_SCOPES, PERMISSIONS>) theEObject;
			T result = casePermission(permission);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.CONCURRENCY_GROUP: {
			ConcurrencyGroup concurrencyGroup = (ConcurrencyGroup) theEObject;
			T result = caseConcurrencyGroup(concurrencyGroup);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.DEFAULTS: {
			Defaults defaults = (Defaults) theEObject;
			T result = caseDefaults(defaults);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.EXPRESSION: {
			Expression expression = (Expression) theEObject;
			T result = caseExpression(expression);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.VARIABLE_DECLARATION: {
			VariableDeclaration variableDeclaration = (VariableDeclaration) theEObject;
			T result = caseVariableDeclaration(variableDeclaration);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.VARIABLE_ASSIGNMENT: {
			@SuppressWarnings("unchecked")
			Map.Entry<VariableDeclaration, Expression> variableAssignment = (Map.Entry<VariableDeclaration, Expression>) theEObject;
			T result = caseVariableAssignment(variableAssignment);
			if (result == null)
				result = caseExpression((Expression) variableAssignment);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.BINARY_OP: {
			BinaryOp binaryOp = (BinaryOp) theEObject;
			T result = caseBinaryOp(binaryOp);
			if (result == null)
				result = caseExpression(binaryOp);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.CONCAT: {
			Concat concat = (Concat) theEObject;
			T result = caseConcat(concat);
			if (result == null)
				result = caseExpression(concat);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.DOT_OP: {
			DotOp dotOp = (DotOp) theEObject;
			T result = caseDotOp(dotOp);
			if (result == null)
				result = caseBinaryOp(dotOp);
			if (result == null)
				result = caseExpression(dotOp);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.EQUALITY: {
			Equality equality = (Equality) theEObject;
			T result = caseEquality(equality);
			if (result == null)
				result = caseBinaryOp(equality);
			if (result == null)
				result = caseExpression(equality);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.COMPARISON: {
			Comparison comparison = (Comparison) theEObject;
			T result = caseComparison(comparison);
			if (result == null)
				result = caseBinaryOp(comparison);
			if (result == null)
				result = caseExpression(comparison);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.LOGICAL_OP: {
			LogicalOp logicalOp = (LogicalOp) theEObject;
			T result = caseLogicalOp(logicalOp);
			if (result == null)
				result = caseBinaryOp(logicalOp);
			if (result == null)
				result = caseExpression(logicalOp);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.OR: {
			Or or = (Or) theEObject;
			T result = caseOr(or);
			if (result == null)
				result = caseLogicalOp(or);
			if (result == null)
				result = caseBinaryOp(or);
			if (result == null)
				result = caseExpression(or);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.AND: {
			And and = (And) theEObject;
			T result = caseAnd(and);
			if (result == null)
				result = caseLogicalOp(and);
			if (result == null)
				result = caseBinaryOp(and);
			if (result == null)
				result = caseExpression(and);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.UNARY_OP: {
			UnaryOp unaryOp = (UnaryOp) theEObject;
			T result = caseUnaryOp(unaryOp);
			if (result == null)
				result = caseExpression(unaryOp);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.NOT: {
			Not not = (Not) theEObject;
			T result = caseNot(not);
			if (result == null)
				result = caseUnaryOp(not);
			if (result == null)
				result = caseExpression(not);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.BUILT_IN_FUNCTION_CALL: {
			BuiltInFunctionCall builtInFunctionCall = (BuiltInFunctionCall) theEObject;
			T result = caseBuiltInFunctionCall(builtInFunctionCall);
			if (result == null)
				result = caseExpression(builtInFunctionCall);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.CONTAINS: {
			Contains contains = (Contains) theEObject;
			T result = caseContains(contains);
			if (result == null)
				result = caseBuiltInFunctionCall(contains);
			if (result == null)
				result = caseExpression(contains);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.STARTS_WITH: {
			StartsWith startsWith = (StartsWith) theEObject;
			T result = caseStartsWith(startsWith);
			if (result == null)
				result = caseBuiltInFunctionCall(startsWith);
			if (result == null)
				result = caseExpression(startsWith);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.ENDS_WITH: {
			EndsWith endsWith = (EndsWith) theEObject;
			T result = caseEndsWith(endsWith);
			if (result == null)
				result = caseBuiltInFunctionCall(endsWith);
			if (result == null)
				result = caseExpression(endsWith);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.FORMAT: {
			Format format = (Format) theEObject;
			T result = caseFormat(format);
			if (result == null)
				result = caseBuiltInFunctionCall(format);
			if (result == null)
				result = caseExpression(format);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.JOIN: {
			Join join = (Join) theEObject;
			T result = caseJoin(join);
			if (result == null)
				result = caseBuiltInFunctionCall(join);
			if (result == null)
				result = caseExpression(join);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.TO_JSON: {
			ToJSON toJSON = (ToJSON) theEObject;
			T result = caseToJSON(toJSON);
			if (result == null)
				result = caseBuiltInFunctionCall(toJSON);
			if (result == null)
				result = caseExpression(toJSON);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.FROM_JSON: {
			FromJSON fromJSON = (FromJSON) theEObject;
			T result = caseFromJSON(fromJSON);
			if (result == null)
				result = caseBuiltInFunctionCall(fromJSON);
			if (result == null)
				result = caseExpression(fromJSON);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.HASH_FILES: {
			HashFiles hashFiles = (HashFiles) theEObject;
			T result = caseHashFiles(hashFiles);
			if (result == null)
				result = caseBuiltInFunctionCall(hashFiles);
			if (result == null)
				result = caseExpression(hashFiles);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.STATUS_CHECK: {
			StatusCheck statusCheck = (StatusCheck) theEObject;
			T result = caseStatusCheck(statusCheck);
			if (result == null)
				result = caseBuiltInFunctionCall(statusCheck);
			if (result == null)
				result = caseExpression(statusCheck);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.ALWAYS: {
			Always always = (Always) theEObject;
			T result = caseAlways(always);
			if (result == null)
				result = caseStatusCheck(always);
			if (result == null)
				result = caseBuiltInFunctionCall(always);
			if (result == null)
				result = caseExpression(always);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.SUCCESS: {
			Success success = (Success) theEObject;
			T result = caseSuccess(success);
			if (result == null)
				result = caseStatusCheck(success);
			if (result == null)
				result = caseBuiltInFunctionCall(success);
			if (result == null)
				result = caseExpression(success);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.CANCELLED: {
			Cancelled cancelled = (Cancelled) theEObject;
			T result = caseCancelled(cancelled);
			if (result == null)
				result = caseStatusCheck(cancelled);
			if (result == null)
				result = caseBuiltInFunctionCall(cancelled);
			if (result == null)
				result = caseExpression(cancelled);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.FAILURE: {
			Failure failure = (Failure) theEObject;
			T result = caseFailure(failure);
			if (result == null)
				result = caseStatusCheck(failure);
			if (result == null)
				result = caseBuiltInFunctionCall(failure);
			if (result == null)
				result = caseExpression(failure);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.VALUE: {
			Value value = (Value) theEObject;
			T result = caseValue(value);
			if (result == null)
				result = caseExpression(value);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.LITERAL: {
			Literal literal = (Literal) theEObject;
			T result = caseLiteral(literal);
			if (result == null)
				result = caseValue(literal);
			if (result == null)
				result = caseExpression(literal);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.STRING_LITERAL: {
			StringLiteral stringLiteral = (StringLiteral) theEObject;
			T result = caseStringLiteral(stringLiteral);
			if (result == null)
				result = caseLiteral(stringLiteral);
			if (result == null)
				result = caseValue(stringLiteral);
			if (result == null)
				result = caseExpression(stringLiteral);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.INTEGER_LITERAL: {
			IntegerLiteral integerLiteral = (IntegerLiteral) theEObject;
			T result = caseIntegerLiteral(integerLiteral);
			if (result == null)
				result = caseLiteral(integerLiteral);
			if (result == null)
				result = caseValue(integerLiteral);
			if (result == null)
				result = caseExpression(integerLiteral);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.DOUBLE_LITERAL: {
			DoubleLiteral doubleLiteral = (DoubleLiteral) theEObject;
			T result = caseDoubleLiteral(doubleLiteral);
			if (result == null)
				result = caseLiteral(doubleLiteral);
			if (result == null)
				result = caseValue(doubleLiteral);
			if (result == null)
				result = caseExpression(doubleLiteral);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.BOOLEAN_LITERAL: {
			BooleanLiteral booleanLiteral = (BooleanLiteral) theEObject;
			T result = caseBooleanLiteral(booleanLiteral);
			if (result == null)
				result = caseLiteral(booleanLiteral);
			if (result == null)
				result = caseValue(booleanLiteral);
			if (result == null)
				result = caseExpression(booleanLiteral);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.VARIABLE_REFERENCE: {
			VariableReference variableReference = (VariableReference) theEObject;
			T result = caseVariableReference(variableReference);
			if (result == null)
				result = caseValue(variableReference);
			if (result == null)
				result = caseExpression(variableReference);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.GIT_HUB_CONTEXT: {
			GitHubContext gitHubContext = (GitHubContext) theEObject;
			T result = caseGitHubContext(gitHubContext);
			if (result == null)
				result = caseValue(gitHubContext);
			if (result == null)
				result = caseExpression(gitHubContext);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.MATRIX: {
			Matrix matrix = (Matrix) theEObject;
			T result = caseMatrix(matrix);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.MATRIX_AXIS: {
			MatrixAxis matrixAxis = (MatrixAxis) theEObject;
			T result = caseMatrixAxis(matrixAxis);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.MATRIX_COMBINATION: {
			MatrixCombination matrixCombination = (MatrixCombination) theEObject;
			T result = caseMatrixCombination(matrixCombination);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.ABSTRACT_STEP: {
			AbstractStep abstractStep = (AbstractStep) theEObject;
			T result = caseAbstractStep(abstractStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.IF_STEP: {
			IfStep ifStep = (IfStep) theEObject;
			T result = caseIfStep(ifStep);
			if (result == null)
				result = caseAbstractStep(ifStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.STEP: {
			Step step = (Step) theEObject;
			T result = caseStep(step);
			if (result == null)
				result = caseAbstractStep(step);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.COMMAND: {
			Command command = (Command) theEObject;
			T result = caseCommand(command);
			if (result == null)
				result = caseStep(command);
			if (result == null)
				result = caseAbstractStep(command);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.PACKAGE: {
			com.mddoai.metamodel.github.githubMM.Package package_ = (com.mddoai.metamodel.github.githubMM.Package) theEObject;
			T result = casePackage(package_);
			if (result == null)
				result = caseStep(package_);
			if (result == null)
				result = caseAbstractStep(package_);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.PARAMETER: {
			Parameter parameter = (Parameter) theEObject;
			T result = caseParameter(parameter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.INPUT: {
			Input input = (Input) theEObject;
			T result = caseInput(input);
			if (result == null)
				result = caseParameter(input);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.SECRET: {
			Secret secret = (Secret) theEObject;
			T result = caseSecret(secret);
			if (result == null)
				result = caseParameter(secret);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GithubMMPackage.OUTPUT: {
			Output output = (Output) theEObject;
			T result = caseOutput(output);
			if (result == null)
				result = caseParameter(output);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workflow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workflow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkflow(Workflow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Job</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Job</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJob(Job object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script Job</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script Job</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScriptJob(ScriptJob object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workflow Call Job</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workflow Call Job</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkflowCallJob(WorkflowCallJob object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAgent(Agent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer(Container object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseService(Map.Entry<String, Container> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Staging Environment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Staging Environment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStagingEnvironment(StagingEnvironment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrigger(Trigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Type Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Type Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventTypeTrigger(EventTypeTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specified Branches Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specified Branches Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecifiedBranchesTrigger(SpecifiedBranchesTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specified Paths Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specified Paths Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecifiedPathsTrigger(SpecifiedPathsTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Standard Event Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Standard Event Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStandardEventTrigger(StandardEventTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputTrigger(InputTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workflow Run Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workflow Run Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkflowRunTrigger(WorkflowRunTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pull Request Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pull Request Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePullRequestTrigger(PullRequestTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pull Request Target Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pull Request Target Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePullRequestTargetTrigger(PullRequestTargetTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Push Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Push Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePushTrigger(PushTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Schedule Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Schedule Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScheduleTrigger(ScheduleTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workflow Call Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workflow Call Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkflowCallTrigger(WorkflowCallTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workflow Dispatch Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workflow Dispatch Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkflowDispatchTrigger(WorkflowDispatchTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Permission</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Permission</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePermission(Map.Entry<PERMISSION_SCOPES, PERMISSIONS> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Concurrency Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Concurrency Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConcurrencyGroup(ConcurrencyGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Defaults</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Defaults</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDefaults(Defaults object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpression(Expression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableDeclaration(VariableDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableAssignment(Map.Entry<VariableDeclaration, Expression> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binary Op</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binary Op</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBinaryOp(BinaryOp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Concat</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Concat</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConcat(Concat object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dot Op</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dot Op</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDotOp(DotOp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Equality</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Equality</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEquality(Equality object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comparison</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comparison</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComparison(Comparison object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Logical Op</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Logical Op</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLogicalOp(LogicalOp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Or</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Or</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOr(Or object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>And</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>And</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnd(And object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unary Op</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unary Op</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnaryOp(UnaryOp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Not</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Not</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNot(Not object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Built In Function Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Built In Function Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuiltInFunctionCall(BuiltInFunctionCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contains</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contains</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContains(Contains object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Starts With</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Starts With</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStartsWith(StartsWith object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ends With</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ends With</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEndsWith(EndsWith object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Format</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Format</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormat(Format object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Join</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Join</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJoin(Join object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>To JSON</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>To JSON</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseToJSON(ToJSON object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>From JSON</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>From JSON</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFromJSON(FromJSON object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hash Files</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hash Files</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHashFiles(HashFiles object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Status Check</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Status Check</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStatusCheck(StatusCheck object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Always</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Always</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlways(Always object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Success</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Success</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSuccess(Success object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cancelled</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cancelled</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCancelled(Cancelled object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Failure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Failure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFailure(Failure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValue(Value object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLiteral(Literal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringLiteral(StringLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerLiteral(IntegerLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDoubleLiteral(DoubleLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanLiteral(BooleanLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableReference(VariableReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Git Hub Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Git Hub Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGitHubContext(GitHubContext object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Matrix</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Matrix</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMatrix(Matrix object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Matrix Axis</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Matrix Axis</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMatrixAxis(MatrixAxis object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Matrix Combination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Matrix Combination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMatrixCombination(MatrixCombination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractStep(AbstractStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>If Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>If Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIfStep(IfStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStep(Step object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Command</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Command</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommand(Command object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(com.mddoai.metamodel.github.githubMM.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInput(Input object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Secret</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Secret</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecret(Secret object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Output</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Output</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutput(Output object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //GithubMMSwitch
