/*
 * Copyright 2011 SpringSource, a division of VMware, Inc
 * 
 * andrew - Initial API and implementation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springsource.sts.jointgrailstests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Properties;

import junit.framework.TestCase;

import com.springsource.sts.grails.core.GrailsCoreActivator;
import com.springsource.sts.grails.core.internal.model.DefaultGrailsInstall;
import com.springsource.sts.grails.core.model.GrailsInstallManager;
import com.springsource.sts.grails.core.model.GrailsVersion;
import com.springsource.sts.grails.core.model.IGrailsInstall;

/**
 * Run a test that ensures the most recent grails version is the build snapshot
 * 
 * @author Andrew Eisenberg
 * @since 2.9.1
 */
public class PreHearbeatSanityTests extends TestCase {
    
    private static String GRAILS_LOCATION = System.getProperty("grails.home");
    
    private static String BUILD_VERSION_STRING;
    private static boolean DOWNLOADED = false;
    public static GrailsVersion BUILDSNAPHOT_VERSION;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (!DOWNLOADED) {
            DOWNLOADED = true;
            configureGrailsVersion();
        }
    }

    public void testIsLatestGrails() {
        assertEquals(BUILD_VERSION_STRING, GrailsVersion.MOST_RECENT.getVersionString());
    }

    private void configureGrailsVersion() throws Exception {
        BUILD_VERSION_STRING = findGrailsVersion();
        BUILDSNAPHOT_VERSION = new GrailsVersion(BUILD_VERSION_STRING);
        GrailsVersion.MOST_RECENT = BUILDSNAPHOT_VERSION;
        System.out.println("Grails version set to: " + GrailsVersion.MOST_RECENT);

        GrailsInstallManager manager = GrailsCoreActivator.getDefault().getInstallManager();
        
        manager.setGrailsInstalls(
                Collections.<IGrailsInstall>singleton(
                        new DefaultGrailsInstall(GRAILS_LOCATION, "Grails " + BUILDSNAPHOT_VERSION, true)));
        System.out.println("Grails default version set to: " + manager.getDefaultGrailsInstall().getVersionString());
    }

    private String findGrailsVersion() throws Exception {
        Properties props = new Properties();
        String pathToProperties = GRAILS_LOCATION;
        if (!pathToProperties.endsWith("/")) {
            pathToProperties += "/";
        }
        pathToProperties += "build.properties";
        
        File propsFile = new File(GRAILS_LOCATION);
        props.load(new FileInputStream(propsFile));
        
        if (!props.containsKey("grails.version")) {
            throw new Exception("no grails version found");
        }
        return props.getProperty("grails.version");
    }
}