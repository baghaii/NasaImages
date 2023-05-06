package com.example.nasaimages.common

import com.example.nasaimages.data.Datum
import junit.framework.TestCase.assertEquals
import org.junit.Test


internal class DatumKtxKtTest {
    private val stubDatum = Datum(
        nasaId = "123",
        dateCreated = "1/1/2023",
        title = "Image title",
        description508 = "Accessible text",
        description = "Normal description",
        location = null,
        photographer = null
    )

    @Test
    fun `Given a Datum with a 508 description, When contentDescription, Then return 508 description`() {
        val subj = stubDatum
        assertEquals(stubDatum.description508, subj.contentDescription )
    }

    @Test
    fun `Given a Datum with no 508 description but a normal description, When contentDescription, Then return description`() {
        val subj = stubDatum.copy(description508 = null)
        assertEquals(stubDatum.description, subj.contentDescription )
    }
}