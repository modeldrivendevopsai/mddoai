/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Canary Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl#getIncrements <em>Increments</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl#getPreDeploy <em>Pre Deploy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl#getDeploy <em>Deploy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl#getRouteTraffic <em>Route Traffic</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl#getPostRouteTraffic <em>Post Route Traffic</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl#getOn <em>On</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CanaryStrategyImpl extends MinimalEObjectImpl.Container implements CanaryStrategy {
	/**
	 * The cached value of the '{@link #getIncrements() <em>Increments</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncrements()
	 * @generated
	 * @ordered
	 */
	protected EList<String> increments;

	/**
	 * The cached value of the '{@link #getPreDeploy() <em>Pre Deploy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreDeploy()
	 * @generated
	 * @ordered
	 */
	protected DeployHook preDeploy;

	/**
	 * The cached value of the '{@link #getDeploy() <em>Deploy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeploy()
	 * @generated
	 * @ordered
	 */
	protected DeployHook deploy;

	/**
	 * The cached value of the '{@link #getRouteTraffic() <em>Route Traffic</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRouteTraffic()
	 * @generated
	 * @ordered
	 */
	protected DeployHook routeTraffic;

	/**
	 * The cached value of the '{@link #getPostRouteTraffic() <em>Post Route Traffic</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostRouteTraffic()
	 * @generated
	 * @ordered
	 */
	protected DeployHook postRouteTraffic;

	/**
	 * The cached value of the '{@link #getOn() <em>On</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOn()
	 * @generated
	 * @ordered
	 */
	protected OnSuccessOrFailureHook on;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CanaryStrategyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.CANARY_STRATEGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getIncrements() {
		if (increments == null) {
			increments = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.CANARY_STRATEGY__INCREMENTS);
		}
		return increments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeployHook getPreDeploy() {
		return preDeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPreDeploy(DeployHook newPreDeploy, NotificationChain msgs) {
		DeployHook oldPreDeploy = preDeploy;
		preDeploy = newPreDeploy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CANARY_STRATEGY__PRE_DEPLOY, oldPreDeploy, newPreDeploy);
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
	public void setPreDeploy(DeployHook newPreDeploy) {
		if (newPreDeploy != preDeploy) {
			NotificationChain msgs = null;
			if (preDeploy != null)
				msgs = ((InternalEObject) preDeploy).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__PRE_DEPLOY, null, msgs);
			if (newPreDeploy != null)
				msgs = ((InternalEObject) newPreDeploy).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__PRE_DEPLOY, null, msgs);
			msgs = basicSetPreDeploy(newPreDeploy, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CANARY_STRATEGY__PRE_DEPLOY,
					newPreDeploy, newPreDeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeployHook getDeploy() {
		return deploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeploy(DeployHook newDeploy, NotificationChain msgs) {
		DeployHook oldDeploy = deploy;
		deploy = newDeploy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CANARY_STRATEGY__DEPLOY, oldDeploy, newDeploy);
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
	public void setDeploy(DeployHook newDeploy) {
		if (newDeploy != deploy) {
			NotificationChain msgs = null;
			if (deploy != null)
				msgs = ((InternalEObject) deploy).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__DEPLOY, null, msgs);
			if (newDeploy != null)
				msgs = ((InternalEObject) newDeploy).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__DEPLOY, null, msgs);
			msgs = basicSetDeploy(newDeploy, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CANARY_STRATEGY__DEPLOY,
					newDeploy, newDeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeployHook getRouteTraffic() {
		return routeTraffic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRouteTraffic(DeployHook newRouteTraffic, NotificationChain msgs) {
		DeployHook oldRouteTraffic = routeTraffic;
		routeTraffic = newRouteTraffic;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CANARY_STRATEGY__ROUTE_TRAFFIC, oldRouteTraffic, newRouteTraffic);
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
	public void setRouteTraffic(DeployHook newRouteTraffic) {
		if (newRouteTraffic != routeTraffic) {
			NotificationChain msgs = null;
			if (routeTraffic != null)
				msgs = ((InternalEObject) routeTraffic).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__ROUTE_TRAFFIC, null, msgs);
			if (newRouteTraffic != null)
				msgs = ((InternalEObject) newRouteTraffic).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__ROUTE_TRAFFIC, null, msgs);
			msgs = basicSetRouteTraffic(newRouteTraffic, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CANARY_STRATEGY__ROUTE_TRAFFIC,
					newRouteTraffic, newRouteTraffic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeployHook getPostRouteTraffic() {
		return postRouteTraffic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPostRouteTraffic(DeployHook newPostRouteTraffic, NotificationChain msgs) {
		DeployHook oldPostRouteTraffic = postRouteTraffic;
		postRouteTraffic = newPostRouteTraffic;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CANARY_STRATEGY__POST_ROUTE_TRAFFIC, oldPostRouteTraffic, newPostRouteTraffic);
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
	public void setPostRouteTraffic(DeployHook newPostRouteTraffic) {
		if (newPostRouteTraffic != postRouteTraffic) {
			NotificationChain msgs = null;
			if (postRouteTraffic != null)
				msgs = ((InternalEObject) postRouteTraffic).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__POST_ROUTE_TRAFFIC, null, msgs);
			if (newPostRouteTraffic != null)
				msgs = ((InternalEObject) newPostRouteTraffic).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__POST_ROUTE_TRAFFIC, null, msgs);
			msgs = basicSetPostRouteTraffic(newPostRouteTraffic, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CANARY_STRATEGY__POST_ROUTE_TRAFFIC, newPostRouteTraffic,
					newPostRouteTraffic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OnSuccessOrFailureHook getOn() {
		return on;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOn(OnSuccessOrFailureHook newOn, NotificationChain msgs) {
		OnSuccessOrFailureHook oldOn = on;
		on = newOn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CANARY_STRATEGY__ON, oldOn, newOn);
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
	public void setOn(OnSuccessOrFailureHook newOn) {
		if (newOn != on) {
			NotificationChain msgs = null;
			if (on != null)
				msgs = ((InternalEObject) on).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__ON, null, msgs);
			if (newOn != null)
				msgs = ((InternalEObject) newOn).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CANARY_STRATEGY__ON, null, msgs);
			msgs = basicSetOn(newOn, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CANARY_STRATEGY__ON, newOn,
					newOn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.CANARY_STRATEGY__PRE_DEPLOY:
			return basicSetPreDeploy(null, msgs);
		case AzuredevopsMMPackage.CANARY_STRATEGY__DEPLOY:
			return basicSetDeploy(null, msgs);
		case AzuredevopsMMPackage.CANARY_STRATEGY__ROUTE_TRAFFIC:
			return basicSetRouteTraffic(null, msgs);
		case AzuredevopsMMPackage.CANARY_STRATEGY__POST_ROUTE_TRAFFIC:
			return basicSetPostRouteTraffic(null, msgs);
		case AzuredevopsMMPackage.CANARY_STRATEGY__ON:
			return basicSetOn(null, msgs);
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
		case AzuredevopsMMPackage.CANARY_STRATEGY__INCREMENTS:
			return getIncrements();
		case AzuredevopsMMPackage.CANARY_STRATEGY__PRE_DEPLOY:
			return getPreDeploy();
		case AzuredevopsMMPackage.CANARY_STRATEGY__DEPLOY:
			return getDeploy();
		case AzuredevopsMMPackage.CANARY_STRATEGY__ROUTE_TRAFFIC:
			return getRouteTraffic();
		case AzuredevopsMMPackage.CANARY_STRATEGY__POST_ROUTE_TRAFFIC:
			return getPostRouteTraffic();
		case AzuredevopsMMPackage.CANARY_STRATEGY__ON:
			return getOn();
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
		case AzuredevopsMMPackage.CANARY_STRATEGY__INCREMENTS:
			getIncrements().clear();
			getIncrements().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__PRE_DEPLOY:
			setPreDeploy((DeployHook) newValue);
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__DEPLOY:
			setDeploy((DeployHook) newValue);
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__ROUTE_TRAFFIC:
			setRouteTraffic((DeployHook) newValue);
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__POST_ROUTE_TRAFFIC:
			setPostRouteTraffic((DeployHook) newValue);
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__ON:
			setOn((OnSuccessOrFailureHook) newValue);
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
		case AzuredevopsMMPackage.CANARY_STRATEGY__INCREMENTS:
			getIncrements().clear();
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__PRE_DEPLOY:
			setPreDeploy((DeployHook) null);
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__DEPLOY:
			setDeploy((DeployHook) null);
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__ROUTE_TRAFFIC:
			setRouteTraffic((DeployHook) null);
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__POST_ROUTE_TRAFFIC:
			setPostRouteTraffic((DeployHook) null);
			return;
		case AzuredevopsMMPackage.CANARY_STRATEGY__ON:
			setOn((OnSuccessOrFailureHook) null);
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
		case AzuredevopsMMPackage.CANARY_STRATEGY__INCREMENTS:
			return increments != null && !increments.isEmpty();
		case AzuredevopsMMPackage.CANARY_STRATEGY__PRE_DEPLOY:
			return preDeploy != null;
		case AzuredevopsMMPackage.CANARY_STRATEGY__DEPLOY:
			return deploy != null;
		case AzuredevopsMMPackage.CANARY_STRATEGY__ROUTE_TRAFFIC:
			return routeTraffic != null;
		case AzuredevopsMMPackage.CANARY_STRATEGY__POST_ROUTE_TRAFFIC:
			return postRouteTraffic != null;
		case AzuredevopsMMPackage.CANARY_STRATEGY__ON:
			return on != null;
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
		result.append(" (increments: ");
		result.append(increments);
		result.append(')');
		return result.toString();
	}

} //CanaryStrategyImpl
