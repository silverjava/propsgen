class Region {
    String name
    String fileName = 'region-dependent.properties'
    def properties = [:]

    Region(String name, Region parentRegion) {
        this.name = name
        this.properties = parentRegion == null ? [:] : parentRegion.properties.clone()
    }

    def prop(String name, String value) {
        properties[name] = value
    }
}
