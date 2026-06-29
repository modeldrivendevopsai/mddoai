/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.VariableDeclaration;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ContainerImpl#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ContainerImpl#getUsername <em>Username</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ContainerImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ContainerImpl#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ContainerImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ContainerImpl#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ContainerImpl#getOptions <em>Options</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerImpl extends MinimalEObjectImpl.Container
		implements com.mddoai.metamodel.github.githubMM.Container {
	/**
	 * The cached value of the '{@link #getImage() <em>Image</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected Expression image;

	/**
	 * The cached value of the '{@link #getUsername() <em>Username</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsername()
	 * @generated
	 * @ordered
	 */
	protected Expression username;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected Expression password;

	/**
	 * The cached value of the '{@link #getEnvironmentVariables() <em>Environment Variables</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentVariables()
	 * @generated
	 * @ordered
	 */
	protected EMap<VariableDeclaration, Expression> environmentVariables;

	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> ports;

	/**
	 * The cached value of the '{@link #getVolumes() <em>Volumes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolumes()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> volumes;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected Expression options;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getImage() {
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImage(Expression newImage, NotificationChain msgs) {
		Expression oldImage = image;
		image = newImage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.CONTAINER__IMAGE, oldImage, newImage);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImage(Expression newImage) {
		if (newImage != image) {
			NotificationChain msgs = null;
			if (image != null)
				msgs = ((InternalEObject) image).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINER__IMAGE, null, msgs);
			if (newImage != null)
				msgs = ((InternalEObject) newImage).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINER__IMAGE, null, msgs);
			msgs = basicSetImage(newImage, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.CONTAINER__IMAGE, newImage,
					newImage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getUsername() {
		return username;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUsername(Expression newUsername, NotificationChain msgs) {
		Expression oldUsername = username;
		username = newUsername;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.CONTAINER__USERNAME, oldUsername, newUsername);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUsername(Expression newUsername) {
		if (newUsername != username) {
			NotificationChain msgs = null;
			if (username != null)
				msgs = ((InternalEObject) username).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINER__USERNAME, null, msgs);
			if (newUsername != null)
				msgs = ((InternalEObject) newUsername).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINER__USERNAME, null, msgs);
			msgs = basicSetUsername(newUsername, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.CONTAINER__USERNAME, newUsername,
					newUsername));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPassword(Expression newPassword, NotificationChain msgs) {
		Expression oldPassword = password;
		password = newPassword;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.CONTAINER__PASSWORD, oldPassword, newPassword);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPassword(Expression newPassword) {
		if (newPassword != password) {
			NotificationChain msgs = null;
			if (password != null)
				msgs = ((InternalEObject) password).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINER__PASSWORD, null, msgs);
			if (newPassword != null)
				msgs = ((InternalEObject) newPassword).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINER__PASSWORD, null, msgs);
			msgs = basicSetPassword(newPassword, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.CONTAINER__PASSWORD, newPassword,
					newPassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<VariableDeclaration, Expression> getEnvironmentVariables() {
		if (environmentVariables == null) {
			environmentVariables = new EcoreEMap<VariableDeclaration, Expression>(
					GithubMMPackage.Literals.VARIABLE_ASSIGNMENT, VariableAssignmentImpl.class, this,
					GithubMMPackage.CONTAINER__ENVIRONMENT_VARIABLES);
		}
		return environmentVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getPorts() {
		if (ports == null) {
			ports = new EObjectContainmentEList<Expression>(Expression.class, this, GithubMMPackage.CONTAINER__PORTS);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getVolumes() {
		if (volumes == null) {
			volumes = new EObjectContainmentEList<Expression>(Expression.class, this,
					GithubMMPackage.CONTAINER__VOLUMES);
		}
		return volumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getOptions() {
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOptions(Expression newOptions, NotificationChain msgs) {
		Expression oldOptions = options;
		options = newOptions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.CONTAINER__OPTIONS, oldOptions, newOptions);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOptions(Expression newOptions) {
		if (newOptions != options) {
			NotificationChain msgs = null;
			if (options != null)
				msgs = ((InternalEObject) options).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINER__OPTIONS, null, msgs);
			if (newOptions != null)
				msgs = ((InternalEObject) newOptions).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINER__OPTIONS, null, msgs);
			msgs = basicSetOptions(newOptions, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.CONTAINER__OPTIONS, newOptions,
					newOptions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.CONTAINER__IMAGE:
			return basicSetImage(null, msgs);
		case GithubMMPackage.CONTAINER__USERNAME:
			return basicSetUsername(null, msgs);
		case GithubMMPackage.CONTAINER__PASSWORD:
			return basicSetPassword(null, msgs);
		case GithubMMPackage.CONTAINER__ENVIRONMENT_VARIABLES:
			return ((InternalEList<?>) getEnvironmentVariables()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.CONTAINER__PORTS:
			return ((InternalEList<?>) getPorts()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.CONTAINER__VOLUMES:
			return ((InternalEList<?>) getVolumes()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.CONTAINER__OPTIONS:
			return basicSetOptions(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GithubMMPackage.CONTAINER__IMAGE:
			return getImage();
		case GithubMMPackage.CONTAINER__USERNAME:
			return getUsername();
		case GithubMMPackage.CONTAINER__PASSWORD:
			return getPassword();
		case GithubMMPackage.CONTAINER__ENVIRONMENT_VARIABLES:
			if (coreType)
				return getEnvironmentVariables();
			else
				return getEnvironmentVariables().map();
		case GithubMMPackage.CONTAINER__PORTS:
			return getPorts();
		case GithubMMPackage.CONTAINER__VOLUMES:
			return getVolumes();
		case GithubMMPackage.CONTAINER__OPTIONS:
			return getOptions();
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
		case GithubMMPackage.CONTAINER__IMAGE:
			setImage((Expression) newValue);
			return;
		case GithubMMPackage.CONTAINER__USERNAME:
			setUsername((Expression) newValue);
			return;
		case GithubMMPackage.CONTAINER__PASSWORD:
			setPassword((Expression) newValue);
			return;
		case GithubMMPackage.CONTAINER__ENVIRONMENT_VARIABLES:
			((EStructuralFeature.Setting) getEnvironmentVariables()).set(newValue);
			return;
		case GithubMMPackage.CONTAINER__PORTS:
			getPorts().clear();
			getPorts().addAll((Collection<? extends Expression>) newValue);
			return;
		case GithubMMPackage.CONTAINER__VOLUMES:
			getVolumes().clear();
			getVolumes().addAll((Collection<? extends Expression>) newValue);
			return;
		case GithubMMPackage.CONTAINER__OPTIONS:
			setOptions((Expression) newValue);
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
		case GithubMMPackage.CONTAINER__IMAGE:
			setImage((Expression) null);
			return;
		case GithubMMPackage.CONTAINER__USERNAME:
			setUsername((Expression) null);
			return;
		case GithubMMPackage.CONTAINER__PASSWORD:
			setPassword((Expression) null);
			return;
		case GithubMMPackage.CONTAINER__ENVIRONMENT_VARIABLES:
			getEnvironmentVariables().clear();
			return;
		case GithubMMPackage.CONTAINER__PORTS:
			getPorts().clear();
			return;
		case GithubMMPackage.CONTAINER__VOLUMES:
			getVolumes().clear();
			return;
		case GithubMMPackage.CONTAINER__OPTIONS:
			setOptions((Expression) null);
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
		case GithubMMPackage.CONTAINER__IMAGE:
			return image != null;
		case GithubMMPackage.CONTAINER__USERNAME:
			return username != null;
		case GithubMMPackage.CONTAINER__PASSWORD:
			return password != null;
		case GithubMMPackage.CONTAINER__ENVIRONMENT_VARIABLES:
			return environmentVariables != null && !environmentVariables.isEmpty();
		case GithubMMPackage.CONTAINER__PORTS:
			return ports != null && !ports.isEmpty();
		case GithubMMPackage.CONTAINER__VOLUMES:
			return volumes != null && !volumes.isEmpty();
		case GithubMMPackage.CONTAINER__OPTIONS:
			return options != null;
		}
		return super.eIsSet(featureID);
	}

} //ContainerImpl
