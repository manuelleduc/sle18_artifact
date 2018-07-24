# Modular Language Composition for the Masses - Artifacts

Manuel Leduc, Thomas Degueule and Benoit Combemale

## Introduction

The Revisitor pattern is a language implementation pattern that enables independent extensibility of the syntax and  semantics of metamodel-based DSLs, with incremental compilation and without anticipation. It is inspired by the Object Algebra design pattern and adapted to the specificities of metamodeling.

On top of the Revisitor pattern, we introduce Alex , a high-level language for semantics specification of metamodels that   compiles to Revisitors to support separate compilation of language modules. 

Alex is tightly integrated with the Eclipse Modeling Framework (EMF) and relies on the Ecore meta-language for the definition of the abstract syntax of DSLs. Operational semantics is defined with ALE using an open-class-like mechanism.

Alex is bundled a set of Eclipse plug-ins.

In the current artifact,  We evaluate Alex in the development of a new DSL for IoT systems modeling resulting from the composition of three independently defined languages (UML activity diagrams, Lua, and the CORBA Interface Description Language).

## Artifacts

The following table lists exhaustively the files and directories of the artifact is located in `./listing.md`

## Requirements

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html);
- Eclipse Photon ([4.8.0](https://www.eclipse.org/downloads/packages/eclipse-ide-java-and-dsl-developers/photonr)) + EcoreTools ([3.2.0](http://download.eclipse.org/ecoretools/updates/releases/3.2.0/neon)).

## Instructions

### Installing Alex

Once eclipse started, `Help -> Install new Software...`.
Then, click on `Add...`, then `Archive...` and select the **archive.zip** file locate at the root of the artifacts directory.
Finally, select the **Alex Feature** entry and follow the usual eclipse installation steps.
Once Eclipse restarted, the Alex plugin is installed.

### Using Alex

#### Tutorials

#### Use Cases

### Building Alex

#### Compilation

In eclipse, importing the Eclipse Plug-ins of the `/plugins` directory.

The named following the pattern `ale.xtext*` are part of the Alex Xtext language and can be developed following the rules of standards of the Xtext framework (Cf the [official documentation](https://www.eclipse.org/Xtext/documentation/index.html)).

The compilation is by default done incrementally by Eclipse, but starting a full compilation can be execute by selecting the `Project -> Clean...` menu entry, the checking `Clean all project` and finally clicking `Clean`.

One the triggered eclipse tasks finished, the projects opened in the workspace are rebuild.

#### Creating an installation archive

- Open the `site.xml` file in the `ale.p2updatesite` project then click on `Build All`.
- Create an zip archive with the `features` directory, the `plugins` directory, `artifacts.jar`, and `content.jar`.