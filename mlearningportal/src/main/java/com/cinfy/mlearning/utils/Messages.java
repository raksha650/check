package com.cinfy.mlearning.utils;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Helper to simplify accessing i18n messages in code.
 * 
 * This finds messages automatically found from src/main/resources (files named messages_*.properties)
 * 
 * This example uses hard-coded English locale.
 *
 */
@Component
public class Messages {
	
	
	private ResourceBundleMessageSource resourceBundleMessageSource;

    private static MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
    	resourceBundleMessageSource = new ResourceBundleMessageSource();
    	resourceBundleMessageSource.setBasename("locale/messages");
    	
    	accessor = new MessageSourceAccessor(resourceBundleMessageSource,Locale.US);
    }

    public static String get(String code) {
        return accessor.getMessage(code);
    }

}
