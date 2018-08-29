# Modular Language Composition for the Masses - Artifacts

Manuel Leduc, Thomas Degueule and Benoit Combemale

## Introduction

The Revisitor pattern is a language implementation pattern that enables independent extensibility of the syntax and  semantics of metamodel-based DSLs, with incremental compilation and without anticipation. It is inspired by the Object Algebra design pattern and adapted to the specificities of metamodeling.

On top of the Revisitor pattern, we introduce Alex , a high-level language for semantics specification of metamodels that compiles to Revisitors to support separate compilation of language modules.

Alex is tightly integrated with the Eclipse Modeling Framework (EMF) and relies on the Ecore meta-language for the definition of the abstract syntax of DSLs. Operational semantics is defined with ALE using an open-class-like mechanism.

Alex is bundled a set of Eclipse plug-ins.

In the current artifact,  we evaluate Alex in the development of a new DSL for IoT systems modeling resulting from the composition of three independently defined languages (UML activity diagrams, Lua, and the CORBA Interface Description Language).

## Artifacts

The following table lists exhaustively the files and directories of the artifact is located in `./listing.md`.

## Requirements

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html);
- Eclipse Photon ([4.8.0](https://www.eclipse.org/downloads/packages/eclipse-ide-java-and-dsl-developers/photonr)) + EcoreTools ([3.2.0](http://download.eclipse.org/ecoretools/updates/releases/3.2.0/neon)).

## Instructions

### Installing Alex

Once eclipse started, `Help -> Install new Software...`, click on `Add...`, then `Archive...` and select the **archive.zip** file locate at the root of the artifacts directory.
Finally, select the **Alex Feature** entry and follow the usual eclipse installation steps.
Once Eclipse restarted, the Alex plugin is installed.

#### Examples

The examples presented in the section are not strictly related to the evaluation use case, but are simpler languages aimed at easing the understanding of Alex. The examples are simple introductory example, and does not make use of the language composition concepts studied in the paper.

###### Import

The examples and located in the `./examples` directory. They are regular eclipse plugins and can be imported by the usual `File → Import... → Existing Projects into Workspace` then selecting the `examples` directory in the `Select root directory` field and clicking on `Finish`.

###### Structure

Each example is structured the same way, with a `model` directory containing an `ecore` file, describing the AST of the language, and a `semantic` directory containing an `ale` file describing a semantics for the language.

The `src` and `src-gen` directories contains java sources derived from the AST and semantics definitions.

Each project contains a `Main.java` class which execute a small program.

###### Languages descriptions

The `boolexp` and `minifsm` examples are standalone languages, respectively showing a boolean expressions language and a minimal Finite State Machine implementations.

The `minifsm.timed` example extends the `fsm` language and introduces the notion of time.

The `guardedfsm` language reuse both the `boolexp` and `minfsm` languages to define a Finiste State Machine language with boolean guards.

![](./examples/dependencies.svg)



#### Use Cases

This section describes the implementation of the languages presented in section 5 of the paper.

##### Iot Lua Language

The IoT Language is build by composing the IoT Language concern with and Activity Diagram, IDL and Lua language concerns.

`./iot/iot.lua.model/model/iot_lua.ecore` composes the language concerns abstract syntaxes to form the IoT Lua abstract syntax.

`./iot/iot.lua.ale/src/iot_lua.ale` composes the language concern semantics, mostly by translation of the different semantics parameters.

For instance,  `ExpressionBindStatement` creates a new Lua environment, complete it with the Activity Diagram context and calls the delegated statement execute method.

Finally it reports back the updates of the environments by the execute call to the Activity Diagram context.

##### Iot Simple Expressions Language

The IoT Simple Expression Language follows the same composition methodology, but using two smaller languages instead of Lua.

##### Running the languages

Both languages proposed the execution of a illustrating usecase, in the form of a simulated IoT system which keeps track of the temperature and light of an environment.

The same program is runned for each language, but they are parsed by different Xtext grammar before being executed with their respective semantics.

The execution is done by running the unit test of the test classes (Right click `Run as → JUnit Plug-in Test`):

- **Iot Lua Language Test class:** `/iot.lua.xtext.tests/src/iot/lua/xtext/tests/IotLuaXtextExecutionTest.xtend`
- **Iot Simple Expression Test class:** `/iot.simpleexpressions.xtext.tests/src/iot/simpleexpressions/xtext/tests/IoTExecutionTest.xtend`

Random values are produced during the execution, leading to nondeterministic execution logs, but an expected result looks like:

```text
bind temp to 0
bind lumen to 0
bind tempThreshold to 21
bind lumenThreshold to 1600
bind i to 0
bind one to 1
bind nbIter to 15
bind waitingTime to 500
bind temp to 19
assign temp = 19
[...]
```

##### Conclusion

![](./dependencies.svg)

The dependency graph presents a bird's eye view of the languages and language concerns dependencies.

The dashed rectangles are language concerns shared between our two IoT Languages (represented by grey rectangles).

This highlights the reuse offered by our approach to language concerns composition.

### Building Alex

#### Compilation

In eclipse, importing the Eclipse Plug-ins of the `/plugins` directory.

The named following the pattern `ale.xtext*` are part of the Alex Xtext language and can be developed following the rules of standards of the Xtext framework (Cf the [official documentation](https://www.eclipse.org/Xtext/documentation/index.html)).

The compilation is by default done incrementally by Eclipse, but starting a full compilation can be execute by selecting the `Project -> Clean...` menu entry, the checking `Clean all project` and finally clicking `Clean`.

One the triggered eclipse tasks finished, the projects opened in the workspace are rebuild.

#### Creating an installation archive

- Open the `site.xml` file in the `ale.p2updatesite` project then click on `Build All`.
- Create a zip archive with the `features` directory, the `plugins` directory, `artifacts.jar`, and `content.jar`.

## Known Issue

While the following issue is not a consequence of our artifact, it can get in the way during the artifact evaluation.

### Builtin LFS support not present/detected

If some log alike the extract below are polluting the Console, please install the latest EGit version as advised [here](https://bugs.eclipse.org/bugs/show_bug.cgi?id=536516).

The EGit update site is located at [http://download.eclipse.org/egit/updates/](http://download.eclipse.org/egit/updates/), the item to update is "Git integration  for Eclipse"

**Error Log sample:**

```text
!ENTRY org.eclipse.egit.core 2 0 2018-08-29 11:19:13.377
!MESSAGE Builtin LFS support not present/detected
!STACK 0
java.lang.ClassNotFoundException: org.eclipse.jgit.lfs.BuiltinLFS cannot be found by org.eclipse.egit.core_5.0.0.201806131550-r
	at org.eclipse.osgi.internal.loader.BundleLoader.findClassInternal(BundleLoader.java:508)
	at org.eclipse.osgi.internal.loader.BundleLoader.findClass(BundleLoader.java:419)
	at org.eclipse.osgi.internal.loader.BundleLoader.findClass(BundleLoader.java:411)
```

