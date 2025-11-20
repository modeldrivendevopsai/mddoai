/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cache</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Cache#getCacheName <em>Cache Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Cache#getKeys <em>Keys</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Cache#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Cache#getStore <em>Store</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCache()
 * @model
 * @generated
 */
public interface Cache extends NonConditionalStep {
	/**
	 * Returns the value of the '<em><b>Cache Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache Name</em>' containment reference.
	 * @see #setCacheName(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCache_CacheName()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getCacheName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Cache#getCacheName <em>Cache Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache Name</em>' containment reference.
	 * @see #getCacheName()
	 * @generated
	 */
	void setCacheName(Expression value);

	/**
	 * Returns the value of the '<em><b>Keys</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keys</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCache_Keys()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Expression> getKeys();

	/**
	 * Returns the value of the '<em><b>Paths</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paths</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCache_Paths()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Expression> getPaths();

	/**
	 * Returns the value of the '<em><b>Store</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.pim.pimMM.CACHE_MODE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Store</em>' attribute.
	 * @see com.mddoai.metamodel.pim.pimMM.CACHE_MODE
	 * @see #setStore(CACHE_MODE)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCache_Store()
	 * @model required="true"
	 * @generated
	 */
	CACHE_MODE getStore();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Cache#getStore <em>Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Store</em>' attribute.
	 * @see com.mddoai.metamodel.pim.pimMM.CACHE_MODE
	 * @see #getStore()
	 * @generated
	 */
	void setStore(CACHE_MODE value);

} // Cache
