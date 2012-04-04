package com.springsource.sts.jointgrailstests;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class JointTestsActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		JointTestsActivator.context = bundleContext;
		
		// ensure started so provisioning agent service is instantiated
		Platform.getBundle("org.eclipse.equinox.p2.core").start();
		Platform.getBundle("org.eclipse.pde.core").start();
        ResourcesPlugin.getWorkspace().removeSaveParticipant("org.eclipse.pde.core");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		JointTestsActivator.context = null;
	}

}
