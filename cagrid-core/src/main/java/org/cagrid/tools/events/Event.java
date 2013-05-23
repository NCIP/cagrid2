/**
 * Event.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Apr 28, 2006 (12:42:00 EDT) WSDL2Java emitter.
 */

package org.cagrid.tools.events;

public class Event implements java.io.Serializable {
	private long eventId;
	private java.lang.String targetId;
	private java.lang.String reportingPartyId;
	private java.lang.String eventType;
	private long occurredAt;
	private java.lang.String message;

	public Event() {
	}

	public Event(long eventId, java.lang.String eventType,
			java.lang.String message, long occurredAt,
			java.lang.String reportingPartyId, java.lang.String targetId) {
		this.eventId = eventId;
		this.targetId = targetId;
		this.reportingPartyId = reportingPartyId;
		this.eventType = eventType;
		this.occurredAt = occurredAt;
		this.message = message;
	}

	/**
	 * Gets the eventId value for this Event.
	 * 
	 * @return eventId
	 */
	public long getEventId() {
		return eventId;
	}

	/**
	 * Sets the eventId value for this Event.
	 * 
	 * @param eventId
	 */
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	/**
	 * Gets the targetId value for this Event.
	 * 
	 * @return targetId
	 */
	public java.lang.String getTargetId() {
		return targetId;
	}

	/**
	 * Sets the targetId value for this Event.
	 * 
	 * @param targetId
	 */
	public void setTargetId(java.lang.String targetId) {
		this.targetId = targetId;
	}

	/**
	 * Gets the reportingPartyId value for this Event.
	 * 
	 * @return reportingPartyId
	 */
	public java.lang.String getReportingPartyId() {
		return reportingPartyId;
	}

	/**
	 * Sets the reportingPartyId value for this Event.
	 * 
	 * @param reportingPartyId
	 */
	public void setReportingPartyId(java.lang.String reportingPartyId) {
		this.reportingPartyId = reportingPartyId;
	}

	/**
	 * Gets the eventType value for this Event.
	 * 
	 * @return eventType
	 */
	public java.lang.String getEventType() {
		return eventType;
	}

	/**
	 * Sets the eventType value for this Event.
	 * 
	 * @param eventType
	 */
	public void setEventType(java.lang.String eventType) {
		this.eventType = eventType;
	}

	/**
	 * Gets the occurredAt value for this Event.
	 * 
	 * @return occurredAt
	 */
	public long getOccurredAt() {
		return occurredAt;
	}

	/**
	 * Sets the occurredAt value for this Event.
	 * 
	 * @param occurredAt
	 */
	public void setOccurredAt(long occurredAt) {
		this.occurredAt = occurredAt;
	}

	/**
	 * Gets the message value for this Event.
	 * 
	 * @return message
	 */
	public java.lang.String getMessage() {
		return message;
	}

	/**
	 * Sets the message value for this Event.
	 * 
	 * @param message
	 */
	public void setMessage(java.lang.String message) {
		this.message = message;
	}

	public boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Event))
			return false;
		Event other = (Event) obj;
		if (this == obj)
			return true;
		return this.eventId == other.getEventId()
				&& ((this.targetId == null && other.getTargetId() == null) || (this.targetId != null && this.targetId
						.equals(other.getTargetId())))
				&& ((this.reportingPartyId == null && other
						.getReportingPartyId() == null) || (this.reportingPartyId != null && this.reportingPartyId
						.equals(other.getReportingPartyId())))
				&& ((this.eventType == null && other.getEventType() == null) || (this.eventType != null && this.eventType
						.equals(other.getEventType())))
				&& this.occurredAt == other.getOccurredAt()
				&& ((this.message == null && other.getMessage() == null) || (this.message != null && this.message
						.equals(other.getMessage())));
	}

	public  int hashCode() {
		int hashCode = 1;
		hashCode += new Long(getEventId()).hashCode();
		if (getTargetId() != null) {
			hashCode += getTargetId().hashCode();
		}
		if (getReportingPartyId() != null) {
			hashCode += getReportingPartyId().hashCode();
		}
		if (getEventType() != null) {
			hashCode += getEventType().hashCode();
		}
		hashCode += new Long(getOccurredAt()).hashCode();
		if (getMessage() != null) {
			hashCode += getMessage().hashCode();
		}
		return hashCode;
	}
}
