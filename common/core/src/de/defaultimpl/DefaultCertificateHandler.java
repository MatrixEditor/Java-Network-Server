package de.defaultimpl;

import certificate.Certificate;
import certificate.keys.DataKey;
import de.io.CertificateHandler;
import de.io.SocketStream;
import de.io.submission.Payload;
import de.monitoring.LoggerFactory;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DefaultCertificateHandler in io.channel (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class DefaultCertificateHandler extends CertificateHandler<SocketStream> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCertificateHandler.class);

    private Certificate certificate;

    private DataKey dataKey;

    @Override
    public Certificate accept(Certificate certificate) {
        this.certificate = certificate;
        return this.certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public void setDataKey(DataKey dataKey) {
        this.dataKey = dataKey;
    }

    public DataKey getDataKey() {
        return dataKey;
    }

    @Override
    public Restitution act(Payload input, Runnable runnable) {
        // check of acceptable certificate
        return Restitution.CONTINUE;
    }

    @Override
    public Restitution act(Payload input) {
        LOGGER.log(Level.INFO, "CertificateHandler checked");
        return Restitution.CONTINUE;
    }

    @Override
    public void onError() {

    }
}

