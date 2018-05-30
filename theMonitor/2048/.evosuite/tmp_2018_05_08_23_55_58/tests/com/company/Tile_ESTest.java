/*
 * This file was automatically generated by EvoSuite
 * Tue May 08 16:59:06 GMT 2018
 */

package com.company;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.company.Point;
import com.company.Tile;
import java.awt.Graphics2D;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Tile_ESTest extends Tile_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Tile tile0 = new Tile((-13), (-13), (-13));
      // Undeclared exception!
      try { 
        tile0.render((Graphics2D) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.company.Tile", e);
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Tile tile0 = new Tile(2288, 2288, 2288);
      tile0.update();
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Tile tile0 = new Tile((-13), (-13), (-13));
      Point point0 = tile0.getSlideTo();
      tile0.setSlideTo(point0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Tile tile0 = new Tile(0, 0, 5551);
      tile0.setY(0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      Tile tile0 = new Tile((-13), (-13), (-13));
      tile0.getValue();
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Tile tile0 = new Tile(0, 0, 5551);
      tile0.getY();
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Tile tile0 = new Tile(0, 0, 5551);
      tile0.getX();
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Tile tile0 = new Tile(0, 0, 5551);
      tile0.canCombine();
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Tile tile0 = new Tile(2288, 2288, 2288);
      tile0.setValue(2);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Tile tile0 = new Tile(2288, 2288, 2288);
      tile0.setValue(4);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Tile tile0 = new Tile(8, 8, 1024);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Tile tile0 = new Tile(412, 2048, 2048);
      tile0.setValue(16);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Tile tile0 = new Tile(32, 32, 32);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Tile tile0 = new Tile(897, 64, 64);
      tile0.setValue(64);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Tile tile0 = new Tile(133, 133, 133);
      tile0.setValue(128);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Tile tile0 = new Tile(256, 256, 256);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Tile tile0 = new Tile(512, 512, 512);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Tile tile0 = new Tile(0, 0, 0);
      tile0.setValue(1024);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      Tile tile0 = new Tile(412, 2048, 2048);
      tile0.setValue(2048);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      Tile tile0 = new Tile(0, (-848), (-848));
      tile0.setCanCombine(false);
      tile0.canCombine();
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      Tile tile0 = new Tile(0, 0, 5551);
      tile0.setSlideTo((Point) null);
      tile0.getSlideTo();
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      Tile tile0 = new Tile(0, (-848), (-848));
      tile0.setValue(3485);
      tile0.getValue();
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      Tile tile0 = new Tile(0, 0, 5551);
      tile0.getValue();
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      Tile tile0 = new Tile((-795), (-795), (-795));
      tile0.getX();
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      Tile tile0 = new Tile(0, 0, 5551);
      tile0.setX(2356);
      tile0.getX();
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      Tile tile0 = new Tile((-795), (-795), (-795));
      tile0.getY();
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      Tile tile0 = new Tile(0, 16, 0);
      tile0.getY();
  }
}