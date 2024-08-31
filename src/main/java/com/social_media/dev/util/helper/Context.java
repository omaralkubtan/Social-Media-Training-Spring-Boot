package com.social_media.dev.util.helper;


import com.social_media.dev.Settings;
import com.social_media.dev.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Context {

    public static User getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() == null) return null;

        if (auth.getPrincipal().equals("anonymousUser")) return null;

        return (User) auth.getPrincipal();
    }

    public static String getCurrentLanguage() {
        var language = LocaleContextHolder.getLocale().getLanguage();

        if (Settings.supportedLanguages.contains(language)) return language;

        return Settings.defaultLanguage;
    }
}
