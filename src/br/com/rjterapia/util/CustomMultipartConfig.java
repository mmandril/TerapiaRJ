package br.com.rjterapia.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

import br.com.caelum.vraptor.observer.upload.DefaultMultipartConfig;

@Specializes
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

    // validates sum of all files up to 100MB
    public long getSizeLimit() {
        return 100 * 1024 * 1024;
    }

    // validates each file up to 20MB
    public long getFileSizeLimit() {
        return 20 * 1024 * 1024;
    }
}