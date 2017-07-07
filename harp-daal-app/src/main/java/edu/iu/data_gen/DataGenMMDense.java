/*
 * Copyright 2013-2016 Indiana University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.iu.data_gen;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @brief generator ascii Matrix Market format for Dense matrix 
 * (Array format column oriented)
 * used by daal_pca, daal_svd, daal_kmeans
 */
public class DataGenMMDense implements Runnable {

  private int pointsPerFile;
  private String localDir;
  private String fileName;
  int vectorSize;

  public DataGenMMDense(int pointsPerFile,
    String localInputDir, String fileName,
    int vectorSize) {
    this.pointsPerFile = pointsPerFile;
    this.localDir = localInputDir;
    this.fileName = fileName;
    this.vectorSize = vectorSize;
  }

  @Override
  public void run() {
    double point;
    Random random = new Random();
    try {

      BufferedWriter writer = new BufferedWriter(new FileWriter(this.localDir + File.separator + "data_" + this.fileName));

      for (int i = 0; i < pointsPerFile; i++) {
        for (int j = 0; j < vectorSize; j++) {
          point = random.nextDouble()*2 -1;
          writer.write(String.valueOf(point));
          writer.newLine();
        }
      }
      
      writer.close();

      System.out.println("Write file "
        + this.fileName);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
