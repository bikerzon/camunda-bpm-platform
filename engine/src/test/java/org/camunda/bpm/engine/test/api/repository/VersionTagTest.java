/*
 * Copyright © 2012 - 2018 camunda services GmbH and various authors (info@camunda.com)
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
package org.camunda.bpm.engine.test.api.repository;

import org.camunda.bpm.engine.impl.test.PluggableProcessEngineTestCase;
import org.camunda.bpm.engine.repository.DecisionDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.test.Deployment;

/**
 * @author Stefan Hentschel.
 */
public class VersionTagTest extends PluggableProcessEngineTestCase {

  @Deployment
  public void testParsingVersionTag() {
    ProcessDefinition process = repositoryService
      .createProcessDefinitionQuery()
      .orderByProcessDefinitionId()
      .asc()
      .singleResult();

    assertEquals("ver_tag_1", process.getVersionTag());
  }

  @Deployment(resources={"org/camunda/bpm/engine/test/api/repository/processOne.bpmn20.xml"})
  public void testParsingNullVersionTag() {
    ProcessDefinition process = repositoryService
      .createProcessDefinitionQuery()
      .orderByProcessDefinitionId()
      .asc()
      .singleResult();

    assertEquals(null, process.getVersionTag());
  }

  @Deployment(resources={"org/camunda/bpm/engine/test/api/repository/versionTag.dmn"})
  public void testParsingVersionTagDecisionDefinition() {
    DecisionDefinition decision = repositoryService
    .createDecisionDefinitionQuery()
    .orderByDecisionDefinitionVersion()
    .asc()
    .singleResult();

    assertEquals("1.0.0", decision.getVersionTag());
  }

  @Deployment(resources={"org/camunda/bpm/engine/test/api/repository/noVersionTag.dmn"})
  public void testParsingNullVersionTagDecisionDefinition() {
    DecisionDefinition decision = repositoryService
      .createDecisionDefinitionQuery()
    .orderByDecisionDefinitionVersion()
    .asc()
    .singleResult();

    assertEquals(null, decision.getVersionTag());
  }
}
