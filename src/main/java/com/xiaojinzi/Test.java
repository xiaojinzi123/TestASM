package com.xiaojinzi;

import org.objectweb.asm.*;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Test implements Opcodes {

    public static void main(String[] args) throws IOException {

        PrintWriter printWriter = new PrintWriter(System.out);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, printWriter);
        CheckClassAdapter cv = new CheckClassAdapter(tcv);
        cw.visit(
                Opcodes.V1_8, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
                "com/xiaojinzi/ApiUtil", null,
                "java/lang/Object", null
        );

        writeStructureMethod(cw);
        writeApiMethod(cw);

        cw.visitEnd();
        byte[] bytes = cw.toByteArray();

        FileOutputStream out = new FileOutputStream(
                new File("/Users/cxj/Documents/code/java/temp/TestASM/build/classes/java/main/com/xiaojinzi/ApiUtil.class")
        );

        out.write(bytes);

        out.close();


    }

    public static void writeStructureMethod(ClassVisitor cw) {
        MethodVisitor methodVisitor = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        // methodVisitor.visitLineNumber(3, label0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        methodVisitor.visitInsn(RETURN);
        Label label1 = new Label();
        methodVisitor.visitLabel(label1);
        methodVisitor.visitLocalVariable("this", "Lcom/xiaojinzi/ApiUtil;", null, label0, label1, 0);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
    }

    public static void writeApiMethod(ClassVisitor cw) {

        MethodVisitor methodVisitor = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "getApi", "(Ljava/lang/String;)Lcom/xiaojinzi/Api;", null, null);
        methodVisitor.visitCode();
        // 开始的标记
        Label labelStart = new Label();
        // 标记开始
        methodVisitor.visitLabel(labelStart);

        // 所有的名称
        List<String> names = new ArrayList<>();
        names.add("user");
        names.add("self");
        names.add("music");
        // names.add("tra");

        for (int i = 0; i < names.size(); i++) {

            // 除了第一个后面的都有前一个 if 跳转过来的 label
            Label ifJumpLabel = new Label();
            // 拿到名称
            String name = names.get(i);

            methodVisitor.visitLdcInsn(name);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
            methodVisitor.visitJumpInsn(IFEQ, ifJumpLabel);
            methodVisitor.visitTypeInsn(NEW,
                    "com/xiaojinzi/" +
                            (name.substring(0, 1).toUpperCase() + name.substring(1)) +
                            "Api"
            );
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/xiaojinzi/UserApi", "<init>", "()V", false);
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitLabel(ifJumpLabel);

        }

        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitInsn(ARETURN);

        // 结束的标记
        Label labelEnd = new Label();
        methodVisitor.visitLabel(labelEnd);
        methodVisitor.visitLocalVariable("name", "Ljava/lang/String;", null, labelStart, labelEnd, 0);
        methodVisitor.visitEnd();

    }

    public static void writeMethodTest(ClassWriter classWriter) {
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "getApi", "(Ljava/lang/String;)Lcom/xiaojinzi/Api;", null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitLineNumber(6, label0);
        methodVisitor.visitLdcInsn("user");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label1);
        Label label2 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLineNumber(7, label2);
        methodVisitor.visitTypeInsn(NEW, "com/xiaojinzi/UserApi");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/xiaojinzi/UserApi", "<init>", "()V", false);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitLineNumber(9, label1);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitLdcInsn("music");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
        Label label3 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label3);
        Label label4 = new Label();
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLineNumber(10, label4);
        methodVisitor.visitTypeInsn(NEW, "com/xiaojinzi/MusicApi");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/xiaojinzi/MusicApi", "<init>", "()V", false);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLineNumber(12, label3);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitLdcInsn("self");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
        Label label5 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label5);
        Label label6 = new Label();
        methodVisitor.visitLabel(label6);
        methodVisitor.visitLineNumber(13, label6);
        methodVisitor.visitTypeInsn(NEW, "com/xiaojinzi/SelfApi");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/xiaojinzi/SelfApi", "<init>", "()V", false);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitLabel(label5);
        methodVisitor.visitLineNumber(15, label5);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitInsn(ARETURN);
        Label label7 = new Label();
        methodVisitor.visitLabel(label7);
        methodVisitor.visitLocalVariable("name", "Ljava/lang/String;", null, label0, label7, 0);
        methodVisitor.visitMaxs(2, 1);
        methodVisitor.visitEnd();
    }

}
