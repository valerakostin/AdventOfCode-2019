package aoc.day16


import org.junit.Assert
import org.junit.Test


internal class Day16FlawedFrequencyTransmissionKtTest {
    @Test
    fun `80871224585914546619083218645595 becomes 24176176`() {
        val input = "80871224585914546619083218645595".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("24176176", task1(input))
    }

    @Test
    fun `19617804207202209144916044189917 becomes 73745418`() {
        val input = "19617804207202209144916044189917".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("73745418", task1(input))
    }

    @Test
    fun `69317163492948606335995924319873 becomes 52432133`() {
        val input = "69317163492948606335995924319873".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("52432133", task1(input))
    }

    @Test
    fun `answer for task1 is 76795888`() {
        val input = "59728776137831964407973962002190906766322659303479564518502254685706025795824872901465838782474078135479504351754597318603898249365886373257507600323820091333924823533976723324070520961217627430323336204524247721593859226704485849491418129908885940064664115882392043975997862502832791753443475733972832341211432322108298512512553114533929906718683734211778737511609226184538973092804715035096933160826733751936056316586618837326144846607181591957802127283758478256860673616576061374687104534470102346796536051507583471850382678959394486801952841777641763547422116981527264877636892414006855332078225310912793451227305425976335026620670455240087933409".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("76795888", task1(input))
    }


    @Test
    fun `03036732577212944063491565474664 becomes 84462026`() {
        val input = "03036732577212944063491565474664".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("84462026", task2(input))
    }

    @Test
    fun `02935109699940807407585447034323 becomes 78725270`() {
        val input = "02935109699940807407585447034323".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("78725270", task2(input))
    }

    @Test
    fun `03081770884921959731165446850517 becomes 53553731`() {
        val input = "03081770884921959731165446850517".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("53553731", task2(input))
    }

    @Test
    fun `answer for task2 is 84024125`() {
        val input = "59728776137831964407973962002190906766322659303479564518502254685706025795824872901465838782474078135479504351754597318603898249365886373257507600323820091333924823533976723324070520961217627430323336204524247721593859226704485849491418129908885940064664115882392043975997862502832791753443475733972832341211432322108298512512553114533929906718683734211778737511609226184538973092804715035096933160826733751936056316586618837326144846607181591957802127283758478256860673616576061374687104534470102346796536051507583471850382678959394486801952841777641763547422116981527264877636892414006855332078225310912793451227305425976335026620670455240087933409".map { Character.getNumericValue(it) }.toList()
        Assert.assertEquals("84024125", task2(input))
    }

}