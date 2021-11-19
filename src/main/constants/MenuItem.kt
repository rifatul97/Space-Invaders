package main.constants

class MenuItem constructor(
    name : String
) {
    val name: String = name
    lateinit var next: MenuItem
    lateinit var prev: MenuItem
}


class MenuItems {
    private val option1: MenuItem = MenuItem(name = "Start Game")
    private val option2: MenuItem = MenuItem(name = "Scoreboard")
    private val option3: MenuItem = MenuItem(name = "Exit Game")

    init {
        option1.next = option2
        option1.prev = option3
        option2.next = option3
        option2.prev = option1
        option3.next = option1
        option3.prev = option2
    }

    fun getMenuItems(): MenuItem {
        return option1
    }

    fun getMenuItemAsList(): ArrayList<String> {
        val optionList = ArrayList<String>()
        var options = getMenuItems()
        var optionName = options.name

        while(!(optionName in optionList)) {
            optionList.add(optionName)
            options = options.next
            optionName = options.name
        }

        return optionList
    }

}