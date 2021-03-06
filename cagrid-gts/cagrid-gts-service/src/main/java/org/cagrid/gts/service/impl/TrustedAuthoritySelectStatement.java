package org.cagrid.gts.service.impl;

import org.cagrid.gts.service.impl.db.TrustedAuthorityTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class TrustedAuthoritySelectStatement {
	private List<String> fields;

	private List<String> whereFields;

	private List<Object> whereValues;

	private List<String> whereOperators;

	private List<String> clauses;


	public TrustedAuthoritySelectStatement() {
		this.fields = new ArrayList<String>();
		this.whereFields = new ArrayList<String>();
		this.whereValues = new ArrayList<Object>();
		this.whereOperators = new ArrayList<String>();
		this.clauses = new ArrayList<String>();
	}


	public void addSelectField(String field) {
		this.fields.add(field);
	}


	public void addWhereField(String field, String operator, Object value) {
		this.whereFields.add(field);
		this.whereOperators.add(operator);
		this.whereValues.add(value);
	}


	public void addClause(String clause) {
		this.clauses.add(clause);
	}


	public PreparedStatement prepareStatement(Connection c) throws Exception {
		StringBuffer sql = new StringBuffer("select ");
		boolean first = true;
		for (int i = 0; i < fields.size(); i++) {
			if (!first) {
				sql.append(",");
			} else {
				first = false;
			}
			sql.append((String) fields.get(i));
		}
		sql.append(" FROM " + TrustedAuthorityTable.TABLE_NAME);

		if ((whereFields.size() > 0) || (clauses.size() > 0)) {
			sql.append(" WHERE ");
		}
		first = true;
		for (int i = 0; i < whereFields.size(); i++) {
			if (!first) {
				sql.append(" AND ");
			} else {
				first = false;
			}
			sql.append((String) whereFields.get(i) + " " + (String) whereOperators.get(i) + " ?");
		}

		for (int i = 0; i < clauses.size(); i++) {
			if (!first) {
				sql.append(" AND");
			} else {
				first = false;
			}
			sql.append((String) clauses.get(i));
		}

		PreparedStatement s = c.prepareStatement(sql.toString());

		for (int i = 0; i < whereValues.size(); i++) {
			Object o = whereValues.get(i);
			int index = i + 1;
			if (o instanceof String) {
				s.setString((index), (String) o);
			} else if (o instanceof Long) {
				s.setLong((index), ((Long) o).longValue());
			} else if (o instanceof Integer) {
				s.setInt((index), ((Integer) o).intValue());
			} else {
				throw new Exception("Unsupported type " + o.getClass().getName());
			}
		}
		// System.out.println(sql.toString());
		return s;
	}

}
