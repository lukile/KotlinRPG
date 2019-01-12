package presentation

import common.ANSWER_1
import common.ANSWER_2
import common.ANSWER_3
import common.NO_ANSWER
import common.YES_ANSWER
import data.*
import data.model.*

class RpgPresenter {

    private val player: Player = Player()
    private val view: RpgConsole = RpgConsole(this, player)

    init {
        initIntroductionPart()
        initStartDungeonPart()
        initWeaponChoicePart()
        initRooms()
        initRoomsEvents()
    }

    private fun initRoomsEvents() {
        player.room?.also {
            view.showCurrentRoom(it)
            handleMovement()
        }

    }

    private fun handleMovement() {
        view.showMovementQuestion()
        player.room?.also {
            it.northRoom?.also {
                view.showPossibility(Direction.NORTH)
            }
            it.eastRoom?.also {
                view.showPossibility(Direction.EAST)
            }
            it.southRoom?.also {
                view.showPossibility(Direction.SOUTH)
            }
            it.westRoom?.also {
                view.showPossibility(Direction.WEST)
            }
        }
        view.askMovementAnswer()

    }

    private fun initRooms() {
        player.room = DataManager.getInitialRoom()
    }

    private fun initWeaponChoicePart() {
        view.showWeaponChoiceQuestion()
        view.askWeaponChoiceAnswer()
        view.showChosenWeapon(player.weapon!!)
    }

    private fun initIntroductionPart() {
        view.showWelcomeMessage()
        view.askNickname()
        view.askNicknameAnswer()
        view.showNickname(player.name!!)
    }

    fun onAnswerNicknameReceived(answer: String) {
        player.name = answer
    }

    fun initStartDungeonPart() {
        view.showStartDungeonQuestion()
        view.askStartDungeonAnswer()
    }

    fun onStartDungeonReceived(answer: String) {
        when (answer.toUpperCase()) {
            YES_ANSWER -> view.showStartDungeonMessage()
            NO_ANSWER -> view.showLeaveDungeonMessage()
            else -> initStartDungeonPart()
        }
    }

    tailrec fun onAnswerWeaponChoiceReceived(answer: String) {
        var weapon: Weapon? = when(answer) {
            ANSWER_1 -> Weapon.Sword("Épée", 12)
            ANSWER_2 -> Weapon.Axe("Hache", 14)
            ANSWER_3 -> Weapon.Bow("Arc", 11, 15)
            else -> null
        }
        if (weapon == null) view.askWeaponChoiceAnswer()
        player.weapon = weapon
    }

    fun onMovementAnswerReceived(answer: String) {
        when(answer) {
            Direction.NORTH.key -> player.updateNewRoom(player.room?.northRoom)
            Direction.SOUTH.key -> player.updateNewRoom(player.room?.southRoom)
            Direction.WEST.key -> player.updateNewRoom(player.room?.westRoom)
            Direction.EAST.key -> player.updateNewRoom(player.room?.eastRoom)
        }
        initRoomsEvents()
    }

    private fun Player.updateNewRoom(newRoom: Room?) {
        newRoom?.also {
            if(newRoom.isLocked) {
                if (newRoom.type == RoomType.ROOM_4 && this.inventory.hasKey("1") ||
                    newRoom.type == RoomType.ROOM_END && this.inventory.hasKey("2")) {
                    unlock(newRoom)
                }
            }

            if(!newRoom.isLocked) {
                this.room = newRoom
                return
            } else {
                view.showRoomLocked()
            }
        }
        handleMovement()
    }

    fun unlock(newRoom: Room) {
        println("* Vous avez dévérouillez la ${newRoom.type}")
        newRoom.isLocked = false
    }
}
