propsgen
========

Groovy DSL script to generate hierarchical properties file for Java

How to write description file?
========

    regions {
        basePath = "build"

        region('default') {
            prop('a', '1')
        }

        region('ci') {
            prop('ci-a', '1')
        }

        region('dev') {
            prop('dev-a', '1')
            prop('a', '2')
        }

        region('dev2', 'dev') {
            prop('a', '3')
        }
    }

This will generate the following three properties

    $APP_ROOT/build/ci/region-dependent.properties
    $APP_ROOT/build/dev/region-dependent.properties
    $APP_ROOT/build/dev2/region-dependent.properties

For each properties file:

**build/ci/region-dependent.properties**
    
    a=1
    ci-a=1
    
**build/dev/region-dependent.properties**

	a=2
	dev-a=1
	
**build/dev2/region-dependent.properties**

	a=3
