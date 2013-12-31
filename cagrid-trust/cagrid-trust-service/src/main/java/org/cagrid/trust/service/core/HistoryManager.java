package org.cagrid.trust.service.core;

import java.io.File;

import org.cagrid.trust.model.DateFilter;
import org.cagrid.trust.model.SyncReport;

public interface HistoryManager {
	public File addReport(SyncReport report) throws Exception;

	public SyncReport getReport(String fileName) throws Exception;

	public SyncReport[] search(DateFilter startDate, DateFilter end) throws Exception;

	public SyncReport[] search(DateFilter startDate, DateFilter end, File histDir) throws Exception;

	public void prune(DateFilter filter) throws Exception;
}
