package data.model

sealed class Weapon(open val label: String, open val damage: Int) {

    data class Sword(override val label: String, override val damage: Int): Weapon(label, damage)

    data class Axe(override val label: String, override val damage: Int): Weapon(label, damage)

    data class Bow(override val label: String, override val damage: Int, var arrows: Int): Weapon(label, damage)

}

/*
 Exemple
sealed class State {
    object LoadingState: State()
    data class Success(var values: String): State()
    data class OnError(var e: Exception): State()
}*/