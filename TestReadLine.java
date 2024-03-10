/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad1;

import java.io.*;

class TestReadLine {
  public static void main(String[] args) {

    BufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
    String str = null;
    try {
      str = in.readLine();
    } catch (IOException e) { e.printStackTrace(); }
    System.out.println("\nline is: " + str);
  }
}