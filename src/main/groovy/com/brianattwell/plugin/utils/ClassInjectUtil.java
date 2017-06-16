package com.brianattwell.plugin.utils;

import javassist.*;

/**
 * Created by jiangbenpeng on 15/06/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ClassInjectUtil {
    public static void insertLog(CtClass cc) throws Exception {
        CtMethod[] ctMethods = cc.getMethods();
        if (ctMethods != null) {
            for (int i = 0; i < ctMethods.length; i++) {
                CtMethod ctMethod = ctMethods[i];
                if (cc.equals(ctMethod.getDeclaringClass())) {
                    insertLog(ctMethod);
                }
            }
        }

        cc.writeFile();
    }

    public static boolean isPresenter(CtClass candidateClass) throws NotFoundException {
        return candidateClass.getName().equals("Presenter");
    }

    public static CtMethod[] getOnCreateMethod(CtClass ctClass)
            throws NotFoundException {
        String className = ctClass.getName();
        ClassPool pool = ctClass.getClassPool();

        CtMethod c1 = pool.get(className).getMethod("onCreate",
                "(Landroid/os/Bundle;Landroid/os/PersistableBundle;)V");
        CtMethod c2 = pool.get(className).getMethod("onCreate",
                "(Landroid/os/Bundle;)V");
        return new CtMethod[]{c1, c2};
    }

    public static void insertLog(CtMethod ctMethod, String fieldName) throws CannotCompileException {
        String methodName = ctMethod.getLongName();
        long injectTime = System.currentTimeMillis();
        System.out.println(methodName + ",start:" + injectTime + ",end:" + System.currentTimeMillis());
        System.out.println(methodName);

        ctMethod.insertBefore("{ " + fieldName + " = System.currentTimeMillis(); }");
        // String methodTail = "{ System.out.println(\"" + methodName +"\" + injectTime); }";
        String methodTail = "{ com.ali.music.api.core.util.PageTrack.getInstance().track(\"" + methodName + "\"," + fieldName + ", System.currentTimeMillis());}";
        System.out.println(methodTail);
        ctMethod.insertAfter(methodTail);
        //  ctMethod.insertAfter("{ System.out.println(methodName + \",start:\" + injectTime + \",end:\" + System.currentTimeMillis());}");
//            ctMethod.insertAfter("{ com.ali.music.api.core.util.PageTrack.getInstance().track(\"" + methodName + "\", injectTime, System.currentTimeMillis());}");
    }


    public static void insertLog(CtMethod ctMethod) throws CannotCompileException {
        String methodName = ctMethod.getLongName();
        long injectTime = System.currentTimeMillis();
        System.out.println(methodName + ",start:" + injectTime + ",end:" + System.currentTimeMillis());
        System.out.println(methodName);

        ctMethod.insertBefore("{ injectTime = System.currentTimeMillis(); }");
       // String methodTail = "{ System.out.println(\"" + methodName +"\" + injectTime); }";
        String methodTail = "{ com.ali.music.api.core.util.PageTrack.getInstance().track(\"" + methodName + "\", injectTime, System.currentTimeMillis());}";
        System.out.println(methodTail);
        ctMethod.insertAfter(methodTail);
      //  ctMethod.insertAfter("{ System.out.println(methodName + \",start:\" + injectTime + \",end:\" + System.currentTimeMillis());}");
//            ctMethod.insertAfter("{ com.ali.music.api.core.util.PageTrack.getInstance().track(\"" + methodName + "\", injectTime, System.currentTimeMillis());}");
    }

}
