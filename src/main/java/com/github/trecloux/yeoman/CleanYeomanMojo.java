package com.github.trecloux.yeoman;

import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.Mojo;

/*
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
// Not bound to a Maven lifecycle to not generate issue for people
// who NOT create clean target in their Grunt configuration file.
@Mojo( name = "clean" )
public class CleanYeomanMojo extends AbstractYeomanMojo {

    public void execute() throws MojoExecutionException {
        if (skip) {
            getLog().info("Skipping Yeoman Execution");
        } else {
            npmInstall();
            gruntClean();
        }
    }

    void gruntClean() throws MojoExecutionException {
        logToolVersion("grunt");
        logAndExecuteCommand("grunt clean");
    }
}
