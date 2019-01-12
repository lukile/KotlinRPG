package presentation

import common.ANSWER_1
import common.ANSWER_2
import common.ANSWER_3
import common.NO_ANSWER
import common.YES_ANSWER
import data.model.*

class RpgConsole(val presenter: RpgPresenter, val player: Player) : RpgView {

    override fun showRoomLocked() {
        println("La porte est fermée, il faudrait une clé pour l'ouvrir...")
    }

    override fun askMovementAnswer() {
        print("Answer : ")
        presenter.onMovementAnswerReceived(readLine()!!)
        println()
    }

    override fun showPossibility(direction: Direction) {
        println(" - ${direction.key} ->  ${direction.label}")
    }

    override fun showMovementQuestion() {
        println("Quelles directions choisissez vous ?")
    }

    override fun showCurrentRoom(room: Room) {
        println("* Vous êtes actuellement dans : ${room.type} *")

        collectItemOrNot(room)
    }

    fun collectItemOrNot(room: Room) {
        if(!room.hasItem()) {
            return
        }
        println("Ici se trouve une ${room.item!!::class.simpleName}, souhaitez vous la ramasser?")
        println("$YES_ANSWER -> Ramasser")
        println("$NO_ANSWER -> Non merci, elle est un peu louche cette potion...")
        val answer = readLine()!!

        if(answer.toUpperCase() == YES_ANSWER) {
            player.addItem(room.item!!)
            room.item = null
        }

        player.inventory.describe()
    }

    override fun showChosenWeapon(weapon: Weapon) {
        println("Très bien tu as donc choisis : ")
        println("""
            - Nom : ${weapon.label}
            - Dégats : ${weapon.damage}
        """.trimIndent())
    }

    override fun askWeaponChoiceAnswer() {
        print("Answer : ")
        presenter.onAnswerWeaponChoiceReceived(readLine()!!)
        println()
    }

    override fun showWeaponChoiceQuestion() {
        println("Quel arme veux tu choisir ?")
        println(" $ANSWER_1 -> Épée ")
        println(" $ANSWER_2 -> Hache ")
        println(" $ANSWER_3 -> Arc ")
    }

    override fun showLeaveDungeonMessage() {
        println("Bye bye...")
    }

    override fun showStartDungeonMessage() {
        println("C'est parti..")
    }

    override fun askStartDungeonAnswer() {
        print("Answer : ")
        presenter.onStartDungeonReceived(readLine()!!)
        println()
    }

    override fun showStartDungeonQuestion() {
        println("Veux tu entrer dans le donjon ?")
        println(" $YES_ANSWER -> pour commencer")
        println(" $NO_ANSWER -> pour quitter")
    }

    override fun showNickname(nickname: String) {
        println("$nickname... quel nom étrange !")
    }

    override fun askNicknameAnswer() {
        print("Pseudo : ")
        presenter.onAnswerNicknameReceived(readLine()!!)
        println()
    }

    override fun askNickname() {
        println("Je voudrais savoir quel est ton pseudo ?")
    }

    override fun showWelcomeMessage() {
        println("Bienvenue à toi !")
    }
}