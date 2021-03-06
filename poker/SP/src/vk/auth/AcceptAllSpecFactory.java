package vk.auth;

import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.params.HttpParams;

import java.util.Collection;

/**
 * User:   Evgeny Avsievich
 * E-mail: ray.evg@gmail.com
 * Date:   12/8/11
 * Time:   4:56 PM
 */
public class AcceptAllSpecFactory implements CookieSpecFactory {

    public CookieSpec newInstance(final HttpParams params) {
        if (params != null) {

            String[] patterns = null;
            Collection<?> param = (Collection<?>) params.getParameter(
                    CookieSpecPNames.DATE_PATTERNS);
            if (param != null) {
                patterns = new String[param.size()];
                patterns = param.toArray(patterns);
            }
            boolean singleHeader = params.getBooleanParameter(
                    CookieSpecPNames.SINGLE_COOKIE_HEADER, false);

            return new AcceptAllCookiesSpec(patterns, singleHeader);
        } else {
            return new AcceptAllCookiesSpec();
        }
    }
}