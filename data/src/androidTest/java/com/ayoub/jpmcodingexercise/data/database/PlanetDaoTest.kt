package com.ayoub.jpmcodingexercise.data.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.ayoub.jpmcodingexercise.data.planets
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals


@RunWith(AndroidJUnit4::class)
@SmallTest
class PlanetDaoTest {
    private lateinit var database: PlanetDatabase
    private lateinit var planetDao: PlanetDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PlanetDatabase::class.java
        ).build()

        planetDao = database.planetDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun test_planet_dao_insert() = runTest {
        planetDao.insertAll(planets)
        val data = planetDao.getPlanets().getData()
        assertContentEquals(planets, data)
    }

    @Test
    fun test_planet_dao_delete()= runTest {
        planetDao.insertAll(planets)
        planetDao.clearPlanets()
        val data = planetDao.getPlanets().getData()
        assertEquals(data.size, 0)
    }

    @Test
    fun test_planet_dao_select_by_id() = runTest{
        planetDao.insertAll(planets)
        val id = planets.first().id
        val planet = planetDao.getPlanet(id).first()
        assertEquals(planet, planets.first())
    }


}