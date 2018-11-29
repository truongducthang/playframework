package com.lightbend.play.fixtures.app

import org.gradle.util.VersionNumber

import static com.lightbend.play.fixtures.Repositories.playRepositories

class WithFailingTestsApp extends PlayApp {
    List<SourceFile> appSources
    List<SourceFile> viewSources
    List<SourceFile> confSources
    List<SourceFile> testSources

    @Override
    SourceFile getGradleBuild() {
        def gradleBuild = sourceFile("", "build.gradle", "basicplayapp")
        def gradleBuildWithRepositories = gradleBuild.content.concat """
            allprojects {
                ${playRepositories()}
            }
        """
        return new SourceFile(gradleBuild.path, gradleBuild.name, gradleBuildWithRepositories)
    }

    WithFailingTestsApp(VersionNumber version){
        super(version)
        appSources = sourceFiles("app", "basicplayapp");
        viewSources = sourceFiles("app/views", "basicplayapp");
        confSources = sourceFiles("conf", "shared") + sourceFiles("conf", "basicplayapp")
        testSources = sourceFiles("test") + sourceFiles("test", "basicplayapp")
    }
}
