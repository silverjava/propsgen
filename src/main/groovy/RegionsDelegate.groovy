class RegionsDelegate {
    def regions = []
    String basePath

    def region(String name, String parent = "default", Closure closure) {
        Region region = new Region(name, findParentRegion(parent))
        regions.add(region)

        closure.delegate = region
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    private def findParentRegion(String name) {
        regions.find { Region region ->
            region.name == name
        }
    }
}
