package com.jdomainurl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class JDomainURLTest 
{
    /**
     * Two levels suffix test
     */
    @Test
    public void twoLevelsSuffixTest()
    {
        String targetUrl = "test.nohost.me";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "test.nohost.me";
        assertTrue( result.equals(expected) );
    }

    /**
     * Two levels in suffix and one more level test
     */
    @Test
    public void twoLevelsSuffixPlusOneLevelTargetTest()
    {
        String targetUrl = "another.test.nohost.me";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "test.nohost.me";
        assertTrue( result.equals(expected) );
    }

    /**
     * Two levels in suffix test
     */
    @Test
    public void oneLevelTest()
    {
        String targetUrl = "another.test.com";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "test.com";
        assertTrue( result.equals(expected) );
    }

    /**
     * One level suffix and one more level in domain suffix test
     */
    @Test
    public void oneLevelPlusOneLevelTargetTest()
    {
        String targetUrl = "another.test.com";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "test.com";
        assertTrue( result.equals(expected) );
    }

    /**
     * Empty string test
     */
    @Test
    public void emptyString()
    {
        String targetUrl = "";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "";
        assertTrue( result.equals(expected) );
    }

    /**
     * without dots test
     */
    @Test
    public void nodotsString()
    {
        String targetUrl = "testcom";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "";
        assertTrue( result.equals(expected) );
    }

    /**
     * only suffix test
     */
    @Test
    public void onlySuffixTest()
    {
        String targetUrl = ".com";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "";
        assertTrue( result.equals(expected) );
    }

    /**
     * Two levels suffix test
     */
    @Test
    public void onlyTwoSuffixTest()
    {
        String targetUrl = ".com.au";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "";
        assertTrue( result.equals(expected) );
    }

    /**
     * Two levels suffix test
     */
    @Test
    public void endingSuffixTest()
    {
        String targetUrl = "orange.com";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "orange.com";
        assertTrue( result.equals(expected) );
    }

    /**
     * Two levels suffix test
     */
    @Test
    public void httpPrefixTest()
    {
        String targetUrl = "http://one.orange.tech.orange";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "orange.tech.orange";
        assertTrue( result.equals(expected) );
    }

    /**
     * Two levels suffix test
     */
    @Test
    public void httpsPrefixTest()
    {
        String targetUrl = "https://one.orange.tech.orange";
        
        String result = new JDomainURL().getTopLevelDomain(targetUrl);
        String expected = "orange.tech.orange";
        assertTrue( result.equals(expected) );
    }

}
