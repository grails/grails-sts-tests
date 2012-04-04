/******************************************************************************************
 * Copyright (c) 2012 SpringSource, a division of VMware, Inc. All rights reserved.
 ******************************************************************************************/
package com.springsource.sts.jointgrailstests;

import java.io.File;
import java.io.IOException;

/**
 * All the prehearbeat tests as well as another test to 
 * ensure that the grails version has been installed
 * @author Andrew Eisenberg
 * @since 2.9.1
 */
public class PostHearbeatSanityTests extends PreHearbeatSanityTests {
    public void testIsValidInstall() throws Exception {
        File file = new File(BUILDSNAPHOT_VERSION.getInstall().getHome());
        checkExists(file, true);
        checkExists(new File(file, "lib"), true);
        checkExists(new File(file, "dist"), true);
        checkExists(new File(file, "bin"), true);
        checkExists(new File(file, "bin/grails"), false);
    }
    private void checkExists(File file, boolean isDirectory) throws IOException {
        assertTrue(file.getCanonicalPath() + " should exist", file.exists());
        if (isDirectory) {
            assertTrue(file.getCanonicalPath() + " should be a directory", file.isDirectory());
        } else {
            assertFalse(file.getCanonicalPath() + " should not be a directory", file.isDirectory());
        }
    }

}
