/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.language.jq;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

public class JqSimpleTransformVariableTest extends JqTestSupport {

    private static String EXPECTED = """
            {
              "country": "se",
            }""";

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start")
                        .setVariable("place", constant("{ \"name\": \"sweden\", \"iso\": \"se\" }"))
                        .transform().simple("""
                                {
                                  "country": "${jq(variable:place,.iso)}",
                                }""")
                        .to("mock:result");
            }
        };
    }

    @Test
    public void testTransform() throws Exception {
        getMockEndpoint("mock:result").expectedBodiesReceived(EXPECTED);

        template.sendBody("direct:start", "{\"id\": 123, \"age\": 42, \"name\": \"scott\"}");

        MockEndpoint.assertIsSatisfied(context);
    }
}
