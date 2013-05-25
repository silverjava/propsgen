class RegionGenerator {

    static void main(String[] args) {
        Script script = new GroovyShell().parse(new File(args[0]).text)
        script.metaClass = createExpandoMetaClass(script.class, {
            ExpandoMetaClass metaClass ->
                metaClass.regions = { Closure closure ->
                    RegionsDelegate regionsDelegate = new RegionsDelegate()
                    closure.delegate = regionsDelegate
                    closure.resolveStrategy = DELEGATE_FIRST
                    closure()

                    RegionFileWriter fileWriter = new RegionFileWriter(regionsDelegate.basePath)
                    regionsDelegate.regions.each { Region region ->
                        if (region.name != "default") {
                            println "generating region properties for ${region.name}..."
                            fileWriter.newPropertiesFile(region)
                        }
                    }
                }
        })
        script.run()
    }

    static MetaClass createExpandoMetaClass(Class clazz, Closure closure) {
        ExpandoMetaClass metaClass = new ExpandoMetaClass(clazz, false)
        closure(metaClass)
        metaClass.initialize()
        return metaClass
    }
}
