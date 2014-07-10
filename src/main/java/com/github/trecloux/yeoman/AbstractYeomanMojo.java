package com.github.trecloux.yeoman;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;


/*
 * Copyright 2013 Thomas Recloux
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
 *
 */
public abstract class AbstractYeomanMojo extends AbstractMojo {

    @Parameter( defaultValue = "yo", required = true )
    File yeomanProjectDirectory;
    @Parameter( defaultValue = "${os.name}", readonly = true)
    String osName;
    @Parameter( property = "yo.test.skip", defaultValue = "false")
    boolean skipTests;
    @Parameter( property = "yo.skip", defaultValue = "false")
    boolean skip;

    void logToolVersion(final String toolName) throws MojoExecutionException {
        getLog().info(toolName + " version :");
        executeCommand(toolName + " --version");
    }

    void logAndExecuteCommand(String command) throws MojoExecutionException {
        logCommand(command);
        executeCommand(command);
    }

    void logCommand(String command) {
        getLog().info("--------------------------------------");
        getLog().info("         " + command.toUpperCase());
        getLog().info("--------------------------------------");
    }

    void executeCommand(String command) throws MojoExecutionException {
        try {
            if (isWindows()) {
                command = "cmd /c " + command;
            }
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            executor.setWorkingDirectory(yeomanProjectDirectory);
            executor.execute(cmdLine);
        } catch (IOException e) {
            throw new MojoExecutionException("Error during : " + command, e);
        }
    }

    private boolean isWindows() {
        return osName.startsWith("Windows");
    }
}
