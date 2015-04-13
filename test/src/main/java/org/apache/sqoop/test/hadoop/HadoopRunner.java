/**
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
package org.apache.sqoop.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.sqoop.test.utils.HdfsUtils;

/**
 * Hadoop cluster runner for testing purpose.
 * 
 * Runner provides methods for bootstrapping and using Hadoop cluster. This
 * abstract implementation is agnostic about in what mode Hadoop is running.
 * Each mode will have it's own concrete implementation (for example
 * LocalJobRunner, MiniCluster or Real existing cluster).
 */
public abstract class HadoopRunner {

  /**
   * Temporary path that can be used as a root for other directories storing
   * various data like logs or stored HDFS files.
   */
  private String temporaryPath;

  /**
   * Configuration object for Hadoop.
   */
  protected Configuration config = null;

  /**
   * Prepare configuration object. This method should be called once before the
   * start method is called.
   *
   * @param config
   *          is the configuration object to prepare.
   */
  abstract public Configuration prepareConfiguration(Configuration config)
      throws Exception;

  /**
   * Start hadoop cluster.
   *
   * @throws Exception
   */
  abstract public void start() throws Exception;

  /**
   * Stop hadoop cluster.
   *
   * @throws Exception
   */
  abstract public void stop() throws Exception;

  /**
   * Return working directory on HDFS instance that this HadoopRunner is using.
   *
   * This directory might be on local filesystem in case of local mode.
   */
  public String getTestDirectory() {
    return "/mapreduce-job-io";
  }

  /**
   * Get temporary path.
   *
   * @return
   */
  public String getTemporaryPath() {
    return temporaryPath;
  }

  /**
   * Set temporary path.
   *
   * @param temporaryPath
   */
  public void setTemporaryPath(String temporaryPath) {
    this.temporaryPath = temporaryPath;
  }

  /**
   * Return directory on local filesystem where logs and other data generated by
   * the Hadoop Cluster should be stored.
   *
   * @return
   */
  public String getDataDir() {
    return HdfsUtils.joinPathFragments(temporaryPath, "data");
  }

  /**
   * Return directory on local filesystem where logs and other data generated by
   * the Hadoop Cluster should be stored.
   *
   * @return
   */
  public String getLogDir() {
    return HdfsUtils.joinPathFragments(temporaryPath, "log");
  }

  /**
   * Get hadoop configuration.
   *
   * @return
   */
  public Configuration getConfiguration() {
    return config;
  }

  /**
   * Set the configuration object that should be used with Miniclusters.
   *
   * @param config
   */
  public void setConfiguration(Configuration config) {
    this.config = config;
  }
}