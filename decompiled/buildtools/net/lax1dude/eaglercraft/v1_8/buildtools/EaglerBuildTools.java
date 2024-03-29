package net.lax1dude.eaglercraft.v1_8.buildtools;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import net.lax1dude.eaglercraft.v1_8.buildtools.task.diff.MergePullRequest;
import net.lax1dude.eaglercraft.v1_8.buildtools.task.diff.PullRequestTask;
import net.lax1dude.eaglercraft.v1_8.buildtools.task.init.CreateUnpatched;
import net.lax1dude.eaglercraft.v1_8.buildtools.task.init.InitTask;
import net.lax1dude.eaglercraft.v1_8.buildtools.task.init.SetupWorkspace;
import net.lax1dude.eaglercraft.v1_8.buildtools.task.init.TaskClean;

public class EaglerBuildTools {
   public static File repositoryRoot = new File(".");

   public static void main(String[] var0) {
      System.out.println("Eaglercraft 1.8 Build Tools");
      System.out.println("Copyright (c) 2022-2024 lax1dude");
      System.out.println();
      if (!System.getProperty("eaglercraft.isJava11", "false").equalsIgnoreCase("true")) {
         try {
            if (!(Boolean)Class.forName("net.lax1dude.eaglercraft.v1_8.buildtools.Java11Check", true, new URLClassLoader(new URL[]{(new File("buildtools/Java11Check.jar")).toURI().toURL()})).getMethod("classLoadCheck").invoke((Object)null)) {
               throw new RuntimeException("wtf?");
            }
         } catch (Throwable var1) {
            System.err.println("ERROR: A minimum of Java 11 is required to run this tool!");
            System.err.println();
            System.err.println("You are using Java " + System.getProperty("java.version"));
            System.err.println();
            return;
         }
      }

      if (var0.length == 0 || var0.length == 1 && var0[0].equalsIgnoreCase("help")) {
         System.out.println("Options:");
         System.out.println("  help - displays this message");
         System.out.println("  init - decompiles 1.12.2 and applies the main repo's patch files");
         System.out.println("  workspace - creates a dev workspace with a gradle project to compile the source");
         System.out.println("  pullrequest - scans changes in the dev workspace and creates patch files for pull requests");
         System.out.println("  pullrequest_test - makes new workspace and re-applies the patches in 'pullrequest'");
         System.out.println("  unpatched - creates a zip file with the vanilla minecraft source without patches");
         System.out.println("  merge - merges the patch files in the pullrequest folder with the repo's main patch files");
         System.out.println("  merge_direct - merges changes in the dev workspace with the repo's main patch files");
         System.out.println("  clean - delete init and pullrequest directories, keeps dev workspace");
         System.out.println();
      } else if (var0.length == 1 && var0[0].equalsIgnoreCase("init")) {
         LicensePrompt.display();
         System.out.println("Running task '" + var0[0] + "':");
         System.out.println();
         if (InitTask.initTask()) {
            System.out.println();
            System.out.println("Task Complete.");
            System.out.println();
         } else {
            System.err.println();
            System.err.println("Task Failed!");
            System.err.println();
         }
      } else if (var0.length == 1 && var0[0].equalsIgnoreCase("workspace")) {
         System.out.println("Running task '" + var0[0] + "':");
         System.out.println();
         if (SetupWorkspace.setupWorkspace()) {
            System.out.println();
            System.out.println("Task Complete.");
            System.out.println();
         } else {
            System.err.println();
            System.err.println("Task Failed!");
            System.err.println();
         }
      } else if (var0.length == 1 && var0[0].equalsIgnoreCase("pullrequest")) {
         System.out.println("Running task '" + var0[0] + "':");
         System.out.println();
         if (PullRequestTask.pullRequest()) {
            System.out.println();
            System.out.println("Task Complete.");
            System.out.println();
         } else {
            System.err.println();
            System.err.println("Task Failed!");
            System.err.println();
         }
      } else if (var0.length == 1 && var0[0].equalsIgnoreCase("pullrequest_test")) {
         System.out.println("Running task '" + var0[0] + "':");
         System.out.println();
         if (SetupWorkspace.pullRequestTest()) {
            System.out.println();
            System.out.println("Task Complete.");
            System.out.println();
         } else {
            System.err.println();
            System.err.println("Task Failed!");
            System.err.println();
         }
      } else if (var0.length == 1 && var0[0].equalsIgnoreCase("unpatched")) {
         System.out.println("Running task '" + var0[0] + "':");
         System.out.println();
         if (CreateUnpatched.createUnpatched()) {
            System.out.println();
            System.out.println("Task Complete.");
            System.out.println();
         } else {
            System.err.println();
            System.err.println("Task Failed!");
            System.err.println();
         }
      } else if (var0.length == 1 && var0[0].equalsIgnoreCase("merge")) {
         System.out.println("Running task '" + var0[0] + "':");
         System.out.println();
         if (MergePullRequest.mergeTask()) {
            System.out.println();
            System.out.println("Task Complete.");
            System.out.println();
         } else {
            System.err.println();
            System.err.println("Task Failed!");
            System.err.println();
         }
      } else if (var0.length == 1 && var0[0].equalsIgnoreCase("merge_direct")) {
         System.out.println("Running task '" + var0[0] + "':");
         System.out.println();
         if (MergePullRequest.mergeDirect()) {
            System.out.println();
            System.out.println("Task Complete.");
            System.out.println();
         } else {
            System.err.println();
            System.err.println("Task Failed!");
            System.err.println();
         }
      } else if (var0.length == 1 && var0[0].equalsIgnoreCase("clean")) {
         System.out.println("Running task '" + var0[0] + "':");
         System.out.println();
         if (TaskClean.taskClean()) {
            System.out.println();
            System.out.println("Task Complete.");
            System.out.println();
         } else {
            System.err.println();
            System.err.println("Task Failed!");
            System.err.println();
         }
      } else {
         System.err.println("Invalid arguments!");
      }

   }
}
