package com.brianattwell.plugin;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.build.IClassTransformer;
import javassist.build.JavassistBuildException;

import static com.brianattwell.plugin.utils.ClassInjectUtil.getOnCreateMethod;
import static com.github.stephanenicolas.morpheus.commons.JavassistUtils.isActivity;
import static com.github.stephanenicolas.morpheus.commons.JavassistUtils.isFragment;

public class ClassTransformer implements IClassTransformer {

    @Override
    public void applyTransformations(CtClass ctClass) throws JavassistBuildException {
        System.out.println(":plugin:applyTransformations on " + ctClass.getName());
        System.out.println(":plugin:applyTransformations on " + ctClass.getName());
        try {
            CtMethod[] methods = getOnCreateMethod(ctClass);
            for (CtMethod method : methods) {
                method.insertAfter("android.util.Log.d(\"MOO\", \"I am inserted code!\");");
            }
        } catch (Exception e) {
            throw new JavassistBuildException(e);
        }
    }

    @Override
    public boolean shouldTransform(CtClass candidateClass) throws JavassistBuildException {
        try {
            return isAnActivity(candidateClass) || isFragment(candidateClass);
        } catch (Exception e) {
            throw new JavassistBuildException(e);
        }
    }

    private static boolean isAnActivity(CtClass candidateClass) throws NotFoundException {
        // TODO: handle support activity
        return isActivity(candidateClass);
    }
}


