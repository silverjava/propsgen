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

This will generate the following properties

    $APP_ROOT/build/ci/region-dependent.properties
    $APP_ROOT/build/dev/region-dependent.properties
    $APP_ROOT/build/dev2/region-dependent.properties
