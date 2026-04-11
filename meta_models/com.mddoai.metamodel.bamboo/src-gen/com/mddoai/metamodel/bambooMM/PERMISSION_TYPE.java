/**
 */
package com.mddoai.metamodel.bambooMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>PERMISSION TYPE</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPERMISSION_TYPE()
 * @model
 * @generated
 */
public enum PERMISSION_TYPE implements Enumerator {
	/**
	 * The '<em><b>View</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VIEW_VALUE
	 * @generated
	 * @ordered
	 */
	VIEW(0, "view", "view"),

	/**
	 * The '<em><b>Edit</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EDIT_VALUE
	 * @generated
	 * @ordered
	 */
	EDIT(1, "edit", "edit"),

	/**
	 * The '<em><b>Build</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BUILD_VALUE
	 * @generated
	 * @ordered
	 */
	BUILD(2, "build", "build"),

	/**
	 * The '<em><b>Clone</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLONE_VALUE
	 * @generated
	 * @ordered
	 */
	CLONE(3, "clone", "clone"),

	/**
	 * The '<em><b>Admin</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ADMIN_VALUE
	 * @generated
	 * @ordered
	 */
	ADMIN(4, "admin", "admin"),

	/**
	 * The '<em><b>Deploy</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOY_VALUE
	 * @generated
	 * @ordered
	 */
	DEPLOY(5, "deploy", "deploy"),

	/**
	 * The '<em><b>View Configuration</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VIEW_CONFIGURATION_VALUE
	 * @generated
	 * @ordered
	 */
	VIEW_CONFIGURATION(6, "viewConfiguration", "view-configuration"),

	/**
	 * The '<em><b>Create Repository</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_REPOSITORY_VALUE
	 * @generated
	 * @ordered
	 */
	CREATE_REPOSITORY(7, "createRepository", "create-repository");

	/**
	 * The '<em><b>View</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VIEW
	 * @model name="view"
	 * @generated
	 * @ordered
	 */
	public static final int VIEW_VALUE = 0;

	/**
	 * The '<em><b>Edit</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EDIT
	 * @model name="edit"
	 * @generated
	 * @ordered
	 */
	public static final int EDIT_VALUE = 1;

	/**
	 * The '<em><b>Build</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BUILD
	 * @model name="build"
	 * @generated
	 * @ordered
	 */
	public static final int BUILD_VALUE = 2;

	/**
	 * The '<em><b>Clone</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLONE
	 * @model name="clone"
	 * @generated
	 * @ordered
	 */
	public static final int CLONE_VALUE = 3;

	/**
	 * The '<em><b>Admin</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ADMIN
	 * @model name="admin"
	 * @generated
	 * @ordered
	 */
	public static final int ADMIN_VALUE = 4;

	/**
	 * The '<em><b>Deploy</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOY
	 * @model name="deploy"
	 * @generated
	 * @ordered
	 */
	public static final int DEPLOY_VALUE = 5;

	/**
	 * The '<em><b>View Configuration</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VIEW_CONFIGURATION
	 * @model name="viewConfiguration" literal="view-configuration"
	 * @generated
	 * @ordered
	 */
	public static final int VIEW_CONFIGURATION_VALUE = 6;

	/**
	 * The '<em><b>Create Repository</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_REPOSITORY
	 * @model name="createRepository" literal="create-repository"
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_REPOSITORY_VALUE = 7;

	/**
	 * An array of all the '<em><b>PERMISSION TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PERMISSION_TYPE[] VALUES_ARRAY = new PERMISSION_TYPE[] { VIEW, EDIT, BUILD, CLONE, ADMIN,
			DEPLOY, VIEW_CONFIGURATION, CREATE_REPOSITORY, };

	/**
	 * A public read-only list of all the '<em><b>PERMISSION TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PERMISSION_TYPE> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>PERMISSION TYPE</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PERMISSION_TYPE get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PERMISSION_TYPE result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PERMISSION TYPE</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PERMISSION_TYPE getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PERMISSION_TYPE result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PERMISSION TYPE</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PERMISSION_TYPE get(int value) {
		switch (value) {
		case VIEW_VALUE:
			return VIEW;
		case EDIT_VALUE:
			return EDIT;
		case BUILD_VALUE:
			return BUILD;
		case CLONE_VALUE:
			return CLONE;
		case ADMIN_VALUE:
			return ADMIN;
		case DEPLOY_VALUE:
			return DEPLOY;
		case VIEW_CONFIGURATION_VALUE:
			return VIEW_CONFIGURATION;
		case CREATE_REPOSITORY_VALUE:
			return CREATE_REPOSITORY;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PERMISSION_TYPE(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
		return value;
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
	public String getLiteral() {
		return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} //PERMISSION_TYPE
