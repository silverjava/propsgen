class RegionFileWriter {
    String basePath

    RegionFileWriter(String basePath) {
        this.basePath = basePath
    }

    def newPropertiesFile(Region region) {
        createAndAppendProperties(region).store(
                new FileWriter(recreateRegionProperties(findOrCreateFolder(region.name), region.fileName)),
                region.name)
    }

    private Properties createAndAppendProperties(Region region) {
        Properties properties = new Properties()
        region.properties.each { key, value ->
            properties.setProperty(key, value)
        }
        properties
    }

    private File recreateRegionProperties(File folder, String fileName) {
        File propFile = new File(folder, fileName)
        if (propFile.exists()) {
            propFile.delete()
        }
        propFile
    }

    private File findOrCreateFolder(String name) {
        File folder = new File(basePath, name)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        folder
    }
}
