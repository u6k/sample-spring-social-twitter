
package me.u6k.sample.sample_spring_social_twitter.social;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

@Slf4j
public class SimpleSigninAdapter implements SignInAdapter {

    private final RequestCache requestCache;

    @Inject
    public SimpleSigninAdapter(RequestCache requestCache) {
        log.trace("#ctor: start");

        this.requestCache = requestCache;

        log.trace("#ctor: end");
    }

    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        log.trace("#signIn: start: localUserId={}", localUserId);

        SigninUtils.signin(localUserId);
        String result = extractOriginalUrl(request);

        log.trace("#signIn: end: result={}", result);

        return result;
    }

    private String extractOriginalUrl(NativeWebRequest request) {
        HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
        SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
        if (saved == null) {
            return null;
        }
        requestCache.removeRequest(nativeReq, nativeRes);
        removeAutheticationAttributes(nativeReq.getSession(false));
        return saved.getRedirectUrl();
    }

    private void removeAutheticationAttributes(HttpSession session) {
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
