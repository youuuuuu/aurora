package com.aurora.util;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

public class HTMLUtil {

    private static final SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance().init();

    public static String filter(String source) {
        source = source.replaceAll("(?!<(img).*?>)<.*?>", "")
                .replaceAll("(onload(.*?)=)", "")
                .replaceAll("(onerror(.*?)=)", "");
        source = deleteHMTLTag(source);
        if (sensitiveWordBs.contains(source)) {
            return sensitiveWordBs.replace(source);
        }
        return source;
    }

    public static String deleteHMTLTag(String source) {
        source = source.replaceAll("&.{2,6}?;", "");
        source = source.replaceAll("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>", "");
        source = source.replaceAll("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>", "");
        return source;
    }

}
