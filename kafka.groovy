/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import Kubernetes

String jobName = "beam_PerformanceTests_Kafka_IO"
String HIGH_RANGE_PORT = "32767"

job(jobName) {
  // TODO(BEAM-9482): Re-enable once fixed.
  // common.setAutoJob(delegate, 'H H/6 * * *')

  String namespace = "test-ns"
  String kubeconfig = ""
  Kubernetes k8s = Kubernetes.create(delegate, kubeconfig, namespace)

  //String kafkaDir = common.makePathAbsolute("src/.test-infra/kubernetes/kafka-cluster")

  // Select available ports for services and avoid collisions
  steps {
    String[] configuredPorts = ["32400", "32401", "32402"]
    (0..1).each { service ->
      k8s.availablePort(service == 0 ? configuredPorts[service]: "\$KAFKA_SERVICE_PORT_${service-1}",
          HIGH_RANGE_PORT, "KAFKA_SERVICE_PORT_0")
      shell("sed -i -e 's/${configuredPorts[service]}/\$KAFKA_SERVICE_PORT_$service/' \
            $WORKSPACE/outside-${service}.yaml")
      shell("cat $WORKSPACE/outside-0.yaml")
    }
  }
  //k8s.apply(kafkaDir)
  //(0..2).each { k8s.loadBalancerIP("outside-$it", "KAFKA_BROKER_$it") }

 
  // We are using a smaller number of records for streaming test since streaming read is much slower
  // than batch read.


}