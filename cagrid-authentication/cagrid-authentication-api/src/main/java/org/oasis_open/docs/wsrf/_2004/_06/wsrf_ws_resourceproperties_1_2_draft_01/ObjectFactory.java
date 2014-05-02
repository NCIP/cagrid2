
package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UnknownQueryExpressionDialectFault_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "UnknownQueryExpressionDialectFault");
    private final static QName _InvalidQueryExpressionFault_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "InvalidQueryExpressionFault");
    private final static QName _InvalidResourcePropertyQNameFault_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "InvalidResourcePropertyQNameFault");
    private final static QName _Update_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "Update");
    private final static QName _ResourcePropertyValueChangeNotification_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "ResourcePropertyValueChangeNotification");
    private final static QName _SetResourcePropertyRequestFailedFault_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "SetResourcePropertyRequestFailedFault");
    private final static QName _Delete_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "Delete");
    private final static QName _GetResourceProperty_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "GetResourceProperty");
    private final static QName _QueryEvaluationErrorFault_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "QueryEvaluationErrorFault");
    private final static QName _InvalidSetResourcePropertiesRequestContentFault_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "InvalidSetResourcePropertiesRequestContentFault");
    private final static QName _ResourceUnknownFault_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "ResourceUnknownFault");
    private final static QName _QueryExpression_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "QueryExpression");
    private final static QName _Insert_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "Insert");
    private final static QName _UnableToModifyResourcePropertyFault_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "UnableToModifyResourcePropertyFault");
    private final static QName _ResourcePropertyValueChangeNotificationTypeOldValue_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", "OldValue");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResourcePropertyValueChangeNotificationType }
     * 
     */
    public ResourcePropertyValueChangeNotificationType createResourcePropertyValueChangeNotificationType() {
        return new ResourcePropertyValueChangeNotificationType();
    }

    /**
     * Create an instance of {@link GetMultipleResourceProperties }
     * 
     */
    public GetMultipleResourceProperties createGetMultipleResourceProperties() {
        return new GetMultipleResourceProperties();
    }

    /**
     * Create an instance of {@link QueryEvaluationErrorFaultType }
     * 
     */
    public QueryEvaluationErrorFaultType createQueryEvaluationErrorFaultType() {
        return new QueryEvaluationErrorFaultType();
    }

    /**
     * Create an instance of {@link InvalidSetResourcePropertiesRequestContentFaultType }
     * 
     */
    public InvalidSetResourcePropertiesRequestContentFaultType createInvalidSetResourcePropertiesRequestContentFaultType() {
        return new InvalidSetResourcePropertiesRequestContentFaultType();
    }

    /**
     * Create an instance of {@link SetResourcePropertiesResponse }
     * 
     */
    public SetResourcePropertiesResponse createSetResourcePropertiesResponse() {
        return new SetResourcePropertiesResponse();
    }

    /**
     * Create an instance of {@link InvalidResourcePropertyQNameFaultType }
     * 
     */
    public InvalidResourcePropertyQNameFaultType createInvalidResourcePropertyQNameFaultType() {
        return new InvalidResourcePropertyQNameFaultType();
    }

    /**
     * Create an instance of {@link InsertType }
     * 
     */
    public InsertType createInsertType() {
        return new InsertType();
    }

    /**
     * Create an instance of {@link QueryResourcePropertiesResponse }
     * 
     */
    public QueryResourcePropertiesResponse createQueryResourcePropertiesResponse() {
        return new QueryResourcePropertiesResponse();
    }

    /**
     * Create an instance of {@link UpdateType }
     * 
     */
    public UpdateType createUpdateType() {
        return new UpdateType();
    }

    /**
     * Create an instance of {@link GetMultipleResourcePropertiesResponse }
     * 
     */
    public GetMultipleResourcePropertiesResponse createGetMultipleResourcePropertiesResponse() {
        return new GetMultipleResourcePropertiesResponse();
    }

    /**
     * Create an instance of {@link QueryResourceProperties }
     * 
     */
    public QueryResourceProperties createQueryResourceProperties() {
        return new QueryResourceProperties();
    }

    /**
     * Create an instance of {@link QueryExpressionType }
     * 
     */
    public QueryExpressionType createQueryExpressionType() {
        return new QueryExpressionType();
    }

    /**
     * Create an instance of {@link UnableToModifyResourcePropertyFaultType }
     * 
     */
    public UnableToModifyResourcePropertyFaultType createUnableToModifyResourcePropertyFaultType() {
        return new UnableToModifyResourcePropertyFaultType();
    }

    /**
     * Create an instance of {@link UnknownQueryExpressionDialectFaultType }
     * 
     */
    public UnknownQueryExpressionDialectFaultType createUnknownQueryExpressionDialectFaultType() {
        return new UnknownQueryExpressionDialectFaultType();
    }

    /**
     * Create an instance of {@link InvalidQueryExpressionFaultType }
     * 
     */
    public InvalidQueryExpressionFaultType createInvalidQueryExpressionFaultType() {
        return new InvalidQueryExpressionFaultType();
    }

    /**
     * Create an instance of {@link GetResourcePropertyResponse }
     * 
     */
    public GetResourcePropertyResponse createGetResourcePropertyResponse() {
        return new GetResourcePropertyResponse();
    }

    /**
     * Create an instance of {@link SetResourcePropertyRequestFailedFaultType }
     * 
     */
    public SetResourcePropertyRequestFailedFaultType createSetResourcePropertyRequestFailedFaultType() {
        return new SetResourcePropertyRequestFailedFaultType();
    }

    /**
     * Create an instance of {@link ResourceUnknownFaultType }
     * 
     */
    public ResourceUnknownFaultType createResourceUnknownFaultType() {
        return new ResourceUnknownFaultType();
    }

    /**
     * Create an instance of {@link DeleteType }
     * 
     */
    public DeleteType createDeleteType() {
        return new DeleteType();
    }

    /**
     * Create an instance of {@link SetResourceProperties }
     * 
     */
    public SetResourceProperties createSetResourceProperties() {
        return new SetResourceProperties();
    }

    /**
     * Create an instance of {@link ResourcePropertyValueChangeNotificationType.OldValue }
     * 
     */
    public ResourcePropertyValueChangeNotificationType.OldValue createResourcePropertyValueChangeNotificationTypeOldValue() {
        return new ResourcePropertyValueChangeNotificationType.OldValue();
    }

    /**
     * Create an instance of {@link ResourcePropertyValueChangeNotificationType.NewValue }
     * 
     */
    public ResourcePropertyValueChangeNotificationType.NewValue createResourcePropertyValueChangeNotificationTypeNewValue() {
        return new ResourcePropertyValueChangeNotificationType.NewValue();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownQueryExpressionDialectFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "UnknownQueryExpressionDialectFault")
    public JAXBElement<UnknownQueryExpressionDialectFaultType> createUnknownQueryExpressionDialectFault(UnknownQueryExpressionDialectFaultType value) {
        return new JAXBElement<UnknownQueryExpressionDialectFaultType>(_UnknownQueryExpressionDialectFault_QNAME, UnknownQueryExpressionDialectFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidQueryExpressionFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "InvalidQueryExpressionFault")
    public JAXBElement<InvalidQueryExpressionFaultType> createInvalidQueryExpressionFault(InvalidQueryExpressionFaultType value) {
        return new JAXBElement<InvalidQueryExpressionFaultType>(_InvalidQueryExpressionFault_QNAME, InvalidQueryExpressionFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidResourcePropertyQNameFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "InvalidResourcePropertyQNameFault")
    public JAXBElement<InvalidResourcePropertyQNameFaultType> createInvalidResourcePropertyQNameFault(InvalidResourcePropertyQNameFaultType value) {
        return new JAXBElement<InvalidResourcePropertyQNameFaultType>(_InvalidResourcePropertyQNameFault_QNAME, InvalidResourcePropertyQNameFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "Update")
    public JAXBElement<UpdateType> createUpdate(UpdateType value) {
        return new JAXBElement<UpdateType>(_Update_QNAME, UpdateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourcePropertyValueChangeNotificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "ResourcePropertyValueChangeNotification")
    public JAXBElement<ResourcePropertyValueChangeNotificationType> createResourcePropertyValueChangeNotification(ResourcePropertyValueChangeNotificationType value) {
        return new JAXBElement<ResourcePropertyValueChangeNotificationType>(_ResourcePropertyValueChangeNotification_QNAME, ResourcePropertyValueChangeNotificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetResourcePropertyRequestFailedFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "SetResourcePropertyRequestFailedFault")
    public JAXBElement<SetResourcePropertyRequestFailedFaultType> createSetResourcePropertyRequestFailedFault(SetResourcePropertyRequestFailedFaultType value) {
        return new JAXBElement<SetResourcePropertyRequestFailedFaultType>(_SetResourcePropertyRequestFailedFault_QNAME, SetResourcePropertyRequestFailedFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "Delete")
    public JAXBElement<DeleteType> createDelete(DeleteType value) {
        return new JAXBElement<DeleteType>(_Delete_QNAME, DeleteType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "GetResourceProperty")
    public JAXBElement<QName> createGetResourceProperty(QName value) {
        return new JAXBElement<QName>(_GetResourceProperty_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryEvaluationErrorFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "QueryEvaluationErrorFault")
    public JAXBElement<QueryEvaluationErrorFaultType> createQueryEvaluationErrorFault(QueryEvaluationErrorFaultType value) {
        return new JAXBElement<QueryEvaluationErrorFaultType>(_QueryEvaluationErrorFault_QNAME, QueryEvaluationErrorFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidSetResourcePropertiesRequestContentFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "InvalidSetResourcePropertiesRequestContentFault")
    public JAXBElement<InvalidSetResourcePropertiesRequestContentFaultType> createInvalidSetResourcePropertiesRequestContentFault(InvalidSetResourcePropertiesRequestContentFaultType value) {
        return new JAXBElement<InvalidSetResourcePropertiesRequestContentFaultType>(_InvalidSetResourcePropertiesRequestContentFault_QNAME, InvalidSetResourcePropertiesRequestContentFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceUnknownFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "ResourceUnknownFault")
    public JAXBElement<ResourceUnknownFaultType> createResourceUnknownFault(ResourceUnknownFaultType value) {
        return new JAXBElement<ResourceUnknownFaultType>(_ResourceUnknownFault_QNAME, ResourceUnknownFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryExpressionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "QueryExpression")
    public JAXBElement<QueryExpressionType> createQueryExpression(QueryExpressionType value) {
        return new JAXBElement<QueryExpressionType>(_QueryExpression_QNAME, QueryExpressionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "Insert")
    public JAXBElement<InsertType> createInsert(InsertType value) {
        return new JAXBElement<InsertType>(_Insert_QNAME, InsertType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnableToModifyResourcePropertyFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "UnableToModifyResourcePropertyFault")
    public JAXBElement<UnableToModifyResourcePropertyFaultType> createUnableToModifyResourcePropertyFault(UnableToModifyResourcePropertyFaultType value) {
        return new JAXBElement<UnableToModifyResourcePropertyFaultType>(_UnableToModifyResourcePropertyFault_QNAME, UnableToModifyResourcePropertyFaultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourcePropertyValueChangeNotificationType.OldValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", name = "OldValue", scope = ResourcePropertyValueChangeNotificationType.class)
    public JAXBElement<ResourcePropertyValueChangeNotificationType.OldValue> createResourcePropertyValueChangeNotificationTypeOldValue(ResourcePropertyValueChangeNotificationType.OldValue value) {
        return new JAXBElement<ResourcePropertyValueChangeNotificationType.OldValue>(_ResourcePropertyValueChangeNotificationTypeOldValue_QNAME, ResourcePropertyValueChangeNotificationType.OldValue.class, ResourcePropertyValueChangeNotificationType.class, value);
    }

}
