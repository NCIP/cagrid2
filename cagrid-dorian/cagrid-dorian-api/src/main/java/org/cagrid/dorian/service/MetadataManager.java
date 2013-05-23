package org.cagrid.dorian.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.common.Metadata;
import org.cagrid.dorian.types.DorianInternalException;
import org.cagrid.tools.database.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class MetadataManager {

	private final static Logger logger = LoggerFactory
			.getLogger(MetadataManager.class);

	private Database db;

	private boolean dbBuilt = false;

	private String table;

	public MetadataManager(Database db, String table) {
		this.db = db;
		this.table = table;
	}

	public boolean exists(String name) throws DorianInternalException {
		this.buildDatabase();
		Connection c = null;
		boolean exists = false;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select count(*) from "
					+ table + " where name= ?");
			s.setString(1, name);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					exists = true;
				}
			}
			rs.close();
			s.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class,
					"Unexpected Database Error, could not determine if the metadata "
							+ name + " exists.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return exists;
	}

	public synchronized void insert(Metadata metadata)
			throws DorianInternalException {
		this.buildDatabase();
		Connection c = null;
		try {
			if (!exists(metadata.getName())) {
				c = db.getConnection();
				PreparedStatement s = c.prepareStatement("INSERT INTO " + table
						+ " SET NAME= ?, DESCRIPTION= ?, VALUE= ?");
				s.setString(1, metadata.getName());
				s.setString(2, metadata.getDescription());
				s.setString(3, metadata.getValue());
				s.execute();
			} else {
				DorianInternalException fault = FaultHelper
						.createFaultException(
								DorianInternalException.class,
								"Could not insert the metadata "
										+ metadata.getName()
										+ " because it already exists.");
				throw fault;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class,
					"Unexpected Database Error, could insert  metadata!!!");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public synchronized void update(Metadata metadata)
			throws DorianInternalException {
		this.buildDatabase();
		Connection c = null;
		try {
			if (exists(metadata.getName())) {
				c = db.getConnection();
				PreparedStatement s = c.prepareStatement("UPDATE " + table
						+ " SET DESCRIPTION= ?, VALUE= ? WHERE NAME= ?");

				s.setString(1, metadata.getDescription());
				s.setString(2, metadata.getValue());
				s.setString(3, metadata.getName());
				s.execute();
			} else {
				insert(metadata);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class,
					"Unexpected Database Error, could update metadata!!!");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public synchronized void remove(String name) throws DorianInternalException {
		this.buildDatabase();
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("DELETE FROM " + table
					+ " WHERE NAME= ?");
			s.setString(1, name);
			s.execute();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class,
					"Unexpected Database Error, could remove metadata!!!");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public Metadata get(String name) throws DorianInternalException {
		this.buildDatabase();
		Connection c = null;

		String value = null;
		String description = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c
					.prepareStatement("select DESCRIPTION,VALUE from " + table
							+ " where name= ?");
			s.setString(1, name);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				value = rs.getString("VALUE");
				description = rs.getString("DESCRIPTION");
			}
			rs.close();
			s.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class,
					"Unexpected Database Error, obtain the metadata " + name
							+ ".");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}

		if (value == null) {
			return null;
		} else {
			Metadata metadata = new Metadata();
			metadata.setName(name);
			metadata.setValue(value);
			metadata.setDescription(description);
			return metadata;
		}
	}

	public void clearDatabase() throws DorianInternalException {
		this.buildDatabase();
		try {
			db.update("DELETE FROM " + table);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper
					.createFaultException(DorianInternalException.class,
							"Unexpected Database Error.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	private void buildDatabase() throws DorianInternalException {
		try {
			if (!dbBuilt) {
				if (!this.db.tableExists(table)) {
					String applications = "CREATE TABLE " + table + " ("
							+ "NAME VARCHAR(255) NOT NULL PRIMARY KEY,"
							+ "DESCRIPTION TEXT," + "VALUE TEXT NOT NULL,"
							+ "INDEX document_index (NAME));";
					db.update(applications);
				}
				this.dbBuilt = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper
					.createFaultException(DorianInternalException.class,
							"Unexpected Database Error.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}
}