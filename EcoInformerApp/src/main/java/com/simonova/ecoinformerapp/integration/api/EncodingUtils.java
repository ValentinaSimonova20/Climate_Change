package com.simonova.ecoinformerapp.integration.api;

import org.apache.tomcat.util.buf.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Utilities to support Swagger encoding formats in Feign.
 */
public final class EncodingUtils {
    /**
     * Private constructor. Do not construct this class.
     */
    private EncodingUtils() {}

    /**
     * URL encode a single query parameter.
     * @param parameter The query parameter to encode. This object will not be
     *                  changed.
     * @return The URL encoded string representation of the parameter. If the
     *         parameter is null, returns null.
     */
    public static String encode(Object parameter) {
        if (parameter == null) {
            return null;
        }
        return URLEncoder.encode(parameter.toString(), StandardCharsets.UTF_8);
    }
}
