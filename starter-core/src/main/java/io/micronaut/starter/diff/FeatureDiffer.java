/*
 * Copyright 2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.starter.diff;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import io.micronaut.starter.application.ApplicationType;
import io.micronaut.starter.application.Project;
import io.micronaut.starter.application.generator.GeneratorContext;
import io.micronaut.starter.application.generator.ProjectGenerator;
import io.micronaut.starter.io.ConsoleOutput;
import io.micronaut.starter.io.MapOutputHandler;
import io.micronaut.starter.options.Options;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Methods for diffing projects and features.
 *
 * @since 1.0.0
 * @author jameskleeh
 * @author graemerocher
 */
@Singleton
public class FeatureDiffer {

    /**
     * Produces a Diff for the given arguments.
     * @param projectGenerator The project generator
     * @param project The project
     * @param applicationType The application type
     * @param options The options
     * @param features The features to diff
     * @param writer The consumer to send output to
     * @throws Exception If something does wrong
     */
    public void produceDiff(
            ProjectGenerator projectGenerator,
            Project project,
            ApplicationType applicationType,
            Options options,
            List<String> features,
            Consumer<String> writer) throws Exception {

        GeneratorContext generatorContext = projectGenerator.createGeneratorContext(
                applicationType,
                project,
                options,
                features,
                ConsoleOutput.NOOP
        );
        produceDiff(projectGenerator, generatorContext, writer);
    }

    /**
     * Produces a Diff for the given arguments.
     * @param projectGenerator The project generator
     * @param generatorContext The generator context
     * @param writer The consumer to send output to
     * @throws Exception If something does wrong
     */
    public void produceDiff(
            ProjectGenerator projectGenerator,
            GeneratorContext generatorContext,
            Consumer<String> writer) throws Exception {
        MapOutputHandler outputHandler = new MapOutputHandler();
        Project project = generatorContext.getProject();
        ApplicationType applicationType = generatorContext.getApplicationType();
        projectGenerator.generate(
                applicationType,
                project,
                new Options(generatorContext.getLanguage(), generatorContext.getTestFramework(), generatorContext.getBuildTool(), generatorContext.getJdkVersion()),
                Collections.emptyList(),
                outputHandler,
                ConsoleOutput.NOOP
        );
        Map<String, String> oldProject = outputHandler.getProject();

        outputHandler = new MapOutputHandler();
        projectGenerator.generate(
                applicationType,
                project,
                outputHandler,
                generatorContext
        );
        Map<String, String> newProject = outputHandler.getProject();

        for (Map.Entry<String, String> entry: newProject.entrySet()) {
            String oldFile = oldProject.remove(entry.getKey());

            if (entry.getValue() == null) {
                continue;
            }

            List<String> oldFileLines = oldFile == null ? Collections.emptyList() : toLines(oldFile);

            String newFile = entry.getValue();
            List<String> newFileLines = toLines(newFile);

            Patch<String> diff = DiffUtils.diff(oldFileLines, newFileLines);
            List<String> unifiedDiff = UnifiedDiffUtils.generateUnifiedDiff(entry.getKey(), entry.getKey(), oldFileLines, diff, 3);

            if (!unifiedDiff.isEmpty()) {
                for (String delta : unifiedDiff) {
                    writer.accept(delta);
                }
                writer.accept("\n");
            }
        }

        for (Map.Entry<String, String> entry: oldProject.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            List<String> oldFileLines = toLines(entry.getValue());
            Patch<String> diff = DiffUtils.diff(oldFileLines, Collections.emptyList());
            List<String> unifiedDiff = UnifiedDiffUtils.generateUnifiedDiff(entry.getKey(), entry.getKey(), oldFileLines, diff, 3);

            if (!unifiedDiff.isEmpty()) {
                for (String delta : unifiedDiff) {
                    writer.accept(delta);
                }
                writer.accept("\n");
            }
        }
    }

    private List<String> toLines(String file) {
        return Arrays.asList(file.split("\n"));
    }
}