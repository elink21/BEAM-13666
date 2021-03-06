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



// These jobs list details about each beam runner, to clarify what software
// is on each machine.
  def machine = "{it}"
  job("beam_Inventory_${machine}") {
    description("Run inventory on ${machine}")

    // Set common parameters.

    // Sets that this is a cron job.
    //commonJobProperties.setCronJob(delegate, '45 6,18 * * *')


    //  parameters {
    //    nodeParam('TEST_HOST') {
    //      description("Select test host slave")
    //      defaultNodes(['34.67.166.101','slave'])
    //      allowedNodes(['34.67.166.101','slave'])
    //      trigger('multiSelectionDisallowed')
    //      eligibility('AllNodeEligibility')
    //    }
    //  }

    steps {
      shell('whoami')
      shell('sudo su jenkins | whoami')
      shell('whoami')
      shell('sudo echo testingSudo')
    }
  }
