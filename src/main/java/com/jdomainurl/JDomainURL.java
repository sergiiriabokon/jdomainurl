package com.jdomainurl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Retrieves Highest Domain Level
 *
 */
public class JDomainURL {
    private static final String PUBLIC_SUFFIX_LIST_URL = "https://publicsuffix.org/list/public_suffix_list.dat";
    private List<String> _suffixes;

    public JDomainURL() {
        this._suffixes = fetchSuffixes();
    }

    private List<String> fetchSuffixes() {
        ArrayList<String> suffixList = new ArrayList<String>();

        try (BufferedInputStream bf = new BufferedInputStream(new URL(PUBLIC_SUFFIX_LIST_URL).openStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));) {

            String line;
            while ((line = r.readLine()) != null) {
                String trimmedLine = line.trim();
                // ommiting comments and empty lines
                if (trimmedLine.indexOf("//") == -1 && trimmedLine.length() > 0) {
                    suffixList.add("." + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return suffixList;
    }

    public static long countDomainLevels(String url) {
        return url.chars().filter(ch -> ch == '.').count();
    }

    public String foundSuffix(String targetUrl) {
        long maxlevels = 0;
        String foundSuffix = "";

        for (String suffix : this._suffixes) {
            if (targetUrl.indexOf(suffix) != -1 
                && targetUrl.length() == targetUrl.indexOf(suffix) + suffix.length()) {
                long domainLevels = countDomainLevels(suffix);

                if (domainLevels > maxlevels
                        || (domainLevels == maxlevels && suffix.length() > foundSuffix.length())) {
                    maxlevels = domainLevels;
                    foundSuffix = suffix;
                }
            }
        }
        return foundSuffix;
    }

    private String cleanUp(String targetUrl) {
        return "." + targetUrl.replace("http://", "")
        .replace("https://","")
        .replaceAll("/","");
    }

    public String getTopLevelDomain(String targetUrl) {
        String cleanTargetUrl = cleanUp(targetUrl);

        String[] levels = cleanTargetUrl.split(Pattern.quote("."));
        if (levels.length == 0 ) {
            return "";
        }
        String suffix = foundSuffix(cleanTargetUrl);
        if (suffix.length() == 0) {
            return "";
        }
        int suffixCount = (int) countDomainLevels(suffix);
        int targetCount = (int) countDomainLevels(cleanTargetUrl);
        
        int index = targetCount - suffixCount;
        String topLevel = index >= 0 ? levels[index] : "";

        return topLevel.length() > 0 ? topLevel + suffix : "";
    }

    public static void main(String[] args) {

        String targetUrl = "http://one.two.orange.tech/";
        
        System.out.println(new JDomainURL().getTopLevelDomain(targetUrl));
    }
}