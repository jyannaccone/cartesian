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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * A utility class to compute the Cartesian product for a List of Lists.
 */
public final class CartesianProduct<T> {
  
  /*
   * 
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(CartesianProduct.class);

  /**
   * Compute the Cartesian product for the List of Lists specified by the
   * provided inputs parameter
   * 
   * @param inputs A List of Lists for which to compute the Cartesian
   *                 product.
   * @return A List of Lists that is the Cartesian product of the inputs
   * @throws CartesianProductException if any of the Lists within the inputs
   *         List are empty or null.
   */
  public List<List<T>> cartesianProduct(List<List<T>> inputs) throws CartesianProductException {
    
    if (inputs==null || inputs.isEmpty()) {
      
      throw new CartesianProductException("inputs List cannot be null or empty");
    }
    
    for (List<T> input : inputs) {
      
      if (input==null || input.isEmpty()) {
        
        throw new CartesianProductException("inputs cannot contain null or empty List");
      }
    }
    
    List<Integer> indices = new ArrayList<>();
    
    List<List<T>> outputs = new ArrayList<>();

    while (nextIndices(inputs, indices)) {
      
      List<T> output = new ArrayList<>();
      
      for (int i=0; i<inputs.size(); i++) {
        
        output.add(inputs.get(i).get(indices.get(i)));
      }
      
      outputs.add(output);
    }
    
    return outputs;
  }
  
  /*
   * Get the next Cartesian combination of indices for inputs.
   * Return true if a new Cartesian combination of indices exists and false
   * otherwise.
   */
  private boolean nextIndices(List<List<T>> inputs, List<Integer> indices) {
    
    // First iteration, initialize each index to zero
    if (indices.isEmpty()) {
      
      for (int i=0; i<inputs.size(); i++) {
        
        indices.add(0);
      }
      
      return true;
    }
    
    // Not first iteration, calculate next Cartesian product of indices
    for (int i=(inputs.size()-1); i>=0; i--) {
      
      List<T> input = inputs.get(i);
      int nextIndex = (indices.get(i)+1) % input.size();

      indices.set(i, nextIndex);
      
      // Only want to increment one index per call, if nextIndex is greater
      // than zero, that has already happened so just return true
      if (nextIndex>0) { return true; }
    }
    
    // None of the indices incremented, return false
    return false;
  }
  
  /**
   * Computes the number of rows in the Cartesian product for the List of Lists
   * specified by the provided inputs parameter.
   * 
   * @param inputs A List of Lists for which to compute the number of rows
   *        in the Cartesian product.
   * @return The number of rows in the Cartesian product.
   * @throws CartesianProductException if any of the Lists within the inputs
   *         List are empty or null.
   */
  public int cartesianProductRows(List<List<T>> inputs) throws CartesianProductException {

    if (inputs==null || inputs.isEmpty()) {
      
      throw new CartesianProductException("inputs List cannot be null or empty");
    }
    
    for (List<T> input : inputs) {
      
      if (input==null || input.isEmpty()) {
        
        throw new CartesianProductException("inputs cannot contain null or empty List");
      }
    }
    
    int numberOfRows = 1;
    
    // The number of rows in the Cartesian product is equal to the
    // product of the sizes of the inner inputs lists.
    for (List<T> input : inputs) {
      
      numberOfRows *= input.size();
    }
    
    return numberOfRows;
  }
  
  /**
   * Computes the number of columns in each row of the Cartesian product for
   * the List of Lists specified by the provided inputs parameter.
   * 
   * @param inputs A List of Lists for which to compute the number of
   *        columns
   * @return The number of columns in each row of the Cartesian product.
   * @throws CartesianProductException if any of the Lists within the inputs
   *         List are empty or null.
   */
  public int cartesianProductColumns(List<List<T>> inputs) throws CartesianProductException {
    
    if (inputs==null || inputs.isEmpty()) {
      
      throw new CartesianProductException("inputs List cannot be null or empty");
    }
    
    for (List<T> input : inputs) {
      
      if (input==null || input.isEmpty()) {
        
        throw new CartesianProductException("inputs cannot contain null or empty List");
      }
    }
    
    return inputs.size();
  }
  
}
