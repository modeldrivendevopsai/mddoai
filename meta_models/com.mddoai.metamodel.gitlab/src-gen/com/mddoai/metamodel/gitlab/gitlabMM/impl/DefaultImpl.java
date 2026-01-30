/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.AfterScript;
import com.mddoai.metamodel.gitlab.gitlabMM.BeforeScript;
import com.mddoai.metamodel.gitlab.gitlabMM.Cache;
import com.mddoai.metamodel.gitlab.gitlabMM.Default;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Image;
import com.mddoai.metamodel.gitlab.gitlabMM.Retry;
import com.mddoai.metamodel.gitlab.gitlabMM.Service;
import com.mddoai.metamodel.gitlab.gitlabMM.Tags;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Default</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl#getCache <em>Cache</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl#getBeforeScript <em>Before Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl#getAfterScript <em>After Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl#isInterruptible <em>Interruptible</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl#getRetry <em>Retry</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DefaultImpl extends MinimalEObjectImpl.Container implements Default {
	/**
	 * The cached value of the '{@link #getImage() <em>Image</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected Image image;

	/**
	 * The cached value of the '{@link #getServices() <em>Services</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EList<Service> services;

	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected Tags tags;

	/**
	 * The cached value of the '{@link #getCache() <em>Cache</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCache()
	 * @generated
	 * @ordered
	 */
	protected Cache cache;

	/**
	 * The cached value of the '{@link #getBeforeScript() <em>Before Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeforeScript()
	 * @generated
	 * @ordered
	 */
	protected BeforeScript beforeScript;

	/**
	 * The cached value of the '{@link #getAfterScript() <em>After Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfterScript()
	 * @generated
	 * @ordered
	 */
	protected AfterScript afterScript;

	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final String TIMEOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected String timeout = TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #isInterruptible() <em>Interruptible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterruptible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERRUPTIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInterruptible() <em>Interruptible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterruptible()
	 * @generated
	 * @ordered
	 */
	protected boolean interruptible = INTERRUPTIBLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRetry() <em>Retry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRetry()
	 * @generated
	 * @ordered
	 */
	protected Retry retry;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefaultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.DEFAULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Image getImage() {
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImage(Image newImage, NotificationChain msgs) {
		Image oldImage = image;
		image = newImage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.DEFAULT__IMAGE, oldImage, newImage);
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
	public void setImage(Image newImage) {
		if (newImage != image) {
			NotificationChain msgs = null;
			if (image != null)
				msgs = ((InternalEObject) image).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__IMAGE, null, msgs);
			if (newImage != null)
				msgs = ((InternalEObject) newImage).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__IMAGE, null, msgs);
			msgs = basicSetImage(newImage, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.DEFAULT__IMAGE, newImage, newImage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Service> getServices() {
		if (services == null) {
			services = new EObjectContainmentEList<Service>(Service.class, this, GitlabMMPackage.DEFAULT__SERVICES);
		}
		return services;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tags getTags() {
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTags(Tags newTags, NotificationChain msgs) {
		Tags oldTags = tags;
		tags = newTags;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.DEFAULT__TAGS, oldTags, newTags);
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
	public void setTags(Tags newTags) {
		if (newTags != tags) {
			NotificationChain msgs = null;
			if (tags != null)
				msgs = ((InternalEObject) tags).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__TAGS, null, msgs);
			if (newTags != null)
				msgs = ((InternalEObject) newTags).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__TAGS, null, msgs);
			msgs = basicSetTags(newTags, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.DEFAULT__TAGS, newTags, newTags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Cache getCache() {
		return cache;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCache(Cache newCache, NotificationChain msgs) {
		Cache oldCache = cache;
		cache = newCache;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.DEFAULT__CACHE, oldCache, newCache);
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
	public void setCache(Cache newCache) {
		if (newCache != cache) {
			NotificationChain msgs = null;
			if (cache != null)
				msgs = ((InternalEObject) cache).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__CACHE, null, msgs);
			if (newCache != null)
				msgs = ((InternalEObject) newCache).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__CACHE, null, msgs);
			msgs = basicSetCache(newCache, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.DEFAULT__CACHE, newCache, newCache));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BeforeScript getBeforeScript() {
		return beforeScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBeforeScript(BeforeScript newBeforeScript, NotificationChain msgs) {
		BeforeScript oldBeforeScript = beforeScript;
		beforeScript = newBeforeScript;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.DEFAULT__BEFORE_SCRIPT, oldBeforeScript, newBeforeScript);
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
	public void setBeforeScript(BeforeScript newBeforeScript) {
		if (newBeforeScript != beforeScript) {
			NotificationChain msgs = null;
			if (beforeScript != null)
				msgs = ((InternalEObject) beforeScript).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__BEFORE_SCRIPT, null, msgs);
			if (newBeforeScript != null)
				msgs = ((InternalEObject) newBeforeScript).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__BEFORE_SCRIPT, null, msgs);
			msgs = basicSetBeforeScript(newBeforeScript, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.DEFAULT__BEFORE_SCRIPT,
					newBeforeScript, newBeforeScript));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AfterScript getAfterScript() {
		return afterScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAfterScript(AfterScript newAfterScript, NotificationChain msgs) {
		AfterScript oldAfterScript = afterScript;
		afterScript = newAfterScript;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.DEFAULT__AFTER_SCRIPT, oldAfterScript, newAfterScript);
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
	public void setAfterScript(AfterScript newAfterScript) {
		if (newAfterScript != afterScript) {
			NotificationChain msgs = null;
			if (afterScript != null)
				msgs = ((InternalEObject) afterScript).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__AFTER_SCRIPT, null, msgs);
			if (newAfterScript != null)
				msgs = ((InternalEObject) newAfterScript).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__AFTER_SCRIPT, null, msgs);
			msgs = basicSetAfterScript(newAfterScript, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.DEFAULT__AFTER_SCRIPT, newAfterScript,
					newAfterScript));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTimeout() {
		return timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeout(String newTimeout) {
		String oldTimeout = timeout;
		timeout = newTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.DEFAULT__TIMEOUT, oldTimeout,
					timeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInterruptible() {
		return interruptible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterruptible(boolean newInterruptible) {
		boolean oldInterruptible = interruptible;
		interruptible = newInterruptible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.DEFAULT__INTERRUPTIBLE,
					oldInterruptible, interruptible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Retry getRetry() {
		return retry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRetry(Retry newRetry, NotificationChain msgs) {
		Retry oldRetry = retry;
		retry = newRetry;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.DEFAULT__RETRY, oldRetry, newRetry);
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
	public void setRetry(Retry newRetry) {
		if (newRetry != retry) {
			NotificationChain msgs = null;
			if (retry != null)
				msgs = ((InternalEObject) retry).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__RETRY, null, msgs);
			if (newRetry != null)
				msgs = ((InternalEObject) newRetry).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.DEFAULT__RETRY, null, msgs);
			msgs = basicSetRetry(newRetry, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.DEFAULT__RETRY, newRetry, newRetry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.DEFAULT__IMAGE:
			return basicSetImage(null, msgs);
		case GitlabMMPackage.DEFAULT__SERVICES:
			return ((InternalEList<?>) getServices()).basicRemove(otherEnd, msgs);
		case GitlabMMPackage.DEFAULT__TAGS:
			return basicSetTags(null, msgs);
		case GitlabMMPackage.DEFAULT__CACHE:
			return basicSetCache(null, msgs);
		case GitlabMMPackage.DEFAULT__BEFORE_SCRIPT:
			return basicSetBeforeScript(null, msgs);
		case GitlabMMPackage.DEFAULT__AFTER_SCRIPT:
			return basicSetAfterScript(null, msgs);
		case GitlabMMPackage.DEFAULT__RETRY:
			return basicSetRetry(null, msgs);
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
		case GitlabMMPackage.DEFAULT__IMAGE:
			return getImage();
		case GitlabMMPackage.DEFAULT__SERVICES:
			return getServices();
		case GitlabMMPackage.DEFAULT__TAGS:
			return getTags();
		case GitlabMMPackage.DEFAULT__CACHE:
			return getCache();
		case GitlabMMPackage.DEFAULT__BEFORE_SCRIPT:
			return getBeforeScript();
		case GitlabMMPackage.DEFAULT__AFTER_SCRIPT:
			return getAfterScript();
		case GitlabMMPackage.DEFAULT__TIMEOUT:
			return getTimeout();
		case GitlabMMPackage.DEFAULT__INTERRUPTIBLE:
			return isInterruptible();
		case GitlabMMPackage.DEFAULT__RETRY:
			return getRetry();
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
		case GitlabMMPackage.DEFAULT__IMAGE:
			setImage((Image) newValue);
			return;
		case GitlabMMPackage.DEFAULT__SERVICES:
			getServices().clear();
			getServices().addAll((Collection<? extends Service>) newValue);
			return;
		case GitlabMMPackage.DEFAULT__TAGS:
			setTags((Tags) newValue);
			return;
		case GitlabMMPackage.DEFAULT__CACHE:
			setCache((Cache) newValue);
			return;
		case GitlabMMPackage.DEFAULT__BEFORE_SCRIPT:
			setBeforeScript((BeforeScript) newValue);
			return;
		case GitlabMMPackage.DEFAULT__AFTER_SCRIPT:
			setAfterScript((AfterScript) newValue);
			return;
		case GitlabMMPackage.DEFAULT__TIMEOUT:
			setTimeout((String) newValue);
			return;
		case GitlabMMPackage.DEFAULT__INTERRUPTIBLE:
			setInterruptible((Boolean) newValue);
			return;
		case GitlabMMPackage.DEFAULT__RETRY:
			setRetry((Retry) newValue);
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
		case GitlabMMPackage.DEFAULT__IMAGE:
			setImage((Image) null);
			return;
		case GitlabMMPackage.DEFAULT__SERVICES:
			getServices().clear();
			return;
		case GitlabMMPackage.DEFAULT__TAGS:
			setTags((Tags) null);
			return;
		case GitlabMMPackage.DEFAULT__CACHE:
			setCache((Cache) null);
			return;
		case GitlabMMPackage.DEFAULT__BEFORE_SCRIPT:
			setBeforeScript((BeforeScript) null);
			return;
		case GitlabMMPackage.DEFAULT__AFTER_SCRIPT:
			setAfterScript((AfterScript) null);
			return;
		case GitlabMMPackage.DEFAULT__TIMEOUT:
			setTimeout(TIMEOUT_EDEFAULT);
			return;
		case GitlabMMPackage.DEFAULT__INTERRUPTIBLE:
			setInterruptible(INTERRUPTIBLE_EDEFAULT);
			return;
		case GitlabMMPackage.DEFAULT__RETRY:
			setRetry((Retry) null);
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
		case GitlabMMPackage.DEFAULT__IMAGE:
			return image != null;
		case GitlabMMPackage.DEFAULT__SERVICES:
			return services != null && !services.isEmpty();
		case GitlabMMPackage.DEFAULT__TAGS:
			return tags != null;
		case GitlabMMPackage.DEFAULT__CACHE:
			return cache != null;
		case GitlabMMPackage.DEFAULT__BEFORE_SCRIPT:
			return beforeScript != null;
		case GitlabMMPackage.DEFAULT__AFTER_SCRIPT:
			return afterScript != null;
		case GitlabMMPackage.DEFAULT__TIMEOUT:
			return TIMEOUT_EDEFAULT == null ? timeout != null : !TIMEOUT_EDEFAULT.equals(timeout);
		case GitlabMMPackage.DEFAULT__INTERRUPTIBLE:
			return interruptible != INTERRUPTIBLE_EDEFAULT;
		case GitlabMMPackage.DEFAULT__RETRY:
			return retry != null;
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
		result.append(" (timeout: ");
		result.append(timeout);
		result.append(", interruptible: ");
		result.append(interruptible);
		result.append(')');
		return result.toString();
	}

} //DefaultImpl
