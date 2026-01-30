/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Push Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PushTrigger#getTagGlobs <em>Tag Globs</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPushTrigger()
 * @model
 * @generated
 */
public interface PushTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Tag Globs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Globs</em>' attribute list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPushTrigger_TagGlobs()
	 * @model
	 * @generated
	 */
	EList<String> getTagGlobs();

} // PushTrigger
