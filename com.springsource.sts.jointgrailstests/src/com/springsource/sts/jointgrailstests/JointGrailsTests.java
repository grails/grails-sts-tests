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


import junit.framework.Test;
import junit.framework.TestSuite;

import org.grails.ide.eclipse.commands.test.GrailsCommandTest;
import org.grails.ide.eclipse.commands.test.JointGrailsCommandTest;
import org.grails.ide.eclipse.core.GrailsCoreActivator;
import org.grails.ide.eclipse.test.GrailsTestsActivator;
import org.grails.ide.eclipse.test.inferencing.DSLDGrailsInferencingTests;
import org.grails.ide.eclipse.test.inferencing.GrailsInferencingTests;
import org.springsource.ide.eclipse.commons.tests.util.ManagedTestSuite;


/**
 * A test suite for testing the lastest snapshot of STS with the latest grails snapshot
 * @author Andrew Eisenberg
 * @since 3.0.0
 */
public class JointGrailsTests {
    public static Test suite() {
        GrailsTestsActivator.setJointGrailsTest(true);
        GrailsCoreActivator.testMode(true);
        
        
        // create the new test suite
        TestSuite suite = new ManagedTestSuite("Grails-STS joint tests");
        suite.addTestSuite(PreHearbeatSanityTests.class);
//        suite.addTest(AllGrailsTests.suite(true));
        suite.addTestSuite(GrailsCommandTest.class);
        suite.addTestSuite(JointGrailsCommandTest.class);
        suite.addTestSuite(GrailsInferencingTests.class);
        suite.addTestSuite(DSLDGrailsInferencingTests.class);
        suite.addTestSuite(PostHearbeatSanityTests.class);
        // now run the tests
        return suite;
    }
}
