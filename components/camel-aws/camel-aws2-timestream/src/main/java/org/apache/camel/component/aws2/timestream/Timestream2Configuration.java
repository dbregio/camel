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
package org.apache.camel.component.aws2.timestream;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.apache.camel.spi.UriPath;
import software.amazon.awssdk.core.Protocol;
import software.amazon.awssdk.services.timestreamquery.TimestreamQueryClient;
import software.amazon.awssdk.services.timestreamwrite.TimestreamWriteClient;

@UriParams
public class Timestream2Configuration implements Cloneable {

    @UriPath(description = "Type of client - write/query")
    @Metadata(required = true)
    private Timestream2ClientType clientType;
    @UriPath(description = "Logical name")
    @Metadata(required = true)
    private String label;
    @UriParam
    @Metadata(required = true)
    private Timestream2Operations operation;
    @UriParam(label = "security", secret = true)
    private String accessKey;
    @UriParam(label = "security", secret = true)
    private String secretKey;
    @UriParam(enums = "ap-south-2,ap-south-1,eu-south-1,eu-south-2,us-gov-east-1,me-central-1,il-central-1,ca-central-1,eu-central-1,us-iso-west-1,eu-central-2,us-west-1,us-west-2,af-south-1,eu-north-1,eu-west-3,eu-west-2,eu-west-1,ap-northeast-3,ap-northeast-2,ap-northeast-1,me-south-1,sa-east-1,ap-east-1,cn-north-1,us-gov-west-1,ap-southeast-1,ap-southeast-2,us-iso-east-1,ap-southeast-3,ap-southeast-4,us-east-1,us-east-2,cn-northwest-1,us-isob-east-1,aws-global,aws-cn-global,aws-us-gov-global,aws-iso-global,aws-iso-b-global")
    private String region;
    @UriParam(label = "proxy", enums = "HTTP,HTTPS", defaultValue = "HTTPS")
    private Protocol proxyProtocol = Protocol.HTTPS;
    @UriParam(label = "proxy")
    private String proxyHost;
    @UriParam(label = "proxy")
    private Integer proxyPort;
    @UriParam(label = "advanced")
    @Metadata(autowired = true)
    private TimestreamWriteClient awsTimestreamWriteClient;
    @UriParam(label = "advanced")
    @Metadata(autowired = true)
    private TimestreamQueryClient awsTimestreamQueryClient;
    @UriParam(defaultValue = "false")
    private boolean pojoRequest;
    @UriParam(defaultValue = "false")
    private boolean trustAllCertificates;
    @UriParam(defaultValue = "false")
    private boolean overrideEndpoint;
    @UriParam
    private String uriEndpointOverride;
    @UriParam(defaultValue = "false")
    private boolean useDefaultCredentialsProvider;
    @UriParam(defaultValue = "false")
    private boolean useProfileCredentialsProvider;
    @UriParam
    private String profileCredentialsName;

    public Timestream2ClientType getClientType() {
        return clientType;
    }

    public TimestreamWriteClient getAwsTimestreamWriteClient() {
        return awsTimestreamWriteClient;
    }

    /**
     * To use a existing configured AwsTimestreamWriteClient as client
     */
    public void setAwsTimestreamWriteClient(TimestreamWriteClient awsTimestreamWriteClient) {
        this.awsTimestreamWriteClient = awsTimestreamWriteClient;
    }

    public TimestreamQueryClient getAwsTimestreamQueryClient() {
        return awsTimestreamQueryClient;
    }

    /**
     * To use a existing configured AwsTimestreamQueryClient as client
     */
    public void setAwsTimestreamQueryClient(TimestreamQueryClient awsTimestreamQueryClient) {
        this.awsTimestreamQueryClient = awsTimestreamQueryClient;
    }

    public String getAccessKey() {
        return accessKey;
    }

    /**
     * Amazon AWS Access Key
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Amazon AWS Secret Key
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    /**
     * The region in which Timestream client needs to work. When using this parameter, the configuration will expect the
     * lowercase name of the region (for example ap-east-1) You'll need to use the name Region.EU_WEST_1.id()
     */
    public void setRegion(String region) {
        this.region = region;
    }

    public Timestream2Operations getOperation() {
        return operation;
    }

    /**
     * The operation to perform. It can be describeEndpoints,createBatchLoadTask,describeBatchLoadTask,
     * resumeBatchLoadTask,listBatchLoadTasks,createDatabase,deleteDatabase,describeDatabase,updateDatabase,
     * listDatabases,createTable,deleteTable,describeTable,updateTable,listTables,writeRecords,
     * createScheduledQuery,deleteScheduledQuery,executeScheduledQuery,updateScheduledQuery,
     * describeScheduledQuery,listScheduledQueries,prepareQuery,query,cancelQuery
     */
    public void setOperation(Timestream2Operations operation) {
        this.operation = operation;
    }

    public Protocol getProxyProtocol() {
        return proxyProtocol;
    }

    /**
     * To define a proxy protocol when instantiating the Timestream client
     */
    public void setProxyProtocol(Protocol proxyProtocol) {
        this.proxyProtocol = proxyProtocol;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * To define a proxy host when instantiating the Timestream client
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    /**
     * To define a proxy port when instantiating the Timestream client
     */
    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public boolean isPojoRequest() {
        return pojoRequest;
    }

    /**
     * If we want to use a POJO request as body or not
     */
    public void setPojoRequest(boolean pojoRequest) {
        this.pojoRequest = pojoRequest;
    }

    public boolean isTrustAllCertificates() {
        return trustAllCertificates;
    }

    /**
     * If we want to trust all certificates in case of overriding the endpoint
     */
    public void setTrustAllCertificates(boolean trustAllCertificates) {
        this.trustAllCertificates = trustAllCertificates;
    }

    public boolean isOverrideEndpoint() {
        return overrideEndpoint;
    }

    /**
     * Set the need for overidding the endpoint. This option needs to be used in combination with uriEndpointOverride
     * option
     */
    public void setOverrideEndpoint(boolean overrideEndpoint) {
        this.overrideEndpoint = overrideEndpoint;
    }

    public String getUriEndpointOverride() {
        return uriEndpointOverride;
    }

    /**
     * Set the overriding uri endpoint. This option needs to be used in combination with overrideEndpoint option
     */
    public void setUriEndpointOverride(String uriEndpointOverride) {
        this.uriEndpointOverride = uriEndpointOverride;
    }

    /**
     * Set whether the Timestream client should expect to load credentials through a default credentials provider or to
     * expect static credentials to be passed in.
     */
    public void setUseDefaultCredentialsProvider(Boolean useDefaultCredentialsProvider) {
        this.useDefaultCredentialsProvider = useDefaultCredentialsProvider;
    }

    public Boolean isUseDefaultCredentialsProvider() {
        return useDefaultCredentialsProvider;
    }

    public boolean isUseProfileCredentialsProvider() {
        return useProfileCredentialsProvider;
    }

    /**
     * Set whether the Timestream client should expect to load credentials through a profile credentials provider.
     */
    public void setUseProfileCredentialsProvider(boolean useProfileCredentialsProvider) {
        this.useProfileCredentialsProvider = useProfileCredentialsProvider;
    }

    public String getProfileCredentialsName() {
        return profileCredentialsName;
    }

    /**
     * If using a profile credentials provider this parameter will set the profile name
     */
    public void setProfileCredentialsName(String profileCredentialsName) {
        this.profileCredentialsName = profileCredentialsName;
    }

    // *************************************************
    //
    // *************************************************

    public Timestream2Configuration copy() {
        try {
            return (Timestream2Configuration) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeCamelException(e);
        }
    }

}
