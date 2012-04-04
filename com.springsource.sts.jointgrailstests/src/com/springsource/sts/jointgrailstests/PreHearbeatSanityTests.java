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

import junit.framework.TestCase;

import com.springsource.sts.grails.core.model.GrailsVersion;
import com.springsource.tests.util.DownloadManager;

/**
 * Run a test that ensures the most recent grails version is the build snapshot
 * 
 * @author Andrew Eisenberg
 * @since 2.9.1
 */
public class PreHearbeatSanityTests extends TestCase {
    private static final String BUILD_VERSION_STRING = "2.0.3";
    private static final String BUILD_VERSION_ZIP_NAME = "grails-" + BUILD_VERSION_STRING + ".zip";
    private static final String GRAILS_URL = "http://hudson.grails.org/view/Grails%202.0.x/job/grails_core_2.0.x/lastSuccessfulBuild/artifact/build/distributions/" + BUILD_VERSION_ZIP_NAME;
    public static final GrailsVersion BUILDSNAPHOT_VERSION = new GrailsVersion(BUILD_VERSION_STRING, GRAILS_URL);
    private static boolean DOWNLOADED = false;
    
    @Override
        protected void setUp() throws Exception {
            super.setUp();
            if (!DOWNLOADED) {
                DOWNLOADED = true;
                // delete the old
                File cacheDir = DownloadManager.getDefault().getCacheDir();
                File oldZip = new File(cacheDir, BUILD_VERSION_ZIP_NAME);
                System.out.println("Deleting old grails snapshot at " + oldZip);
                oldZip.delete();
                // get new snapshot and force it to be the most recent
                System.out.println("Setting new grails version to be from " + GRAILS_URL);
    //                GrailsTest.ensureDefaultGrailsVersion(BUILDSNAPHOT_VERSION);
                GrailsVersion.MOST_RECENT = BUILDSNAPHOT_VERSION;
            }
        }

    public void testIsLatestGrails() {
        assertEquals(BUILDSNAPHOT_VERSION, GrailsVersion.MOST_RECENT);
    }
}