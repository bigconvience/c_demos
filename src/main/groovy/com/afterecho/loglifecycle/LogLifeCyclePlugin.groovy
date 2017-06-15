package com.afterecho.loglifecycle;

import com.github.stephanenicolas.morpheus.AbstractMorpheusPlugin;
import javassist.build.IClassTransformer;
import org.gradle.api.Project;

/**
 * @author SNI
 */
class LogLifeCyclePlugin extends AbstractMorpheusPlugin {

    @Override
    protected Class getPluginExtension() {
        LogLifeCyclePluginExtension
    }

    @Override
    protected String getExtension() {
        "loglifecycle"
    }

    @Override
    public boolean skipVariant(def variant) {
        return variant.name.contains('release')
    }

    @Override
    public IClassTransformer[] getTransformers(Project project) {
        return new LogLifeCycleProcessor()
    }
}
