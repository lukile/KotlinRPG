package data.model

data class Player(var name: String? = null,
                  var hp: Int = 100,
                  var weapon: Weapon? = null,
                  var room: Room? = null,
                  var inventory: Inventory = Inventory()) {

    fun addItem(item: Item) {
        inventory.add(item)
    }
}
