package com.example.idleclicker

object Enemies {
    fun getEnemies(): ArrayList<Enemy>{
        val enemiesList= ArrayList<Enemy>()
        val e1 = Enemy(1, "Papirus", R.drawable.papirus2, 5)
        val e2 = Enemy(2, "Doggo", R.drawable.doggo, 4)
        val e3 = Enemy(3, "Undyne", R.drawable.undyne, 3)
        val e4 = Enemy(4, "Muffet", R.drawable.muffet, 2)
        val e5 = Enemy(5, "Sans", R.drawable.sans, 1)

        enemiesList.add(e1)
        enemiesList.add(e2)
        enemiesList.add(e3)
        enemiesList.add(e4)
        enemiesList.add(e5)

        return enemiesList
    }
}