package ipca.plantas.a234234

class Plant {
var id : Long=0
    var name        :String
    var latinName   :String?=null
    var description :String?=null
    var imgRes      :Int? = null

    constructor(name: String, latinName: String?, description: String?, imgRes :Int?) {
        this.name = name
        this.latinName = latinName
        this.description = description
        this.imgRes = imgRes
    }
}