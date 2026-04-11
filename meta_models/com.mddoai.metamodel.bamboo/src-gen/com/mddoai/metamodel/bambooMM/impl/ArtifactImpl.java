/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.Artifact;
import com.mddoai.metamodel.bambooMM.BambooPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ArtifactImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ArtifactImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ArtifactImpl#getPattern <em>Pattern</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ArtifactImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ArtifactImpl#isShared <em>Shared</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ArtifactImpl#getHttpCompressionOn <em>Http Compression On</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArtifactImpl extends MinimalEObjectImpl.Container implements Artifact {
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
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected String pattern = PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected boolean required = REQUIRED_EDEFAULT;

	/**
	 * The default value of the '{@link #isShared() <em>Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShared()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SHARED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isShared() <em>Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShared()
	 * @generated
	 * @ordered
	 */
	protected boolean shared = SHARED_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpCompressionOn() <em>Http Compression On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpCompressionOn()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean HTTP_COMPRESSION_ON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpCompressionOn() <em>Http Compression On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpCompressionOn()
	 * @generated
	 * @ordered
	 */
	protected Boolean httpCompressionOn = HTTP_COMPRESSION_ON_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArtifactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.ARTIFACT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ARTIFACT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		String oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ARTIFACT__LOCATION, oldLocation,
					location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPattern() {
		return pattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPattern(String newPattern) {
		String oldPattern = pattern;
		pattern = newPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ARTIFACT__PATTERN, oldPattern,
					pattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRequired() {
		return required;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequired(boolean newRequired) {
		boolean oldRequired = required;
		required = newRequired;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ARTIFACT__REQUIRED, oldRequired,
					required));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isShared() {
		return shared;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setShared(boolean newShared) {
		boolean oldShared = shared;
		shared = newShared;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ARTIFACT__SHARED, oldShared, shared));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getHttpCompressionOn() {
		return httpCompressionOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHttpCompressionOn(Boolean newHttpCompressionOn) {
		Boolean oldHttpCompressionOn = httpCompressionOn;
		httpCompressionOn = newHttpCompressionOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ARTIFACT__HTTP_COMPRESSION_ON,
					oldHttpCompressionOn, httpCompressionOn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.ARTIFACT__NAME:
			return getName();
		case BambooPackage.ARTIFACT__LOCATION:
			return getLocation();
		case BambooPackage.ARTIFACT__PATTERN:
			return getPattern();
		case BambooPackage.ARTIFACT__REQUIRED:
			return isRequired();
		case BambooPackage.ARTIFACT__SHARED:
			return isShared();
		case BambooPackage.ARTIFACT__HTTP_COMPRESSION_ON:
			return getHttpCompressionOn();
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
		case BambooPackage.ARTIFACT__NAME:
			setName((String) newValue);
			return;
		case BambooPackage.ARTIFACT__LOCATION:
			setLocation((String) newValue);
			return;
		case BambooPackage.ARTIFACT__PATTERN:
			setPattern((String) newValue);
			return;
		case BambooPackage.ARTIFACT__REQUIRED:
			setRequired((Boolean) newValue);
			return;
		case BambooPackage.ARTIFACT__SHARED:
			setShared((Boolean) newValue);
			return;
		case BambooPackage.ARTIFACT__HTTP_COMPRESSION_ON:
			setHttpCompressionOn((Boolean) newValue);
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
		case BambooPackage.ARTIFACT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case BambooPackage.ARTIFACT__LOCATION:
			setLocation(LOCATION_EDEFAULT);
			return;
		case BambooPackage.ARTIFACT__PATTERN:
			setPattern(PATTERN_EDEFAULT);
			return;
		case BambooPackage.ARTIFACT__REQUIRED:
			setRequired(REQUIRED_EDEFAULT);
			return;
		case BambooPackage.ARTIFACT__SHARED:
			setShared(SHARED_EDEFAULT);
			return;
		case BambooPackage.ARTIFACT__HTTP_COMPRESSION_ON:
			setHttpCompressionOn(HTTP_COMPRESSION_ON_EDEFAULT);
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
		case BambooPackage.ARTIFACT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case BambooPackage.ARTIFACT__LOCATION:
			return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
		case BambooPackage.ARTIFACT__PATTERN:
			return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
		case BambooPackage.ARTIFACT__REQUIRED:
			return required != REQUIRED_EDEFAULT;
		case BambooPackage.ARTIFACT__SHARED:
			return shared != SHARED_EDEFAULT;
		case BambooPackage.ARTIFACT__HTTP_COMPRESSION_ON:
			return HTTP_COMPRESSION_ON_EDEFAULT == null ? httpCompressionOn != null
					: !HTTP_COMPRESSION_ON_EDEFAULT.equals(httpCompressionOn);
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
		result.append(", location: ");
		result.append(location);
		result.append(", pattern: ");
		result.append(pattern);
		result.append(", required: ");
		result.append(required);
		result.append(", shared: ");
		result.append(shared);
		result.append(", httpCompressionOn: ");
		result.append(httpCompressionOn);
		result.append(')');
		return result.toString();
	}

} //ArtifactImpl
