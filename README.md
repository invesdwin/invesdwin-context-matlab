# invesdwin-context-matlab
Integrate Matlab/Octave functionality with these modules for the [invesdwin-context](https://github.com/subes/invesdwin-context) module system.

## Maven

Releases and snapshots are deployed to this maven repository:
```
http://invesdwin.de/artifactory/invesdwin-oss-remote
```

Dependency declaration:
```xml
<dependency>
	<groupId>de.invesdwin</groupId>
	<artifactId>invesdwin-context-matlab-runtime-javaoctave</artifactId>
	<version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Runtime Integration Modules

We have a few options available for integrating Matlab/Octave:
- **invesdwin-context-matlab-runtime-matconsolectl**: Via [MatConsoleCtl](https://github.com/diffplug/matconsolectl) we are able to run our Matlab scripts in the official [Matlab](https://en.wikipedia.org/wiki/MATLAB) environment. The integration works by remote controlling the Matlab command line client and transferring data efficiently over [RMI](https://en.wikipedia.org/wiki/Java_remote_method_invocation). Multiple instances of Matlab are pooled for performance reasons and to allow parallel computation. You can change the path to the executable via the following system property:
```properties
de.invesdwin.context.matlab.runtime.matconsolectl.MatConsoleCtlProperties.MATLAB_COMMAND=matlab
```
- **invesdwin-context-matlab-runtime-javaoctave**: Via [JavaOctave](https://github.com/prateek/javaoctave) we are able to run our Matlab scripts in [Octave](https://www.gnu.org/software/octave/), which is an open source alternative implementation for the Matlab language. The integration works by remote controlling the Octave command line client and transferring data via files. Multiple instances of Octave are pooled for performance reasons and to allow parallel computation. You can change the path to the executable via the following system property:
```properties
de.invesdwin.context.matlab.runtime.javaoctave.JavaOctaveProperties.OCTAVE_COMMAND=octave
```

With this you can switch easily between Matlab/Octave for your scripts to test for interoperability and decide which implementation provides the best performance for your use case. Notably Matlab takes a long time to start up but has efficient data transfer, while Octave is faster to start up but the data transfer is less efficient. 

Also Octave is free while Matlab requires a paid license. Even though Octave is licensed as GPL, usage and integration with it can happen without falling under the GPL. Though please consider that when using modules for Octave/Matlab that are licensed under the GPL, you might have to make your own scripts available under the GPL too. For a more elaborate license discussion, see the documentation of [invesdwin-context-r](https://github.com/subes/invesdwin-context-r) which faces the same topic.

## Example Code

This is a minimal example of the famous `Hello World!` as a script:

```java
final AScriptTaskMatlab<String> script = new AScriptTaskMatlab<String>() {

    @Override
    public void populateInputs(final IScriptTaskInputs inputs) {
	inputs.putString("hello", "World");
    }

    @Override
    public void executeScript(final IScriptTaskEngine engine) {
	//execute this script inline:
	engine.eval("world = strcat({'Hello '}, hello, '!')");
	//or run it from a file:
	//engine.eval(new ClassPathResource("HelloWorldScript.m", getClass()));
    }

    @Override
    public String extractResults(final IScriptTaskResults results) {
        return results.getString("world");
    }
};
final String result = script.run(); //optionally pass a specific runner as an argument here
Assertions.assertThat(result).isEqualTo("Hello World!");
```

For more elaborate examples of the Matlab/Octave script integration, have a look at the `invesdwin-context-matlab-ornsteinuhlenbeck` module or the test cases in `invesdwin-context-matlab-runtime-contract` which are executed in each individual runtime module test suite.

## More Programming Languages

Similar integration modules like this one also exist for the following other programming languages: 

- **R Modules**: Scripting with R
	- https://github.com/subes/invesdwin-context-r 
- **Python Modules**: Scripting with Python
	- https://github.com/subes/invesdwin-context-python

## Support

If you need further assistance or have some ideas for improvements and don't want to create an issue here on github, feel free to start a discussion in our [invesdwin-platform](https://groups.google.com/forum/#!forum/invesdwin-platform) mailing list.
