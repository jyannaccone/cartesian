/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership.  The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package net.yannaccone.commons.cartesian;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class TestCartesianProduct {

  /*
   * 
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(TestCartesianProduct.class);
  
  /*
   * 
   */
  private List<List<String>> validInputs;
  
  /*
   * 
   */
  private List<List<String>> invalidInputs1;
  
  /*
   * 
   */
  private List<List<String>> invalidInputs2;
  
  /*
   * 
   */
  private List<List<String>> expectedOutputs;
  
  /*
   * 
   */
  private int expectedRows;
  
  /*
   * 
   */
  private int expectedColumns;
  
  /*
   * 
   */
  private CartesianProduct<String> cartesianProduct;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    
    //
    // Build the validInputs
    //
    
    List<String> input0 = Arrays.asList(
        "uno",
        "dos"
    );
    
    
    List<String> input1 = Arrays.asList(
        "one"
    );
    
    List<String> input2 = Arrays.asList(
        "eins",  
        "zwei",
        "drei"
    );
    
    validInputs = Arrays.asList(input0, input1, input2);
    
    invalidInputs1 = Arrays.asList();
    
    invalidInputs2 = Arrays.asList(input0, Arrays.asList(), input2);
    
    //
    // Build the expectedOutputs
    //
    
    List<String> expectedOutput0 = Arrays.asList(
      "uno", "one", "eins"  
    );

    List<String> expectedOutput1 = Arrays.asList(
      "uno", "one", "zwei"  
    );

    List<String> expectedOutput2 = Arrays.asList(
      "uno", "one", "drei"  
    );

    List<String> expectedOutput3 = Arrays.asList(
      "dos", "one", "eins"  
    );

    List<String> expectedOutput4 = Arrays.asList(
      "dos", "one", "zwei"  
    );

    List<String> expectedOutput5 = Arrays.asList(
      "dos", "one", "drei"  
    );
    
    expectedOutputs = Arrays.asList(expectedOutput0, expectedOutput1, expectedOutput2,
        expectedOutput3, expectedOutput4, expectedOutput5);
    
    //
    // Set expectedRows and expectedColumns
    //
    
    expectedRows = 6;
    expectedColumns = 3;
    
    //
    // Build the CartesianProduct
    //
    
    cartesianProduct = new CartesianProduct<>();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * 
   * @throws CartesianProductException
   */
  @Test
  public void testCartesianProduct() throws CartesianProductException {
    
    List<List<String>> outputs = cartesianProduct.cartesianProduct(validInputs);
    
    assertNotNull("verify outputs is not null", outputs);
    
    assertEquals("verify outputs equals expectedOutputs", expectedOutputs, outputs);
  }
  
  /**
   * 
   * @throws CartesianProductException
   */
  @Test(expected=CartesianProductException.class)
  public void testCartesianProductInvalidInputs1() throws CartesianProductException {
    
    cartesianProduct.cartesianProduct(invalidInputs1);
    
    fail("should have thrown CartesianProductException");
  }

  /**
   * 
   * @throws CartesianProductException
   */
  @Test(expected=CartesianProductException.class)
  public void testCartesianProductInvalidInputs2() throws CartesianProductException {
    
    cartesianProduct.cartesianProduct(invalidInputs2);
    
    fail("should have thrown CartesianProductException");
  }

  /**
   * 
   * @throws CartesianProductException
   */
  @Test
  public void testCartesianProductRows() throws CartesianProductException {
    
    int rows = cartesianProduct.cartesianProductRows(validInputs);
    
    assertEquals("verify outputs equals expectedOutputs", expectedRows, rows);
  }
  
  /**
   * 
   * @throws CartesianProductException
   */
  @Test(expected=CartesianProductException.class)
  public void testCartesianProductRowsInvalidInputs1() throws CartesianProductException {
    
    cartesianProduct.cartesianProductRows(invalidInputs1);
    
    fail("should have thrown CartesianProductException");
  }

  /**
   * 
   * @throws CartesianProductException
   */
  @Test(expected=CartesianProductException.class)
  public void testCartesianProductRowsInvalidInputs2() throws CartesianProductException {
    
    cartesianProduct.cartesianProductRows(invalidInputs2);
    
    fail("should have thrown CartesianProductException");
  }

  /**
   * 
   * @throws CartesianProductException
   */
  @Test
  public void testCartesianProductColumns() throws CartesianProductException {
    
    int columns = cartesianProduct.cartesianProductColumns(validInputs);
    
    assertEquals("verify outputs equals expectedOutputs", expectedColumns, columns);
  }
  
  /**
   * 
   * @throws CartesianProductException
   */
  @Test(expected=CartesianProductException.class)
  public void testCartesianProductColumnsInvalidInputs1() throws CartesianProductException {
    
    cartesianProduct.cartesianProductColumns(invalidInputs1);
    
    fail("should have thrown CartesianProductException");
  }

  /**
   * 
   * @throws CartesianProductException
   */
  @Test(expected=CartesianProductException.class)
  public void testCartesianProductColumnsInvalidInputs2() throws CartesianProductException {
    
    cartesianProduct.cartesianProductColumns(invalidInputs2);
    
    fail("should have thrown CartesianProductException");
  }

}
