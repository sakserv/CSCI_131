package com.shanekumpf.lab1;
import javax.swing.*;
/**
* Provides utilities to execute thread safe AWT events
* in a synchronous or asynchronous manner.
* <p>
* Dynamically loads a singleton {@link java.awt.EventDispatchThread}
* class object for later use in comparison of the class type of the
* currently executing AWT event thread.
*
* @author Johnny
* @author Shane Kumpf
* @version 0.1, 08/23/12
* @since 1.6
*
*/
public class EventThreadUtil {

	protected static Class EVENT_DISPATCH_THREAD_CLASS;

	static {
		try {
			EVENT_DISPATCH_THREAD_CLASS = Class.forName("java.awt.EventDispatchThread");
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	* Returns true if the current thread of execution is
	* type compatible with {@link java.awt.EventDispatchThread}.
	*
	* @return bool		Is the current thread a {@link java.awt.EventDispatchThread} thread
	* @deprecated		Removed in favor of {@link SwingUtilities#isEventDispatchThread}
	*/
	public static final boolean isCurrentThreadEventThread() {
		return EVENT_DISPATCH_THREAD_CLASS.isInstance(Thread.currentThread());
	}

	/**
	* Synchronously execute an AWT event if <code>EVENT_DISPATCH_THREAD_CLASS</code>
	* is the current {@link java.awt.EventDispatchThread} executing.
	*
	* @param runnable 		the {@link Runnable} to be executed synchronously
	* @throws InterruptedException 		thread interrupted, swallowed
	* @throws InvocationTargetException		wraps {@link InterruptedException}
	*/
	public static final void runNowInEventThread(Runnable runnable) {
		if(runnable == null) {
			return;
		}
		if(SwingUtilities.isEventDispatchThread()) {
			runnable.run();
		} else {
			try {
				SwingUtilities.invokeAndWait(runnable);
			} catch(InterruptedException ie) {
			} catch(java.lang.reflect.InvocationTargetException ite) {}
		}
	}

	/**
	* Asynchronously execute an AWT event if <code>EVENT_DISPATCH_THREAD_CLASS</code>
	* is the current {@link java.awt.EventDispatchThread} executing.
	*
	* @param runnable		the {@link Runnable} to be executed asynchronously
	*/
	public static final void runLaterInEventThread(Runnable runnable) {
		if(runnable == null) {
			return;
		}
		if(SwingUtilities.isEventDispatchThread()) {
			runnable.run();
		} else {
			SwingUtilities.invokeLater(runnable);
		}
	}
}
