/*
 * This file was automatically generated by EvoSuite
 * Fri Jun 23 20:15:20 GMT 2023
 */

package com.example.demo.model;

import org.junit.Test;
import static org.junit.Assert.*;
import com.example.demo.model.Grade;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Grade_ESTest extends Grade_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Grade.GradeBuilder grade_GradeBuilder0 = Grade.builder();
      String string0 = grade_GradeBuilder0.toString();
      assertEquals("Grade.GradeBuilder(id=0, grade=null)", string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Grade.GradeBuilder grade_GradeBuilder0 = Grade.builder();
      Grade.GradeBuilder grade_GradeBuilder1 = grade_GradeBuilder0.id(33);
      assertSame(grade_GradeBuilder0, grade_GradeBuilder1);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      Grade.GradeBuilder grade_GradeBuilder0 = Grade.builder();
      Double double0 = Double.valueOf(0.0);
      Grade.GradeBuilder grade_GradeBuilder1 = grade_GradeBuilder0.grade(double0);
      assertSame(grade_GradeBuilder1, grade_GradeBuilder0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      Grade.GradeBuilder grade_GradeBuilder0 = Grade.builder();
      Grade grade0 = grade_GradeBuilder0.build();
      grade0.setId((-1185));
      assertEquals((-1185), grade0.getId());
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      Grade.GradeBuilder grade_GradeBuilder0 = Grade.builder();
      Grade grade0 = grade_GradeBuilder0.build();
      grade0.getGrade();
      assertEquals(0, grade0.getId());
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      Grade.GradeBuilder grade_GradeBuilder0 = Grade.builder();
      Grade grade0 = grade_GradeBuilder0.build();
      Double double0 = Double.valueOf(0.0);
      grade0.setGrade(double0);
      assertEquals(0, grade0.getId());
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      Grade.GradeBuilder grade_GradeBuilder0 = Grade.builder();
      Grade grade0 = grade_GradeBuilder0.build();
      int int0 = grade0.getId();
      assertEquals(0, int0);
  }
}
