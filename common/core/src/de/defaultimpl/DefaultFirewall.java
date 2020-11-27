package de.defaultimpl;

import de.io.Firewall;
import de.io.SocketStream;
import de.io.submission.Payload;
import de.validation.Validator;

import java.util.logging.Level;

/**
 * DefaultFirewall in de.jns.io (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class DefaultFirewall extends Firewall<SocketStream> {

    private String[] blacklist;

    private String[] whitelist;

    @Override
    public boolean check(Payload input) {
        //later
        //ValidationResult result = validator.validate();
        //if (result.isValidate() == 1){
        LOGGER.log(Level.INFO, "Firewall checked");
        return false;
        //}
    }

    @Override
    public Validator validator() {
        return null;
    }

    @Override
    public Restitution act(Payload input, Runnable runnable) {
        if (check(input)) {
            runnable.run();
            return Restitution.CONTINUE;
        } else return Restitution.STOP;
    }

    @Override
    public Restitution act(Payload input) {
        if (check(input)) return Restitution.CONTINUE;
        else return Restitution.STOP;
    }

    @Override
    public void onError() {
        //do sth
    }
}
