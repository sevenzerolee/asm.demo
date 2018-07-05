package org.sevenzero.asm.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		test();
	}
	
	static void test() {
		ClassWriter cw = new ClassWriter(0);
		String className = "org.sevenzero.HelloWorld";
		cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null);
		
		MethodVisitor init = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
		init.visitCode();
		init.visitVarInsn(Opcodes.ALOAD, 0);
		init.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "V()");
		init.visitInsn(Opcodes.RETURN);
		init.visitMaxs(1, 1);
		init.visitEnd();
		
		cw.visitEnd();
		
		byte[] code = cw.toByteArray();
		File file = new File("/home/lb/tmp/HelloWorld.class");
		try (
				FileOutputStream fos = new FileOutputStream(file);
				) {
			fos.write(code);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
