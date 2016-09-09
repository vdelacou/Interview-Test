/**
 * Author: Dustin Mao
 */
package com.mfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewApplication {

  public static void main(String[] args) {
    System.out.println("Before SpringApplication.run");
    SpringApplication.run(InterviewApplication.class, args);
    System.out.println("After SpringApplication.run");
  }
}
