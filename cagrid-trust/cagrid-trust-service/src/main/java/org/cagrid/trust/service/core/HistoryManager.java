package org.cagrid.trust.service.core;

import gov.nih.nci.cagrid.common.Utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.cagrid.core.xml.XMLUtils;
import org.cagrid.trust.model.DateFilter;
import org.cagrid.trust.model.SyncReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @author <A href="mailto:David.Ervin@osumc.edu">David W. Ervin</A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class HistoryManager {

	public static final int DEFAULT_MAX_REPORTS = 150;
	public static final String MAX_REPORTS_PROPERTY = "max.sync.reports";

	private Logger log;

	private File historyDirectory;

	public HistoryManager(File historyDirectory) {
		log = LoggerFactory.getLogger(this.getClass().getName());
		this.historyDirectory = historyDirectory;
		this.historyDirectory.mkdirs();
	}

	public File addReport(SyncReport report) throws Exception {
		File r = getFile(report.getTimeOfSync());
		XMLUtils.toXMLFile(report, r);
		return r;
	}

	public SyncReport getReport(String fileName) throws Exception {
		return (SyncReport) XMLUtils.fromXMLFile(SyncReport.class, new File(fileName));
	}

	public SyncReport getLastReport() throws Exception {
		File dir = getLastDayDir();
		if (dir != null) {
			File last = null;
			if (dir != null && dir.exists()) {
				File[] files = dir.listFiles(new FileFilter() {
					public boolean accept(File pathname) {
						return pathname.getName().endsWith(".xml");
					}
				});
				if (files != null) {
					sortByFilename(files);
					last = files[files.length - 1];
				}
			}
			if (last != null) {
				return getReport(last.getAbsolutePath());
			}
		}
		return null;
	}

	private File getLastDayDir() {
		return getLastDir(getLastMonthDir());
	}

	private File getLastMonthDir() {
		return getLastDir(getLastYearDir());
	}

	private File getLastYearDir() {
		return getLastDir(getHistoryDirectory());
	}

	private File getLastDir(File dir) {
		File last = null;
		if (dir != null && dir.exists()) {
			File[] dirs = dir.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					return pathname.isDirectory();
				}
			});
			if (dirs != null) {
				sortByFilename(dirs);
				last = dirs[dirs.length - 1];
			}
		}
		return last;
	}

	public SyncReport[] search(DateFilter startDate, DateFilter end) throws Exception {
		DateFilter start = new DateFilter();
		start.setDay(startDate.getDay());
		start.setMonth(startDate.getMonth());
		start.setYear(startDate.getYear());
		SyncReport[] reports = new SyncReport[DEFAULT_MAX_REPORTS];
		int checkMax = 0;
		int iterator = 0;
		this.incrementDate(end);
		while (!start.equals(end)) {
			File startDir = getDirectory(start);
			if ((startDir.exists()) && (startDir.isDirectory())) {
				String[] fileList = startDir.list();
				checkMax = checkMax + fileList.length;
				if (checkMax > DEFAULT_MAX_REPORTS)
					throw new Exception();
				else {
					for (int i = 0; i < fileList.length; i++) {
						File inputFile = new File(startDir.getAbsolutePath() + File.separator + fileList[i]);
						FileReader in = new FileReader(inputFile);
						if (in.read() != -1) {
							reports[iterator] = this.getReport(startDir.getAbsolutePath() + File.separator + fileList[i]);
							iterator++;
						} else {
							in.close();
							throw new Exception();
						}
						in.close();
					}
				}
			}
			this.incrementDate(start);
		}
		SyncReport[] returnReports = new SyncReport[checkMax];
		System.arraycopy(reports, 0, returnReports, 0, returnReports.length);

		return returnReports;
	}

	public SyncReport[] search(DateFilter startDate, DateFilter end, File histDir) throws Exception {
		DateFilter start = new DateFilter();
		start.setDay(startDate.getDay());
		start.setMonth(startDate.getMonth());
		start.setYear(startDate.getYear());
		SyncReport[] reports = new SyncReport[DEFAULT_MAX_REPORTS];
		int checkMax = 0;
		int iterator = 0;
		this.incrementDate(end);
		while (!start.equals(end)) {
			File startDir = getDirectory(start, histDir);
			if ((startDir.exists()) && (startDir.isDirectory())) {
				String[] fileList = startDir.list();
				checkMax = checkMax + fileList.length;
				if (checkMax > DEFAULT_MAX_REPORTS)
					throw new Exception();
				else {
					for (int i = 0; i < fileList.length; i++) {
						File inputFile = new File(startDir.getAbsolutePath() + File.separator + fileList[i]);
						FileReader in = new FileReader(inputFile);
						if (in.read() != -1) {
							reports[iterator] = this.getReport(startDir.getAbsolutePath() + File.separator + fileList[i]);
							iterator++;
						} else {
							in.close();
							throw new Exception();
						}
						in.close();
					}
				}
			}
			this.incrementDate(start);
		}
		SyncReport[] returnReports = new SyncReport[checkMax];
		System.arraycopy(reports, 0, returnReports, 0, returnReports.length);

		return returnReports;
	}

	private File getFile(Date timestamp) {
		File dir = getDirectory(timestamp);
		return new File(dir.getAbsolutePath() + File.separator + timestamp.getTime() + ".xml");
	}

	protected File getHistoryDirectory() {
		return historyDirectory;
	}

	private File getDirectory(DateFilter f) {
		File histDir = getHistoryDirectory();
		return getDirectory(f, histDir);
	}

	private File getDirectory(DateFilter f, File histDir) {
		String month;
		String day;
		if (f.getMonth() < 10) {
			month = "0" + f.getMonth();
		} else {
			month = "" + f.getMonth();
		}

		if (f.getDay() < 10) {
			day = "0" + f.getDay();
		} else {
			day = "" + f.getDay();
		}

		File dir = new File(histDir, f.getYear() + File.separator + month + File.separator + day);
		return dir;
	}

	private File getDirectory(Date timestamp) {
		Calendar c = new GregorianCalendar();
		c.setTime(timestamp);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String strMonth = "";
		if (month < 10) {
			strMonth = strMonth + "0";
		}

		strMonth = strMonth + month;

		int day = c.get(Calendar.DAY_OF_MONTH);

		String dom = "";
		if (day < 10) {
			dom = dom + "0";
		}
		dom = dom + day;

		File histDir = getHistoryDirectory();
		File dir = new File(histDir, year + File.separator + strMonth + File.separator + dom);
		dir.mkdirs();
		return dir;
	}

	private void incrementDate(DateFilter f) {
		Calendar cal = Calendar.getInstance();
		// lenient calendar since the old implementation allowed
		// conditions like "Feb 31st" to exist...
		cal.setLenient(true);
		// set up the current date
		cal.set(Calendar.YEAR, f.getYear());
		cal.set(Calendar.MONTH, f.getMonth() - 1);
		cal.set(Calendar.DAY_OF_MONTH, f.getDay());
		// move forward one day
		cal.roll(Calendar.DAY_OF_MONTH, 1);

		// put the date back into the DateFilter bean
		f.setDay(cal.get(Calendar.DAY_OF_MONTH));
		f.setMonth(cal.get(Calendar.MONTH + 1));
		f.setYear(cal.get(Calendar.YEAR));
	}

	private void sortByFilename(File[] files) {
		Comparator<File> comp = new Comparator<File>() {
			public int compare(File o1, File o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		Arrays.sort(files, comp);
	}

	public void prune(DateFilter filter) throws Exception {

		Calendar c = new GregorianCalendar();
		c.add(Calendar.YEAR, filter.getYear());
		c.add(Calendar.MONTH, filter.getMonth());
		c.add(Calendar.DAY_OF_MONTH, filter.getDay());
		// list all the history files and dirs in date order
		List<File> historyFiles = listHistoryFilesByDate();
		for (File f : historyFiles) {
			if (f.isFile() && f.getName().endsWith(".xml")) {
				String fileName = f.getName();
				int index = fileName.indexOf(".xml");
				String timestamp = fileName.substring(0, index);
				Date fileDate = new Date(Long.valueOf(timestamp));
				if (fileDate.after(c.getTime())) {
					log.debug("Pruning the report " + f.getAbsolutePath() + " the report was created on " + fileDate.toString() + " which is after the cache allowed date of " + c.getTime().toString()
							+ ".");
					File parent = f.getParentFile();
					f.delete();
					deleteIfEmpty(parent);
				}
			}
		}

		// clean up any empty directories
		for (File f : historyFiles) {
			if (f.exists() && f.isDirectory()) {
				List<File> contents = Utils.recursiveListFiles(f, new FileFilter() {
					public boolean accept(File pathname) {
						return true;
					}
				});
				boolean empty = true;
				for (File content : contents) {
					if (content.isFile()) {
						empty = false;
					}
				}
				if (empty) {
					Utils.deleteDir(f);
				}
			}
		}
	}

	private List<File> listHistoryFilesByDate() {
		final File historyDir = getHistoryDirectory();
		List<File> historyFiles = Utils.recursiveListFiles(historyDir, new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".xml") || pathname.isDirectory();
			}
		});
		Comparator<File> comp = new Comparator<File>() {
			public int compare(File o1, File o2) {
				int val = 0;
				try {
					String rel1 = Utils.getRelativePath(historyDir, o1);
					String rel2 = Utils.getRelativePath(historyDir, o2);
					val = rel1.compareTo(rel2);
				} catch (Exception ex) {
					// uhoh
				}
				return val;
			}
		};
		Collections.sort(historyFiles, comp);
		return historyFiles;
	}

	private void deleteIfEmpty(File dir) {
		if (dir.isDirectory()) {
			File files[] = dir.listFiles();
			if ((files == null) || (files.length == 0)) {
				File parent = dir.getParentFile();
				log.debug("Deleting the directory " + dir.getAbsolutePath() + " no more reports exist in the directory.");
				dir.delete();
				deleteIfEmpty(parent);
			}
		}
	}

}
