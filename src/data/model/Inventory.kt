package data.model

class Inventory {

    var potions: MutableList<Potion> = ArrayList()
    var keys: MutableList<Key> = ArrayList()

    fun getWestRoomKey(): Int {
        var westRoomKey = 0
        westRoomKey += 1

        return westRoomKey
    }

    fun describe() {
        println("Vous disposez de : ${potions.size} potion(s) et de ${keys.size} clé")
    }

    fun add(item: Item) {
        if(item is Potion) {
            potions.add(item)
        }
        if(item is Key) {
            keys.add(item)
        }
    }

    fun hasKey(keyId: String): Boolean {
        return keys.any { it.id == keyId }
    }
}