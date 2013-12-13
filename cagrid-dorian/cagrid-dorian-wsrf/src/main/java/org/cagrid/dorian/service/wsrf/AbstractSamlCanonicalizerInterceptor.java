package org.cagrid.dorian.service.wsrf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.binding.soap.interceptor.SoapPreProtocolOutInterceptor;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/*
 * http://cxf.apache.org/docs/interceptors.html
 * http://www.mastertheboss.com/jboss-web-services/apache-cxf-interceptors
 */
public abstract class AbstractSamlCanonicalizerInterceptor extends AbstractPhaseInterceptor<Message> {

    public AbstractSamlCanonicalizerInterceptor(String phase) {
        super(phase);
    }

    private static final Logger LOG = LoggerFactory.getLogger(AbstractSamlCanonicalizerInterceptor.class);

    protected String changeOutboundMessage(String currentEnvelope) {
        return canonicalize(currentEnvelope);
    }

    protected String changeInboundMessage(String currentEnvelope) {
        return canonicalize(currentEnvelope);
    }

    protected String canonicalize(String string) {
        Canonicalizer c;
        try {
            c = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
            return new String(c.canonicalize(string.getBytes()));
        } catch (InvalidCanonicalizerException e) {
            LOG.warn("Skipping canonicalization due to invalid canonicalizer:"
                    + Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS, e);
        } catch (CanonicalizationException e) {
            LOG.warn("Skipping canonicalization due to canonicalization exception:" + e.getMessage(), e);
        } catch (ParserConfigurationException e) {
            LOG.warn("Skipping canonicalization due to parser exception:" + e.getMessage(), e);
        } catch (IOException e) {
            LOG.warn("Skipping canonicalization due to IO exception:" + e.getMessage(), e);
        } catch (SAXException e) {
            LOG.warn("Skipping canonicalization due to Sax exception:" + e.getMessage(), e);
        }
        return null;
    }

    protected abstract boolean doesOutboundMessageApply(Message message);

    protected abstract boolean doesInboundMessageApply(Message message);

    public void handleMessage(Message message) {
        boolean isOutbound = false;
        isOutbound = message == message.getExchange().getOutMessage()
                || message == message.getExchange().getOutFaultMessage();

        if (isOutbound && doesOutboundMessageApply(message)) {
            OutputStream os = message.getContent(OutputStream.class);

            CachedStream cs = new CachedStream();
            message.setContent(OutputStream.class, cs);

            message.getInterceptorChain().doIntercept(message);

            try {
                cs.flush();
                IOUtils.closeQuietly(cs);
                CachedOutputStream csnew = (CachedOutputStream) message.getContent(OutputStream.class);

                String currentEnvelopeMessage = IOUtils.toString(csnew.getInputStream(), "UTF-8");
                csnew.flush();
                IOUtils.closeQuietly(csnew);

                if (LOG.isDebugEnabled()) {
                    LOG.debug("Outbound message: " + currentEnvelopeMessage);
                }

                String res = changeOutboundMessage(currentEnvelopeMessage);
                if (res != null) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Outbound message has been changed: " + res);
                    }
                }
                res = res != null ? res : currentEnvelopeMessage;

                InputStream replaceInStream = IOUtils.toInputStream(res, "UTF-8");

                IOUtils.copy(replaceInStream, os);
                replaceInStream.close();
                IOUtils.closeQuietly(replaceInStream);

                os.flush();
                message.setContent(OutputStream.class, os);
                IOUtils.closeQuietly(os);

            } catch (IOException ioe) {
                LOG.warn("Unable to perform change.", ioe);
                throw new RuntimeException(ioe);
            }
        } else if (doesInboundMessageApply(message)) {
            try {
                InputStream is = message.getContent(InputStream.class);
                String currentEnvelopeMessage = IOUtils.toString(is, "UTF-8");
                IOUtils.closeQuietly(is);

                if (LOG.isDebugEnabled()) {
                    LOG.debug("Inbound message: " + currentEnvelopeMessage);
                }

                String res = changeInboundMessage(currentEnvelopeMessage);
                if (res != null) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Inbound message has been changed: " + res);
                    }
                }
                res = res != null ? res : currentEnvelopeMessage;

                is = IOUtils.toInputStream(res, "UTF-8");
                message.setContent(InputStream.class, is);
                IOUtils.closeQuietly(is);
            } catch (IOException ioe) {
                LOG.warn("Unable to perform change.", ioe);

                throw new RuntimeException(ioe);
            }
        }
    }

    public void handleFault(Message message) {
    }

    private class CachedStream extends CachedOutputStream {
        public CachedStream() {
            super();
        }

        protected void doFlush() throws IOException {
            currentStream.flush();
        }

        protected void doClose() throws IOException {
        }

        protected void onWrite() throws IOException {
        }
    }

}
