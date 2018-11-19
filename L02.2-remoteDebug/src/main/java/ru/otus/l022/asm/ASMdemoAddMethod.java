package ru.otus.l022.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @author sergey
 * created on 17.07.18.
 */
public class ASMdemoAddMethod {

    private int value = 10;

    public int getValue() {
        return value;
    }

    public static void main(String[] args) throws Exception {
        addMethod();
        System.out.println("DONE");
    }

    private static void addMethod() throws IOException {
        ClassReader cr = new ClassReader("ru.otus.l022.asm.ASMdemoAddMethod");
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM7, cw){};
        cr.accept(cv, Opcodes.ASM7);


        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC,  "setValue", "(I)V", null, null);

        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD,1);
        mv.visitFieldInsn(Opcodes.PUTFIELD,"ru/otus/l022/ASMdemoAddMethod", "value", "I");

        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        try(OutputStream fos = new FileOutputStream("ASMdemoAddMethod_test.class")) {
            fos.write(cw.toByteArray());
        }
    }

}