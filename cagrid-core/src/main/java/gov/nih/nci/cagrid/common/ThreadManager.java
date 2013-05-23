package gov.nih.nci.cagrid.common;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella</A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings</A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster</A>
 * @author <A HREF="MAILTO:ervin@bmi.osu.edu">David W. Ervin </A>
 * @created Dec 18, 2003
 * @version $Id: ThreadManager.java,v 1.2 2007-04-05 16:42:59 langella Exp $
 */
public class ThreadManager {

	private static int POOL_SIZE = 20;
	private ThreadPoolExecutor workerPool;


	public ThreadManager() {
		workerPool = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 1L, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
	}


	public void executeInBackground(Runner task) throws InterruptedException {
		workerPool.execute(task);
	}


	public void execute(Runner task) throws InterruptedException {
		CountDownLatch barrier = new CountDownLatch(1);
		task.setSync(barrier);
		workerPool.execute(task);
		barrier.await();
	}


	public void executeGroup(RunnerGroup group) throws InterruptedException {
		CountDownLatch barrier = new CountDownLatch(group.size());

		for (int i = 0; i < group.size(); i++) {
			Runner task = group.get(i);
			task.setSync(barrier);
			workerPool.execute(task);
		}
		barrier.await();

	}


	public void executeGroupInBackground(RunnerGroup group) throws InterruptedException {
		for (int i = 0; i < group.size(); i++) {
			Runner task = group.get(i);
			workerPool.execute(task);
		}
	}

}